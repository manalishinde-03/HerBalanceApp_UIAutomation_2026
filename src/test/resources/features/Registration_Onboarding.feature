@UserRegistrationScenario
Feature: Onboarding Process-User Registration


  Scenario: Successful registration with valid details
    Given User clicks on sign up
    And User clicks register button after entering valid details
    Then Title of the page should be Upload Your Recent Blood Work