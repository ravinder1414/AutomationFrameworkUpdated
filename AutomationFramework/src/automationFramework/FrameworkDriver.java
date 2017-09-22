package automationFramework;
/********************************************************************************************************
 *Project Name		: Ignite Automation framework 
 *Author		    : Bharat Sethi
 *Version	    	: V1.0
 *Date of Creation	: 28-04-2016
 *Date Last modified: 04/05/2016
 *Description       : Getting maximum benefits with minimum effort. This Framework will give ability to increase 
 *					  the efficiency of resources, increase test coverage, and increase the quality and 
 *					  reliability of the software.
 *Functions			: 
 *
@BeforeSuite  - beforeSuite - Configures the log
@BeforeTest   - loadLocally - Load all arguments to the LocalTC object
@BeforeClass  - 
@BeforeMethod - ReadTest    - If Integration is set to true then run a loop to read each step from SpiraReader else from excel
@Test         - StartTest   - Loop through all steps in a test case and send steps string to translator  
@AfterMethod  -
@AfterClass   -
@AfterTest    -
@AfterSuite   -
@DataProvider -
 ********************************************************************************************************
 */

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ObjectMap.OR;
import SpiraTest.SpiraReader;
import mail.SendMail;
import utility.BrowserFactory;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class FrameworkDriver
{
	static LocalTC Vars;
	static SpiraReader SpiraRead;
	int rowCountBw = 0;
	int rowCount = 0;
	static ReporterSummaryObject reportSumObj;
	static ReporterCommon reportCommonObj;
	static String strTestCaseId = "";
	static String testSetBrowserName = "";
	static String testsetname = "";

	/****************************
	 * This function is called by TestNG at the start of the test suite
	 * 
	 * @throws Exception
	 ***********************/
	// @BeforeSuite
	public static void beforeSuite(String ProjectID, String ReleaseID, String TestSetID, String TestCaseID,
			String TestRunPath, String Integration) throws Exception {
		Log.info("beforeSuite : Strating Project Test Execution" );
		SpiraRead = new SpiraReader();
		loadLocally(ProjectID, ReleaseID, TestSetID, TestCaseID, TestRunPath, Integration);
	}

	/*************
	 * This function is called by TestNG at the start of the test by loading all
	 * arguments to object
	 * 
	 * @throws Exception
	 ********/
	// @Parameters({"ProjectID","ReleaseID","TestSetID","TestCaseID","TestRunPath","Integration"})
	// @BeforeTest
	public static void loadLocally(String ProjectID, String ReleaseID, String TestSetID, String TestCaseID,
			String TestRunPath, String Integration) throws Exception {
		Vars = new LocalTC(ProjectID, ReleaseID, TestSetID, TestCaseID, TestRunPath, Integration);
		Log.info("loadLocally : Running test for Project ID " + Vars.getProjectID());
		if (Vars.getIntegration() == false)
			Log.startTestSet("loadLocally : Starting to execute Test Set " + TestSetID + " and Test Case " + TestCaseID);
		init();
	}
	/****************************Initiate the 
	 * 
	 * @throws Exception
	 */
	//@BeforeMethod //initiate the browser of a particular type (ie/firefox/chrome)
	public static void init() throws Exception
	{		
		Vars.Translate = new TranslateEngine();
		Vars.TestRun = new ExcelUtils();
		Vars.TestData = new ExcelUtils();
		Vars.TestCaseRun = new ExcelUtils();
		Constant.Vars = Vars;
		reportSumObj = new ReporterSummaryObject();
		reportCommonObj = new ReporterCommon();
		
		if (Vars.getIntegration() == false && Vars.fscreenlock == 300) {

			Vars.TestCaseRun.setExcelFile(Constant.Path_IgniteTestCaseRun, "Test Case Runs");
			int retRowCount = Vars.TestCaseRun.getRowCount();
				//ExcelUtils.createScreenShotDirectory(Vars, "Details");
			//ExcelUtils.deleteFile(Vars, null);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Vars.setExecutionStartTime(dateFormat.format(new Date()));
				//ExcelUtils.createScreenShotDirectory(Vars, "Details");
			//ExcelUtils.deleteFile(Vars, null);
			reportSumObj = new ReporterSummaryObject();
			reportCommonObj = new ReporterCommon();
			reportCommonObj.setReportBrowserName(Vars.getbrowsername());
			reportCommonObj.setReportTestSetId(Integer.toString(99999));
			Vars.obj = new OR("ObjectRepository/OR.Properties");
			// create detail directory
			String browsername = Vars.obj.getBrowser();
			if (null != browsername && !browsername.isEmpty()) {
				Vars.browsername = browsername;
			}
			//testsetname = Vars.obj.getTestSetName();
			if (null != testsetname && !testsetname.isEmpty())
				reportCommonObj.setReportTestSetName(testsetname);
			else
				reportCommonObj.setReportTestSetName("TestSet");
			for (int rowItr = 2; rowItr < retRowCount; rowItr++) {
				//String strtestcaseid = Vars.TestCaseRun.getCellData(rowItr, 1);
				
				// String strTestCaseFilePath =
				// Vars.TestCaseRun.getCellData(rowItr, 2);
				// String strTestCaseStatus =
				// Vars.TestCaseRun.getCellData(rowItr, 3);
				if (Vars.TestCaseRun.getCellData(rowItr, 3).equalsIgnoreCase("Y")) {
					Vars.setsTestCaseRunPath(Vars.TestCaseRun.getCellData(rowItr, 2));
					Vars.TestRun.setExcelFile(Vars.TestCaseRun.getCellData(rowItr, 2), "Test Runs");
					Log.info("init : test case excel opened");
					Log.info("init: It is start of test execution");
					testsetname = Vars.obj.getTestSetName();
					Vars.setStrReportStatusConf(Vars.obj.getScreenShotForReport());
					StartTest();
					/*
					 * ExcelUtils.updateExcellSheet(Constant.Vars);
					 * Constant.Vars.setResultStatus("");
					 */
				}
			}
			afterMethod();
		} else if (Vars.getIntegration() == false && Vars.fscreenlock != 300) {
			// Create test run for the test set this has to be called for each
			// set in release
			Vars.TestRun.setExcelFile(Vars.getTestRunPath(), "Test Runs");
			Log.startTestSet("Starting to creating backup file " + new File(Vars.sTestRunPath).getName() + " in location: "
					+ Constant.tempTestReportPath);
			//creating ExcelFileBackup directory
			ExcelUtils.createDirectory(Vars, "ExcelFileBackup");
			ExcelUtils.writeBackupFile(Vars);
			Log.info("init : test case excel opened");
			Log.info("init: It is start of test execution");
			if (Vars.fscreenlock == 100) {
				Vars.obj = new OR("ObjectRepository/TestComplete.Properties");
			} else {
				Vars.obj = new OR("ObjectRepository/OR.Properties");
			}
			if (Vars.getIntegration() == true){
				//Create test run for the test set this has to be called for each set in release
				//SpiraRead.CreateTestRun(Vars);  
			}
			//create detail directory
			ExcelUtils.createDirectory(Vars, "Detail");
			ExcelUtils.deleteFile(Vars, null);
			Vars.setStrReportStatusConf(Vars.obj.getScreenShotForReport());
			StartTest();
		}else if (Vars.getIntegration() == true){
			Log.info("init: It is start of test execution");
			if (Vars.fscreenlock == 100) {
				Vars.obj = new OR("ObjectRepository/TestComplete.Properties");
			} else {
				Vars.obj = new OR("ObjectRepository/OR.Properties");
			}
			ExcelUtils.createDirectory(Vars, "Detail");
			ExcelUtils.deleteFile(Vars, null);
			Vars.setStrReportStatusConf(Vars.obj.getScreenShotForReport());
			StartTest();
		}
		
	}

	//@Test 
	public static void StartTest() throws Exception 
	{
		//if set to false open the excel file from TestRunPath sample added in TestData package
		//loop through the excel or SpiraTest for all the test cases in the given Set
		if (Vars.fscreenlock != 300) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Vars.setExecutionStartTime(dateFormat.format(new Date()));

			reportSumObj = new ReporterSummaryObject();
			reportCommonObj = new ReporterCommon();
		}
		if (Vars.getIntegration() == false) {
			int retRowCount = Vars.TestRun.getRowCount();
			// Loop through all rows test step, expected, test step id and
			// sample data from excel and
			String browsername = Vars.obj.getBrowser();
			if(null != browsername && !browsername.isEmpty()){
				Vars.browsername = browsername;
			}
			if(Vars.fscreenlock != 300){
			reportCommonObj.setReportBrowserName(Vars.getbrowsername());
			reportCommonObj.setReportTestSetId(Integer.toString(99999));
			testsetname = Vars.obj.getTestSetName();
			if(null != testsetname && !testsetname.isEmpty())
				reportCommonObj.setReportTestSetName(testsetname);
			else
				reportCommonObj.setReportTestSetName("TestSet");
			if(Vars.fscreenlock == 100){
				Vars.setTestSetName("Frostnet TestSet");
			}else{
				if(null != testsetname && !testsetname.isEmpty()){
					reportCommonObj.setReportTestSetName(testsetname);
				}
				else{
					reportCommonObj.setReportTestSetName("TestSet");
				}
			}
			}
			if(null == Vars.TestRun.getCellData(2, 2) || Vars.TestRun.getCellData(2, 2).isEmpty()){
				Vars.setReportReleaseId(Integer.toString(1000));
				Vars.iReleaseID = 1000;
			}
			else{
				Vars.setReportReleaseId(Vars.TestRun.getCellData(2, 2));
				Vars.iReleaseID = Integer.parseInt(Vars.TestRun.getCellData(2, 2));
			}
			testSetBrowserName = testsetname + "&#" + Vars.getbrowsername();
			boolean isToBeExecuted = true;
			for (int rowItr = 2; rowItr < retRowCount; rowItr++) {
				final Object lockableObject = LockableObject.getLockableObject();
				if (ExecutionPauseUtil.isExecutionPaused()) {
					synchronized (lockableObject) {
						lockableObject.wait();
					}
				}
				if (ExecutionPauseUtil.isExecutionStopped()) {
					break;
				}
				if (Vars.getStopExecution().equals("false")) {
					// Reset execution result
					Vars.row = rowItr - 2;
					if (Vars.testcasestart != 0 && null != Vars.TestRun.getCellData(rowItr, 1)
							&& !Vars.TestRun.getCellData(rowItr, 1).isEmpty() && (Vars.TestRun.getCellData(rowItr, 5).equalsIgnoreCase("Y") || Vars.TestRun.getCellData(rowItr, 5).isEmpty())) {
						/*if (Vars.fscreenlock != 300 && Vars.loopflag != 1) {*/
						if (Vars.loopflag != 1) {
							reportSumObj.getReportObjMap().put(Vars.getTestCaseName(),
									new ArrayList<>(Vars.reporterObjectList));
							Vars.getReporterSumObjList().add(new ReporterSummaryObject(reportSumObj));
							Vars.getReportMap().put(testSetBrowserName, new ArrayList<>(Vars.getReporterSumObjList()));
							Vars.getReportCommonObj().add(reportCommonObj);
						}
					}
					if (null != Vars.TestRun.getCellData(rowItr, 1) && !Vars.TestRun.getCellData(rowItr, 1).isEmpty()) {

						String toBeExecuted = Vars.TestRun.getCellData(rowItr, 5);
						if (toBeExecuted == null || toBeExecuted.trim().length() == 0
								|| "Y".equalsIgnoreCase(toBeExecuted)) {
							isToBeExecuted = true;
						} else {
							isToBeExecuted = false;
							continue;
						}

						if (Vars.fscreenlock != 300 && Vars.loopflag != 1) {
						reportSumObj = new ReporterSummaryObject();
						}
						if(Vars.loopflag != 1)
							Vars.reporterObjectList = new ArrayList<>();
						Vars.conditionSkip = false;
						if (Vars.loopflag == 1) {
							Vars.loopflag = 0;
							KeywordAction.endloop(Vars);
							reportSumObj.setReportSummaryTestCaseStatus(Vars.getTestCaseStatus());
							reportSumObj.getReportObjMap().put(Vars.getTestCaseName(), new ArrayList<>(Vars.reporterObjectList));
							Vars.getReporterSumObjList().add(new ReporterSummaryObject(reportSumObj));
							Vars.getReportMap().put(testSetBrowserName, new ArrayList<>(Vars.getReporterSumObjList()));
							Vars.getReportCommonObj().add(reportCommonObj);
							//reportSumObj = new ReporterSummaryObject(); 
							Vars.reporterObjectList = new ArrayList<>();
							Vars.loopReportFlag = false;
							/*if(Vars.fscreenlock != 300)
								Vars.setReporterSumObjList(new ArrayList<>());*/
						}
						//Log.endTestCase("End of Test Case : " + Vars.getTestCaseName());
						Vars.setExecutionCount(rowItr - 1);
						Vars.testcasestart = rowItr;
						//strTestCaseId = Vars.TestRun.getCellData(rowItr, 0).replaceAll("[|?\\/*:<>TCtc(A-Z)(A-Z)-]*[\\_]*", "");	//TC_ or tc_
						strTestCaseId = Vars.TestRun.getCellData(rowItr, 0);
						Vars.setTestCaseID(Integer.parseInt(strTestCaseId.replaceAll("[|?\\/*:<>TCtc(A-Z)(A-Z)-]*[\\_]*", "")));
						Vars.setTestCaseName(Vars.TestRun.getCellData(rowItr, 1));
						Vars.setResultStatus(Vars.TestRun.getCellData(rowItr, 9));
						Log.startTestCase("Start of Test Case " + Vars.getTestCaseName());
						Vars.setTestCaseStatus("Passed");
						if(null != Vars.TestRun.getCellData(rowItr, 3) && !Vars.TestRun.getCellData(rowItr, 3).isEmpty())
							Vars.setTestSetID(Integer.parseInt(Vars.TestRun.getCellData(rowItr, 3)));
						// storing the object of report into arraylist for
						// summary report
						reportSumObj.setReportSummaryTestCaseID(strTestCaseId);
						reportSumObj.setReportSummaryTestCaseName(Vars.getTestCaseName());
					} else {
						if (!isToBeExecuted) {
							continue;
						}
						if (null != Vars.TestRun.getCellData(rowItr, 6)
								&& !Vars.TestRun.getCellData(rowItr, 6).isEmpty()) {
							// Reading one row of excel for Step, Expected, Test
							// StepID, Sample Data
							//Vars.setTestStep(Utils.htmlToTextConvertMethod(Vars.TestRun.getCellData(rowItr, 6)));
							Vars.setTestStep(Vars.TestRun.getCellData(rowItr, 6));
							if (null != Vars.TestRun.getCellData(rowItr, 7)
									&& !Vars.TestRun.getCellData(rowItr, 7).isEmpty())
								//Vars.setExpected(Utils.htmlToTextConvertMethod(Vars.TestRun.getCellData(rowItr, 7)));
								Vars.setExpected(Vars.TestRun.getCellData(rowItr, 7));
							else
								Vars.setExpected("");
							Vars.setTestStepID(Vars.TestRun.getNumaricCellData(rowItr, 5));
							Vars.setSampleData(Vars.TestRun.getCellData(rowItr, 8));
							Vars.setExecutionResult("");
							KeywordLibrary.ReadTest(Vars); // Execute all
															// actions in a test
															// step
							reportSumObj.setReportSummaryTestCaseStatus(Vars.getTestCaseStatus());
							if (Vars.fscreenlock != 100 && !Vars.loopReportFlag) {
								ExcelUtils.updateExcellSheet(Constant.Vars);
								Constant.Vars.setResultStatus("");
							}
						}
					}
				} else
					break;

			}
			
			//Vars.reporterSumObjList = new ArrayList<>();
			if(Vars.loopflag == 1)
			{
				Vars.loopflag =0;
				KeywordAction.endloop(Vars);
			}
			reportSumObj.setReportSummaryTestCaseStatus(Vars.getTestCaseStatus());
			reportSumObj.getReportObjMap().put(Vars.getTestCaseName(), new ArrayList<>(Vars.reporterObjectList));
			Vars.getReporterSumObjList().add(new ReporterSummaryObject(reportSumObj));
			Vars.getReportMap().put(testSetBrowserName, new ArrayList<>(Vars.getReporterSumObjList()));
			Vars.getReportCommonObj().add(reportCommonObj);
			if(Vars.fscreenlock != 300)
				Vars.setReporterSumObjList(new ArrayList<>());
			DateFormat dateFormatEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Constant.Vars.setExecutionEndTime(dateFormatEndTime.format(new Date()));
			Vars.conditionSkip = false;
			Log.endTestCase("End of Test Case : " + Vars.getTestCaseName());
		}
		if (Vars.getIntegration()==true) {
			Log.info(" StartTest : calling  getIntegration " + Vars.getIntegration());
			Log.info("StartTest : calling Reader function ");
			SpiraRead.ExtractRelease(Vars);
			DateFormat dateFormatEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Constant.Vars.setExecutionEndTime(dateFormatEndTime.format(new Date()));
		}
		if(Vars.fscreenlock != 300)
			afterMethod();
	}

	//@AfterMethod
	public static void afterMethod() throws Exception {
		if (Vars.fscreenlock == 100) {
			Process process = null;
			try {
				ExcelUtils.writeCSVForTestComplete(Vars);
				File batchFile = new File(Constant.testCompleteBatch);
				Log.info("Automation Framework End!!");
				Log.info("CSV for TestComplete has been generated and stored in path " + Constant.tempTestReportPath
						+ "TestComplete.csv");
				Log.info("TestComplete is called");
				// "ProjectId" “ReleaseId” “ExecutionStartTime” “csvPath”
				// “excelPath” “booleanFlag”
				process = Runtime.getRuntime()
						.exec("cmd /c start" + " " + batchFile.getAbsolutePath() + " " + Vars.getProjectID() + " "
								+ Vars.getReportReleaseId() + " \"" + Vars.getExecutionStartTime() + "\" " + "\""
								+ Constant.tempTestReportPath + "TestComplete.csv" + "\"" + " " + "\""
								+ Vars.getTestRunPath() + "\"" + " " + Vars.getIntegration());
			} catch (Exception e) {
				Log.error("Error in writing CSV: " + e.getMessage());
			}finally{
				Runtime.getRuntime().exec("taskkill " + process);
			}
		} else {
			Reporter.generateReport(Vars);
			Vars.bw1.close();
			Log.endTestSet("Ending TestSet " + Vars.getTestSetID());
			try {
				String[] mailObjects = Vars.obj.getPropertyMail().split(";");
				if (mailObjects.length > 0 && mailObjects.length == 5)
					SendMail.sendAttachmentEmail(mailObjects[0], mailObjects[1], mailObjects[2], mailObjects[3],
							mailObjects[4]);
			} catch (Exception e) {
				Log.error("Error in getting object for mail: " + e.getMessage());
			}
		}
		tearDown();
	}

	//@AfterSuite
	public static void tearDown() throws Exception
	{
		if (Vars.fscreenlock != 100) {
			//if (authObjects.length > 1) {
				Runtime.getRuntime().exec("taskkill /F /IM winAuthentication.exe");
			//}
			BrowserFactory.closeAllDriver();
			ExecutionPauseUtil.closeExecutionFrame();
			Log.info("Browser closed");
			Log.info("################################### --- Framework End --- #################################");
		}
	}
}
