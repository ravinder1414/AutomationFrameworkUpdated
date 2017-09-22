package automationFramework;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.jfairy.Fairy;
import org.jfairy.producer.company.Company;
import org.jfairy.producer.person.Person;
import org.jfairy.producer.person.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.testcomplete.TestCompleteBean;

import ObjectMap.OR;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

/**
 * @author mohammad.makki
 *
 */
public class LocalTC {
	/*************Database connection variables****************/
	public String database;
	public String host_name;
	public String Varshost_name; //store IP address of DB host name
	public String portnumber;
	public String schemaname;
	public String username;
	public String password;
	public String sqlquery;
	public Connection con;
	public Statement stmt;
	public ResultSet rs;
	public int fscreenlock;
	public By Locator;
	public String update=null;
	public boolean captureperform;
	public boolean capturestorevalue;
	public boolean capturecheckvalue;
	public ExcelUtils TestRun;
	public ExcelUtils TestData;
	public ExcelUtils DTsheet;
	public WebElement elem;
	public OR obj;
	public int row;
	Robot robot;
	public int passed = 0;
	public int failed = 0;
	public int caution = 0;
	public int blocked = 0;
	public int notrun = 0;
	public int act;
	public int objFoundFlag;
	public int testcasestart;
	public String sTestStep;
	public BufferedWriter bw;
	public BufferedWriter bw1;
	public int reporttype;
	public String ORvalname;
	public String ORvalue;
	public String exeStatus;
	public Map<String, String> Browsers;
	public Map<String, String> Env;
	public int iflag = 0;
	public TranslateEngine Translate;
	public String ExecutionStatus;
	public String ActualResult;
	public String TestCaseStatus;
	public boolean conditionSkip;
	public int today;
	public String filenamer;
	public int runtestloopflag;
	public ExcelUtils TestCaseRun;
	///////////////////////////////Running loop//////////////////////////////////////////////////////////////
	public int loopsize = 0;                   //Counter for the loops/nested loops been placed 
	public int[] loopstart = new int[1];        //Record index of the looptestcase array for loop start point
	public int[] loopcount = new int[1];        //How many times to execute current loop 
	public int[] loopend = new int[1];          //Record index of the looptestcase array for loop end point
	public int[] loopcnt = new int[1];          //Record how many nested loops are in progress
	public int[] loopstepcnt = new int[1];          //Record how many nested loops are in progress
	public int[] loopstepstart = new int[1];          //Record how many nested loops are in progress
	public int[] loopstepend = new int[1];          //Record how many nested loops are in progress
	public int[] dtrownumloop = new int[1];     //Data table row number for the current loop 
	public String[] loopTestCases = new String[1];              //Holds test actions for loop and nested loop
	public String[] loopTestStepID = new String[1];                //Holds test step for loop and nested loop
	public int startrow = -1;                    //Counter for recording steps been addded to array comes in loop
	public int dtrownum = 1;					//Not Used
	public int loopnum = -1;                     //Loops number running   
	public int rowcnt;                          //Not Used
	public String[] loopTestData = new String[1];           //Holds TestData sheet for each loop 
	public int loopflag = 0;  //record start of the loop keyword stays on until execution of all steps in loop
	public String RunTestCase="";
	public int looprow;
	public int loopRepeatFlag = 0;
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	public int executionCount = 0;
	public String[] loopTestCaseName = new String[1];              //Holds test cases name for loop and nested loop
	public String[] loopTestCaseId = new String[1];                //Holds test case id for loop and nested loop
	public String[] loopTestStepId = new String[1];                //Holds only test step id for loop and nested loop
	public boolean loopReportFlag = false;					//Holds flag for report generating and updating the excel sheet.
	/**********************************************************/

	/************Excel Connection Variables********************/

	static XSSFSheet ws;
	boolean isinvaliddb = false;
	boolean isconnected =false;	
	/**********************************************************/

	/************************SpiraTestReader*******************/
	String[][][] SpiraTestSteps;
	String TestSetName;
	String TestCaseName;
	String TestStep;
	String Expected;
	String SampleData;
	int TestStepID;
	ArrayList<Integer> TCID_list;
	String TestRunStatus;
	int spiraTestSetId;
	Map<Integer, String> mapLoop;
	public String getTestCaseName()	{
		return TestCaseName;
	}
	public void setTestCaseName(String act)	{
		TestCaseName = act;
	}
	public String getTestStep()	{
		return TestStep;
	}
	public void setTestStep(String act)	{
		TestStep = act;
	}
	public String getTestSetName()	{
		return TestSetName;
	}
	public void setTestSetName(String act)	{
		TestSetName = act;
	}
	public String getSampleData()	{
		return SampleData;
	}
	public void setSampleData(String act)	{
		SampleData = act;
	}
	public String getExpected()	{
		return Expected;
	}
	public void setExpected(String act)	{
		Expected = act;
	}
	public int getTestStepID()	{
		return TestStepID;
	}
	public void setTestStepID(int act)	{
		TestStepID = act;
	}
	public String getTestRunStatus()	{
		return TestRunStatus;
	}
	public void setTestRunStatus(String act)	{
		TestRunStatus = act;
	}
	public ArrayList<Integer> getTCID_list()	{
		return TCID_list;
	}
	public void setTCID_list(int act)	{
		TCID_list.add(act);
	}
	public int getSpiraTestSetId() {
		return spiraTestSetId;
	}
	public void setSpiraTestSetId(int spiraTestSetId) {
		this.spiraTestSetId = spiraTestSetId;
	}
	public Map<Integer, String> getMapLoop() {
		if(null == mapLoop){
			mapLoop = new HashMap<>();
		}
		return mapLoop;
	}
	public void setMapLoop(Map<Integer, String> mapLoop) {
		this.mapLoop = mapLoop;
	}
	/**********************************************************/

	/*********Translated Object********************************/
	String Action;
	String Obj;
	String ObjProp;
	String Event;
	String Testdata;	
	String ExecutionResult;
	public String ResultStatus="Passed";
	ArrayList<String> ResultsStatus = new ArrayList<>();
	Map<String, String> map;
	boolean bFlagSpell = false;
	/**********************************************************/

	public void setVariableData(String Variable,String Value)
	{
		map.put(Variable, Value);
	}
	public String getVariableData(String Variable)
	{
		int num;
		try{
			String randomValue = "";
			if(Variable.contains("random")){
				int SEED = 7;
				Fairy fairy = Fairy.create(); //Fairy.builder().withRandom(new Random(SEED)).withLocale(Locale.forLanguageTag("EN")).build();
				Person person = fairy.person();
				Company supplier=fairy.company();
				Variable = Variable.replace("random", "").replace("_", "");
				Pattern pt = Pattern.compile("\\(([^)]+)\\)");
				Matcher mt = pt.matcher(Variable);
				if(mt.find())
				{
					num = Integer.parseInt(mt.group().replace("(", "").replace(")", "").trim());
					Variable = Variable.replace(mt.group(), "").trim();
				}
				else
					num=0;
				switch (Variable) {
				case "name":
					randomValue = person.firstName() + " " + person.lastName();
					if(randomValue.length() >= num && num >0){
						randomValue = randomValue.substring(0, num);
					}
					break;
				case "firstname":
					randomValue = person.firstName();
					if(randomValue.length() >= num && num >0){
						randomValue = randomValue.substring(0, num);
					}
					break;
				case "lastname":
					randomValue = person.lastName();
					if(randomValue.length() >= num && num >0){
						randomValue = randomValue.substring(0, num);
					}
					break;
				case "middlename":
					randomValue = person.lastName().substring(0, 2);
					if(randomValue.length() >= num && num >0){
						randomValue = randomValue.substring(0, num);
					}
					break;
				case "number": //assign value #abc as #random_number(2)
					String min="1", max="9";
					Random rnd = new Random();
					if(num == 0){
						min = "100000";
						max = "999999";
					}else{
						for(int i=0; i<num-1; i++){
							min = min + "0";
							max = max + "9";
						}
					}
					randomValue = Integer.toString(rnd.nextInt(Integer.parseInt(max)-Integer.parseInt(min)) + Integer.parseInt(min));
					break;
				case "email":
					randomValue = person.email();
					break;
				case "address":
					randomValue = person.username();
					break;
				case "city":
					randomValue = person.getAddress().city();
					break;
				case "postcode":
					randomValue = person.getAddress().postalCode();
					break;
				case "age":
					randomValue = Integer.toString(person.age());
					break;	
				case "ninumber":
					String niFirstChar = "ABCEGHJKLMNOPRSTWXYZ";
					String niSecondChar = "ABCEGHJKLMNPRSTWXYZ";
					String niFirst = "";
					//= person.firstName().substring(0, 2).toUpperCase();
					Random rndm = new Random();
					int randomNum = rndm.nextInt(999999 - 100000) + 100000;
					niFirst = String.valueOf(niFirstChar.charAt(rndm.nextInt(niFirstChar.length())));
					niFirst = niFirst + String.valueOf(niSecondChar.charAt(rndm.nextInt(niSecondChar.length())));
					String niLast = "";
					//= person.firstName().substring(0, 1);
					String[] chars = {"A", "B", "C", "D"};
					double[] probabilities = {0.2, 0.3, 0.4, 0.5};
					double r = Math.random();
					double cdf = 0.0;
					for (int i = 0; i < chars.length; i++) {
					    cdf += probabilities[i];
					    if (r < cdf) {
					    	niLast =  chars[i];
					    	break;
					    }
					}
					if (randomNum == 000000 || randomNum == 123456 || niFirst.equalsIgnoreCase("BG")
							|| niFirst.equalsIgnoreCase("GB") || niFirst.equalsIgnoreCase("KN")
							|| niFirst.equalsIgnoreCase("NK") || niFirst.equalsIgnoreCase("BG")
							|| niFirst.equalsIgnoreCase("NT") || niFirst.equalsIgnoreCase("TN")
							|| niFirst.equalsIgnoreCase("ZZ")) {
						getVariableData("random_ninumber");
					}
					
					randomValue = niFirst + Integer.toString(randomNum) + niLast;
					break;
				case "dob":
				case "dateofbirth":
					/*SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
					//Calendar c = Calendar.getInstance();
					//c.setTime(sdf.parse(person.dateOfBirth()+""));
					randomValue = sdf.format(person.dateOfBirth().toDate());
					System.out.println(randomValue);*/
					GregorianCalendar gc = new GregorianCalendar();
			        int year = 1900 + (int)Math.round(Math.random() * (2000 - 1900));
			        gc.set(gc.YEAR, year);
			        int dayOfYear = 1 + (int)Math.round(Math.random() * (gc.getActualMaximum(gc.DAY_OF_YEAR) - 1900)); 
			        gc.set(gc.DAY_OF_YEAR, dayOfYear);
			        randomValue = gc.get(gc.DAY_OF_MONTH) + " " + Utils.getMonthName((gc.get(gc.MONTH) + 1)) + " " + gc.get(gc.YEAR);
					break;
				case "supplier":
					randomValue = supplier.name();
					break;
				case "url":
					randomValue = supplier.url();
					break;

				default:
					randomValue = supplier.vatIdentificationNumber();
					break;
				}
				return randomValue;
			}
			else if (fscreenlock == 100) {
				if (Variable.contains("#"))
					return Variable;
				else
					return "#" + Variable;
			} else {
				return map.get(Variable.replace("#", ""));
			}
		}
		catch (Exception e) {
			Log.info("random not generated " + e.getMessage());
			return "";
		}
	}


	public String getAction()	{
		return Action;
	}
	public String getObj()	{
		return Obj;
	}
	public String getObjProp()	{
		return ObjProp;
	}
	public String getEvent()	{
		return Event;
	}
	public String getTestdata()	{
		return Testdata;
	}
	public String getExecutionResult()	{
		return ExecutionResult;
	}
	public void setExecutionResult(String act)	{
		if(act=="")
			ExecutionResult ="";
		else
			ExecutionResult = ExecutionResult + act ;
	}
	public void setAction(String act)	{
		Action = act;
	}
	public void setObj(String obje)	{
		Obj = obje;
	}
	public void setObjProp(String prop)	{
		ObjProp = prop;
	}
	public void setEvent(String eve)	{
		Event = eve;
	}
	public void setTestdata(String td)	{
		Testdata =  td;
	}


	/*******Ignite Arguments from Spire or Commandline*********/
	int iProjectID;
	int iReleaseID;
	int iTestSetID;
	int iTestCaseID;
	public String sTestRunPath;
	boolean fIntegration;
	public String sTestCaseRunPath;
	/***********************************************************/

	/*************Framework Execution Variables*****************/
	String browsername;
	String URL;
	String browserver;
	String executionStartTime;
	String executionEndTime;
	public String reportReleaseId;
	public boolean runTestCaseFlag = false;
	public String getbrowsername()	{
		return browsername;
	}
	public String getURL()	{
		return URL;
	}
	public void setURL(String sURL)	{
		URL = sURL;
	}
	public void setbrosername(String sBrowseName)	{
		browsername = sBrowseName;
	}
	public void setBrowserVer(String BV)	{
		browserver = BV;
	}
	public String getBrowserVer()	{
		return browserver;
	}
	public String getExecutionStartTime() {
		return executionStartTime;
	}
	public void setExecutionStartTime(String executionStartTime) {
		this.executionStartTime = executionStartTime;
	}
	public String getExecutionEndTime() {
		return executionEndTime;
	}
	public void setExecutionEndTime(String executionEndTime) {
		this.executionEndTime = executionEndTime;
	}
	public boolean isRunTestCaseFlag() {
		return runTestCaseFlag;
	}
	public void setRunTestCaseFlag(boolean runTestCaseFlag) {
		this.runTestCaseFlag = runTestCaseFlag;
	}
	/***********************************************************/

	public LocalTC(String ProjectID,String ReleaseID,String TestSetID,String TestCaseID,String TestRunPath,String Integration) {
		iProjectID = Integer.parseInt(ProjectID) ;
		iReleaseID =Integer.parseInt(ReleaseID) ;
		//iTestSetID = Integer.parseInt(TestSetID) ;
		Constant.tempTestReportPath = TestSetID;
		if (! (Constant.tempTestReportPath.endsWith("//") || Constant.tempTestReportPath.endsWith("\\")))
			Constant.tempTestReportPath = Constant.tempTestReportPath + "\\";
		//Constant.ieDriverPath = Constant.tempTestReportPath + Constant.ieDriverPath;
		//Constant.chromeDriverPath = Constant.tempTestReportPath + Constant.chromeDriverPath;
		Constant.Path_TestData = Constant.tempTestReportPath + Constant.Path_TestData;  
		Constant.Path_ScreenShot=Constant.tempTestReportPath +Constant.Path_ScreenShot;
		Constant.Path_IgniteTestCaseRun=Constant.tempTestReportPath +Constant.Path_IgniteTestCaseRun;
		Constant.File_DownloadPath=TestSetID;
		fscreenlock = Integer.parseInt(TestCaseID) ;
		sTestRunPath = TestRunPath;
		TestCaseName = "";
		runtestloopflag =0;
		TestStep = "";
		Expected = "";
		SampleData = "";
		TestStepID = 0;
		ScreenshotTypeFlag = 0;
		TestRunStatus = "";
		host_name="uk-evosql-t2";
		database="mssql";
		schemaname="dbEvolutionCMS6Test";
		username="EvoReportBuildRunner";
		password="Agamemn0n!";
		fIntegration = Boolean.parseBoolean(Integration);
		browsername = "Firefox";
		URL = Constant.URL;
		TCID_list = new ArrayList<Integer>(500);
		reporttype = 0;
		exeStatus = "True";
		conditionSkip = false;
		capturestorevalue =true;
		capturecheckvalue = true;
		objFoundFlag =0;
		passed = 0;
		failed = 0;
		con = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		captureperform = true;
		startrow = -1;
		map = new HashMap<String, String>();
		ActualResult = "";
		ExecutionStatus ="";
		TestCaseStatus ="Passed"; 
		Browsers = new LinkedHashMap<String, String>();
		Env = new LinkedHashMap<String, String>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		map.put("current_date", sdf.format(date));
		map.put("currentdate", sdf.format(date));
		map.put("random_number",Randomnumber());
		map.put("csvDataFile","csv_Data_File.csv");
		Browsers.put("483", "Firefox");
		Browsers.put("484", "IE");
		Browsers.put("485", "Chrome");
		Browsers.put("512", "IE");
		Browsers.put("514", "Firefox");
		Browsers.put("513", "Chrome");
		Browsers.put("640", "IE");
		Browsers.put("639", "Firefox");
		Browsers.put("638", "Chrome");
		Env.put("641", "https://carbon60global-cm-sitecoreprod.impellam.com");
		Env.put("481", "https://cms6.test.evolution-system.com");
		Env.put("482", "https://cms2.test.evolution-system.com");
		Env.put("480", "https://cms5test.evolution-system.com");
		Env.put("515", "https://cms6.test.evolution-system.com");
		Env.put("516", "https://cms2.test.evolution-system.com");
		Env.put("517", "https://staging.evolution-system.com/universal/loginaccess.aspx");
		Env.put("518", "https://cms1test.evolution-system.com");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(new Date());
		String[] dateArray = strTimeStamp.split("-");
		today = Integer.parseInt(dateArray[2]);
		loopstart[0] = 0;        //Record index of the looptestcase array for loop start point
		loopcount[0] = 0;        //How many times to execute current loop 
		loopend[0] = 0;          //Record index of the looptestcase array for loop end point
		loopcnt[0] = 0;          //Record how many nested loops are in progress
		dtrownumloop[0] = 0;     //Data table row number for the current loop 
		loopTestCases[0] = "";              //Holds test actions for loop and nested loop
		loopTestStepID[0] = "";                //Holds test step for loop and nested loop
		loopTestData[0]= "";  
	}

	public int getProjectID()	{
		return this.iProjectID;
	}
	public int getReleaseID()	{
		return this.iReleaseID;
	}
	public int getTestSetID()	{
		return this.iTestSetID;
	}
	public void setTestSetID(int tsid)	{
		iTestSetID = tsid;
	}
	public int getTestCaseID()	{
		return this.iTestCaseID;
	}
	public void setTestCaseID(int tcid)	{
		iTestCaseID = tcid;
	}
	public String getTestRunPath()	{
		return this.sTestRunPath;
	}
	public boolean getIntegration()	{
		return this.fIntegration;
	}
	public String getsTestCaseRunPath() {
		return sTestCaseRunPath;
	}
	public void setsTestCaseRunPath(String sTestCaseRunPath) {
		this.sTestCaseRunPath = sTestCaseRunPath;
	}

	/************************************Framework Variables**********************************/
	String strResultPath;
	String DetailReport;
	String ScreenShotReport;
	int ScreenshotTypeFlag;
	String globalCompName;
	public List<ReporterObject> reporterObjectList = new ArrayList<>();
	public List<ReporterSummaryObject> reporterSumObjList = null;
	public LinkedHashMap<String, List<ReporterSummaryObject>> reportMap = null;
	public List<ReporterCommon> reportCommonObj = new ArrayList<>();
	String res_type;
	String exceptionVar;
	String screenshotTimeStamp;
	int screenTestCaseID;
	String stopExecution = "false";
	FileWriter fileWriter = null;
	public String reportHeader;
	public String sPreURL = "";
	public String sNextURL = "";
	public String strReportStatusConf = "";
	public String getstrResultPath() {
		return strResultPath;
	}
	public void setstrResultPath(String strpath)	{
		strResultPath = strpath;
	}
	public String getDetailReport()	{
		return DetailReport;
	}
	public void setDetailReport(String strpath)	{
		DetailReport = strpath;
	}
	public int getScreenshotTypeFlag()	{
		return ScreenshotTypeFlag;
	}
	public void setScreenshotTypeFlag(int strpath)	{
		ScreenshotTypeFlag = strpath;
	}
	public String getScreenShotReport()	{
		return ScreenShotReport;
	}
	public void setScreenShotReport(String strpath)	{
		ScreenShotReport = strpath;
	}
	public String getglobalCompName()	{
		return globalCompName;
	}
	public void setglobalCompName(String strpath)	{
		globalCompName = strpath;
	}
	public String getActualResult() {
		return ActualResult;
	}
	public void setActualResult(String actualResult) {
		ActualResult = actualResult;
	}
	public String getResultStatus() {
		return ResultStatus;
	}
	public void setResultStatus(String resultStatus) {
		
		if (resultStatus.equals(""))
			ResultStatus = "Passed";
		else if (resultStatus.equals("Passed") && (ResultStatus.equals("Passed")))
			ResultStatus = "Passed";	
		else if (resultStatus.equals("Failed") && (ResultStatus.equals("Passed") || ResultStatus.equals("Blocked")))
			ResultStatus = "Failed";
		else if (ResultStatus.equals("Failed"))
			ResultStatus = "Failed";
		else if (ResultStatus.equals("Blocked"))
			ResultStatus = "Blocked";
		else if (resultStatus.equals("Blocked") && (ResultStatus.equals("Passed")))
			ResultStatus = "Blocked";
		else if (resultStatus.equals("Skipped") && (ResultStatus.equals("Passed")))
			ResultStatus = "Passed";
		else if (resultStatus.equals("Caution") && (ResultStatus.equals("Passed")))
			ResultStatus = "Caution";
		else
			ResultStatus = resultStatus;
	}
	public ArrayList<String> getResultsStatus() {
		return ResultsStatus;
	}
	public void setResultsStatus(ArrayList<String> resultsStatus) {
		ResultsStatus = resultsStatus;
	}
	public String getExecutionStatus() {
		return ExecutionStatus;
	}
	public void setExecutionStatus(String executionStatus) {
		if(executionStatus.equals("Failed"))
			ExecutionStatus = "Failed";
		else if(executionStatus.equals("Blocked") )
			ExecutionStatus = "Blocked";
		else if(executionStatus.equals("Caution"))
			ExecutionStatus = "Caution";
		else
			ExecutionStatus =executionStatus;

	}
	public String getTestCaseStatus() {
		return TestCaseStatus;
	}
	public void setTestCaseStatus(String testCaseStatus) {
		if(!testCaseStatus.equals(""))
			TestCaseStatus=testCaseStatus;
		else if (ExecutionStatus.equals("Passed") && (TestCaseStatus.equals("Passed")))
			TestCaseStatus = "Passed";	
		else if (ExecutionStatus.equals("Failed") && (TestCaseStatus.equals("Passed") || TestCaseStatus.equals("Blocked") || TestCaseStatus.equals("Failed")))
			TestCaseStatus = "Failed";
		else if (ExecutionStatus.equals("Blocked") && TestCaseStatus.equals("Failed"))
			TestCaseStatus = "Failed";
		else if ((ExecutionStatus.equals("Blocked") || ExecutionStatus.equals("Caution")) && (TestCaseStatus.equals("Passed")))
			TestCaseStatus = "Blocked";
		else if (ExecutionStatus.equals("Skipped") && (TestCaseStatus.equals("Passed")))
			TestCaseStatus = "Passed";
		/*else if (ExecutionStatus.equals("Caution") && (TestCaseStatus.equals("Passed")))
			TestCaseStatus = "Caution";*/
		
	}
	public String getReportReleaseId() {
		return reportReleaseId;
	}
	public void setReportReleaseId(String reportReleaseId) {
		this.reportReleaseId = reportReleaseId;
	}
	public LinkedHashMap<String, List<ReporterSummaryObject>> getReportMap() {
		if(null == reportMap)
			reportMap = new LinkedHashMap<>();
		return reportMap;
	}
	public void setReportMap(LinkedHashMap<String, List<ReporterSummaryObject>> reportMap) {
		this.reportMap = reportMap;
	}
	public FileWriter getFileWriter() {
		return fileWriter;
	}
	public void setFileWriter(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}
	public String getReportHeader() {
		return reportHeader;
	}
	public void setReportHeader(String reportHeader) {
		this.reportHeader = reportHeader;
	}

	public List<ReporterSummaryObject> getReporterSumObjList() {
		if (null == reporterSumObjList) {
			reporterSumObjList = new ArrayList<>();
		}
		return reporterSumObjList;
	}
	public void setReporterSumObjList(List<ReporterSummaryObject> reporterSumObjList) {
		this.reporterSumObjList = reporterSumObjList;
	}
	public String getsPreURL() {
		return sPreURL;
	}
	public void setsPreURL(String sPreURL) {
		this.sPreURL = sPreURL;
	}
	public String getsNextURL() {
		return sNextURL;
	}
	public void setsNextURL(String sNextURL) {
		this.sNextURL = sNextURL;
	}
	public String getStrReportStatusConf() {
		return strReportStatusConf;
	}
	public void setStrReportStatusConf(String strReportStatusConf) {
		this.strReportStatusConf = strReportStatusConf;
	}

	/////////////////////Date picker////////////////////////////////////////
	private String[] envprevMonth1={"prev","Prev"}; // Specify a class name through which the framework can identify the image representing the previous month
	private String[] envnextMonth1={"next","Next"};// Specify a class name through which the framework can identify the image representing the next month
	private String[] envtitleMonth={"month"};// Specify a class name through which we can identify the title month element in calendar control element
	private String[] envtitleYear={"year"};// Specify a class name through which we can identify the title year element in calendar control element
	public String[] getEnvnextMonth1() {
		return envnextMonth1;
	}
	public void setEnvnextMonth1(String[] envnextMonth1) {
		this.envnextMonth1 = envnextMonth1;
	}
	public String[] getEnvprevMonth1() {
		return envprevMonth1;
	}
	public void setEnvprevMonth1(String[] envprevMonth1) {
		this.envprevMonth1 = envprevMonth1;
	}
	public String[] getEnvtitleMonth() {
		return envtitleMonth;
	}
	public void setEnvtitleMonth(String[] envtitleMonth) {
		this.envtitleMonth = envtitleMonth;
	}
	public String[] getEnvtitleYear() {
		return envtitleYear;
	}
	public void setEnvtitleYear(String[] envtitleYear) {
		this.envtitleYear = envtitleYear;
	}
	public String Randomnumber(){
		Random rnd = new Random();
		return ((1 + rnd.nextInt(999))+"");
	}
	public String getRes_type() {
		return res_type;
	}
	public void setRes_type(String res_type) {
		this.res_type = res_type;
	}
	public String getExceptionVar() {
		return exceptionVar;
	}
	public void setExceptionVar(String exceptionVar) {
		this.exceptionVar = exceptionVar;
	}
	public int getExecutionCount() {
		return executionCount;
	}
	public void setExecutionCount(int executionCount) {
		this.executionCount = executionCount;
	}
	public String getScreenshotTimeStamp() {
		if(null == screenshotTimeStamp){
			screenshotTimeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		}
		return screenshotTimeStamp;
	}
	public void setScreenshotTimeStamp(String screenshotTimeStamp) {
		this.screenshotTimeStamp = screenshotTimeStamp;
	}
	public int getScreenTestCaseID() {
		return screenTestCaseID;
	}
	public void setScreenTestCaseID(int screenTestCaseID) {
		this.screenTestCaseID = screenTestCaseID;
	}
	public String getStopExecution() {
		return stopExecution;
	}
	public void setStopExecution(String stopExecution) {
		this.stopExecution = stopExecution;
	}
	public boolean isbFlagSpell() {
		return bFlagSpell;
	}
	public void setbFlagSpell(boolean bFlagSpell) {
		this.bFlagSpell = bFlagSpell;
	}
	public List<ReporterCommon> getReportCommonObj() {
		if(null == reportCommonObj)
			reportCommonObj = new ArrayList<>();
		return reportCommonObj;
	}
	public void setReportCommonObj(List<ReporterCommon> reportCommonObj) {
		this.reportCommonObj = reportCommonObj;
	}
	/**********************************Thread Variable***********************************/
	public Thread automationThread = null;
	public Thread appletThread = null;

	/*************************************Test Complete*************************************************/
	ArrayList<TestCompleteBean> testCompleteList;
	String csvFilePath;

	//ProjectId, ReleaseID, executionStartTime, testCompleteStatus, csvFilePath, excelFilePath, integrationFlag
	public LocalTC(String ProjectId, String ReleaseID, String executionStartTime, String testCompleteCode, String csvFilePath, String excelFilePath, String integration) {
		iProjectID = Integer.parseInt(ProjectId);
		iReleaseID = Integer.parseInt(ReleaseID);
		this.executionStartTime = executionStartTime;
		this.csvFilePath = csvFilePath;
		fscreenlock = Integer.parseInt(testCompleteCode);
		sTestRunPath = excelFilePath;
		fIntegration = Boolean.parseBoolean(integration);
		TestRunStatus = "";
	}
	public ArrayList<TestCompleteBean> getTestCompleteList() {
		if(null == testCompleteList){
			testCompleteList = new ArrayList<>();
		}
		return testCompleteList;
	}
	public void setTestCompleteList(ArrayList<TestCompleteBean> testCompleteList) {
		this.testCompleteList = testCompleteList;
	}
	public String getCsvFilePath() {
		return csvFilePath;
	}
	public void setCsvFilePath(String csvFilePath) {
		this.csvFilePath = csvFilePath;
	}

	/*****************************************Spira for Test complete*****************************************/

	public int testComTestSetId;
	public int testComTestCaseId;
	public int testComTestStepId;
	public String testComTestSetStatus = "";
	public String testComTestCaseStatus = "";
	public String testComTestStepStatus = "";
	public int getTestComTestSetId() {
		return testComTestSetId;
	}
	public void setTestComTestSetId(int testComTestSetId) {
		this.testComTestSetId = testComTestSetId;
	}
	public int getTestComTestCaseId() {
		return testComTestCaseId;
	}
	public void setTestComTestCaseId(int testComTestCaseId) {
		this.testComTestCaseId = testComTestCaseId;
	}
	public int getTestComTestStepId() {
		return testComTestStepId;
	}
	public void setTestComTestStepId(int testComTestStepId) {
		this.testComTestStepId = testComTestStepId;
	}
	public String getTestComTestSetStatus() {
		return testComTestSetStatus;
	}
	public void setTestComTestSetStatus(String testComTestSetStatus) {
		this.testComTestSetStatus = testComTestSetStatus;
	}
	public String getTestComTestCaseStatus() {
		return testComTestCaseStatus;
	}
	public void setTestComTestCaseStatus(String testComTestCaseStatus) {
		this.testComTestCaseStatus = testComTestCaseStatus;
	}
	public String getTestComTestStepStatus() {
		return testComTestStepStatus;
	}
	public void setTestComTestStepStatus(String testComTestStepStatus) {
		this.testComTestStepStatus = testComTestStepStatus;
	}
	public boolean bFlagAuth = true;
	public boolean isbFlagAuth() {
		return bFlagAuth;
	}
	public void setbFlagAuth(boolean bFlagAuth) {
		this.bFlagAuth = bFlagAuth;
	}
}
