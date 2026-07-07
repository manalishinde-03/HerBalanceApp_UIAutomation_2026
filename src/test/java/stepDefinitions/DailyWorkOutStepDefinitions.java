package stepDefinitions;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.DailyWorkOutPage;
import pageObjects.DashboardPage;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.OnboardingPage;
import utilities.ExcelReader;
import utilities.LoggerFactory;
import webElementActions.ElementActions;

public class DailyWorkOutStepDefinitions {

	private WebDriver driver;
	private LaunchPage launchPage;
	private DashboardPage dashBoardPage;
	private DailyWorkOutPage dailyWorkOutPage;
	private OnboardingPage onboardingPage;
	private LoginPage loginPage;
	private ElementActions elementActions;
	private Map<String, String> testData;

	public DailyWorkOutStepDefinitions() {
		this.driver = DriverFactory.getDriver();
		this.launchPage = new LaunchPage(driver);
		this.loginPage = new LoginPage(driver);
		this.elementActions = new ElementActions(driver);
		this.dashBoardPage = new DashboardPage(driver);
		this.dailyWorkOutPage = new DailyWorkOutPage(driver);
	}

	@Given("User is in dashboard page after logged in")
	public void user_is_in_dashboard_page_after_logged_in() {
		LoggerFactory.info("User clicks login button after entering login details");
		launchPage.clickLoginInBtn();
		String testcaseKey = "Valid_User";
		launchPage.clickLoginInBtn();
		testData = ExcelReader.getData("DashboardDailyWorkout", testcaseKey);
		onboardingPage = (OnboardingPage) loginPage.loginWithValidCredentials(testData.get("Email"),
				testData.get("Password"));
		dailyWorkOutPage.waitForMsg();
		LoggerFactory.info("User is in dashboard page after logged in");
		dailyWorkOutPage.clickWorkOutLink();

	}

	@Given("User is on the workout page")
	public void user_is_on_the_workout_page() {
		LoggerFactory.info("Verifying Workout Page location");
		boolean onWorkOutPage = elementActions.waitForCondition(() -> dailyWorkOutPage.isAtWorkOutPage(), 15);
		Assert.assertTrue(onWorkOutPage,
				"Landing page mismatch. Expected Workout page but was: " + driver.getCurrentUrl());
	}

	@When("User clicks {string} button")
	public void user_clicks_button(String string) {
		LoggerFactory.info("User clicks Generate Workout Plan button");
		dailyWorkOutPage.clickGenerateWorkOutBtn();
	}

	@Then("User should see the section with title {string}")
	public void user_should_see_the_section_with_title(String expectedTitle) {
		LoggerFactory.info("User should see the section with title Daily workout plan");
		dailyWorkOutPage.waitForMsg();
		LoggerFactory.info("Waiting for section with title: " + expectedTitle);
		boolean generateWrkoutVisible = elementActions
				.waitForCondition(() -> dailyWorkOutPage.isGenerateWorkOutHeaderVisible(), 30);
		Assert.assertTrue(generateWrkoutVisible,
				"Section with title '" + expectedTitle + "' did not appear within 30s.");
	}

	@Then("User should see the labels with values {string}, {string}, and {string}")
	public void user_should_see_the_labels_with_values_and(String phaseValues, String energyValues,
			String focusValues) {
		LoggerFactory.info("Phase:Menstrual", "Energy:Low", "and", "Focus:Recovery");
		Assert.assertTrue(dailyWorkOutPage.isLabelVisibleWithValue("Phase"), "Phase label missing");
		Assert.assertTrue(dailyWorkOutPage.isLabelVisibleWithValue("Energy"), "Energy label missing");
		Assert.assertTrue(dailyWorkOutPage.isLabelVisibleWithValue("Focus"), "Focus label missing");
	}

	@Then("User should see the workout day displayed as {string}")
	public void user_should_see_the_workout_day_displayed_as(String expectedDayTxt) {
		LoggerFactory.info("Waiting for Cycle Day text to populate...");
		boolean isVisible = elementActions.waitForCondition(() -> {
			String text = dailyWorkOutPage.getCycleBtnTxt();
			return text != null && text.toLowerCase().contains("cycle day");
		}, 20);
		Assert.assertTrue(isVisible, "Cycle Day text never appeared on the button!");
	}

	@Then("User should see the title {string}")
	public void user_should_see_the_title(String expectedCycleDayTitle) {
		LoggerFactory.info("Then User should see the title Cycle Day 28 Luteal Phase Workout");
		String actualTitle = dailyWorkOutPage.getCycleDayTitle();
		boolean matches = actualTitle.toLowerCase().contains("cycle day")
				&& actualTitle.toLowerCase().contains("phase workout");
		Assert.assertTrue(matches, "Title format mismatch. Actual: " + actualTitle);
	}

	@Then("User should see the list of exercises {string},{string},{string}")
	public void user_should_see_the_list_of_exercises(String Deepex, String Catcowex, String Childsex3) {
		String[] expectedExercises = { Deepex, Catcowex, Childsex3 };
		for (String exerciseName : expectedExercises) {
			boolean isListExcerciseFound = elementActions.waitForCondition(() -> {
				return dailyWorkOutPage.isListOfExcerciseVisible(exerciseName);
			}, 20);
			Assert.assertTrue(isListExcerciseFound, "AI failed to display exercise: " + exerciseName);
		}
	}

	@Then("User should see  each exercise should show its duration in minutes")
	public void user_should_see_each_exercise_should_show_its_duration_in_minutes() {
		LoggerFactory.info("all excercise loaded");
		dailyWorkOutPage.waitForExerciseListToLoad();
		LoggerFactory.info("User should see  each exercise should show its duration in minutes");
		boolean durationsVisible = dailyWorkOutPage.isDurationVisible();
		Assert.assertTrue(durationsVisible, "User does not see each exercise duration");
	}

	@Then("User should see an icon beside each exercise name in the list")
	public void user_should_see_an_icon_beside_each_exercise_name_in_the_list() {
		LoggerFactory.info("User should see an icon beside each exercise name in the list");
		Assert.assertTrue(dailyWorkOutPage.isIconVisible(), "icon not visible beside each exercise name in the list");
	}

	@Then("User should see a  link {string}")
	public void user_should_see_a_link(String expectedLinkMsg) {
		LoggerFactory.info("User should see a  link More Excercise");
		boolean actualLinkMsg = dailyWorkOutPage.isMoreExcerciseLinkVisible();
		Assert.assertTrue(actualLinkMsg, "MoreExcercise link not visible");
	}

	@Then("User should see “View All Exercises” button displayed below the exercise list")
	public void user_should_see_view_all_exercises_button_displayed_below_the_exercise_list() {
		LoggerFactory.info("User should see “View All Exercises” button displayed below the exercise list");
		boolean isViewAllBtnVisible = elementActions.waitForCondition(() -> dailyWorkOutPage.isViewAllBtnVisible(), 15);
		Assert.assertTrue(isViewAllBtnVisible, "“View All Exercises” button not displayed");

	}

	@Then("User should see a content message during workout generation under above daily workout section")
	public void user_should_see_a_content_message_during_workout_generation_under_above_daily_workout_section() {
		boolean iscontentVisible = elementActions.waitForCondition(() -> dailyWorkOutPage.isGenerateWorkOutTxtVisible(),
				40);
		Assert.assertTrue(iscontentVisible, "The Gemini AI technology message was not visible.");
	}

	@Then("User should see the “About Daily Workouts” section displayed below the workout plan")
	public void user_should_see_the_about_daily_workouts_section_displayed_below_the_workout_plan() {
		LoggerFactory.info("User should see the “About Daily Workouts” section displayed below the workout plan");
		Assert.assertTrue(dailyWorkOutPage.isAllAboutWorkOutTxtVisible("About Daily Workouts"),
				"“About Daily Workouts” section not displayed below the workout plan");
	}

	@Then("User should see the content {string} under above daily workout section")
	public void user_should_see_the_content_under_above_daily_workout_section(String expectedWorkOutContent) {
		LoggerFactory.info("User should see the content under above daily workout section");
		Assert.assertTrue(dailyWorkOutPage.isAllAboutWorkOutContentVisible(expectedWorkOutContent),
				"content under About Daily Workouts section not Visible" + expectedWorkOutContent);
	}

}
