@WorkoutUIVerification
Feature: Workout Page UI verification

  Background: 
   Given User is on the dashboard page
   And User is on the workout page

@WorkoutTC1
  Scenario: Verify the workout page title
    Then User should see page title as "Daily Personalized Workouts"

@WorkoutTC2
  Scenario: Verify the presence of  "Back to Dashboard" text on the Workout page.
    Then User should see the link Back to dashboard

@WorkoutTC3
  Scenario: Verify the presence of daily workout plan section
    Then User should see the "Daily Workout Plan" section
@WorkoutTC4
  Scenario: Verify the description under daily workout plan section
    Then User should see the description as "Fresh AI-powered workout tailored to your current cycle phase".

@WorkoutTC5
  Scenario: Verify the message under daily workout plan section
    Then User should see the message as "No workout plan found"

@WorkoutTC6
  Scenario: Verify the alignment of message under daily workout plan section
    Then User should see the message as "No workout plan found Generate a personalized daily workout based on your current cycle phase (luteal) A new workout is generated each day with Gemini Flash 2.0 AI" aligned centre

@WorkoutTC7
  Scenario: Verify the display of Generate workout plan button
    Then User should see "Generate workout plan" button displayed
    
@WorkoutTC8
  Scenario: Verify the presence of your Follicular phase workout section
    Then User should see "Your Follicular Phase Workouts" section
    
@WorkoutTC9
  Scenario: Verify the presence of benefits of cycle synced workout section
    Then User should see a "Benefits of Cycle-Synced Workouts" section
    
@WorkoutTC10
  Scenario: Verify the description under benefits of cycle synced workout section
    Then User should see the description  under benefits of cycle synced workout section as "This personalized 7-day workout plan is tailored to your health profile and current cycle phase.Working with your hormones-not against them-optimizes results and helps you feel your best"

   @WorkoutTC11
  Scenario: Verify icons under benefits of cycle section
    Then User should see below icons listed :
    |Home Workouts |
    |Mood Enhancement|
    |Calorie Tracking|
    |Hormonal Balance|