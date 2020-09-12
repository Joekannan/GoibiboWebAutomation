package com.gmail.test.pages;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;

public class GoibiboHomePage extends PageObject {

	@FindBy(id="gosuggest_inputSrc")
	WebElement txtFrom;
	
	@FindBy(id="gosuggest_inputDest")
	WebElement txtTo;
	
	@FindBy(id="departureCalendar")
	WebElement calDepart;
	
	@FindBy(className="DayPicker-Month")
	WebElement departDatePicker;
	
	@FindBy(id="returnCalendar")
	WebElement calReturn;
	
	@FindBy(id="gi_search_btn")
	WebElement btnSearch;
	
	@FindBy(xpath=".//div[@class='DayPicker-Day' or @class='DayPicker-Day DayPicker-Day--today DayPicker-Day--selected']")
	List <WebElement> dates;
	
	public void verifyHomePage() {
		try {
			open();
			getDriver().manage().window().maximize(); 
			String title = getTitle();
			assertEquals("Goibibo - Best Travel Website. Book Hotels, Flights, Trains, Bus and Cabs with upto 50% off", title);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void enterSource(String From) {
		try {
			clickOn(txtFrom);
			typeInto(txtFrom, From);
			WebDriverWait wait = new WebDriverWait(getDriver(), 20);
			wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("react-autosuggest-1-suggestion--0"))));
			clickOn(getDriver().findElement(By.id("react-autosuggest-1-suggestion--0")));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void enterDestination(String To) {
		try {
			clickOn(txtTo);
			typeInto(txtTo, To);
			WebDriverWait wait = new WebDriverWait(getDriver(), 20);
			wait.until(ExpectedConditions.elementToBeClickable(getDriver().findElement(By.id("react-autosuggest-1-suggestion--0"))));
			clickOn(getDriver().findElement(By.id("react-autosuggest-1-suggestion--0")));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickSearch() {
		try {
			clickOn(btnSearch);	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickDeparture() {
		try {
			clickOn(calDepart);
			waitFor(ExpectedConditions.elementToBeClickable(departDatePicker));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selectDepartDate(int day) {
		try {
			dates.get(day).click();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
