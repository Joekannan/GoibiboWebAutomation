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
import net.serenitybdd.screenplay.waits.Wait;
import net.serenitybdd.screenplay.waits.WaitUntil;

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
	List <WebElement> duration1;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='arrTime']")
	List <WebElement> arrival1;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='arrTime']//following-sibling::span")
	List <WebElement> arrival2;
	
	@FindBy(xpath=".//div[contains(@data-cy,'flightItem_NAV42')]//span[@data-cy='finalPrice']")
	List <WebElement> price1;
	
	
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
		File myFile = new File(".\\src\\test\\resources\\Output\\Flights.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook();
		XSSFSheet sh= wb.createSheet("Flights");
		FileOutputStream fos=new FileOutputStream(myFile);
	    waitForCondition().until(ExpectedConditions.presenceOfAllElementsLocatedBy(flightResult));
		int noOfFlights = flightResults.size();
		Map<Integer, Object[]> data = new HashMap<Integer, Object[]>(); 
		data.put(0, new Object[] {"DEPARTURE", "DURATION", "ARRIVAL", "PRICE"});
		for(int i =0; i <= noOfFlights-1; i++ ) {
			String departure = depTime.get(i).getText().toString();
			String duration = duration1.get(i).getText().toString();
			String arrival = arrival1.get(i).getText().toString();
			String price = price1.get(i).getText().toString();
			data.put(i+1, new Object[] {departure, duration, arrival, price});
			waitForCondition().until(ExpectedConditions.presenceOfAllElementsLocatedBy(flightResult));
			noOfFlights = flightResults.size();			
		}
		
		// Set to Iterate and add rows into XLS file 
		Set<Integer> newRows = data.keySet(); 
		// get the last row number to append new data 
		int rownum = sh.getLastRowNum(); 
		for (Integer key : newRows) { 
			// Creating a new Row in existing XLSX sheet 
			Row row = sh.createRow(rownum++); 
			Object [] objArr = data.get(key); 
			int cellnum = 0; 
			for (Object obj : objArr) { 
				Cell cell = row.createCell(cellnum++); 
				if (obj instanceof String) { 
					cell.setCellValue((String) obj); 
					} 
				else if (obj instanceof Boolean) { 
					cell.setCellValue((Boolean) obj); 
					} 
				else if (obj instanceof Date) { 
					cell.setCellValue((Date) obj); 
					} 
				else if (obj instanceof Double) { 
					cell.setCellValue((Double) obj); 
					} 
				} 
			} // open an OutputStream to save written data into XLSX file 
		FileOutputStream os = new FileOutputStream(myFile); 
		wb.write(os); 
		System.out.println("Writing on XLSX file Finished ...");
		}
}
