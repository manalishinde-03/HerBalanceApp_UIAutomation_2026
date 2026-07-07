@OnboardingScenariosStep6
Feature: Onboarding Process

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page
    When User clicks on Continue Without Report Button
    When User selects "Health_Condition" and clicks on Continue Button
    When User completes onboarding till Step 4 using "VALID_USER"
    When User selects Menstrual Cycle Info "Cycle_Info" and clicks on Continue Button

#Onboarding step 5 Functionality and Step 6 UI verification				
  @TC61
  Scenario: Title of the onboarding step6
    Then Title of the page should be Last Period Date

  @TC62
  Scenario: Continue is visible
    Then Continue button should be visible

  @TC63
  Scenario: State of Continue button Step 6
    Then Continue button should be enabled

  @TC64
  Scenario: Description text for step 6
    Then Verify description text for step6

  @TC65
  Scenario: Presence of question in step 6
    Then User should see the question on Step6

  @TC66
  Scenario: Presence of calendar icon in step 6
    Then User should see the calendar icon in step6

  @TC67
  Scenario: Placeholder text for date field in step 6
    Then Verify Date input field is displayed

  @TC68
  Scenario: Label below the date field
    Then Label "Cycle Length (days)" should be visible

  @TC69
  Scenario: Slider for cycle length
    Then Slider control for cycle length should be displayed

  @TC70
  Scenario: Default cycle length in slider
    Then Default cycle length value "28" should be shown beside the slider

  @TC71
  Scenario: Information text for cycle length
    Then Information text "Average cycle length is 28 days, but can vary from 21 to 45 days" should be visible

  @TC72
  Scenario: Display onboarding progress
    Then Verify Onboarding progress bar is displayed

  @TC73
  Scenario: Progress bar is visible
    Then Progress bar shows the current step as  6 of 10

  @TC74
  Scenario: Back button is visible
    Then Back button is displayed

  @TC75
  Scenario: State of Back button
    Then Back button should be enabled

  @TC76
  Scenario: Navigating back to step 5
    When User clicks on Back Button on Step6
    Then User should navigate to step5 Menstrual Cycle Awareness

  @TC77
  Scenario: Message for related to last period date
    When User selects last menstrual date "Menstrual_Date"
    Then Verify the Message after selecting the "Menstrual_Date" on Step 6
    
    @TC777
  Scenario Outline: Error message for invalid values date field
    When User enters invalid last period date "Invalid_Date"
    When User clicks on Continue Button
    Then Verify error message for invalid last period date after clicking Continue button
#
    #Examples: 
      #| ScenarioName       |
      #| Invalid Date |

      @TC778
  Scenario Outline: Error message for empty values date field
    When User clicks on Continue Button
    Then Verify error message for invalid last period date after clicking Continue button