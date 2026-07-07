@OnboardingScenariosStep7
Feature: Onboarding Process

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page
    When User clicks on Continue Without Report Button
    When User selects "Health_Condition" and clicks on Continue Button
    When User completes onboarding till Step 4 using "VALID_USER"
    When User selects Menstrual Cycle Info "Cycle_Info" and clicks on Continue Button
    When User selects last menstrual date "Menstrual_Date"
    And User clicks on Continue Button

  #Onboarding step 6 Functionality and Step 7 UI verification
  @TC78
  Scenario: Title of the onboarding step7
    Then Title of the page should be Current Weight and Height

  @TC79
  Scenario: Continue is visible
    Then Continue button should be visible

  @TC80
  Scenario: State of Continue button Step 7
    Then Continue button should be enabled

  @TC81
  Scenario: Description text for step 7
    Then Verify description text for step7

  @TC82
  Scenario: Two text fields visibility in step 7
    Then Verify input text field count is 2 in step7

  @TC83
  Scenario: Progress bar is visible
    Then Progress bar shows the current step as  7 of 10

  @TC84
  Scenario: Back button is visible
    Then Back button is displayed

  @TC85
  Scenario: State of Back button
    Then Back button should be enabled

  @TC86
  Scenario: Navigating back to step 6
    When User clicks on Back Button on Step6
    Then Title of the page should be Last Period Date

  @TC87
  Scenario: Text field labels text in step 7
    Then User should see the following input field labels on Step 7:
      | What's your current weight? |
      | What's your height?         |
