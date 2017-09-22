package com.inflectra.remotelaunch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.inflectra.remotelaunch.services.AutomationAttachment;
import com.inflectra.remotelaunch.services.SpiraImportExport;
import com.inflectra.remotelaunch.services.SpiraImportExport.AttachmentType;
import com.inflectra.remotelaunch.services.SpiraImportExport.TestCaseStatus;
import com.inflectra.remotelaunch.services.SpiraImportExport.TestSetStatus;
import com.inflectra.remotelaunch.services.SpiraImportExportException;
import com.inflectra.remotelaunch.services.soap.RemoteAutomatedTestRun;
import com.inflectra.remotelaunch.services.soap.RemoteTestSetTestCaseParameter;

/**
 * Responsible for downloading test sets from SpiraTest and executing them
 * @author Inflectra Corporation
 *
 */
public class TestScheduler
{
	String url;
	String login;
	String password;
	String hostToken;
	
	List<RemoteAutomatedTestRun> automatedTestRuns;
	
	SpiraImportExport spiraClient;

	/**
	 * Constructor
	 * @param url The URL to connect to Spira
	 * @param login The login for Spira
	 * @param password The password for Spira
	 * @param hostToken The automation host token
	 */
	public TestScheduler (String url, String login, String password, String hostToken)
	{
		this.url = url;
		this.login = login;
		this.password = password;
		this.hostToken = hostToken;
		
		//Instantiate spira client
		this.spiraClient = new SpiraImportExport(url, login, password);
	}
	
	/**
	 * Returns the full token of a test caseparameter from its name
	 * (We use the same parameter format as Ant/NAnt)
	 * @param parameterName The name of the parameter
	 * @return The tokenized representation of the parameter used for search/replace
	 */
    private String createParameterToken(String parameterName)
    {
        return "${" + parameterName + "}";
    }
	
	/**
	 * Executes the specified command-line test
	 * @param testCaseId The ID of the test case being executed
	 * @param automationAttachment The test script information
	 * @return
	 */
	public TestResult executeTest (int projectId, int testCaseId, AutomationAttachment automationAttachment, int testSetId, Integer releaseId, HashMap<String, String> parameters)
	{
		try
		{
			//Extract the test case information
			String filenameOrUrl = automationAttachment.getRemoteDocument().getFilenameOrUrl().getValue();
			
			//Create the new test result
			TestResult testResult = new TestResult();
			testResult.setRunnerTestName(filenameOrUrl);
			
			//Log what we're doing
			System.out.println("Executing test case TC" + testCaseId + " with filename '" + filenameOrUrl + "'");
	
	        //See if we have an attached or linked test script
	        //The parameters work differently for the two types
	        String arguments = "";
	        String path = "";
	        File tempFile = null;
			Integer attachmentType = automationAttachment.getRemoteDocument().getAttachmentTypeId();
			if (attachmentType.intValue() == AttachmentType.File.getId())
			{
				System.out.println("This is an Attached test script");
				String testScript = new String(automationAttachment.getTestScript());
				
	            //We have an embedded script which we need to save onto the file system so that it can be executed
	            //by the command-line tool
	
	            /*
	             * The filename of the test case is the full path of the application to run
	             * with the test script to execute being one of the arguments, specified as {filename} or [Filename]
	             * If they want to provide any arguments, they need to specify them separated by a pipe (|)
	             * (i.e. Execution Path|[Arguments])
	             * 
	             * e.g. C:\MyFolder\MyCommand.exe|-execute "{filename}" -arg1 -arg2                   
	             * 
	             * would become:
	             * 
	             * C:\MyFolder\MyCommand.exe -execute "C:\Documents And Settings\Username\Local Settings\Application Data\MyScript.txt" -arg1 -arg2
	             * 
	             * In this mode, the parameters are used to replace tokens in the actual test script rather than
	             * being passed to the command-line handler
	            */
	
	            //Replace any parameters (in ${parametername} lower-case syntax)
	            if (parameters != null)
	            {
	                for (Entry<String, String> entry : parameters.entrySet())
	                {
	                    testScript = testScript.replace(createParameterToken(entry.getKey()), entry.getValue());
	                }
	            }
	            
                //Replace the special test case and test run id tokens in the script
	            testScript = testScript.replace("[ProjectId]", projectId + "");
	            testScript = testScript.replace("[TestCaseId]", testCaseId + "");
	            testScript = testScript.replace("[TestSetId]", testSetId + "");
                if (releaseId != null)
                {
                	testScript = testScript.replace("[ReleaseId]", releaseId.toString());
                }
	
	            //Create a new temporary file for this test script
				tempFile = File.createTempFile("RemoteLaunchX", null);
			    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			    writer.write (testScript);
	
			    //Close writer
			    writer.close();
	
	            //See if we have any pipes in the 'filename' that contains arguments or parameters
	            String[] filenameElements = filenameOrUrl.split(Pattern.quote("|"));
	
	            //To make it easier, we have certain shortcuts that can be used in the path
	            path = filenameElements[0];
	
	            //See if we have any arguments
	            if (filenameElements.length > 1)
	            {
	                arguments = filenameElements[1];
	                arguments = arguments.replace("[Filename]", tempFile.getAbsolutePath());
	                arguments = arguments.replace("{Filename}", tempFile.getAbsolutePath());
	            }
			}
			else if (attachmentType.intValue() == AttachmentType.URL.getId())
			{
				System.out.println("This is a Linked test script");
				
	            /*
	             * The "URL" of the test case is the full command line including the name of the application to run
	             * with the test script to execute being one of the arguments
	             * If they want to provide any arguments and parameters, they need to specify them separated by a pipe (|)
	             * (i.e. Execution Path|[Arguments]|[Parameter Mask])
	             * 
	             * e.g. C:\MyFolder\MyCommand.exe|-execute MyScript.txt -arg1 -arg2|-name:value                    
	             * 
	             * would become:
	             * 
	             * C:\MyFolder\MyCommand.exe -execute MyScript.txt -arg1 -arg2 -param1:value1 - param2:value2
	             * 
	             * If you specify [ProjectId], [TestCaseId], [TestRunId], [TestSetId] or [ReleaseId] in the list of parameters
	             * they will be replaced by the appropriate ID (if a value is set)
	             * 
	            */
				
	            //See if we have any pipes in the 'filename' that contains arguments or parameters
	            String[] filenameElements = filenameOrUrl.split(Pattern.quote("|"));
	            path = filenameElements[0];
	            
	            //See if we have any arguments (not parameters)
	            if (filenameElements.length > 1)
	            {
	                arguments = filenameElements[1];
	
	                //Replace the special test case and test run id tokens
	                arguments = arguments.replace("[ProjectId]", projectId + "");
	                arguments = arguments.replace("[TestCaseId]", testCaseId + "");
	                arguments = arguments.replace("[TestSetId]", testSetId + "");
	                if (releaseId != null)
	                {
	                    arguments = arguments.replace("[ReleaseId]", releaseId.toString());
	                }
	            }
	
	            //See if we have a parameter mask
	            if (filenameElements.length > 2)
	            {
	                String parameterMask = filenameElements[2];
	
	                //Now iterate through the provided parameters and add to the arguments based on the mask
	                if (parameters != null)
	                {
	                    for (Entry<String, String> entry : parameters.entrySet())
	                    {
	                        String parameterArgument = parameterMask.replace("name", entry.getKey());
	                        parameterArgument = parameterArgument.replace("value", entry.getValue());
	                        arguments += " " + parameterArgument;
	                    }
	                }
	            }
			}
			
			//Now run the actual command-line test
			StringBuilder stdOut = new StringBuilder("");
			StringBuilder stdOutSingleLine = new StringBuilder("");
			StringBuilder stdErr = new StringBuilder("");;
			try
			{
				System.out.println("Executing command '" + path + "' with arguments '" + arguments + "'");
				Runtime rt = Runtime.getRuntime();
				ArrayList<String> commands = new ArrayList<String>();
				commands.add(path);
				if (arguments != null && arguments.length() > 0)
				{
					String[] argumentArray = arguments.split(Pattern.quote(" "));
					for (String arg : argumentArray)
					{
						commands.add(arg);
					}
				}
				Process proc = rt.exec(commands.toArray(new String[commands.size()]));
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
	
		        //read the output from the command
		        String s;
		        while ((s = stdInput.readLine()) != null)
		        {
		        	stdOut.append(s + "\n");
		        	stdOutSingleLine.append(s);
		        }
		
		        //read any errors from the attempted command
		        while ((s = stdError.readLine()) != null)
		        {
		        	stdErr.append(s + "\n");
		        }
		        
		        //Populate the test result
		        String stackTrace = "Standard Out\n============\n";
		        stackTrace = stackTrace.concat(stdOut.toString().replaceAll("\\r","\\r\\n"));
		        stackTrace = stackTrace.concat("Standard Error\n==============\n");
		        stackTrace = stackTrace.concat(stdErr.toString().toString().replaceAll("\\r","\\r\\n"));
		        testResult.setRunnerStackTrace(stackTrace);
				
	            //We use the Regexes to determine the status
	            TestCaseStatus executionStatus = TestCaseStatus.NotRun;
	            Pattern passRegex = Pattern.compile(Settings.getPassRegex(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	            Pattern failRegex = Pattern.compile(Settings.getFailRegex(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	            Pattern cautionRegex = Pattern.compile(Settings.getCautionRegex(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	            Pattern blockedRegex = Pattern.compile(Settings.getBlockedRegex(), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
	            
	            //Check passed
	            if (passRegex.matcher(stdOutSingleLine).matches())
	            {
	            	executionStatus = TestCaseStatus.Passed;
	            }
	            if (cautionRegex.matcher(stdOutSingleLine).matches())
	            {
	            	executionStatus = TestCaseStatus.Caution;
	            }
	            if (failRegex.matcher(stdOutSingleLine).matches())
	            {
	            	executionStatus = TestCaseStatus.Failed;
	            }
	            if (blockedRegex.matcher(stdOutSingleLine).matches())
	            {
	            	executionStatus = TestCaseStatus.Blocked;
	            }
	            testResult.setExecutionStatus(executionStatus);
				System.out.println("Execution Status = " + executionStatus.toString());
			}
			catch (Exception exception)
			{
				//Record as a blocked test run
				System.out.println("Error: Unable to execute test, recording as Blocked test run - " + exception.getLocalizedMessage());
	            testResult.setExecutionStatus(TestCaseStatus.Blocked);
				stdOut.append(exception.getLocalizedMessage());
		        testResult.setRunnerStackTrace(exception.getLocalizedMessage());
			}
	        
            if (stdOut.length() > 50)
            {
            	testResult.setRunnerMessage(stdOut.substring(0, 50));
            }
            else
            {
            	testResult.setRunnerMessage(stdOut.toString());
            }
            testResult.setRunnerAssertCount((testResult.getExecutionStatus() == TestCaseStatus.Passed) ? 0 : 1);            
	        
            //If we have a temporary file, delete it
            if (tempFile != null)
            {
            	tempFile.delete();
            }
            
			//Return test result
			return testResult;
		}
		catch (IOException exception)
		{
			System.out.println("Unable to write temporary file to hold attached test script, skipping test! - " + exception.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Executes the polled tests
	 */
	public void executeTests() throws Exception
	{
		//Make sure we have some tests
		if (this.automatedTestRuns == null || this.automatedTestRuns.size() < 1)
		{
			throw new NoTestRunsException("No test runs listed for execution!");
		}
		
		//We need to store which test sets have been started/completed
		HashMap<Integer, TestSetStatus> testSetTracker = new HashMap<Integer, TestSetStatus>();
		
		//Loop through the test runs
		for (RemoteAutomatedTestRun automatedTestRun : this.automatedTestRuns)
		{
			//Set the project id on the spira client
			int projectId = automatedTestRun.getProjectId().getValue().intValue();
			this.spiraClient.setProjectId(projectId);
			
			//If this test run is the first one for the specified test set, update
			//the test set to 'In Progress' and it to the test set tracking dictionary
			
			if (automatedTestRun.getTestSetId().isNil() || automatedTestRun.getTestSetId().getValue() == null)
			{
				System.out.println("Error: The test run doesn't have a test set specified so ignoring test case");
			}
			else
			{
				Integer testSetId = automatedTestRun.getTestSetId().getValue();
				if (!testSetTracker.containsKey(testSetId))
				{
					//This test set hasn't yet been started, so update status on server
					try
					{
						this.spiraClient.updateTestSetStatus(testSetId.intValue(), TestSetStatus.InProgress);
						testSetTracker.put(testSetId, TestSetStatus.InProgress);
					}
					catch (SpiraImportExportException exception)
					{
						System.out.println("Warning: Unable to set test set TX" + testSetId + " to In-Progress. (" + exception.getLocalizedMessage() + ")");					
					}
				}				
			}
			
			//Download the automated testing information for this test case
			if (automatedTestRun.getTestCaseId() == null)
			{
				System.out.println("Error: The test run doesn't have a test case id specified so ignoring test case.");				
			}
			else
			{
				int testCaseId = automatedTestRun.getTestCaseId().intValue();
				try
				{
					AutomationAttachment automationAttachment = this.spiraClient.retrieveAutomatedTestScript(testCaseId);			
					if (automationAttachment == null)
					{
						System.out.println("Error: Unable to retrieve test script for test case TC" + testCaseId + " so skipping test case.");												
					}
					else
					{
						//Get the parameters (if we have any)
						HashMap<String, String> parameters = new HashMap<String, String>();
						if (!automatedTestRun.getParameters().isNil() && automatedTestRun.getParameters().getValue() != null && automatedTestRun.getParameters().getValue().getRemoteTestSetTestCaseParameter() != null)
						{
							List<RemoteTestSetTestCaseParameter> remoteTestSetTestCaseParameters = automatedTestRun.getParameters().getValue().getRemoteTestSetTestCaseParameter();
							for (RemoteTestSetTestCaseParameter remoteTestSetTestCaseParameter : remoteTestSetTestCaseParameters)
							{
								if (!parameters.containsKey(remoteTestSetTestCaseParameter.getName().getValue()))
								{
									String name = remoteTestSetTestCaseParameter.getName().getValue();
									String value = remoteTestSetTestCaseParameter.getValue().getValue();
									parameters.put(name, value);
								}
							}
						}
						
						//Actually run this test and get the result, capturing the before/after dates
						Date startDate = new Date();
						Integer releaseId = automatedTestRun.getReleaseId().getValue();
						Integer testSetId = automatedTestRun.getTestSetId().getValue();
						TestResult testResult = this.executeTest(projectId, testCaseId, automationAttachment, testSetId, releaseId, parameters);
						Date endDate = new Date();
						
						//Finally record the result
						TestCaseStatus executionStatus = testResult.getExecutionStatus();
						String runnerTestName = testResult.getRunnerTestName();
						int runnerAssertCount = testResult.getRunnerAssertCount();
						String runnerMessage = testResult.getRunnerMessage();
						String runnerStackTrace = testResult.getRunnerStackTrace();
						this.spiraClient.recordTestRun(null, testCaseId, releaseId, testSetId, startDate, endDate, executionStatus, runnerTestName, runnerAssertCount, runnerMessage, runnerStackTrace);
						
						//If blocked, update the test set tracker
						if (executionStatus == TestCaseStatus.Blocked)
						{
							if (!testSetTracker.containsKey(testSetId) || testSetTracker.get(testSetId) == TestSetStatus.InProgress)
							{
								testSetTracker.put(testSetId, TestSetStatus.Blocked);
							}
						}
					}
				}
				catch (SpiraImportExportException exception)
				{
					System.out.println("Error: Unable to retrieve test script for test case TC" + testCaseId + " so skipping test case. (" + exception.getLocalizedMessage() + ")");						
				}
			}
		}
						
		//Now mark the various test sets as complete or blocked
		for(Entry<Integer, TestSetStatus> entry : testSetTracker.entrySet())
		{
			Integer testSetId = entry.getKey();
			TestSetStatus status = entry.getValue();
			if (testSetId != null && status != null)
			{
				//Mark this test set as completed unless it is already marked as blocked
				try
				{
					if (status == TestSetStatus.Blocked)
					{
						this.spiraClient.updateTestSetStatus(testSetId.intValue(), TestSetStatus.Blocked);
					}
					if (status == TestSetStatus.InProgress)
					{
						this.spiraClient.updateTestSetStatus(testSetId.intValue(), TestSetStatus.Completed);
						testSetTracker.put(testSetId, TestSetStatus.Completed);
					}
				}
				catch (SpiraImportExportException exception)
				{
					System.out.println("Warning: Unable to set test set TX" + testSetId + " to Completed. (" + exception.getLocalizedMessage() + ")");					
				}
			}
		}
	}
	
	/**
	 * Polls Spira for tests that are scheduled for the planned date or earlier
	 * @param plannedDate The date/time to look for
	 * @return Returns the number of test sets that were found matching this date
	 */
	public int pollForTests(Date plannedDate)
		throws Exception
	{
		//Connect to SpiraTest to get the scheduled test sets for this host
		System.out.println("Testing connection to Spira...");
		boolean success = this.spiraClient.testConnection();
		if (success)
		{
			System.out.println("Successfully connected to Spira.");
			
			//Next we need to get the list of upcoming test runs for this automation host
			this.automatedTestRuns = this.spiraClient.retrieveTestRunsForHost(this.hostToken, plannedDate);			
			int testRunCount = this.automatedTestRuns.size();
			System.out.println("Retrieved " + testRunCount + " test run(s) from SpiraTest.");
			
			return testRunCount;
		}
		else			
		{
			System.out.println("Unable to connect to Spira. Please check login/password and try again.");
			return 0;
		}		
	}
}
