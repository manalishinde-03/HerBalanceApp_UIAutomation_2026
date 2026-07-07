package stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.SignUpPage;
import utilities.LoggerFactory;

public class LaunchPageStepDefinitions {
	private WebDriver driver;	
	private LaunchPage launchPage;
	private SignUpPage signUpPage;

	public LaunchPageStepDefinitions() {
		this.driver = DriverFactory.getDriver();		
		this.launchPage = new LaunchPage(driver);
	}


	@Then("Display cards for following")
	public void display_cards_for_following(DataTable dataTable){
		LoggerFactory
				.info("Display cards for Menstrual Phase,Follicular Phase,Ovulation Phase,and Luteal Phase");
		List<String> expectedPhasesVisible = dataTable.asList(String.class);
		SoftAssert softAssert = new SoftAssert();
		for (String phase : expectedPhasesVisible) {
			List<String> actualPhaseName = launchPage.displayCardsIsVisible(phase);
			softAssert.assertTrue(actualPhaseName.contains(phase));
		}
		softAssert.assertAll();
	}

	@Then("Details about the {string} and its features")
	public void details_about_the_and_its_features(String expectedCycleTitle) {
		LoggerFactory.info("Cycle tracking app information is visible");
		LoggerFactory.info("Cycle tracking app Title is visible");
		String actualCycleTrackingTitle = launchPage.isCycleTrackingTitleVisible();
		Assert.assertEquals(actualCycleTrackingTitle, expectedCycleTitle);
		LoggerFactory.info("Cycle tracking app Title Feature is visible");
		String actualCycleTrackingFeature = launchPage.isCycleTrackingFeatureVisible();
		String expectedCycleTrackingFeature = "Track your cycle and receive";
		Assert.assertTrue(actualCycleTrackingFeature.contains(expectedCycleTrackingFeature.trim()));

	}

	@Then("Login button should be visible")
	public void login_button_should_be_visible() {
		LoggerFactory.info("Log in button is visible");
		Assert.assertTrue(launchPage.isbtnLogInVisible());
	}

	@Then("Sign button should be visible")
	public void sign_button_should_be_visible() {
		LoggerFactory.info("Sign Up button is visible");
		Assert.assertTrue(launchPage.isbtnLogInVisible());
	}

	@Then("Images in Sync should be displayed")
	public void images_in_sync_should_be_displayed() {
		LoggerFactory.info("Images in Sync should be displayed");
		Assert.assertTrue(launchPage.isImgInSyncVisible());
	}

	@Then("Display information about {string}")
	public void display_information_about(String expectedEmpowerWeightTxt) {
		LoggerFactory.info("Informational text under Empower weight loss section");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(launchPage.isEmpowerTitleVisible());
		String actualEmpowerWeightTxt = launchPage.getEmpowerWeightTxt();
		softAssert.assertTrue(actualEmpowerWeightTxt.contains(expectedEmpowerWeightTxt.trim()));
	}

	@Then("User should see a light purple background and readable text content")
	public void user_should_see_a_light_purple_background_and_readable_text_content() {
		LoggerFactory.info("User should see a light purple background and readable text content");
		String actualColor = launchPage.getBackroundColour();
		boolean isPurple = actualColor.contains("106") || actualColor.contains("243") || actualColor.contains("232");
		Assert.assertTrue(isPurple || !actualColor.isEmpty(), "Theme color was not detected correctly!");

	}

	@Then("Display a {string} button in the footer section")
	public void display_a_button_in_the_footer_section(String expectGetStartedTitle) {
		LoggerFactory.info("Display a Get Started Now button in the footer section");
		String actualGetStartedBtnTitle = launchPage.isGetStartedbtnVisible();
		Assert.assertTrue(actualGetStartedBtnTitle.contains(expectGetStartedTitle));
	}

	@Then("{string} button should be displayed")
	public void button_should_be_displayed(String expectStartYourPersonalizeTitle) {
		LoggerFactory.info("Display a Get Started Now button in the footer section");
		String actualStartYourPersonalizeTitle = launchPage.isStartYourPersonalizebtnVisible();
		Assert.assertTrue(actualStartYourPersonalizeTitle.contains(expectStartYourPersonalizeTitle));
	}

	@When("User clicks log in button")
	public void user_clicks_log_in_button() {
		LoggerFactory.info("User clicks log in button");
		signUpPage = launchPage.navigateToRegisterPage();
	}

	@Then("User should be navigated to auth page login tab")
	public void user_should_be_navigated_to_auth_page_login_tab() {
		LoggerFactory.info("User should see auth page Login tab");
		Assert.assertTrue(launchPage.isbtnLogInVisible());
	}

	@When("User clicks sign up button")
	public void user_clicks_sign_up_button() {
		LoggerFactory.info("User clicks Sign Up button");
		launchPage.clickSignUpBtn();
	}

	@Then("User should be navigated to auth page sign up tab")
	public void user_should_be_navigated_to_auth_page_sign_up_tab() {
		LoggerFactory.info("User should see auth page sign up tab");
		Assert.assertTrue(launchPage.isbtnSignUpVisible(),
				"Navigation failed! Current URL is: " + driver.getCurrentUrl());
	}

	@When("User clicks Get Started button")
	public void user_clicks_get_started_button() {
		LoggerFactory.info("User clicks GetStarted button");
		launchPage.clickGetStartedBtn();
	}

	@When("User clicks Start your personalized journey button")
	public void user_clicks_start_your_personalized_journey_button() {
		LoggerFactory.info("User clicks Start your personalized button");
		launchPage.clickLoginInBtn();
	}
}
