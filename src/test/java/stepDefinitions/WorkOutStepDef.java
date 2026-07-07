package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import pageObjects.DailyWorkOutPage;
import pageObjects.DashboardPage;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.OnboardingPage;
import pageObjects.WorkOutPage;
import utilities.ConfigReader;
import utilities.ExcelReader;
import webElementActions.ElementActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

public class WorkOutStepDef {

	private WebDriver driver;


	WorkOutPage workOutPage;
	ConfigReader readConfig = new ConfigReader();
	private LaunchPage launchPage;
	private DashboardPage dashBoardPage;
	private DailyWorkOutPage dailyWorkOutPage;
	private OnboardingPage onboardingPage;
	private LoginPage loginPage;

	private ElementActions util;

	Map<String, String> testData;

	public WorkOutStepDef() {
		this.driver = DriverFactory.getDriver();
		this.workOutPage = new WorkOutPage(driver);
		this.launchPage = new LaunchPage(driver);
		this.loginPage = new LoginPage(driver);
		this.dashBoardPage = new DashboardPage(driver);
		this.util = new ElementActions(driver);
		this.dailyWorkOutPage = new DailyWorkOutPage(driver);
		

	}
	
	@Given("User is on the dashboard page")
	public void user_is_in_dashboard_page_after_logged_in() {
		launchPage.clickLoginInBtn();
		String testcaseKey = "Valid_User_WorkoutUI";
		launchPage.clickLoginInBtn();
		testData = ExcelReader.getData("DashboardDailyWorkout", testcaseKey);
		onboardingPage = (OnboardingPage) loginPage.loginWithValidCredentials(testData.get("Email"),
				testData.get("Password"));
		dailyWorkOutPage.waitForMsg();
		dailyWorkOutPage.clickWorkOutLink();
	}



	@Then("User should see page title as {string}")
	public void user_should_see_page_title_as(String expectedTitle) {
		try {
			boolean isDailyPersonalised = workOutPage.isDailyPersonalisedDisplayed();
			String elementText = workOutPage.getDailyPersonalisedText();

			System.out.println("The Daily Personalised Page is displayed: " + isDailyPersonalised);
			System.out.println("The text of Daily personalised element is: " + elementText);

			Assert.assertTrue(isDailyPersonalised, " Daily Personalised element is not displayed");
			Assert.assertEquals(elementText, "Daily Personalized Workouts", " Text does not match expected");

			System.out.println("Back to dashboard link is displayed with correct text.");

		} catch (Exception e) {
			System.err.println("Error verifying Daily Personalised element: " + e.getMessage());
			Allure.addAttachment("Daily Personalised Error", "text/plain", e.toString());
			Assert.fail("Test failed due to unexpected exception.");
		}
	}

	@Then("User should see the link Back to dashboard")
	public void user_should_see_the_link_back_to_dashboard() {
		try {
			boolean isBackToDashBoard = workOutPage.isDailyPersonalisedDisplayed();
			String linkText = workOutPage.getBacktoDashBoardText();

			System.out.println("Back to DashBoard is displayed: " + isBackToDashBoard);
			System.out.println("The text of Back to DashBoard element is: " + linkText);

			Assert.assertTrue(isBackToDashBoard, " Back to dashboard link is not displayed");
			Assert.assertEquals(linkText, "Back to Dashboard", "Link text does not match expected");

			System.out.println("Back to dashboard link is displayed with correct text.");

		} catch (Exception e) {
			System.err.println(" Error verifying Back to dashboard link: " + e.getMessage());
			Allure.addAttachment("Back to Dashboard Error", "text/plain", e.toString());
			Assert.fail("Test failed due to unexpected exception.");
		}
	}

	@Then("User should see the {string} section")
	public void user_should_see_the_section(String expectedText) {
		try {
			boolean isDailyWorkOutPlan = workOutPage.isDailyWorkOutPlanDisplayed();
			String linkText = workOutPage.getDailyWorkOutPlanText();

			System.out.println("Daily WorkOut Plan is displayed: " + isDailyWorkOutPlan);
			System.out.println("The text of Daily WorkOut Plan  element is: " + linkText);

			Assert.assertTrue(isDailyWorkOutPlan, " Daily WorkOut Plan link is not displayed");
			Assert.assertEquals(linkText, expectedText, "Link text does not match expected");

			System.out.println("Back to dashboard link is displayed with correct text.");

		} catch (Exception e) {
			System.err.println(" Error verifying Back to dashboard link: " + e.getMessage());
			Allure.addAttachment("Back to Dashboard Error", "text/plain", e.toString());
			Assert.fail("Test failed due to unexpected exception.");
		}
	}

	@Then("User should see the description as {string}.")
	public void user_should_see_the_description_as(String expectedText) {
		try {
			boolean isFreshAIPlan = workOutPage.isFreshAIDisplayed();
			String linkText = workOutPage.getFreshAIText();

			System.out.println("Fresh AI is displayed: " + isFreshAIPlan);
			System.out.println("The text ofFresh AI i element is: " + linkText);

			Assert.assertTrue(isFreshAIPlan, " Fresh AI i link is not displayed");
			Assert.assertEquals(linkText, expectedText, "Link text does not match expected");

			System.out.println("Fresh AI is displayed with correct text.");

		} catch (Exception e) {
			System.err.println(" Error verifying Back to dashboard link: " + e.getMessage());
			Allure.addAttachment("Back to Dashboard Error", "text/plain", e.toString());
			Assert.fail("Test failed due to unexpected exception.");
		}
	}

	@Then("User should see the message as {string}")
	public void user_should_see_the_message_as(String expectedText) {
		try {
			boolean isNoWorkOutPlan = workOutPage.isNoWorkOutPlanDisplayed();
			String linkText = workOutPage.getNoWorkOutPlanText();

			System.out.println("No workOut Plan  is displayed: " + isNoWorkOutPlan);
			System.out.println("The text No workOut Plan is element is: " + linkText);

			Assert.assertTrue(isNoWorkOutPlan, " Workout Plan link is not displayed");
			Assert.assertEquals(linkText, expectedText, "Link text does not match expected");

			System.out.println("Workout is displayed with correct text.");

		} catch (Exception e) {
			System.err.println(" Error verifying Workout Planlink: " + e.getMessage());
			Assert.fail("Test failed due to unexpected exception.");
		}
	}

	@Then("User should see the message as {string} aligned centre")
	public void user_should_see_the_message_as_aligned_centre(String expectedMessage) {
		try {

			boolean isDisplayed = workOutPage.isNoWorkoutMessageDisplayed();
			String actualMessage = workOutPage.getNoWorkoutMessageText();
			String alignment = workOutPage.getNoWorkoutMessageAlignment();

			Allure.addAttachment("No Workout Message", actualMessage);
			Allure.addAttachment("Message Alignment", alignment);

			if (!isDisplayed) {
				System.err.println("Message element not displayed.");
			} else if (!actualMessage.equals(expectedMessage)) {
				System.err.println(" Message text does not match expected.");
			} else if (!alignment.equalsIgnoreCase("center")) {
				System.err.println("Message is not aligned center.");
			} else {
				System.out.println("Message displayed correctly and aligned center.");
			}

		} catch (Exception e) {
			System.err.println("Error verifying message: " + e.getMessage());
			Allure.addAttachment("Message Error", "text/plain", e.toString());
		}
	}
	
	@Then("User should see {string} button displayed")
	public void user_should_see_button_displayed(String expectedText) {
	    try {

	        boolean isDisplayed = workOutPage.isGenerateWorkoutButtonDisplayed();
	        String actualText = workOutPage.getGenerateWorkoutButtonText();

	        System.out.println("Button displayed: " + isDisplayed);
	        System.out.println("Button text: " + actualText);

	        Allure.addAttachment("Generate Workout Button Text", actualText);

	        if (!isDisplayed) {
	            System.err.println(" Generate Workout Plan button is not displayed.");
	        } else if (!actualText.equals(expectedText)) {
	            System.err.println(" Button text does not match expected. Expected: " 
	                               + expectedText + " but got: " + actualText);
	        } else {
	            System.out.println(" Generate Workout Plan button is displayed with correct text.");
	        }

	    } catch (Exception e) {
	        System.err.println(" Error verifying Generate Workout Plan button: " + e.getMessage());
	        Allure.addAttachment("Button Error", "text/plain", e.toString());
	    }
	}
	
	@Then("User should see {string} section")
	public void user_should_see_section(String expectedText) {
	    try {

	        boolean isDisplayed = workOutPage.isFollicularPhaseSectionDisplayed();
	        String actualText = workOutPage.getFollicularPhaseSectionText();
	        
	        System.out.println("Section displayed: " + isDisplayed);
	        System.out.println("Section text: " + actualText);

	        if (!isDisplayed) {
	            System.err.println("Section is not displayed.");
	        } else if (!actualText.equals(expectedText)) {
	            System.err.println(" Section text does not match expected. Expected: " 
	                               + expectedText + " but got: " + actualText);
	        } else {
	            System.out.println("Section is displayed with correct text.");
	        }

	    } catch (Exception e) {
	        System.err.println(" Error verifying section: " + e.getMessage());
	        Allure.addAttachment("Section Error", "text/plain", e.toString());
	    }
	}
	
	@Then("User should see description as {string}")
	public void user_should_see_description_as(String expectedDescription) {
	    try {

	        boolean isDisplayed = workOutPage.isFollicularPhaseDescriptionDisplayed();
	        String actualText = workOutPage.getFollicularPhaseDescriptionText();

	        System.out.println("Description displayed: " + isDisplayed);
	        System.out.println("Description text: " + actualText);

	        Allure.addAttachment("Follicular Phase Description", actualText);

	        if (!isDisplayed) {
	            System.err.println(" Follicular phase description is not displayed.");
	        } else if (!actualText.equals(expectedDescription)) {
	            System.err.println("Description text does not match expected. Expected: " 
	                               + expectedDescription + " but got: " + actualText);
	        } else {
	            System.out.println(" Follicular phase description is displayed with correct text.");
	        }

	    } catch (Exception e) {
	        System.err.println(" Error verifying luteal phase description: " + e.getMessage());
	        Allure.addAttachment("Description Error", "text/plain", e.toString());
	    }
	}
	@Then("User should see a {string} section")
	public void user_should_see_a_section(String expectedText) {
	    try {

	        boolean isDisplayed = workOutPage.isBenefitsSectionDisplayed();
	        String actualText = workOutPage.getBenefitsSectionText();

	        System.out.println("Section displayed: " + isDisplayed);
	        System.out.println("Section text: " + actualText);

	        Allure.addAttachment("Section Text", actualText);

	        if (!isDisplayed) {
	            System.err.println(" Section is not displayed.");
	        } else if (!actualText.equals(expectedText)) {
	            System.err.println("Section text does not match expected. Expected: " 
	                               + expectedText + " but got: " + actualText);
	        } else {
	            System.out.println("Section is displayed with correct text.");
	        }

	    } catch (Exception e) {
	        System.err.println(" Error verifying section: " + e.getMessage());
	        Allure.addAttachment("Section Error", "text/plain", e.toString());
	    }
	}
	@Then("User should see the description  under benefits of cycle synced workout section as {string}")
	public void user_should_see_the_description_under_benefits_of_cycle_synced_workout_section_as(String expectedDescription) {
	    try {

	        boolean isDisplayed = workOutPage.isSevenDayPlanDescriptionDisplayed();
	        String actualText = workOutPage.getSevenDayPlanDescriptionText();

	        System.out.println("Description displayed: " + isDisplayed);
	        System.out.println("Description text: " + actualText);

	        Allure.addAttachment("7-Day Plan Description", actualText);

	        if (!isDisplayed) {
	            System.err.println("7-Day workout plan description is not displayed.");
	        } else if (!actualText.equals(expectedDescription)) {
	            System.err.println(" Description text does not match expected. Expected: " 
	                               + expectedDescription + " but got: " + actualText);
	        } else {
	            System.out.println(" 7-Day workout plan description is displayed with correct text.");
	        }

	    } catch (Exception e) {
	        System.err.println(" Error verifying 7-Day workout plan description: " + e.getMessage());
	        Allure.addAttachment("Description Error", "text/plain", e.toString());
	    }
	}

	@Then("User should see below icons listed :")
	public void workout_options_should_be_visible(io.cucumber.datatable.DataTable dataTable) {

		List<String> expectedOptions = dataTable.asList();
		Assert.assertEquals(workOutPage.workoutPlanOptionsText(), expectedOptions, "Workout plan options do not match");
	}


}
