package com.inflectra.remotelaunch.services;

import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.*;

import com.inflectra.remotelaunch.services.soap.ArrayOfRemoteAutomatedTestRun;
import com.inflectra.remotelaunch.services.soap.DateRange;
import com.inflectra.remotelaunch.services.soap.IImportExport;
import com.inflectra.remotelaunch.services.soap.IImportExportAutomationEngineRetrieveByIdServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportDocumentOpenFileServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportDocumentRetrieveByIdServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportProjectRetrieveServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportSystemGetArtifactUrlServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestCaseRetrieveByIdServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestRunCreateForAutomationHostServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestRunRecordAutomated1ServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestRunRecordAutomated1ValidationFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestSetRetrieveByIdServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestSetUpdateServiceFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.IImportExportTestSetUpdateValidationFaultMessageFaultFaultMessage;
import com.inflectra.remotelaunch.services.soap.ImportExport;
import com.inflectra.remotelaunch.services.soap.RemoteAutomatedTestRun;
import com.inflectra.remotelaunch.services.soap.RemoteAutomationEngine;
import com.inflectra.remotelaunch.services.soap.RemoteDocument;
import com.inflectra.remotelaunch.services.soap.RemoteProject;
import com.inflectra.remotelaunch.services.soap.RemoteTestCase;
import com.inflectra.remotelaunch.services.soap.RemoteTestSet;


/**
 * This defines the 'SpiraImportExport' class that provides the Java facade
 * for calling the SOAP web service exposed by SpiraTest
 * 
 * @author		Inflectra Corporation
 * @version		4.0.0
 *
 */
public class SpiraImportExport
{
	private static final String WSDL_LOCAL_FILENAME = "ImportExport40.wsdl";
	private static final String WEB_SERVICE_SUFFIX = "/Services/v4_0/ImportExport.svc?WSDL";
	private static final String WEB_SERVICE_NAMESPACE = "{http://www.inflectra.com/SpiraTest/Services/v4.0/}ImportExport";
	private static final String WEB_SERVICE_NAMESPACE_DATA_OBJECTS = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v4_0.DataObjects";

	private static final String SPIRA_PLUG_IN_NAME = "RemoteLaunch-X";
	private static final String SPIRA_AUTOMATION_ENGINE_TOKEN = "CommandLine";
	
	private String url;
	private String userName;
	private String password;
	private int projectId;
	private IImportExport soap;
	
	//List of test run types
	public enum TestRunType
	{
		Manual (1),
		Automated (2);
		
		private final int testRunTypeId;
		
		private TestRunType(int testRunTypeId)
		{
			this.testRunTypeId = testRunTypeId;
		}
		
	    public int getId()
	    {
	    	return this.testRunTypeId;
	    }
	}
	
	//List of attachment types
    public enum AttachmentType
    {
        File (1),
        URL (2);
        
		private final int attachmentTypeId;
		
		private AttachmentType(int attachmentTypeId)
		{
			this.attachmentTypeId = attachmentTypeId;
		}
		
	    public int getId()
	    {
	    	return this.attachmentTypeId;
	    }
    }
	
	//List of test run formats
	public enum TestRunFormat
	{
		PlainText (1),
		Html (2);
		
		private final int testRunFormatId;
		
		private TestRunFormat(int testRunFormatId)
		{
			this.testRunFormatId = testRunFormatId;
		}
		
	    public int getId()
	    {
	    	return this.testRunFormatId;
	    }
	}
	
	//List of test case statuses
	public enum TestCaseStatus
	{
		Failed (1),
		Passed (2),
		NotRun (3),
		NotApplicable (4),
		Blocked (5),
		Caution (6);
		
		private final int testCaseStatusId;
		
		private TestCaseStatus(int testCaseStatusId)
		{
			this.testCaseStatusId = testCaseStatusId;
		}
		
	    public int getId()
	    {
	    	return this.testCaseStatusId;
	    }
	}
	
	//List of test set statuses
	public enum TestSetStatus
	{
		NotStarted (1),
		InProgress (2),
		Completed (3),
		Blocked (4),
		Deferred (5);
		
		private final int testSetStatusId;
		
		private TestSetStatus(int testSetStatusId)
		{
			this.testSetStatusId = testSetStatusId;
		}
		
	    public int getId()
	    {
	    	return this.testSetStatusId;
	    }
	}
	
	//Artifact type enums
	public enum ArtifactType
	{
		REQUIREMENT ("RQ", 1),
		TESTCASE ("TC", 2),
		INCIDENT ("IN", 3),
		RELEASE ("RL", 4),
		TESTRUN ("TR", 5),
		TASK ("TK", 6),
		TESTSTEP ("TS", 7),
		TESTSET ("TX", 8),
		HOST ("AH", 9);
		
	    private final String prefix;
	    private final int artifactId;
	    private static Map<String, ArtifactType> map = new HashMap<String, ArtifactType>();

	    private ArtifactType(String prefix, int artifactId)
	    {
	        this.prefix = prefix;
	        this.artifactId = artifactId;
	    }
	    
	    public String getPrefix()
	    {
	    	return this.prefix;
	    }
	    
	    public int getArtifactId()
	    {
	    	return this.artifactId;
	    }
	    
        static
        { 
            for (ArtifactType a : ArtifactType.values()) 
                map.put(a.getPrefix(), a); 
        } 

        public static ArtifactType getByPrefix(String prefix)
        { 
            return map.get(prefix); 
        } 

	}
	
	public SpiraImportExport()
	{
		this.soap = null;
	}

	public SpiraImportExport(String url, String userName, String password)
	{
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.soap = null;
	}
	
	public SpiraImportExport(String url, String userName, String password, int projectId)
	{
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.projectId = projectId;
		this.soap = null;
	}
	
	public AutomationAttachment retrieveAutomatedTestScript(int testCaseId) throws SpiraImportExportException
	{
		try
		{
			//Instantiate the soap proxy
			this.createBinding();
			
			//Authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//Throw the error
				throw new SpiraImportExportException ("Unable to authenticate with SpiraTest server. Please check the username/password and try again\n\n");				
			}
			
	        //Connect to the project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				throw new SpiraImportExportException ("Unable to connect to SpiraTest project " + projectId + ", please check that the user is a member of this project\n\n");
			}
			
			//First retrieve the test case itself
			RemoteTestCase remoteTestCase = soap.testCaseRetrieveById(testCaseId);
			if (remoteTestCase == null)
			{
				throw new SpiraImportExportException ("Unable to retrieve SpiraTest test case TC " + testCaseId + " in project PR" + projectId + ", please check that this test case exists and is a part of this project\n\n");				
			}
			
			//Now we need to get the associated automation attachment, make sure this test case actually has a script
			//and that the engine token is "CommandLine"
			if (remoteTestCase.getAutomationEngineId().isNil() || remoteTestCase.getAutomationEngineId().getValue() == null)
			{
				throw new SpiraImportExportException ("SpiraTest test case TC " + testCaseId + " in project PR" + projectId + " does not have an automation engine specified, skipping this test case\n\n");				
			}
			if (remoteTestCase.getAutomationAttachmentId().isNil() || remoteTestCase.getAutomationAttachmentId().getValue() == null)
			{
				throw new SpiraImportExportException ("SpiraTest test case TC " + testCaseId + " in project PR" + projectId + " does not have an automation script associated, skipping this test case\n\n");				
			}
			
			//Get the automation engine
			int automationEngineId = remoteTestCase.getAutomationEngineId().getValue().intValue();
			RemoteAutomationEngine remoteAutomationEngine = soap.automationEngineRetrieveById(automationEngineId);
			if (remoteAutomationEngine == null)
			{
				throw new SpiraImportExportException ("Unable to retrieve SpiraTest automation engine " + automationEngineId + ", please check that automation engine exists and is marked as active.\n\n");								
			}
			
			//Make sure the token matches
			if (remoteAutomationEngine.getToken().isNil() || remoteAutomationEngine.getToken().getValue() == null)
			{			
				throw new SpiraImportExportException ("SpiraTest automation engine " + automationEngineId + ", has a NULL token name so skipping test case.\n\n");												
			}
			if (!remoteAutomationEngine.getToken().getValue().equalsIgnoreCase(SPIRA_AUTOMATION_ENGINE_TOKEN))
			{
				throw new SpiraImportExportException ("SpiraTest automation engine " + automationEngineId + ", has the token name '" + remoteAutomationEngine.getToken().getValue() + "' instead of '" + SPIRA_AUTOMATION_ENGINE_TOKEN + "', so skipping this test case\n\n");												
			}
			
			//Now retrieve the actual attachment
			int automationAttachmentId = remoteTestCase.getAutomationAttachmentId().getValue().intValue();
			RemoteDocument remoteDocument = soap.documentRetrieveById(automationAttachmentId);
			if (remoteDocument == null)
			{
				throw new SpiraImportExportException ("Unable to retrieve SpiraTest automation attachment " + automationAttachmentId + ", please check that test case has an automation script/filename associated.\n\n");												
			}
			
			//Populate the return object
			AutomationAttachment automationAttachment = new AutomationAttachment();
			automationAttachment.setRemoteDocument(remoteDocument);
			
			//If we have a file, download the script
			if (remoteDocument.getAttachmentTypeId().intValue() == AttachmentType.File.getId())
			{
				byte[] testScript = soap.documentOpenFile(automationAttachmentId);
				automationAttachment.setTestScript(testScript);
			}
			
			return automationAttachment;
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error connecting to SpiraTest project (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (SpiraImportExportException exception)
		{
			throw new SpiraImportExportException ("Error updating test set status on SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportTestCaseRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error retrieving test case TC" + testCaseId + " from SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportAutomationEngineRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error retrieving automation engine from SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportDocumentRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error retrieving automation attachment from SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportDocumentOpenFileServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error downloading automation attachment data from SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
	}
	
	/**
	 * Updates the status of a specific SpiraTest test set
	 * @param testSetId
	 * @param testSetStatus
	 * @throws SpiraImportExportException
	 */
	public void updateTestSetStatus(int testSetId, TestSetStatus testSetStatus) throws SpiraImportExportException
	{
		try
		{
			//Instantiate the soap proxy
			this.createBinding();
			
			//Authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//Throw the error
				throw new SpiraImportExportException ("Unable to authenticate with SpiraTest server. Please check the username/password and try again\n\n");				
			}
			
	        //Connect to the project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				throw new SpiraImportExportException ("Unable to connect to SpiraTest project " + projectId + ", please check that the user is a member of this project\n\n");
			}
			
			//Update the status
			RemoteTestSet remoteTestSet = soap.testSetRetrieveById(testSetId);
			remoteTestSet.setTestSetStatusId(testSetStatus.getId());
			soap.testSetUpdate(remoteTestSet);
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error connecting to SpiraTest project (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (SpiraImportExportException exception)
		{
			//Display the error
			throw new SpiraImportExportException ("Error updating test set status on SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportTestSetRetrieveByIdServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error retrieving test set TX" + testSetId + " from SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportTestSetUpdateServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error updating test set status for test set TX" + testSetId + " on SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportTestSetUpdateValidationFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error updating test set status for test set TX" + testSetId + " on SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
	}
	
	/**
	 * Gets the list of test runs for a specific host and plannedDate (tests need to be scheduled <= plannedDate)
	 * @param hostToken
	 * @param plannedDate
	 * @throws SpiraImportExportException
	 */
	public List<RemoteAutomatedTestRun> retrieveTestRunsForHost(String hostToken, Date plannedDate) throws SpiraImportExportException
	{
		try
		{
			//Instantiate the soap proxy
			this.createBinding();
			
			//Authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//Throw the error
				throw new SpiraImportExportException ("Unable to authenticate with SpiraTest server. Please check the username/password and try again\n\n");				
			}
			
			//Now get the list of projects that this user has access to
			List<RemoteProject> remoteProjects = soap.projectRetrieve().getRemoteProject();
			if (remoteProjects == null || remoteProjects.size() < 1)
			{
				throw new SpiraImportExportException ("The specified SpiraTest user does not have access to any projects!\n\n");			
			}
			
			//Create the date range to send to the API
			DateRange dateRange = new DateRange();
			dateRange.setEndDate(createJAXBXMLGregorianCalendar("EndDate", convertDatesJava2Xml(plannedDate)));

			//Create a list to store the returned test runs
			List<RemoteAutomatedTestRun> automatedTestRuns = new ArrayList<RemoteAutomatedTestRun>();
			
			//Loop through each of the projects
			for (RemoteProject remoteProject : remoteProjects)
			{
				int projectId = remoteProject.getProjectId().getValue().intValue();
				//Get the list of test runs for this project
				try
				{
					success = soap.connectionConnectToProject(projectId);
					ArrayOfRemoteAutomatedTestRun arrayObj = soap.testRunCreateForAutomationHost(hostToken, dateRange);
					if (arrayObj != null)
					{
						List<RemoteAutomatedTestRun> projectTestRuns = arrayObj.getRemoteAutomatedTestRun();
						if (projectTestRuns != null)
						{
							automatedTestRuns.addAll(projectTestRuns);
						}
					}
				}
				catch (IImportExportTestRunCreateForAutomationHostServiceFaultMessageFaultFaultMessage ex)
				{
					//Write as console warning
			        System.out.println("WARNING: Unable to retrieve test runs for SpiraTest project PR" + projectId + ", so skipping this project - " + ex.getLocalizedMessage());
				}
				catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage ex)
				{
					//Write as console warning
			        System.out.println("WARNING: Unable to connect to SpiraTest project PR" + projectId + ", so skipping this project - " + ex.getLocalizedMessage());
				}
			}
			
			return automatedTestRuns;
		}
		catch (IImportExportProjectRetrieveServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error retrieving SpiraTest projects (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
		}		
	}

	/**
	 * Records a test run
	 * 
		@param testerUserId			The user id of the person who's running the test (null for logged in user)
		@param testCaseId			The test case being executed
		@param releaseId			The release being executed against (optional)
		@param testSetId			The test set being executed against (optional)
		@param executionStatusId	The status of the test run (pass/fail/not run)
		@param runnerTestName		The name of the test as stored in JUnit
		@param runnerAssertCount	The number of assertions
		@param runnerMessage		The failure message (if appropriate)
		@param runnerStackTrace		The error stack trace (if any)
		@param endDate				When the test run ended
		@param startDate			When the test run started
	 */
	public int recordTestRun(Integer testerUserId, int testCaseId, Integer releaseId, Integer testSetId, Date startDate, Date endDate, TestCaseStatus executionStatus, String runnerTestName, int runnerAssertCount, String runnerMessage, String runnerStackTrace) throws SpiraImportExportException 
	{
		//Instantiate the soap proxy
		try
		{
			//Instantiate the soap proxy
			this.createBinding();
						
			//Authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			if (!success)
			{
				//Display the error
				throw new SpiraImportExportException ("Unable to authenticate with SpiraTest server. Please check the username/password and try again\n\n");
			}
	        
	        //Connect to the project
			success = soap.connectionConnectToProject(projectId);
			if (!success)
			{
				throw new SpiraImportExportException ("Unable to connect to SpiraTest project " + projectId + ", please check that the user is a member of this project\n\n");
			}
			
			//Now record the test result
			RemoteAutomatedTestRun remoteTestRun = new RemoteAutomatedTestRun();
			remoteTestRun.setTestCaseId(testCaseId);
			if (releaseId != null)
			{
				remoteTestRun.setReleaseId(createJAXBInteger("ReleaseId", releaseId));
			}
			if (testSetId != null)
			{
				remoteTestRun.setTestSetId(createJAXBInteger("TestSetId", testSetId));
			}
			remoteTestRun.setStartDate(convertDatesJava2Xml(startDate));
			remoteTestRun.setEndDate(createJAXBXMLGregorianCalendar("EndDate", convertDatesJava2Xml(endDate)));
			remoteTestRun.setExecutionStatusId(executionStatus.getId());
			remoteTestRun.setRunnerName(createJAXBString("RunnerName", SPIRA_PLUG_IN_NAME));
			remoteTestRun.setRunnerTestName(createJAXBString("RunnerTestName", runnerTestName));
			remoteTestRun.setRunnerAssertCount(createJAXBInteger("RunnerAssertCount", runnerAssertCount));
			remoteTestRun.setRunnerMessage(createJAXBString("RunnerMessage", runnerMessage));
			remoteTestRun.setRunnerStackTrace(createJAXBString("RunnerStackTrace", runnerStackTrace));
			remoteTestRun.setTestRunFormatId(TestRunFormat.PlainText.getId());
			
			remoteTestRun = soap.testRunRecordAutomated1(remoteTestRun);
			int testRunId = remoteTestRun.getTestRunId().getValue();
			return testRunId;
		}
		catch (IImportExportTestRunRecordAutomated1ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error sending results to SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportConnectionConnectToProjectServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error connecting to SpiraTest project (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (SpiraImportExportException exception)
		{
			//Display the error
			throw new SpiraImportExportException ("Error sending results to SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (IImportExportTestRunRecordAutomated1ValidationFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error sending results to SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
	}
	
	/**
	 * Creates the SOAP binding, needs to be called by all other public methods
	 */
	private void createBinding() throws SpiraImportExportException
	{
		//Instantiate the soap proxy
		try
		{
			//Trust all SSL certificates
			SSLUtilities.trustAllHttpsCertificates();
			
			//Create the full URL for the web service
			URL serviceUrl = new URL(this.url + WEB_SERVICE_SUFFIX);
			
			//We need to get the location of the local WSDL file
			URL wsdlUrl = SpiraImportExport.class.getResource(WSDL_LOCAL_FILENAME);
			ImportExport service = new ImportExport(wsdlUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
			
			//Now we try and create the binding
			this.soap = service.getBasicHttpBindingIImportExport();
			
			//If binding port is null, throw exception
			if (soap == null)
			{
				//Display the error
				throw new SpiraImportExportException ("Unable to create the SpiraTest SOAP API Binding!");
			}
						
			//Make sure that session is maintained and also specify the actual location of the real WSDL
			//not the local copy
			Map<String, Object> requestContext = ((BindingProvider)soap).getRequestContext();
			requestContext.put(BindingProvider.SESSION_MAINTAIN_PROPERTY,true);
			requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, serviceUrl.toString());
		}
		catch (MalformedURLException exception)
		{
			throw new SpiraImportExportException ("Error creating URL for connecting to SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
		catch (Exception exception)
		{
			//Display the error
			throw new SpiraImportExportException ("Error connecting to SpiraTest server (" + exception.getMessage() + ")\n\n");
		}		
	}
	
	/**
	 * Tests the SpiraTeam connection
	 * @return
	 */
	public boolean testConnection() throws SpiraImportExportException
	{
		//Instantiate the soap proxy
		try
		{
			this.createBinding();
			
			//Authenticate
			boolean success = soap.connectionAuthenticate2(this.userName, this.password, SPIRA_PLUG_IN_NAME);
			return success;
		}
		catch (IImportExportConnectionAuthenticate2ServiceFaultMessageFaultFaultMessage exception)
		{
			throw new SpiraImportExportException ("Error authenticating with SpiraTest server (" + exception.getMessage() + ")\n\n");
		}
	}	
	
	/**
	 * Returns the full spira artifact URL for an artifact
	 * @param projectId		The current spira project
	 * @param prefix		The artifact prefix
	 * @param artifactId	The artifact id
	 * @return
	 * @throws Exception 
	 */
	public String createArtifactUrl (String prefix, int artifactId) throws Exception
	{
		//Instantiate the soap proxy
		try
		{
			//Trust all SSL certificates
			SSLUtilities.trustAllHttpsCertificates();
			
			//Break up the URL into server name and the service path
			URL serviceUrl = new URL(this.url + WEB_SERVICE_SUFFIX);

			ImportExport service = new ImportExport(serviceUrl, QName.valueOf(WEB_SERVICE_NAMESPACE));
			//Try both the HTTP and HTTPS ports
			IImportExport soap = null;
			IImportExport soap1 = null;
			String soapMessage = "";
			try
			{
				soap = service.getBasicHttpBindingIImportExport();
			}
			catch (WebServiceException ex)
			{
				//Ignore as the port will be left as null
				soapMessage = ex.getMessage() + " - " + extractStackTrace(ex.getStackTrace());
			}
			try
			{
				soap1 = service.getBasicHttpBindingIImportExport1();
			}
			catch (WebServiceException ex)
			{
				//Ignore as the port will be left as null
				soapMessage = ex.getMessage() + " - " + extractStackTrace(ex.getStackTrace());
			}
			
			//If both are NULL, throw exception
			if (soap == null && soap1 == null)
			{
				//Display the error
				throw new Exception ("Unable to connect with either the SpiraTest HTTP or HTTPS APIs. Please check the URL and try again: " + soapMessage + "\n");
			}
			
			//If one is NULL, simply set to the same as the other to avoid having to add NULL checks in the subsequent code
			if (soap == null)
			{
				soap = soap1;
			}
			else
			{
				soap1 = soap;
			}
			
			//Get the navigation link for the prefix
			int artifactTypeId;
			artifactTypeId = ArtifactType.getByPrefix(prefix).getArtifactId();
			
			//Get the artifact URL from the server
			String relativeUrl = soap.systemGetArtifactUrl(artifactTypeId, projectId, artifactId, "");
			String absoluteUrl = relativeUrl.replaceFirst("~", this.url);
			return absoluteUrl;
		}
		catch (IImportExportSystemGetArtifactUrlServiceFaultMessageFaultFaultMessage ex)
		{
			throw new Exception ("Unable to create artifact URL", ex);
		}
	}
	
	
	/**
	 * Removes any invalid XML contract characters from a string before being used in a SOAP call
	 * @param text
	 * @return
	 */
	public String cleanText(String text)
	{
		if (text == null)
		{
			return null;
		}
		return text.replaceAll("\\p{Cntrl}", "");
	}
	
	public String extractStackTrace(StackTraceElement[] elements)
	{
		String output = "";
		if (elements != null)
		{
			for (int i = 0; i < elements.length; i++)
			{
				output += elements[i].toString();
			}
		}
		
		return output;
	}
	
	public static XMLGregorianCalendar convertDatesJava2Xml(Date date)
	{
		if (date == null)
		{
			return null;
		}
		try
		{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			//We need to specify that these dates are really in UTC - Spira 4.0 and later APIs
			TimeZone utc = TimeZone.getTimeZone("UTC");
			calendar.setTimeZone(utc);
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			XMLGregorianCalendar xmlCal = datatypeFactory.newXMLGregorianCalendar(calendar);
			//We need to unset the timezone from the XML because SpiraTeam is not expecting it
			//and it will break concurrency
			xmlCal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			return xmlCal;
		}
		catch (DatatypeConfigurationException ex)
		{
			return null;
		}
	}
	
	public static Date convertDatesXml2Java(XMLGregorianCalendar xmlCal)
	{
		if (xmlCal == null)
		{
			return null;
		}
		GregorianCalendar calendar = xmlCal.toGregorianCalendar();
		//We need to specify that these dates are really in UTC - Spira 4.0 and later APIs
		TimeZone utc = TimeZone.getTimeZone("UTC");
		calendar.setTimeZone(utc);
		Date date = calendar.getTime();
		return date;
	}
	
	/***
	 * Creates a JAXB web service string element from a Java string
	 * @param value
	 * @return
	 */
	public static JAXBElement<String> createJAXBString(String fieldName, String value)
	{
		JAXBElement<String> jaxString = new JAXBElement<String>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), String.class, value);
		if (value == null)
		{
			jaxString.setNil(true);
		}
		return jaxString;
	}
	
	
	/***
	 * Creates a JAXB web service integer element from a Java integer
	 * @param value
	 * @return
	 */
	public static JAXBElement<Integer> createJAXBInteger(String fieldName, Integer value)
	{
		JAXBElement<Integer> jaxInteger = new JAXBElement<Integer>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), Integer.class, value);
		if (value == null)
		{
			jaxInteger.setNil(true);
		}
		return jaxInteger;
	}
	
	/***
	 * Creates a JAXB web service XMLGregorianCalendar element from a Java XMLGregorianCalendar object
	 * @param value
	 * @return
	 */
	public static JAXBElement<XMLGregorianCalendar> createJAXBXMLGregorianCalendar(String fieldName, XMLGregorianCalendar value)
	{
		JAXBElement<XMLGregorianCalendar> jaxXMLGregorianCalendar = new JAXBElement<XMLGregorianCalendar>(new QName(WEB_SERVICE_NAMESPACE_DATA_OBJECTS, fieldName), XMLGregorianCalendar.class, value);
		if (value == null)
		{
			jaxXMLGregorianCalendar.setNil(true);
		}
		return jaxXMLGregorianCalendar;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
}
