@OnboardingScenariosStep1
Feature: Onboarding Process Step1

  Background: 
    Given User is already registered with "User_Data" and on Upload Blood Report page

  @TC1
  Scenario: Title of the onboarding step1
    Then Title of the page should be Upload Your Recent Blood Work

  @TC2
  Scenario: Supporting text for the title
    Then Verify the supporting text on Onboarding page1

  @TC3
  Scenario: Display onboarding progress
    Then Verify Onboarding progress bar is displayed

  @TC4
  Scenario: Progress bar is visible
    Then Verify Progress bar shows the current step as 1 of 10

  @TC5
  Scenario: Upload PDF button is displayed
    Then Verify Upload PDF button is displayed

  @TC6
  Scenario: Upload PDF button is enabled
    Then Verify Upload PDF button is enabled

  @TC7
  Scenario: Notes for upload pdf is displayed
    Then Verify PDF files only (Max 10MB) is visible

  @TC8
  Scenario: Display skip option
    Then Skip for Now card is visible

  @TC9
  Scenario: Text explanation for skip report
    Then Explanation text about continuing without lab results is displayed

  @TC10
  Scenario: Continue Without Report button is displayed
    Then Continue Without Report button is displayed

  @TC11
  Scenario: Continue without Report button is enabled
    Then Continue Without Report button is enabled

  @TC12
  Scenario: Display data security information
    Then Note about secure processing and no sharing with third parties is displayed

  #Upload Functionality
  @TC13
  Scenario: Navigation to Onboarding step3
    And User clicks on Continue Without Report Button
    Then User should navigate to step3 Health Conditions

  @TC14
  Scenario: Navigation to Windows Explorer from Onboarding step3
    And User clicks on Upload PDF Button

  # Then User should see Windows Explorer opened
  @TC15
  Scenario: Valid PDF file upload and percentage bar
    And User uploads the blood report
    Then User should see upload processing percentage bar

  @TC16
  Scenario: Valid PDF file upload with Report Analysis
    And User uploads the blood report
    Then User should see Report analysis

  @TC17
  Scenario: Valid PDF file upload with fields in Report
    And User uploads the blood report
    Then User should see Blood Test Results,Complete Blood Count, Medical Conditions ,Abnormal Values, Recommendations

  @TC18
  Scenario: Invalid file upload
    And User uploads invalid blood report
    Then User should see error for unsupported file type

  @TC19
  Scenario: Valid PDF file upload with size more than 10MB
    And User uploads the blood report
   # Then User should see "File exceeds 10MB" error
