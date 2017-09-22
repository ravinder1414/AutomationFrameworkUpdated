package appModules;

import org.openqa.selenium.WebDriver;

import pageObjects.LoginPage;
import utility.Log;

public class FuncLogin {

	public static void Execute(WebDriver driver,String sUsername, String sPassword) throws Exception 
	{
		LoginPage.LoginFame.txtbx_UserName().sendKeys(sUsername);
		Log.info("Username entered in the Username text box");
		LoginPage.LoginFame.txtbx_Password().sendKeys(sPassword);
		Log.info("Password entered in the Password text box");
		LoginPage.LoginFame.btn_LogIn().click();
		Log.info("Login button clicked");
		System.out.println(" Login Successfully, now it is the time to Log Off buddy.");
		//Home_Page.lnk_LogOut(driver).click(); 
		driver.quit();
		//jdjdjjd
	}

}