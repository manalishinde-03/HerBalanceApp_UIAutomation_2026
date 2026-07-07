Feature: DashBoard Page
Background:
	Given User is in login page and User is on the HerBalance auth page
	When User clicks login in button after entering  a valid credential
		
@Dp1
	Scenario: Title of the Dashboard
		Then User should see "Free Plan Dashboard" title
	
	Scenario: Profile Name visibility
		Then User should see user name on the top right side
	
	Scenario:  Profile icon Visibility
	 Then User should see profile icon near user name
	 
	Scenario: Notification Icon visibility 
		Then User should see bell icon for notification
	
	Scenario: Search bar visibility
		Then User should see search bar in dashboard
		
	Scenario Outline: Button text
		Then User Should see "<headings>"
		Examples:
			
			|headings                |
			|Activity Insights       |
			|Diet Plan               |
			|Workout                 |
			|Water Tracker           |
			|View Full Cycle Details |
			|Upload Blood Report     |
			|Upgrade to Premium      |
			|Generate 7-Day Plan     |
			|See Premium Plans       |
			
	Scenario Outline: Sub text for title
     Then User should see "<mainText>" and "<subText>"

		Examples:
  		| mainText                                 | subText                                                                 |
  		| Free Plan • Access to Basic Features     | Enjoy our free plan with basic features and exclusive workout planning  |
	
	Scenario: Sub text location
		Then Sub text should be located at the centre of the page
	
Scenario Outline: Section heading text
    Then User should see the following section "<heading>"

Examples:
    | heading                     |
    | Weight & Body Metrics       |
    | Health Conditions           |
    | Blood Report Insights       |
    | Menstrual Cycle Insights    |
    | Subscription Information    |

		
		
		
	Scenario Outline: Metrics Section Displays All Relevant Sub-sections
		Then the dashboard should display "<metric>"

			Examples:
  				| metric   		          |
  				| Starting                |
  				| Current                 |
  				| Height                  |
  				| BMI                     |
  				| BMI Reference Guide     |
	
		
	



	Scenario: Daily weigh in schedule display
	 	Then Daily weigh in should be - "Morning, before food"

	

	Scenario: Presence of slider in BMI reference guide
		Then  Slider should be present in BMI reference guide
		
	

	

	#Scenario Outline: BMI category labels
	#	Then the label "<category>" should be visible and color-coded correctly

#		Examples:
  #			| category     |
  #			| Underweight  |
  #			| Normal       |
  #			| Overweight   |
  #			| Obese        |


	
	Scenario: Slider is non-interactive
		Slider should not allow manual movement it should remain fixed based on the user BMI
		
	Scenario: Info label visibility	 
		Then Info label should be visible above the slider
		
	Scenario: Free plan note
	    Then Message “Free plan includes 7 days of limited tracking” should be displayed clearly below the slider
		
	Scenario: Presence of start / joined date of subcription
		Then Joined date should be displayed
		
	Scenario: Presence of todays date section
		Then Todays date should be display
		

	Scenario: Presence of Upgrade to Premium button		
		Then Upgrade to Premium button should be displayed	
		
	Scenario: Display Blood Report Insights based on report not uploaded
		Then User should see the Upload Blood Report button
		
	Scenario:  Upload report button 
		Then Upload Blood Report button should be enabled

	Scenario Outline: Sub menu in  profile name
		When User clicks on profile name
		Then User should see "<options>" profile name
		Examples:
			| options     |
			| Home        |
			| Edit profile|
			| Subscription|
			| Logout      |
			
	Scenario Outline: Sub menu in activity insights 
		When User clicks on activity Insight
		Then User should see "<options>" activity insights
		Examples:
			|options             |
			|Track Weight        |
			|Food Intake         |
			|Menstrual Phase Logs|
		
	Scenario: Navigation on diet plan	
		When User clicks on Diet plan button
		Then User should redirected to diet-plan page
		
		
	Scenario: Navigation on workout plan
		When User clicks on workout button
		Then User should redirected to workout page
		
	Scenario: Navigation on water Tracker plan
		When User clicks on water tracker button
		Then User should redirected to water tracker page
		
	Scenario: Navigation on View Full cycle details	
		When User clicks on View Full Cycle Details 
		Then User should be redirected to Menstrual cycle tracker page
		
	Scenario: Navigation on Upload Blood report	
		When User clicks on Upload Blood report
		Then User should be redirected to explorer to choose the file
		
	Scenario: Navigation on Upgrade premium 	
		When User clicks on  Upgrade to Premium 
		Then User should be redirected to Subcription page
		
	Scenario: Navigation on  generate 7-day Plan	
		When User clicks on Generate 7-Day Plan
		Then User should be redirected to diet-plan page
		
	Scenario: Navigation on see premium plans	
		When User clicks on See Premium Plans 
		Then User should be redirected to premium Subcription page
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	






	
 	