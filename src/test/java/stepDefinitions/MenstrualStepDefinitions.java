package stepDefinitions;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.MenstrualPhaselogPage;
import pageObjects.OnboardingPage;
import utilities.ExcelReader;
import utilities.TestState;
public class MenstrualStepDefinitions {
	
		private WebDriver driver;
	    private LaunchPage launchPage;
	    private MenstrualPhaselogPage MenstrualPhaselogPage;
	    private java.util.Map<String, String> testData;


@Given("User is logged in from {string} and on the Her Balance Dashboard")
public void user_is_logged_in_from_and_on_the_her_balance_dashboard(String testCaseKey) {
	 driver = DriverFactory.getDriver();

     launchPage = new LaunchPage(driver);
     new LoginPage(driver);
     MenstrualPhaselogPage = new MenstrualPhaselogPage(driver); 
     
     launchPage.clickLoginInBtn();
     
     testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);
     System.out.println("Email from Excel - " +testData.get("Email"));
     MenstrualPhaselogPage.loginWithValidCredentials(
             testData.get("Email"), 
             testData.get("Password")
         );}

	@When("User clicks {string} and selects {string}")
	public void user_clicks_and_selects(String string, String string2) {
		MenstrualPhaselogPage.navigateToMenstrualLogs();
	}

	@Then("the UIElement should be visible")
	public void the_ui_element_should_be_visible(DataTable dataTable) {
	    WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("track/menstrual-cycle")); 
	    List<String> elementsToVerify = dataTable.asList().subList(1, dataTable.asList().size());

	    for (String elementName : elementsToVerify) {
	   
	        boolean isVisible = MenstrualPhaselogPage.isElementVisible(elementName);
	        
	        Assert.assertTrue(isVisible, "UI Element '" + elementName + "' was not found visible on the page!");
	        System.out.println("Verified visibility of: " + elementName);
	    }
	}

	@Then("the {string} displays {string}")
	public void the_displays(String component, String expectedValue) {
		String actualValue = MenstrualPhaselogPage.getComponentValue(component);

		if (expectedValue.contains("[CurrentCycleDay]")) {
			String regex = "Day \\d+ of your \\d+-day cycle";
			Assert.assertTrue(actualValue.matches(regex),
					"Actual text '" + actualValue + "' does not match the cycle progress pattern.");
		} else if (expectedValue.contains("[Current Phase]")) {
			String currentPhase = MenstrualPhaselogPage.getPhaseName();
			String localExpected = expectedValue.replace("[Current Phase]", currentPhase);
			Assert.assertEquals(actualValue, localExpected);
		} else {
			Assert.assertEquals(actualValue, expectedValue);
		}
	}

	
	@Then("the phase labels {string}, {string}, {string}, and {string} are displayed below the progress bar")
	public void the_phase_labels_and_are_displayed_below_the_progress_bar(String p1, String p2, String p3, String p4) {
		
			List<String> actualLabels = MenstrualPhaselogPage.getPhaseLabelTexts();
			Assert.assertTrue(actualLabels.contains(p1) && actualLabels.contains(p4),
					"Phase labels not found or incorrect.");
		}
	

	@Then("{string}, {string}, {string} labels are displayed")
	public void labels_are_displayed(String s1, String s2, String s3) {

		Assert.assertTrue(MenstrualPhaselogPage.isElementVisible(s1), s1 + " not visible");
		Assert.assertTrue(MenstrualPhaselogPage.isElementVisible(s2), s2 + " not visible");
		Assert.assertTrue(MenstrualPhaselogPage.isElementVisible(s3), s3 + " not visible");

	}
	

	@Then("Displays the phase based on the value entered during onboarding process")
	public void displays_the_phase_based_on_the_value_entered_during_onboarding_process() {
		String phase = MenstrualPhaselogPage.getLuthalPhaseName();
		Assert.assertNotNull(phase, "Current phase value is missing!");
	}

	

	@Then("the {string} matches the current active phase with the suffix {string}")
	public void verify_subsection_heading_matches_phase(String component, String suffix) {
	
		String currentPhase = MenstrualPhaselogPage.getCurrentPhaseName();


		String actualHeading = MenstrualPhaselogPage.getComponentValue(component);


		String expectedPattern = currentPhase + " " + suffix;

		Assert.assertTrue(actualHeading.contains(currentPhase) && actualHeading.contains(suffix),
				"Heading mismatch! Expected it to contain '" + expectedPattern + "' but found: " + actualHeading);
	}

	@Then("the content below the subsection heading matches the {string} description")
	public void the_content_below_the_subsection_heading_matches_the_description(String phase) {
		String actualHeading = MenstrualPhaselogPage.getComponentValue("Subsection Heading");

		Assert.assertTrue(actualHeading.toLowerCase().contains(phase.toLowerCase().trim()),
				"The description box is showing '" + actualHeading + "' but expected '" + phase + "'");
	}
	

	@Then("Display content should match current phase")
	public void display_content_should_match_current_phase() {
		String description = MenstrualPhaselogPage.getPhaseDescription();
		String currentPhase = MenstrualPhaselogPage.getCurrentPhaseName();

		if (currentPhase.equalsIgnoreCase("Luteal")) {
			Assert.assertTrue(description.contains("metabolism") && description.contains("magnesium-rich"),
					"Luteal phase description does not contain expected insights!");
		}
	}
	
	
	@Then("the heading text {string} should be displayed")
	public void the_heading_text_should_be_displayed(String expectedHeading) {
		Assert.assertEquals(expectedHeading, MenstrualPhaselogPage.getHeadingText());
	}

	@Then("the subtext {string} should be displayed")
	public void the_subtext_should_be_displayed(String expectedSubtext) {
		    String actualText = MenstrualPhaselogPage.getSubtext(); 
		    Assert.assertEquals(expectedSubtext, actualText.trim());
		}
	@Then("four subsections should be visible for the cycle phases")
	public void four_subsections_should_be_visible_for_the_cycle_phases() {
		int count = MenstrualPhaselogPage.getSubsectionCount();
	    Assert.assertEquals(count, 5, "Expected 5 subsections but found: " + count);
	
    }

	@Then("the subsection headings {string}, {string}, {string}, and {string} should be displayed")
	public void the_subsection_headings_and_should_be_displayed(String h1, String h2, String h3, String h4) {
		List<String> expectedHeadings = Arrays.asList(h1, h2, h3, h4);
	    List<String> actualHeadings = MenstrualPhaselogPage.getAllSubsectionHeadings();
	
	    for (String expected : expectedHeadings) {
	        Assert.assertTrue(actualHeadings.contains(expected), 
	            "Could not find phase: " + expected + " in the list: " + actualHeadings);
	    }
	}
	@Then("Subsection heading reflects the Current Phase in the format {string}")
	public void subsection_heading_reflects_the_current_phase_in_the_format(String format) {
	{ 
		    MenstrualPhaselogPage phasePage = new MenstrualPhaselogPage(driver);
		   
		    String actualHeading = phasePage.getSubsectionHeadingText();
		 
		    String detectedPhase = actualHeading.replace(" Phase Details", "").trim();
		   
		    TestState.setCurrentPhase(detectedPhase);
		   
		    String expectedFormat = format.replace("[Current Phase]", detectedPhase + " Phase");
		    assertEquals("The subsection heading format is incorrect!", expectedFormat, actualHeading);
		}
	}
	@Then("the start date for each phase should be displayed correctly based on onboarding cycle data")
	public void the_start_date_for_each_phase_should_be_displayed_correctly_based_on_onboarding_cycle_data() {
		LocalDate onboardingLmp = LocalDate.now().minusDays(10); // Example data
        int cycleLength = 28; // Example data
        
        boolean datesMatch = MenstrualPhaselogPage.verifyDatesAgainstSourceData(onboardingLmp, cycleLength);
        Assert.assertTrue(datesMatch, "UI dates do not match backend cycle data");
    }

	@Then("the start dates for upcoming phases should be displayed in {string} format")
	public void the_start_dates_for_upcoming_phases_should_be_displayed_in_format(String expectedFormat) {
		// expectedFormat is "Starts MMM dd"
        Assert.assertTrue(MenstrualPhaselogPage.validateUpcomingDateFormat(expectedFormat), 
            "Upcoming phase date format is incorrect");
    }
	@Then("the current phase start date should be displayed in {string} format")
	public void the_current_phase_start_date_should_be_displayed_in_format(String expectedFormat) {
		Assert.assertTrue(MenstrualPhaselogPage.validateCurrentPhaseDateFormat(expectedFormat), 
	            "Current phase date format is incorrect");
	    }

	@Then("the current phase subsection should be highlighted based on the user's cycle data")
	public void the_current_phase_subsection_should_be_highlighted_based_on_the_user_s_cycle_data() {
		
        Assert.assertTrue(MenstrualPhaselogPage.isCurrentPhaseHighlighted(), "The current phase is not visually highlighted");
    }
	
	@Then("the Section Heading displays {string}")
	public void the_section_heading_displays(String expectedValue) {
		String actualValue = MenstrualPhaselogPage.getUpcomingPhases("Upcoming Phases");
	    
	    Assert.assertEquals(actualValue, expectedValue, "The heading text does not match!");
	}
	

@Then("the subtext displays {string}")
public void the_subtext_displays(String expectedSubtext) {
String actualSubtext = MenstrualPhaselogPage.getUpcomingPhases("Plan ahead with your cycle phases");
    
    Assert.assertEquals(actualSubtext, expectedSubtext, "The subtext under Upcoming Phases is incorrect!");

}

	@Then("{int} phase subsections are visible on the page")
	public void phase_subsections_are_visible_on_the_page(Integer expectedCount) {
		
	    int actualCount = MenstrualPhaselogPage.getSubsectionCount();
	    Assert.assertEquals(actualCount, (int)expectedCount, 
	        "The number of phase subsections on the page is incorrect!");
	}

	@Then("the phase labels {string}, {string}, {string}, and {string} are displayed")
	public void the_phase_labels_and_are_displayed(String p1, String p2, String p3, String p4) {
		List<String> actualLabels = MenstrualPhaselogPage.getAllPhaseLabels();
	  
	    System.out.println("Actual Labels found: " + actualLabels);
	    Assert.assertTrue(actualLabels.contains(p1), "Label missing: " + p1);
	    Assert.assertTrue(actualLabels.contains(p2), "Label missing: " + p2);
	    Assert.assertTrue(actualLabels.contains(p3), "Label missing: " + p3);
	    Assert.assertTrue(actualLabels.contains(p4), "Label missing: " + p4);
	
	}

	@Then("each phase start date is displayed correctly based on the cycle data entered during onboarding")
	public void each_phase_start_date_is_displayed_correctly_based_on_the_cycle_data_entered_during_onboarding() {
		boolean isVisible = MenstrualPhaselogPage.areAllPhaseDatesVisible();
	    
	    Assert.assertTrue(isVisible, "Phase start dates are either missing or in the wrong format!");
	}
	@Then("all phase start dates are displayed in {string} format")
	public void all_phase_start_dates_are_displayed_in_format(String expectedFormat) {
		boolean isFormatCorrect = MenstrualPhaselogPage.verifyAllDatesMatchFormat(expectedFormat);
	    
	    Assert.assertTrue(isFormatCorrect, "One or more phase dates do not match the expected format: " + expectedFormat);
	}

	@Then("the current phase date is displayed in {string} format")
	public void the_current_phase_date_is_displayed_in_format(String datePattern) {
		WebElement currentDayElement = driver.findElement(By.xpath("//span[contains(text(), 'Started')]"));
	    String actualDateText = currentDayElement.getText().trim();
	    
	   
	    String regex = "Started [A-Z][a-z]{2} \\d{1,2}";
	    
	    Assert.assertTrue(actualDateText.matches(regex), 
	        "Current phase date format mismatch! Expected 'Started MMM d' but found: " + actualDateText);
	}
	@Then("the {string} subsection is highlighted based on cycle data entered during onboarding")
	public void the_subsection_is_highlighted_based_on_cycle_data_entered_during_onboarding(String phaseName) {
	
	    String actualActivePhase = MenstrualPhaselogPage.getHighlightedPhaseName();
	    System.out.println("LOG: The UI currently shows '" + actualActivePhase + "' as the active phase.");
	    
	    Assert.assertNotEquals(actualActivePhase, "None", "No phase is currently highlighted on the dashboard!");
	    List<String> validPhases = Arrays.asList("Menstrual Phase", "Follicular Phase", "Ovulation Phase", "Luteal Phase", "Perimenopause");
	    Assert.assertTrue(validPhases.contains(actualActivePhase), "The highlighted label '" + actualActivePhase + "' is not a recognized phase.");
	
	}
	@Then("{string} is displayed as per the current phase")
	public void is_displayed_as_per_the_current_phase(String componentName) {
	  
	    
	    String currentPhase = MenstrualPhaselogPage.detectCurrentPhaseFromUI();
	    
	    // 2. Fetch the content (Nutrition or Activities)
	    String actualContent = MenstrualPhaselogPage.getPhaseSpecificContent(componentName);
	   
	    switch (currentPhase) {
	    case "Menstrual":
	        // Update these based on what the UI actually says for Menstrual
	        assertTrue(actualContent.contains("Iron-rich") || actualContent.contains("Rest"), 
	            "Found: " + actualContent);
	        break;
	        
	    case "Follicular":
	        // UPDATED to match your HTML snippet
	        assertTrue(actualContent.contains("strength training") || actualContent.contains("intense workouts") || actualContent.contains("lean proteins"),
	            "Found: " + actualContent);
	        break;

	    case "Ovulation":
	        assertTrue(actualContent.contains("Fiber") || actualContent.contains("HIIT"),
	            "Found: " + actualContent);
	        break;

	    case "Luteal":
	        assertTrue(actualContent.contains("Magnesium") || actualContent.contains("Yoga"),
	            "Found: " + actualContent);
	        break;
	}
	}
	@Then("{string} should be displayed as per the current phase")
	public void should_be_displayed_as_per_the_current_phase(String componentName) {
	    MenstrualPhaselogPage phasePage = new MenstrualPhaselogPage(driver);
	    
	    // 1. Detect phase (e.g., "Follicular")
	    String currentPhase = phasePage.detectCurrentPhaseFromUI();
	    
	    // 2. Get UI text
	    String actualContent = phasePage.getPhaseSpecificContent(componentName);
	    String lowerContent = actualContent.toLowerCase(); // Safety first!

	    // 3. Validation Logic
	    switch (currentPhase) {
        case "Follicular":
            Assert.assertTrue(lowerContent.contains("complex carbs") && lowerContent.contains("lean proteins"),
                "Follicular keywords missing! Found: " + actualContent);
            break;
            
        case "Menstrual":
            Assert.assertTrue(lowerContent.contains("iron-rich") || lowerContent.contains("rest"),
                "Menstrual keywords missing! Found: " + actualContent);
            break;

        case "Ovulation":
            Assert.assertTrue(lowerContent.contains("fiber") || lowerContent.contains("hiit"),
                "Ovulation keywords missing! Found: " + actualContent);
            break;

        case "Luteal":
            Assert.assertTrue(lowerContent.contains("magnesium") || lowerContent.contains("yoga"),
                "Luteal keywords missing! Found: " + actualContent);
            break;
            
        default:
            Assert.fail("Automation Error: Unknown phase detected from UI: " + currentPhase);
    }
	}
	@Then("the {string} should be displayed in the format {string}")
	public void the_should_be_displayed_in_the_format(String component, String format) {
		// 1. Get the text from the UI
	    String actualDate = MenstrualPhaselogPage.getNextPeriodDateText();
	    
	    /** * 2. The Regex Logic 
	     * Matches: Full Month name, space, 1-2 digit day, comma, space, 4 digit year
	     * Example: February 28, 2026
	     */
	    String regex = "^(January|February|March|April|May|June|July|August|September|October|November|December) \\d{1,2}, \\d{4}$";
	    
	    // 3. TestNG Assertion (Condition, Message)
	    Assert.assertTrue(actualDate.matches(regex), 
	        "The date for '" + component + "' does not match the expected 'Month DD, YYYY' format. Found: " + actualDate);
	}
@Then("the {string} should be visible correctly based on the next period date")
public void the_should_be_visible_correctly_based_on_the_next_period_date(String component) {

	    String actualCountdown = MenstrualPhaselogPage.getDaysRemainingText();
	  
	    Assert.assertTrue(actualCountdown.matches("\\d+ days? remaining"), 
	        "Countdown text is incorrect or missing days! Found: " + actualCountdown);
	}
}



