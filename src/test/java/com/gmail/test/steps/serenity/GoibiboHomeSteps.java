package com.gmail.test.steps.serenity;

import com.gmail.test.pages.GoibiboHomePage;

import net.thucydides.core.annotations.Step;

public class GoibiboHomeSteps {

	GoibiboHomePage goibiboFlightsPage;
	
	@Step
	public void homePage() {
		goibiboFlightsPage.verifyHomePage();
	}
	
	@Step
	public void searchForFlights(String From, String To, int Day) {
		goibiboFlightsPage.enterSource(From);
		goibiboFlightsPage.enterDestination(To);
		goibiboFlightsPage.clickDeparture();
		goibiboFlightsPage.selectDepartDate(Day);
		goibiboFlightsPage.clickSearch();
	}
	
}
