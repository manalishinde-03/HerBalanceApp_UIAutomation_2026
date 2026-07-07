package stepDefinitions;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import factory.DriverFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.SignUpPage;
import utilities.ExcelReader;
import utilities.LoggerFactory;

public class LoginPageStepDefinitions {
	private LoginPage loginPage;
	private LaunchPage launchPage;
	private SignUpPage signUpPage;
	private WebDriver driver;
	Map<String, String> testData;

	public LoginPageStepDefinitions() {
		this.driver = DriverFactory.getDriver();		
		this.launchPage = new LaunchPage(driver);
		this.loginPage = new LoginPage(driver);
		this.signUpPage= new SignUpPage(driver);

	}

	@When("User clicked login button in home page")
	public void user_clicked_login_button_in_home_page() {	
		LoggerFactory.info("User clicked login button in home page");
		loginPage = launchPage.navigateToAuthPage();
	}

	@When("User enters login details for {string} from {string}")
	public void user_enters_login_details_for_from(String scenarioName, String sheetName) {
		LoggerFactory.info("User enters login details for validEnteries from loginsheet");
		testData = ExcelReader.getData(sheetName, scenarioName);
		loginPage.registerWithValidData(testData.get("Email"), testData.get("Password"));
	}

	@When("User clicks the login button")
	public void user_clicks_the_login_button() {
		LoggerFactory.info("User clicks the login button");
		loginPage.clickLoginBtn();

	}
	@Then("HerBalance logo should be visible on the top-left corner")
	public void her_balance_logo_should_be_visible_on_the_top_left_corner() {
		LoggerFactory.info("HerBalance logo should be visible on the top-left corner");
	    Assert.assertTrue(loginPage.isHerLogoVisible(),"HerBalance logo not visible on the top-left corner");
	}

	@Then("Both {string} and {string} tabs should be visible")
	public void both_and_tabs_should_be_visible(String loginTab, String signUpTab) {
		LoggerFactory.info("Both Login and SignUp tabs should be visible");
	    Assert.assertTrue(loginPage.isloginBtnTabVisible(), "Login tab not visible!");
	    Assert.assertTrue(loginPage.issignUpBtnTabVisible(), "Sign Up tab not visible!");
	}

	@Then("“Login” tab should be active by default")
	public void login_tab_should_be_active_by_default() {
		LoggerFactory.info("Login” tab should be active by default");
	    Assert.assertEquals(loginPage.isLoginTabActive(),"active");
	}

	@Then("Email ID input field with placeholder {string} should be visible")
	public void email_id_input_field_with_placeholder_should_be_visible(String expectedEmail) {
	    Assert.assertEquals(loginPage.verifyEmailIdPresences(),expectedEmail);
	}

	@Then("Password input field with masked entry should be visible")
	public void password_input_field_with_masked_entry_should_be_visible() {
		LoggerFactory.info("Password input field with masked entry should be visible");
		String attributeValue = loginPage.verifyPasswrdPresences();
	    Assert.assertEquals(attributeValue, "password", "The password field was not found or not masked!");
	}

	@Then("“Show password” should be visible")
	public void show_password_should_be_visible() {
		LoggerFactory.info("Show password” should be visible");
	    Assert.assertTrue(loginPage.isShowpwdVisible(),"“Show password” not visible");
	}

	@Then("“Forgot password?” link should be visible")
	public void forgot_password_link_should_be_visible() {
		LoggerFactory.info("Forgot password?” link should be visible");
		Assert.assertTrue(loginPage.isForgotpwdVisible(),"“Forgot password?” link not visible");
	}

	@Then("“Sign Up” link should be visible and navigates to the Sign-Up page")
	public void sign_up_link_should_be_visible_and_navigates_to_the_sign_up_page() {
		LoggerFactory.info("Sign Up” link should be visible and navigates to the Sign-Up page");
		SoftAssert softAssert = new SoftAssert();
		loginPage.clickSignUpTab();
		String currentUrl = driver.getCurrentUrl();
		softAssert.assertTrue(currentUrl.contains("register") || currentUrl.contains("auth"),
				"Navigation failed! Current URL is: " + currentUrl);
		boolean fieldsLoaded = loginPage.isSignUpPageLoaded();
		softAssert.assertTrue(fieldsLoaded, "Sign Up fields did not load even though navigation worked");
		softAssert.assertAll();
	}

	@Then("The right section should display “Cycle-Synced Plans”, “Blood Work Analysis”, and “Personalized Dashboard” content")
	public void the_right_section_should_display_cycle_synced_plans_blood_work_analysis_and_personalized_dashboard_content() {
	   LoggerFactory.info("The right section should display “Cycle-Synced Plans”, “Blood Work Analysis”, and “Personalized Dashboard” content");
	   List<String> actualCards = loginPage.getAllCards();
	   Assert.assertTrue(actualCards.contains("Cycle-Synced Plans"),"Cycle-Synced Plans not displayed");
	   Assert.assertTrue(actualCards.contains("Blood Work Analysis"),"Blood Work Analysis not displayed");
	   Assert.assertTrue(actualCards.contains("Personalized Dashboard"),"Personalized Dashboard not displayed");
	}

	@Then("^A testimonial with text and name \\(e\\.g\\., “Sarah, \\{int\\}”\\) should be displayed$")
	public void a_testimonial_with_text_and_name_e_g_sarah_should_be_displayed(Integer age) {
		LoggerFactory.info("A testimonial with text and name \\\\(e.g., “Sarah, {int}”) should be displayed");
	     Assert.assertTrue(loginPage.getTestimonialTxt(age),"testimonial text sarah 34 not displayed");
	}

	@Then("There should be exactly {int} tabs — “Login” and “Sign Up”")
	public void there_should_be_exactly_tabs_login_and_sign_up(Integer expectedCount) {
		LoggerFactory.info("There should be exactly {int} tabs — “Login” and “Sign Up");
		Assert.assertEquals(loginPage.getTotalTabs(),(int)expectedCount,"Tabs count mismatch");
	}
	@Then("Radio button for show password")
	public void radio_button_for_show_password() {
		LoggerFactory.info("Radio button for show password");
	    Assert.assertTrue(loginPage.isShowpwdVisible(),"Radio button for show password not Visible");
	}
	@Then("There should be exactly {int} input field")
	public void there_should_be_exactly_input_field(Integer expectedCount) {
		LoggerFactory.info("There should be exactly {int} input field");
		Assert.assertEquals(loginPage.getTotalInputFields(),(int)expectedCount,"Input field count mismatch");
	}


	@Then("The login Error should be displayed for {string}")
	public void the_login_error_should_be_displayed_for(String scenarioName) {
		LoggerFactory.info("The login Error should be displayed for ExpectedScenarios");
		String expectedContent = testData.get("ExpectedError").trim();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if (expectedContent.equalsIgnoreCase("Onboardpage") || expectedContent.equalsIgnoreCase("Onboarding")) {
			boolean redirected = wait.until(ExpectedConditions.urlContains("onboard"));
			Assert.assertTrue(redirected, "Failed to redirect! Current URL: " + driver.getCurrentUrl());
		} else {
			List<String> actualErrors = loginPage.getAllErrorMessages();
			String[] expectedParts = expectedContent.split(",");
			for (String part : expectedParts) {
				String cleanExpected = part.trim().toLowerCase();
				boolean matchFound = actualErrors.stream()
						.anyMatch(actual -> actual.toLowerCase().contains(cleanExpected));
				Assert.assertTrue(matchFound, "Could not find expected error: [" + cleanExpected + "]");
			}
		}
	}

	@When("User clicks show password after entering password")
	public void user_clicks_show_password_after_entering_password() {
		LoggerFactory.info("User clicks show password checkbox after entering password");
		loginPage.verifyPasswordVisibility();

	}

	@Then("Password should be visible in plain text")
	public void password_should_be_visible_in_plain_text() {
		LoggerFactory.info("Password should be visible in plain text");
		Assert.assertTrue(loginPage.isPasswordVisible());
	}

	@When("User unchecks the {string} option after entering the password")
	public void user_unchecks_the_option_after_entering_the_password(String option) {
		LoggerFactory.info("User unchecks the Show password checkBox option after entering the password");
		loginPage.verifyPasswordVisibility();
	}

	@Then("Password should again be masked")
	public void password_should_again_be_masked() {
		LoggerFactory.info("Password should again be masked");
		boolean passwordtype = loginPage.isPasswordVisible();
		Assert.assertTrue(passwordtype);
	}

	@When("User enters a password and clicks the eye icon")
	public void user_enters_a_password_and_clicks_the_eye_icon() {
		LoggerFactory.info("User enters a password and clicks the eye icon");
		loginPage.enterPswrd("TstPwd12");
		loginPage.clickEyeIcon();
	}

	@Then("Password field should show the entered password in plain text")
	public void password_field_should_show_the_entered_password_in_plain_text() {
		LoggerFactory.info("Password field should show the entered password in plain text");
		Assert.assertTrue(loginPage.verifyPasswordVisibility(),"Password field not showing the entered password in plain text");
	}

	@When("User clicks the eye icon again")
	public void user_clicks_the_eye_icon_again() {
		LoggerFactory.info("User clicks the eye icon again");
		loginPage.clickEyeIcon();
	}

	@When("User clicks on {string}")
	public void user_clicks_on(String linkName) {	
		if (linkName.contains("Forgot")) {
			loginPage.clickForgotPassword();
		}
	}

	@Then("User should be redirected to the Forgot Password page")
	public void user_should_be_redirected_to_the_forgot_password_page() {
		boolean isForgotVisible = loginPage.navigateToForgotPwdPage();
		Assert.assertTrue(isForgotVisible,"User is not redirected to the Forgot Password page");
		
	}

	@When("User clicks on {string} tab")
	public void user_clicks_on_tab(String signUpTab) {
		loginPage.clickSignUpTab();
	}

	@Then("User should be redirected to the sign up tab")
	public void user_should_be_redirected_to_the_sign_up_tab() {
		Assert.assertTrue(loginPage.navigateToSignUpPage(),"User is not redirected to the sign up tab");
		loginPage.navigateToSignUpPage();
	}

}
