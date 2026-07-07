@OnboardingScenariosStep3
Feature: Onboarding Process Step3

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page
    When User clicks on Continue Without Report Button

  @TC15
  Scenario: Display onboarding progress
    Then User should navigate to step3 Health Conditions
    And Verify Step 3 Onboarding progress bar is displayed

  @TC16
  Scenario: Progress bar is visible
    Then Progress bar shows the current step as  3 of 10

  @TC17
  Scenario: Back button is visible
    Then Back button is displayed

  @TC18
  Scenario: State of Back button
    Then Back button should be enabled

  @TC19
  Scenario: Continue is visible
    Then Continue button should be visible

  @TC20
  Scenario: State of Continue button
    Then Continue button should be enabled

  @TC21
  Scenario: Step 3 Header is displayed
    Then Header should be Health conditions

  @TC22
  Scenario: Sub title for the header
    Then Sub title for the header in Step3 is visible

  @TC23
  Scenario: Total number of radio buttons in Step3
    Then Total 10 Radio buttons should be visible

  @TC24
  Scenario: Options text for health condition
    Then Health condition options should be :
      | PCOS                                         |
      | Hypothyroidism                               |
      | Pre-diabetes / Diabetes                      |
      | High cholesterol                             |
      | Sleep apnea                                  |
      | Digestive issues (IBS, leaky gut, etc.)      |
      | Cardiovascular Disease                       |
      | Liver Disease                                |
      | Kidney Disease                               |
      | I am perimenopausal or menopausal (age ~45+) |

  @TC25
  Scenario: Informational note section
    Then Note text should displayed

  @TC26
  Scenario Outline: Navigation to Step 4 Personal Details
    When User selects "<healthCondition>" health condition and clicks on Continue Button
    Then Title of the page should be Personal Details

    Examples: 
      | healthCondition  |
      | PCOS             |
      | High cholesterol |
