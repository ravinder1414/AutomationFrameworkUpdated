package automationFramework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/********************************************************************************************************
 *Project Name		: Ignite Automation framework 
 *Author		    : mohammad.makki
 *Version	    	: V1.0
 *Date of Creation	: 17-08-2017
 *Date Last modified: 
 *Description       : bean file for storing the object of summary report
 *Functions			: 
 *
 ********************************************************************************************************
 */
public class ReporterSummaryObject {
	public String reportSummaryTestCaseID;
	public String reportSummaryTestCaseName;
	public String reportSummaryTestCaseStatus;
	public String reportSummaryScreenShot;
	public String reportSummaryTestSetName;
	public String reportSummaryTestSetId;
	//List<ReporterObject> reporterObjList = new ArrayList<>();
	public HashMap<String, List<ReporterObject>> reportObjMap = new HashMap<>();
	
	public ReporterSummaryObject(ReporterSummaryObject clone) {
	    this.reportSummaryTestCaseID = clone.reportSummaryTestCaseID;  
	    this.reportSummaryTestCaseName = clone.reportSummaryTestCaseName;  
	    this.reportSummaryTestCaseStatus = clone.reportSummaryTestCaseStatus;  
	    this.reportSummaryTestSetName = clone.reportSummaryTestSetName;  
	    this.reportSummaryScreenShot = clone.reportSummaryScreenShot;
	    this.reportSummaryTestSetId = clone.reportSummaryTestSetId;
	    this.reportObjMap = clone.reportObjMap;
	  }
	
	public ReporterSummaryObject() {
		reportSummaryTestCaseID = "";
		reportSummaryTestCaseName = "";
		reportSummaryTestCaseStatus = "";
		reportSummaryScreenShot = "";
		reportSummaryTestSetName = "";
		reportSummaryTestSetId = "";
		reportObjMap = new HashMap<>();
	}

	public String getReportSummaryTestCaseID() {
		return reportSummaryTestCaseID;
	}
	public void setReportSummaryTestCaseID(String reportSummaryTestCaseID) {
		this.reportSummaryTestCaseID = reportSummaryTestCaseID;
	}
	public String getReportSummaryTestCaseName() {
		return reportSummaryTestCaseName;
	}
	public void setReportSummaryTestCaseName(String reportSummaryTestCaseName) {
		this.reportSummaryTestCaseName = reportSummaryTestCaseName;
	}
	public String getReportSummaryTestCaseStatus() {
		return reportSummaryTestCaseStatus;
	}
	public void setReportSummaryTestCaseStatus(String reportSummaryTestCaseStatus) {
		this.reportSummaryTestCaseStatus = reportSummaryTestCaseStatus;
	}
	public String getReportSummaryScreenShot() {
		return reportSummaryScreenShot;
	}
	public void setReportSummaryScreenShot(String reportSummaryScreenShot) {
		this.reportSummaryScreenShot = reportSummaryScreenShot;
	}
	public String getReportSummaryTestSetName() {
		return reportSummaryTestSetName;
	}
	public void setReportSummaryTestSetName(String reportSummaryTestSetName) {
		this.reportSummaryTestSetName = reportSummaryTestSetName;
	}
	public String getReportSummaryTestSetId() {
		return reportSummaryTestSetId;
	}
	public void setReportSummaryTestSetId(String reportSummaryTestSetId) {
		this.reportSummaryTestSetId = reportSummaryTestSetId;
	}
	public HashMap<String, List<ReporterObject>> getReportObjMap() {
		if(null == reportObjMap)
			reportObjMap = new HashMap<>();
		return reportObjMap;
	}
	public void setReportObjMap(HashMap<String, List<ReporterObject>> reportObjMap) {
		this.reportObjMap = reportObjMap;
	}
	
}
