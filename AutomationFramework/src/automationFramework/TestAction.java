package automationFramework;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.log4j.xml.DOMConfigurator;

import utility.BrowserFactory;
import utility.Constant;
import utility.ExcelUtils;
import utility.Log;
import utility.Utils;

public class TestAction {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String arg;
		/*
		 * 30 1025 "C:\\temp" 100
		 * "C:\AutomationFramework\src\\testData\\Timesheet_Multiplecandidate_Updated.xlsx" true 
		 * ProjectId, ReleaseID, executionStartTime, testCompleteStatus,
		 * csvFilePath, excelFilePath, integrationFlag
		 */
		if (args[3].equalsIgnoreCase("200")) {
			arg = "Reading all test arguments as ";
			for (String s : args) {
				arg = arg + " <" + s + ">";
			}
			Log.info(arg);
			Constant.Vars = new LocalTC(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
			Constant.Vars.setReportReleaseId(Integer.toString(Constant.Vars.iReleaseID));
			try {
				ExcelUtils.readCSVForTestComplete();
				Reporter.generateReport(Constant.Vars);
				Constant.Vars.bw1.close();
				Log.info("Report has been generated");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		else {
			if (args.length != 6) {
				System.err.println("Blocked: Test started with wrong input arguments");
				System.exit(1);
			}
			arg = "Reading all test arguments as ";
			for (String s : args) {
				arg = arg + " <" + s + ">";
			}
			// Provide Log4j configuration settings
			DOMConfigurator.configure("log4j.xml");
			Log.info(arg);

			/****************
			 * Create a TestNG XML at runtime by passing the command line
			 * variables to TestNG
			 **************/
			// Set Suite Name in XML
			/*XmlSuite suite = new XmlSuite();
			suite.setName("IgniteSuite");
			Log.info("added xml suite");
			// Set Test name in XML
			XmlTest test = new XmlTest(suite);
			test.setName("IgniteTest");
			Log.info("added xml test");
			// Add Parameters to the XML
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("ProjectID", args[0]);
			parameters.put("ReleaseID", args[1]);
			parameters.put("TestSetID", args[2]);
			parameters.put("TestCaseID", args[3]);
			parameters.put("TestRunPath", args[4]);
			parameters.put("Integration", args[5]);
			Log.info("added xml parameters");
			test.setParameters(parameters);
			Log.info("added xml parameters to xml");
			// Add Class name in xml
			List<XmlClass> myclasses = new ArrayList<XmlClass>(); // List of
																	// XMLClass
			String classname = "automationFramework.FrameworkDriver";
			XmlClass cl = new XmlClass(classname);
			myclasses.add(cl); // add new class to the list
			test.setXmlClasses(myclasses); // add class list to XML
			// Create a list of XmlTests and add the Xmltest you created earlier
			// to it.
			List<XmlTest> myTests = new ArrayList<XmlTest>();
			myTests.add(test);
			suite.setTests(myTests);

			*//************************************************************************************************************//*
			Log.info("Created a dynamic XML using parameters");
			*//****************
			 * Calling Framework to execute test for this test case
			 ****************************************//*
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			TestNG testng = new TestNG();
			testng.setXmlSuites(suites);
			Log.info("Starting IgniteFramework to execute test");
			testng.run();*/
			try {
				FrameworkDriver.beforeSuite(args[0], args[1], args[2], args[3], args[4], args[5]);
			} catch (MalformedURLException e) {
				Log.error("Error occure on calling FrameworkDriver: " + e.getMessage());
				Utils.callAfterException();
			} catch (Exception ex) {
				Log.error("Error occure on calling FrameworkDriver beforeSuite method: " + ex.getMessage());
				Utils.callAfterException();
			}
			finally {
				ExecutionPauseUtil.closeExecutionFrame();
				/*Utils.callAfterException();*/
				Runtime.getRuntime().exec("taskkill /F /IM winAuthentication.exe");
				BrowserFactory.closeAllDriver();
			}
		}

		/*************************************************************************************************************/
	}

}
