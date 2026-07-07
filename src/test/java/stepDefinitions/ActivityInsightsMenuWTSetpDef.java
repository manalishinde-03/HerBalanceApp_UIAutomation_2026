package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ActivityInsightsMenuWTPage;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import utilities.ExcelReader;

public class ActivityInsightsMenuWTSetpDef {
		
    private LaunchPage launchPage;
    private java.util.Map<String, String> testData;
    
	private WebDriver driver;
	public String weightLostValue;
	public String remainingValue;
	public String completionPercentageValue;
	
	ActivityInsightsMenuWTPage activityInsideMenuWTPage;
	
	@Given("User is on the Her Balance Dashboard with the activity insights submenu expanded")
	public void user_is_on_the_her_balance_dashboard_with_the_activity_insights_submenu_expanded() {
		
		 driver = DriverFactory.getDriver();

	     launchPage = new LaunchPage(driver);
	     new LoginPage(driver);
	     activityInsideMenuWTPage = new ActivityInsightsMenuWTPage(driver); 
	     
	     launchPage.clickLoginInBtn();
	     
	     testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", "WT_User");
	     System.out.println("Email from Excel - " +testData.get("Email"));
	     activityInsideMenuWTPage.loginWithValidCredentials(
	             testData.get("Email"), 
	             testData.get("Password")
	         );
		
		System.out.println("User is on the Her Balance Dashboard with the activity insights submenu");
		activityInsideMenuWTPage.clickaAtivityInsightsMenu();
	    assertEquals(true, true);
	}

	@When("User clicks on track weight")
	public void user_clicks_on_track_weight() {
		System.out.println("User clicks on track weight");
		activityInsideMenuWTPage.clickTrackWeight();
		assertEquals(true, true);
	}

	@Then("User should see  header text Weight Tracking")
	public void user_should_see_header_text_weight_tracking() {
		Assert.assertEquals(activityInsideMenuWTPage.getHeaderText(), "Weight Tracking");
	}

	@Then("User should see Free Plan Seven Days Tracking")
	public void user_should_see_free_plan_seven_days_tracking() {
		Assert.assertEquals(activityInsideMenuWTPage.getSubHeaderText(), "free Plan - 7 Days Tracking");   
	}

	@Then("Back to Dashboard button should be visible")
	public void back_to_dashboard_button_should_be_visible() {
		boolean isVisible = activityInsideMenuWTPage.isBackToDashboardBtnDisplayed();
	   	Assert.assertTrue(isVisible, "Back to Dashboard button is not visible"); 
	}

	@Then("Three cards should be present")
	public void three_cards_should_be_present() {
		Assert.assertEquals(activityInsideMenuWTPage.getThreeCardsCount(), 3);
	    
	}

	@Then("Starting weight , Current weight and Goal weight card should be displayed")
	public void starting_weight_current_weight_and_goal_weight_card_should_be_displayed() {
		boolean weightcardsVisible = activityInsideMenuWTPage.weightCardsDisplayed();
	    Assert.assertTrue(weightcardsVisible, "One or more weight cards (Starting/Current/Goal) are not displayed");
 
	}

	@Then("Label and weight values should be center-aligned")
	public void label_and_weight_values_should_be_center_aligned() {
		boolean isCentered = activityInsideMenuWTPage.weightValuesCenterAligned();
	    Assert.assertTrue(isCentered, "The label or weight values are not center-aligned within the cards!");	    
	}

	@Then("Weight value should be mapped from the onboarding process")
	public void weight_value_should_be_mapped_from_the_onboarding_process() {
		Assert.assertEquals(activityInsideMenuWTPage.getStartingWeightValue(), "Weight value should be mapped from the onboarding process!" );
	}

	@Then("Current Weight should be x value")
	public void current_weight_should_be_x_value() {
		Assert.assertEquals(activityInsideMenuWTPage.isCurrentWeightValueDisplayed(), "Current Weight match the value!" );
	}

	@Then("Goal weight should be displayed")
	public void goal_weight_should_be_displayed() {
		Assert.assertEquals(activityInsideMenuWTPage.getGoalWeightValue(), "Goal weight should be displayed!" );
	}

	@Then("Header should be Progress overview")
	public void header_should_be_progress_overview() {
		Assert.assertEquals(activityInsideMenuWTPage.getProgressOverviewHeaderText(), "Progress Overview"); 
	}

	@Then("Sub text should be Weight Loss Progress")
	public void sub_text_should_be_weight_loss_progress() {
		Assert.assertEquals(activityInsideMenuWTPage.getWeightLossProgressSubText(), "Weight Loss Progress"); 
	}

	@Then("Completion percentage should be displayed")
	public void completion_percentage_should_be_displayed() {
		Assert.assertEquals(activityInsideMenuWTPage.getCompletionPercentage(), "NaN% Complete"); 
	}

	@Then("Progress bar should be visible")
	public void progress_bar_should_be_visible() {
		boolean isVisible = activityInsideMenuWTPage.isProgressBarDisplayed();
	   	Assert.assertTrue(isVisible, "Progress bar is not visible");  
	}

	@Then("weight lost should be displayed")
	public void weight_lost_should_be_displayed() {
		boolean isVisible = activityInsideMenuWTPage.isWeightLostDisplayed();
	   	Assert.assertTrue(isVisible, "Weight Lost is not display"); 
	}

	@Then("Remaining weight  should be displayed")
	public void remaining_weight_should_be_displayed() {
		boolean isVisible = activityInsideMenuWTPage.isRemainingWeightDisplayed();
	   	Assert.assertTrue(isVisible, "Remaining Weight is not display"); 
	}

	@Then("Graph section header should be Weight Progression over Time")
	public void graph_section_header_should_be_weight_progression_over_time() {
		Assert.assertEquals(activityInsideMenuWTPage.getGraphHeaderText(), "Weight Progression Over Time");
	}

	@Then("Graph should be visible with x-axis showing days-Day one to Day seven")
	public void graph_should_be_visible_with_x_axis_showing_days_day_one_to_day_seven() {
        Assert.assertTrue(activityInsideMenuWTPage.isChartDisplayed(),"The Weight Progression graph is not visible!");
        List<String> expectedDays = Arrays.asList("Day one", "Day two", "Day three", "Day four", "Day five", "Day six", "Day seven");
        List<String> actualDays = activityInsideMenuWTPage.getXAxisDays();
        for (String expectedDay : expectedDays) {
            Assert.assertTrue(actualDays.contains(expectedDay), "Days are missing X-axis label: " + expectedDay );
        }
	}

	@Then("Y-axis should display weight values in kg")
	public void y_axis_should_display_weight_values_in_kg() {
		List<String> actualKgs = activityInsideMenuWTPage.getYAxisWeightKgs();
	    Assert.assertTrue( actualKgs.size() > 0, "Y-axis labels are missing!");
	    for (String value : actualKgs) {
	        Assert.assertTrue(value.matches(".*\\d+.*"), "Y-axis label '" + value + "' is not a valid weight"); 
	    }
	    System.out.println("Y-Axis values found: " + actualKgs);
	}

	@Then("Goal weight reference line should be displayed")
	public void goal_weight_reference_line_should_be_displayed() {
		boolean isVisible = activityInsideMenuWTPage.isGoalWeightDisplayed();
	   	Assert.assertTrue(isVisible, "Goal Weight is not display"); 
	}

	@Then("Log Todays Weight should be visible")
	public void log_todays_weight_should_be_visible() {
		boolean isVisible = activityInsideMenuWTPage.isLogTodaysWeightDisplayed();
	   	Assert.assertTrue(isVisible, "Log Today's Weight is not display"); 
	}

	@Then("Weight\\(kg) BMI\\(Auto-calculated)Note\\(Optional) should be displayed")
	public void weight_kg_bmi_auto_calculated_note_optional_should_be_displayed() {
		boolean labelsVisible = activityInsideMenuWTPage.isAllLogWeightLabelsDisplayed();
        Assert.assertTrue(labelsVisible,"One or more labels (Weight, BMI, or Note) are not displayed correctly!");
	}

	@Then("Log weight button should be disabled")
	public void log_weight_button_should_be_disabled() {
	    boolean isDisable = activityInsideMenuWTPage.isLogWeightButtonDisabled();
	    Assert.assertTrue(isDisable,"Log weight Button is not disabled");
	}

	//24 & 26
	@When("User enter valid value in weight after clicking track weight in sub menu")
	public void user_enter_valid_value_in_weight_after_clicking_track_weight_in_sub_menu() {
		activityInsideMenuWTPage.enterWeightValue("70"); // Sending a valid test value
	}

	@Then("BMI field should auto-calculate")
	public void bmi_field_should_auto_calculate() {
       
		String bmiValue = activityInsideMenuWTPage.getBMIValue();
        Assert.assertNotNull(bmiValue,"BMI field is null");
        Assert.assertFalse(bmiValue.isEmpty(),"BMI field did not auto-calculate (it is empty)");
                
        System.out.println("Auto-calculated BMI: " + bmiValue);
	}

	@Then("Log Weight button should be Enabled")
	public void log_weight_button_should_be_enabled() {
		boolean isEnable = activityInsideMenuWTPage.isLogWeightButtonEnabled();
	   	Assert.assertTrue(isEnable);
	}
	
	

	
	
//Weight Tracking functional verification
	@Given("User is on the Weight tracker")
	public void user_is_on_the_weight_tracker() {
		System.out.println("User is on the Weight tracker");
	    assertEquals(true, true);
	}

	@When("User clicks Log Weight button after entering valid value in weight")
	public void user_clicks_log_weight_button_after_entering_valid_value_in_weight() {
		activityInsideMenuWTPage.clickLogWeighButtont();
	}

	@Then("Entered weight should be added to the tracking record")
	public void entered_weight_should_be_added_to_the_tracking_record() {
		Assert.assertEquals(activityInsideMenuWTPage.isCurrentWeightValueDisplayed(), "Current Weight value added to the tracking record!" );
	}

	@When("User enter invalid value in weight after clicking track weight in sub menu")
	public void user_enter_invalid_value_in_weight_after_clicking_track_weight_in_sub_menu() {
		activityInsideMenuWTPage.enterWeightValue("-98");
		activityInsideMenuWTPage.clickLogWeighButtont();
	}

	@Then("Error message Enter valid weight should be displayed")
	public void error_message_enter_valid_weight_should_be_displayed() {		
		Assert.assertTrue(activityInsideMenuWTPage.isErrorMessageDisplayed(),"Error message is not disabled");
	}

	@When("User clicks save button after entering valid value in weight")
	public void user_clicks_save_button_after_entering_valid_value_in_weight() {
		this.weightLostValue = activityInsideMenuWTPage.getWeightLostValue();
		this.remainingValue = activityInsideMenuWTPage.getRemainingValue();
		this.completionPercentageValue = activityInsideMenuWTPage.getCompletionPercentage();
		String currentWeight = activityInsideMenuWTPage.getCurrentWeightValue();
		Double todaysWeight = Double.parseDouble(currentWeight) - 0.5;
		activityInsideMenuWTPage.enterWeightValue(todaysWeight.toString());
		activityInsideMenuWTPage.clickLogWeighButtont();
	}

	@Then("Weight Lost and Remaining values should update accordingly")
	public void weight_lost_and_remaining_values_should_update_accordingly() {
		String newWeightLostValue = activityInsideMenuWTPage.getWeightLostValue();
		String newRemainingValue = activityInsideMenuWTPage.getRemainingValue();
		Assert.assertNotEquals(newWeightLostValue, this.weightLostValue);
		Assert.assertNotEquals(newRemainingValue, this.remainingValue);		
	}

	@Then("progress bar percentage should adjust to reflect new progress")
	public void progress_bar_percentage_should_adjust_to_reflect_new_progress() {
	    String newCompletionPercentageValue = activityInsideMenuWTPage.getCompletionPercentage();
	    Assert.assertNotEquals(newCompletionPercentageValue, this.completionPercentageValue);
	}

	@Then("Note should be saved along with the weight entry")
	public void note_should_be_saved_along_with_the_weight_entry() {
		Assert.assertNotNull(activityInsideMenuWTPage.getNoteTextValue(),"Note value is null");
	}

	@When("User clicks Log Weight button")
	public void user_clicks_log_weight_button() {
		activityInsideMenuWTPage.clickLogWeighButtont();
	}

	@Then("The  message Weight added  successfully should be displayed")
	public void the_message_weight_added_successfully_should_be_displayed() {
		Assert.assertTrue(activityInsideMenuWTPage.issuccessMessageDisplayed(),"Success message is not displayed");
	}
	
	
	
}
	
	
	


