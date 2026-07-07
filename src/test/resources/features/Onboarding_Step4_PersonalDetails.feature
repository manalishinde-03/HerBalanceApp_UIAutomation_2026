@OnboardingScenariosStep4
Feature: Onboarding Process Step4

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page
    When User clicks on Continue Without Report Button
    When User selects "Health_Condition" and clicks on Continue Button

  @TC27
  Scenario: Continue is visible
    Then Continue button should be visible

  @TC28
  Scenario: State of Continue button
    Then Continue button should be enabled

  @TC29
  Scenario: Title of the onboarding step4
    Then Title of the page should be Personal Details

  @TC30
  Scenario: Description text for step4
    Then Verify description text for step4

  @TC31
  Scenario: Input box count
    Then Verify input box count is 2 on step4

  @TC32
  Scenario: Field lables
    Then User should see the following input field labels:
      | What's your first name?                         |
      | What's your age?                                |
      | What best describes your blood pressure status? |

  @TC33
  Scenario: Helper text for the fields
    Then User should see the following helper text under the input fields:
      | Personalizing your experience starts with knowing your name.           |
      | Your age helps us tailor recommendations suitable for your life stage. |

  @TC34
  Scenario: Total number of radio buttons in Step4
    Then Total number of radio buttons should be 4

  @TC35
  Scenario: Blood pressure description options
    Then Blood pressure description options should be :
      | I have been diagnosed with high blood pressure and take medication for it |
      | I have been diagnosed but don't take medication                           |
      | I have never been diagnosed                                               |
      | I'm not sure                                                              |

  @TC36
  Scenario: Display onboarding progress
    Then Verify Onboarding progress bar is displayed

  @TC37
  Scenario: Progress bar is visible
    Then Progress bar shows the current step as  4 of 10

  @TC38
  Scenario: Back button is visible
    Then Back button is displayed

  @TC39
  Scenario: State of Back button
    Then Back button should be enabled

  @TC40
  Scenario: Navigating back to step 3 Health Conditions
    When User clicks on Back Button on Step4
    Then User should navigate to step3 Health Conditions

  @TC41
  Scenario Outline: Navigation to Step 5 Menstrual Cycle Awareness
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify user navigates to Step5 after clicking Continue button

    Examples: 
      | ScenarioName                         |
      | Navigation to Step 5 with valid data |

  #defect
  @TC54
  Scenario Outline: Error message for invalid values in First name in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for invalid First Name after clicking Continue button

    Examples: 
      | ScenarioName       |
      | Invalid First Name |

  @TC55
  Scenario Outline: Error message for invalid values in Age in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for invalid Age after clicking Continue button

    Examples: 
      | ScenarioName |
      | Invalid Age  |

  @TC56
  Scenario Outline: Error message for invalid values in Age in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for letter in Age field after clicking Continue button

    Examples: 
      | ScenarioName            |
      | Invalid Age With Letter |

  @TC57
  Scenario Outline: Error message for Empty values in Age field in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for Empty Age after clicking Continue button

    Examples: 
      | ScenarioName |
      | Empty Age    |

  @TC58
  Scenario Outline: Error message for Empty values in First name in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for empty First Name after clicking Continue button

    Examples: 
      | ScenarioName     |
      | Empty First Name |

  @TC59
  Scenario Outline: Error message for invalid BP Status in Step 4
    When User enters valid personal details for "<ScenarioName>" from "Onboarding_PersonalDetails"
    When User clicks on Continue Button
    Then Verify error message for invalid BP Status after clicking Continue button

    Examples: 
      | ScenarioName   |
      | Empty BPStatus |
