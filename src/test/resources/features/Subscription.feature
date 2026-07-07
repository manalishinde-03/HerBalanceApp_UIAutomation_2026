@tag
Feature: Subcription 
Background:User completes onboarding process until step 10
  
  @tag1
  Scenario: Subcription- Page Header Section
    Given User is on step eleven onboarding process
    When User clicks on Complete after entering the required medication details
    Then Choose Your Transformation Journey header is visible
    Then sub-text header is visible
    Then There should be exactly three subscription plan tiles displayed

   @tag2
  Scenario: Subcription- Free Plan tile
    Then Free Plan - Seven Days Trial section tile is displayed inside Free plan tile
    Then Seven day meal plan section title is displayed
    Then Free header is visible
    Then Seven days access subtext under header is visible
    Then Cycle-based diet plan lines preceeded with checkmark are visible
    Then Active Plan button is visible
    Then Active Plan button should be enabled
  
   @tag3
  Scenario: Subcription- Monthly Plan tile
    Then Most Popular badge text is displayed on Monthly plan tile
    Then Monthly Plan thirty Days subheader is displayed inside Monthly plan tile
    Then Unlock a full month subtext is displayed
    Then Thirty-nine header is visible
    Then Billed monthly subtext under header is visible
    Then Thirty day AI diet plan lines preceeded with checkmark are visible
    Then Scribe Now button is visible
    Then Subscribe Now button is should be enabled
    
    @tag4
  Scenario:  Subcription- 3 Month Plan tile
    Then Best Value badge text is displayed on three Month Plan tile
    Then Three Month Plan Full Transformation subheader is displayed inside Three Month Plan tile
    Then A Ninty day journey using menstrual cycle-aligned health planning subtext is displayed
    Then Ninty header is visible
    Then One time payment for Three months subtext under header is visible
    Then Ninty day meal text preceeded with star symbol are visible
    Then Get Started button is visible
    
    
    
     @tag5
  Scenario:  Subcription- 3 Month Plan tile
    Given User is on subscribe plan page 
    When User clicks Active Plan button in free plan
    Then User should be Redirected to Dashboard page
    
  
    
    
   

  

    
  
  



    
     