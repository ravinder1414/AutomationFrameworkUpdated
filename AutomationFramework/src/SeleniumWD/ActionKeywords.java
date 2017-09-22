package SeleniumWD;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utility.*;

public class ActionKeywords {
	public static WebDriver driver;


	public static WebDriver OpenBrowser(String object,String sBrowserName) throws Exception{
		//String sBrowserName;
		try{
			//sBrowserName = ExcelUtils.getCellData(iTestCaseRow, Constant.Col_Browser);
			if(sBrowserName.equals("Firefox")){
				driver = new FirefoxDriver();
				Log.info("New driver instantiated");
			}
			else if(sBrowserName.equals("IE")){
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
			}
			else if(sBrowserName.equals("Chrome")){
				driver=new ChromeDriver();
				Log.info("New driver instantiated");
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Log.info("Implicit wait applied on the driver for 10 seconds");
			driver.get(Constant.URL);
			Log.info("Web application launched successfully");
		}catch (Exception e){
			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());
		}
		return driver;
	}

	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(Constant.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
		}
	}

	public static void click(String object, String data){
		try{
			Log.info("Clicking on Webelement "+ object);
			//driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
			Log.error("Not able to click --- " + e.getMessage());
		}
	}

	public static void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			//driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}catch(Exception e){
			Log.error("Not able to Enter UserName --- " + e.getMessage());
		}
	}

	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 5 seconds");
			Thread.sleep(5000);
		}catch(Exception e){
			Log.error("Not able to Wait --- " + e.getMessage());
		}
	}

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			driver.quit();
		}catch(Exception e){
			Log.error("Not able to Close the Browser --- " + e.getMessage());
		}
	}

}
