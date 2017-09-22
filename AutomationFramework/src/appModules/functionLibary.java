package appModules;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import automationFramework.KeywordAction;
import utility.Constant;
import utility.RandomGenerator;

public class functionLibary {
	
	static WebDriver driver = Constant.driver;

public static void newreq(String HiringManager,String Directorate,String ServiceArea,String LocationofVacancy,String JobTitle,String ReasonforVacancy,String StartDate,String EndDate,String PayRate,String Duration,String Summary) throws Exception{
		
		new Select(driver.findElement(By.id("BookingRequestedByDDL"))).selectByVisibleText(HiringManager);
		new Select(driver.findElement(By.id("Department"))).selectByVisibleText(Directorate);
		new Select(driver.findElement(By.id("Division"))).selectByVisibleText(ServiceArea);
		new Select(driver.findElement(By.id("Location"))).selectByVisibleText(LocationofVacancy);
		new Select(driver.findElement(By.id("JobTitleDDL"))).selectByVisibleText(JobTitle);
		new Select(driver.findElement(By.id("TempPerm"))).selectByVisibleText(ReasonforVacancy);
		driver.findElement(By.id("StartDate")).clear();
		driver.findElement(By.id("StartDate")).sendKeys("11 Jul 2016");
//		WebElement datepicker = driver.findElement(By.id("ui-datepicker-div"));  
//		 List<WebElement> rows=datepicker.findElements(By.tagName("tr"));
//		 //System.out.println(rows.size());
//		List<WebElement> columns=datepicker.findElements(By.tagName("td"));  
//		// System.out.println(columns.size());   
//		for (WebElement cell: columns){  
//		//Select 20th Date   
//		if (cell.getText().equals(StartDate)){  
//		cell.findElement(By.linkText(StartDate)).click();  
//		break;
//		 }  
//		} 
		driver.findElement(By.id("EndDate")).clear();
		driver.findElement(By.id("EndDate")).sendKeys("10 Sep 2016");
//		WebElement enddate = driver.findElement(By.id("ui-datepicker-div"));  
//		 List<WebElement> rows1=datepicker.findElements(By.tagName("tr"));
//		 //System.out.println(rows.size());
//		List<WebElement> columns1=datepicker.findElements(By.tagName("td"));  
//		// System.out.println(columns1.size());   
//		for (WebElement cell: columns1){  
//		//Select 20th Date   
//		if (cell.getText().equals(EndDate)){  
//		cell.findElement(By.linkText(EndDate)).click();
//		break;
//		 }  
//		} 
		//Thread.sleep(3000);
	
	new Select(driver.findElement(By.id("RateTypeID"))).selectByVisibleText(Duration);
	driver.findElement(By.id("JobDescription")).sendKeys(Summary);
	driver.findElement(By.id("RangeLower")).clear();
	driver.findElement(By.id("RangeLower")).sendKeys(PayRate);
	KeywordAction.screencapture(Constant.Vars);
	driver.findElement(By.id("submitAction")).click();
	Thread.sleep(3000);
	/*new Select(driver.findElement(By.id("AWRJobTypeId"))).selectByVisibleText("H2");
	driver.findElement(By.id("submit2")).click();
	
	
	WebElement we =driver.findElement(By.xpath("//span[@class='fileinput-button btn']"));
	//JavascriptExecutor js = (JavascriptExecutor) driver; 
	//js.executeScript("var evt = document.createEvent('MouseEvents');"+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"+ "arguments[0].dispatchEvent(evt);", we);
		//js.executeScript("arguments[0].click();" , we);
		Actions act = new Actions(driver);
		act.moveToElement(we).click().perform();
		
	*/	
}

public static void findinvoiced(String HiringManager,String Directorate,String ServiceArea,String LocationofVacancy,String JobTitle,String ReasonforVacancy,String StartDate,String EndDate,String PayRate,String Duration,String Summary){
	
	WebElement tableelem =driver.findElement(By.xpath("//table[@class='FormContent']/tbody"));
	List<WebElement> rowcoll =tableelem.findElements(By.tagName("tr"));
	List<WebElement> clmncoll = null;
	int count = 0;
	boolean bflag = false;
	for(int i=0;i<rowcoll.size();i++){
		
		clmncoll=rowcoll.get(i).findElements(By.tagName("td"));
		
		for(int j=0;j<clmncoll.size();j++){
			
			String txt =clmncoll.get(j).getText();
			
			if(txt.contains("Invoiced")){
				if(count == 0){
					count++;
					continue;
				}else{
					count = j;
					bflag = true;
					break;	
				}
			}
		}
		if(bflag)
			break;
		
	}
	if(bflag)
		clmncoll.get(count).findElement(By.id("TimesheetID")).click();
	
	
}
	
}


