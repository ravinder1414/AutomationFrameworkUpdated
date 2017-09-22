package automationFramework;
/********************************************************************************************************
 *Project Name		: Ignite Automation framework 
 *Author		    : Mohammad Sayemul Makki
 *Version	    	: V1.0
 *Date of Creation	: 25-07-2017
 *Date Last modified: 
 *Description       : bean file for storing the object of report
 *Functions			: 
 *
 ********************************************************************************************************
 */

public class ReporterObject {
	private String reportTestStepID;
	private String reportAction;
	private String reportCCellData;
	private String reportDCellData;
	private String reportComments;
	private String reportStrTime;
	private String reportRes_type;
	private String reportException;
	private String reportObjectEvent;
	private String reportObjectTestData;
	private int reporterObjectTestCaseId;
	private String reporterScreenFile;
	private int reportObjectTestStepId; 
	public String getReportTestStepID() {
		return reportTestStepID;
	}
	public void setReportTestStepID(String reportTestStepID) {
		this.reportTestStepID = reportTestStepID;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getReportCCellData() {
		return reportCCellData;
	}
	public void setReportCCellData(String reportCCellData) {
		this.reportCCellData = reportCCellData;
	}
	public String getReportDCellData() {
		return reportDCellData;
	}
	public void setReportDCellData(String reportDCellData) {
		this.reportDCellData = reportDCellData;
	}
	public String getReportComments() {
		return reportComments;
	}
	public void setReportComments(String reportComments) {
		this.reportComments = reportComments;
	}
	public String getReportStrTime() {
		return reportStrTime;
	}
	public void setReportStrTime(String reportStrTime) {
		this.reportStrTime = reportStrTime;
	}
	public String getReportRes_type() {
		return reportRes_type;
	}
	public void setReportRes_type(String reportRes_type) {
		this.reportRes_type = reportRes_type;
	}
	public String getReportException() {
		return reportException;
	}
	public void setReportException(String reportException) {
		this.reportException = reportException;
	}
	public String getReportObjectEvent() {
		return reportObjectEvent;
	}
	public void setReportObjectEvent(String reportObjectEvent) {
		this.reportObjectEvent = reportObjectEvent;
	}
	public String getReportObjectTestData() {
		return reportObjectTestData;
	}
	public void setReportObjectTestData(String reportObjectTestData) {
		this.reportObjectTestData = reportObjectTestData;
	}
	public int getReporterObjectTestCaseId() {
		return reporterObjectTestCaseId;
	}
	public void setReporterObjectTestCaseId(int reporterObjectTestCaseId) {
		this.reporterObjectTestCaseId = reporterObjectTestCaseId;
	}
	public String getReporterScreenFile() {
		return reporterScreenFile;
	}
	public void setReporterScreenFile(String reporterScreenFile) {
		this.reporterScreenFile = reporterScreenFile;
	}
	public int getReportObjectTestStepId() {
		return reportObjectTestStepId;
	}
	public void setReportObjectTestStepId(int reportObjectTestStepId) {
		this.reportObjectTestStepId = reportObjectTestStepId;
	}
}