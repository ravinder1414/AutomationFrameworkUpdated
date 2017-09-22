package SpiraTest;

/********************************************************************************************************
 *Project Name		: Ignite Automation framework 
 *Author		    : Bharat Sethi
 *Version	    	: V1.0
 *Date of Creation	: 13-05-2016
 *Date Last modified: 16/05/2016
 *Description       : Connect to SpiraTest to read test steps, test cases for requested test set
 *Functions			: https://impellam.spiraservice.net/Services/v4_0/RestService.aspx
 *SpraReader : Constructor to set proxy variable after checking a flag file on the system
 *ExtractTestSteps(LocalTC obj) 		: Get all test steps in a testcase in SpiraTest
 *ExtractTestCases(LocalTC obj) 		: Get all test cases from SpiraTest in a Test Set
 *CreateTestRun(LocalTC obj)    		: Create a test run for requested test set
 *UpdateTestRun(LocalTC obj)    		: Update the result back in SpiraTest after execution
 *ExtractProject(LocalTC obj)			: Get all the test set in the project
 *buildUrl(String type, LocalTC obj )	: Build a restful URL to request SpiraTest 
 ********************************************************************************************************
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import static java.nio.charset.StandardCharsets.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
//import org.omg.CORBA.portable.OutputStream;

import automationFramework.ExecutionPauseUtil;
import automationFramework.KeywordAction;
import automationFramework.KeywordLibrary;
import automationFramework.LocalTC;
import automationFramework.LockableObject;
import automationFramework.ReporterCommon;
import automationFramework.ReporterObject;
import automationFramework.ReporterSummaryObject;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;
/***************************Constructor to SpiraTest reader class***************************************
 * 
 * @author bharat.sethi
 *
 */
public class SpiraReader {
	URL RequestURL;
	Proxy proxy;
	HttpURLConnection conn;
	int TestCasesID[];
	String TestSteps[];
	JSONArray TestSet;
	JSONArray AllTest;
	JSONObject TestCase;
	Instant enddate;
	//JSONArray StepsinTest;
	JSONArray TestResult;
	boolean IndividualTest = false;

	public SpiraReader() throws MalformedURLException {
		super();
		enddate=Instant.now();
		File f = new File(Constant.tempTestReportPath + "\\Impellam.txt");
		if (f.exists()) 
			this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.21.0.25", 8080));
		else
			this.proxy = null; //
	}
	/************************Call to SpiraTest to provide all test steps in a test case****************************
	 * @request https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-cases/16209?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	 * @param obj
	 * @throws Exception 
	 */
	public void ExtractTestSteps(LocalTC obj, String TestCaseID) throws Exception
	{
		StringBuilder sb;
		JSONArray Stepsin;
		sb = new StringBuilder();
		int tempTestCaseID = obj.getTestCaseID();
		IndividualTest =true;
		obj.setTestCaseID(Integer.parseInt(TestCaseID));
		try{
			String requestURL = buildUrl("teststep", obj); //Build a URL for Restful request to read all steps in Test case
			RequestURL = new URL(requestURL);              // 
			if (this.proxy != null){                       // Add Proxy detail if running in Impellam  
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
				Log.info("Running with proxy");
			}
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: ExtractTestSteps has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading teststeps from spira response");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) 
			{
				sb.append(output);
				Log.info("List Test Steps " + output + "");
			}
			TestCase = new JSONObject(sb.toString()); 
			//JSONObject jsonTS = jsonObj.getJSONObject("TestSteps");
			Stepsin = TestCase.getJSONArray("TestSteps");
			Log.info("\n\nTestStepsArray: " + Stepsin);
			ExecuteSteps(obj,Stepsin);
			Log.info("\njsonArray: " + Stepsin);
			conn.disconnect();
			obj.setTestCaseID(tempTestCaseID);
		} 
		catch (MalformedURLException e) {
			Log.info("Blocked: ExtractTestSteps has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: ExtractTestSteps has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
		catch (JSONException e) {
			Log.info("Blocked: ExtractTestSteps has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	public void ExecuteSteps(LocalTC obj,JSONArray Stepsin) throws Exception{
		if (IndividualTest==false){		
		int TestCaseId = Integer.parseInt(TestCase.getString("TestCaseId").replaceAll("[|?\\/*:<>TCtc-]*", ""));
		Log.info("\nTestCase: " + TestCaseId);
		obj.setTestCaseID(TestCaseId);
		//KeywordLibrary.CreateReport(obj,"d");
		}
		//JSONArray Stepsin = TestCase.getJSONArray("TestRunSteps");
		for (int i = 0; i < Stepsin.length(); i++) 
		{  
			final Object lockableObject = LockableObject.getLockableObject();
			if (ExecutionPauseUtil.isExecutionPaused()) {
				synchronized (lockableObject) {
					lockableObject.wait();
				}
			}
			if (ExecutionPauseUtil.isExecutionStopped()) {
				break;
			}
			obj.row = i+1;
			JSONObject childJSONObject = Stepsin.getJSONObject(i);
			Log.info("\nChildObject: " + childJSONObject);
			//Extract teststep from JSON
			String TestStep = Utils.htmlToTextConvertMethod(childJSONObject.getString("Description").replace("&nbsp;", " "));
			obj.setTestStep(TestStep);
			Log.info("\nTestStep: " + TestStep);
			//Extract test expected from JSON
			String TestExpected = Utils.htmlToTextConvertMethod(childJSONObject.getString("ExpectedResult").replace("&nbsp;", " "));
			obj.setExpected(TestExpected);
			Log.info("\nTestExpected: " + TestExpected);
			//Extract SampleData from JSON
			String SampleData = Utils.htmlToTextConvertMethod(childJSONObject.getString("SampleData").replace("&nbsp;", " "));
			Log.info("\nSampleData: " + SampleData);
			if(obj.RunTestCase.contains("null"))
				obj.RunTestCase="";
			if(obj.RunTestCase.isEmpty() || obj.RunTestCase.equalsIgnoreCase(""))
				obj.setSampleData(SampleData);
			else
				obj.setSampleData("dt_" + obj.RunTestCase.replace("\\n", ""));
			obj.RunTestCase="";
			//Extract TestStepID from JSON
			int TestStepId = childJSONObject.getInt("TestStepId");
			obj.setTestStepID(TestStepId);
			Log.info("\nTestStepID: " + Integer.toString(TestStepId));
			KeywordLibrary.ReadTest(obj); //Execute all actions in a test step 
			int exid = 3;
			if (obj.loopflag == 0) {
				exid = getExecutionStatus(obj.ExecutionStatus);
				// Put the actual result and status back in the object
				byte ptext[] = obj.ActualResult.replace(";", "<br />").replace("\"", "").getBytes(ISO_8859_1);
				String value = new String(ptext, UTF_8);
				childJSONObject.put("ActualResult", value.toString());
				// childJSONObject.put("ActualResult",
				// obj.ActualResult.replace(";", "<br />").replace("\"", ""));
				childJSONObject.put("ExecutionStatusId", new Integer(exid));
			}
			//Stepsin.put(i,childJSONObject);
		}
		if(obj.loopflag == 1)
		{
			obj.loopflag =0;
			KeywordAction.endloop(obj);
			if(obj.getMapLoop().size()>0){
				Iterator itrMap = obj.getMapLoop().entrySet().iterator();
				while (itrMap.hasNext()) {
				    Map.Entry entry = (Map.Entry) itrMap.next();
				    Integer key = (Integer)entry.getKey();
				    for (int i = 0; i < Stepsin.length(); i++) {
				    	JSONObject childJSONObject = Stepsin.getJSONObject(i);
				    	int TestStepId = childJSONObject.getInt("TestStepId");
				    	if(TestStepId == key){
				    		String strStaus = (String) entry.getValue();
				    		if(strStaus.contains(Constant.Failed))
				    			obj.ExecutionStatus = Constant.Failed;
				    		else if(strStaus.contains(Constant.Blocked))
				    			obj.ExecutionStatus = Constant.Blocked;
				    		else if(strStaus.contains(Constant.Passed))
				    			obj.ExecutionStatus = Constant.Passed;
				    		else if(strStaus.contains(Constant.NotRun))
				    			obj.ExecutionStatus = Constant.NotRun;
				    		childJSONObject.put("ExecutionStatusId", new Integer(getExecutionStatus(obj.ExecutionStatus)));
				    	}
				    }
				    //System.out.println("Key = " + key + ", Value = " + value);
				}
			}
		}
	}


	/*********************************Extract all test cases in a given test set*******************************
	 * @request https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/releases/587/test-cases?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	 * @param obj
	 * @throws Exception 
	 */
	public void ExtractTestCases(LocalTC obj) throws Exception
	{
		StringBuilder sb;
		sb = new StringBuilder();
		try{
			String requestURL = buildUrl("testcase", obj);

			RequestURL = new URL(requestURL);
			if (this.proxy != null)
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: ExtractTestCases has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading testcases from spira response");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) 
			{
				sb.append(output);
				Log.info("All Test cases in set" + output + "");
			}
			JSONArray TestSet = new JSONArray(sb.toString()); 
			for (int i = 0; i < TestSet.length(); i++) 
			{  
				TestCase = TestSet.getJSONObject(i);
				ExtractTestSteps(obj,obj.getTestSetID()+"");
				if(null != obj.bw){
					obj.bw.close();
				}
			}
			Log.info("\njsonArray: " + TestSet);
			conn.disconnect();
		} 
		catch (MalformedURLException e) {
			Log.info("Blocked: ExtractTestCases has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: ExtractTestCases has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
		catch (JSONException e) {
			Log.info("Blocked: ExtractTestCases has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/*********************************Extract all test set name and other detail *******************************
	 * @request https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-sets/161?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	 * @param obj
	 * @throws IOException
	 */
	public void ExtractTestSet(LocalTC obj, ReporterCommon reportCommonObj) throws IOException
	{
		StringBuilder sb;
		sb = new StringBuilder();
		try{
			String requestURL = buildUrl("testset", obj);  // Create URL to read info about test set

			RequestURL = new URL(requestURL);
			if (this.proxy != null)
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: ExtractTestSet has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading testset name from spira response");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) 
			{
				sb.append(output);
				Log.info("Test Set Detail" + output + "");
			}
			//Read test set name to understand which browser or url to use
			JSONObject jsonObj = new JSONObject(sb.toString()); 
			String TestSetName = jsonObj.getString("Name");
			obj.setTestSetName(TestSetName);
			if(TestSetName.contains("IE"))
				obj.setbrosername("IE");
			else if(TestSetName.contains("Chrome"))
				obj.setbrosername("Chrome");
			else if(TestSetName.contains("Firefox"))
				obj.setbrosername("Firefox");
			reportCommonObj.setReportTestSetName(obj.getTestSetName());
			reportCommonObj.setReportBrowserName(obj.getbrowsername());
			String regex = "http:*.*";
			Pattern patt = Pattern.compile(regex);
			Matcher matcher = patt.matcher(TestSetName);
			while(matcher.find())
				obj.setURL(matcher.group());
			//Log.info("\n\nTestStepsArray: " + jsonArray);
			conn.disconnect();
		} 
		catch (MalformedURLException e) {
			Log.info("Blocked: ExtractTestSet has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: ExtractTestSet has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
		catch (JSONException e) {
			Log.info("Blocked: ExtractTestSet has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/*************Extract all test sets in project linked to current release ******************************
	 * @request https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-sets/161?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	 * @param obj
	 * @throws Exception 
	 */
	public void ExtractTestSetinProject(LocalTC obj) throws Exception
	{
		StringBuilder sb;
		String rid = "" + obj.getReleaseID();
		sb = new StringBuilder();
		ReporterCommon reportCommonObj = null;
		if (obj.fscreenlock != 200) {
			reportCommonObj = new ReporterCommon();
		}
		try{
			String requestURL = buildUrl("testsetsinproject", obj); //create url to extract the test sets linked to a release
			RequestURL = new URL(requestURL);
			if (this.proxy != null)
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: ExtractTestSetinProject has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading testsets in project having running release id");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) 
			{
				sb.append(output);
				Log.info("Test Sets" + output + "");
			}
			TestSet = new JSONArray(sb.toString());
			//Loop through all the test sets in the project
			for (int i = 0; i < TestSet.length(); i++) 
			{  
				final Object lockableObject = LockableObject.getLockableObject();
				if (ExecutionPauseUtil.isExecutionPaused()) {
					synchronized (lockableObject) {
						lockableObject.wait();
					}
				}
				if (ExecutionPauseUtil.isExecutionStopped()) {
					break;
				}
				JSONObject TestCase = TestSet.getJSONObject(i);
				String ReleaseId = TestCase.getString("ReleaseId");
				if(ReleaseId.equals(rid)){ //Find test set matching to the release requested for
					obj.setReportReleaseId(ReleaseId);
					obj.setTestSetID(Integer.parseInt(TestCase.getString("TestSetId")));
					obj.setTestSetName(TestCase.getString("Name"));
					if (obj.fscreenlock != 200) {
						reportCommonObj.setReportTestSetId(Integer.toString(obj.getTestSetID()));
						reportCommonObj.setReportTestSetName(obj.getTestSetName());
						reportCommonObj.setReportBrowserName(obj.getbrowsername());
					}
					Log.startTestSet("Start Test Set " + obj.getTestSetID());
					if (obj.fscreenlock != 200) {
						if (obj.reporttype != 1)
							// Extract Test set name for a given test set found
							// in the release
							ExtractTestSet(obj, reportCommonObj);
						// obj.reporttype = 0;
						// Run current found test set that is insdide the
						// release
						CreateTestRun(obj);
						String testSetBrowserName = obj.getTestSetName() + "&#" + obj.getbrowsername();
						obj.getReportMap().put(testSetBrowserName, new ArrayList<>(obj.getReporterSumObjList()));
						obj.getReportCommonObj().add(reportCommonObj);
						obj.setReporterSumObjList(new ArrayList<>());
					}/*else if(){
						updateTestCaseInProject(obj);
					}*/
					//BrowserFactory.closeAllDriver();
					Log.endTestSet("End Test Set " + obj.getTestSetID());
				}	
			}
			conn.disconnect();
			Constant.driver = null;
		} 
		catch (MalformedURLException e) {
			Log.info("Blocked: ExtractTestSetinProject has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: ExtractTestSetinProject has been blocked due to exception in IOException while reading SpiraTest");
			e.printStackTrace();
		} catch (JSONException e) {
			Log.info("Blocked: ExtractTestSetinProject has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/*********************************Create a test run for this execution****************************
	 * @request https://impellam.spiraservice.net/Services/v4_0/RestService.svc/projects/75/test-runs/create/test_set/161?username=bharat.sethi&api-key={D6DAC12E-189E-4BDC-A1F3-3F2EC3CFCB3A}
	 * @param obj
	 * @throws Exception 
	 */
	public void CreateTestRun(LocalTC obj) throws Exception
	{
		StringBuilder sb;
		JSONArray Stepsin;
		sb = new StringBuilder();
		try{
			String requestURL = buildUrl("testrun", obj); //Create URL to create test run
			Log.info(requestURL);
			RequestURL = new URL(requestURL);
			if (this.proxy != null){
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
				Log.info("Running with proxy");
			}
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Accept", "application/json");

			OutputStream os = conn.getOutputStream();
			os.write(obj.getTestRunStatus().getBytes());
			//	        os.flush();
			os.close();
			conn.connect();
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				conn.disconnect();
				obj.TestCaseStatus = "Blocked";
				
				Log.info("There are no test cases found in the test set " + obj.getTestSetID());
				//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			else{
				Log.info("Creating testrun for automation execution");
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				while ((output = br.readLine()) != null) 
				{
					sb.append(output);
					Log.info("All test cases in the set" + output + "");
				}
				AllTest = new JSONArray(sb.toString()); 
				if(obj.fscreenlock == 100){
					ExcelUtils.writeJASON(AllTest);
				}
				Log.info("Run Created for Test as : " + AllTest.toString());
				for (int i = 0; i < AllTest.length(); i++) 
				{  	
					final Object lockableObject = LockableObject.getLockableObject();
					if (ExecutionPauseUtil.isExecutionPaused()) {
						synchronized (lockableObject) {
							lockableObject.wait();
						}
					}
					if (ExecutionPauseUtil.isExecutionStopped()) {
						break;
					}
					ReporterSummaryObject reportSumObj = new ReporterSummaryObject();
					List<ReporterObject> reporterObjList = new ArrayList<>();
					TestCase = AllTest.getJSONObject(i);
					obj.setTestCaseName(TestCase.getString("Name"));
					int TestCaseId = Integer.parseInt(TestCase.getString("TestCaseId").replaceAll("[|?\\/*:<>TCtc-]*", ""));
					Log.startTestCase("\nStart of Test Case: " + TestCaseId);
					obj.setTestCaseID(TestCaseId);
					//KeywordLibrary.CreateReport(obj,"d");
					obj.setTestCaseStatus("Passed");
					Stepsin = TestCase.getJSONArray("TestRunSteps");
					Log.info(Stepsin.toString());
					long startTime = System.currentTimeMillis();
					ExecuteSteps(obj,Stepsin);
					if(null != obj.bw){
						obj.bw.close();						
					}if(null != obj.bw1){
						obj.bw1.close();
					}
					int exid = 3;
					exid = getExecutionStatus(obj.getTestCaseStatus());
					reportSumObj.setReportSummaryTestCaseID(Integer.toString(obj.getTestCaseID()));
					reportSumObj.setReportSummaryTestCaseName(obj.getTestCaseName());
					reportSumObj.setReportSummaryTestCaseStatus(obj.getTestCaseStatus());
					reporterObjList = new ArrayList<>(obj.reporterObjectList);
					//reporterObjList = (List<ReporterObject>) ((Object) obj.reporterObjectList).clone();
					reportSumObj.getReportObjMap().put(obj.getTestCaseName(), reporterObjList);
					obj.getReporterSumObjList().add(reportSumObj);
					obj.reporterObjectList = new ArrayList<>();
					TestCase.put("ExecutionStatusId", new Integer(exid));
					TestCase.put("ActualDuration", (System.currentTimeMillis() - startTime)/60000);
					//TestCase.put("EndDate", enddate.getEpochSecond()+"");//TestCase.getString("StartDate"));
					obj.conditionSkip = false;
					//Reporter.ReportEvent(obj);
					Log.endTestCase("Finishing Test Case ID " +TestCaseId );
				}
				Log.endTestSet("Ending TestSet " + obj.getTestSetID());
				conn.disconnect();
				/////////////////////////////////////////////////////
				//uncomment when spiratest is fixed
				UpdateTestRun(obj);
			}
		}
		catch (ProtocolException e){
			UpdateTestRun(obj);
			Log.info(e.getMessage());
		}
		catch (MalformedURLException e) {
			UpdateTestRun(obj);
			Log.info("Blocked: CreateTestRun has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			UpdateTestRun(obj);
			Log.info("Blocked: CreateTestRun has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
	} 

	/***************************Create Json update Test run for this execution ******************************
	 * 
	 * @param obj
	 * @throws IOException
	 * @throws JSONException 
	 */

	public void UpdateTestRun(LocalTC obj) throws IOException, JSONException
	{
		StringBuilder sb;
		sb = new StringBuilder();
		try{
			String requestURL = buildUrl("status", obj);
			RequestURL = new URL(requestURL);
			if (this.proxy != null){
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
				Log.info("Running with proxy");
			}
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
			os.write(AllTest.toString());
			Log.info("Update SpiraTest with following test : " + AllTest.toString());
			os.flush();
			os.close();
			conn.connect();
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					Log.info (line);
				}
				Log.info("Blocked: UpdateTestRun has been blocked due to connectivity with SpiraTest in updating results");
				//throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			else
			{
			/*	BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				while ((output = br.readLine()) != null) 
				{
					sb.append(output);
					Log.info("Test run update :: " + output);
				}*/
				Log.info("SpiraTest has been updated sucessfully");
			}
			conn.disconnect();
		}
		catch (ProtocolException e){
			Log.info(e.getMessage());
		}
		catch (MalformedURLException e) {
			Log.info("Blocked: UpdateTestRun has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: UpdateTestRun has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	public void ExtractRelease(LocalTC obj) throws Exception
	{
		StringBuilder sb;
		sb = new StringBuilder();
		try{
			String requestURL = buildUrl("release", obj);

			RequestURL = new URL(requestURL);
			if (this.proxy != null)
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: ExtractRelease has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading Release from spira response");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) 
			{
				sb.append(output);
				Log.info("Release detail" + output + "");
			}
			JSONObject Release = new JSONObject(sb.toString()); 
			Log.info("\nRelease Json: " + Release);
			JSONArray customprop = Release.getJSONArray("CustomProperties");
			if(customprop.length()!=0){
				JSONObject BrowsersList = customprop.getJSONObject(0);
				JSONObject EnvList = customprop.getJSONObject(1);
				JSONArray BrowserNameArr = BrowsersList.getJSONArray("IntegerListValue");
				JSONArray envnameArr = EnvList.getJSONArray("IntegerListValue");
				for(int j=0;j<envnameArr.length();j++) {//run a loop on all selected URLs
					Set<String> envSet = obj.Env.keySet();
					for (String Envword : envSet){//Look for URL names in the list of browsers available in constant
						if (envnameArr.get(j).toString().equals(Envword) ){
							obj.setURL(obj.Env.get(Envword));
							Log.info("Reading URL from SPIRA : " + obj.Env.get(Envword));
							break;
						}
					}
					for(int i=0;i<BrowserNameArr.length();i++){//run a loop on all selected browsers
						Set<String> browSet = obj.Browsers.keySet();
						for (String Broword : browSet) {//Look for browser names in the list of browsers available in constant
							if (BrowserNameArr.get(i).toString().equals(Broword) ){
								obj.setbrosername(obj.Browsers.get(Broword));
								Log.info("Reading Browser from SPIRA : " + obj.Browsers.get(Broword));
								obj.reporttype = 1;
								break;}
						}
						//if (envnameArr.length() == 0)
						ExtractTestSetinProject(obj);
					}
				}
			}
			else{
				ExtractTestSetinProject(obj);
			}
			conn.disconnect();

		}
		catch (MalformedURLException e) {
			Log.info("Blocked: ExtractRelease has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: ExtractRelease has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
		catch (JSONException e) {
			Log.info("Blocked: ExtractRelease has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	public String buildUrl(String type, LocalTC obj )
	{
		String requestURL = null;
		//String pattern = "yyyy-MM-dd'T'HH:mm:ss.fff";
		//DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
		switch(type)
		{
		case "testcase":
			requestURL = new String(Constant.TestCasesInSet); 
			break;
		case "testset":
			requestURL = new String(Constant.TestSet);
			break;
		case "testsetsinproject":
			requestURL = new String(Constant.TestSetsinProject);
			break;
		case "runfromhost":
			requestURL = new String(Constant.RunfromHost);
			break;
		case "teststep":
			requestURL = new String(Constant.TestSteps);
			break;
		case "project":
			requestURL = new String(Constant.ProjectDetail);
			break;
		case "release":
			requestURL = new String(Constant.ReleaseDetail);
			break;
		case "testrun":
			requestURL = new String(Constant.TestRun);
			break;
		case "status":
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			requestURL = new String(Constant.TestStatus);
			requestURL = requestURL.replace("{end_date}", dateFormat.format((new Date())).toString());
			break;
		}
		requestURL = requestURL.replace("{project_id}", Integer.toString(obj.getProjectID()));
		requestURL = requestURL.replace("{test_case_id}", Integer.toString(obj.getTestCaseID()));
		requestURL = requestURL.replace("{test_set_id}", Integer.toString(obj.getTestSetID()));
		requestURL = requestURL.replace("{release_id}", Integer.toString(obj.getReleaseID()));
		requestURL = requestURL.replace("{automation_host_token}", Constant.automation_host_token);
		if(! requestURL.contains("?"))
			requestURL = requestURL + Constant.ServiceUser;
		else requestURL = requestURL + Constant.ServiceUser.replace("?", "&");
		Log.info("Build URL: " + type + "- " + requestURL );
		return requestURL;
	}
	public int getExecutionStatus(String status){
		int exid = 6;
		switch (status){  //Set the ExecutionID for execution status captured during execution
		case "Passed":
			exid = 2;
			break;
		case "Failed":
			exid = 1;
			break;
		case "Not Run":
			exid = 3;
			break;	
		case "Blocked":
			exid = 5;
			break;	
		case "Caution":
			exid = 6;
			break;	
		}
		return exid;
	}
}