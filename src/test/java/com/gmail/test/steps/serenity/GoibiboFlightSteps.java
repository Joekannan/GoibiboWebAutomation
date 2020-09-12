package com.gmail.test.steps.serenity;

import java.io.IOException;

import com.gmail.test.pages.GoibiboFlightPage;

import net.thucydides.core.annotations.Step;

public class GoibiboFlightSteps {

	GoibiboFlightPage goibiboFlightPage;
	
	@Step
	public void selectAirline(String Airline){
		goibiboFlightPage.selectAirlines(Airline);
	}
	
	@Step
	public void extractInExcel() throws IOException{
		goibiboFlightPage.writeInExcel();
	}
}
