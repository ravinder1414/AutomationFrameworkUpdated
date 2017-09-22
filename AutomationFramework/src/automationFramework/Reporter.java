package automationFramework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utility.Constant;
import utility.ImageToVideoConvertor;
import utility.Log;

public class Reporter {
	public static void ReportEvent(String Res_type)	throws IOException {}
	public static void ReportEvent(String Res_type,Exception e)	throws IOException {}
	/*********************************************************************************************************
	 * Function:ReportEvent
	 * Description: This function is used for setup a report for summary of execution
	 /*************************************2*******************************************************************
	 * @return 
	 * @throws IOException 
	 */
	public static void ReportEvent(String Res_type, String ObjectEvent,
			String ObjectTestData) throws IOException // for Adding the values
	// instead variable names in
	// the report
	{
		String TestStepID = Constant.Vars.getTestStepID() + "_" + Constant.Vars.row + "-" + Constant.Vars.act;
		String Action =Constant.Vars.getAction(); 
		String cCellData = Constant.Vars.getObj() + " " + Constant.Vars.getObjProp();
		String dCellData = Constant.Vars.getEvent() + " " + Constant.Vars.getTestdata();
		String comments = Constant.Vars.sTestStep;
		try {
			String str_time;
			// String[] str_rep = new String[2];
			Date exec_time = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			str_time = dateFormat.format(exec_time);
			if (Constant.Vars.reporttype == 1) {
			} else {
			}
			if (Res_type.startsWith(Constant.Executed)
					&& ObjectEvent.equalsIgnoreCase(Constant.Page)) {
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ dCellData + ": " + ObjectEvent
						+ " ; "
						+ ObjectTestData
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = GREEN>"
						+ "Passed" + "</font></td></tr>");
				 Constant.Vars.ExecutionStatus = "Failed";
			} else if (Res_type.startsWith(Constant.Executed)) {
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " : "
						+ ObjectTestData
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = GREEN>"
						+ "Passed" + "</font></td></tr>");
			} else if (Res_type.startsWith(Constant.NoWindowFound)
					&& ObjectEvent.equalsIgnoreCase("page")) {
				 Constant.Vars.ExecutionStatus = "Failed";
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " ; "
						+ ObjectTestData + "<font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number: "
									+ ". Description: The Window not Found</div></font>"
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = RED>"
						+ "<a href='https://impellam.spiraservice.net/30/TestStep/" + Constant.Vars.getTestStepID() + ".aspx'>" + "Failed" + "</a>" + "</font></td></tr>");
				//Constant.Vars.bw.write("<TR COLS=6><th colspan= 6 BGCOLOR=#EEEEEE width=5%><font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number: "
				//		+ TestStepID
				//		+ ". Description: The Window not Found</div></th></font></tr>");
				 Constant.Vars.ExecutionStatus = "Failed";
			}

			else if (Res_type.startsWith(Constant.Failed)) {
				Constant.Vars.exeStatus = "Failed,";
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " : "
						+ ObjectTestData 
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = RED>"
						+ "<a href='https://impellam.spiraservice.net/30/TestStep/" + Constant.Vars.getTestStepID() + ".aspx'>" + "Failed" + "</a>" + "</font></td></tr>");
				 Constant.Vars.ExecutionStatus = "Failed";
			} else if (Res_type.startsWith(Constant.Loop)) {

				Constant.Vars.bw1.write("<TR COLS=6><th colspan= 6 BGCOLOR=#EEEEEE width=5%><font FACE='WINGDINGS 2' SIZE=3 color=BLUE><div align=left></font><font FACE=VERDANA SIZE=2 color = BLUE>"
						+ Res_type + "</div></th></font></tr>");
				Constant.Vars.ExecutionStatus = "Failed,";
			} else if (Res_type.startsWith(Constant.Missing)) {
				 Constant.Vars.ExecutionStatus = "Failed";
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " : "
						+ ObjectTestData + "<font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number "
									+ ". Description: The Datatable column name not found</div></font>"
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = RED>"
						+ "<a href='https://impellam.spiraservice.net/30/TestStep/" + Constant.Vars.getTestStepID() + ".aspx'>" + "Failed" + "</a>" + "</font></td></tr>");
			//	Constant.Vars.bw.write("<TR COLS=6><th colspan= 6 BGCOLOR=#EEEEEE width=5%><font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number "
			//			+ TestStepID
			//			+ ". Description: The Datatable column name not found</div></th></font></tr>");
				 Constant.Vars.ExecutionStatus = "Failed";
			} else if (Res_type.startsWith(Constant.ObjectLocator)) {
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " : "
						+ ObjectTestData + "<font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number "
									+ ". Description: Object Locator is wrong or not supported. Supported Locators are Id,Name,Xpath& CSS</div></font>"
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = RED>"
						+ "<a href='https://impellam.spiraservice.net/30/TestStep/" + Constant.Vars.getTestStepID() + ".aspx'>" + "Failed" + "</a>" + "</font></td></tr>");
			//	Constant.Vars.bw.write("<TR COLS=6><th colspan= 6 BGCOLOR=#EEEEEE width=5%>|<font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in Keyword test step number "
			//			+ TestStepID
			//			+ ". Description: Object Locator is wrong or not supported. Supported Locators are Id,Name,Xpath& CSS</div></th></font></tr>");
				
				 Constant.Vars.ExecutionStatus = "Failed";
			} else if (Res_type.startsWith(Constant.Property)) {
				Constant.Vars.bw1.write("<TR COLS=6><td BGCOLOR=#EEEEEE width=5%><font FACE=VERDANA SIZE=2>"
						+ "<a href='" + Constant.Vars.filenamer + "'>" + TestStepID + "</a>" 
						+ "</font></td><td BGCOLOR=#EEEEEE width=17%><font FACE=VERDANA SIZE=2>"
						+ Action
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ cCellData
						+ "</font></td></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ ObjectEvent
						+ " : "
						+ ObjectTestData + "<font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in test step number "
								+ ". This property is not supported by Ignite</div></font>"
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ comments
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2>"
						+ str_time
						+ "</font></td><td BGCOLOR=#EEEEEE width=30%><font FACE=VERDANA SIZE=2 color = RED>"
						+ "<a href='https://impellam.spiraservice.net/30/TestStep/" + Constant.Vars.getTestStepID() + ".aspx'>" + "Failed" + "</a>" + "</font></td></tr>");
			//	Constant.Vars.bw.write("<TR COLS=6><th colspan= 6 BGCOLOR=#EEEEEE width=5%><font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>Error Occurred in test step number "
			//			+ TestStepID
			//			+ ". This property is not supported by Ignite</div></th></font></tr>");
				 Constant.Vars.ExecutionStatus = "Failed";
			}

		} catch (Exception e1) {
			System.out
			.println("Report will not be printed. Check the file path."
					+ e1);
		}
	}
	
	/*********************************************************************************************************
	 * Function:ReportEvent
	 * Description: This function is used for setup a report for summary of execution
	 /********************************************************************************************************
	 * @return 
	 * @throws IOException 
	 */
	public static void ReportEvent(LocalTC Vars, ReporterSummaryObject reporterObjSum) throws IOException
	{
			if (reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Failed)) {
				/*strResultPath pointing towards Detail report*/	   
				// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
				
				Vars.bw1.write("<tr><td class=\"width73 bold\">"
						+ "<a href=#" + reporterObjSum.getReportSummaryTestCaseID() + ">"+ reporterObjSum.getReportSummaryTestCaseID() + ":" + reporterObjSum.getReportSummaryTestCaseName() +"</a>"
						+ "</td>"
						+ "<td class=\"width27 red\">"
						+ reporterObjSum.getReportSummaryTestCaseStatus() + "</td></tr>");
				
			} else if(reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Passed)){
				/*strResultPath pointing towards Detail report*/	   
				// 2016-03-28 Text color of a status(Pass) result of the　Summary report was corrected.
				// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
				
				Vars.bw1.write("<tr><td class=\"width73 bold\">"
						+ "<a href=#" + reporterObjSum.getReportSummaryTestCaseID() + ">"+ reporterObjSum.getReportSummaryTestCaseID() + ":" + reporterObjSum.getReportSummaryTestCaseName() +"</a>"
						+ "</td>"
						+ "<td class=\"width27 green\">"
						+ reporterObjSum.getReportSummaryTestCaseStatus() + "</td></tr>");
				
			}else if(reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Blocked)){
				/*strResultPath pointing towards Detail report*/	   
				// 2016-03-28 Text color of a status(Blocked) result of the　Summary report was corrected.
				// 2016-03-28 A pass of a reference previous HTML was corrected in a relative path.
				
				Vars.bw1.write("<tr><td class=\"width73 bold\">"
						+ "<a href=#" + reporterObjSum.getReportSummaryTestCaseID() + ">"+ reporterObjSum.getReportSummaryTestCaseID() + ":" + reporterObjSum.getReportSummaryTestCaseName() +"</a>"
						+ "</td>"
						+ "<td class=\"width27 blocked\">"
						+ reporterObjSum.getReportSummaryTestCaseStatus() + "</td></tr>");
			}
		//}
		
	}
	/*********************************************************************************************************
	 * Function:detailReportEvent
	 * Description: This function is used for setup a report for details test steps 
	 /********************************************************************************************************
	 * @return 
	 * @throws IOException 
	 */
	public static void detailReportEvent(LocalTC Vars) throws IOException {
		String reportStatus = null;
		String description = null;
		try {
			for (String testSetbrowserName : Vars.getReportMap().keySet()) {
				String testSetName = testSetbrowserName.split("&#")[0];
				String browserName = testSetbrowserName.split("&#")[1];
				ArrayList<ReporterSummaryObject> reporterSumObjList = (ArrayList<ReporterSummaryObject>) Vars
						.getReportMap().get(testSetbrowserName);
				Vars.bw1.write(
						"<tr><td><div class=headertext1>Release Iteration with " + browserName + "</div></td></tr>");
				Vars.bw1.write("</br>");
				Vars.bw1.write(
						"<tr><td><div class=\"headertext2 bold\">TestSet Name - " + testSetName + "</div></td></tr>");
				for (ReporterSummaryObject reporterObjSum : reporterSumObjList) {

					String reportTestCaseName = reporterObjSum.reportSummaryTestCaseName;
					Vars.bw1.write("<tr><td id=" + reporterObjSum.getReportSummaryTestCaseID() + " class=\"bold blue\">"
							+ reporterObjSum.getReportSummaryTestCaseID() + ":"
							+ reporterObjSum.getReportSummaryTestCaseName() + "</td></tr>");
					Vars.bw1.write("<tr><td>");
					Vars.bw1.write("<table colspan=7 border=0 cellpadding=3 cellspacing=1 class=\"reporttable\">");
					Vars.bw1.write("<tr><th class=auto-style1>Test StepID</th>" + "<th class=auto-style2>Action</th>"
							+ "<th class=auto-style3>Object</th>" + "<th class=auto-style4>Actual Result</th>"
							+ "<th class=auto-style5>Test Step</th>" + "<th class=auto-style6>Execution Time</th>"
							+ "<th class=auto-style7>Status</th></tr>");
					for (String testCaseName : reporterObjSum.getReportObjMap().keySet()) {
						ArrayList<ReporterObject> reportObjList = (ArrayList<ReporterObject>) reporterObjSum
								.getReportObjMap().get(testCaseName);

						if (reportTestCaseName.equals(testCaseName)) {
							for (ReporterObject reportObj : reportObjList) {
								String strReportScrenShotFile = reportObj.getReportTestStepID();
								String[] strOptions = Vars.getStrReportStatusConf().split("#d;b@");
								// System.out.println(strOptions.length);
								if (strOptions[0].equalsIgnoreCase("yes")
										&& reportObj.getReportRes_type().equalsIgnoreCase(Constant.Executed)) {
									strReportScrenShotFile = "<a href='" + reportObj.getReporterScreenFile()
											+ "' target=\"_blank\">" + reportObj.getReportTestStepID() + "</a>";
								} else if (strOptions[1].equalsIgnoreCase("yes")
										&& (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Failed)
												|| reportObj.getReportRes_type()
														.equalsIgnoreCase(Constant.NoBlankAvailable)
												|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action1)
												|| reportObj.getReportRes_type()
														.equalsIgnoreCase(Constant.NoBlankAvailable)
												|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action1))) {
									strReportScrenShotFile = "<a href='" + reportObj.getReporterScreenFile()
											+ "' target=\"_blank\">" + reportObj.getReportTestStepID() + "</a>";
								} else if (strOptions[2].equalsIgnoreCase("yes") && (reportObj.getReportRes_type()
										.equalsIgnoreCase(Constant.NoWindowFound)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionfnf)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionff)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectLocator)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.TooManyArguments)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectNotFound)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Keyword)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nodatatable)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Objectmiss)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property1)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate1)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Prevmonth)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nextmonth)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titlemonth)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titleyear)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titledefault)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Monthnotidentified)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invalidmonth)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound1)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound2)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Calendaraction)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Getcelldata)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nofetchdata)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoColumnFound)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoMatchinDataTable)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectNotFound)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.InvalidQuery)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.InvalidConnection)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Loop)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionstart)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionend)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Screenshot)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Blocked))) {
									strReportScrenShotFile = "<a href='" + reportObj.getReporterScreenFile()
											+ "' target=\"_blank\">" + reportObj.getReportTestStepID() + "</a>";
								} else if (strOptions[3].equalsIgnoreCase("yes")
										&& reportObj.getReportRes_type().equalsIgnoreCase(Constant.CondFailed)) {
									strReportScrenShotFile = "<a href='" + reportObj.getReporterScreenFile()
											+ "' target=\"_blank\">" + reportObj.getReportTestStepID() + "</a>";
								} else if (strOptions[4].equalsIgnoreCase("yes")
										&& (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Missing)
												|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Userdefined)
												|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Caution))) {
									strReportScrenShotFile = "<a href='" + reportObj.getReporterScreenFile()
											+ "' target=\"_blank\">" + reportObj.getReportTestStepID() + "</a>";
								}
								if(Constant.Vars.fIntegration){
									reportStatus = "<a href='https://impellam.spiraservice.net/30/TestStep/"
											+ reportObj.getReportObjectTestStepId()
											+ ".aspx'";
								}else{
									reportStatus ="<a href='" +Constant.Vars.sTestRunPath+ "'"; 
								}

								if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Executed)) {
									if (reportObj.getReportObjectEvent().equalsIgnoreCase(Constant.Page)) {
										reportObj.setReportDCellData(reportObj.getReportObjectEvent() + " ; "
												+ reportObj.getReportObjectTestData());
									}
									Constant.Vars.bw1.write("<tr>" + "<td class=\"auto-style1 blue\">" + strReportScrenShotFile // teststepid
											+ "</td><td class=auto-style2>" + reportObj.getReportAction() // Constant.Vars.action
											+ "</td><td class=auto-style3>" + reportObj.getReportCCellData() // Constant.Vars.obj
																												// =
																												// Constant.Vars.objprop
											+ "</td><td class=auto-style4>" + reportObj.getReportDCellData() // Constant.Vars.events
																												// =
																												// Constant.Vars.Tesdata
											+ "</td><td class=auto-style5>" + reportObj.getReportComments() // TestStep
																											// SpiraTest
											+ "</td><td class=auto-style6>" + reportObj.getReportStrTime() // Execution
																											// Time
											+ "</td><td class=\"auto-style7 green\">" + "Passed" + "</td></tr>");
									Constant.Vars.ExecutionStatus = "Passed";
								} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Failed)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoBlankAvailable)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action1)) {
									if (null != reportObj.getReportException()
											&& !reportObj.getReportException().isEmpty()) {
										description = reportObj.getReportException();
									} else {
										description = "Error Occurred";
									}
									if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoBlankAvailable)) {
										description = "Error @ Line NO:  "
												+ ". No Blank Value is available in the Element";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action1)) {
										description = "Error Occurred in  test step number. "
												+ " : The action is not supported for this type of object";
									}
									Constant.Vars.bw1.write("<tr>" + "<td class=\"auto-style1 blue\">" + strReportScrenShotFile + "</td><td class=auto-style2>"
											+ reportObj.getReportAction() + "</td><td class=auto-style3>"
											+ reportObj.getReportCCellData() + "</td><td class=auto-style4>"
											+ reportObj.getReportDCellData()
											+ " <font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>"
											+ description + "</div></font>" + "</td><td class=auto-style5>"
											+ reportObj.getReportComments() + "</td><td class=auto-style6>"
											+ reportObj.getReportStrTime() + "</td><td class=\"auto-style7 red\">"
											+ reportStatus + "class=red target=\"_blank\">"
											+ "Failed" + "</a>" + "</td></tr>");
									Constant.Vars.ExecutionStatus = "Failed";
								} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Missing)
										|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Userdefined) || reportObj.getReportRes_type().equalsIgnoreCase(Constant.Caution)) {
									if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Userdefined)) {
										description = "Error Occurred in  test step number. "
												+ "Function is not executed. Error exists in  User Defined function. Correct the User Defined Function";
									} else {
										description = "Error Occurred in Keyword test step number "
												+ ".Description: The Datatable column name not found";
									}
									Constant.Vars.bw1.write("<tr>" + "<td class=\"auto-style1 blue\">" + strReportScrenShotFile + "</td><td class=auto-style2>"
											+ reportObj.getReportAction() + "</td><td class=auto-style3>"
											+ reportObj.getReportCCellData() + "</td><td class=auto-style4>"
											+ reportObj.getReportDCellData()
											+ " <font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>"
											+ description + "</div></font>" + "</td><td class=auto-style5>"
											+ reportObj.getReportComments() + "</td><td class=auto-style6>"
											+ reportObj.getReportStrTime() + "</td><td class=\"auto-style7 caution\">"
											+ reportStatus + "calss=caution target=\"_blank\">" + "Caution" + "</a>"
											+ "</td></tr>");
									Constant.Vars.ExecutionStatus = Constant.Caution;

								} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.CondFailed)) {
									description = "Condition Failed. Statements within the condition and End Condition will not be executed";
									Constant.Vars.bw1.write("<tr>" + "<td class=\"auto-style1 blue\">" + strReportScrenShotFile + "</td><td class=auto-style2>"
											+ reportObj.getReportAction() + "</td><td class=auto-style3>"
											+ reportObj.getReportCCellData() + "</td><td class=auto-style4>"
											+ reportObj.getReportDCellData()
											+ " <font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>"
											+ description + "</div></font>" + "</td><td class=auto-style5>"
											+ reportObj.getReportComments() + "</td><td class=auto-style6>"
											+ reportObj.getReportStrTime() + "</td><td class=\"auto-style7 notrun\">"
											+ reportStatus + "class=notrun target=\"_blank\">"
											+ "Not Run" + "</a>" + "</td></tr>");
									Constant.Vars.ExecutionStatus = "Not Run";
								} else {
									if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoWindowFound)) {
										if (reportObj.getReportObjectEvent().equalsIgnoreCase(Constant.Page)) {
											reportObj.setReportDCellData(reportObj.getReportObjectEvent() + " ; "
													+ reportObj.getReportObjectTestData());
										}
										description = "Error Occurred in Keyword test step number: Description: The Window not Found";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionfnf)) {
										description = "Error Occurred in Keyword test step number: "
												+ ". Description: The Action(TestScript) name given is not available in the given path. Check the file path and action name.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionff)) {
										description = "Error Occurred in Keyword test step number: "
												+ ". Description: The Action(TestScript) format is not in supported. Give the file name with xlsx extension only.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectLocator)) {
										description = "Error Occurred in Keyword test step number "
												+ ". Description: Object Locator is wrong or not supported. Supported Locators are Id,Name,Xpath& CSS";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.TooManyArguments)) {
										description = "WARNING @ Line NO:  "
												+ ". Pass only one argument if you are using data or environment variables. Otherwise only the first variable will be considered";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.ObjectNotFound)) {
										description = "Error Occurred in test step number "
												+ ". Object with the given object name is not found in objectrepository";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Keyword)) {
										description = "Error Occurred in  test step number. "
												+ ": Keyword not supported by Ignite";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nodatatable)) {
										description = "Error Occurred in  test step number. "
												+ "Data table not available, Ensure whether you have imported the right Data Table.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action)) {
										description = "Error Occurred in  test step number. "
												+ " : Action not supported by Ignite";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Objectmiss)) {
										description = "Error Occurred in  test step number "
												+ ".Object not found in the page";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property)) {
										description = "Error Occurred in  test step number. "
												+ "Property not supported by Ignite";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property1)) {
										description = "Error Occurred in  test step number. "
												+ "Property not supported for this kind of object";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate1)) {
										description = "Error Occurred in  test step number. "
												+ "Invalid date has been given. Give the proper date format(It should be mm-dd-yyyy format) for a valid date in test script.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate)) {
										description = "Error Occurred in  test step number. "
												+ "Given Date cannot be set. Check the date is valid and in allowed range";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Prevmonth)) {
										description = "Error Occurred in  test step number. "
												+ "Element releated to the previous month is not identified in the page. Try with different idenfication class by adding the same in setting.java.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nextmonth)) {
										description = "Error Occurred in  test step number. "
												+ "Element releated to the next month is not identified in the page. Try with different idenfication class by adding the same in setting.java.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titlemonth)) {
										description = "Error Occurred in  test step number. "
												+ "Element releated to the title month is not identified in the page. Try with different idenfication class by adding the same in setting.java.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titleyear)) {
										description = "Error Occurred in  test step number. "
												+ "Element releated to the titleyear is not identified in the page. Try with different idenfication class by adding the same in setting.java.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titledefault)) {
										description = "Error Occurred in  test step number. "
												+ "Element releated to the title year and month  is not identified in the page. Try with different idenfication class by adding the same in setting.java.";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.Monthnotidentified)) {
										description = "Error Occurred in  test step number. "
												+ "Month in the calendar is not identified. English and Japanese character set are supported.";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invalidmonth)) {
										description = "Error Occurred in  test step number. "
												+ "Given month is invalid. Correct the month and retry.";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.FilePathNotFound)) {
										description = "Error Occurred in  test step number. "
												+ "Given file does not exist.Check the File path and File Name.";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.FilePathNotFound1)) {
										description = "Error Occurred in  test step number. "
												+ "Given file path does not exist.Check the File path.";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.FilePathNotFound2)) {
										description = "Error Occurred in  test step number. "
												+ "FilePath is not Given.Give a valid file path";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.Calendaraction)) {
										description = "Error Occurred in  test step number. "
												+ "For SetDate Operation ObjectType Should be calendar and Object Name should start with cal_";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Getcelldata)) {
										description = "Error Occurred in  test step number. "
												+ "Invalid Syntax for getcelldata. Pass Exact number of arguments";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nofetchdata)) {
										description = "Error Occurred in  test step number. "
												+ "Invalid Syntax for Excelfile.  Data can be exported to only .xlsx file";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoColumnFound)) {
										description = "Error Occurred in  test step number. "
												+ "NO Column found in Data Sheet-which mentioned as dt_XXX; Please verify your Data sheet and column";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.NoMatchinDataTable)) {
										description = "Error Occurred in  test step number. "
												+ "NO Matching text found in Data Sheet- for table element which you mentioned in script;";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.ObjectNotFound)) {
										description = "Error Occurred in  test step number. "
												+ "Object details you mentioned in c column of script file, dosen't match with the Object repository";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.InvalidQuery)) {
										description = "Error Occurred in  test step number. "
												+ "Given Query is not Valid , Please enter correct Select query";
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.InvalidConnection)) {
										description = "Error Occurred in  test step number. "
												+ "Not able to Connect to Given Data base, please check the coneection parameters or appropriate Jar files are uploaded";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Loop)) {
										description = reportObj.getReportRes_type();
									} else if (reportObj.getReportRes_type()
											.equalsIgnoreCase(Constant.Callactionstart)) {
										description = " Start of CallAction execution";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionend)) {
										description = " End of CallAction execution";
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Screenshot)) {
										description = reportObj.getReportTestStepID();
									} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Blocked)) {
										description = "Blocked";
									}
									Constant.Vars.bw1.write("<tr>" + "<td class=\"auto-style1 blue\">" + strReportScrenShotFile + "</td><td class=auto-style2>"
											+ reportObj.getReportAction() + "</td><td class=auto-style3>"
											+ reportObj.getReportCCellData() + "</td><td class=auto-style4>"
											+ reportObj.getReportDCellData()
											+ " <font FACE='WINGDINGS 2' SIZE=3 color=RED><div align=left>X </font><font FACE=VERDANA SIZE=2 color = RED>"
											+ description + "</div></font>" + "</td><td class=auto-style5>"
											+ reportObj.getReportComments() + "</td><td class=auto-style6>"
											+ reportObj.getReportStrTime() + "</td><td class=\"auto-style7 blocked\">"
											+ reportStatus+ "class=blocked target=\"_blank\">" + "Blocked" + "</a>"
											+ "</td></tr>");
									Constant.Vars.ExecutionStatus = "Blocked";
								}
							}
						}
					}
					Vars.bw1.write("</table>");
					Vars.bw1.write("</td></tr>");
				}
			}
			Vars.bw1.write("</table></td></tr></table></body></html>");

		} catch (Exception ex) {
			System.out.println("Report will not be printed. Check the file path.  " + ex);
			Log.error("Report will not be printed. Check the file path.  " + ex);
		}
	}
	
	/*********************************************************************************************************
	 * Function:CreateSummaryReport
	 * Description: This function is used to generate a report and setting the path of report  
	 /********************************************************************************************************
	 * @return 
	 * @throws IOException 
	 */
	public static void createSummaryReport(LocalTC Vars) throws IOException {
		Date cur_dt = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);
		String rp = Constant.tempTestReportPath;
		int stepPassed = 0;
		int stepFailed = 0;
		int stepCaution = 0;
		int stepNotrun = 0;
		int stepBlocked = 0;
		String cssData = "";
		String cssPath = new File(Constant.cssPath)
                .getAbsolutePath();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(cssPath));
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			while (line != null) {
				stringBuilder.append(line);
				stringBuilder.append(System.lineSeparator());
				line = bufferedReader.readLine();
			}
			cssData = stringBuilder.toString();
		} finally {
			bufferedReader.close();
		}
		// checks whether the path ends with // '/'
		if (!rp.endsWith("\\")) {
			rp = rp + "\\";
		}
		Vars.setstrResultPath(rp + "Detail");
		String htmlname = rp + "Detail\\" + strTimeStamp + ".html";
		Vars.setDetailReport(strTimeStamp);
		String path = new File(Constant.movieMakerPath)
                .getAbsolutePath();
		String logoPath = new File(Constant.espireLogo).getAbsolutePath();
		if (Vars.fscreenlock != 200) {
			try {
				if (new File(Vars.getstrResultPath().replace("//", "") + "\\" + Vars.getScreenshotTimeStamp()
						+ "\\Screenshot").exists()) {
					ImageToVideoConvertor.ConvertImagesToVideo(
							Vars.getstrResultPath().replace("//", "") + "\\" + Vars.getScreenshotTimeStamp()
									+ "\\Screenshot",
							Vars.getstrResultPath().replace("//", "") + "\\Video_" + Vars.getDetailReport() + ".avi");
				}
			} catch (Exception ex) {
				Log.error("Movie can not create " + ex.getMessage());
			}
		}
		for (String key : Vars.getReportMap().keySet()) {
			ArrayList<ReporterSummaryObject> reporterSumObjList = (ArrayList<ReporterSummaryObject>) Vars.getReportMap()
					.get(key);

			for (ReporterSummaryObject reporterObjSum : reporterSumObjList) {
				reporterObjSum.setReportSummaryScreenShot(Vars.getstrResultPath() + "\\Screenshot");
				String reportTestCaseName = reporterObjSum.reportSummaryTestCaseName;
				if (reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Failed)) {
					Vars.failed++;
				} else if (reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Passed)) {
					Vars.passed++;
				} else if (reporterObjSum.getReportSummaryTestCaseStatus().equalsIgnoreCase(Constant.Blocked)) {
					Vars.blocked++;
				}
				for (String testCaseName : reporterObjSum.getReportObjMap().keySet()) {
					ArrayList<ReporterObject> reportObjList = (ArrayList<ReporterObject>) reporterObjSum
							.getReportObjMap().get(testCaseName);
					if (reportTestCaseName.equals(testCaseName)) {
						for (ReporterObject reportObj : reportObjList) {
							if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Executed)) {
								stepPassed++;
							} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Failed)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoBlankAvailable)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action1)) {
								stepFailed++;
							} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.Missing)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Userdefined) || reportObj.getReportRes_type().equalsIgnoreCase(Constant.Caution)) {
								stepCaution++;
							} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoWindowFound)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionfnf)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionff)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectLocator)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.TooManyArguments)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectNotFound)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Keyword)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nodatatable)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Action)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Objectmiss)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Property1)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate1)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invaliddate)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Prevmonth)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nextmonth)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titlemonth)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titleyear)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Titledefault)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Monthnotidentified)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Invalidmonth)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound1)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.FilePathNotFound2)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Calendaraction)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Getcelldata)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Nofetchdata)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoColumnFound)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.NoMatchinDataTable)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.ObjectNotFound)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.InvalidQuery)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.InvalidConnection)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Loop)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionstart)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Callactionend)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Screenshot)
									|| reportObj.getReportRes_type().equalsIgnoreCase(Constant.Blocked)) {
								stepBlocked++;
							} else if (reportObj.getReportRes_type().equalsIgnoreCase(Constant.CondFailed)) {
								stepNotrun++;
							}
						}
					}
				}
			}
		}

		Vars.bw1 = new BufferedWriter(new FileWriter(htmlname));
		Vars.bw1.write(
				"<html><head><script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>");
		Vars.bw1.write("<style>" + cssData);
		Vars.bw1.write("</style>");
		Vars.bw1.write("</head><body>");
		Vars.bw1.write("<table class=width100>");
		Vars.bw1.write("<tr><td>");
		Vars.bw1.write("<table class=width100>");
		/*Vars.bw1.write("<tr><td><img src=" + logoPath + "></img></td>"
				+ "<td class=\"headertext\">" + Vars.getReportHeader() + "</td>");*/
		Vars.bw1.write("<tr><td><img src=" + Constant.espireLogo + "></img></td>"
				+ "<td class=\"headertext\">" + Vars.getReportHeader() + "</td>");
		
		Vars.bw1.write("</tr></table><hr>");
		Vars.bw1.write("</td></tr>");
		Vars.bw1.write("<tr><td>");
		Vars.bw1.write("<table class=width100>");
		Vars.bw1.write("<tr><td class=width50>");
		Vars.bw1.write("<table cellpadding=3 cellspacing=1>");
		Vars.bw1.write("<tr><td class=\"width50 bold\">Execution Start Time</td><td class=width50>"
				+ Vars.getExecutionStartTime() + "</td></tr>");
		Vars.bw1.write("<tr><td class=\"width50 bold\">Execution End Time</td><td class=width50>"
				+ Vars.getExecutionEndTime() + "</td></tr>");
		Vars.bw1.write("<tr><td class=\"width50 bold\">Total TestCase Executed</td><td class=\"width50 bold\">"
				+ (Vars.passed + Vars.failed + Vars.blocked) + "</td></tr>");
		Vars.bw1.write(
				"<tr><td class=\"width50 green\">Passed</td><td class=\"width50 green\">" + Vars.passed + "</td></tr>");
		Vars.bw1.write(
				"<tr><td class=\"width50 red\">Failed</td><td class=\"width50 red\">" + Vars.failed + "</td></tr>");
		Vars.bw1.write("<tr><td class=\"width50 blocked\">Blocked</td><td class=\"width50 blocked\">" + Vars.blocked
				+ "</td></tr>");
		Vars.bw1.write("<tr><td class=\"width50 releaseid\">Release ID</td><td class=\"width50 releaseid\">"
				+ Vars.getReportReleaseId() + "</td></tr>");
		Vars.bw1.write("</table></td>");
		Vars.bw1.write("<td class=width50>");
		Vars.bw1.write("<table>");
		Vars.bw1.write("<tr><td class=width25>");
		Vars.bw1.write("<script type=\"text/javascript\">");
		Vars.bw1.write("google.charts.load('current', {'packages':['corechart']});");
		Vars.bw1.write("google.charts.setOnLoadCallback(drawChart);");
		Vars.bw1.write("function drawChart() {");
		Vars.bw1.write("var data = new google.visualization.DataTable();");
		Vars.bw1.write("data.addColumn('string', 'Test Status');");
		Vars.bw1.write("data.addColumn('number', 'Count');");
		Vars.bw1.write("data.addRows([");
		Vars.bw1.write("['Passed'," + Vars.passed + "],");
		Vars.bw1.write("['Failed'," + Vars.failed + "],");
		Vars.bw1.write("['Caution'," + Vars.caution + "],");
		Vars.bw1.write("['Not Run'," + Vars.notrun + "],");
		Vars.bw1.write("['Blocked'," + Vars.blocked + "]]);");
		Vars.bw1.write(
				"var options = {'title':'Test Execution Summary Graph',colors: ['#12C909', '#C3100A', '#FE9A2E', '#A6ACAF', '#DAE70B'],is3D: true,");
		Vars.bw1.write("'width':300,");
		Vars.bw1.write("'height':200};");
		Vars.bw1.write("var chart = new google.visualization.PieChart(document.getElementById('chart_div'));");
		Vars.bw1.write("chart.draw(data, options); } </script>");
		Vars.bw1.write("<div id=\"chart_div\"></div>");
		Vars.bw1.write("</td><td class=width25>");
		Vars.bw1.write("<script type=\"text/javascript\">");
		Vars.bw1.write("google.charts.setOnLoadCallback(drawDetailsChart);");
		Vars.bw1.write("function drawDetailsChart() {");
		Vars.bw1.write("var data = new google.visualization.DataTable();");
		Vars.bw1.write("data.addColumn('string', 'Test Execution Detail Graph');");
		Vars.bw1.write("data.addColumn('number', 'Count');");
		Vars.bw1.write("data.addRows([");
		Vars.bw1.write("['Passed'," + stepPassed + "],");
		Vars.bw1.write("['Failed'," + stepFailed + "],");
		Vars.bw1.write("['Caution'," + stepCaution + "],");
		Vars.bw1.write("['Not Run'," + stepNotrun + "],");
		Vars.bw1.write("['Blocked'," + stepBlocked + "]]);");
		Vars.bw1.write(
				"var options = {'title':'Test Step Details Graph',colors: ['#12C909', '#C3100A', '#FE9A2E', '#A6ACAF', '#DAE70B'],is3D: true,");
		Vars.bw1.write("'width':300,");
		Vars.bw1.write("'height':200};");
		Vars.bw1.write("var chart = new google.visualization.PieChart(document.getElementById('detailsChart_div'));");
		Vars.bw1.write("chart.draw(data, options); } </script>");
		Vars.bw1.write("<div id=\"detailsChart_div\"></div>");
		Vars.bw1.write("</td></tr></table>");
		Vars.bw1.write("</td></tr></table><hr></td></tr>");

	}
	/*********************************************************************************************************
	 * Function:CreateReport
	 * Description: This function is used for setup a report variable so that report can be generated 
	 /********************************************************************************************************
	 * @return 
	 * @throws IOException 
	 */
	public static void CreateReport(LocalTC Vars, String Type) throws IOException {
		if (Type == "s") {
			Vars.bw1.write("<tr><td>");
			Vars.bw1.write("<div class=headertext1>Test Execution Summary ( ");
			if(new File(Vars.getstrResultPath().replace("//", "") + "\\" + Vars.getScreenshotTimeStamp()
			+ "\\Screenshot").exists()){
				Vars.bw1.write("<a href='" + Vars.getstrResultPath().replace("//", "") + "\\Vedio_" + Vars.getDetailReport()
				+ ".avi'> Download Video </a> ");
			}
			Vars.bw1.write(" )</div>");
			Vars.bw1.write("</br>");
			for (String testSetbrowserName : Vars.getReportMap().keySet()) {
				String testSetName = testSetbrowserName.split("&#")[0];
				String browserName = testSetbrowserName.split("&#")[1];
				ArrayList<ReporterSummaryObject> reporterSumObjList = (ArrayList<ReporterSummaryObject>) Vars
						.getReportMap().get(testSetbrowserName);
				Vars.bw1.write("<div class=headertext1>Release Iteration with " + browserName + "</div>");
				Vars.bw1.write("</br>");
				Vars.bw1.write("<div class=\"headertext2 bold\">TestSet Name - " + testSetName + "</div>");
				Vars.bw1.write("<table class=\"reporttable width100\" border=0 cellpadding=3 cellspacing=1>");
				Vars.bw1.write("<tr><th class=width73>TestCase Name</th><th class=width27>Status</th></tr>");
				for (ReporterSummaryObject reporterObjSum : reporterSumObjList) {
					ReportEvent(Vars, reporterObjSum);
				}
				Vars.bw1.write("</table>");
			}
			Vars.bw1.write("</td></tr>");
		} else {
			Vars.bw1.write(
					"<tr><td><table><tr><td><div class=headertext1>Test Execution Detail Report</div></td></tr>");
		}
	}
	
	
	/**
	 * @param Vars
	 * @throws Exception 
	 */
	public static void generateReport(LocalTC Vars) {

		String reportTitle = "";
		try {
			if (Vars.fscreenlock != 200) {
				reportTitle = Vars.obj.getReportTitle();
			}
			if (null == reportTitle || reportTitle.equalsIgnoreCase("")) {
				Vars.setReportHeader("Automation Framework");
			} else {
				Vars.setReportHeader(reportTitle);
			}
			createSummaryReport(Vars);
			CreateReport(Vars, "s");
			CreateReport(Vars, "d");
			detailReportEvent(Vars);
			Vars.bw1.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}