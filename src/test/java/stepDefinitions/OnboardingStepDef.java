package stepDefinitions;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.OnboardingPage;
import pageObjects.SignUpPage;
//import pageObjects.OnboardingPage;
//import pageObjects.SignUpPage;
//import utilities.ElementUtil;
//import utilities.ReadConfig;
import utilities.ConfigReader;
import utilities.ExcelReader;
import webElementActions.ElementActions;

public class OnboardingStepDef {

	private WebDriver driver;
	private String appURL = null;

	SignUpPage signUpPage;
	LoginPage loginPage;
	LaunchPage launchPage;

	OnboardingPage onboardingPage;
	ConfigReader readConfig = new ConfigReader();

	private ElementActions util;

	Map<String, String> testData;

	public OnboardingStepDef() {
		driver = DriverFactory.getDriver();
		onboardingPage = new OnboardingPage(driver);
		loginPage = new LoginPage(driver);
		signUpPage = new SignUpPage(driver);
		launchPage = new LaunchPage(driver);

	}

	@Given("User clicks on sign up")
	public void user_clicks_on_sign_up() {
		launchPage.clickOnSignUpButton();
	}

	@Given("User is already registered with {string} and on Upload Blood Report page")
	public void user_already_registered_onboardingStep1(String testCaseKey) {

		launchPage.clickLoginInBtn();

		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);
		onboardingPage = (OnboardingPage) loginPage.loginWithValidCredentials(testData.get("Email"),
				testData.get("Password"));
		Assert.assertEquals(onboardingPage.getOnboardingPageTitle(), "Upload Your Recent Blood Work");
	}

	
	@Then("Title of the page should be Upload Your Recent Blood Work")
	public void title_of_the_page_should_be_upload_your_recent_blood_work() {
		Assert.assertEquals(onboardingPage.getOnboardingPageTitle(), "Upload Your Recent Blood Work");

	}

	@Then("Verify the supporting text on Onboarding page1")
	public void verify_supporting_text_onboarding_step1() {
		String expectedText = "Welcome to HerBalance! To create a health plan that truly fits you, "
				+ "upload your recent blood work so we can provide personalized weight management "
				+ "recommendations based on your unique health profile.";
		Assert.assertEquals(onboardingPage.getSupportingTextStep1(), expectedText);

	}

	@Then("Verify Onboarding progress bar is displayed")
	public void verify_onboarding_progressBar_isDisplayed() {
		assertTrue(onboardingPage.isOnboardingProgressBarDisplayed(), "Onboarding progress bar should be displayed");

	}

	@Then("Verify Onboarding progress bar Step6 is displayed")
	public void verify_onboarding_progressBar_step6_isDisplayed() {
		assertTrue(onboardingPage.isOnboardingProgressBarStep6Displayed(),
				"Onboarding progress bar should be displayed");

	}

	@Then("Verify Step 5 Onboarding progress bar is displayed")
	public void verify_Step5_onboarding_progressBar_isDisplayed() {
		assertTrue(onboardingPage.isOnboardingProgressBar5of10Displayed(),
				"Onboarding progress bar should be displayed");

	}

	@Then("Verify Step 3 Onboarding progress bar is displayed")
	public void verify_step3_onboarding_progressBar_isDisplayed() {
		assertTrue(onboardingPage.isOnboardingProgressBar3of10Displayed(),
				"Step 3 Onboarding progress bar should be displayed");

	}

	@Then("Verify Progress bar shows the current step as 1 of 10")
	public void verify_onboarding_progressBar_stepas1of10_isDisplayed() {

		assertTrue(onboardingPage.isOnboardingProgressBar1of10Displayed(),
				"Onboarding progress bar should show the current step as 1 of 10");

	}

	@Then("Verify Upload PDF button is displayed")
	public void verify_upload_pdf_button_is_displayed() {
		assertTrue(onboardingPage.isUploadPDFButtonDisplayed(), "Upload PDF button should be displayed");

	}

	@When("User uploads the blood report")
	public void user_uploads_valid_blood_report() {
		onboardingPage.uploadBloodReport("sample_blood_test_report.pdf");
	}

	@When("User uploads invalid blood report")
	public void user_uploads_invalid_blood_report() {
		onboardingPage.uploadBloodReport("invalid_blood_test_report.txt");
	}

	@Then("User should see error for unsupported file type")
	public void user_should_see_error_for_unsupported_file_type() {

		String expectedText = "Invalid file type" + "\nOnly PDF files are supported for blood report analysis.";
		Assert.assertEquals(onboardingPage.getInvalidFileUploadErrorText(), expectedText);

	}

	@Then("User should see Report analysis")
	public void user_should_see_report_analysis() {
		// Defect
	}

	@Then("User should see Blood Test Results,Complete Blood Count, Medical Conditions ,Abnormal Values, Recommendations")
	public void user_should_see_blood_test_results_complete_blood_count_medical_conditions_abnormal_values_recommendations() {
		// Defect
	}

	@Then("Verify Upload PDF button is enabled")
	public void verify_upload_pdf_button_is_enabled() {
		assertTrue(onboardingPage.isUploadPDFButtonEnabled(), "Upload PDF button should be enabled");
	}

	@Then("Verify PDF files only \\(Max 10MB) is visible")
	public void verify_pdf_files_only_max_10mb_is_visible() {
		assertTrue(onboardingPage.isPDFFilesOnlyTextDisplayed(), "PDF Files Only Text should be displayed");

		String expectedText = "PDF files only (Max 10MB)";
		Assert.assertEquals(onboardingPage.getPDFFilesOnlyTextStep1(), expectedText);
	}

	@Then("Skip for Now card is visible")
	public void skip_for_now_card_is_visible() {
		assertTrue(onboardingPage.isSkipForNowTextDisplayed(), "Skip For Now Text should be displayed");
	}

	@Then("Explanation text about continuing without lab results is displayed")
	public void explanation_text_about_continuing_without_lab_results_is_displayed() {
		assertTrue(onboardingPage.isExplanationTextDisplayed(),
				"Explanation text about continuing without lab results should be displayed");

		String expectedText = "You can continue without uploading lab results and add this information later for more personalized recommendations.";
		Assert.assertEquals(onboardingPage.getSkipExplanationText(), expectedText);
	}

	@Then("Continue Without Report button is displayed")
	public void continue_without_report_button_is_displayed() {
		assertTrue(onboardingPage.isContinueWithoutReportBtnDisplayed(),
				"Continue Without Report button should be displayed");
	}

	@Then("Continue Without Report button is enabled")
	public void continue_without_report_button_is_enabled() {
		assertTrue(onboardingPage.isContinueWithoutReportBtnEnabled(),
				"Continue Without Report button should be enabled");
	}

	@Then("Note about secure processing and no sharing with third parties is displayed")
	public void note_about_secure_processing_and_no_sharing_with_third_parties_is_displayed() {
		assertTrue(onboardingPage.isDataSecurityTextDisplayed(),
				"Note about secure processing and no sharing with third parties Text should be displayed");

		String expectedText = "By sharing your blood work, we can create a personalized weight management plan that works with your body's unique biochemistry.\n"
				+ "Your data is securely processed and never shared with third parties.";
		Assert.assertEquals(onboardingPage.getDataSecurityTextStep1(), expectedText);

	}

	@When("User clicks on Continue Without Report Button")
	public void user_clicks_on_continue_without_report_button() {
		onboardingPage.clickOnContinueWithoutReportBtn();

	}

	@Then("User should navigate to step3 Health Conditions")
	public void user_should_navigate_to_step3_health_conditions() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep3Title(), "Health Conditions");

	}

	@When("User clicks on Upload PDF Button")
	public void user_clicks_on_upload_pdf_button() {
		onboardingPage.clickOnUploadPDFBtn();
	}

	@When("User clicks on Back Button on Step4")
	public void user_clicks_on_back_button_step4() {
		onboardingPage.clickOnBackBtnStep4();
	}

	@When("User clicks on Continue Button")
	public void user_clicks_on_continue_button_step4() {
		onboardingPage.clickOnContinueBtnStep4();
	}

	@Then("User should see Windows Explorer opened")
	public void file_upload_dialog_should_be_opened() throws InterruptedException {
		WebElement fileInput = driver.findElement(By.id("bloodwork-upload"));

		fileInput.sendKeys("C:\\Users\\manal_\\Documents\\test.txt"); //
		Assert.assertTrue(fileInput.isDisplayed(), "File input is not available");
		Assert.assertTrue(onboardingPage.fileUploadDialogIsDisplayed(), "File upload dialog should be displayed ");
	}

	@Then("User should see upload processing percentage bar")
	public void user_should_see_upload_processing_percentage_bar() {

		onboardingPage.clickOnAnalyzeReportBtn();
		assertTrue(onboardingPage.isUploadFilePercentageProgressBarDisplayed(),
				"Percentage progress bar should be displayed after uploading PDF File.");

	}

	@Then("Progress bar shows the current step as  3 of 10")
	public void progress_bar_shows_the_current_step_as_of() {

		assertTrue(onboardingPage.isOnboardingProgressBar3of10Displayed(),
				"Onboarding progress bar should show the current step as 3 of 10");
	}

	@Then("Back button is displayed")
	public void back_button_is_displayed() {

		assertTrue(onboardingPage.isBackButtonStep3Displayed(), "Back button should be displayed");
	}

	@Then("Back button should be enabled")
	public void back_button_should_be_enabled() {
		assertTrue(onboardingPage.isBackButtonStep3Enabled(), "Back button should be enabled");
	}

	@Then("Continue button should be visible")
	public void continue_button_should_be_visible() {

		assertTrue(onboardingPage.isContinueButtonStep3Displayed(), "Continue button should be displayed");
	}

	@Then("Continue button should be enabled")
	public void continue_button_should_be_enabled() {

		assertTrue(onboardingPage.isContinueButtonStep3Enabled(), "Continue button should be enabled");
	}

	@Then("Continue button should be disabled")
	public void continue_button_should_be_disabled() {

		assertFalse(onboardingPage.isContinueButtonStep3Enabled(), "Continue button should be Disabled");
	}

	@Then("Header should be Health conditions")
	public void header_should_be_health_conditions() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep3Title(), "Health Conditions");
	}

	@Then("Sub title for the header in Step3 is visible")
	public void sub_title_for_the_header_in_step3_is_visible() {

		Assert.assertEquals(onboardingPage.getOnboardingPageStep3SubTitle(),
				"Want to manually enter any key conditions or comorbidities?");
	}

	@Then("Total {int} Radio buttons should be visible")
	public void total_radio_buttons_should_be_visible(int expectedCount) {

		Assert.assertEquals(onboardingPage.radioButtonsSize(), expectedCount);
	}

	@Then("Health condition options should be :")
	public void health_condition_options_should_be_visible(io.cucumber.datatable.DataTable dataTable) {

		List<String> expectedOptions = dataTable.asList();
		Assert.assertEquals(onboardingPage.radioButtonsOptionsText(), expectedOptions, "Radio options do not match");
	}

	@Then("Note text should displayed")
	public void note_text_should_displayed() {

		String expectedText = "Conditions like PCOS, thyroid issues, and insulin resistance affect how your body responds to workouts, food, and stress. We'll weave this into your personalized plan.";

		Assert.assertEquals(onboardingPage.getNoteTextStep3(), expectedText);

	}

	@When("User selects {string} health condition and clicks on Continue Button")
	public void user_selects_one_health_condition_and_clicks_on_continue_button(String healthCondition) {
		onboardingPage.selectHealthConditionAndClickOnContinue(healthCondition);
	}

	@When("User selects {string} and clicks on Continue Button")
	public void user_selects_health_condition_and_clicks_on_continue_button(String testCaseKey) {

		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.selectHealthConditionAndClickOnContinue(testData.get("Condition"));
		onboardingPage.clickOnContinueBtnStep4();

	}

	@Then("Title of the page should be Personal Details")
	public void title_of_the_page_should_be_personal_details() {

		Assert.assertEquals(onboardingPage.getOnboardingPageStep4Title(), "Personal Details");
	}

	@Then("Verify description text for step4")
	public void verify_description_text_for_step4() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep4SubTitle(),
				"Let's get to know you better to personalize your experience.");
	}

	@Then("Verify input box count is {int} on step4")
	public void verify_input_box_count_on_step4(int expectedCount) {

		Assert.assertEquals(onboardingPage.inputBoxCountStep4(), expectedCount);
	}

	@Then("User should see the following input field labels:")
	public void user_should_see_the_following_input_field_labels(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedLabels = dataTable.asList();

		for (String expected : expectedLabels) {
			Assert.assertTrue(onboardingPage.getActualInputFieldLabels().contains(expected));
		}
	}

	@Then("User should see the following helper text under the input fields:")
	public void verify_helper_text_for_the_fields(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedHelperTexts = dataTable.asList();

		for (String expected : expectedHelperTexts) {
			Assert.assertTrue(onboardingPage.getActualHelperTexts().contains(expected));
		}
	}

	@Then("Total number of radio buttons should be {int}")
	public void total_number_of_radio_buttons_should_be(int expectedCount) {

		Assert.assertEquals(onboardingPage.radioButtonsStep4Size(), expectedCount);
	}

	@Then("Blood pressure description options should be :")
	public void blood_pressure_description_options_should_be(io.cucumber.datatable.DataTable dataTable) {

		List<String> expectedBPDescriptionOptions = dataTable.asList();

		Assert.assertEquals(onboardingPage.getActualBPDescriptionOptions(), expectedBPDescriptionOptions,
				"Radio options do not match");

	}

	@Then("Progress bar shows the current step as  4 of 10")
	public void progress_bar_shows_the_current_step_as_of1() {
		assertTrue(onboardingPage.isOnboardingProgressBar4of10Displayed(),
				"Onboarding progress bar should show the current step as 4 of 10");
	}

	@Then("Verify user navigates to Step5 after clicking Continue button")
	public void verify_user_navigates_to_step5_after_clicking_continue_button() {

		Assert.assertEquals(onboardingPage.getOnboardingPageStep5Title(), "Menstrual Cycle Awareness");
	}

	// Not Data Driven-From Config
	@When("User enters valid personal details {string},{string} and {string} and clicks on Continue Button")
	public void user_enters_valid_personal_details_and_and_clicks_on_continue_button(String FirstName, String Age,
			String BPStatus) {
		onboardingPage.enterPersonalDetailsStep4(FirstName, Age);
		onboardingPage.selectBloodPressureStatus(BPStatus);
		onboardingPage.clickOnContinueBtnStep4();
	}

	// DataDriven-From Excel
	@When("User enters valid personal details for {string} from {string}")
	public void user_enters_valid_personal_details_for_from(String scenarioName, String sheetName) {
		testData = ExcelReader.getData(sheetName, scenarioName);
		onboardingPage.enterPersonalDetailsStep4(testData.get("FirstName"), testData.get("Age"));
		onboardingPage.selectBloodPressureStatus(testData.get("BPStatus"));

	}

	@Given("User completes onboarding till Step 4 using {string}")
	public void user_completes_onboarding_till_step4(String testCaseKey) {

		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.enterPersonalDetailsStep4(testData.get("FirstName"), testData.get("Age"));
		onboardingPage.selectBloodPressureStatus(testData.get("BPStatus"));
		onboardingPage.clickOnContinueBtnStep4();
	}

	@Given("User enters {string} values in Personal Details Step 4")
	public void user_enters_invalidData_onboarding_step4(String testCaseKey) {

		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.enterPersonalDetailsStep4(testData.get("FirstName"), testData.get("Age"));
		onboardingPage.selectBloodPressureStatus(testData.get("BPStatus"));
		onboardingPage.clickOnContinueBtnStep4();
	}

	@Then("Title of the page should be Menstrual Cycle Awareness")
	public void title_of_the_page_should_be_menstrual_cycle_awareness() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep5Title(), "Menstrual Cycle Awareness");
	}

	@Then("Verify description text for step5")
	public void verify_description_text_for_step5() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep5SubTitle(),
				"Understanding your cycle helps us optimize your weight loss journey.");
	}

	@Then("User should see the question on Step5")
	public void user_should_see_the_question_on_step5() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep5QuestionText(),
				"Do you currently track your menstrual cycle?");
	}

	@Then("Progress bar shows the current step as  5 of 10")
	public void progress_bar_shows_the_current_step_as_of5() {
		assertTrue(onboardingPage.isOnboardingProgressBar5of10Displayed(),
				"Onboarding progress bar should show the current step as 5 of 10");
	}

	@Then("Total number of radio buttons in Step5 should be {int}")
	public void total_number_of_radio_buttons_should_be_step5(int expectedCount) {

		Assert.assertEquals(onboardingPage.radioButtonsStep5Size(), expectedCount);
	}

	@Then("Following Radio options for  menstrual cycle awareness should be displayed")
	public void following_radio_options_for_menstrual_cycle_awareness_should_be_displayed(
			io.cucumber.datatable.DataTable dataTable) {

		List<String> expectedRadioOptions = dataTable.asList();

		Assert.assertEquals(onboardingPage.getActualRadioOptionsMenstrualCycleAwareness(), expectedRadioOptions,
				"Radio options do not match");

	}

	@When("User clicks on Back Button on Step5")
	public void user_clicks_on_back_button_on_step5() {
		onboardingPage.clickOnBackBtnStep4();

	}

	@Then("User should navigate to step4 Personal Details")
	public void user_should_navigate_to_step4_personal_details() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep4Title(), "Personal Details");

	}

	@When("User selects {string} radio option on step5 and clicks on Continue Button")
	public void user_selects_radio_option_on_step5_and_clicks_on_continue_button(String cycleInfo) {
		onboardingPage = new OnboardingPage(driver);
		onboardingPage.selectCycleInfoOption(cycleInfo);
		onboardingPage.clickOnContinueBtnStep4();

	}

	@Then("Title of the page should be Last Period Date")
	public void title_of_the_page_should_be_last_period_date() {

		Assert.assertEquals(onboardingPage.getOnboardingPageStep6Title(), "Last Period Date");

	}

	@Then("Verify description text for step6")
	public void verify_description_text_for_step6() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep6SubTitle(),
				"This helps us understand your current cycle phase and tailor recommendations.");
	}

	@Then("User should see the question on Step6")
	public void user_should_see_the_question_on_step6() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep6QuestionText(),
				"When did your last menstrual period start?");
	}

	@Then("User should see the calendar icon in step6")
	public void user_should_see_the_calendar_icon_in_step() {
		assertTrue(onboardingPage.isCalendarIconStep6Displayed(), "Calendar Icon should be displayed");
	}

	@Then("Verify Date input field is displayed")
	public void date_input_field_with_placeholder_should_be_displayed() {
		Assert.assertTrue(onboardingPage.verifyDateFieldPlaceholderText());
	}

	@Then("Label {string} should be visible")
	public void label_should_be_visible(String string) {
		assertTrue(onboardingPage.isCycleLengthLabelDisplayed(), "Label should be displayed");
	}

	@Then("Slider control for cycle length should be displayed")
	public void slider_control_for_cycle_length_should_be_displayed() {
		assertTrue(onboardingPage.isCycleLengthSliderDisplayed(), "Slider control should be displayed");
	}

	@Then("Default cycle length value {string} should be shown beside the slider")
	public void default_cycle_length_value_should_be_shown_beside_the_slider(String defaultLength) {
		Assert.assertEquals(onboardingPage.getDefaultCycleLength(), defaultLength);

	}

	@Then("Information text {string} should be visible")
	public void information_text_should_be_visible(String expectedInfoText) {

		Assert.assertEquals(onboardingPage.getInfoTextStep6(), expectedInfoText);
	}

	@Then("Progress bar shows the current step as  6 of 10")
	public void progress_bar_shows_the_current_step_as_6of10() {

		assertTrue(onboardingPage.isOnboardingProgressBar6of10Displayed(),
				"Onboarding progress bar should show the current step as 6 of 10");
	}

	@When("User clicks on Back Button on Step6")
	public void user_clicks_on_back_button_on_step6() {
		onboardingPage.clickOnBackBtnStep4();
	}

	@Then("User should navigate to step5 Menstrual Cycle Awareness")
	public void user_should_navigate_to_step5_menstrual_cycle_awareness() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep5Title(), "Menstrual Cycle Awareness");

	}

	@Then("Verify error message for invalid First Name after clicking Continue button")
	public void verify_error_message_for_invalid_first_name_after_clicking_continue_button() {
		String actualError = onboardingPage.getinvalidFirstNameErrorMessage();
		String expectedError = "Please enter valid name";

		Assert.assertEquals(actualError, expectedError, "First Name validation error message mismatch");
	}

	@Then("Verify error message for invalid Age after clicking Continue button")
	public void verify_error_message_for_invalid_age_after_clicking_continue_button() {

		String actualError = onboardingPage.getInvalidAgeErrorMessage();
		String expectedError = "Age must be between 18 and 100";

		Assert.assertEquals(actualError, expectedError, "Age validation error message mismatch");

	}

	@Then("Verify error message for letter in Age field after clicking Continue button")
	public void verify_error_message_for_letter_age_after_clicking_continue_button() {

		String actualError = onboardingPage.getInvalidAgeWithLetterErrorMessage();
		String expectedError = "Please enter a number.";

		Assert.assertEquals(actualError, expectedError, "Age validation error message mismatch");

	}

	@Then("Verify error message for empty First Name after clicking Continue button")
	public void verify_error_message_for_empty_first_name_after_clicking_continue_button() {
		String actualError = onboardingPage.getEmptyFirstNameErrorMessage();
		String expectedError = "Name is required";

		Assert.assertEquals(actualError, expectedError, "First Name validation error message mismatch");
	}

	@Then("Verify error message for Empty Age after clicking Continue button")
	public void verify_error_message_for_empty_age_after_clicking_continue_button() {
		String actualError = onboardingPage.getEmptyAgeErrorMessage();
		String expectedError = "Age is required";

		Assert.assertEquals(actualError, expectedError, "Age validation error message mismatch");
	}

	@Then("Verify error message for invalid BP Status after clicking Continue button")
	public void verify_error_message_for_invalid_bpStatus_after_clicking_continue_button() {
		String actualError = onboardingPage.getinvalidBPStatusErrorMessage();
		String expectedError = "Please select your blood pressure status";

		Assert.assertEquals(actualError, expectedError, "BP Status validation error message mismatch");

	}

	@When("User selects Menstrual Cycle Info {string} and clicks on Continue Button")
	public void user_selects_menstrual_cycle_info_and_clicks_on_continue_button(String testCaseKey) {
		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.selectCycleInfoAndClickOnContinue(testData.get("MenstrualCycleInfo"));
	}

	@When("User selects last menstrual date {string}")
	public void user_selects_last_menstrual_date(String testCaseKey) {
		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.selectLastMenstrualDate(testData.get("Date"));

	}
	
	@When("User enters invalid last period date {string}")
	public void user_selects_invalid_last_menstrual_date(String testCaseKey) {
		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.selectLastMenstrualDate(testData.get("Date"));

	}

	@Then("Verify the Message after selecting the {string} on Step 6")
	public void message_should_be_visible(String testCaseKey) {
		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);

		onboardingPage.verifyMessageForSelectedDate(testData.get("Date"));

	}
	
	@Then("Title of the page should be Current Weight and Height")
	public void title_of_the_page_should_be_step7() {

		Assert.assertEquals(onboardingPage.getOnboardingPageStep7Title(), "Current Weight and Height");

	}
	
	@Then("Verify description text for step7")
	public void verify_description_text_for_step7() {
		Assert.assertEquals(onboardingPage.getOnboardingPageStep4SubTitle(),
				"This helps us calculate your BMI and set appropriate goals.");
	}
	
	@Then("Verify input text field count is {int} in step7")
	public void verify_input_box_count_on_step7(int expectedCount) {

		Assert.assertEquals(onboardingPage.inputBoxCountStep4(), expectedCount);
	}
	
	@Then("Progress bar shows the current step as  7 of 10")
	public void verify_Step7_onboarding_progressBar_isDisplayed() {
		assertTrue(onboardingPage.isOnboardingProgressBar7of10Displayed(),
				"Onboarding progress bar should be displayed");

	}
	
	@Then("User should see the following input field labels on Step 7:")
	public void user_should_see_the_following_input_field_labels_step7(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedLabels = dataTable.asList();

		for (String expected : expectedLabels) {
			Assert.assertTrue(onboardingPage.getActualInputFieldLabels().contains(expected));
		}
	}
	
	@Then("Verify error message for invalid last period date after clicking Continue button")
	public void verify_error_message_for_invalid_date_after_clicking_continue_button() {

		String actualError = onboardingPage.getEmptyLastPeriodDateErrorMessage();
		String expectedError = "Please select your last period start date";

		Assert.assertEquals(actualError, expectedError, "Date validation error message mismatch");

	}

}
