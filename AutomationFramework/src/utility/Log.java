package utility;

import org.apache.log4j.Logger;

public class Log {
	// Initialize Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());//
	
	public static void startTestSet(String sTestSetName){
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		Log.info("#######################     TestSet    "+sTestSetName+ "       ########################");
		Log.info("****************************************************************************************");
		Log.info("****************************************************************************************");
		System.out.println("#######################     TestSet    "+sTestSetName+ "       ########################");
	}

	//This is to print log for the ending of the test case
	public static void endTestSet(String sTestCaseName){
		Log.info("-------------------   TestSet   "+sTestCaseName +" -E---N---D-"+"  ----------------------");
		Log.info("@");
		Log.info("@");
		Log.info("@");
		Log.info("@");
		System.out.println("-------------------   TestSet   "+sTestCaseName +" -E---N---D-"+"  ----------------------");
	}
	
	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
	public static void startTestCase(String sTestCaseName){
		Log.info("****************************************************************************************");
		Log.info("$$$$$$$$ TestCase "+sTestCaseName+ " $$$$$$$$$$$$");
		Log.info("****************************************************************************************");
		System.out.println("$$$$$$$$$$$$$$$$$$ TestCase "+sTestCaseName+ "    $$$$$$$$$$$$$$$$$$");
	}

	//This is to print log for the ending of the test case
	public static void endTestCase(String sTestCaseName){
		Log.info("xxxxxxxxx Test Case "+sTestCaseName+"-E---N---D-"+" xxxxxxxxxxx");
		System.out.println("xxxxxxxxxxxxxxx Test Case "+sTestCaseName+"-E---N---D-"+"      xxxxxxxxxxxxx");
	}

	// Need to create these methods, so that they can be called  
	public static void info(String message) {
		Log.info(message);
		System.out.println("INFO :" + message);
	}

	public static void warn(String message) {
		Log.warn(message);
		System.out.println("WARN :" +message);
	}

	public static void error(String message) {
		Log.error(message);
		System.out.println("ERROR :" + message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
		System.out.println("FATAL :"+message);
	}

	public static void debug(String message) {
		Log.debug("DEBUG : " +message);
	}
}