package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.SignUpPage;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerFactory;

public class SignUpStepDefinitions {
	private WebDriver driver;
	private LoginPage loginPage;
	private LaunchPage launchPage;
	private SignUpPage signUpPage;
	Map<String, String> testData;
	ConfigReader configReader = new ConfigReader();

	@Given("User is on the HerBalance auth page")
	public void user_is_on_the_her_balance_auth_page() {
		this.driver = DriverFactory.getDriver();
		this.launchPage = new LaunchPage(driver);
		this.signUpPage = new SignUpPage(driver);
		this.loginPage = new LoginPage(driver);

	}

	@When("User is clicked Sign up button in home page")
	public void user_is_clicked_sign_up_button_in_home_page() {
		LoggerFactory.info("User is clicked Sign up button in home page");
		loginPage.clickSignUp();

	}

	@When("User enters details from {string} from {string}")
	public void user_enters_details_from_from(String scenarioName, String sheetName) {
		LoggerFactory.info("User enters details from scenarioname from SignUpsheet");
		testData = ExcelReader.getData(sheetName, scenarioName);
		signUpPage.registerWithValidData(testData.get("Email"), testData.get("Password"),
				testData.get("Password Confirmation"), testData.get("TermsAccepted"));
	}

	@When("User clicks the Register button")
	public void user_clicks_the_register_button() {
		LoggerFactory.info("User clicks the Register button for SignUp");
		signUpPage.clickRegisterBtn();

	}

	@Then("Radio button for show passwords is present")
	public void radio_button_for_show_passwords_is_present() {
		LoggerFactory.info("Radio button for show passwords is present");
		Assert.assertTrue(loginPage.isShowpwdVisible(), "Show password toggle not found on SignUp page");
	}

	@Then("Terms and conditions details should be present")
	public void terms_and_conditions_details_should_be_present() {
		LoggerFactory.info("Terms and conditions details should be present");
		Assert.assertTrue(signUpPage.isTermTxtVisible(), "Terms and conditions details not present in radio");
	}

	@Then("Radio button for Terms and condition is present")
	public void radio_button_for_terms_and_condition_is_present() throws InterruptedException {
		LoggerFactory.info("Radio button for Terms and condition is present");
		Assert.assertTrue(signUpPage.isRdioBtnTermVisible(),"Radio button for Terms and condition is not present");
	}

	@Then("There should be exactly {int} input field in signUpPage")
	public void there_should_be_exactly_input_field_in_sign_up_page(Integer expectedCount) {
		LoggerFactory.info("There should be exactly 3 input field in signUpPage");
		Assert.assertEquals(signUpPage.getTotalCount(), (int) expectedCount, "3 input fields not found");

	}

	@Then("User should be redirected to the onboarding step")
	public void user_should_be_redirected_to_the_onboarding_step() {
		LoggerFactory.info("User should be redirected to the onboarding step");
		signUpPage.navigateToOnboardpage();
	}

	@Then("The expected outcome should be displayed for {string}")
	public void the_expected_outcome_should_be_displayed_for(String scenarioName) {
		LoggerFactory.info("User should see expected outcome for scenarioname");
		String expectedError = testData.get("ExpectedError");
		List<String> actualErrors = signUpPage.getAllErrorMessages();
		Assert.assertTrue(actualErrors.toString().toLowerCase().contains(expectedError.toLowerCase()));
	}

	@When("User clicks the eye icon to show the passwords after entering it")
	public void user_clicks_the_eye_icon_to_show_the_passwords_after_entering_it() {
		LoggerFactory
				.info("User clicks the eye icon to show the passwords after entering password and confirmPassword");
		configReader.loadProperties();
		String pwd = configReader.getTestPassword();
		signUpPage.enterPswrd(pwd, pwd);
		signUpPage.clickEyeIcon("password");
		signUpPage.clickEyeIcon("confirm");
	}

	@Then("The entered passwords should be visible in both password fields")
	public void the_entered_passwords_should_be_visible_in_both_password_fields() {
		LoggerFactory
				.info("User clicks the eye icon to show the passwords after entering password and confirmPassword");
		boolean isPwdVisible = signUpPage.isFieldTypeCorrect("password", "text");
		boolean isConfirmVisible = signUpPage.isFieldTypeCorrect("confirm", "text");
		Assert.assertTrue(isPwdVisible);
		Assert.assertTrue(isConfirmVisible);
	}

	@When("User clicks the eye icon again \\(unchecks Show Passwords) after entering the passwords")
	public void user_clicks_the_eye_icon_again_unchecks_show_passwords_after_entering_the_passwords() {
		LoggerFactory.info("User clicks the eye icon again after entering the passwords");
		LoggerFactory.info("change into visible text");
		signUpPage.clickEyeIcon("password");
		signUpPage.clickEyeIcon("confirm");
		LoggerFactory.info("Change back to masked");
		signUpPage.clickEyeIcon("password");
		signUpPage.clickEyeIcon("confirm");
	}

	@Then("passwords should be masked again \\(displayed as dots or asterisks) in both fields")
	public void passwords_should_be_masked_again_displayed_as_dots_or_asterisks_in_both_fields() {
		LoggerFactory.info("passwords should be masked again in both fields");
		boolean isPwdMasked = signUpPage.isFieldTypeCorrect("password", "password");
		boolean isConfirmMasked = signUpPage.isFieldTypeCorrect("confirm", "password");
		Assert.assertTrue(isPwdMasked);
		Assert.assertTrue(isConfirmMasked);
	}

	@When("User clicks the eye icon again on signUpPage")
	public void user_clicks_the_eye_icon_again_on_sign_up_page() {
		LoggerFactory.info("User clicks the eye icon again on signUpPage");
		signUpPage.clickEyeIcon("password");
		signUpPage.clickEyeIcon("password");
	}

	@Then("Password field should mask the entered password in plain text")
	public void password_field_should_mask_the_entered_password_in_plain_text() {
		LoggerFactory.info("Password field should mask the entered password in plain text");
		Assert.assertTrue(signUpPage.isFieldTypeCorrect("password", "password"));
	}

	@When("User enters a password and clicks the eye icon on signUpPage")
	public void user_enters_a_password_and_clicks_the_eye_icon_on_sign_up_page() {
		LoggerFactory.info("User enters a password and clicks the eye icon on signUpPage");
		configReader.loadProperties();
		String pwd = configReader.getTestPassword();
		signUpPage.enterPswrd(pwd, pwd);
		signUpPage.clickEyeIcon("confirm");
	}

	@Then("Confirm Password field should show the entered password in plain text")
	public void confirm_password_field_should_show_the_entered_password_in_plain_text() {
		LoggerFactory.info("Confirm Password field should show the entered password in plain text");
		boolean isVisible = signUpPage.isFieldTypeCorrect("confirm", "text");
		Assert.assertTrue(isVisible, "Confirm Password field is still masked!");
	}

	@Then("Confirm Password field should mask the entered password in plain text")
	public void confirm_password_field_should_mask_the_entered_password_in_plain_text() {
		LoggerFactory.info("Confirm Password field should show the entered password in plain text");
		Assert.assertTrue(signUpPage.isFieldTypeCorrect("confirm", "password"));
	}

	@Then("User should be redirected to the login tab")
	public void user_should_be_redirected_to_the_login_tab() {
		LoggerFactory.info("User should be redirected to the login tab");
		signUpPage.verifyLogin();
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("auth"));
	}

}
