package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import com.automation.testcomplete.TestCompleteBean;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import SpiraTest.SpiraReaderTestComplete;
import automationFramework.KeywordLibrary;
import automationFramework.LocalTC;
import automationFramework.ReporterCommon;
import automationFramework.ReporterSummaryObject;
public class ExcelUtils {
	private  XSSFSheet ExcelWSheet;
	private  XSSFWorkbook ExcelWBook;
	private  XSSFCell Cell;
	private  XSSFRow Row;
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\r\n";
	public static ArrayList<String> columnValue = new ArrayList<>();
	
	//CSV file header
	private static final String FILE_HEADER = "Test Set Id, Test Case Id, Test Step id, Test Set Name, Test Case Name, Test Step, Action, Object, ObjectProp, Event, Test Data, Execution Result, Status, Execution Time, Screenshot Path, Report Status, Test Step Number";
	
	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	
	/**
	 * @param Path
	 * @param SheetName
	 * @throws Exception
	 */
	public  void setExcelFile(String Path,String SheetName) throws Exception {
		FileInputStream ExcelFile = null;
		try {
			
			// Open the Excel file
			if (null != Path || !Path.isEmpty()) {
				ExcelFile = new FileInputStream(Path);
				// Access the required test data sheet
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				ExcelWSheet = ExcelWBook.getSheet(SheetName.trim().replace("\\n", "").trim());
			}
		} catch (Exception e){
			e.getCause().printStackTrace();
			Log.info("error message"+e.getMessage());
			throw (e);
		}finally {
			if(null != ExcelFile){
				ExcelFile.close();
			}
		}
	}

	
	//This method returns number of rows in the sheet
	/**
	 * @return
	 */
	public int getRowCount() {
		int rowCount = 0;
		try {
			if (null != ExcelWSheet) {
				rowCount = ExcelWSheet.getLastRowNum() + 1;
				Log.info(" no of rows from excel utility..." + rowCount);
			}
			return rowCount;
		} catch (Exception e) {
			Log.info("error message.." + e.getMessage());
			throw (e);
		}
	}
	
		//This method returns number of rows in the sheet
		public  int getColCount()
	{
				try{
					int ColCount=ExcelWSheet.getRow(1).getLastCellNum();
					Log.info(" no of cols from excel utility..."+ColCount);
					return ColCount;
		}catch (Exception e){
			Log.info("error message.."+e.getMessage());
		throw(e);
		}	
	}
		
	// This function is return the getnumaric cell value.it is use for take the test case ID.
	public  int getNumaricCellData(int RowNum, int ColNum) throws Exception{
		int  CellData = 0;
		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		switch (Cell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				CellData = -1;
			break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				CellData = -1;
			break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				CellData = (int)Cell.getNumericCellValue();
			break;
			case XSSFCell.CELL_TYPE_BLANK:
				CellData = 0;
			break; 
			case XSSFCell.CELL_TYPE_FORMULA:
				CellData = -1;
			break; 
		}
			return CellData;
		}catch (Exception e){
			Log.info("error message.."+e.getMessage());
			return -1;
		}
	}

	//.......................................................................................
	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public  String getCellData(int RowNum, int ColNum) throws Exception{
		String CellData = "";
		try {
			
				Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
				if(null == Cell){
					return CellData;
				}
				Cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
				switch (Cell.getCellType()) {
				
				case XSSFCell.CELL_TYPE_STRING:
					CellData = Cell.getStringCellValue();
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					CellData = "";
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					 if (HSSFDateUtil.isCellDateFormatted(Cell))
						 CellData = Cell.getDateCellValue()+"";
					 else
						 CellData = BigDecimal.valueOf(Cell.getNumericCellValue()).toPlainString();
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					CellData = "";
					break;
				case XSSFCell.CELL_TYPE_FORMULA:
					CellData = Cell.getCellFormula();
					break;
				}
				
			/* else {
				return null;
			}*/
			return CellData;
		}catch (Exception e){
			Log.info("error message.."+e.getMessage());
			return"";
		}
	}

	//This method is to write in the Excel cell, Row num and Col num are the parameters
	public  void setCellData(LocalTC vars,  int RowNum, int ColNum) throws Exception	{
		try{
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(vars.ResultStatus);
			} else {
				Cell.setCellValue(vars.ResultStatus);
			}
			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.Path_TestData + Constant.File_TestData);
			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}catch(Exception e){
			throw (e);
		}
	}

	public static void updateExcellSheet(LocalTC Vars) throws Exception {
		
		try {
			FileInputStream file = null;
			FileOutputStream outFile = null;
			if(Vars.fscreenlock == 300){
				file = new FileInputStream(Vars.getsTestCaseRunPath());
			}else
				file = new FileInputStream(Vars.sTestRunPath);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Test Runs");
			XSSFCell cell = null;
			// Update the value of cell
			if(Vars.loopReportFlag){
				cell = sheet.getRow(Vars.looprow+2).getCell(9);
				if (cell == null) {
					cell = sheet.getRow(Vars.looprow+2).createCell((short) 9);
				}
			}else{
				cell = sheet.getRow(Vars.row+2).getCell(9);
				if (cell == null) {
					cell = sheet.getRow(Vars.row+2).createCell((short) 9);
				}
			}
			cell.setCellValue(Vars.ResultStatus);
			cell = sheet.getRow(Vars.testcasestart).getCell(9);
			if (cell == null) {
				cell = sheet.getRow(Vars.testcasestart).createCell((short) 9);
			}
			cell.setCellValue(Vars.TestCaseStatus);
			if(Vars.loopReportFlag){
				cell = sheet.getRow(Vars.looprow+2).getCell(10);
				if (cell == null) {
					cell = sheet.getRow(Vars.looprow+2).createCell((short) 10);
				}
			}else{
				cell = sheet.getRow(Vars.row+2).getCell(10);
				if (cell == null) {
					cell = sheet.getRow(Vars.row+2).createCell((short) 10);
				}
			}
			cell.setCellValue(Vars.getActualResult());
			Vars.getResultsStatus().add(Vars.ResultStatus);
			file.close();
			if(Vars.fscreenlock == 300){
				outFile = new FileOutputStream(new File(Vars.getsTestCaseRunPath()));
			}else
				outFile = new FileOutputStream(new File(Vars.sTestRunPath));
			workbook.write(outFile);
			outFile.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * @return boolean
	 */
	// reading pdf/excel file from the local machine
	public static boolean readDataFromFile(String filePath, int row, int col, String ObjectEventCh, String ObjectValCh, String strContain) throws Exception {
		FileInputStream ExcelFile = null;
		String filename = "";
		boolean bflag = false;
		String extension = null;
		if (null == filePath || filePath.isEmpty()) {
			filePath = lastFileModified(Constant.tempTestReportPath, filename);
		}
		//filePath = "C:\\temp\\" + latestFileName;
		int i = filePath.lastIndexOf('.');
		if (i > 0) {
		    extension = filePath.substring(i+1);
		}
		if (extension.equalsIgnoreCase("pdf")) {
			PDFTextStripper pdfStripper = null;
			PDDocument pdDoc = null;
			try {
			    pdDoc = PDDocument.load(new File(filePath));
			    pdfStripper = new PDFTextStripper();
			    String parsedText = pdfStripper.getText(pdDoc);
			    bflag = Arrays.asList(parsedText.split(" ")).contains(ObjectValCh);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pdDoc.close();
			}
		} else if(extension.equalsIgnoreCase("csv")){
				String line = "";
		        String cvsSplitBy = ",";
				try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
					int count = 0;
		            while ((line = br.readLine()) != null) {
		            	count++;
		                // use comma as separator
		                String[] csvValue = line.split(cvsSplitBy); //split by separator to each line
		                if((row > 0 && !(csvValue.length < row)) || (col > 0 && !(csvValue.length < col))){
		                if(count == row){
							for (String strData : csvValue) {
								if (strContain.equalsIgnoreCase("contain")) {
									if (strData.replaceAll("\"", "").contains(ObjectValCh)) {
										bflag = true;
										break;
									}
								} else {
									if (strData.replaceAll("\"", "").equals(ObjectValCh)) {
										bflag = true;
										break;
									}
								}
							}
						} else if (col > 0) {
							if (strContain.equalsIgnoreCase("contain")) {
								if (csvValue[col - 1].replaceAll("\"", "").contains(ObjectValCh)) {
									bflag = true;
									break;
								}
							} else {
								if (csvValue[col - 1].replaceAll("\"", "").equals(ObjectValCh)) {
									bflag = true;
									break;
								}
							}
		                }
		                }
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			}else{
			try {
				XSSFSheet ExcelSheet;
				XSSFWorkbook ExcelBook;
				XSSFRow rw;
				// Open the Excel file
				ExcelFile = new FileInputStream(filePath);
				// Access the required test data sheet
				ExcelBook = new XSSFWorkbook(ExcelFile);
				ExcelSheet = ExcelBook.getSheetAt(0);
				int rows = ExcelSheet.getLastRowNum() + 1;
				int cols = ExcelSheet.getRow(1).getLastCellNum();
				int countxl = 0;
				if (row > 0 && row <= rows) {
					rw = ExcelSheet.getRow(row-1);
					Iterator<Cell> itr = rw.cellIterator();
					while (itr.hasNext()) {
						XSSFCell cellValue = (XSSFCell) itr.next();
						bflag = getCellData(ObjectEventCh, ObjectValCh, cellValue, strContain);
						if (bflag)
							break;
					}
				}
				if (col > 0) {
					Iterator<Row> itrRow = ExcelSheet.iterator();
					while (itrRow.hasNext()) {
						rw = (XSSFRow) itrRow.next();
						Iterator<Cell> cells = rw.cellIterator();
						while (cells.hasNext()) {
							XSSFCell cellValue = (XSSFCell) cells.next();
							if (cellValue.getColumnIndex() == col-1) {
								bflag = getCellData(ObjectEventCh, ObjectValCh, cellValue, strContain);
								break;
							}

						}
						if(bflag)
							break;
					}
				}
			} catch (Exception e) {
				Log.info("error message" + e.getMessage());
				throw (e);
			} finally {
				if (null != ExcelFile) {
					ExcelFile.close();
				}
			}
			}
		
		return bflag;

	}

	/**
	 * @param ObjectEventCh
	 * @param ObjectValCh
	 * @param cellValue
	 * @param strContain
	 * @return
	 * returning the cell data from the sheet
	 */
	public static boolean getCellData(String ObjectEventCh, String ObjectValCh, XSSFCell cellValue, String strContain) {
		boolean bflag = false;
		if (!ObjectEventCh.isEmpty() && !ObjectValCh.isEmpty()) {
			if (Integer.parseInt(ObjectEventCh) <= Integer.parseInt(cellValue.getStringCellValue())) {
				if (Integer.parseInt(cellValue.getStringCellValue()) <= Integer.parseInt(ObjectValCh)) {
					bflag = true;
				}
			}
		} else if (!ObjectEventCh.isEmpty() && ObjectValCh.isEmpty()) {
			if(strContain.equalsIgnoreCase("contain")){
				if(cellValue.getStringCellValue().contains(ObjectEventCh)){
					bflag = true;
				}
			}else if(ObjectEventCh.equals(cellValue.getStringCellValue())){
				bflag = true;	
			}
		} else if (ObjectEventCh.isEmpty() && !ObjectValCh.isEmpty()) {
			if(strContain.equalsIgnoreCase("contain")){
				if(cellValue.getStringCellValue().contains(ObjectValCh)){
					bflag = true;
				}
			}else if(ObjectValCh.equals(cellValue.getStringCellValue())){
				bflag = true;	
			}
		}
		return bflag;
	}

	/**
	 * @param dir
	 * @param fileNameVariable
	 * @return
	 * last downloaded file in the given path
	 */
	
	public static String lastFileModified(String dir, String fileNameVariable) {
	    File fl = new File(dir);
	    File[] files = fl.listFiles();
	    String extension = "";
	    if (files == null || files.length == 0) {
	        return null;
	    }
	    File lastModifiedFile = files[0];
    	int i = lastModifiedFile.toString().lastIndexOf('.');
        	if (i > 0) {
			    extension = lastModifiedFile.toString().substring(i+1);
			}
        	if(!extension.isEmpty() && (extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("xlsx"))){
        		if(null != fileNameVariable && !fileNameVariable.isEmpty())
			    	Constant.Vars.setVariableData(fileNameVariable, lastModifiedFile.getName());
        	}
	    for (File file : files) {
	    	i = file.toString().lastIndexOf('.');
	        if (lastModifiedFile.lastModified() < file.lastModified()) {
	        	if (i > 0) {
				    extension = file.toString().substring(i+1);
				}
	        	if(!extension.isEmpty() && (extension.equalsIgnoreCase("csv") || extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("xlsx"))){
	        		lastModifiedFile = file;
	        		if(null != fileNameVariable && !fileNameVariable.isEmpty())
				    	Constant.Vars.setVariableData(fileNameVariable, lastModifiedFile.getName());
	        	}
	        }
	    }
	    //Constant.Vars.setVariableData("file_extention", lastModifiedFile.toString());
	    Constant.Vars.setVariableData("file_extention", extension);
	    return lastModifiedFile.toString();
	}
	
	/**
	 * @param Vars
	 */
	public static void writeCSVForTestComplete(LocalTC Vars) {
		FileWriter fileWriter = null;
		try {

			if (!Constant.tempTestReportPath.endsWith("\\")) { // checks whether
																// the path ends
																// with
				// '/'
				Constant.tempTestReportPath = Constant.tempTestReportPath + "\\";
			}
			String fileName = Constant.tempTestReportPath + "TestComplete.csv";
			if(new File(fileName).exists()){
				new File(fileName).delete();
			}
			fileWriter = new FileWriter(fileName);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			// Write a new student object list to the CSV file
			for (TestCompleteBean testCompleteObj : Vars.getTestCompleteList()) {
				
				fileWriter.append(testCompleteObj.getTestSetId());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestCaseId());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestStepID());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestSetName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestCaseName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestStep());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getAction());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getObj());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getObjProp());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getEvent());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getTestdata());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getExecutionResult());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getStatus());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getExecutionTime());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getScreenshotPath());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(testCompleteObj.getReportStatus());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(Integer.toString(testCompleteObj.getAct()));
				
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
		} catch (Exception e) {
			Log.info("error in csv writing" + e.getMessage());
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				Log.info("Error while flushing/closing fileWriter " + e.getMessage());
			}

		}

	}
	/**
	 * @param Vars
	 * @throws MalformedURLException 
	 * @throws JSONException 
	 * @throws NumberFormatException 
	 */
	public static void readCSVForTestComplete() throws MalformedURLException, NumberFormatException, JSONException {
		String line = "";
		String cvsSplitBy = ",";
		String testSetBrowserName = "";
		String filePath = Constant.Vars.getCsvFilePath();
		Constant.Vars.ExecutionStatus = "";
		SpiraReaderTestComplete spiraReaderTestComplete = new SpiraReaderTestComplete();
		ReporterSummaryObject reportSumObj = new ReporterSummaryObject();
		ReporterCommon reportCommonObj = new ReporterCommon();
		if (null != filePath || !filePath.isEmpty()) {
			Constant.tempTestReportPath = new File(filePath).getParent();
			if (!(Constant.tempTestReportPath.endsWith("//") || Constant.tempTestReportPath.endsWith("\\")))
				Constant.tempTestReportPath = Constant.tempTestReportPath + "\\";
		}
		if (new File(filePath.toString()).exists()) {
			try {
				if (Constant.Vars.getIntegration() == true) {
					spiraReaderTestComplete.ExtractTestSetinProjectForTestComplete(Constant.Vars);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

				br.readLine();
				line = br.readLine();
				while (line != null && !(line.equalsIgnoreCase("")) && line !="null") {
					// Test Case Id, Test Step id, Test Set Name, Test Case
					// Name, Test Step, Action,
					// Object, ObjectProp, Event, Test Data, Execution Result,
					// Status, Execution Time, Screenshot Path
					// split by separator to each line
					//[1402, 29807, 94542, Verify if a value is entered for the Vat No field does the letter tag display  Yes the_vat no_entered, Verify if a value is entered for the Vat No field does the letter tag display Yes the_vat no_entered, click on menuitem obj=frost.mainemenu "New~Applicant", perform, menuitem, a.menu:Mdimain, click, New~Applicant, Click on mainemenu "New~Applicant"The menu item 'New|Applicant' was clicked., Passed, 2017-03-30-06-59-11, C:\temp\Detail\2017-03-30-06-57-38\Screenshot\Verify if a value is entered for the Vat No field does the letter tag display Yes the_vat no_entered_1rowno_94542_1_2017-03-30-06-59-11.jpg, executed, 1]
					String[] csvValue = line.split(cvsSplitBy);

					if (Constant.Vars.getIntegration() == true && 0 != Constant.Vars.getTestComTestStepId()
							&& Integer.parseInt(csvValue[2]) != Constant.Vars.getTestComTestStepId()) {
						spiraReaderTestComplete.updateTestStep(Constant.Vars);
						Constant.Vars.setTestComTestStepStatus("");
						Constant.Vars.setActualResult("");
					}
					if (Constant.Vars.getIntegration() == true && 0 != Constant.Vars.getTestComTestCaseId()
							&& Integer.parseInt(csvValue[1]) != Constant.Vars.getTestComTestCaseId()) {
						spiraReaderTestComplete.updateTestCase(Constant.Vars);
						Constant.Vars.setTestComTestCaseStatus("");
					}
					if (Constant.Vars.getIntegration() == true && 0 != Constant.Vars.getTestComTestSetId()
							&& Integer.parseInt(csvValue[0]) != Constant.Vars.getTestComTestSetId()) {
						spiraReaderTestComplete.updateTestSet(Constant.Vars);
						//Constant.Vars.setTestComTestCaseStatus("");
					}
					if (null != Constant.Vars.getTestCaseName() && !Constant.Vars.getTestCaseName().isEmpty()
							&& !csvValue[4].equalsIgnoreCase(Constant.Vars.getTestCaseName())) {
						setVariableToReport(reportSumObj, reportCommonObj, testSetBrowserName);
						Constant.Vars.reporterObjectList = new ArrayList<>();
						Constant.Vars.ExecutionStatus = "";
					}
					if(Constant.Vars.getIntegration() == true)
						setJSONObjectVar(Constant.Vars, csvValue);
					KeywordLibrary.setReportVarForTestComplete(csvValue);
					line = br.readLine();
					Log.info(line);
				}
				
			} catch (IOException e) {
				Log.info("Error in reading file: " + e.getMessage());
			}
			if (Constant.Vars.getIntegration() == true) {//update the very last step and very last test case
				spiraReaderTestComplete.updateTestStep(Constant.Vars);
				spiraReaderTestComplete.updateTestCase(Constant.Vars);
				spiraReaderTestComplete.updateAll(Constant.Vars); //update the jason in spiratest
			}
			setVariableToReport(reportSumObj, reportCommonObj, testSetBrowserName);
			DateFormat dateFormatEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Constant.Vars.setExecutionEndTime(dateFormatEndTime.format(new Date()));
			Constant.Vars.ExecutionStatus = "";

		} else {
			Log.info("File does not exists.");
		}
	}
	/**
	 * @param reportSumObj
	 * @param reportCommonObj
	 * @param testSetBrowserName
	 */
	public static void setVariableToReport(ReporterSummaryObject reportSumObj, ReporterCommon reportCommonObj, String testSetBrowserName){
		reportCommonObj.setReportBrowserName("Test Complete");
		reportCommonObj.setReportTestSetName(Constant.Vars.getTestSetName());
		reportSumObj.setReportSummaryTestCaseID(Integer.toString(Constant.Vars.getTestCaseID()));
		reportSumObj.setReportSummaryTestCaseName(Constant.Vars.getTestCaseName());
		testSetBrowserName = Constant.Vars.getTestSetName() + "&#" + reportCommonObj.getReportBrowserName();
		if (Constant.Vars.ExecutionStatus.contains(Constant.Failed))
			Constant.Vars.TestCaseStatus = "Failed";
		else if (Constant.Vars.ExecutionStatus.contains("Blocked") && (!Constant.Vars.ExecutionStatus.contains(Constant.Failed)))
			Constant.Vars.TestCaseStatus = "Blocked";
		else if (Constant.Vars.ExecutionStatus.contains("Skipped") || Constant.Vars.ExecutionStatus.contains("Caution") || Constant.Vars.ExecutionStatus.contains(Constant.Passed))
			Constant.Vars.TestCaseStatus = "Passed";
		reportSumObj.setReportSummaryTestCaseStatus(Constant.Vars.getTestCaseStatus());
		reportSumObj.getReportObjMap().put(Constant.Vars.getTestCaseName(),
				new ArrayList<>(Constant.Vars.reporterObjectList));
		Constant.Vars.getReporterSumObjList().add(new ReporterSummaryObject(reportSumObj));
		Constant.Vars.getReportMap().put(testSetBrowserName,
				new ArrayList<>(Constant.Vars.getReporterSumObjList()));
		Constant.Vars.getReportCommonObj().add(reportCommonObj);
	}
	
	/**
	 * @param AllTest
	 */
	public static void writeJASON(JSONArray AllTest) {
		ObjectOutputStream outputStream = null;
		// checks whether the path ends with '/'
		try {
			if (!Constant.tempTestReportPath.endsWith("\\")) {
				Constant.tempTestReportPath = Constant.tempTestReportPath + "\\";
			}
			String fileName = Constant.tempTestReportPath + "SpiraJSON.json";
			outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
			System.out.println("Start Writings");
			outputStream.writeObject(AllTest.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * @param vars
	 * @param csvValues
	 */
	public static void setJSONObjectVar(LocalTC vars, String[] csvValues) {
		vars.setTestComTestSetId(Integer.parseInt(csvValues[0]));
		vars.setTestComTestCaseId(Integer.parseInt(csvValues[1]));
		vars.setTestComTestStepId(Integer.parseInt(csvValues[2]));
		vars.setTestComTestSetStatus(vars.getTestComTestSetStatus() + csvValues[12] + ",");
		vars.setTestComTestCaseStatus(vars.getTestComTestCaseStatus() + csvValues[12] + ",");
		vars.setTestComTestStepStatus(vars.getTestComTestStepStatus() + csvValues[12] + ",");
		if (null == vars.getActualResult() || vars.getActualResult().isEmpty()) {
			vars.setActualResult(csvValues[11]);
		} else {
			vars.setActualResult(vars.getActualResult() + ";" + csvValues[11]);
		}

	}
	/**
	 * @param vars
	 * Create Detail firectory for screenShot and report
	 */
	public static void createDirectory(LocalTC vars, String folderName){
		File detailDir = new File(Constant.tempTestReportPath + folderName);
		// if the directory does not exist, create it
		if (!detailDir.exists()) {
		    try{
		    	detailDir.mkdir();
		    } 
		    catch(SecurityException se){
		       Log.error("Error in creating Detail directory");
		    }        
		}
	}
	
	/**
	 * @param Vars
	 * Writing value and column in csv file
	 */
	public static void writeDataCSV(LocalTC Vars) {
		int count = 0;
		int countCol = 0, countColTemp = 0;
		int countVal = 0;
		String strData = "";
		ArrayList<String> arrColList = new ArrayList<>();
		ArrayList<String> arrValList = new ArrayList<>();
		try {
			String[] strObjectType = Vars.getObjProp().split(",");
			String filePath = Vars.getObj();
			String line;
			File newCSVFile = new File(filePath);
			newCSVFile.createNewFile();
			for (String strObject : strObjectType) {
				try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
					if(strObject.split(":")[1].contains("#")){
						strData = Vars.getVariableData(strObject.split(":")[1].replace("#", ""));
					}else{
						strData = strObject.split(":")[1];
					}
					while ((line = br.readLine()) != null) {
						count++;
						if (count % 2 != 0) {
							for (String strlength : line.split(",")) {
								countCol++;
								arrColList.add(strlength);
								if (strlength.equalsIgnoreCase(strObject.split(":")[0])) {
									countColTemp = countCol;
								}
							}
							if (countColTemp == 0) {
								arrColList.add(strObject.split(":")[0]);
							}
						}
						if (count % 2 == 0) {
							for (String strlength : line.split(",")) {
								countVal++;
								arrValList.add(strlength);
								if (countVal == countColTemp) {
									arrValList.remove(countVal - 1);
									arrValList.add(strData);
								}
							}
							if (countColTemp == 0) {
								arrValList.add(strData);
							}
						}
					}
					if (count == 0) {
						Vars.setFileWriter(new FileWriter(filePath));
						// Write header in the CSV file
						Vars.getFileWriter().append(strObject.split(":")[0]);
						Vars.getFileWriter().append(NEW_LINE_SEPARATOR);
						//Write value in the CSV file
						Vars.getFileWriter().append(strData);
						Vars.getFileWriter().flush();
						Vars.getFileWriter().close();
					} else {
						Vars.setFileWriter(new FileWriter(filePath));
						strData = "";
						for (String strCol : arrColList) {
							if (strData.isEmpty()) {
								strData = strCol;
							} else {
								strData = strData + "," + strCol;
							}
						}
						Vars.getFileWriter().append(strData);
						Vars.getFileWriter().append(NEW_LINE_SEPARATOR);
						strData = "";
						for (String strVal : arrValList) {
							if (strData.isEmpty()) {
								strData = strVal;
							} else {
								strData = strData + "," + strVal;
							}
						}
						Vars.getFileWriter().append(strData);
						Vars.getFileWriter().flush();
						Vars.getFileWriter().close();
					}
					count = 0;
					countCol = 0;
					countVal = 0;
					countColTemp = 0;
					arrColList = new ArrayList<>();
					arrValList = new ArrayList<>();
				}
			}
		} catch (Exception e) {
			Log.info("error in csv writing" + e.getMessage());
		}
	}

	/**
	 * @param Vars
	 */
	public static void deleteFile(LocalTC Vars, String fileName) {
		String filePath = "";
		if(null != fileName){
			filePath = Constant.tempTestReportPath + fileName;
		}else{
			filePath = Constant.tempTestReportPath + Vars.getVariableData("csvDataFile");
		}
		if (new File(filePath).exists()) {
			new File(filePath).delete();
		}
	}
	/**
	 * @param dateFormate
	 * @param strData
	 * @throws IOException 
	 * @throws ParseException 
	 */
	/*public static String readWriteCSV(String filePath, String dateFormat, String strData)
			throws IOException, ParseException {

		ArrayList<String> arrDateTemp = new ArrayList<>();
		File file = new File(filePath);
		boolean bFlag = true;
		int index = 0;
		SimpleDateFormat originalFormat = null;
		SimpleDateFormat targetFormat = null;
		List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
		System.out.println(lines.size());
		for (String line : lines) {
			String[] array = line.split(",");
			if (array.length > 3) {
				if (bFlag == true) {
					for (String arr : array) {

						if (arr.equalsIgnoreCase(strData)) {
							bFlag = false;
							break;
						}
						index++;
					}
				} else
					columnValue.add(array[index + 1]);
			}
		}
		System.out.println(columnValue);
		String[] dateFormatArr = dateFormat.split("&&");
		originalFormat = new SimpleDateFormat(dateFormatArr[0], Locale.ENGLISH);
		targetFormat = new SimpleDateFormat(dateFormatArr[1]);
		for (String strColumnValue : columnValue) {
			Date date = originalFormat.parse(strColumnValue);
			String formattedDate = targetFormat.format(date);
			arrDateTemp.add(formattedDate);
			System.out.println(arrDateTemp);
		}
		System.out.println(arrDateTemp);
		CSVReader reader = new CSVReader(new FileReader(filePath), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column and replace with by using row and column
		for (int i = 0; i < arrDateTemp.size(); i++) {
			System.out.println(csvBody.get(i + 2)[index]);
			csvBody.get(i + 2)[index] = arrDateTemp.get(i);
			System.out.println(csvBody.get(i + 2)[index]);
		}
		reader.close();
		//writing a copy file into temp 
		File filePath1 = new File(Constant.tempTestReportPath + "tempCSV.csv");
		CSVWriter writer = new CSVWriter(new FileWriter(filePath1), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
		return filePath1.toString();
	}*/
	
	/**
	 * @param Vars
	 * @throws IOException 
	 * Create backup file of excel for future purpose
	 */
	public static void writeBackupFile(LocalTC Vars) throws IOException {
		FileOutputStream outFile = null;
		XSSFWorkbook workbook = Vars.TestRun.ExcelWBook;
		try {
			XSSFSheet sheet = Vars.TestRun.ExcelWSheet;
			outFile = new FileOutputStream(new File(
					Constant.tempTestReportPath + "ExcelFileBackup\\" + new File(Vars.sTestRunPath).getName()));
			workbook.write(outFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			outFile.close();
			workbook.close();
		}
	}
}
	