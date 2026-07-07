@Subscription1
Feature: Subscription Management UI and Navigation Verification

Background:
  Given User clicks profile button labelled as "pam" after logged in from "Login_UserData"
 Given User is on the Her Balance Dashboard with the Profile submenu expanded

# ---------------- Subscription Management Page ----------------

Scenario: Verify Subscription Management page title
  When User clicks on Subscription
 Then Subscription Management should be visible

Scenario: Verify Subscription Management page section count
  When User clicks on Subscription
  Then exactly 3 sections should be visible

Scenario: Verify Subscription Management section headings
 When User clicks on Subscription
 Then the following section headings should be displayed
   | Current Subscription |
   | Subscription History |

# ---------------- Current Subscription Section ----------------

Scenario: Verify Current Subscription header and sub text
  When User clicks on Subscription
  Then the following header texts should be visible
    | Current Subscription |
    | Your active subscription plan details |

Scenario: Verify subheaders in Current Subscription section
  When User clicks on Subscription
  Then the following subheaders should be visible
    | Subscription Information |
    | Subscription Benefits    |

Scenario: Verify fields under Subscription Information
  When User clicks on Subscription
  Then the following subscription information fields should be visible
    | Status     |
    | Plan       |
    | Start Date |
    | End Date   |

Scenario: Verify Go to Dashboard button in Current Subscription section
  When User clicks on Subscription
  Then Go to Dashboard button should be visible
  Then Go to Dashboard button should be enabled

# Subscription Management Details and Navigation

  Scenario: Verify Subscription History header and subtext
    Given User is on the Her Balance Dashboard with the Profile submenu expanded
    When User clicks on Subscription
    Then "Subscription History" is displayed
    Then "Your Subscrpition payment history" is displayed

  Scenario: Verify visibility of subscription sections
    Given User is on the Her Balance Dashboard with the Profile submenu expanded
    When User clicks on Subscription
    Then Exactly 2 sections should be visible on page
    Then "Free Plan " and " Go to DashBoard " sections should be displayed

  Scenario: Verify Free Plan details visibility
    Given User is on the Her Balance Dashboard with the Profile submenu expanded
    When User clicks on Subscription
    Then "Start Date -End Date", "Cost in $", "Transaction Id", "Payment Method" should be visible

  Scenario: Verify navigation from Current Subscription section
    Given User is on Subscription Management Page
    When User Clicks on Go to Dashboard in Current Subscription section
    Then User should navigate to Free Plan Dashboard page

  Scenario: Verify navigation from Subscription History section
    Given User is on Subscription Management Page
    When User Clicks on Go to Dashboard in Subscription History section
    Then User should navigate to Free Plan Dashboard page

