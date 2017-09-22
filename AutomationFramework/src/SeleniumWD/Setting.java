package SeleniumWD;
import java.util.List;
import org.openqa.selenium.WebElement;
public class Setting
{
	
	private String utilityFilePath="<<Give the SeleniumUtility Path here Example:D://SeleniumUtility.xls>>";
	private String[] envprevMonth1={"prev","Prev"}; // Specify a class name through which the framework can identify the image representing the previous month
	private String[] envnextMonth1={"next","Next"};// Specify a class name through which the framework can identify the image representing the next month
	private String[] envtitleMonth={"month"};// Specify a class name through which we can identify the title month element in calendar control element
	private String[] envtitleYear={"year"};// Specify a class name through which we can identify the title year element in calendar control element
	public String getUtilityFilePath() {
		return utilityFilePath;
	}
	public void setUtilityFilePath(String utilityFilePath) {
		this.utilityFilePath = utilityFilePath;
	}
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
}
