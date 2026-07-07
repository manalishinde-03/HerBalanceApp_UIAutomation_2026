@OnboardingScenariosStep5
Feature: Onboarding Process

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page
    When User clicks on Continue Without Report Button
    When User selects "Health_Condition" and clicks on Continue Button
    When User completes onboarding till Step 4 using "VALID_USER"

  @TC42
  Scenario: Continue is visible
    Then Continue button should be visible

  @TC43
  Scenario: State of Continue button
    Then Continue button should be disabled

  @TC44
  Scenario: Navigation to onboarding step5 Menstrual Cycle Awareness
    Then Verify user navigates to Step5 after clicking Continue button

  @TC45
  Scenario: Description text for step5
    Then Verify description text for step5

  @TC46
  Scenario: Presence of question in step 5
    Then User should see the question on Step5

  #defect
  @TC47
  Scenario: Total number of radio buttons in Step5
    Then Total number of radio buttons in Step5 should be 5

  #defect
  @TC48
  Scenario: Radio button options in Step5
    Then Following Radio options for  menstrual cycle awareness should be displayed
      | Yes                                      |
      | No                                       |
      | My cycle is irregular                    |
      | I'm perimenopausal                       |
      | I no longer menstruate (e.g., menopause) |

  @TC49
  Scenario: Display onboarding progress
    Then Verify Step 5 Onboarding progress bar is displayed

  @TC50
  Scenario: Progress bar is visible
    Then Progress bar shows the current step as  5 of 10

  @TC51
  Scenario: Back button is visible
    Then Back button is displayed

  @TC52
  Scenario: State of Back button
    Then Back button should be enabled

  @TC53
  Scenario: Navigating back to step 4 Personal Details
    When User clicks on Back Button on Step5
    Then User should navigate to step4 Personal Details

  @TC60
  Scenario: Navigation to Step 6 Last Period Date
    When User selects Menstrual Cycle Info "Cycle_Info" and clicks on Continue Button
    Then Title of the page should be Last Period Date
