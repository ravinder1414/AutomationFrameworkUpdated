package SpiraTest;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/********************************************************************************************************
 *Project Name		: Automation framework 
 *Author		    : Mohammad Sayemul Makki
 *Version	    	: V1.0
 *Date of Creation	: 08-11-2016
 *Date Last modified: 
 *Description       : Connect to SpiraTest to read test steps, test cases for requested test set
 *Functions			: https://impellam.spiraservice.net/Services/v4_0/RestService.aspx
 *updateTesCase(LocalTC obj) 		: Update the test case result back in SpiraTest after execution
 *CreateTestSetForTestComplete(LocalTC obj)    		: Create a test run for requested test set
 *updateTestStep(LocalTC obj)    		: Update the test step result back in SpiraTest after execution
 *ExtractTestSetinProjectForTestComplete(LocalTC obj)			: Get all the test set in the project
 *buildUrl(String type, LocalTC obj )	: Build a restful URL to request SpiraTest 
 *updateAll(LocalTC obj)    		: Update the result back in SpiraTest after execution
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import automationFramework.LocalTC;
import utility.Constant;
import utility.Log;

public class SpiraReaderTestComplete {

	URL RequestURL;
	Proxy proxy;
	HttpURLConnection conn;
	JSONArray TestSet;
	JSONArray AllTest;
	JSONObject TestCase;
	Instant enddate;

	/**
	 * @throws MalformedURLException
	 */
	public SpiraReaderTestComplete() throws MalformedURLException {
		super();
		enddate = Instant.now();
		File f = new File(Constant.tempTestReportPath + "\\Impellam.txt");
		if (f.exists())
			this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.21.0.25", 8080));
		else
			this.proxy = null; //
	}

	/**
	 * @param obj
	 * @throws Exception
	 */
	public void ExtractTestSetinProjectForTestComplete(LocalTC obj) throws Exception {
		StringBuilder sb;
		String rid = "" + obj.getReleaseID();
		sb = new StringBuilder();
		try {
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
				Log.info("Blocked: test has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading Release from spira response");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
				Log.info("Release detail" + output + "");
			}
			JSONObject Release = new JSONObject(sb.toString());
			sb = new StringBuilder();
			// create url to extract the test sets linked to a release
			requestURL = buildUrl("testsetsinproject", obj);
			RequestURL = new URL(requestURL);
			if (this.proxy != null)
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
			else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				Log.info("Blocked: test has been blocked due to connectivity with SpiraTest");
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			Log.info("Reading testsets in project having running release id");
			br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			// String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
				Log.info("Test Sets" + output + "");
			}
			TestSet = new JSONArray(sb.toString());
			// Loop through all the test sets in the project
			for (int i = 0; i < TestSet.length(); i++) {
				JSONObject TestCase = TestSet.getJSONObject(i);
				String ReleaseId = TestCase.getString("ReleaseId");
				if (ReleaseId.equals(rid)) { // Find test set matching to the
												// release requested for
					obj.setTestSetID(Integer.parseInt(TestCase.getString("TestSetId")));
					obj.setTestSetName(TestCase.getString("Name"));
					Log.startTestSet("Start Test Set " + obj.getTestSetID());
					CreateTestSetForTestComplete(obj);
					Log.endTestSet("End Test Set " + obj.getTestSetID());
				}
			}
			conn.disconnect();
			Constant.driver = null;
		} catch (MalformedURLException e) {
			Log.info(
					"Blocked: test has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info(
					"Blocked: test has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (JSONException e) {
			Log.info("Blocked: test has been blocked due to exception in JSONException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/**
	 * @param obj
	 * @throws Exception
	 */
	public void CreateTestSetForTestComplete(LocalTC obj) throws Exception {
		StringBuilder sb;
		JSONArray Stepsin;
		sb = new StringBuilder();
		try {
			// Create URL to create test run
			String requestURL = buildUrl("testrun", obj);
			Log.info(requestURL);
			RequestURL = new URL(requestURL);
			if (this.proxy != null) {
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
				Log.info("Running with proxy");
			} else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Accept", "application/json");
			OutputStream os = conn.getOutputStream();
			os.write(obj.getTestRunStatus().getBytes());
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
			} else {
				Log.info("Creating testrun for Test complete");
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output;
				while ((output = br.readLine()) != null) {
					sb.append(output);
					Log.info("All test cases in the set" + output + "");
				}
				if (null == AllTest)
					AllTest = new JSONArray(sb.toString());
				else {
					AllTest = concatJSONArray(AllTest, new JSONArray(sb.toString()));
				}
				Log.info("Run Created for Test as : " + AllTest.toString());
				conn.disconnect();
			}
		} catch (ProtocolException e) {
			updateAll(obj);
			Log.info(e.getMessage());
		} catch (MalformedURLException e) {
			updateAll(obj);
			Log.info(
					"Blocked: test has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			updateAll(obj);
			Log.info(
					"Blocked: test has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/**
	 * @param obj
	 * @throws NumberFormatException
	 * @throws JSONException
	 */
	public void updateTestStep(LocalTC obj) throws NumberFormatException, JSONException {
		boolean bflag = false;
		for (int i = 0; i < AllTest.length(); i++) {
			TestCase = AllTest.getJSONObject(i);
			int testCaseId = Integer.parseInt(TestCase.getString("TestCaseId").replaceAll("[|?\\/*:<>TCtc-]*", ""));
			int testSetId = Integer.parseInt(TestCase.getString("TestSetId"));
			JSONArray stepsin = TestCase.getJSONArray("TestRunSteps");
			Log.info(stepsin.toString());
			for (int j = 0; j < stepsin.length(); j++) {
				JSONObject childJSONObject = stepsin.getJSONObject(j);
				Log.info("\nChildObject: " + childJSONObject);
				// Extract TestStepID from JSON
				int testStepId = childJSONObject.getInt("TestStepId");
				Log.info("\nTestStepID: " + Integer.toString(testStepId));
				if (testSetId == obj.getTestComTestSetId() && testCaseId == obj.getTestComTestCaseId()
						&& testStepId == obj.getTestComTestStepId()) {
					int exid = 3;
					obj.setTestComTestStepStatus(setStatus(obj.getTestComTestStepStatus()));
					exid = getExecutionStatus(obj.getTestComTestStepStatus());
					byte ptext[] = obj.ActualResult.replace(";", "<br />").replace("\"", "").getBytes(ISO_8859_1); 
					String value = new String(ptext, UTF_8); 
					childJSONObject.put("ActualResult", value.toString());
					childJSONObject.put("ExecutionStatusId", new Integer(exid));
					bflag = true;
					break;
				}
			}
			if (bflag)
				break;
		}
	}

	/**
	 * @param obj
	 * @throws JSONException
	 */
	public void updateTestCase(LocalTC obj) throws JSONException {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < AllTest.length(); i++) {
			TestCase = AllTest.getJSONObject(i);
			int testCaseId = Integer.parseInt(TestCase.getString("TestCaseId").replaceAll("[|?\\/*:<>TCtc-]*", ""));
			int testSetId = Integer.parseInt(TestCase.getString("TestSetId"));
			if (testSetId == obj.getTestComTestSetId() && testCaseId == obj.getTestComTestCaseId()) {
				int exid = 3;
				obj.setTestComTestCaseStatus(setStatus(obj.getTestComTestCaseStatus()));
				exid = getExecutionStatus(obj.getTestComTestCaseStatus());
				TestCase.put("ExecutionStatusId", new Integer(exid));
				//TestCase.put("ActualDuration", (System.currentTimeMillis() - startTime)/60000);
				break;
			}
		}
	}

	/**
	 * @param obj
	 */
	public void updateTestSet(LocalTC obj){
		
	}
	/**
	 * @param obj
	 *            Updating the jsonObject into spira test
	 * @throws JSONException 
	 */
	public void updateAll(LocalTC obj) throws JSONException {
		try {
			String requestURL = buildUrl("status", obj);
			RequestURL = new URL(requestURL);
			if (this.proxy != null) {
				this.conn = (HttpURLConnection) RequestURL.openConnection(proxy);
				Log.info("Running with proxy");
			} else
				this.conn = (HttpURLConnection) RequestURL.openConnection();
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setAllowUserInteraction(false);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
			for (int i=0; i<AllTest.length(); i++) {
				os.write(AllTest.get(i).toString());
			}
			//os.write(AllTest.toString());
			Log.info("Update SpiraTest with following test : " + AllTest.toString());
			os.flush();
			os.close();
			conn.connect();
			if (conn.getResponseCode() != 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				String line;
				while ((line = br.readLine()) != null) {
					Log.info(line);
				}
				Log.info("Blocked: test has been blocked due to connectivity with SpiraTest in updating results");
			}
			conn.disconnect();
		} catch (ProtocolException e) {
			Log.info("Blocked: test has been blocked due to exception in ProtocolException while reading SpiraTest");
			Log.info(e.getMessage());
		} catch (MalformedURLException e) {
			Log.info(
					"Blocked: test has been blocked due to exception in MalformedURLException while reading SpiraTest");
			e.printStackTrace();
		} catch (IOException e) {
			Log.info("Blocked: test has been blocked due to exception while reading SpiraTest");
			e.printStackTrace();
		}
	}

	/**
	 * @param jsonArray1
	 * @param jsonArray2
	 * @return
	 * @throws JSONException
	 */
	private JSONArray concatJSONArray(JSONArray jsonArray1, JSONArray jsonArray2) throws JSONException {
		JSONArray result = new JSONArray();
		for (int i = 0; i < jsonArray1.length(); i++) {
			result.put(jsonArray1.get(i));
		}
		for (int i = 0; i < jsonArray2.length(); i++) {
			result.put(jsonArray2.get(i));
		}
		return result;
	}

	/**
	 * @param type
	 * @param obj
	 * @return
	 */
	public String buildUrl(String type, LocalTC obj) {
		String requestURL = null;
		switch (type) {
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
		if (!requestURL.contains("?"))
			requestURL = requestURL + Constant.ServiceUser;
		else
			requestURL = requestURL + Constant.ServiceUser.replace("?", "&");
		Log.info("Build URL: " + type + "- " + requestURL);
		return requestURL;
	}

	/**
	 * @param status
	 * @return
	 */
	public int getExecutionStatus(String status) {
		int exid = 6;
		// Set the ExecutionID for execution status captured during execution
		switch (status) {
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

	/**
	 * @param strStatus
	 * @return
	 */
	public String setStatus(String strStatus) {
		if (strStatus.contains(Constant.Failed))
			strStatus = "Failed";
		else if (strStatus.contains("Blocked") && (!strStatus.contains(Constant.Failed)))
			strStatus = "Blocked";
		else if (strStatus.contains(Constant.Passed))
			strStatus = "Passed";
		return strStatus;
	}
}
