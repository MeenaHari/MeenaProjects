package XeroModularFrameWork;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class  XeroReUsableMethod extends XeroAutomationScriptsDriver{
	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;

	static String result;

	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	/* Method name: enterText
	 * Brief Description: Enter text to text field
	 * Arguments: webObj --> Text box Object, textval --> Value to be entered, objName --> Name of the object
	 * Created by: automation Team
	 * Creation Date: Mar 5 2017
	 * Last Modified: Mar 6 2017
	 * */
	
	public static void enterText(WebElement webObj, String textVal, String objName ) throws IOException{
		if(webObj.isDisplayed()){
			webObj.sendKeys(textVal);
			Update_Report("Pass", "enterText", textVal + "  entered in " + objName+" field");
		
		}else{
			Update_Report("Fail", "enterText", objName + " field does not exist , please check your application.");
		
		}
	}
	
	
	
	public static void clickButton(WebElement webObj, String objName)throws IOException
	{
		if(webObj.isDisplayed())
		{
			webObj.click();
			Update_Report("Pass", "clickButton", objName+ " exists and is clicked");
			//System.out.println("Pass:"+objName+" exists and is clicked");
		}
		else
		{    Update_Report("Fail", "clickButton",  objName+ " does not exist. Please check your application.");
			//System.out.println("Fail:"+objName+" does not exist. Please check your application.");
		}
	}
	
	public static void checkElement(WebElement webObj, String objName)throws IOException
	{
		if(webObj.isDisplayed())
		{
			webObj.click();
			//Update_Report(String "Res_Type", String "name Of Method", objName); 
			Update_Report("Pass", "checkElement", objName+ " exists and is clicked");
			//System.out.println("Pass:"+objName+" exists and is clicked");
		}
		else
		{    Update_Report("Fail", "clickButton",  objName+ " does not exist. Please check your application.");
			//System.out.println("Fail:"+objName+" does not exist. Please check your application.");
		}
	}
	public static void selectCheckBox(WebElement webObj, String objName) throws IOException
	{
		boolean flag;
		flag = webObj.isSelected();
		if(flag == false)
		{
			webObj.click();
			//System.out.println("Pass:"+objName+" is selected");
			Update_Report("Pass", "selectCheckBox", objName+ "is selected ");
			
		}	else{
			//System.out.println("Pass:"+objName+" is already selected");
			Update_Report("Pass", "selectCheckBox", objName+ "is already selected ");
		}
	}
	
	public static void deSelectCheckBox(WebElement webObj, String objName) throws IOException
	{
		boolean flag;
		flag = webObj.isSelected();
		if(flag == true)
		{
			webObj.click();
			//System.out.println("Pass:"+objName+" is deselected");
			Update_Report("Pass", "deselectCheckBox", objName+ "is selected ");
		}else{
			//System.out.println("Pass:"+objName+" is already deselected");
			Update_Report("Pass", "deselectCheckBox", objName+ "is already deselected ");
		}
	}

	
	/* Name of the Method: readSheet ( CMMI - 5 level coding )
	 * Brief Description: Read xl sheet content 
	 * Arguments: dt_path --> Path of Xl sheet, sheetName --> Name of the sheet
	 * Created By:  Automation team
	 * Creation Date: Mar 5 2017
	 * Last Modified date: Mar 6 2017
	 * */

	public static String[][] readSheet(String dt_Path, String sheetName) throws IOException{


		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_Path);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);


		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);


		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);

		int iRowCount = sheet.getLastRowNum()+1;
		System.out.println("Row Count:"+iRowCount);
		int iColCount = sheet.getRow(0).getLastCellNum();
		System.out.println("Column Count:"+iColCount);
		String[][] xlData = new String[iRowCount][iColCount];
		
		for(int i=0; i<iRowCount; i++){
			for(int j = 0; j<iColCount; j++){
				
				xlData[i][j]= sheet.getRow(i).getCell(j).getStringCellValue();
				//System.out.println(xlData[i][j]);
			}
			
		}
		
		return xlData;
	}

	/* Name of the Method: writeExcel ( CMMI - 5 level coding )
	 * Brief Description: Write to xl sheet content 
	 * Arguments: dt_path --> Path of Xl sheet, sheetName --> Name of the sheet, iRowCount --> row number
	 * iColCount --> colNumber, XlData --> text to be entered 
	 * Created By:  Automation team
	 * Creation Date: Feb 15 2017
	 * Last Modified date: Feb 15 2017
	 * */
	public static void writeExcel(String dt_path, String sheetName, int iRowCount, int iColCount, String XlData) throws IOException{
		/*Step 1: Get the XL Path*/
		File xlFile = new File(dt_path);

		/*Step2: Access the Xl File*/
		FileInputStream xlDoc = new FileInputStream(xlFile);

		/*Step3: Access the work book */
		HSSFWorkbook wb = new HSSFWorkbook(xlDoc);

		/*Step4: Access the Sheet */
		HSSFSheet sheet = wb.getSheet(sheetName);
		
		/*Step 5: Access Row*/
		HSSFRow row = sheet.getRow(iRowCount);
		
		/*Step 6: Access Column*/
		HSSFCell cell = row.getCell(iColCount);
		
		cell.setCellValue(XlData);
		
		if(XlData.equalsIgnoreCase("Pass")){


			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.GREEN().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
		}
		else if(XlData.equalsIgnoreCase("Fail")){
		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setFillForegroundColor(new HSSFColor.RED().getIndex());
		titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		//titleStyle..setUnderline(FontUnderlineType.SINGLE);
		cell.setCellStyle(titleStyle);
		}
		else{
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setFillForegroundColor(new HSSFColor.YELLOW().getIndex());
			titleStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
			cell.setCellStyle(titleStyle);
			
		}
		
		FileOutputStream fout = new FileOutputStream(dt_path);
		wb.write(fout);
		fout.flush();
		fout.close();
	}
	
	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;


		String testScriptName =scriptName;


		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";



		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Line Number</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");


	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			reportflag = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

				+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
				+ result + "</FONT></TD></TR>");

		} 
	}
	
	
}
