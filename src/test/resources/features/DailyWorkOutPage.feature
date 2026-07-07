@Workout @Regression
Feature: Workout Page -Functional validation and generate workout

  Background:
    Given User is in dashboard page after logged in

  Scenario: Verify Generate Workout Plan button is clickable
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the section with title "Daily workout plan"

  Scenario: Verify the labels and values in Daily Workout Plan header
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the labels with values "Phase:Menstrual", "Energy:Low", and "Focus:Recovery"

  Scenario: Verify the current cycle day display
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the workout day displayed as "Cycle Day 21"

  Scenario: Verify the workout title
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the title "Cycle Day 21 Luteal Phase Workout"

  Scenario: Verify the list of exercises is displayed
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the list of exercises "Deep Belly Breathing","Cat-Cow Stretch","Child's Pose"

  Scenario: Verify each exercise displays duration
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see  each exercise should show its duration in minutes

  Scenario: Verify icons are visible beside each exercise name
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see an icon beside each exercise name in the list

  Scenario: Verify "+5 more exercises" link presence
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see a  link "+4 more exercises"

  Scenario: Verify “View All Exercises” button presence
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see “View All Exercises” button displayed below the exercise list

  Scenario: Verify “Generating Next Work Out…” message appearance
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see “Generating Next Work Out…” section once workout is completed

  Scenario: Verify the “About Daily Workouts” section under Daily Workout Plan
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see the “About Daily Workouts” section displayed below the workout plan

  Scenario: Verify the content of “About Daily Workouts” section
    Given User is on the workout page
    When User clicks "Generate Workout Plan" button
    Then User should see a content message during workout generation under above daily workout section
    
