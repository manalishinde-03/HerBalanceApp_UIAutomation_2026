@Login @Regression
Feature: Auth(Login) Page TestCases

  Background:
    Given User is on the HerBalance auth page
    When User clicked login button in home page
# NonFunctional

  Scenario: HerBalance logo visibility
    Then HerBalance logo should be visible on the top-left corner

  Scenario: Login and Sign Up tabs are displayed
    Then Both "Login" and "Sign Up" tabs should be visible

  Scenario: Default selected tab is “Login”
    Then “Login” tab should be active by default

  Scenario: Email ID input field presence
    Then Email ID input field with placeholder "you@example.com" should be visible

  Scenario: “Show Password” presence
    Then “Show password” should be visible

  Scenario: “Forgot password?” link presence
    Then “Forgot password?” link should be visible

  Scenario: “Sign Up” link visibility
    Then “Sign Up” link should be visible and navigates to the Sign-Up page

  Scenario: Right panel content (Transform Your Weight Loss Journey)
    Then The right section should display “Cycle-Synced Plans”, “Blood Work Analysis”, and “Personalized Dashboard” content

  Scenario: Testimonial section presence
    Then A testimonial with text and name (e.g., “Sarah, 34”) should be displayed

  Scenario: total number of tabs
    Then There should be exactly 2 tabs — “Login” and “Sign Up”

  Scenario: Two input field
    Then There should be exactly 2 input field

  Scenario: Radio button for show password
    Then Radio button for show password

  Scenario: background and theme consistency
    Then User should see a light purple background and readable text content

  # Functional
  Scenario Outline: login validation
    When User enters login details for "<ScenarioName>" from "LoginSheet"
    When User clicks the login button
    Then The login Error should be displayed for "<ScenarioName>"

    Examples:
      | ScenarioName                     |
      | Successful login with valid data |
      | Login with invalid credentials   |
      | Login with empty fields          |

  Scenario: Check show password behaviour
    When User clicks show password after entering password
    Then Password should be visible in plain text

  Scenario: Hide Password after unchecking
    When User unchecks the "Show Password" option after entering the password
    Then Password should again be masked

  Scenario: Password visible when clicking eye icon
    When User enters a password and clicks the eye icon
    Then Password field should show the entered password in plain text

  Scenario: Password hides when eye icon clicked again
    When User clicks the eye icon again
    Then Password should again be masked

  Scenario: Navigate to Forgot Password page
    When User clicks on "Forgot password?"
    Then User should be redirected to the Forgot Password page

  Scenario: Navigate to Sign Up page
    When User clicks on "Sign up" tab
    Then User should be redirected to the sign up tab
