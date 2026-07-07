@SignUp @Regression
Feature: SignUp Page TestCases

  Background:
    Given User is on the HerBalance auth page
    When User is clicked Sign up button in home page

  # NonFunctionalTest
  Scenario: “Show Passwords”  presence
    Then “Show password” should be visible

  Scenario: “Login" link visibility
    Then “Sign Up” link should be visible and navigates to the Sign-Up page

  Scenario: Three input field
    Then There should be exactly 3 input field in signUpPage

  Scenario: Radio button for show passwords
    Then Radio button for show passwords is present

  Scenario: Terms and condition presence
    Then Terms and conditions details should be present

  Scenario: Radio button for Terms and condition
    Then Radio button for Terms and condition is present
# FunctionalTest

  Scenario: Registration validation with valid Inputs
    When User enters details from "Registration with valid details" from "SignUpSheet"
    When User clicks the Register button
    Then User should be redirected to the onboarding step

  Scenario Outline: Registration validation with Invalid Inputs
    When User enters details from "<ScenarioName>" from "SignUpSheet"
    When User clicks the Register button
    Then The expected outcome should be displayed for "<ScenarioName>"

    Examples:
      | ScenarioName                         |
      | Registration with Invalid email      |
      | Registration with password length 5  |
      | Registration with mismatch passwords |
      | Registration with empty fields       |
      | Registration without accepting terms |

  Scenario: Check Show Passwords behavior using eye icon
    When User clicks the eye icon to show the passwords after entering it
    Then The entered passwords should be visible in both password fields

  Scenario: Hide Passwords after unchecking eye icon
    When User clicks the eye icon again (unchecks Show Passwords) after entering the passwords
    Then passwords should be masked again (displayed as dots or asterisks) in both fields

  Scenario: Value hides when clicking eye icon in password field
    When User clicks the eye icon again on signUpPage
    Then Password field should mask the entered password in plain text

  Scenario: Value visible when clicking eye icon in confirm password field
    When User enters a password and clicks the eye icon on signUpPage
    Then Confirm Password field should show the entered password in plain text

  Scenario: Value hides when clicking eye icon in confirm password field
    When User clicks the eye icon again on signUpPage
    Then Confirm Password field should mask the entered password in plain text

  Scenario: Navigate to login page
    When User clicks on "Login"
    Then User should be redirected to the login tab
