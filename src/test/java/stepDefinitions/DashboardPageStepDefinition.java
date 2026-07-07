package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DashboardPage;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;

import pageObjects.OnboardingPage;
import utilities.ExcelReader;

public class DashboardPageStepDefinition {
	 
	private WebDriver driver;
	
	
	private LaunchPage launchPage;
	private LoginPage loginPage;
	private OnboardingPage onboardingPage;
	private DashboardPage dashboardpage;
	
	Map<String, String> testData;


	 @Before
	    public void setup() {

	        driver = DriverFactory.getDriver();

	        launchPage = new LaunchPage(driver);
	        loginPage = new LoginPage(driver);
	        dashboardpage = new DashboardPage(driver);
	    }
	
	
	@Given("User is in login page and User is on the HerBalance auth page")
	public void user_is_in_login_page_and_user_is_on_the_her_balance_auth_page() {
		launchPage.clickLoginInBtn();
		String testcaseKey = "Login";
		launchPage.clickLoginInBtn();
		testData = ExcelReader.getData("DashboardLogin", testcaseKey);
		onboardingPage = (OnboardingPage) loginPage.loginWithValidCredentials(testData.get("Email"),
				testData.get("Password"));
		
		driver = DriverFactory.getDriver();
	
		
	}

	@When("User clicks login in button after entering  a valid credential")
	public void user_clicks_login_in_button_after_entering_a_valid_credential() {
		
		String testcaseKey = "Login";
		launchPage.clickLoginInBtn();
		testData = ExcelReader.getData("DashboardLogin", testcaseKey);
		onboardingPage = (OnboardingPage) loginPage.loginWithValidCredentials(testData.get("Email"),
				testData.get("Password"));
		
		driver = DriverFactory.getDriver();
	    dashboardpage = new DashboardPage(driver);
		
		
	
	}

	@Then("User should see {string} title")
	public void user_should_see_title(String expected_title) {
		String actual_title=dashboardpage.getDashboardTitle();
		Assert.assertEquals(actual_title, expected_title,
				 "Dashboard title does NOT match the expected value");
	} 


	@Then("User should see user name on the top right side")
	public void user_should_see_user_name_on_the_top_right_side() {
		 Assert.assertTrue(dashboardpage.isProfileNameVisible(),
				 "profile name is not visible on the dashboard");
	}

	@Then("User should see profile icon near user name")
	public void user_should_see_profile_icon_near_user_name() {
		 Assert.assertTrue(dashboardpage.isProfileIconVisible(),
		            "profile icon is not visible on the dashboard");
	    
	}

	@Then("User should see bell icon for notification")
	public void user_should_see_bell_icon_for_notification() {
		 Assert.assertTrue(dashboardpage.isNotificationBellVisible(),
		            "Notification bell icon is not visible on the dashboard");

	}

	@Then("User should see search bar in dashboard")
	public void user_should_see_search_bar_in_dashboard() {
		 Assert.assertTrue(dashboardpage.isSearchBarVisible(),
		            "Search bar is not visible on the dashboard");  
	}
	@Then("User Should see {string}")
	public void user_should_see(String heading) {
		 
		    Assert.assertTrue(dashboardpage.isHeadingVisible(heading), heading + " is not visible");

	}

	@Then("User should see {string} and {string}")
	public void user_should_see_and(String expectedMain, String expectedSub)
	{
			Assert.assertTrue(dashboardpage.areBothTextsVisible(),"Either main text or sub text is NOT visible");

		    

	}
	

	@Then("Sub text should be located at the centre of the page")
	public void sub_text_should_be_located_at_the_centre_of_the_page() {
			Assert.assertTrue(dashboardpage.isSubTextCentered(),"Text is not centered");
	}

	@Then("User should see the following section {string}")
	public void user_should_see_the_following_section_headings(String expectedHeading) {
		 Assert.assertTrue(dashboardpage.isSectionHeadingVisible(expectedHeading),
				        "Heading not found " + expectedHeading);
	}



	@Then("the dashboard should display {string}")
	public void the_dashboard_should_display(String expectedMetric) {
		  Assert.assertTrue(dashboardpage.isMetricDisplayed(expectedMetric),"Metric not found " + expectedMetric);


	}
	
	@Then("Daily weigh in should be - {string}")
	public void daily_weigh_in_should_be(String expectedText) {
		  String actualText = dashboardpage.dailyWeightIn();

	Assert.assertEquals(actualText.trim(),expectedText.trim(),"Daily Weigh-in text does not match the expected value");

	}

	


	
	@Then("Slider should be present in BMI reference guide")
	public void Slider_should_be_present_in_BMI_reference_guide()
	{
		Assert.assertTrue(dashboardpage.isBMISliderPresent(),"BMI slider is NOT present");

	}
	
	@Then("Slider should not allow manual movement it should remain fixed based on the user BMI")
	public void Slider_should_not_allow_manual_movement_it_should_remain_fixed_based_on_the_user_BMI()
	{
		Assert.assertFalse(dashboardpage.isSliderNonInteractive(),"Slider is interactive");

	}
	
	
	@Then("Info label should be visible above the slider")
	public void info_label_should_be_visible_above_the_slider() {
		
		Assert.assertTrue(dashboardpage.isInfoLabelVisible(),"Info label is NOT visible");
	}

	@Then("Message “Free plan includes {int} days of limited tracking” should be displayed clearly below the slider")
	public void message_free_plan_includes_days_of_limited_tracking_should_be_displayed_clearly_below_the_slider(Integer int1) {
		
		Assert.assertTrue(dashboardpage.isFreePlan7DayTextVisible(),"Free plan message is NOT visible");

	}

	@Then("Joined date should be displayed")
	public void joined_date_should_be_displayed() {
		 String date = dashboardpage.getJoinedDate();
		    Assert.assertFalse(date.isEmpty(), "Joined Date is not displayed");


	}
	@Then("Todays date should be display")
	public void todays_date_should_be_display() {
		String date = dashboardpage.gettodayDate();
	    Assert.assertFalse(date.isEmpty(), "Today's Date is not displayed");
	}
	
	@Then("Upgrade to Premium button should be displayed")
	public void Upgrade_to_Premium_button_should_be_displayed() {

	    Assert.assertTrue(dashboardpage.isUpgradeToPremiumButtonVisible(),"button is not displayed" );
	   
	}

	@When("User clicks on profile name")
	public void user_clicks_on_profile_name() {
	    dashboardpage.clickprofile();
	}

	@Then("User should see {string} profile name")
	public void user_should_see_profile_name(String expectedOption) {

	    boolean actual = dashboardpage.isProfileOptionPresent(expectedOption);

	    Assert.assertTrue(actual,"Expected submenu option '" + expectedOption + "' was not found under Profile Name");
	}

	@When("User clicks on activity Insight")
	public void user_clicks_on_activity_insight() {
	    dashboardpage.clickActivity();
	}
	
	@Then("User should see {string} activity insights")
	public void user_should_see_activity_insights(String expectedOption) {

	    boolean actual = dashboardpage.clickActivityInsights(expectedOption);

	    Assert.assertTrue(actual,
	        "Expected submenu option '" + expectedOption + "' was not found under Activity Insights"
	    );
	}




	@Then("User should see the Upload Blood Report button")
	public void user_should_see_the_Upload_Blood_Report_button() {
		Assert.assertTrue(
		        dashboardpage.isUploadBloodReportButtonVisible(),
		        "Subscription plan is not visible on the dashboard");
		   
		

	}
	@Then("Upload Blood Report button should be enabled")
	public void upload_blood_report_button_should_be_enabled() {
		Assert.assertTrue(
		        dashboardpage.isUploadBloodReportButtonEnabled(),
		        "Button not enabled");
	}


	

	@When("User clicks on Diet plan button")
	public void user_clicks_on_diet_plan_button() {
		 dashboardpage.clickDietPlan();
   
	}


	@Then("User should redirected to diet-plan page")
	public void user_should_redirected_to_diet_plan_page() {
		 String actualText = dashboardpage.IsDietplanPageNavigated();
       System.out.println(actualText);
		    Assert.assertEquals(actualText.trim(),"Create Your Diet Plan",
		        "User is not on the Diet Plan page");

	}

	@When("User clicks on workout button")
	public void user_clicks_on_workout_button() {
		 dashboardpage.clickWorkout();

	}

	@Then("User should redirected to workout page")
	public void user_should_redirected_to_workout_page() {
		String actualText = dashboardpage.IsWorkoutPageNavigated();

	    Assert.assertEquals(actualText.trim(),"Daily Workout Plan",
	    		"User is not on the Workout page");

	}

	@When("User clicks on water tracker button")
	public void user_clicks_on_water_tracker_button() {
		dashboardpage.clickWaterTracker();

	}

	@Then("User should redirected to water tracker page")
	public void user_should_redirected_to_water_tracker_page() {
		String actualText = dashboardpage.IsWaterTrackerPageNavigated();

	    Assert.assertEquals(actualText.trim(),"Water Tracker",
	        "User is not on the Water Tracker page");
	    

	}

	@When("User clicks on View Full Cycle Details")
	public void user_clicks_on_view_full_cycle_details() {
		dashboardpage.clickFullCycleDetails();

	}

	@Then("User should be redirected to Menstrual cycle tracker page")
	public void user_should_be_redirected_to_menstrual_cycle_tracker_page() {
	    String actualText = dashboardpage.IsMenstrualPageNavigated();

	    Assert.assertEquals(actualText.trim(),"Menstrual Cycle Tracker",
	    		 "User is not on the Menstrual Cycle Tracker page" );
	        
	 }

	@When("User clicks on Upload Blood report")
	public void user_clicks_on_upload_blood_report() {
		 dashboardpage.clickUploadBloodReport();

	}

	@Then("User should be redirected to explorer to choose the file")
	public void user_should_be_redirected_to_explorer_to_choose_the_file() 
	{
		Assert.assertTrue(dashboardpage.isUploadBloodReportButtonEnabled(),
	        "Upload Blood Report button did not click");
		
	        

	}

	@When("User clicks on  Upgrade to Premium")
	public void user_clicks_on_upgrade_to_premium() {
		 dashboardpage.clickUpgradeToPremium();

	   
	}

	@Then("User should be redirected to Subcription page")
	public void user_should_be_redirected_to_subcription_page() {
		String actualText = dashboardpage.SubscribtionPageNavigated();
System.out.print(actualText);
	    Assert.assertEquals(actualText,"Choose Your Transformation Journey",
	        "User is not on the Transformation page");
	   
  
	}

	@When("User clicks on Generate {int}-Day Plan")
	public void user_clicks_on_generate_day_plan(Integer int1) {
		 dashboardpage.clickGenerate7DayPlan();

	}

	@Then("User should be redirected to diet-plan page")
	public void user_should_be_redirected_to_diet_plan_page() {
		 String actualText = dashboardpage.clickGenerate7DayPlanPageNavigated();

		    Assert.assertEquals(actualText,"Create Your Diet Plan",
		        "User is not on the Diet Plan page");

	}
	@When("User clicks on See Premium Plans")
	public void user_clicks_on_see_premium_plans() {
		dashboardpage.clickSeePremiumPlans();

	}

	

	@Then("User should be redirected to premium Subcription page")
	public void user_should_be_redirected_to_premium_subcription_page() {
	    
		String actualText = dashboardpage.clickSeePremiumPlansPageNavigated();

	    Assert.assertEquals(actualText,"Choose Your Transformation Journey",
	        "User is not on page");
		
	}

	

}
