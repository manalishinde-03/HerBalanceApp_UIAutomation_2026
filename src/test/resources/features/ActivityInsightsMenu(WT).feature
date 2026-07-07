@tag
Feature: Activity Insights Menu (Weight Tracking )
Background: User clicks activity insights button after logged in from "User_Data"
  @tag1  
    Scenario: Weight Tracking UI Verification
    Given User is on the Her Balance Dashboard with the activity insights submenu expanded
    When User clicks on track weight
    Then User should see  header text Weight Tracking
    Then User should see Free Plan Seven Days Tracking
    Then Back to Dashboard button should be visible
    Then Three cards should be present
    Then Starting weight , Current weight and Goal weight card should be displayed
    Then Label and weight values should be center-aligned
    Then Weight value should be mapped from the onboarding process
    Then Current Weight should be x value
    Then Goal weight should be displayed
    Then Header should be Progress overview
    Then Sub text should be Weight Loss Progress
    Then Completion percentage should be displayed
    Then Progress bar should be visible
    Then weight lost should be displayed
    Then Remaining weight  should be displayed
    Then Graph section header should be Weight Progression over Time
    Then Graph should be visible with x-axis showing days-Day one to Day seven
    Then Y-axis should display weight values in kg
    Then Goal weight reference line should be displayed
    Then Log Todays Weight should be visible
    Then Weight(kg) BMI(Auto-calculated)Note(Optional) should be displayed
    Then Log weight button should be disabled
    
    Scenario: BMI auto-calculation after entering weight 24
    When User enter valid value in weight after clicking track weight in sub menu
    Then BMI field should auto-calculate
    
    Scenario: Log Weight button enables after valid input 26
    Then Log Weight button should be Enabled
    
     
  @tag2 
    Scenario: Weight Tracking functional verification 28 
    Given User is on the Weight tracker
    When User clicks Log Weight button after entering valid value in weight
    Then Entered weight should be added to the tracking record
    
    Scenario: Weight Tracking functional verification 29
    When User enter invalid value in weight after clicking track weight in sub menu
    Then Error message Enter valid weight should be displayed
    
    Scenario: Weight Tracking functional verification 32, 33, 34
    When User clicks save button after entering valid value in weight
    Then Weight Lost and Remaining values should update accordingly
    Then progress bar percentage should adjust to reflect new progress
    Then Note should be saved along with the weight entry
    
    Scenario: Weight Tracking functional verification 35
    When User clicks Log Weight button
    Then The  message Weight added  successfully should be displayed