@Menstrual
Feature: Menstrual Cycle Tracker - UI Verification

  Background:
    Given User is logged in from "Login_UserData" and on the Her Balance Dashboard

  Scenario: Verify visibility of UI elements on Menstrual Phase Logs page
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then the UIElement should be visible
      | UIElement                |
      | Current Cycle Status     |
      | Upcoming Phases          |
      | Recommended Activities   |
      | Nutrition Tips           |
      | Next Period              |
      | Update Cycle Information |
      | Back to Dashboard        |
      | Add Period Log           |
      | Cycle Overview Tab       |
      | Menstrual Calendar Tab   |
      | Period History Tab       |

  Scenario: Verify Dynamic Cycle Status and Progress Bar
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then the "Section Heading" displays "Current Cycle Status"
    Then the phase labels "Menstrual", "Follicular", "Ovulation", and "Luteal" are displayed below the progress bar
    Then "Current Phase", "Last period started", "Next period expected" labels are displayed
    Then Subsection heading reflects the Current Phase in the format "[Current Phase] Details"
    Then Display content should match current phase

  Scenario: Verify Upcoming Phases Section Display and Content
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then the Section Heading displays "Upcoming Phases"
    Then the subtext displays "Plan ahead with your cycle phases"
    Then 5 phase subsections are visible on the page
    Then the phase labels "Menstrual Phase", "Follicular Phase", "Ovulation Phase", and "Luteal Phase" are displayed
    Then each phase start date is displayed correctly based on the cycle data entered during onboarding
    Then all phase start dates are displayed in "Starts MMM dd" format
    Then the current phase date is displayed in "Started MMM dd" format
    Then the "Current Phase" subsection is highlighted based on cycle data entered during onboarding

  Scenario: User views Recommended Activities for current phase
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then "Recommended Activities" is displayed as per the current phase

  Scenario: User views Nutrition Tips for current phase
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then "Nutrition Tips" should be displayed as per the current phase

  Scenario: User checks the next period date displa
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then the "Next period date" should be displayed in the format "Month Day, Year"

  Scenario: User views days remaining until next period
    When User clicks "Activity Insights" and selects "Menstrual Phase Logs"
    Then the "Number of days remaining" should be visible correctly based on the next period date
