package SeleniumWD;

/********************************************************************************************************
 *Project Name		: Ignite Automation framework 
 *Author		    : Bharat Sethi
 *Version	    	: V1.0
 *Date of Creation	: 28-04-2016
 *Description       : Getting maximum benefits with minimum effort. This Framework will give ability to increase 
 *					  the efficiency of resources, increase test coverage, and increase the quality and 
 *					  reliability of the software.
 *Functions			:
 *
 ********************************************************************************************************
 */
import static org.junit.Assert.*;
import org.junit.After;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert; 
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.android.AndroidDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
//import java.awt.event.ActionEvent;
import java.io.*;
import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import automationFramework.Reporter;
import utility.Log;

//import trying.xpath.Setting;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import test.sample.LogerExample;
//import autoitx4java.AutoItX;
//import com.thoughtworks.selenium.Selenium;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Calendar;
//import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet; 
import java.util.Iterator; 
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set; 
import java.util.Stack;
//import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.System;
import java.lang.reflect.Method;
import java.net.InetAddress;
//import java.lang.reflect.Method;
//import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit; 
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Test {
	int scriptfound = 0;
	Setting setObj = new Setting(); 

	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static RemoteWebDriver D8;
	String browserver = null;
	String mm = null;
	String dd = null;
	String yyyy = null;
	String monthpart;
	String monthpartjap;
	Date cur_dt = null;
	String testSuite;
	String testScript;

	String database;
	String host_name;
	String portnumber;
	String schemaname;
	String username;
	String password;
	String sqlquery;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String update=null;

	static XSSFSheet ws;
	boolean isinvaliddb = false;
	boolean isconnected =false;

	static String globalCompName;
	static String objectRepository;
	int startrow = 0;
	static String reportsPath;
	String strResultPath;
	String[] TCNm = null;
	static String exeStatus = "True";
	static String screenReport;
	static int rowcnt;
	static int ORrowcount = 0;
	static String ORvalname = "";
	static int dtrownum = 1;
	static String ORvalue = "";
	static String Action = "";
	static String cCellData = "";
	static String dCellData = "";
	static String sComments="";
	static double nComments=0;
	static boolean  bComments=false;
	static String htmlname = "";
	static String cCellObjType = "";
	static String cCellObjName = ""; 

	String[] cCellDataVal = null;
	String[] dCellDataVal = null;
	String ObjectSet;
	String[] ObjectSet1 = null;
	String ObjectSet2 = "";
	String ObjectSetVal = "";
	static XSSFSheet DTsheet = null;
	static XSSFSheet ORsheet;
	String Searchtext;
	String parentWindowHandle;
	String parentWindowHandle1;
	String windowHandle;
	String ieDriverPath;
	String tempTestReportPath;


	String reportsFolder="";
	static int iflag = 0;
	static String comments =null;

	static int screenshotflag = 0;
	static int loopflag = 0;
	static int j = 0;
	static int loopsize = -1;
	int[] loopstart = new int[1];
	int[] loopcount = new int[1];
	int[] loopend = new int[1];
	static int[] loopcnt = new int[1];
	static int[] dtrownumloop = new int[1];
	boolean captureperform = false;
	static boolean capturecheckvalue = false;
	static boolean capturestorevalue = false;
	static XSSFSheet TScsheet;
	static XSSFWorkbook TScworkbook;
	static String execpath;
	static int TScrowcount = 0;
	static int loopnum = 1;
	static int windowFound = 0;
	static String TScname;
	static String ActionVal;
	String BrowserType;// Assign with either FF or IE
	static int DTcolumncount = 0;
	static WebElement elem = null;
	Alert dialogSwitch = null; 
	private static Map<String, String> map = new HashMap<String, String>();
	// private static Map<String, Float> mapint = new HashMap<String, Float>();
	public static int devFlag = 0;
	public static String varname;
	public static int initscreen = 1;
	public static int initscreenFlag = 1;
	String browserName;
	public static String objectType;
	public static int objFoundFlag = 0;
	public static String capString = "";
	static int locatorError = 0;
	static int ScreenshotTypeFlag = 0;
	int varUpdateReport;
	int today = 0;
	String selectedDateClass = "";
	int lastSelecteddateJQ = 0;
	int lastSelectedmonthJQ = 0;
	int lastSelectedyearJQ = 0;
	static int conditionline = 0;
	static int reporttype = 0;
	static String reusableComponents;
	static String ExpectedReportData = "";
	static String ActualReportData = "";
	static String OptionalString = "";
	static String OptionalString2 = "";
	static String[] getCellArray;
	static int cCellDatanum =0;
	static int waitcCellDatanum=0;
	static String DetailReport="";
	static String ScreenshotPath="";
	public static String ScreenFolder;


	/****************************************************************************************
	 * 
	 * This function reads the selenium utility file and identifies where Object
	 * Repository, Test Suite & Test Scripts are located
	 */
	@SuppressWarnings({"unchecked", "deprecation"})
	public void readUtilFile() {
		//LoadLog();
		XSSFWorkbook w1 = null;
		try {

			if (!setObj.getUtilityFilePath().toString().endsWith("xlsx")) {
				System.out
				.println("Utility file defined in Setting.Java is not a correct one. Give the correct (xlsx) Format of Utility File");
				Log.info("Utility file defined in Setting.Java is not a correct one. Give the correct (xlsx) Format of Utility File");
				return;
			} else {
				w1 = new XSSFWorkbook(setObj.getUtilityFilePath());

			}

		} catch (Exception e) {
			System.out
			.println("Selenium Utility file is not accessible. Check your Utility file in the given path  "+e.getMessage());
			Log.info("Selenium Utility file is not accessible. Check your Utility file in the given path  ");
			return;
		} 


		try {
			XSSFSheet sheet = w1.getSheetAt(0);

			if(sheet.getRow(1).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				testSuite ="";
			}else if(sheet.getRow(1).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	sTestSuite = sheet.getRow(1).getCell(1).getNumericCellValue();
				testSuite = String.valueOf(sTestSuite);
			}else if(sheet.getRow(1).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				testSuite = sheet.getRow(1).getCell(1).getStringCellValue();
			}else if(sheet.getRow(1).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				testSuite = sheet.getRow(1).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(2).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				testScript ="";
			}else if(sheet.getRow(2).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	sTestScript = sheet.getRow(2).getCell(1).getNumericCellValue();
				testScript = String.valueOf(sTestScript);
			}else if(sheet.getRow(2).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				testScript = sheet.getRow(2).getCell(1).getStringCellValue();
			}else if(sheet.getRow(2).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				testScript = sheet.getRow(2).getCell(1).getStringCellValue();
			}	 


			if(sheet.getRow(3).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				objectRepository ="";
			}else if(sheet.getRow(3).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	objRepository = sheet.getRow(3).getCell(1).getNumericCellValue();
				objectRepository = String.valueOf(objRepository);
			}else if(sheet.getRow(3).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				objectRepository = sheet.getRow(3).getCell(1).getStringCellValue();
			}else if(sheet.getRow(3).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				objectRepository = sheet.getRow(3).getCell(1).getStringCellValue();
			}			



			if(sheet.getRow(5).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				reportsPath ="";
			}else if(sheet.getRow(5).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	sReportPath = sheet.getRow(5).getCell(1).getNumericCellValue();
				reportsPath = String.valueOf(sReportPath);
			}else if(sheet.getRow(5).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				reportsPath = sheet.getRow(5).getCell(1).getStringCellValue();
			}else if(sheet.getRow(5).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				reportsPath = sheet.getRow(5).getCell(1).getStringCellValue();
			}				 

			if(sheet.getRow(6).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				screenReport ="";
			}else if(sheet.getRow(6).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	sScreenReport = sheet.getRow(6).getCell(1).getNumericCellValue();
				screenReport = String.valueOf(sScreenReport);
			}else if(sheet.getRow(6).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				screenReport = sheet.getRow(6).getCell(1).getStringCellValue();
			}else if(sheet.getRow(6).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				screenReport = sheet.getRow(6).getCell(1).getStringCellValue();
			}	


			if(sheet.getRow(7).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				browserName ="";
			}else if(sheet.getRow(7).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	sBrowserName = sheet.getRow(7).getCell(1).getNumericCellValue();
				browserName = String.valueOf(sBrowserName);
			}else if(sheet.getRow(7).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				browserName = sheet.getRow(7).getCell(1).getStringCellValue();
			}else if(sheet.getRow(7).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				browserName = sheet.getRow(7).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(9).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				tempTestReportPath ="";
			}else if(sheet.getRow(9).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(9).getCell(1).getNumericCellValue();
				tempTestReportPath = String.valueOf(password1);
			}else if(sheet.getRow(9).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				tempTestReportPath = sheet.getRow(9).getCell(1).getStringCellValue();
			}else if(sheet.getRow(9).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				tempTestReportPath = sheet.getRow(9).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(8).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				ieDriverPath ="";
			}else if(sheet.getRow(8).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(8).getCell(1).getNumericCellValue();
				ieDriverPath = String.valueOf(password1);
			}else if(sheet.getRow(8).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				ieDriverPath = sheet.getRow(8).getCell(1).getStringCellValue();
			}else if(sheet.getRow(8).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				ieDriverPath = sheet.getRow(8).getCell(1).getStringCellValue();
			}

			if(sheet.getRow(11).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				database ="";
			}else if(sheet.getRow(11).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(11).getCell(1).getNumericCellValue();
				database = String.valueOf(password1);
			}else if(sheet.getRow(11).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				database = sheet.getRow(11).getCell(1).getStringCellValue();
			}else if(sheet.getRow(11).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				database = sheet.getRow(11).getCell(1).getStringCellValue();
			}

			if(sheet.getRow(12).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				host_name ="";
			}else if(sheet.getRow(12).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(12).getCell(1).getNumericCellValue();
				host_name = String.valueOf(password1);
			}else if(sheet.getRow(12).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				host_name = sheet.getRow(12).getCell(1).getStringCellValue();
			}else if(sheet.getRow(12).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				host_name = sheet.getRow(12).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(13).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				portnumber ="";
			}else if(sheet.getRow(13).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(13).getCell(1).getNumericCellValue();
				portnumber = String.valueOf(password1);
			}else if(sheet.getRow(13).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				portnumber = sheet.getRow(13).getCell(1).getStringCellValue();
			}else if(sheet.getRow(13).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				portnumber = sheet.getRow(13).getCell(1).getStringCellValue();
			}

			if(sheet.getRow(14).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				schemaname ="";
			}else if(sheet.getRow(14).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(14).getCell(1).getNumericCellValue();
				schemaname = String.valueOf(password1);
			}else if(sheet.getRow(14).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				schemaname = sheet.getRow(14).getCell(1).getStringCellValue();
			}else if(sheet.getRow(14).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				schemaname = sheet.getRow(14).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(15).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				username ="";
			}else if(sheet.getRow(15).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(15).getCell(1).getNumericCellValue();
				username = String.valueOf(password1);
			}else if(sheet.getRow(15).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				username = sheet.getRow(15).getCell(1).getStringCellValue();
			}else if(sheet.getRow(15).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				username = sheet.getRow(15).getCell(1).getStringCellValue();
			}


			if(sheet.getRow(16).getCell(1).getCellType()==XSSFCell.CELL_TYPE_BLANK)	{
				password ="";
			}else if(sheet.getRow(16).getCell(1).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
				double	password1 = sheet.getRow(16).getCell(1).getNumericCellValue();
				password = String.valueOf(password1);
			}else if(sheet.getRow(16).getCell(1).getCellType()==XSSFCell.CELL_TYPE_STRING){
				password = sheet.getRow(16).getCell(1).getStringCellValue();
			}else if(sheet.getRow(16).getCell(1).getCellType()==XSSFCell.CELL_TYPE_FORMULA){
				password = sheet.getRow(16).getCell(1).getStringCellValue();
			}

			update = sheet.getRow(17).getCell(1).getStringCellValue();

			try {

				execpath = sheet.getRow(10).getCell(1).getStringCellValue();

				reusableComponents =sheet.getRow(11).getCell(1).getStringCellValue();


			} catch (ArrayIndexOutOfBoundsException exp10) {
				System.out
				.println("WARNING: If you want to use file upload/ download automation feature, use the new format of Utility(Configuration) Excel Which contains the path for the FileManager EXE. ");
				Log.info("If you want to use file upload/ download automation feature, use the new format of Utility(Configuration) Excel Which contains the path for the FileManager EXE.");
			}
			if (!testSuite.endsWith(".xlsx")) {
				if (testSuite.toString() == "") {
					System.out
					.println("No proper TestSuite is defined in Selenium Utility file. Give the correct TestSuite name.");
				} else {
					System.out
					.println("Invalid File Format of TestSuite in Selenium Utility file: "
							+ testSuite + ". Give only xlsx file format");
					Log.info( "Invalid File Format of TestSuite in Selenium Utility file: "+ testSuite + ". Give only xlsx file format  ");
				}

			}

			if (!objectRepository.endsWith(".xlsx")) {
				if (objectRepository.toString() == "") {
					System.out
					.println("No proper Object repository is defined in Selenium Utility excel. Give the correct Object repository. ");
					Log.info("No proper Object repository is defined in Selenium Utility excel. Give the correct Object repository.");
				} else {
					System.out
					.println("Invalid File Format of Object Repository : "
							+ objectRepository
							+ " in Selenium Utility file. Give only xlsx file format");
					Log.info("Invalid File Format of Object Repository : "
							+ objectRepository
							+ " in Selenium Utility file. Give only xlsx file format");
				}

			}
			if (browserName.toString() == "") 
			{
				System.out
				.println("The browser type in the Selenium Utility file is not proper. Give a valid browser type");
				Log.info( "The browser type in the Selenium Utility file is not proper. Give a valid browser type");
			}

			for (int z = 0; z < 1; z++) 
			{
				loopstart[z] = 0;
				loopend[z] = 0;
				loopcnt[z] = 0;
				dtrownumloop[z] = 1;
				loopcount[z] = 0;
			}

		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out
			.println("The format of Utility file is not a correct one. Give the correct (Xlsx) Format of Utility File.");
			Log.info( "The format of Utility file is not a correct one. Give the correct (Xlsx) Format of Utility File");
			System.out.println(ex.getMessage());
			return;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Log.info( e.getMessage());

		}
		try {

			setBrowser(browserName);

		} catch (Exception e) {
			System.out
			.println("Could not launch the Browser. Check the browser settings");

			Log.info( "Could not launch the Browser. Check the browser settings");
			return;
		}
		try {
			findExecTestscript(testSuite, testScript, objectRepository);

		} catch (Exception e) {
			System.out.println("Error Occured in FindExecTestscript function "+e.getMessage());
			Log.info( "Error Occured in FindExecTestscript function "+e.getMessage());

		}

	}




	private  String createConnection(String database, String schemaName, String username,String password) 
	{
		System.out.println(" I am in createconnection");
		String url1=null;
		try {
			if(database.equalsIgnoreCase("mysql")){
				String hostadd=  host_name.trim();
				InetAddress ipaddr =InetAddress.getByName(host_name.trim());
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://"+host_name+":"+portnumber+"/"+schemaName;
				if (captureperform == true) {
					screenShot(loopnum, TScrowcount, TScname);
				}
				return url;
			}
			else if(database.equalsIgnoreCase("mssql")){
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				url1 = "jdbc:sqlserver://";
				String hostname =host_name;
				String db = "databasename="+schemaName;
				String user = "user="+username;
				String pass  ="password="+password;
				String url =url1+host_name+";"+db+";"+user+";"+pass+";";
				return url;
			}
			else{
				System.out.println(database+" is not handeled as database in this version of  framework");
				Reporter.ReportEvent("missing");
				if (captureperform == true) {
					screenShot(loopnum, TScrowcount, TScname);
				}
				return url1;
			}

		} catch (Exception e) {
			try {
				Reporter.ReportEvent("invalidConnection");
				if (captureperform == true) {
					screenShot(loopnum, TScrowcount, TScname);
				}
			} catch (IOException e1) {
				System.out.println("Invalid Connection  "+e1.getMessage());
				Log.info( "Invalid Connection  "+e1.getMessage());

			} catch (Exception e2) {
				System.out.println("Error Occured in Reporter.ReportEvent Function "+e2.getMessage());
				Log.info( "Error Occured in Reporter.ReportEvent Function "+e2.getMessage());
			}
		} 

		return url1;
	}


	private boolean  getConnection(String url){
		boolean parameter = false;
		SQLException errorString =null;
		try {

			if(database.equalsIgnoreCase("mssql")){

				con=DriverManager.getConnection(url);


				stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);


				rs=stmt.executeQuery(sqlquery);
				isconnected = true;
				return isconnected;

			}else if(database.equalsIgnoreCase("mysql")){
				System.out.println("In get connection part of MSSQL");
				con=DriverManager.getConnection(url, username, password);
				stmt=con.createStatement();
				rs=stmt.executeQuery(sqlquery);
				isconnected = true;
				return isconnected;
			}
			return isconnected;

		} catch (SQLException e) {

			errorString = e;
			Log.info( "Connection to Database is failed, please verify connection URL parameters which are set in Selenium Utility File  "+e.getMessage());
			System.out.println("Connection to Database is failed, please verify connection URL parameters which are set in Selenium Utility File  "+e.getMessage());
			return isconnected;


		}


	}


	private void executeQuery(ResultSet rs){

		String resultfile =null;

		File file = new File("Temp");
		boolean result=false; 

		if(!file.exists()){
			System.out.println("Creating Directory - "+file);;
			try{
				file.mkdir();
				result = true;
			}catch(SecurityException se){
				System.out.println(se.getMessage());
				Log.info( "SecurityException Error Occured in executeQuery  "+se.getMessage());
			}
		}
		if(result){
			System.out.println("Direcotry is already Created");
			Log.info( "Direcotry is already Created");
		}


		String workingDir = System.getProperty("user.dir");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		Date date = new Date();
		String CurrentDate= dateFormat.format(date);
		String filepath=workingDir+"\\"+"Temp"+"\\"+CurrentDate+"."+"xlsx";

		resultfile = filepath;
		String SheetName ="dbData";

		try {
			FileOutputStream fo = new FileOutputStream(filepath);

			XSSFWorkbook workbook = new XSSFWorkbook(); 

			ws = workbook.createSheet(SheetName);
			FormulaEvaluator wbevaluator = workbook.getCreationHelper().createFormulaEvaluator();
			if(update.equalsIgnoreCase("yes")){
				wbevaluator.evaluateAll();
			}


			if (!resultfile.endsWith(".xlsx")){
				System.out.println("Please correct Excel file extension, Data can be exported to only .xlsx file");
				Log.info( "Exception occured in executeQuery Please correct Excel file extension, Data can be exported to only .xlsx file");
				System.out.println("nofetchdata");
			}else{

				rs.beforeFirst(); 
				XSSFCell cell =null;
				ResultSetMetaData rsmd= (ResultSetMetaData) rs.getMetaData();

				int column = rsmd.getColumnCount();

				String columnname = rsmd.getColumnLabel(1);

				int rowcount=0;
				while(rs.next()){
					rowcount++;
				}



				rs.beforeFirst();
				int tempj=0;
				while(rs.next()){                      //Writing Column Name in excel
					XSSFRow row = ws.createRow(rowcount);

					for(int c =0;c<column;c++){
						cell = row.createCell(c);


						cell.setCellValue(rsmd.getColumnLabel(c+1)+0+" "+c);								

					}

				}

				rs.beforeFirst();
				int i=0;
				tempj=1;
				while(rs.next()){

					for(int c =0;c<column;c++){


						cell.setCellValue(rs.getString(c+1)+tempj+" "+c);


					}


					tempj++;
				}



				try {
					FileOutputStream fileOut = new FileOutputStream(filepath);

					workbook.write(fileOut);

					workbook.close();
					fo.close();


				} catch (IOException e) {
					Log.info( "IO Exception Occured in executeQuery -  while Writing data into Excel"+e.getMessage());
					System.out.println("IO Exception Occured, while Writing data into Excel "+e);

				}


			}	

		} catch (SQLException e) {
			System.out.println(" SQLException occured in connection with DB, check your credentials are correct "+e);
			Log.info( " SQLException occured in executeQuery -  while connection with DB, check your credentials are correct "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IO exception occured in executeQuery function  "+e.getMessage());
			Log.info( "IO Exception Occured in executeQuery -   "+e.getMessage());
		}
	}


	public void setBrowser(String BrowserType) {
		try {
			System.out.println(BrowserType);

			switch (BrowserType.toUpperCase()) {
			case "IE":
				System.setProperty("webdriver.ie.driver", ieDriverPath);
				// Changed on 16/12/2013
				DesiredCapabilities capability = DesiredCapabilities
						.internetExplorer();
				capability
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				capability.setCapability("useLegacyInternalServer", true);
				capability.setCapability("nativeEvents", false);
				D8 = new InternetExplorerDriver(capability);
				D8.getWindowHandle();
				D8.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				D8.manage().window().maximize();
				Capabilities cap = ((RemoteWebDriver) D8).getCapabilities();
				browserver = cap.getVersion();

				break;
			case "FF":
				D8 = new FirefoxDriver();
				D8.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				D8.manage().window().maximize();
				break;

			default:
				Log.info( "Error : Invalid browser type");
				System.out.println("Error : Invalid browser type");
			}
		} catch (Exception e1) {
			Log.info( "Error :Check the Test Suite file contents"+e1.getMessage());
			System.out.println("Check the Test Suite file contents");
			System.out.println(e1);
		}
	}


	public void findExecTestscript(String TestSuite, String TestScript,	String ObjectRepository) throws Exception {
		try{
			FileInputStream fs = null;
			fs = new FileInputStream(new File(TestSuite));
			XSSFWorkbook TSworkbook = new XSSFWorkbook(fs);
			XSSFSheet TSsheet = TSworkbook.getSheetAt(0);
			boolean ValidTestSuit=false;
			try {
				fs = new FileInputStream(new File(TestSuite));
			} catch (Exception e) {
				System.out
				.println("No proper TestSuite is defined in Selenium Utility file. Give the correct TestSuite name");
				System.out.println(e);
				Log.info( "Error :No proper TestSuite is defined in Selenium Utility file. Give the correct TestSuite name"+e.getMessage());
				return;
			}

			cur_dt = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String strTimeStamp = dateFormat.format(cur_dt);
			String[] dateArray = strTimeStamp.split("-");
			today = Integer.parseInt(dateArray[2]);
			String rp = reportsPath;

			if (rp == "") {
				// if results path is passed as null, by
				// default 'C:\' drive is used

				rp = tempTestReportPath;
			}

			if (!rp.endsWith("\\")) { // checks whether the path ends with
				// '/'
				rp = rp + "\\";
			}

			strResultPath = rp + "Log";
			System.out.println();


			String htmlname1 = rp + "Log" + "Test_Suite_" + strTimeStamp
					+ ".html";
			File f = new File(strResultPath);
			if (strResultPath != f.getAbsolutePath().toString()) {
				System.out
				.println("Report will be printed in the following path since THE REPORT PATH WAS NOT GIVEN / THE GIVEN PATH IS INCORRECT.");
				Log.info( "WARNING :Report will be printed in the following path since THE REPORT PATH WAS NOT GIVEN / THE GIVEN PATH IS INCORRECT." );
				System.out.println(f.getAbsolutePath().toString());
			}
			f.mkdirs();
			bw1 = new BufferedWriter(new FileWriter(htmlname1));
			bw1.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw1.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
			bw1.write("<TR><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Testcase Name</B></FONT></TD><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Status</B></FONT></TD></TR>");


			Iterator<Row> iterator = TSsheet.iterator();


			String run="r";
			boolean runscript =false;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if(cell.getStringCellValue().equalsIgnoreCase(run)){
							runscript =true;
							scriptfound = 1;
						}


						if(runscript==true){
							cell =cellIterator.next();

							runscript=false;

							String ScriptName = cell.getStringCellValue();
							System.out.println(" ScriptName  "+ScriptName);

							if(!ScriptName.endsWith(".xlsx")){

								System.out
								.println("Invalid File Format of TestScript: "
										+ ScriptName
										+ "in Test Suite.Give only xlsx format");
								Log.info( "Invalid File Format of TestScript: "
										+ ScriptName
										+ "in Test Suite.Give only xlsx format" );
							}
							try{


								execKeywordScript(ScriptName, TestScript,
										ObjectRepository);
							}catch(Exception ex){
								System.out.println("Function can not called   "+ex.getMessage());
								Log.info( "Function can not called   "+ex.getMessage() );

								Log.info( "Invalid File: "
										+ ScriptName
										+ "is not available.Give the correct TestScript" );

								System.out
								.println("Invalid File:"
										+ ScriptName
										+ " is not available.Give the correct TestScript");
							}

							if (exeStatus.equalsIgnoreCase("Failed")) {
								/*strResultPath pointing towards Detail report*/	   
								// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
								bw1.write("<TR><TD COLSPAN=6 BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>"
										+ "<a href=" + DetailReport + ">"+ TCNm[0] +"</a>"
										+ "</B></FONT></TD><TD BGCOLOR=WHITE WIDTH=27%><FONT FACE=VERDANA COLOR=RED SIZE=2><B>"
										+ exeStatus + "</B></FONT></TD></TR>");
							} else {
								/*strResultPath pointing towards Detail report*/	   
								// 2016-03-28 Text color of a status(Pass) result of theã€€Summary report was corrected.
								// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
								bw1.write("<TR><TD COLSPAN=6 BGCOLOR=WHITE><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>"
										+ "<a href=" + DetailReport + ">"+ TCNm[0] +"</a>"
										+ "</B></FONT></TD><TD BGCOLOR=WHITE WIDTH=27%><FONT FACE=VERDANA COLOR=GREEN SIZE=2><B>"
										+ exeStatus + "</B></FONT></TD></TR>");
							}
						}

						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue());
						break;

					case Cell.CELL_TYPE_BLANK:        
						if(cell.getStringCellValue().equalsIgnoreCase(run)){
							runscript =true;
							scriptfound = 1;
						}
						break;
					case Cell.CELL_TYPE_FORMULA:        
						if(cell.getStringCellValue().equalsIgnoreCase(run)){
							runscript =true;
							scriptfound = 1;
						}
						break;
					}

				}

			}

			if (!(scriptfound == 1)) {
				System.out
				.println("Invalid File Format of Testsuite/ No Test Script to execute. Give the correct Testsuite/TestScript. ");
				Log.info( "Invalid File Format of Testsuite/ No Test Script to execute. Give the correct Testsuite/TestScript.  "  );
			}

			bw1.close();
			TSworkbook.close();
			fs.close();

		}catch(Exception e1){
			System.out.println("Error Occured in FindExecTestscript function "+e1.getMessage());
			Log.info( "Error Occured in FindExecTestscript function  "+e1.getMessage() );


			bw1.close();
		}
	}


	public void execKeywordScript(String scriptName, String TestScript,
			String ObjectRepository) throws Exception

	{

		FileInputStream fs2 = null;
		int cCellDatanum =0;
		// Report header
		cur_dt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (reportsPath == "") {

			reportsPath = tempTestReportPath;
		}

		if (!reportsPath.endsWith("\\"))

		{

			reportsPath = reportsPath + "\\";
		}
		TCNm = scriptName.split("\\.");
		strResultPath = reportsPath + "Log" + "\\" + TCNm[0] + "\\";
		String htmlname = reportsPath + "Log" + "\\" + TCNm[0] + "\\"
				+ strTimeStamp + ".html";

		reportsFolder = reportsPath + "Log" + "\\" + TCNm[0] + "\\";


		// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
		DetailReport = ".\\Log" + "\\" + TCNm[0] + "\\"
				+ strTimeStamp + ".html";

		screenReport = screenReport.substring(0, screenReport.length()-1);

		ScreenshotPath=screenReport+"\\"+TCNm[0];



		File f = new File(strResultPath);
		f.mkdirs();
		bw = new BufferedWriter(new FileWriter(htmlname));
		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");

		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%>"
				+ "<FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Test Case Name:</B></FONT></TD>"
				+ "<TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+"<a href=\"file:///"+ScreenshotPath+"\">"+ TCNm[0] + "</a>"+"</B></FONT></TD></TR>");




		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");


		bw.write("<TR COLS=7><TD BGCOLOR=#FFCC99 WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Row</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99  WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Keyword</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99 WIDTH=20% ><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Object</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99  WIDTH=23%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Action</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99 WIDTH=21%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Comments</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99 WIDTH=25%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD>"
				+ "<TD BGCOLOR=#FFCC99 WIDTH=5%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>");






		exeStatus = "Pass";
		String scriptPath = TestScript + scriptName;


		TScname = scriptName;

		FileInputStream fs1 = null;

		fs1 = new FileInputStream(new File(scriptPath));

		XSSFWorkbook TScworkbook = new XSSFWorkbook(fs1);
		TScsheet = TScworkbook.getSheetAt(0);

		FormulaEvaluator evaluator = TScworkbook.getCreationHelper().createFormulaEvaluator();



		TScrowcount = TScsheet.getLastRowNum();
		rowcnt = 0;

		for (j = 0; j < TScrowcount; j++)

		{
			rowcnt = rowcnt + 1;
			String TSvalidate = "r";

			if((TScsheet.getRow(j).getCell(0).getStringCellValue().equalsIgnoreCase(TSvalidate)==true))	{

				try{
					Action =TScsheet.getRow(j).getCell(1).getStringCellValue();


					if(TScsheet.getRow(j).getCell(2).getCellType()== XSSFCell.CELL_TYPE_STRING)   {
						cCellData = TScsheet.getRow(j).getCell(2).getStringCellValue();  
					}
					else     if(TScsheet.getRow(j).getCell(2).getCellType()== XSSFCell.CELL_TYPE_NUMERIC)  {                                                                                                                                  

						waitcCellDatanum =(int) TScsheet.getRow(j).getCell(2).getNumericCellValue();
						cCellData = Integer.toString(waitcCellDatanum);


					}
					else     if(TScsheet.getRow(j).getCell(2).getCellType()== XSSFCell.CELL_TYPE_FORMULA)  {                                                                                                                                  

						evaluator.evaluateFormulaCell(TScsheet.getRow(j).getCell(2));
						cCellData=TScsheet.getRow(j).getCell(2).getStringCellValue();  
					}
					else     if(TScsheet.getRow(j).getCell(2).getCellType()== XSSFCell.CELL_TYPE_BLANK)  {                                                                                                                                  
						cCellDatanum = (int) TScsheet.getRow(j).getCell(2).getNumericCellValue();  
					}


					if(TScsheet.getRow(j).getCell(3).getCellType()==XSSFCell.CELL_TYPE_BLANK){
						dCellData =TScsheet.getRow(j).getCell(3).getStringCellValue();
					}
					else 	 if(TScsheet.getRow(j).getCell(3).getCellType()==XSSFCell.CELL_TYPE_FORMULA){

						if(update.equalsIgnoreCase("yes")){
							evaluator.evaluateAll();
							dCellData = TScsheet.getRow(j).getCell(3).getStringCellValue();
						}
						else {
							dCellData = TScsheet.getRow(j).getCell(3).getStringCellValue();
						}

					}
					else if(TScsheet.getRow(j).getCell(3).getCellType()==XSSFCell.CELL_TYPE_STRING){
						dCellData = TScsheet.getRow(j).getCell(3).getStringCellValue();
					} 
					if(TScsheet.getRow(j).getCell(3).getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
						double  dCellData1 = TScsheet.getRow(j).getCell(3).getNumericCellValue();
						dCellData =String.valueOf(dCellData1);
					}



					if(TScsheet.getRow(j).getCell(5).getCellType() == XSSFCell.CELL_TYPE_BLANK){
						sComments ="";
						comments =sComments;
					}else if(TScsheet.getRow(j).getCell(5).getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
						nComments = TScsheet.getRow(j).getCell(5).getNumericCellValue();
						comments = Double.toString(nComments);
					}else if(TScsheet.getRow(j).getCell(5).getCellType()==XSSFCell.CELL_TYPE_STRING){
						sComments = TScsheet.getRow(j).getCell(5).getStringCellValue();
						comments = sComments;
					}
				}catch(Exception e){
					System.out.println("Error Occured, while reading TestScript File "+e.getMessage());
					Log.info( "Error Occured, while reading TestScript File in execKeywordScript  "+e.getMessage() );
				}
				String ORPath = ObjectRepository;


				try {
					fs2 = new FileInputStream(new File(ORPath));
					XSSFWorkbook ORworkbook = new  XSSFWorkbook(fs2);

					ORsheet = ORworkbook.getSheetAt(0);

					ORrowcount =ORsheet.getLastRowNum();


					ActionVal = Action.toLowerCase();
					iflag = 0;

				} catch (Exception e)
				{
					System.out.println("In catch Block of ExecKey . . ");
					Log.info( "Error Occured  in execKeywordScript  "+e.getMessage() );
					System.out.print(e.getMessage());
					fail("Object Repository format is not correct.Check the File format");

				}

				System.out.println((j+1)+" | "+Action+" | "+cCellData+" | "+dCellData+" | "+comments);

				bCellAction(scriptName);

			}

		}
		bw.close();
		fs1.close();
		fs2.close();



	}


	public static void screenShot(int loopn, int rown, String Sname)
			throws Exception {
		try {
			if (devFlag == 0) {
				screenshotflag = screenshotflag + 1;
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyyMMdd-HH-mm-ss");
				Date date = new Date();
				String filenamer = "";
				String strTime = dateFormat.format(date);
				Sname = Sname.substring(0, Sname.indexOf("."));
				ScreenFolder = Sname;

				ScreenshotPath = screenReport + Sname;


				screenReport = screenReport.toLowerCase();
				if (screenReport == "") {
					screenReport = reportsPath;
					initscreenFlag = 2;
				}

				if (!screenReport.endsWith("\\")) {
					screenReport = screenReport + "\\";

				}
				if (!(screenReport.contains("screen"))) {
					screenReport = screenReport + "Screenshot\\";

				}
				initscreen = initscreen + 1;
				if (initscreen == 2 && initscreenFlag == 2) {
					System.out
					.println("Screenshots will be captured in the following path since the SCREENSHOTS PATH IS NOT GIVEN.");
					Log.info( "Screenshots will be captured in the following path since the SCREENSHOTS PATH IS NOT GIVEN    "  );
					System.out.println(screenReport);


				}

				else {
					if (initscreen == 2) {
						File f1s = new File(screenReport);
						if (!screenReport.substring(0,
								screenReport.length() - 1).equalsIgnoreCase(

										f1s.getAbsolutePath().toString()))

						{
							System.out
							.println("Screenshots will be captured in the following path since the given SCREENSHOTS PATH IS NOT AVAILABLE.");
							Log.info( "Screenshots will be captured in the following path since the given SCREENSHOTS PATH IS NOT AVAILABLE   " +f1s.getAbsolutePath().toString() );
							System.out
							.println(f1s.getAbsolutePath().toString());

						}
						f1s.delete();
					}
				}
				if (loopflag == 0) {

					filenamer = screenReport + Sname + "\\" + Sname + "_"
							+ screenshotflag + "_rowno_" + (j + 1) + "_"
							+ strTime + ".png";

				} else {

					filenamer = screenReport + Sname + "\\" + Sname + "_"
							+ screenshotflag + "_loop_"
							+ (loopcnt[loopsize] + 1) + "_rowno_" + (j + 1)
							+ "_" + strTime + ".png";
				}



				if (ScreenshotTypeFlag == 0) {
					File screenshot = D8.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(screenshot, new File(filenamer));

				} else {

					BufferedImage image = new Robot()
							.createScreenCapture(new Rectangle(Toolkit
									.getDefaultToolkit().getScreenSize()));
					File outputfile = new File(filenamer);
					outputfile.mkdirs();
					ImageIO.write(image, "png", outputfile);
					Thread.sleep(3000);
				}
			}
			/*
			 * else { Reporter.ReportEvent("notForDevice1"); }
			 */
		}

		catch (Exception e) {
			System.out
			.println("Screenshot was not printed. Check the file path");
			Log.info( "Screenshot was not printed. Check the file path  "  );
			Reporter.ReportEvent("screenshot", e);
		}
	}

	private static void func_StoreCheck() throws Exception {
		try {
			String actval = null;
			String expval = null;
			Boolean boolval = null;
			String varname;
			String[] cCellDataValCh = cCellData.split(";");
			String objectType = cCellDataValCh[0];     
			String ObjectValCh = "";
			try {
				// Inserted on 16/12/2012. For holding Object Type
				ObjectValCh = cCellDataValCh[1];                      

				// Inserted on 16/12/2012. For Holding Object Name
			} catch (ArrayIndexOutOfBoundsException e) {
				ObjectValCh = "";
			}
			String[] dCellDataValCh = dCellData.split(":");

			String ObjectSetCh = dCellDataValCh[0];                             

			String ObjectSetValCh = "";
			int DTcolumncountCh = 0;
			if (objectType.equalsIgnoreCase("textelement")) {
				ObjectSetValCh = dCellData.replaceFirst("text:", "");
				System.out.println("ObjectSetValCh   is ><> > "+ObjectSetValCh);
			} else {

				if (dCellDataValCh.length == 2) {
					ObjectSetValCh = dCellDataValCh[1];                    
				}
				// 2016-03-23ã€€Multiple Selected Check Support
				else if (dCellDataValCh.length > 2) {
					ObjectSetValCh = dCellDataValCh[1];
					for(int i = 2; i < dCellDataValCh.length; i++) {
						ObjectSetValCh += ":" + dCellDataValCh[i];
					}
				}
			}
			if (dCellDataValCh.length == 2) {
				if (ObjectSetValCh.substring(0, 1).equalsIgnoreCase("#")) {
					System.out.println("In #");
					if (map.get(ObjectSetValCh.substring(1,
							(ObjectSetValCh.length()))) != null) {
						ObjectSetValCh = map.get(ObjectSetValCh.substring(1,
								(ObjectSetValCh.length())));

					} else {
						ObjectSetValCh = "";
					}
				}
			}
			if (objectType.equalsIgnoreCase("page")
					|| objectType.equalsIgnoreCase("dialog")) {

				objFoundFlag = 1;

			} else

			{


				for (int k = 0; k < ORrowcount; k++) {
					// String ORName = ORsheet.getCell(1, k).getContents();


					if(((ORsheet.getRow(k).getCell(1).getStringCellValue()).equalsIgnoreCase(ObjectValCh)==true))

						/*if (((ORsheet.getCell(1, k).getContents()).equalsIgnoreCase(ObjectValCh) == true))*/
						{
						String[] ORcellData = new String[3];

						ORcellData = ORsheet.getRow(k).getCell(4).getStringCellValue().split("=",2);
						ORvalname = ORcellData[0];
						ORvalue = ORcellData[1];
						k = ORrowcount + 1;
						objFoundFlag = 1;

						}

				}
			}

			if (objFoundFlag == 1) {

				objFoundFlag = 0;
				if (dCellDataValCh.length == 2) {
					if (ObjectSetValCh.contains("dt_")) {
						iflag = 0;
						String ObjectSetValtableheader[] = ObjectSetValCh
								.split("_");
						int column = 0;
						String Searchtext = ObjectSetValtableheader[1];
						try {
							DTcolumncountCh = DTsheet.getRow(0).getLastCellNum();

						} catch (NullPointerException e) {
							return;
						}

						for (column = 0; column < DTcolumncountCh; column++) {

							if(Searchtext.equalsIgnoreCase(DTsheet.getRow(0).getCell(column).getStringCellValue())==true)

							{
								ObjectSetValCh = DTsheet.getRow(dtrownum).getCell(column).getStringCellValue();


								iflag = 1;
								if (ObjectSetValCh.length() == 0) {
									ObjectSetValCh = "";
								}  
							}

						}
						if (iflag == 0) {
							ObjectSetValCh = "nodatarow";
						}
					}

					if (ObjectSetValCh.contains("dt_")) {
						String ObjectSetValtableheader[] = ObjectSetValCh
								.split("_");
						int column = 0;
						String Searchtext = ObjectSetValtableheader[1];
						DTcolumncountCh =DTsheet.getRow(0).getLastCellNum();


						for (column = 0; column < DTcolumncountCh; column++) {

							if(Searchtext.equalsIgnoreCase(DTsheet.getRow(0).getCell(column).getStringCellValue())==true)

							{

								ObjectSetValCh = DTsheet.getRow(dtrownum).getCell(column).getStringCellValue();


								iflag = 1;
								if (ObjectSetValCh.length() == 0) {
									ObjectSetValCh = "";
								}
							}

						}
						if (iflag == 0) {
							ORvalname = "exit";
						}
					}
				}
				switch (ObjectSetCh.toLowerCase()) {
				case "enabled":
					if (objectType.equalsIgnoreCase("textbox")
							|| objectType.equalsIgnoreCase("combobox")
							|| objectType.equalsIgnoreCase("listbox")
							|| objectType.equalsIgnoreCase("radiobutton")
							|| objectType.equalsIgnoreCase("button")
							|| objectType.equalsIgnoreCase("checkbox")
							|| objectType.equalsIgnoreCase("textarea")
							|| objectType.equalsIgnoreCase("image")
							|| objectType.equalsIgnoreCase("table")
							|| objectType.equalsIgnoreCase("link")
							|| objectType.equalsIgnoreCase("element")) {
						func_FindObj(ORvalname, ORvalue);
						boolval = elem.isEnabled();
						actval = boolval.toString();
					} else {
						Reporter.ReportEvent("property1");
					}
					break;
				case "text":
					// Specifications change for STH 
					if (objectType.equalsIgnoreCase("button")) {
						func_FindObj(ORvalname, ORvalue);
						if (elem.getTagName().equalsIgnoreCase("button")) {
							actval = elem.getText();
						} else if (elem.getTagName().equalsIgnoreCase("input")) {
							actval = elem.getAttribute("value");
						} else {
							Reporter.ReportEvent("property1");
						}
					} else if (objectType.equalsIgnoreCase("textbox")
							|| objectType.equalsIgnoreCase("textarea")) {

						func_FindObj(ORvalname, ORvalue);
						actval = elem.getAttribute("value");

					} else if (objectType.equalsIgnoreCase("textelement")
							|| objectType.equalsIgnoreCase("element")) {

						func_FindObj(ORvalname, ORvalue);
						actval = elem.getText();

					} else if (objectType.equalsIgnoreCase("combobox")
							|| objectType.equalsIgnoreCase("listbox")
							) {
						func_FindObj(ORvalname, ORvalue);
						// 2016-03-23 Multiple Selected Check Support
						List<WebElement> selectedList = new Select(elem).getAllSelectedOptions();
						actval = selectedList.get(0).getText();
						if(selectedList.size() > 1) {
							for(int i = 1; i < selectedList.size(); i++) {
								actval += ":" + selectedList.get(i).getText();
							}
						}
						/*
						actval = new Select(elem).getFirstSelectedOption()
								.getText().toString();
						 */
					} else {

						Reporter.ReportEvent("property1");
					}
					break;
				case "value":
					// Specifications change for STH
					if(objectType.equalsIgnoreCase("checkbox")
							|| objectType.equalsIgnoreCase("combobox")
							|| objectType.equalsIgnoreCase("radiobutton")) {
						func_FindObj(ORvalname, ORvalue);
						actval = elem.getAttribute("value");
					} else if(objectType.equalsIgnoreCase("listbox")) {
						func_FindObj(ORvalname, ORvalue);
						List<WebElement> selectedList = new Select(elem).getAllSelectedOptions();
						actval = selectedList.get(0).getAttribute("value");
						if(selectedList.size() > 1) {
							for (int i = 1; i < selectedList.size(); i++) {
								actval += ":" + selectedList.get(i).getAttribute("value");
							}
						}
					} else {
						Reporter.ReportEvent("property1");
					}
					break;
				case "visible":
					if (objectType.equalsIgnoreCase("textbox")
							|| objectType.equalsIgnoreCase("combobox")
							|| objectType.equalsIgnoreCase("listbox")
							|| objectType.equalsIgnoreCase("radiobutton")
							|| objectType.equalsIgnoreCase("button")
							|| objectType.equalsIgnoreCase("checkbox")
							|| objectType.equalsIgnoreCase("textarea")
							|| objectType.equalsIgnoreCase("image")
							|| objectType.equalsIgnoreCase("table")
							|| objectType.equalsIgnoreCase("link")
							|| objectType.equalsIgnoreCase("element")) {
						func_FindObj(ORvalname, ORvalue);
						boolval = elem.isDisplayed();
						actval = boolval.toString();
					} else {
						Reporter.ReportEvent("property1");
					}

					break;
				case "checked":
					if ((objectType.equalsIgnoreCase("radiobutton")
							|| objectType.equalsIgnoreCase("checkbox") || objectType
							.equalsIgnoreCase("element"))) {
						func_FindObj(ORvalname, ORvalue);
						boolval = elem.isSelected();
						actval = boolval.toString();

					} else {
						Reporter.ReportEvent("property1");
					}
					break;
				case "linktext":
					if (objectType.equalsIgnoreCase("link")) {
						func_FindObj(ORvalname, ORvalue);

						actval = elem.getText();
					} else {
						Reporter.ReportEvent("Property1");
					}
					break;
				case "pagetitle":
					if (ObjectValCh != "") {
						actval = ObjectValCh;
					} else {
						actval = D8.getTitle();
					}
					break;

				case "exist":
					boolval = false;
					actval = boolval.toString();

					if ((objectType.equalsIgnoreCase("page")) == true

							&& (D8.getTitle().toString()
									.equalsIgnoreCase(ObjectValCh)) == true) {

						actval = "true";
					} else {
						if (objectType.equalsIgnoreCase("page")) {

							String currentWindowHandle = D8.getWindowHandle();
							int windowFound = 0;
							WebDriver newWindow = null;
							Set<String> al = new HashSet<String>();
							al = D8.getWindowHandles();
							Iterator<String> windowIterator = al.iterator();

							if (D8.getTitle().toString()
									.equalsIgnoreCase(ObjectValCh) != true) {
								while (windowIterator.hasNext()) {
									String windowHandle = windowIterator.next();
									newWindow = D8.switchTo().window(
											windowHandle);
									if (newWindow.getTitle().toString()
											.equalsIgnoreCase(ObjectValCh) == true) {
										boolval = true;
										actval = boolval.toString();
										windowFound = 1;
										break;
									}

								}
								if (windowFound != 1) {
									boolval = false;

									actval = boolval.toString();
								}
								D8.switchTo().window(currentWindowHandle);
							}

						} else {

							if (objectType.equalsIgnoreCase("dialog") == true) {
								try {

									Alert dialogExist = null;
									dialogExist = D8.switchTo().alert();
									if (dialogExist.toString() != null) {
										boolval = true;
										actval = boolval.toString();
									} else {
										boolval = false;
										actval = boolval.toString();
									}

								} catch (NoAlertPresentException e) {

									boolval = false;
									actval = boolval.toString();

								}

							}
						}

					}

					break;
				case "rowcount":
					func_FindObj(ORvalname, ORvalue);
					List<WebElement> rows = elem.findElements(By.tagName("tr"));
					Integer rowCount = rows.size();
					if (rowCount == 0) {
						// WebElement rows1=elem.findElement(By.tagName("tr"));
						rowCount = 1;
					}
					actval = rowCount.toString();
					break;

				case "columncount":
					WebElement headerRow = null;
					func_FindObj(ORvalname, ORvalue);
					List<WebElement> rows1 = elem
							.findElements(By.tagName("tr"));
					try {
						headerRow = rows1.get(rows1.size()-2);
					} catch (Exception Ed) {
						try
						{
							headerRow = rows1.get(1);
						}
						catch(Exception Ed1)
						{
							headerRow = rows1.get(0);
						}
					}

					List<WebElement> columns = headerRow.findElements(By
							.tagName("th"));
					Integer colCount = columns.size();
					if (colCount == 0) {
						List<WebElement> columns0 = headerRow.findElements(By
								.tagName("td"));
						colCount = columns0.size();
						if (colCount == 0) {
							WebElement columns1 = headerRow.findElement(By
									.tagName("th"));
							if (columns1 != null) {
								colCount = 1;
							} else {
								WebElement columns2 = headerRow.findElement(By
										.tagName("td"));
								if (columns2 != null) {
									colCount = 1;
								}
							}
						}

					}
					actval = colCount.toString();
					break;
				case "getcelldata":

					func_FindObj(ORvalname, ORvalue);
					try {
						List<WebElement> cellRows = elem.findElements(By
								.tagName("tr"));
						getCellArray = dCellData.split(":");
						String cellData = "";
						int rowNumber = Integer.parseInt(getCellArray[2]);
						int colNumber = Integer.parseInt(getCellArray[3]);
						System.out.println("rownum - " + rowNumber
								+ "  col num - " + colNumber);
						WebElement reqrow = cellRows.get(rowNumber - 1);
						List<WebElement> reqcolmns = reqrow.findElements(By
								.tagName("td"));
						WebElement reqcellData = reqcolmns.get(colNumber - 1);
						cellData = reqcellData.getText();
						actval = cellData.toString();
						ObjectSetValCh = getCellArray[1];
					} catch (Exception e) {
						Reporter.ReportEvent("getcelldata");
					}
					break;

				default:
					actval = "Invalid syntax";
					Reporter.ReportEvent("property");
					break;
				}

				if ((ActionVal).equalsIgnoreCase("check")) {
					expval = ObjectSetValCh;
					// Specifications change for STH 
					if (objectType.equalsIgnoreCase("radiobutton")) {
						if (expval.equalsIgnoreCase("On")) {
							expval = "True";
						} else if (expval.equalsIgnoreCase("Off")) {
							expval = "False";
						}
					}

					if (ObjectSetCh.equalsIgnoreCase("checked") 
							|| ObjectSetCh.equalsIgnoreCase("visible")
							|| ObjectSetCh.equalsIgnoreCase("enabled")
							|| ObjectSetCh.equalsIgnoreCase("exist")) {
						if (expval.equalsIgnoreCase(actval)) {
							// Specifications change for STH 

							System.out
							.println("Actual value matches with expected value. "
									+ " Actual Value is " + actval 
									+ " and expected value is " + expval);

							if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {

								Reporter.ReportEvent("executed");
							} else {
								Reporter.ReportEvent("executed", ObjectSetCh,
										ObjectSetValCh);
							}
							if (capturecheckvalue == true) {
								screenShot(loopnum, TScrowcount, TScname);
							}	
						} else {

							System.out
							.println("Actual value doesn't match with expected value. Actual value is "
									+ actval
									+ " expected value is "
									+ expval);

							if (ORvalname == "exit") {
								Reporter.ReportEvent("missing", ObjectSetCh,
										ObjectSetValCh);

							} else {
								if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {
									Reporter.ReportEvent("failed");
								} else {
									Reporter.ReportEvent("failed", ObjectSetCh,
											ObjectSetValCh);
								}

							}
							if (capturecheckvalue == true) {
								screenShot(loopnum, TScrowcount, TScname);
							}
						}
					} else {
						if (expval.equals(actval)) {
							// Specifications change for STH 
							System.out
							.println("Actual value matches with expected value. "
									+ " Actual Value is " + actval 
									+ " and expected value is " + expval);
							if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {

								Reporter.ReportEvent("executed");
							} else {
								Reporter.ReportEvent("executed", ObjectSetCh,
										ObjectSetValCh);
							}
							if (capturecheckvalue == true) {
								screenShot(loopnum, TScrowcount, TScname);
							}	
						} else {
							System.out
							.println("Actual value doesn't match with expected value. Actual value is "
									+ actval
									+ " expected value is "
									+ expval);

							if (ORvalname == "exit") {
								Reporter.ReportEvent("missing", ObjectSetCh,
										ObjectSetValCh);

							} else {
								if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {
									Reporter.ReportEvent("failed");
								} else {
									Reporter.ReportEvent("failed", ObjectSetCh,
											ObjectSetValCh);
								}
							}
							if (capturecheckvalue == true) {
								screenShot(loopnum, TScrowcount, TScname);
							}
						}						
					}
				} else if ((ActionVal).equalsIgnoreCase("storevalue"))

				{
					varname = ObjectSetValCh;

					if (actval.equalsIgnoreCase("Invalid syntax")) {
						Reporter.ReportEvent("missing", ObjectSetCh, ObjectSetValCh);

					} else {
						if (map.containsKey(varname)) {
							map.remove(varname);
							map.put(varname, actval);

							if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {
								Reporter.ReportEvent("executed");
							} else {
								Reporter.ReportEvent("executed", ObjectSetCh,
										ObjectSetValCh);
							}
							System.out
							.println("Overwriting the value of the variable "
									+ varname
									+ " to store the value as mentioned in the test case row number"
									+ rowcnt);
						} else {
							map.put(varname, actval);
							if (ObjectSetCh.equalsIgnoreCase("getcelldata")) {
								Reporter.ReportEvent("executed");
							} else {
								Reporter.ReportEvent("executed", ObjectSetCh,
										ObjectSetValCh);
							}
							System.out
							.println("Overwriting the value of the variable "
									+ varname
									+ " to store the value as mentioned in the test case row number"
									+ rowcnt);
							if (ObjectSetValCh == "nodatarow") {
								Reporter.ReportEvent("missing");
							} else {

							}
						}
					}
					if (capturestorevalue == true) {
						screenShot(loopnum, TScrowcount, TScname);
					}
				}

			} else {
				Reporter.ReportEvent("objNotFound");
			}

		} catch (Exception e) {
			//	if (locatorError == 1) {

			//	} else {

			Reporter.ReportEvent("failed", e);
			//}
		}

	}


	@After
	public void close() throws Exception

	{
		try {
			System.out.println("Test end.");

			D8.quit();
		} catch (UnhandledAlertException e) {

			Log.info( "Because of specification of SeleniumWebDriver, downloading may be failed  "+ e.getMessage() );
			System.out.println(e);
			System.out
			.println("Because of specification of SeleniumWebDriver, downloading may be failed.");
			System.out
			.println("Please confirm the report file and screenshot about test result.");
			Log.info( "Please confirm the report file and screenshot about test result." );
		}
	}


	private static void func_FindObj(String strObjtype, String strObjpath)
			throws Exception

	{
		if (strObjpath.contains("@@@")) {


			//	String eCellDataVal = TScsheet.getCell(4, j).getContents();
			String eCellDataVal  = TScsheet.getRow(j).getCell(4).getStringCellValue();


			if (map.containsKey(eCellDataVal)) {
				eCellDataVal = map.get(eCellDataVal);
			}
			strObjpath = strObjpath.replace("@@@", eCellDataVal);

		}

		try {
			if (strObjtype.length() > 0 && strObjpath.length() > 0) {

				if (strObjtype.equalsIgnoreCase("id")) {
					elem = D8.findElement(By.id(strObjpath));
				} else if (strObjtype.equalsIgnoreCase("name")) {
					elem = D8.findElement(By.name(strObjpath));
				} else if (strObjtype.equalsIgnoreCase("xpath")) {
					elem = D8.findElement(By.xpath(strObjpath));
				} else if (strObjtype.equalsIgnoreCase("link")) {
					elem = D8.findElement(By.linkText(strObjpath.toString()));
				} else if (strObjtype.equalsIgnoreCase("css")) {
					elem = D8.findElement(By.cssSelector(strObjpath));
				}

			}
		} catch (Exception ex2) {
			Reporter.ReportEvent("objectmiss");
			Log.info( "Error Occured in func_FindObj: " +ex2.getMessage());
			System.out.println(ex2.toString());
			elem = null;
		}
	}


	private static WebElement  func_FindObj_return(String strObjtype, String strObjpath)

	{

		if (strObjpath.contains("@@@")) {

			//String eCellDataVal = TScsheet.getCell(4, j).getContents();
			String eCellDataVal  = TScsheet.getRow(j).getCell(4).getStringCellValue();
			if (map.containsKey(eCellDataVal)) {
				eCellDataVal = map.get(eCellDataVal);
			}
			strObjpath = strObjpath.replace("@@@", eCellDataVal);

		}

		try {
			if (strObjtype.length() > 0 && strObjpath.length() > 0) {


				if (strObjtype.equalsIgnoreCase("id")) {
					elem = D8.findElement(By.id(strObjpath));
				} else if (strObjtype.equalsIgnoreCase("name")) {
					elem = D8.findElement(By.name(strObjpath));
				} else if (strObjtype.equalsIgnoreCase("xpath")) {

					elem = D8.findElement(By.xpath(strObjpath));

				} else if (strObjtype.equalsIgnoreCase("link")) {
					elem = D8.findElement(By.linkText(strObjpath.toString()));
				} else if (strObjtype.equalsIgnoreCase("css")) {
					elem = D8.findElement(By.cssSelector(strObjpath));
				}

			}
		} catch (Exception ex2) {

			try {
				Reporter.ReportEvent("ObjectNotFound");
			} catch (IOException e) {
				System.out.println("  Object Not Found Error occured  "+e.getMessage());
				Log.info( "Object Not Found Error occured in func_FindObj: " +e.getMessage());

			}



			elem = null;
		}
		return elem;

	}


	private static boolean search_Excel(String toSearch){

		boolean isfound=false;
		String[] dCellDataValCh = dCellData.split(":");
		String ObjectSetCh = dCellDataValCh[0];
		String ObjectSetValCh = null;
		boolean columnFound = false;
		String compare = null;
		int columnno =0;
		String getColumn = null;
		if (dCellDataValCh.length == 2) {
			ObjectSetValCh = dCellDataValCh[1];
		}
		if (ObjectSetValCh.contains("dt_")) {
			iflag = 0;

			String ObjectSetValtableheader[] = ObjectSetValCh
					.split("_",2);
			int column = 0;

			String Searchincolumn = ObjectSetValtableheader[1];


			Row row = ws.getRow(0);

			int cell = row.getLastCellNum();
			for(int i=0;i<cell;i++){
				getColumn = (ws.getRow(0).getCell(i).getStringCellValue());
				if(getColumn.equalsIgnoreCase(Searchincolumn)){
					columnFound = true;
					break;
				}
			}                                                            

			if((columnFound == true)&&(columnno!=0)){
				int rowcnt = 0;
				for(Row r : ws){
					rowcnt= ws.getLastRowNum();

				}

				System.out.println("Row Count is  "+rowcnt);

				for(int i=0;i<rowcnt;i++){
					String getCelldata = ws.getRow(i).getCell(columnno).getStringCellValue();
					if(getCelldata.equalsIgnoreCase(toSearch)){
						System.out.println("Script Found  "+getCelldata);
						isfound = true;
					}
				}


			}



		}
		return isfound;


	}


	public static int ifContidionSkipper(String strifConditionStatus)
			throws Exception {
		try {
			String strKeyword;
			int intIfEndConditionCount, intIfConditionCount;
			// String strKeyWord;
			intIfConditionCount = 1;
			intIfEndConditionCount = 0;

			if (strifConditionStatus.equalsIgnoreCase("false")) {
				// intLogicStartRow = j;

				do {
					j = j + 1;
					strKeyword =TScsheet.getRow(j).getCell(1).getStringCellValue();



					if (strKeyword.equalsIgnoreCase("Condition")) {
						intIfConditionCount = intIfConditionCount + 1;
					}

					if (strKeyword.equalsIgnoreCase("Endcondition")) {
						intIfEndConditionCount = intIfEndConditionCount + 1;

						if (intIfConditionCount == intIfEndConditionCount) {
							j = j + 1;
							break;
						}
					}

				} while (true);
			}
		} catch (Exception e) {
			System.out.println(e);
			Log.info( "Error Occured  in  ifContidionSkipper: " +e.getMessage());

		}
		return j;
	}


	public String func_IfCondition(String strConditionArgs) throws Exception {

		int iFlag = 1;
		String str[] = strConditionArgs.split(";");
		String value1 = str[0];
		String value2 = str[2];
		String strOperation = str[1];
		strOperation = strOperation.toLowerCase().trim();
		switch (strOperation.toLowerCase()) {
		case "equals":
			if (value1.substring(0, 1).equalsIgnoreCase("#")) {

				value1 = map.get(value1.substring(1, (value1.length())));
				System.out
				.println("Variable used in condition statement has value: "
						+ value1);


				if (value1.trim().equalsIgnoreCase(value2.trim())) {

					iFlag = 0;
				}
			} else if (value1.trim().equalsIgnoreCase(value2.trim())) {
				iFlag = 0;
			}
			break;

		case "notequals":
			if (value1.substring(0, 1).equalsIgnoreCase("#")) {
				value1 = map.get(value1.substring(1, (value1.length())));
				System.out
				.println("Variable used in condition statement has values: "
						+ value1);
				if (!value1.trim().equalsIgnoreCase(value2.trim())) {

					iFlag = 0;
				}

			} else if (!value1.trim().equals(value2.trim())) {
				iFlag = 0;
			}
			break;
		case "greaterthan":
			if (value1.substring(0, 1).equalsIgnoreCase("#")) {
				value1 = map.get(value1.substring(1, (value1.length())));
				if (isInteger(value1) && isInteger(value2)) {
					if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
						iFlag = 0;
					}
				}
			}

			else if (isInteger(value1) && isInteger(value2)) {
				if (Integer.parseInt(value1) > Integer.parseInt(value2)) {
					iFlag = 0;
				}
			}

			else {
				System.out.println("Give Only Integers for Compare ");
			}
			break;
		case "lessthan":
			if (value1.substring(0, 1).equalsIgnoreCase("#")) {
				value1 = map.get(value1.substring(1, (value1.length())));
				if (isInteger(value1) && isInteger(value2)) {
					if (Integer.parseInt(value1) < Integer.parseInt(value2)) {
						iFlag = 0;
					}
				}
			}

			else if (isInteger(value1) && isInteger(value2)) {
				if (Integer.parseInt(value1) < Integer.parseInt(value2)) {
					iFlag = 0;
				}
			}

			else {
				Log.info( "Error Occured in lessthan : Give Only Integers for Compare" );
				System.out.println("Give Only Integers for Compare ");
			}
			break;
		default:
			Reporter.ReportEvent("missing");
			break;
		}
		if (iFlag == 0) {

			return "true";

		} else {
			return "false";
		}

	}


	public void arrayreSize() {
		if (loopstart.length <= loopsize) {
			loopstart = Arrays.copyOf(loopstart, loopstart.length + 1);
			loopend = Arrays.copyOf(loopend, loopend.length + 1);
			loopcnt = Arrays.copyOf(loopcnt, loopcnt.length + 1);
			dtrownumloop = Arrays.copyOf(dtrownumloop, dtrownumloop.length + 1);
			loopcount = Arrays.copyOf(loopcount, loopcount.length + 1);
		}
	}


	public void bCellAction(String scriptName) throws Exception {
		try {

			switch (ActionVal.toLowerCase()) {
			case "loop":
				startrow = j;
				dtrownum = 1;
				loopsize = loopsize + 1;
				if (loopsize >= 1) {
					arrayreSize();

				}
				loopflag = 1;
				loopcount[loopsize] = Integer.parseInt(cCellData);
				loopstart[loopsize] = j;
				loopcnt[loopsize] = 0;
				dtrownumloop[loopsize] = dtrownum;
				Reporter.ReportEvent("loop : " + "Start of loop : " + (loopsize + 1));
				Reporter.ReportEvent("executed");
				break;
			case "endloop":
				loopcnt[loopsize] = loopcnt[loopsize] + 1;
				loopnum = loopnum + 1;
				if (loopcnt[loopsize] == loopcount[loopsize]) {
					Reporter.ReportEvent("loop" + " End of Loop : " + (loopsize + 1)
							+ " : Loop count : " + loopcnt[loopsize]);
					loopsize = loopsize - 1;
					if (loopsize >= 0)
						dtrownum = dtrownumloop[loopsize];
					else {
						dtrownum = 1;
						loopflag = 0;
					}
					Reporter.ReportEvent("executed");
				} else {
					j = loopstart[loopsize];
					dtrownum = dtrownum + 1;
					dtrownumloop[loopsize] = dtrownum;
					Reporter.ReportEvent("loop" + " End of Loop : " + (loopsize + 1)
							+ " : Loop count : " + loopcnt[loopsize]);
				}
				rowcnt = 1;
				break;
				// 2016-03-23 close Keyword Support
			case "launchapp":
				if(D8 == null) {
					setBrowser(browserName);
				}
				D8.get(cCellData);
				Reporter.ReportEvent("executed");
				break;
				// 2016-03-23 close Keyword Support
			case "close":
				D8.close();
				D8 = null;
				Reporter.ReportEvent("executed");
				break;

			case "wait":

				Thread.sleep((waitcCellDatanum));
				Reporter.ReportEvent("executed");
				break;

			case "condition":



				String strConditionStatus = func_IfCondition(cCellData);
				if (strConditionStatus.equalsIgnoreCase("false")) {


					conditionline = j;
					j = ifContidionSkipper(strConditionStatus);
					j = j - 1;
				}
				if (strConditionStatus.equalsIgnoreCase("false")) {


					Reporter.ReportEvent("condFailed");
				} else {


					Reporter.ReportEvent("executed");
				}
				break;

			case "endcondition":

				Reporter.ReportEvent("executed");
				break;

			case "screencaptureoption":
				String[] sco = cCellData.split(";");

				for (int s = 0; s < sco.length; s++) {
					if (sco[s].equalsIgnoreCase("perform")) {
						captureperform = true;
					}
					if (sco[s].equalsIgnoreCase("storevalue")) {
						capturestorevalue = true;
					}
					if (sco[s].equalsIgnoreCase("check")) {
						capturecheckvalue = true;
					}

				}
				Reporter.ReportEvent("executed");
				break;

			case "importdata":
				try {
					String xcelpath = cCellData;
					FileInputStream fs3 = null;				 
					fs3 = new FileInputStream(new File(xcelpath));			 

					XSSFWorkbook DTworkbook = new XSSFWorkbook(fs3);			
					FormulaEvaluator DTevaluator = DTworkbook.getCreationHelper().createFormulaEvaluator();

					if(update.equalsIgnoreCase("yes")){
						DTevaluator.evaluateAll();
					}
					DTsheet = DTworkbook.getSheetAt(0);				 
					Reporter.ReportEvent("executed");
				} catch (Exception e) {
					Log.info( "Error Occured in importdata : No Data table found  " +e.getMessage() );
					Reporter.ReportEvent("nodatatable");
				}
				break;


			case "fetchdb":

				try{

					boolean parameter = false;
					sqlquery = cCellData;

					if((database.equalsIgnoreCase("mssql"))||(database.equalsIgnoreCase("mysql"))){
						isinvaliddb = true;
					}else{
						isinvaliddb=false;
						Reporter.ReportEvent("invalidConnection");
						return;
					}

					if(host_name.length()==0){
						System.out.println("Please Enter Host Name");
						isinvaliddb=false;
						Reporter.ReportEvent("invalidConnection");
						return;
					}

					if((schemaname.length()==0)){
						System.out.println("Please Enter Schema Name");
						isinvaliddb=false;
						Reporter.ReportEvent("invalidConnection");
						return;
					}

					String substr_query = (sqlquery.trim()).substring(0,6);
					System.out.println("Query is  "+sqlquery);

					if(sqlquery.length()>0){
						String query = sqlquery.trim();
						String isSelect = query.substring(0, 6);


						if((! isSelect.equalsIgnoreCase("select"))){

							parameter=true;

						}

					}else{
						System.out.println("SQL query is not given, Please enter SQL query ");
						Reporter.ReportEvent("invalidQuery");
						return;
					}
					if(parameter == true){
						Reporter.ReportEvent("invalidQuery");
						return;
					}

					String url =createConnection(database, schemaname, username, password);
					if(url.length()>0){
						isconnected = getConnection(url);		
					}
					if(isconnected){

						executeQuery(rs);
						Reporter.ReportEvent("executed");

					}else{
						Reporter.ReportEvent("invalidConnection");

						isinvaliddb = false;
					}

				}catch (Exception e){

					System.out.println(e.getMessage());

				}

				break;


			case "comparedbcell":
				try{

					if(isinvaliddb){
						ScreenshotTypeFlag = 0;
						WebElement getElement = getWebElement();
						if(getElement==null){
							System.out.println(" No matching Element found in Object Repository");
							Reporter.ReportEvent("ObjectNotFound");
							return;

						}
						String toSearch = getElement.getText();

						boolean isfound = search_Excel(toSearch);
						if(isfound){	
							Reporter.ReportEvent("executed");
						}else{
							Reporter.ReportEvent("NoMatchinDataTable");
						}
					}else{
						System.out.println("Basic parameters are invalid");
						Reporter.ReportEvent("invalidConnection");

					}
				}
				catch(Exception e){
					Log.info( "Exception Occured in comparedbcell : No Data table found  " +e.getMessage() );
					System.out.println("Exception Occured as -"+e.getMessage());
					//Reporter.ReportEvent("NoMatchinDataTable");
				}
				break;

			case "screencapture":
				ScreenshotTypeFlag = 0;
				screenShot(loopnum, TScrowcount, TScname);
				Reporter.ReportEvent("executed");
				break;
			case "context":
				cCellObjName = "";
				cCellObjType = "";
				dCellDataVal = null;
				int frameindex = 0;
				if (cCellData.contains(";")) // Dialog Support
				{

					if (cCellData.endsWith(";")) {
						cCellObjType = cCellData.substring(0,
								cCellData.length() - 1);
						cCellObjName = cCellData.substring(0,
								cCellData.length() - 1);

					} else {
						cCellDataVal = cCellData.split(";");
						cCellObjType = cCellDataVal[0];
						cCellObjName = cCellDataVal[1];
					}
				}

				else {
					cCellObjType = cCellData;
					if (cCellObjType.equalsIgnoreCase("frame")
							|| cCellObjType.equalsIgnoreCase("iframe")) {
						frameindex = 1;
					} else {
						cCellObjName = cCellData;
					}
				}
				if (cCellObjType.equalsIgnoreCase("frame")
						|| cCellObjType.equalsIgnoreCase("iframe")) {
					if (frameindex == 1) {
						D8.switchTo().parentFrame();
						Reporter.ReportEvent("executed");
						frameindex = 0;
						// 2015-06-17 add <---
					} else if("default".equals(cCellObjName)) {
						D8.switchTo().defaultContent();	
						Reporter.ReportEvent("executed");
					} else if(cCellObjName.matches("^[0-9]+") == true){
						int test = new Integer(cCellObjName);
						D8.switchTo().frame(test);
						Reporter.ReportEvent("executed");
						// 2015-06-17 add --->
					} else {
						for (int k = 0; k < ORrowcount; k++) {
							if((ORsheet.getRow(k).getCell(1).getStringCellValue()).equalsIgnoreCase(cCellObjName)==true)

							{
								String[] ORcellData = new String[3];

								ORcellData = ORsheet.getRow(k).getCell(4).getStringCellValue().split("=");



								ORvalname = ORcellData[0];

								ORvalue = ORsheet.getRow(k).getCell(4).getStringCellValue().substring(ORcellData[0].length()+1);



								k = ORrowcount + 1;
							}

						}
						func_FindObj(ORvalname, ORvalue);
						if (elem == null) {
							return;
						} else {
							D8.switchTo().frame(elem);
							Reporter.ReportEvent("executed");
						}
					}
				} else {

					String windowType = null;
					try {
						dCellData.toString();
						if (dCellData.contains("::")) {
							String tempDCellData = dCellData;
							dCellDataVal = dCellData.split(";");
							windowType = dCellDataVal[0];
							ObjectSetVal = dCellDataVal[1];
							dCellData = dCellData.substring(
									dCellData.indexOf("::") + 2,
									dCellData.length());
							if (dCellData.contains(";")) 
							{
								if (dCellData.endsWith(";")) {
									cCellObjType = dCellData.substring(0,
											dCellData.length() - 1);
									cCellObjName = dCellData.substring(0,
											dCellData.length() - 1);

								} else {
									dCellDataVal = dCellData.split(";");
									cCellObjType = dCellDataVal[0];
									cCellObjName = dCellDataVal[1];

								}
							}

							else {
								cCellObjType = dCellData;

								if (cCellObjType.equalsIgnoreCase("frame")
										|| cCellObjType
										.equalsIgnoreCase("iframe")) {
									frameindex = 1;
								} else {
									cCellObjName = dCellData;
								}
							}
							if (cCellObjType.equalsIgnoreCase("frame")
									|| cCellObjType.equalsIgnoreCase("iframe")) {
								if (frameindex == 1) {
									D8.switchTo().parentFrame();
									dCellData = tempDCellData;
									Reporter.ReportEvent("executed");
									frameindex = 0;

								} else {
									for (int k = 0; k < ORrowcount; k++) {

										if(((ORsheet.getRow(k).getCell(1).getStringCellValue())						
												.equalsIgnoreCase(cCellObjName) == true)) {
											String[] ORcellData = new String[3];

											ORcellData =((ORsheet.getRow(k).getCell(4).getStringCellValue()).split("="));



											ORvalname = ORcellData[0];

											ORvalue = ORsheet.getRow(k).getCell(4).getStringCellValue().substring(ORcellData[0].length()+1);


											k = ORrowcount + 1;

										}

									}
									func_FindObj(ORvalname, ORvalue);
									if (elem == null) {
										return;
									} else {
										D8.switchTo().frame(elem);
										dCellData = tempDCellData;

										Reporter.ReportEvent("executed");

									}
								}
							}
						} else if (dCellData.contains(";")) {
							if (dCellData.endsWith(";")) {

								windowType = dCellData.toString();
								ObjectSetVal = dCellData.toString();

							} else {
								dCellDataVal = dCellData.split(";");
								windowType = dCellDataVal[0];
								ObjectSetVal = dCellDataVal[1]; // 2015-06-17
								if (ObjectSetVal.substring(0, 1)
										.equalsIgnoreCase("#")) {
									if (map.get(ObjectSetVal.substring(1,
											(ObjectSetVal.length()))) != null) {
										ObjectSetVal = map
												.get(ObjectSetVal
														.substring(
																1,
																(ObjectSetVal
																		.length())));

									} else {
										ObjectSetVal = "";
									}
									// data table check Add 2015-08-19 --->
								} else if (ObjectSetVal.contains("dt_")) {
									String ObjectSetValtableheader[] = ObjectSetVal.split("_");

									Row r1 = DTsheet.getRow(0);
									int DTcolumncountCh = r1.getLastCellNum();


									int column = 0;
									String Searchtext = ObjectSetValtableheader[1];

									for (column = 0; column < DTcolumncountCh; column++) {
										if(Searchtext.equalsIgnoreCase(DTsheet.getRow(0).getCell(column).getStringCellValue())==true)


										{
											ObjectSetVal = DTsheet.getRow(dtrownum).getCell(column).getStringCellValue();



											if (ObjectSetVal.length() == 0) {
												ObjectSetVal = "";
											}
											iflag = 1;
										}
									}	
								}
								// data table check Add 2015-08-19 <---
							}
						} else {
							windowType = dCellData.toString();
							ObjectSetVal = dCellData.toString();
						}
						// 2015-06-23  <--- condition changedEEotext|dialog;EE
						if ((!windowType.equalsIgnoreCase("dialog;")) && ((windowType.equalsIgnoreCase("page")
								|| windowType.equalsIgnoreCase("page;")) && !dCellData.contains("::")
								|| !windowType.equalsIgnoreCase("page;WindowRtn;"))) {
							// 2015-06-23 --->
							int windowNums = 0;
							int windowItr = 0;
							WebDriver newWindow = null;
							Set<String> al = new HashSet<String>();
							al = D8.getWindowHandles();
							windowNums = al.size(); // get the number of window
							// handles
							Iterator<String> windowIterator = al.iterator();
							if (windowNums == 1) { 				 
								// Switch the hundle, if number of available hundle is 1.
								String handle = windowIterator.next();
								D8.switchTo().window(handle);
								// Reset Iterator
								windowIterator = al.iterator();
							} else {
								// save the current window handle.
								parentWindowHandle = D8.getWindowHandle();
							}
							if (D8.getTitle().toString()

									.equalsIgnoreCase(ObjectSetVal) == true) {
								Reporter.ReportEvent("executed"); // 2015-08-19 Add
							} else {
								if (!((ObjectSetVal.equalsIgnoreCase("page") || (ObjectSetVal
										.equalsIgnoreCase("page;"))) || (ObjectSetVal
												.toString() == ""))) {
									if (D8.getTitle().toString()
											.equalsIgnoreCase(ObjectSetVal) == false) {
										for (windowItr = 0; windowItr < windowNums; windowItr++) {

											String windowHandle = windowIterator
													.next();
											newWindow = D8.switchTo().window(
													windowHandle);

											if (newWindow
													.getTitle()
													.toString()
													.equalsIgnoreCase(
															ObjectSetVal)) {

												windowFound = 1;
												Reporter.ReportEvent("executed",
														windowType,
														ObjectSetVal);
												break;

											}

										}
										if (windowFound != 1) {

											Reporter.ReportEvent("NoWindowFound",
													windowType, ObjectSetVal);
										}

									}
								} else {
									if (windowNums == 1) {
										Reporter.ReportEvent("executed");
									} else {
										int winItr1 = 0;
										String windowHandle = null;

										while (winItr1 != windowNums) {
											windowHandle = windowIterator
													.next();
											System.out.println(windowHandle);
											newWindow = D8.switchTo().window(
													windowHandle);
											if (parentWindowHandle
													.equalsIgnoreCase(windowHandle)) {

												if (winItr1 != windowNums - 1) {
													windowHandle = windowIterator
															.next();
													D8.switchTo().window(
															windowHandle);
													Reporter.ReportEvent("executed");
													windowFound = 1;
													break;
												} else

												{
													Iterator<String> windowIterator1 = al
															.iterator();
													windowHandle = windowIterator1
															.next();
													D8.switchTo().window(
															windowHandle);
													Reporter.ReportEvent("executed");
													windowFound = 1;
													break;

												}
											}

											winItr1++;
										}
										if (windowFound != 1) {
											Reporter.ReportEvent("NoWindowFound");
										}

									}
								}

							}
							// 2015-06-18 <---
						} else if(windowType.equalsIgnoreCase("page;WindowRtn;")) {
							D8.switchTo().window(parentWindowHandle);
							Reporter.ReportEvent("executed");
							// 2015-06-18 --->
						}

						if ((windowType.equalsIgnoreCase("dialog") || windowType
								.equalsIgnoreCase("dialog;"))) {
							D8.switchTo().alert();
							Reporter.ReportEvent("executed");

						}

					} catch (Exception e) {

						Reporter.ReportEvent("failed", e);

					}

				}
				break;
			case "check":
				ScreenshotTypeFlag = 0;
				func_StoreCheck();
				break;
			case "storevalue":
				ScreenshotTypeFlag = 0;
				func_StoreCheck();
				break;
			case "upload":

				if (cCellData.toString() == "") {

					Reporter.ReportEvent("filePathNotFound2");
					doUploadDownload("abortupload");
				} else {

					if (new File(cCellData.toString()).exists()) {

						// System.out.println(cCellData.toString());
						doUploadDownload("upload");
					} else {

						Reporter.ReportEvent("filePathNotFound");
						doUploadDownload("abortupload");
					}
				}
				break;
			case "download":

				cCellObjName = "";
				cCellObjType = "";
				dCellDataVal = null;

				if (cCellData.contains(";")) 
				{
					if (cCellData.endsWith(";")) {
						cCellObjType = cCellData;
						cCellObjName = cCellData;
					} else {
						cCellDataVal = cCellData.split(";");
						cCellObjType = cCellDataVal[0];
						cCellObjName = cCellDataVal[1];

					}
				}

				else {
					cCellObjType = cCellData;
					cCellObjName = cCellData;
				}
				ObjectSet = dCellData.toString();



				if (ObjectSetVal.contains("dt_"))

					DTcolumncount = DTsheet.getRow(0).getLastCellNum();
				//DTcolumncount = DTsheet.getColumns();
				if (!(cCellObjType.equalsIgnoreCase("page")
						|| cCellObjType.equalsIgnoreCase("page;")
						|| cCellObjType.equalsIgnoreCase("dialog")
						|| cCellObjType.equalsIgnoreCase("dialog;")
						|| cCellObjType.equalsIgnoreCase("DownloadDialog") || cCellObjType
						.equalsIgnoreCase("uploadDialog"))) 
				{
					for (int k = 0; k < ORrowcount; k++) {


						if(((ORsheet.getRow(k).getCell(1).getStringCellValue()).equalsIgnoreCase(cCellObjName)==true))



						{
							String[] ORcellData = new String[3];

							ORcellData = ORsheet.getRow(k).getCell(4).getStringCellValue().split("=");


							ORvalname = ORcellData[0];
							ORvalue = ORsheet.getRow(k).getCell(4).getStringCellValue().substring(ORcellData[0].length() + 1);



							k = ORrowcount + 1;
						}
					}

				}
				try {
					// String[] cCellDataValCh = cCellData.split(";");
					// String objectType = cCellDataValCh[0];
					readAttributeforPerform();
					func_FindObj(ORvalname, ORvalue);
					if (elem == null) {
						return;
					} else {
						if (ObjectSet == "") {

							Reporter.ReportEvent("filePathNotFound2");
						} else {
							try {

								// System.out.println(ObjectSet);

								ObjectSet1 = ObjectSet.split("\\\\");
							} catch (Exception e2) {

								System.out.println(e2);
							}
							for (int i = 0; i < ObjectSet1.length - 1; i++) {
								ObjectSet2 = ObjectSet2 + ObjectSet1[i] + "\\";

							}
							if (new File(ObjectSet2.toString()).exists()) {

								doUploadDownload("download");

							} else {

								Reporter.ReportEvent("filePathNotFound1");

							}
							ObjectSet2 = "";
							ObjectSet1 = null;
						}

					}

				} catch (Exception e) {
					Log.info( "Error Occured in Download :   " +e.getMessage() );
					System.out.println(e);
				}
				break;

			case "perform":

				ScreenshotTypeFlag = 0;

				cCellObjName = "";
				cCellObjType = "";
				dCellDataVal = null;

				if (cCellData.contains(";")) 
				{
					if (cCellData.endsWith(";")) {
						cCellObjType = cCellData;
						cCellObjName = cCellData;
					} else {
						cCellDataVal = cCellData.split(";");
						cCellObjType = cCellDataVal[0];
						cCellObjName = cCellDataVal[1];
					}
				}

				else {
					cCellObjType = cCellData;
					cCellObjName = cCellData;
				}

				dCellData.toString();

				if (dCellData.contains(":")) {
					if (!dCellData.endsWith(":")) {
						dCellDataVal = dCellData.split(":");
						ObjectSet = dCellDataVal[0].toLowerCase();
						ObjectSetVal = dCellDataVal[1];

						if (dCellDataVal.length > 2) {
							for (int dCellvalcount = 2; dCellvalcount < dCellDataVal.length; dCellvalcount++) {
								if (ObjectSetVal.startsWith("dt_")
										|| ObjectSetVal.startsWith("#"))

								{
									Reporter.ReportEvent("tooManyArguments");
									break;
								} else {
									ObjectSetVal = ObjectSetVal + ":"
											+ dCellDataVal[dCellvalcount];
								}
							}
						}
					} else {
						ObjectSet = dCellData.replace(":", "");
						ObjectSetVal = "";

					}
				} else {
					ObjectSet = dCellData.toString();
				}
				DTcolumncount = 0;
				if (ObjectSetVal.contains("dt_"))
					DTcolumncount = DTsheet.getRow(0).getLastCellNum();
				//DTcolumncount = DTsheet.getColumns();
				if (!(cCellObjType.equalsIgnoreCase("page")
						|| cCellObjType.equalsIgnoreCase("page;")
						|| cCellObjType.equalsIgnoreCase("dialog")
						|| cCellObjType.equalsIgnoreCase("dialog;")
						|| cCellObjType.equalsIgnoreCase("DownloadDialog") || cCellObjType
						.equalsIgnoreCase("uploadDialog"))) 				
				{
					for (int k = 0; k < ORrowcount; k++) {


						if(((ORsheet.getRow(k).getCell(1).getStringCellValue()).equalsIgnoreCase(cCellObjName)==true))



						{
							String[] ORcellData = new String[3];

							ORcellData = ORsheet.getRow(k).getCell(4).getStringCellValue().split("=",2);



							ORvalname = ORcellData[0];

							ORvalue =ORsheet.getRow(k).getCell(4).getStringCellValue().substring(ORcellData[0].length() + 1);


							k = ORrowcount + 1;
						}
					}

				}

				dCellAction();
				break;
			case "callfunction":

				try {
					func_InvokeFunction(cCellData, dCellData);
				} catch (Exception e) {
					Reporter.ReportEvent("userdefined");
				}
				break;
			case "callaction":

				reporttype = 1;
				exeStatus = "Pass";
				String ComponentPath = reusableComponents + cCellData;
				if (cCellData.contains("xlsx")) {
					String ComponentName = cCellData.split(".xlsx")[0];

					XSSFSheet  TestScriptSheet = TScsheet;

					FileInputStream ComponentFile1 = null;
					//	WorkbookSettings ComponentWS1 = null;

					try {
						ComponentFile1 = new FileInputStream(new File(
								ComponentPath));

						XSSFWorkbook  ComponentWorkBook = new XSSFWorkbook(ComponentFile1);



						XSSFSheet ComponentSheet = ComponentWorkBook.getSheetAt(0);
						int ComponentRowCount = 0;
						int introwcnt = 0;
						int introwcntStore = j;
						Reporter.ReportEvent("executed");
						j = j + 1;
						TScsheet = ComponentSheet;
						Stack<String> ComponentStack = new Stack<String>();
						globalCompName = ComponentName;
						ComponentStack.push(ComponentName);
						Reporter.ReportEvent("callactionstart");





						ComponentRowCount = ComponentSheet.getLastRowNum();


						introwcnt = 0;
						for (int jloop = 0; jloop < ComponentRowCount; jloop++) {
							introwcnt = introwcnt + 1;
							j = jloop;
							String CTValidate = "r";

							if(((ComponentSheet.getRow(jloop).getCell(0).getStringCellValue()).equalsIgnoreCase(CTValidate)==true))

							{

								// 2016-03-23 Get Cell Value Support(xlsx)
								Action = getCellData(ComponentWorkBook, ComponentSheet.getRow(jloop).getCell(1));
								cCellData = getCellData(ComponentWorkBook, ComponentSheet.getRow(jloop).getCell(2));
								dCellData=	getCellData(ComponentWorkBook, ComponentSheet.getRow(jloop).getCell(3));

								String ORPath = objectRepository;
								FileInputStream ComponentFile2 = null;

								try {
									ComponentFile2 = new FileInputStream(
											new File(ORPath));

								} catch (Exception e) {
									System.out.println("File not found");
								}
								try {

									XSSFWorkbook ORworkbook = new XSSFWorkbook(ComponentFile2);
									ORsheet = ORworkbook.getSheetAt(0);
									ORrowcount = ORsheet.getLastRowNum();
									ActionVal = Action.toLowerCase();
									iflag = 0;
								} catch (Exception e) {

									fail("Excel file of Ignite is not correct.");
								}
								System.out.println(Action + "||" + cCellData
										+ "||" + dCellData);
								bCellAction(scriptName);

								jloop = j;
							}// End of Execution
						}// End of If that get all rows in Test Script
						globalCompName = ComponentStack.pop();
						Reporter.ReportEvent("callactionend");
						j = introwcntStore;
						reporttype = 0;
						TScsheet = TestScriptSheet;
					} catch (FileNotFoundException FNF1) {
						Reporter.ReportEvent("callactionfnf");
					}
				} else {
					Reporter.ReportEvent("fail");
					Reporter.ReportEvent("callactionff");
				}
				break;
			default:
				Reporter.ReportEvent("keyword");
				break;

			}
		}

		catch (Exception ex) {
			Reporter.ReportEvent("failed", ex);
			Log.info( "failed  " +ex.getMessage() );
			//System.out.println(ex);
			Log.info( "------Error Information : Ignite------- "  );
			Log.info( "Current Script:" + scriptName );
			Log.info( "Current ScriptPath:" + testScript );
			Log.info( "Using ObjectRepositoryPath:" + objectRepository );
			Log.info( "Current Keyword:" + Action );
			Log.info( "Current ObjectDetails:" + cCellData );
			Log.info( "Current ObjectDetailsPath:" + ORvalue );
			Log.info( "Current Action:" + dCellData );
			Log.info( "------Error Information : Ignite-------" );


			System.out.println("------Error Information : Ignite-------");
			System.out.println("Current Script:" + scriptName);
			System.out.println("Current ScriptPath:" + testScript);
			System.out
			.println("Using ObjectRepositoryPath:" + objectRepository);
			System.out.println("Current Keyword:" + Action);
			System.out.println("Current ObjectDetails:" + cCellData);
			System.out.println("Current ObjectDetailsPath:" + ORvalue);
			System.out.println("Current Action:" + dCellData);
			System.out.println("------Error Information : Ignite-------");

			fail("Cannot test normally by Ignite.");
			// return;
		}
	}

	public static String getCellData(XSSFWorkbook xWB, XSSFCell tCell) {
		String cellStr = null;
		switch (tCell.getCellType()) {
		case XSSFCell.CELL_TYPE_STRING :
			cellStr = tCell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_NUMERIC :
			double dNum = tCell.getNumericCellValue();
			int iNum = (int) dNum;
			cellStr = String.valueOf(iNum);
			break;
		case XSSFCell.CELL_TYPE_FORMULA :
			FormulaEvaluator evaluator = xWB.getCreationHelper().createFormulaEvaluator();
			evaluator.evaluateFormulaCell(tCell);
			cellStr = tCell.getStringCellValue();
			break;
		case XSSFCell.CELL_TYPE_BOOLEAN :
			cellStr = String.valueOf(tCell.getBooleanCellValue());
		default:
			cellStr = "";
		}
		return cellStr;
	}

	public static WebElement getWebElement(){
		try{
			String[] cCellDataValCh = cCellData.split(";");
			String objectType = cCellDataValCh[0];
			String ObjectValCh = "";
			ObjectValCh = cCellDataValCh[1];


			for (int k = 0; k < ORrowcount; k++) {


				if(((ORsheet.getRow(k).getCell(1).getStringCellValue()).equalsIgnoreCase(ObjectValCh)==true))



				{
					String[] ORcellData = new String[3];

					ORcellData = (ORsheet.getRow(k).getCell(4).getStringCellValue().split("=",2));



					ORvalname = ORcellData[0]; 
					ORvalue = ORcellData[1]; 
					k = ORrowcount + 1;
					objFoundFlag = 1;
				}

			}

			try {
				WebElement elem;
				elem = func_FindObj_return(ORvalname, ORvalue);

				return elem ;
			} catch (Exception e) {
				System.out.println("Error Occured While getting Element-  "+e.getMessage());
				Log.info( "Error Occured While getting Element- " +e.getMessage());

			}
			//return elem ;
		}catch(Exception e){
			System.out.println("Exception Occured as -"+e.getMessage());
			Log.info( "Exception Occured as- " +e.getMessage());
		}
		return elem ;

	}



	public void readAttributeforPerform() throws Exception {


		try {
			if (ObjectSetVal.length() > 0)

			{

				if (ObjectSetVal.substring(0, 1).equalsIgnoreCase("#")) {

					if (map.get(ObjectSetVal.substring(1,
							(ObjectSetVal.length()))) != null) {
						ObjectSetVal = map.get(ObjectSetVal.substring(1,
								(ObjectSetVal.length())));
					} else {
						ObjectSetVal = "";
					}

				} else if (ObjectSetVal.contains("dt_")) {
					iflag = 0;

					String ObjectSetValtableheader[] = ObjectSetVal.split("_");
					int column = 0;
					String Searchtext = ObjectSetValtableheader[1];

					for (column = 0; column < DTcolumncount; column++) {

						if(Searchtext.equalsIgnoreCase(DTsheet.getRow(0).getCell(column).getStringCellValue())==true)


						{
							ObjectSetVal = DTsheet.getRow(dtrownum).getCell(column).getStringCellValue();



							if (ObjectSetVal.length() == 0) {
								ObjectSetVal = "";
							}
							iflag = 1;

						}

					}

					if (iflag != 1) {
						ObjectSetVal = "nodatarow";
					}

					else {
						Reporter.ReportEvent("toomanyarguments");
					}
				}

			}
		} catch (Exception e) {
			System.out.println(" Error Occured while Reading Attribute for Perform  "+e.getMessage());
			Log.info( "Exception Occured while Reading Attribute for Perform- " +e.getMessage());
		}


	}


	public void dCellAction() throws Exception {

		if (!(cCellObjType.equalsIgnoreCase("page")
				|| cCellObjType.equalsIgnoreCase("page;")
				|| cCellObjType.equalsIgnoreCase("dialog")
				|| cCellObjType.equalsIgnoreCase("dialog;")
				|| cCellObjType.equalsIgnoreCase("downloaddialog")
				|| cCellObjType.equalsIgnoreCase("downloaddialog;")
				|| cCellObjType.equalsIgnoreCase("uploaddialog") || cCellObjType
				.equalsIgnoreCase("uploaddialog;")))
		{
			try {
				String[] cCellDataValCh = cCellData.split(";");
				String objectType = cCellDataValCh[0];
				readAttributeforPerform();
				func_FindObj(ORvalname, ORvalue);
				if (elem == null) {
					return;
				} else {
					switch (ObjectSet.toLowerCase()) {
					case "set":
						if (objectType.equalsIgnoreCase("textbox")
								|| objectType.equalsIgnoreCase("textarea")) {

							elem.clear();
							StringBuffer inputvalue = new StringBuffer();
							if (ObjectSetVal == "nodatarow") {
								Reporter.ReportEvent("missing");
							} else {

								inputvalue.append(ObjectSetVal);


								D8.executeScript(
										"arguments[0].value=arguments[0].value + '"
												+ inputvalue.toString() + "';",
												elem);

								Reporter.ReportEvent("executed", ObjectSet,
										ObjectSetVal);
							}

							if (captureperform == true) {
								screenShot(loopnum, TScrowcount, TScname);
							}
						} else {
							Reporter.ReportEvent("action1");
						}
						break;
					case "listselect":
						if (objectType.equalsIgnoreCase("listbox")) {
							int foundCount = 0;
							String[] listvalues = ObjectSetVal.split(":");
							List<WebElement> listboxitems = elem
									.findElements(By.tagName("option"));
							Select chooseoptn = new Select(elem);
							chooseoptn.deselectAll();
							if (ObjectSetVal == "nodatarow") {
								Reporter.ReportEvent("missing");
							} else {
								for (WebElement opt : listboxitems) {
									for (int i = 0; i < listvalues.length; i++) {
										if (opt.getText().equalsIgnoreCase(
												listvalues[i])) {
											chooseoptn.selectByVisibleText(opt
													.getText());
											foundCount = foundCount + 1;
										}

									}
								}
								if (foundCount == 0
										&& ObjectSetVal.contains(""))

								{
									Reporter.ReportEvent("NoBlankAvailable");

								} else {
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
								}

								if (captureperform == true) {
									screenShot(loopnum, TScrowcount, TScname);
								}
							}
						} else {
							Reporter.ReportEvent("action1");
						}

						break;

					case "select":
						if (objectType.equalsIgnoreCase("combobox")) {
							if (ObjectSetVal != "") {
								new Select(elem)
								.selectByVisibleText(ObjectSetVal);
								Reporter.ReportEvent("executed", ObjectSet,
										ObjectSetVal);
							} else if (ObjectSetVal == "nodatarow") {
								Reporter.ReportEvent("missing");
							}

							else {
								if (new Select(elem).getOptions().toString()
										.contains("") == true) {
									try {
										new Select(elem)
										.selectByVisibleText("");
										Reporter.ReportEvent("executed", ObjectSet,
												ObjectSetVal);
									} catch (Exception e) {
										Reporter.ReportEvent("NoBlankAvailable");
									}
								} else {
									Reporter.ReportEvent("NoBlankAvailable");
								}
							}
							if (captureperform == true) {
								screenShot(loopnum, TScrowcount, TScname);

							}
						} else {
							Reporter.ReportEvent("action1");
						}

						break;

					case "check":

						if (ObjectSetVal == "nodatarow") {
							Reporter.ReportEvent("missing");
						} else {

							if (objectType.equalsIgnoreCase("checkbox")) {
								if (elem.isSelected()
										&& ObjectSetVal.equalsIgnoreCase("On")) {
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);

								} else if ((elem.isSelected() && ObjectSetVal
										.equalsIgnoreCase("off"))) {
									elem.click();
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
								} else if (ObjectSetVal.equalsIgnoreCase("on")) {
									elem.click();
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
								} else if ((ObjectSetVal
										.equalsIgnoreCase("off"))) {
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
								} else {
									Reporter.ReportEvent("failed", ObjectSet,
											ObjectSetVal);
								}

								if (captureperform == true) {

									screenShot(loopnum, TScrowcount, TScname);
								}
							} else {
								Reporter.ReportEvent("action1");
							}
						}
						break;

					case "click":
						parentWindowHandle1 = D8.getWindowHandle();
						try {

							if (elem.getAttribute("type").toLowerCase()
									.equals("file")
									&& browserName.equalsIgnoreCase("ff")) {

								JavascriptExecutor executor = (JavascriptExecutor) D8;
								executor.executeScript("arguments[0].click();",
										elem);

								Reporter.ReportEvent("executed");
								break;

							} else if (elem.getAttribute("type").toLowerCase()
									.equals("file")
									&& browserName.equalsIgnoreCase("ie")
									&& Integer.parseInt(browserver) == 8) {
								Reporter.ReportEvent("executed");

							} else {
								elem.click();
								Reporter.ReportEvent("executed");
							}
						} catch (Exception exp1) {
							elem.click();
							Reporter.ReportEvent("executed");
						}

						if (captureperform == true) {
							screenShot(loopnum, TScrowcount, TScname);
						}
						break;
					case "hover":
						JavascriptExecutor js = (JavascriptExecutor) D8;
						String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
						js.executeScript(mouseOverScript, elem);
						Reporter.ReportEvent("executed");
						break;

					case "altclick":
						JavascriptExecutor executor = (JavascriptExecutor) D8;
						executor.executeScript("arguments[0].click();", elem);
						Reporter.ReportEvent("executed"	);
						break;
					case "enter":
						elem.sendKeys(org.openqa.selenium.Keys.ENTER);
						Reporter.ReportEvent("executed");
						if (captureperform == true) {
							screenShot(loopnum, TScrowcount, TScname);
						}
						break;
					case "setdate":

						Robot robot1 = new Robot();
						ScreenshotTypeFlag = 1;
						String calstring = cCellObjName.toLowerCase();
						if (cCellObjType.equalsIgnoreCase("calendar")
								&& calstring.startsWith("cal_")) {
							try {

								String[] datearray = ObjectSetVal.split("-");
								mm = datearray[0];
								dd = datearray[1];
								yyyy = datearray[2];
								if (Integer.parseInt(mm) > 12
										|| Integer.parseInt(mm) < 1
										|| Integer.parseInt(yyyy) < 1000
										|| Integer.parseInt(yyyy) > 2999
										|| Integer.parseInt(yyyy) < 1700
										|| ((Integer.parseInt(dd) > 30) && (Integer
												.parseInt(dd) == 2
												|| Integer.parseInt(dd) == 4
												|| Integer.parseInt(dd) == 6
												|| Integer.parseInt(dd) == 9 || Integer
												.parseInt(dd) == 11))
										|| (Integer.parseInt(dd) > 28
												&& Integer.parseInt(mm) == 2 && (Integer
														.parseInt(yyyy) % 4 != 0))) {
									Reporter.ReportEvent("invaliddate1");
								} else {
									selectDate(mm, dd, yyyy);
								}

							} catch (Exception e) {
								Reporter.ReportEvent("invaliddate1");
								robot1.keyPress(KeyEvent.VK_ESCAPE);
								robot1.keyRelease(KeyEvent.VK_ESCAPE);

							}
						} else {

							Reporter.ReportEvent("calendaraction");
						}
						break;

					default:
						Reporter.ReportEvent("action");
						break;
					}
				}

			} catch (Exception e) {
				System.out.println(e.toString());
				Reporter.ReportEvent("failed", e);
			}
		} else {
			try {
				switch (ObjectSet.toLowerCase()) {

				case "ok":
					ScreenshotTypeFlag = 1;
					if (cCellObjType.equalsIgnoreCase("dialog")
							|| cCellObjType.equalsIgnoreCase("dialog;")) {
						dialogSwitch = D8.switchTo().alert();
						dialogSwitch.accept();
						Reporter.ReportEvent("executed");
					}
					if (captureperform == true) {
						screenShot(loopnum, TScrowcount, TScname);
					}

					break;
				case "cancel":
					ScreenshotTypeFlag = 1;

					if (cCellObjType.equalsIgnoreCase("dialog")
							|| cCellObjType.equalsIgnoreCase("dialog;")) {
						dialogSwitch = D8.switchTo().alert();
						dialogSwitch.dismiss();
						Reporter.ReportEvent("executed");
					}

					if (captureperform == true) {
						screenShot(loopnum, TScrowcount, TScname);
					}
					break;

				case "close":
					ScreenshotTypeFlag = 1;

					if (cCellObjType.equalsIgnoreCase("dialog")
							|| cCellObjType.equalsIgnoreCase("dialog;")) {

						dialogSwitch = D8.switchTo().alert();
						dialogSwitch.dismiss();
						Reporter.ReportEvent("executed");
						if (captureperform == true) {
							screenShot(loopnum, TScrowcount, TScname);
						}

					}

					else {
						windowFound = 0;
						int windowNums = 0;
						int windowItr = 0;
						String currentWindowHandle = D8.getWindowHandle();
						WebDriver newWindow = null;
						Set<String> al = new HashSet<String>();
						al = D8.getWindowHandles();
						windowNums = al.size();
						Iterator<String> windowIterator = al.iterator();
						if (cCellObjName.equalsIgnoreCase("page;")
								|| cCellObjName.equalsIgnoreCase("page")) {
							if (windowNums == 1) {
								if (captureperform == true) {
									screenShot(loopnum, TScrowcount, TScname);
								}
								D8.close();
								Reporter.ReportEvent("executed");
								windowFound = 1;
							} else {
								int winItr1 = 0;
								String windowHandle = null;
								String tempWindowHandle = null;
								while (winItr1 != windowNums) {
									tempWindowHandle = windowHandle;
									windowHandle = windowIterator.next();
									newWindow = D8.switchTo().window(
											windowHandle);
									if (currentWindowHandle
											.equalsIgnoreCase(windowHandle)) {
										if (winItr1 == 0) {
											if (captureperform == true) {
												screenShot(loopnum,
														TScrowcount, TScname);
											}
											D8.close();
											windowHandle = windowIterator
													.next();
											D8.switchTo().window(windowHandle);
											Reporter.ReportEvent("executed");
											windowFound = 1;
											break;
										} else {
											if (captureperform == true) {
												screenShot(loopnum,
														TScrowcount, TScname);
											}
											D8.close();
											D8.switchTo().window(
													tempWindowHandle);
											Reporter.ReportEvent("executed");
											windowFound = 1;
											break;

										}
									}

									winItr1++;
								}
							}
						} else {
							if (windowNums == 1) {
								if (D8.getTitle().toString()
										.equalsIgnoreCase(ObjectSetVal) == true) {
									if (captureperform == true) {
										screenShot(loopnum, TScrowcount,
												TScname);
									}
									D8.close();
									Reporter.ReportEvent("executed");
									windowFound = 1;
								}

							} else {
								if (cCellObjType.equalsIgnoreCase("page")
										&& D8.getTitle().equalsIgnoreCase(
												cCellObjName) == false) {
									for (windowItr = 0; windowItr < windowNums; windowItr++) {
										windowHandle = windowIterator.next();
										newWindow = D8.switchTo().window(
												windowHandle);
										if (newWindow.getTitle()
												.equalsIgnoreCase(cCellObjName)) {
											if (captureperform == true) {
												screenShot(loopnum,
														TScrowcount, TScname);
											}
											newWindow.close();
											Reporter.ReportEvent("executed");
											D8.switchTo().window(
													currentWindowHandle);
											windowFound = 1;
											break;
										}

									}

								} else {
									if (cCellObjType.equalsIgnoreCase("page")
											&& D8.getTitle()
											.toString()
											.equalsIgnoreCase(
													cCellObjName) == true) {
										// D8.close();
										int winItr1 = 0;
										String windowHandle = null;
										String tempWindowHandle = null;
										while (winItr1 != windowNums) {
											tempWindowHandle = windowHandle;
											windowHandle = windowIterator
													.next();
											newWindow = D8.switchTo().window(
													windowHandle);
											if (currentWindowHandle
													.equalsIgnoreCase(windowHandle)) {
												if (winItr1 == 0) {
													if (captureperform == true) {
														screenShot(loopnum,
																TScrowcount,
																TScname);
													}
													D8.close();
													windowHandle = windowIterator
															.next();
													D8.switchTo().window(
															windowHandle);
													Reporter.ReportEvent("executed");
													windowFound = 1;
													break;
												} else {
													D8.close();
													D8.switchTo().window(
															tempWindowHandle);
													Reporter.ReportEvent("executed");
													windowFound = 1;
													break;

												}
											}

											winItr1++;
										}

									}
								}
							}
						}
						if (windowFound != 1) {
							Reporter.ReportEvent("NoWindowFound");
						}
					}
					break;
				case "closeupload":
					ScreenshotTypeFlag = 1;
					doUploadDownload("closeupload");
					break;

				case "cancelupload":
					ScreenshotTypeFlag = 1;
					doUploadDownload("cancelupload");
					break;

				default:
					System.out.println("Action not supported");
					break;

				}
			}

			catch (NoAlertPresentException e) {
				Log.info( "Exception Occured in dCellAction- " +e.getMessage());
			}

		}

	}


	private void doUploadDownload(String action1) throws Exception {
		// Robot robot = new Robot();
		if (browserName.equalsIgnoreCase("ff")) {
			switch (action1) {
			case "upload":
				try {

					Thread.sleep(2000);
					Runtime.getRuntime().exec(
							execpath + " 2 upload " + cCellData + " "
									+ browserName.toLowerCase());
					Reporter.ReportEvent("executed");
				} catch (IOException e) {

					// TODO Auto-generated catch block
					System.out.println(e);

				}
				break;

			case "closeupload":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 closeupload " + cCellData + " "
									+ browserName.toLowerCase());
					Reporter.ReportEvent("executed");

				} catch (IOException e) {


					System.out.println(e);
					Log.info( "Exception Occured in closeupload- " +e.getMessage());

				}
				break;
			case "abortupload":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 closeupload " + "abort" + " "
									+ browserName.toLowerCase());

				} catch (IOException e) {

					Log.info( "Exception Occured in abortupload- " +e.getMessage());
					System.out.println(e);

				}
				break;

			case "cancelupload":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 cancelupload " + cCellData + " "
									+ browserName.toLowerCase());
					Reporter.ReportEvent("executed");
				} catch (IOException e) {

					Log.info( "Exception Occured in cancelupload- " +e.getMessage());
					System.out.println(e);

				}
				break;

			case "download":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 download " + ObjectSet + " "
									+ browserName.toLowerCase() + " "
									+ elem.getAttribute("href"));
					Thread.sleep(4000);
					Reporter.ReportEvent("executed");
				} catch (IOException e) {
					System.out.println("Error Occured in Dowload keyword execution  "+e.getMessage());
					Log.info( "IOException Occured in download- " +e.getMessage()); 

				}
				break;

			default:
				System.out.println("Action not supported");
				break;
			}
		} else if (browserName.equalsIgnoreCase("ie")) {
			switch (action1) {
			case "upload":
				if (Integer.parseInt(browserver) != 8) {
					try {

						Runtime.getRuntime().exec(
								execpath + " 2 upload " + cCellData + " "
										+ browserName.toLowerCase());
						Reporter.ReportEvent("executed");
					} catch (IOException e) {

						Log.info( "IOException Occured in upload- " +e.getMessage()); 
						System.out.println(e);

					}

				} else {
					elem.sendKeys(cCellData);
				}
				break;

			case "closeupload":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 closeupload " + cCellData + " "
									+ browserName.toLowerCase());
					Reporter.ReportEvent("executed");

				} catch (IOException e) {

					Log.info( "IOException Occured in closeupload- " +e.getMessage()); 
					System.out.println(e);

				}
				break;

			case "cancelupload":
				try {

					Runtime.getRuntime().exec(
							execpath + " 2 cancelupload " + cCellData + " "
									+ browserName.toLowerCase());
					Reporter.ReportEvent("executed");
				} catch (IOException e) {

					Log.info( "IOException Occured in cancelupload- " +e.getMessage()); 
					System.out.println(e);

				}
				break;

			case "download":

				try {
					Runtime.getRuntime().exec(
							execpath + " 2 download " + ObjectSet + " "
									+ browserName.toLowerCase() + " "
									+ elem.getAttribute("href"));
					Reporter.ReportEvent("executed");
				} catch (IOException e) {
					System.out.println("IO exception occured in   download key woard "+e.getMessage());
					Log.info( "IOException Occured in download- " +e.getMessage()); 

				}
				break;

			}

		}

		if (captureperform == true) {
			screenShot(loopnum, TScrowcount, TScname);
		}

	}


	public void selectDate(String mm2, String dd2, String yyyy2)
			throws Exception {
		String dateClass = null;
		int objectfound = 0;
		int monthnum1 = 0;
		String usedTitleMonth = null;
		String usedTitleYear = null;
		String usedTitletag = null;
		WebElement prevMonth = null;
		WebElement nextMonth = null;
		WebElement titleYear = null;
		WebElement daytoClick = null;
		int titleYearnum = 0;
		String titleMonthString = "";
		WebElement titleMonth = null;
		String datePickerType = "";
		String locatorNext = "";
		String locatorPrev = "";
		String outerHTMLCalendar = "";
		outerHTMLCalendar = elem.getAttribute("outerHTML");
		elem.click();
		int setmm = Integer.parseInt(mm2);
		int setyyyy = Integer.parseInt(yyyy2);
		int setdd = Integer.parseInt(dd2);
		WebElement titleDefault = null;
		String titleDefaultText;
		// List<WebElement> titlebootYearTags;
		List<WebElement> realCategoryDeciders = new ArrayList<WebElement>();
		// int titlebootYearNum;
		Robot robot1 = new Robot();
		int isDateNotSelected = 0;
		int isDateSelected = 0;

		if (outerHTMLCalendar.toLowerCase().contains("datepicker")) {
			datePickerType = "jquery";
		} else if (outerHTMLCalendar.toLowerCase().contains("type=")
				&& outerHTMLCalendar.toLowerCase().contains("date")) {
			datePickerType = "html5";
		} else {
			datePickerType = "boot";
		}

		switch (setmm) {

		case 1:
			monthpart = "Jan";
			monthpartjap = "1";
			break;
		case 2:
			monthpart = "Feb";
			monthpartjap = "2";
			break;
		case 3:
			monthpart = "Mar";
			monthpartjap = "3";
			break;
		case 4:
			monthpart = "Apr";
			monthpartjap = "4";
			break;
		case 5:
			monthpart = "May";
			monthpartjap = "5";
			break;
		case 6:
			monthpart = "Jun";
			monthpartjap = "6";
			break;
		case 7:
			monthpart = "Jul";
			monthpartjap = "7";
			break;
		case 8:
			monthpart = "Aug";
			monthpartjap = "8";
			break;
		case 9:
			monthpart = "Sep";
			monthpartjap = "9";
			break;
		case 10:
			monthpart = "Oct";
			monthpartjap = "10";
			break;
		case 11:
			monthpart = "Nov";
			monthpartjap = "11";
			break;
		case 12:
			monthpart = "Dec";
			monthpartjap = "12";
			break;
		default:
			Reporter.ReportEvent("invalidmonth");
			break;
		}
		int titleLength;
		switch (datePickerType) {

		case "jquery":
			if (!(lastSelecteddateJQ == setdd && lastSelectedmonthJQ == setmm && lastSelectedyearJQ == setyyyy)) {

				for (String strPrevMonth : setObj.getEnvprevMonth1()) {
					try {
						prevMonth = D8
								.findElementByXPath("//a[contains(@class,'"
										+ strPrevMonth + "')]");
						locatorPrev = prevMonth.getAttribute("class")
								.toString();
						objectfound = 1;
						break;
					} catch (Exception e) {
						continue;
					}
				}
				if (objectfound == 0) {
					Reporter.ReportEvent("prevmonth");
					break;
				} else {
					objectfound = 0;
				}
				for (String strNextMonth : setObj.getEnvnextMonth1()) {
					try {
						nextMonth = D8
								.findElementByXPath("//a[contains(@class,'"
										+ strNextMonth + "')]");
						locatorNext = nextMonth.getAttribute("class")
								.toString();
						objectfound = 1;
						break;
					} catch (Exception e) {
						continue;
					}
				}

				if (objectfound == 0) {
					Reporter.ReportEvent("nextmonth");

					break;
				} else {
					objectfound = 0;

				}

				for (String strtitleMonth : setObj.getEnvtitleMonth()) {

					try {
						titleMonth = D8
								.findElementByXPath("//span[contains(@class,'"
										+ strtitleMonth + "')]");
						titleMonthString = titleMonth.getText();

						usedTitleMonth = strtitleMonth;
						usedTitletag = "span";
						objectfound = 1;
						break;
					} catch (Exception e) {
						try {
							titleMonth = D8
									.findElementByXPath("//select[contains(@class,'"
											+ strtitleMonth + "')]");
							titleMonthString = new Select(titleMonth)
									.getFirstSelectedOption().getText()
									.toString();
							usedTitleMonth = strtitleMonth;
							usedTitletag = "select";
							objectfound = 1;
							break;
						} catch (Exception e4) {

						}
						continue;
					}

				}

				if (objectfound == 0) {
					Reporter.ReportEvent("titlemonth");

					break;
				} else {
					objectfound = 0;
				}
				for (String strtitleYear : setObj.getEnvtitleYear()) {

					try {
						titleYear = D8
								.findElementByXPath("//span[contains(@class,'"
										+ strtitleYear + "')]");
						titleYearnum = Integer.parseInt(titleYear.getText());

						usedTitleYear = strtitleYear;
						usedTitletag = "span";
						objectfound = 1;
						break;
					} catch (Exception e) {
						try {
							titleYear = D8
									.findElementByXPath("//select[contains(@class,'"
											+ strtitleYear + "')]");
							titleYearnum = Integer.parseInt(new Select(
									titleYear).getFirstSelectedOption()
									.getText());
							usedTitleYear = strtitleYear;
							usedTitletag = "select";
							objectfound = 1;

							break;
						} catch (Exception e5) {

						}
						continue;
					}
				}
				if (objectfound == 0) {
					Reporter.ReportEvent("titleyear");
					break;
				} else {
					objectfound = 0;

				}

				switch (titleMonthString.toLowerCase()) {

				case "jan":
					monthnum1 = 1;
					break;
				case "1":
					monthnum1 = 1;
					break;
				case "january":
					monthnum1 = 1;
					break;
				case "feb":
					monthnum1 = 2;
					break;
				case "2":
					monthnum1 = 2;
					break;
				case "february":
					monthnum1 = 2;
					break;
				case "mar":
					monthnum1 = 3;
					break;
				case "3":
					monthnum1 = 3;
					break;
				case "march":
					monthnum1 = 3;
					break;
				case "apr":
					monthnum1 = 4;
					break;
				case "4":
					monthnum1 = 4;
					break;
				case "april":
					monthnum1 = 4;
					break;
				case "may":
					monthnum1 = 5;
					break;
				case "5":
					monthnum1 = 5;
					break;
				case "june":
					monthnum1 = 6;
					break;
				case "jun":
					monthnum1 = 6;
					break;
				case "6":
					monthnum1 = 6;
					break;
				case "july":
					monthnum1 = 7;
					break;
				case "jul":
					monthnum1 = 7;
					break;
				case "7":
					monthnum1 = 7;
					break;
				case "aug":
					monthnum1 = 8;
					break;
				case "august":
					monthnum1 = 8;
					break;
				case "8":
					monthnum1 = 8;
					break;
				case "sep":
					monthnum1 = 9;
					break;
				case "september":
					monthnum1 = 9;
					break;
				case "9":
					monthnum1 = 9;
					break;
				case "oct":
					monthnum1 = 10;
					break;
				case "october":
					monthnum1 = 10;
					break;
				case "10":
					monthnum1 = 10;
					break;
				case "nov":
					monthnum1 = 11;
					break;
				case "november":
					monthnum1 = 11;
					break;
				case "11":
					monthnum1 = 11;
					break;
				case "dec":
					monthnum1 = 12;
					break;
				case "december":
					monthnum1 = 12;
					break;
				case "12":
					monthnum1 = 12;
					break;
				default:
					Reporter.ReportEvent("monthnotidentified");
					break;
				}

				if (setyyyy > titleYearnum && setmm > monthnum1) {
					try {
						do {

							nextMonth.click();
							monthnum1++;
							if (monthnum1 == 13)
								monthnum1 = 1;
							nextMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorNext + "')]");

							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");

							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}

						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( "IOException Occured in jquery case [date]- " +e.getMessage()); 
					}

				} else if (setyyyy < titleYearnum && setmm < monthnum1) {

					try {
						do {

							prevMonth.click();
							monthnum1--;
							if (monthnum1 == 0)
								monthnum1 = 12;
							prevMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorPrev + "')]");

							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");

							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}

						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date]- " +e.getMessage()); 
					}
				}

				else if (setyyyy == titleYearnum && setmm < monthnum1) {
					try {
						do {
							prevMonth.click();
							monthnum1--;
							if (monthnum1 == 0)
								monthnum1 = 12;
							prevMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorPrev + "')]");
							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");
							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}
						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date]- " +e.getMessage()); 
					}
				} else if (setyyyy == titleYearnum && setmm > monthnum1) {
					try {
						do {
							nextMonth.click();

							monthnum1++;
							if (monthnum1 == 13)
								monthnum1 = 1;
							nextMonth = D8

									.findElementByXPath("//a[contains(@class,'"
											+ locatorNext + "')]");

							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");

							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}

						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date]- " +e.getMessage()); 
					}
				}

				else if (setyyyy > titleYearnum && setmm == monthnum1) {
					try {
						do {

							nextMonth.click();
							monthnum1++;
							if (monthnum1 == 13)
								monthnum1 = 1;
							nextMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorNext + "')]");

							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");

							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}

						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date Calendar]- " +e.getMessage()); 
					}
				} else if (setyyyy < titleYearnum && setmm == monthnum1) {
					try {
						do {
							prevMonth.click();
							monthnum1--;
							if (monthnum1 == 0)
								monthnum1 = 12;
							prevMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorPrev + "')]");
							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");
							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();

								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}

						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date Calendar]- " +e.getMessage()); 
					}

				} else if (setyyyy > titleYearnum && setmm < monthnum1) {
					try {
						do {

							nextMonth.click();
							monthnum1++;
							if (monthnum1 == 13)
								monthnum1 = 1;
							nextMonth = D8

									.findElementByXPath("//a[contains(@class,'"
											+ locatorNext + "')]");

							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");

							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}
						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date Calendar]- " +e.getMessage()); 
					}
				} else if (setyyyy < titleYearnum && setmm > monthnum1) {
					try {
						do {
							prevMonth.click();
							monthnum1--;
							if (monthnum1 == 0)
								monthnum1 = 12;
							prevMonth = D8
									.findElementByXPath("//a[contains(@class,'"
											+ locatorPrev + "')]");
							titleMonth = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleMonth + "')]");
							titleYear = D8.findElementByXPath("//"
									+ usedTitletag + "[contains(@class,'"
									+ usedTitleYear + "')]");
							if (usedTitletag == "span") {
								titleMonthString = titleMonth.getText();
								titleYearnum = Integer.parseInt(titleYear
										.getText());
							} else if (usedTitletag == "select") {

								titleMonthString = new Select(titleMonth)
										.getFirstSelectedOption().getText()
										.toString();
								titleYearnum = Integer.parseInt(new Select(
										titleYear).getFirstSelectedOption()
										.getText());
							}
						} while (!((titleMonthString.toLowerCase().contains(
								monthpart.toLowerCase()) || titleMonthString
								.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
					} catch (Exception e) {
						isDateNotSelected = 1;
						Log.info( " Exception Occured in jquery case [date Calendar]- " +e.getMessage()); 
					}
				}
				if (isDateNotSelected == 0) {
					if (setyyyy == titleYearnum && setmm == monthnum1) {

						try {

							List<WebElement> daystoClick = D8
									.findElementsByXPath("//a[contains(@class,'"
											+ locatorPrev
											+ "')]"
											+ "/following::a[contains(text(),'"
											+ setdd + "')]");

							List<WebElement> categoryDeciders = D8
									.findElementsByXPath("//a[contains(@class,'"
											+ locatorPrev
											+ "')]"
											+ "/following::a[contains(text(),'"
											+ 15 + "')]");

							for (WebElement categoryDecider : categoryDeciders) {
								if (categoryDecider.getAttribute("href")
										.endsWith("#")
										&& categoryDecider.getText().equals(
												"15")) {
									realCategoryDeciders.add(categoryDecider);
									dateClass = categoryDecider
											.getAttribute("class");

								}
							}

							for (int i2 = 0; i2 < daystoClick.size(); i2++) {
								// Date date = new Date();
								if (today == setdd
										&& Integer.parseInt(daystoClick.get(i2)
												.getText()) == setdd
												&& daystoClick.get(i2)
												.getAttribute("href")
												.endsWith("#")) {

									daytoClick = daystoClick.get(i2);
									daytoClick.click();
									lastSelecteddateJQ = setdd;
									lastSelectedmonthJQ = setmm;
									lastSelectedyearJQ = setyyyy;
									isDateSelected = 1;
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
									break;

								} else if (Integer.parseInt(daystoClick.get(i2)
										.getText()) == setdd
										&& (daystoClick.get(i2)
												.getAttribute("class")
												.equalsIgnoreCase(dateClass) || (daystoClick
														.get(i2).getAttribute("class")
														.equalsIgnoreCase(selectedDateClass))
												&& daystoClick.get(i2)
												.getAttribute("href")
												.endsWith("#"))) {

									daytoClick = daystoClick.get(i2);
									daytoClick.click();
									lastSelecteddateJQ = setdd;
									lastSelectedmonthJQ = setmm;
									lastSelectedyearJQ = setyyyy;
									isDateSelected = 1;
									selectedDateClass = daytoClick
											.getAttribute("class");
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
									break;
								}
							}

						} catch (Exception e1) {
							isDateNotSelected = 1;
							Log.info( " Exception Occured in jquery case [date Calendar]- " +e1.getMessage()); 

						}

					}
					// }
					if (captureperform == true) {
						screenShot(loopnum, TScrowcount, TScname);
					}
					if (isDateSelected == 0) {

						Reporter.ReportEvent("invaliddate");
						robot1.keyPress(KeyEvent.VK_ESCAPE);
						robot1.keyRelease(KeyEvent.VK_ESCAPE);
					} else {
						isDateSelected = 0;
					}

				} else {

					isDateNotSelected = 0;
					Reporter.ReportEvent("invaliddate");
					robot1.keyPress(KeyEvent.VK_ESCAPE);
					robot1.keyRelease(KeyEvent.VK_ESCAPE);

				}
			} else {

				Reporter.ReportEvent("executed", ObjectSet, ObjectSetVal);
				robot1.keyPress(KeyEvent.VK_ESCAPE);
				robot1.keyRelease(KeyEvent.VK_ESCAPE);
				if (captureperform == true) {
					screenShot(loopnum, TScrowcount, TScname);
				}
			}

			break;

		case "boot":

			for (String strPrevMonth : setObj.getEnvprevMonth1()) {
				try {
					prevMonth = D8.findElementByXPath("//th[contains(@class,'"
							+ strPrevMonth + "')]");
					locatorPrev = strPrevMonth;
					objectfound = 1;
					break;
				} catch (Exception e) {
					continue;
				}
			}
			if (objectfound == 0) {
				Reporter.ReportEvent("prevmonth");
				break;
			} else {
				objectfound = 0;
			}

			for (String strNextMonth : setObj.getEnvnextMonth1()) {
				try {
					nextMonth = D8.findElementByXPath("//th[contains(@class,'"
							+ strNextMonth + "')]");
					locatorNext = strNextMonth;
					objectfound = 1;
					break;
				} catch (Exception e) {
					continue;
				}
			}
			if (objectfound == 0) {
				Reporter.ReportEvent("nextmonth");
				break;
			} else {
				objectfound = 0;
			}

			try {
				titleDefault = D8.findElementByXPath("//th[contains(@class,'"
						+ "switch" + "')]");

			}

			catch (Exception e) {
				Reporter.ReportEvent("titledefault");
				break;
			}
			titleDefaultText = titleDefault.getText().trim();
			titleLength = titleDefaultText.length();
			titleYearnum = Integer.parseInt(titleDefaultText
					.substring(titleLength - 4));
			titleMonthString = titleDefaultText.substring(0, titleLength - 5)
					.trim();
			switch (titleMonthString.toLowerCase()) {

			case "jan":
				monthnum1 = 1;
				break;
			case "1":
				monthnum1 = 1;
				break;
			case "january":
				monthnum1 = 1;
				break;
			case "feb":
				monthnum1 = 2;
				break;
			case "2":
				monthnum1 = 2;
				break;
			case "february":
				monthnum1 = 2;
				break;
			case "mar":
				monthnum1 = 3;
				break;
			case "3":
				monthnum1 = 3;
				break;
			case "march":
				monthnum1 = 3;
				break;
			case "apr":
				monthnum1 = 4;
				break;
			case "4":
				monthnum1 = 4;
				break;
			case "april":
				monthnum1 = 4;
				break;
			case "may":
				monthnum1 = 5;
				break;
			case "5":
				monthnum1 = 5;
				break;
			case "june":
				monthnum1 = 6;
				break;
			case "jun":
				monthnum1 = 6;
				break;
			case "6":
				monthnum1 = 6;
				break;
			case "july":
				monthnum1 = 7;
				break;
			case "jul":
				monthnum1 = 7;
				break;
			case "7":
				monthnum1 = 7;
				break;
			case "aug":
				monthnum1 = 8;
				break;
			case "august":
				monthnum1 = 8;
				break;
			case "8":
				monthnum1 = 8;
				break;
			case "sep":
				monthnum1 = 9;
				break;
			case "september":
				monthnum1 = 9;
				break;
			case "9":
				monthnum1 = 9;
				break;
			case "oct":
				monthnum1 = 10;
				break;
			case "october":
				monthnum1 = 10;
				break;
			case "10":
				monthnum1 = 10;
				break;
			case "nov":
				monthnum1 = 11;
				break;
			case "november":
				monthnum1 = 11;
				break;
			case "11":
				monthnum1 = 11;
				break;
			case "dec":
				monthnum1 = 12;
				break;
			case "december":
				monthnum1 = 12;
				break;
			case "12":
				monthnum1 = 12;
				break;
			default:
				Reporter.ReportEvent("monthnotidentified");
				break;
			}
			try {
				nextMonth = D8.findElementByXPath("//th[contains(@class,'"
						+ locatorNext + "')]");
			} catch (Exception e) {
				Reporter.ReportEvent("nextmonth");
				break;
			}
			try {
				prevMonth = D8.findElementByXPath("//th[contains(@class,'"
						+ locatorPrev + "')]");
			} catch (Exception e) {
				Reporter.ReportEvent("prevmonth");
				Log.info( " Exception Occured in jquery case [Switch case of Month ]- " +e.getMessage()); 
				break;
			}
			if (setyyyy > titleYearnum && setmm > monthnum1) {
				try {
					do {

						nextMonth.click();
						monthnum1++;
						if (monthnum1 == 13)
							monthnum1 = 1;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();

					}

					while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date Calendar]- " +e.getMessage()); 
					break;
				}
			} else if (setyyyy < titleYearnum && setmm < monthnum1) {
				try {
					do {

						prevMonth.click();
						monthnum1--;
						if (monthnum1 == 0)
							monthnum1 = 12;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			}

			else if (setyyyy == titleYearnum && setmm < monthnum1) {
				try {
					do {
						prevMonth.click();
						monthnum1--;
						if (monthnum1 == 0)
							monthnum1 = 12;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			} else if (setyyyy == titleYearnum && setmm > monthnum1) {
				try {
					do {
						nextMonth.click();
						monthnum1++;
						if (monthnum1 == 13)
							monthnum1 = 1;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}

			}

			else if (setyyyy > titleYearnum && setmm == monthnum1) {
				try {
					do {
						nextMonth.click();
						monthnum1++;
						if (monthnum1 == 13)
							monthnum1 = 1;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			} else if (setyyyy < titleYearnum && setmm == monthnum1) {
				try {
					do {
						prevMonth.click();
						monthnum1--;
						if (monthnum1 == 0)
							monthnum1 = 12;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();

					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			} else if (setyyyy > titleYearnum && setmm < monthnum1) {
				try {
					do {
						nextMonth.click();
						monthnum1++;
						if (monthnum1 == 13)
							monthnum1 = 1;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			} else if (setyyyy < titleYearnum && setmm > monthnum1) {
				try {
					do {
						prevMonth.click();
						monthnum1--;
						if (monthnum1 == 0)
							monthnum1 = 12;
						titleDefault = D8
								.findElementByXPath("//th[contains(@class,'"
										+ "switch" + "')]");
						titleDefaultText = titleDefault.getText().trim();
						titleLength = titleDefaultText.length();
						titleYearnum = Integer.parseInt(titleDefaultText
								.substring(titleLength - 4));
						titleMonthString = titleDefaultText.substring(0,
								titleLength - 5).trim();
					} while (!((titleMonthString.toLowerCase().contains(
							monthpart.toLowerCase()) || titleMonthString
							.toLowerCase().contains(monthpartjap)) && (setyyyy == titleYearnum)));
				} catch (Exception e) {
					Reporter.ReportEvent("invaliddate");
					Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					break;
				}
			}
			if (setyyyy == titleYearnum && setmm == monthnum1) {
				try {
					List<WebElement> daystoClick = D8
							.findElementsByXPath("//td[contains(text(),'"
									+ setdd + "')]");

					if (daystoClick.size() == 1) {
						daytoClick = daystoClick.get(0);
						daytoClick.click();
						Reporter.ReportEvent("executed", ObjectSet, ObjectSetVal);
					} else if (daystoClick.size() > 1) {
						if (setdd <= 7) {
							for (int i2 = 0; i2 < daystoClick.size(); i2++) {

								if (Integer.parseInt(daystoClick.get(i2)
										.getText()) == setdd) {

									daytoClick = daystoClick.get(i2);
									daytoClick.click();
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
									break;
								}
							}
							// daytoClick = daystoClick.get(0);

						} else {
							for (int i2 = daystoClick.size() - 1; i2 >= 0; i2--) {

								if (Integer.parseInt(daystoClick.get(i2)
										.getText()) == setdd) {

									daytoClick = daystoClick.get(i2);
									daytoClick.click();
									Reporter.ReportEvent("executed", ObjectSet,
											ObjectSetVal);
									break;
								}
							}

						}
					} else {
						Reporter.ReportEvent("invaliddate");
						Log.info( " Exception Occured in jquery case [date invaliddate]- " ); 
						break;
					}

				} catch (Exception ex1) {
					try {
						List<WebElement> daystoClick = D8
								.findElementsByXPath("//span[contains(text(),'"
										+ setdd + "')]");
						if (daystoClick.size() == 1) {
							daytoClick = daystoClick.get(0);
							daytoClick.click();
							Reporter.ReportEvent("executed", ObjectSet, ObjectSetVal);
						} else if (daystoClick.size() > 1) {
							if (setdd <= 7) {
								for (int i2 = 0; i2 < daystoClick.size(); i2++) {

									if (Integer.parseInt(daystoClick.get(i2)
											.getText()) == setdd) {

										daytoClick = daystoClick.get(i2);
										daytoClick.click();
										Reporter.ReportEvent("executed", ObjectSet,
												ObjectSetVal);
										break;
									}
								}
								// daytoClick = daystoClick.get(0);

							} else {
								for (int i2 = daystoClick.size() - 1; i2 >= 0; i2--) {

									if (Integer.parseInt(daystoClick.get(i2)
											.getText()) == setdd) {

										daytoClick = daystoClick.get(i2);
										daytoClick.click();
										Reporter.ReportEvent("executed", ObjectSet,
												ObjectSetVal);
										break;
									}
								}

							}
						} else {
							Reporter.ReportEvent("invaliddate");
						}

						Reporter.ReportEvent("executed", ObjectSet, ObjectSetVal);
					}

					catch (Exception e) {
						Reporter.ReportEvent("invaliddate");
						Log.info( " Exception Occured in jquery case [date invaliddate]- " +e.getMessage()); 
					}
				}

			}
			if (captureperform == true) {
				screenShot(loopnum, TScrowcount, TScname);
			}
			elem.click();

			break;

		default:
			System.out.println("Notdefined");
			break;

		}

	}


	public static void func_InvokeFunction(String functionName,	String argumentlist) 
	{
		Object[] argument_list = null;
		int checkNULL = 0;
		int CheckONE = 0;
		Class[] parameterTypes = null;
		//if (argumentlist == "") {
		if (argumentlist.isEmpty()) {
			checkNULL = 1;

		} else if (argumentlist.contains(":")) {
			argument_list = argumentlist.split(":");
		} else {
			CheckONE = 1;

		}
		String function_name = functionName;
		try {
			SeleniumWD s1 = new SeleniumWD();
			Method[] declaredMethods = SeleniumWD.class.getDeclaredMethods();
			for (Method m : declaredMethods) {
				System.out.println(m.getName());
				if (checkNULL != 1) {
					parameterTypes = m.getParameterTypes();
				}
				if (checkNULL == 0) {
					if ((m.getName()).equals(function_name)) {
						if (parameterTypes.length > 1) {
							if (parameterTypes.length == argument_list.length) {
								try {
									m.invoke(s1, argument_list);
									Reporter.ReportEvent("executed");
								} catch (Exception e) {
									Reporter.ReportEvent("userdefined");
								}
							}
							break;
						} else if ((m.getName()).equals(function_name)
								&& CheckONE == 1 && parameterTypes.length == 1) {

							try {
								m.invoke(s1, argumentlist);
								Reporter.ReportEvent("executed");
							} catch (Exception e) {
								Reporter.ReportEvent("userdefined");
							}

							break;
						}
					}
				} else if (m.getName().equals(function_name) && checkNULL == 1) {
					try {
						m.invoke(s1);
						Reporter.ReportEvent("executed");
					} catch (Exception e) {
						Reporter.ReportEvent("userdefined");
					}
					break;
				}

			}

		} catch (Exception e) {
			System.out.println(e);
			Log.info( " Exception Occured in func_InvokeFunction- " +e.getMessage()); 
		}
	}


	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			Log.info( " Exception Occured in isInteger- " +e.getMessage()); 
			return false;
		}
	}

	// Single Argument
	public static void Display(String Name)
	{
		System.out.println(Name);
	}

	// three
	public static void Display(String Name,String Name2,String Name3)
	{
		System.out.println(Name+ " "+Name2+ " "+Name3+ " ");
	}


	public static void CalculationTest(String value1, String value2, String value3, String value4)
	{
		int convert_value1 = Integer.parseInt(value1);
		int convert_value2 = Integer.parseInt(value2);
		int convert_value3 = Integer.parseInt(value3);
		int convert_value4 = Integer.parseInt(value4);
		int Add = 0;
		int Subtraction = 0;
		int Multiplication = 0;
		int Division = 0;

		Add = convert_value1 + convert_value2;
		Subtraction = convert_value3 - convert_value4;
		Multiplication = convert_value1 * convert_value3;
		Division = convert_value2 / convert_value4;

		System.out.println("value1=" + value1);
		System.out.println("value2=" + value2);
		System.out.println("value3=" + value3);
		System.out.println("value4=" + value4);
		System.out.println("Add = value1 + value2");
		System.out.println("Add="+ Add);
		System.out.println("Subtraction = value3 - value4");
		System.out.println("Subtraction=" + Subtraction);
		System.out.println("Multiplication = value1 * value3");
		System.out.println("Multiplication=" + Multiplication);
		System.out.println("Division = value2 / value4");
		System.out.println("Division=" + Division);

	}
}
