package pageObjects;

//Calling functions from   
//http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-http://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.Constant;
import utility.Log;
import utility.Utils;

public class LoginPage 
{
	public static class LoginFame{
		private static WebElement element = null;
		public static WebElement txtbx_UserName() throws Exception{
			try{
				element = Constant.Vars.obj.findelement(Constant.Vars.obj.getLocator("username"));
			}
			catch (Exception e)
			{
				// Printing logs for my report
				Log.error("Report Category button element is not found.");
				// Taking screenshot for defect reporting
				Utils.takeScreenshot(Constant.driver,"LoginPage");
				// After doing my work, now i want to stop my test case
				throw(e);
			}
			return element;
		}

		public static WebElement txtbx_Password() throws Exception{
			try{
				element = Constant.Vars.obj.findelement(Constant.Vars.obj.getLocator("password"));
			}
			catch (Exception e)
			{
				// Printing logs for my report
				Log.error("Report Category button element is not found.");
				// Taking screenshot for defect reporting
				Utils.takeScreenshot(Constant.driver,"LoginPage");
				// After doing my work, now i want to stop my test case
				throw(e);
			}
			return element;
		}

		public static WebElement btn_LogIn() throws Exception{
			try{
				element = Constant.driver.findElement(By.id("username"));
			}
			catch (Exception e)
			{
				// Printing logs for my report
				Log.error("Report Category button element is not found.");
				// Taking screenshot for defect reporting
				Utils.takeScreenshot(Constant.driver,"LoginPage");
				// After doing my work, now i want to stop my test case
				throw(e);
			}
			return element;
		}
	}
}