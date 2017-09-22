package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationFramework.Reporter;
import mail.SendMail;

public class Utils {

	public static String getTestCaseName(String sTestCase) throws Exception {

		String value = sTestCase;

		try {

			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");
			value = value.substring(posi + 1);
			return value;
		} catch (Exception e) {
			Log.error("Class Utils | Method getTestCaseName | Exception desc : " + e.getMessage());
			throw (e);
		}
	}

	public static void mouseHoverAction(WebElement mainElement, String subElement) {
		// Actions action = new Actions(driver);
		// action.moveToElement(mainElement).perform();
		if (subElement.equals("Accessories")) {
			Log.info("Accessories link is found under Product Category");
		}
		if (subElement.equals("iMacs")) {
			Log.info("iMacs link is found under Product Category");
		}
		if (subElement.equals("iPads")) {
			Log.info("iPads link is found under Product Category");
		}
		if (subElement.equals("iPhones")) {
			Log.info("iPhones link is found under Product Category");
		}
		Log.info("Click action is performed on the selected Product Type");
	}

	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(Constant.driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void takeScreenshot(WebDriver driver, String sTestCaseName) throws Exception {

		try {

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(scrFile, new File(Constant.Path_ScreenShot + sTestCaseName + ".jpg"));

		} catch (Exception e) {

			Log.error("Class Utils | Method takeScreenshot | Exception occured while capturing ScreenShot : "
					+ e.getMessage());

			throw new Exception();

		}

	}

	public static String htmlToTextConvertMethod(String readLine) {
		StringBuilder strBuilder = new StringBuilder();
		String strInLine[] = readLine.split("\n");
		for (int i = 0; i < strInLine.length; i++) {
			strInLine[i] = strInLine[i].replaceAll("&nbsp;", "");
			String temptext = Jsoup.parse(strInLine[i].trim()).text();
			strBuilder.append(temptext + "\n");
		}
		return strBuilder.toString();
	}

	public static boolean isDate(String input) {
		try {
			long datedata = Date.parse(input);
			return true;
		} catch (Exception e) {
			// Log.error(Level.SEVERE, " Exception Occured in isInteger- "
			// +e.getMessage());
			return false;
		}
	}

	/**
	 * @param input
	 * @param dateFormat
	 * @return
	 */
	public static boolean isDate(String input, String dateFormat) {
		if (dateFormat == null || dateFormat.trim().length() <= 0) {
			return isDate(input);
		} else {
			DateFormat format = new SimpleDateFormat(dateFormat);
			try {
				Date d = format.parse(input);
				return true;
			} catch (Exception err) {
				Log.error("Exception while formatting date :" + input + " , with pattern : +" + dateFormat
						+ ", Message :" + err.getMessage());
			}

		}
		return false;
	}
	
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			// Log.error(Level.SEVERE, " Exception Occured in isInteger- "
			// +e.getMessage());
			return false;
		}
	}

	/**
	 * @param object
	 * @param param
	 * @return
	 * @throws IOException
	 *             Method for autocorrecting spelling mistake of test step and
	 *             object in object repository
	 */
	public static String spellCheckAndCorrect(String object, String param) throws IOException {
		String[] strTestStep = new String[30];
		String strTestStepSpell = "";
		String line = "";
		int i = 0;
		
		try {

			if (param.equalsIgnoreCase(Constant.Keyword)) {
				strTestStep = object.split(" ");

				for (String strTestStepSplit : strTestStep) {
					line = SpellingCorrector.correct(strTestStepSplit);
					if (i == 0)
						strTestStepSpell = line;

					else
						strTestStepSpell = strTestStepSpell + " " + line;
					i++;
				}
				Log.info("Corrected Sentence: " + strTestStepSpell);

			} else if (param.equalsIgnoreCase("Execute")) {
				line = SpellingCorrector.correct(object);
				strTestStepSpell = line;
				Log.info("Corrected Sentence: " + strTestStepSpell);
			}

		} catch (Exception e) {
			Log.info("Correction is failed: " + e.getMessage());
		}
		return strTestStepSpell;
	}

	public static boolean isAlertPresent() {
		try {
			Constant.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}

	}

	/**
	 * @param month
	 * @return Method for converting month into short month name
	 */
	public static String getMonthName(int month) {
		switch (month) {
		case 1:
			return "Jan";
		case 2:
			return "Feb";
		case 3:
			return "Mar";
		case 4:
			return "Apr";
		case 5:
			return "May";
		case 6:
			return "Jun";
		case 7:
			return "Jul";
		case 8:
			return "Aug";
		case 9:
			return "Sep";
		case 10:
			return "Oct";
		case 11:
			return "Nov";
		case 12:
			return "Dec";
		}
		return null;

	}

	/**
	 * @throws Exception 
	 * 
	 */
	public static void callAfterException() throws Exception {
		try {
			Reporter.generateReport(Constant.Vars);
			Constant.Vars.bw1.close();
			Log.endTestSet("Ending TestSet " + Constant.Vars.getTestSetID());
			try {
				String[] mailObjects = Constant.Vars.obj.getPropertyMail().split(";");
				if (mailObjects.length > 0 && mailObjects.length == 5)
					SendMail.sendAttachmentEmail(mailObjects[0], mailObjects[1], mailObjects[2], mailObjects[3],
							mailObjects[4]);
			} catch (Exception exc) {
				Log.error("Error in getting object for mail: " + exc.getMessage());
			}
			Runtime.getRuntime().exec("taskkill /F /IM winAuthentication.exe");
			BrowserFactory.closeAllDriver();
			Log.info("Browser closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * @param input
	 * @return
	 */
	public static boolean isDouble(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			//Log.error(Level.SEVERE, " Exception Occured in isInteger- " +e.getMessage()); 
			return false;
		}
	}

}