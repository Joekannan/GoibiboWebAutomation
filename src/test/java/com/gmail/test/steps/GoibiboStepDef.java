package com.gmail.test.steps;

import java.io.IOException;

import com.gmail.test.steps.serenity.GoibiboFlightSteps;
import com.gmail.test.steps.serenity.GoibiboHomeSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

public class GoibiboStepDef {
	
	@Steps
	GoibiboHomeSteps goibiboSteps;
	
	@Steps
	GoibiboFlightSteps goibiboFlightSteps;
	
	@Given("^the user is on goibibo home page$")
	public void the_user_is_on_goibibo_home_page() {
		goibiboSteps.homePage();
	}

	@When("^the user searches flight from \"([^\"]*)\" to \"([^\"]*)\" depature as \"([^\"]*)\" from current date$")
	public void the_user_searches_flight_from_to_depature_as_from_current_date(String From, String To, int Day) {
		goibiboSteps.searchForFlights(From, To, Day);
	}
	
	@When("^the user selects \"([^\"]*)\"$")
	public void the_user_selects(String airlines){
		goibiboFlightSteps.selectAirline(airlines);
	}

	@Then("^the results are available to extract in the spreadsheet$")
	public void the_results_are_available_to_extract_in_the_spreadsheet() throws IOException {
		goibiboFlightSteps.extractInExcel();
	}

	
	
}
