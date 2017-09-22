package utility;
//
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
/********************************************************************************************************
 * Usage: 	WebDriver driver = BrowserFactory.getBrowser("Firefox");
 * 			BrowserFactory.closeAllDriver();
 * 			using browser stack to run test on different browsers https://www.browserstack.com/automate/java
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import automationFramework.LocalTC;

public class BrowserFactory extends RemoteWebDriver  {

	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	/*
	 * Factory method for getting browsers
	 */
	public static WebDriver getBrowser(LocalTC Vars) {
		WebDriver driver = null;
		String driverPath = "";
		switch (Vars.getbrowsername()) {
		case "Firefox":
			driver = drivers.get("Firefox");
			if (driver == null || driver.equals("FirefoxDriver: firefox on WINDOWS (null)")) {
				//System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				System.setProperty("webdriver.gecko.driver", Constant.FirefoxGeckoDriver);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAcceptUntrustedCertificates(true);
				profile.setAssumeUntrustedCertificateIssuer(false);
				profile.setPreference("browser.download.folderList", 2);
				profile.setPreference("browser.download.dir", Constant.File_DownloadPath);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream/application/xml,text/plain,text/xml,image/jpeg,text/csv,application/pdf,application/xls,application/xlsx");
				profile.setPreference("browser.download.manager.showWhenStarting", false);
				profile.setPreference("browser.download.manager.focusWhenStarting", false);  
				profile.setPreference("browser.download.useDownloadDir", true);
				profile.setPreference("browser.helperApps.alwaysAsk.force", false);
				profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
				profile.setPreference("browser.download.manager.closeWhenDone", true);
				profile.setPreference("browser.download.manager.showAlertOnComplete", false);
				profile.setPreference("browser.download.manager.useWindow", false);
				profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
				profile.setPreference("pdfjs.disabled", true);
				DesiredCapabilities dc = DesiredCapabilities.firefox();
				dc.setJavascriptEnabled(true);
				dc.setCapability("marionette", true);
			    //dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			    dc.setCapability("acceptInsecureCerts",true);
			    //Object capabilities = new FirefoxOptions().addTo(dc);
				dc.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(dc);
				drivers.put("Firefox", driver);
				//HtmlUnitDriver unitDriver = new HtmlUnitDriver();
			}
			break;
		case "IE":
			driver = drivers.get("IE");
			driverPath = new File(Constant.ieDriverPath)
	                .getAbsolutePath();
			if (driver == null) {
				
				DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
				//capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);   
				capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capability.setCapability("ignoreZoomSetting", true);
				capability.setCapability("useLegacyInternalServer", true);
				capability.setCapability("nativeEvents", false);
				capability.setCapability("initialBrowserUrl", Constant.Vars.getObjProp());
				System.setProperty("webdriver.ie.driver",driverPath);
				driver = new InternetExplorerDriver(capability);
				driver.getWindowHandle();
				Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
				Vars.setBrowserVer(cap.getVersion());
				drivers.put("IE", driver);
			}
			break;
		case "Chrome":
			driver = drivers.get("Chrome");
			driverPath = new File(Constant.chromeDriverPath)
	                .getAbsolutePath();
			if (driver == null) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");
				System.setProperty("webdriver.chrome.driver",driverPath);
				driver = new ChromeDriver(options); 
				drivers.put("Chrome", driver);
			}
			break;
		case "BS-IE8":
			driver = drivers.get("BS-IE8");
			if (driver ==null){

			}
			break;
		case "BS-IE9":
			driver = drivers.get("BS-IE9");
			if (driver ==null){

			}
			break;
		case "BS-Firefox":
			driver = drivers.get("BS-Firefox");
			if (driver ==null){
				try {
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("browser.download.folderList", 2);
					profile.setPreference("browser.download.dir", Constant.File_DownloadPath);
					profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
					profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream/application/xml,text/plain,text/xml,image/jpeg,text/csv,application/pdf");
					profile.setPreference("browser.download.manager.showWhenStarting", false);
					profile.setPreference("browser.download.manager.focusWhenStarting", false);  
					profile.setPreference("browser.download.useDownloadDir", true);
					profile.setPreference("browser.helperApps.alwaysAsk.force", false);
					profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
					profile.setPreference("browser.download.manager.closeWhenDone", true);
					profile.setPreference("browser.download.manager.showAlertOnComplete", false);
					profile.setPreference("browser.download.manager.useWindow", false);
					profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
					profile.setPreference("pdfjs.disabled", true);
					DesiredCapabilities dc = DesiredCapabilities.firefox();
					dc.setJavascriptEnabled(true);
					dc.setCapability(FirefoxDriver.PROFILE, profile);
				    dc.setCapability("browser", "IE");
				    dc.setCapability("browser_version", "10.0");
				    dc.setCapability("os", "Windows");
				    dc.setCapability("os_version", "XP");
				    dc.setCapability("browserstack.debug", "true");

						driver = new RemoteWebDriver(new URL(Constant.BS_URL), dc);
						drivers.put("BS-IE10", driver);
					} catch (MalformedURLException e) {
						Log.info(e.getMessage());
					}
			}
			break;
		case "BS-Chrome":
			driver = drivers.get("BS-Chrome");
			if (driver ==null){

			}
			break;
		case "BS-IE10":
			driver = drivers.get("BS-IE10");
			if (driver ==null){
				try {
			    DesiredCapabilities caps = new DesiredCapabilities();
			    caps.setCapability("browser", "IE");
			    caps.setCapability("browser_version", "10.0");
			    caps.setCapability("os", "Windows");
			    caps.setCapability("os_version", "XP");
			    caps.setCapability("browserstack.debug", "true");

					driver = new RemoteWebDriver(new URL(Constant.BS_URL), caps);
					drivers.put("BS-IE10", driver);
				} catch (MalformedURLException e) {
					Log.info(e.getMessage());
				}
			}
			break;
		case "BS-IE11":
			driver = drivers.get("BS-IE11");
			if (driver ==null){

			}
			break;
		}
		
		if (!Vars.getbrowsername().equalsIgnoreCase("Chrome")) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}
		return driver;
	}

	public static void closeAllDriver() throws Exception {
		Set<String> Keys = drivers.keySet();
		try {
			for (String key : Keys) {
				drivers.get(key).quit();
				if (Constant.Vars.getbrowsername().equalsIgnoreCase("Firefox"))
					Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
				else if (Constant.Vars.getbrowsername().equalsIgnoreCase("chrome"))
					Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
				else if (Constant.Vars.getbrowsername().equalsIgnoreCase("IE"))
					Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			}
		} catch (Exception e) {
			Log.fatal("Error occured while closing the browser " + e.getMessage());
			throw e;
		}
	}
}