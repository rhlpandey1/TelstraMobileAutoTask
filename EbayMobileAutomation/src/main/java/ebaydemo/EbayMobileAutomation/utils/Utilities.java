package ebaydemo.EbayMobileAutomation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Utilities {
	public static Logger log = Logger.getLogger(Utilities.class);
	/*
	 * This method is used to get the property values from .properties file
	 */
	public static String getProperty(String key) throws IOException
	{
		Properties prop=new Properties();
		String baseProjectPath = System.getProperty(Constants.USER_DIR);
		FileInputStream fis=new FileInputStream(baseProjectPath.concat(Constants.CONFIG_PROPERTY));
		prop.load(fis);
		String value=prop.getProperty(key).trim();
		return value;
	}
	/**
	 * wrapped the Thread.sleep() into a method
	 * @param timeInSeconds
	 */
	public static void staticWait(long timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds);
			log.info("Applied static wait of " + timeInSeconds + " Successfully");
		} catch (InterruptedException e) {

		}
	}
	
	/**
	 * scroll and click a particular element
	 * @param visibleText
	 * @param androidDriver
	 */
	public void scrollAndClick(String visibleText,AndroidDriver<MobileElement> androidDriver) {
		androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
	}
	/**
	 * Utility to read test data from external test data excel
	 * @param testDataSheetPath
	 * @param sheetName
	 * @param testCaseName
	 * @return
	 * @throws IOException
	 */
	public static List<String> getData(String testDataSheetPath,String sheetName,String testCaseName) throws IOException
	{
		List<String> list=new ArrayList<String>();
		FileInputStream fis=new FileInputStream(testDataSheetPath);
		//Accepts FileInputStream arg
		XSSFWorkbook workBook=new XSSFWorkbook(fis);
		XSSFSheet sheet;
		//getting no of sheets
		int noOfSheets=workBook.getNumberOfSheets();
		for(int i=0;i<noOfSheets;i++)
		{
			//Navigating to a particular sheet
			if(workBook.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				 sheet=workBook.getSheetAt(i); //sheet is collection of rows
				 //it will give all the rows
				 Iterator<Row> rows= sheet.iterator();
				 Row firstRow=rows.next();//Row is collection of cells
				 int count=0;
				 int column=0;
				 //it will give all the cells of first row
				 Iterator<Cell> cells= firstRow.cellIterator();
				 while(cells.hasNext())
				 {
					 Cell value=cells.next();
					 //checking whether any cell of first row contains TC or not
					 if(value.getStringCellValue().equalsIgnoreCase("TestCase"))
					 {
						 column=count;
					 }
					 //increasing the count to get the particular column where we have TC cell value
					 count++;
				 }
				 System.out.println(column);
				 //we are iterating all the rows again
				 while(rows.hasNext())
				 {
					 Row r=rows.next();
					 //we are iterating over all the rows and a particular column which we have already got
					 //And checking whether that particular cell value is equal to TC_2 or not
					 if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName))
					 {
						Iterator<Cell> cellValues=r.cellIterator();
						while(cellValues.hasNext())
						{
							//getting all the cell values
							//storing the cell values in an Array List
							Cell c=cellValues.next();
							if(c.getCellType()==CellType.STRING)
								list.add(c.getStringCellValue());
							else
								list.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							
						}
					 }
				 }
			}
		}
		return list;
	}
	    
}
