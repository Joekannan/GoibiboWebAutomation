Feature: Extract Flight Information
  In order to book a flight
  As a traveller
  I want to extract a specific flight details

  Scenario Outline: Extracting and writing the flight information
    Given the user is on goibibo home page
    When the user searches flight from "<From>" to "<To>" depature as "<Day>" from current date
    And the user selects "<PreferredAirlines>"
    Then the results are available to extract in the spreadsheet

    Examples: 
      | From    | To    | Day | PreferredAirlines |
      | Chennai | Delhi |   3 | IndiGo            |
