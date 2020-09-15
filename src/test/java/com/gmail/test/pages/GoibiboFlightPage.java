package com.gmail.test.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell; 
import org.apache.poi.xssf.usermodel.XSSFRow; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class GoibiboFlightPage extends PageObject {

	@FindBy(xpath=".//div[@id='IndiGo']//input")
	WebElement chkIndiGo;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]")
	List <WebElement> flightResults;
	
	//MAA
//	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@class='db textOverflow']/span[1]")
//	List <WebElement> source;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='depTime']")
	List <WebElement> depTime;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//div[@data-cy='duration']")
	List <WebElement> duration;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='arrTime']")
	List <WebElement> arrival1;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='arrTime']//following-sibling::span")
	List <WebElement> arrival2;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='finalPrice']")
	List <WebElement> price;
	
	
	public static final By flightResult = By.xpath(".//div[contains(@data-cy,'flightItem_NAV42')]");
	
	public void selectAirlines(String airlines) {
		try {
			switch(airlines) {
				case "Air India":
					break;
				case "AirAsia India":
					break;
				case "IndiGo":
					withAction().moveToElement(element(By.xpath(".//div[@id='IndiGo']//input"))).click().build().perform();
					waitFor(ExpectedConditions.elementToBeClickable(flightResult));
					break;
				case "Spicejet":
					break;
				case "Visatara":
					break;
				default:
					break;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void writeInExcel() throws IOException {
//		String filepath= (".\\src\\test\\resources\\Output\\Flights.xlsx");
		File myFile = new File(".\\src\\test\\resources\\Output\\Flights.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook();
		XSSFSheet sh= wb.createSheet("Flights");
		FileOutputStream fos=new FileOutputStream(myFile);
		XSSFRow header = sh.createRow(0);
	    header.createCell(0).setCellValue("DEPARTURE");
	    header.createCell(1).setCellValue("DURATION");
	    header.createCell(2).setCellValue("ARRIVAL");
	    header.createCell(3).setCellValue("PRICE");
		int noOfFlights = flightResults.size();
//		Map<String, Object[]> data = new HashMap<String, Object[]>(); 
//		data.put("7", new Object[] {7d, "Sonya", "75K", "SALES", "Rupert"}); 
//		data.put("8", new Object[] {8d, "Kris", "85K", "SALES", "Rupert"}); 
//		data.put("9", new Object[] {9d, "Dave", "90K", "SALES", "Rupert"}); 
//		// Set to Iterate and add rows into XLS file 
//		Set<String> newRows = data.keySet(); 
//		// get the last row number to append new data 
//		int rownum = sh.getLastRowNum(); 
//		for (String key : newRows) { 
//			// Creating a new Row in existing XLSX sheet 
//			Row row = sh.createRow(rownum++); 
//			Object [] objArr = data.get(key); 
//			int cellnum = 0; 
//			for (Object obj : objArr) { 
//				Cell cell = row.createCell(cellnum++); 
//				if (obj instanceof String) { 
//					cell.setCellValue((String) obj); 
//					} 
//				else if (obj instanceof Boolean) { 
//					cell.setCellValue((Boolean) obj); 
//					} 
//				else if (obj instanceof Date) { 
//					cell.setCellValue((Date) obj); 
//					} 
//				else if (obj instanceof Double) { 
//					cell.setCellValue((Double) obj); 
//					} 
//				} 
//			} // open an OutputStream to save written data into XLSX file 
//		FileOutputStream os = new FileOutputStream(myFile); 
//		wb.write(os); 
//		System.out.println("Writing on XLSX file Finished ...");
//		}
	for(int j=0; j <= 4; j++ ) {
			for(int i = 0; i <= noOfFlights-1; i++) {
				XSSFRow rw= sh.createRow(i);
				XSSFCell cl=rw.createCell(j);
				cl.setCellType(CellType.STRING);
				String departure = depTime.get(i).getText().toString();
	            System.out.println(departure);
	            cl.setCellValue(departure);
			}
			
		}
		fos.flush();     
		wb.write(fos);     
		fos.close();
		
	}
}
