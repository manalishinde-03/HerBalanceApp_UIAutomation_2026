@Launch @Regression @smoke
Feature: Launch Page Test Cases


# Non Functional

  Scenario: Cycle phases information is displayed
    Then Display cards for following
      | Menstrual Phase  |
      | Follicular Phase |
      | Ovulation Phase  |
      | Luteal Phase     |

  Scenario: Cycle tracking app information is visible
    Then Details about the 'Cycle Tracking App' and its features

  Scenario: Login buttons are visible
    Then Login button should be visible

  Scenario: Sign up buttons are visible
    Then Sign button should be visible

  Scenario: Images in Sync your weight loss journey section are displayed
    Then Images in Sync should be displayed

  Scenario: Informational text under Empower weight loss section
    Then Display information about 'hormonal shifts and metabolism during the menstrual cycle'

  Scenario: Layout and colors are loaded correctly
    Then User should see a light purple background and readable text content

  Scenario: Get started now button are visible
    Then Display a 'Get Started Now' button in the footer section

  Scenario: Start Your Personalized Journey is visible
    Then 'Start Your Personalized' button should be displayed
# Functional

  Scenario: Login button is clickable
    When User clicks log in button
    Then User should be navigated to auth page login tab

  Scenario: Sign up button is clickable
    When User clicks sign up button
    Then User should be navigated to auth page sign up tab

  Scenario: Get started now button are Clickable
    When User clicks Get Started button
    Then User should be navigated to auth page login tab

  Scenario: Start Your Personalized Journey is clickable
    When User clicks Start your personalized journey button
    Then User should be navigated to auth page login tab
