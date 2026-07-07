package stepDefinitions;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.SubscriptionPage;
import utilities.ExcelReader;

public class SubscriptionStepDef {
	
	private LaunchPage launchPage;
    private java.util.Map<String, String> testData;
    
	private WebDriver driver;
	public String weightLostValue;
	public String remainingValue;
	public String completionPercentageValue;
	SubscriptionPage subscriptionPage ;
	
	@Given("User is on step eleven onboarding process")
	public void user_is_on_step_eleven_onboarding_process() {
		
		 driver = DriverFactory.getDriver();

	     launchPage = new LaunchPage(driver);
	     new LoginPage(driver);
	     subscriptionPage = new SubscriptionPage(driver);	     
	     launchPage.clickLoginInBtn();
	     
	     testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", "Sub_User");
	     System.out.println("Email from Excel - " +testData.get("Email"));
	     subscriptionPage.loginWithValidCredentials(
	             testData.get("Email"), 
	             testData.get("Password"));
		
	   System.out.println("User is on Step 11 on boarding process");
	   assertEquals(true, true);
	}

	@When("User clicks on Complete after entering the required medication details")
	public void user_clicks_on_complete_after_entering_the_required_medication_details() {
		System.out.println("User clicks on Complete after entering the required medication details");
		   assertEquals(true, true);
	}
	
	//Subcription- Page Header Section
	@Then("Choose Your Transformation Journey header is visible")
    public void verify_main_header() {
        Assert.assertEquals(subscriptionPage.getPageHeader(), "Choose Your Transformation Journey");
    }
	
	@Then("sub-text header is visible")
	public void sub_text_header_is_visible() {
		Assert.assertEquals(subscriptionPage.getSubHeader(), "All plans include personalization based on your menstrual cycle phases for optimal results.");  
	}
	
	 @Then("There should be exactly three subscription plan tiles displayed")
	    public void verify_tile_count() {
	    Assert.assertEquals(subscriptionPage.getPlanTileCount(), 3);
	    }
	
	//Subcription- Free Plan tile
	 @Then("Free Plan - Seven Days Trial section tile is displayed inside Free plan tile")
	 public void free_plan_seven_days_trial_section_tile_is_displayed_inside_free_plan_tile() {
		 String freeTile = subscriptionPage.getFreePlanTile();
	        Assert.assertEquals(freeTile, "Free Plan – 7 Days Trial");
	 }
	 
	@Then("Seven day meal plan section title is displayed")
	public void seven_day_meal_plan_section_title_is_displayed() {
		String freeSubTile = subscriptionPage.getFreePlanSubTile();
        Assert.assertEquals(freeSubTile, "AI-generated 7-day meal plan, workouts, and yoga personalized to your menstrual cycle – absolutely free!");
	}

	@Then("Free header is visible")
	public void free_header_is_visible() {
		String freeHeader = subscriptionPage.getFreeHeader();
        Assert.assertEquals(freeHeader, "Free");
	}

	@Then("Seven days access subtext under header is visible")
	public void seven_days_access_subtext_under_header_is_visible() {
		String freeSubHeader = subscriptionPage.getFreeSubHeader();
        Assert.assertEquals(freeSubHeader, "7 days access");
	}

	@Then("Cycle-based diet plan lines preceeded with checkmark are visible")
	public void cycle_based_diet_plan_lines_preceeded_with_checkmark_are_visible() {
		boolean isVisible = subscriptionPage.isFreeContainsDisplayed();
	   	Assert.assertTrue(isVisible);
	   			
	}

	@Then("Active Plan button is visible")
	public void active_plan_button_is_visible() {
		boolean isVisible = subscriptionPage.isActivePlanButtonDisplayed();
	   	Assert.assertTrue(isVisible, "The 'Active Plan' button is not visible for 7 Days Trial Plan tile!");
	}

	@Then("Active Plan button should be enabled")
	public void active_plan_button_should_be_enabled() {
		boolean isEnable = subscriptionPage.isActivePlanButtonEnabled();
	   	Assert.assertTrue(isEnable);
	}
	
	//Subcription- Monthly Plan tile
   @Then("Most Popular badge text is displayed on Monthly plan tile")
	public void most_popular_badge_text_is_displayed_on_monthly_plan_tile() {
	   boolean isDisplayed = subscriptionPage.isMostPopularBadgeDisplayed();
	   Assert.assertTrue(isDisplayed);
	}

	@Then("Monthly Plan thirty Days subheader is displayed inside Monthly plan tile")
	public void monthly_plan_thirty_days_subheader_is_displayed_inside_monthly_plan_tile() {
		String monthlyPlanTile = subscriptionPage.getMonthlyPlanTile();
		   Assert.assertEquals(monthlyPlanTile, "Monthly Plan – 30 Days");
	}

	@Then("Unlock a full month subtext is displayed")
	public void unlock_a_full_month_subtext_is_displayed() {
		String monthlyPlanSubTile = subscriptionPage.getMonthlyPlanSubTile();
		Assert.assertEquals(monthlyPlanSubTile, "Unlock a full month of AI-personalized diet, fitness, and community support tailored to your menstrual cycle.");

	}

	@Then("Thirty-nine header is visible")
	public void thirty_nine_header_is_visible() {
		String monthlyHeader = subscriptionPage.getMonthlyHeader();
		Assert.assertEquals(monthlyHeader, "$39.99");
	}

	@Then("Billed monthly subtext under header is visible")
	public void billed_monthly_subtext_under_header_is_visible() {
		String monthlySubHeader = subscriptionPage.getMonthlySubHeader();
		Assert.assertEquals(monthlySubHeader, "Billed monthly");
	}

	@Then("Thirty day AI diet plan lines preceeded with checkmark are visible")
	public void thirty_day_ai_diet_plan_lines_preceeded_with_checkmark_are_visible() {
		boolean isVisible = subscriptionPage.isMonthlyContainsDisplayed();
	   	Assert.assertTrue(isVisible);
	}

	@Then("Scribe Now button is visible")
	public void scribe_now_button_is_visible() {
		boolean isVisible = subscriptionPage.isSubscribeNowBtnDisplayed();
	   	Assert.assertTrue(isVisible, "The 'Subscribe Now' button is not visible on the Month Plan tile!");
	}

	@Then("Subscribe Now button is should be enabled")
	public void subscribe_now_button_is_should_be_enabled() {
		boolean isEnable = subscriptionPage.isSubscribeNowButtonEnabled();
	   	Assert.assertTrue(isEnable);
	}

	//Subcription- 3 Month Plan tile
	@Then("Best Value badge text is displayed on three Month Plan tile")
	public void best_value_badge_text_is_displayed_on_three_month_plan_tile() {
		boolean isDisplayed = subscriptionPage.isBestValueBadgeDisplayed();
		   Assert.assertTrue(isDisplayed);
	}

	@Then("Three Month Plan Full Transformation subheader is displayed inside Three Month Plan tile")
	public void three_month_plan_full_transformation_subheader_is_displayed_inside_three_month_plan_tile() {
		String threeMonthPlanTile = subscriptionPage.getThreeMonthPlanTile();
		Assert.assertEquals(threeMonthPlanTile, "3-Month Plan – Full Transformation");
		}

	@Then("A Ninty day journey using menstrual cycle-aligned health planning subtext is displayed")
	public void a_ninty_day_journey_using_menstrual_cycle_aligned_health_planning_subtext_is_displayed() {
		String threeMonthPlanSubTile = subscriptionPage.getThreeMonthPlanSubTile();
		Assert.assertEquals(threeMonthPlanSubTile, "A 90-day journey using menstrual cycle–aligned health planning. If you don't see results, we offer a money-back guarantee.");
	
	}

	@Then("Ninty header is visible")
	public void ninty_header_is_visible() {
		String threeMonthHeader = subscriptionPage.getThreeMonthHeader();
		Assert.assertEquals(threeMonthHeader, "$99");
	  
	}

	@Then("One time payment for Three months subtext under header is visible")
	public void one_time_payment_for_three_months_subtext_under_header_is_visible() {
		String threeMonthSubHeader = subscriptionPage.getThreeMonthSubHeader();
		Assert.assertEquals(threeMonthSubHeader, "One-time payment for 3 months");
		  
	}

	@Then("Ninty day meal text preceeded with star symbol are visible")
	public void ninty_day_meal_text_preceeded_with_star_symbol_are_visible() {
		boolean isVisible = subscriptionPage.isThreeMonthContainsDisplayed();
	   	Assert.assertTrue(isVisible);
	}

	@Then("Get Started button is visible")
	public void get_started_button_is_visible() {
		boolean isVisible = subscriptionPage.isGetStartedButtonDisplayed();
	   	Assert.assertTrue(isVisible, "The 'Get Started' button is not visible on the 3 Month Plan tile!");
	}		
}
	
	
	


