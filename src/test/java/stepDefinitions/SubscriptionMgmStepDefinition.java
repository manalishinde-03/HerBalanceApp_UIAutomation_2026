package stepDefinitions;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import factory.DriverFactory;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LaunchPage;
import pageObjects.LoginPage;
import pageObjects.MenstrualPhaselogPage;
import pageObjects.SubScriptionMgm;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerFactory;
public class SubscriptionMgmStepDefinition {

	
	private SubScriptionMgm SubScriptionMgm;
	private WebDriver driver;
	private LaunchPage launchPage;
	private MenstrualPhaselogPage MenstrualPhaselogPage;
	private java.util.Map<String, String> testData;

	public SubscriptionMgmStepDefinition() {
		new ConfigReader();

		this.MenstrualPhaselogPage = new MenstrualPhaselogPage(DriverFactory.getDriver());

	}

	@Given("User clicks profile button labelled as {string} after logged in from {string}")
	public void user_clicks_profile_button_labelled_as_after_logged_in_from(String username, String testCaseKey) {

		driver = DriverFactory.getDriver();

		launchPage = new LaunchPage(driver);
		new LoginPage(driver);
		MenstrualPhaselogPage = new MenstrualPhaselogPage(driver);

		launchPage.clickLoginInBtn();

		testData = ExcelReader.getPersonalDetailsData("Onboarding_PersonalDetails", testCaseKey);
		System.out.println("Email from Excel - " + testData.get("Email"));
		MenstrualPhaselogPage.loginWithValidCredentials(testData.get("Email"), testData.get("Password"));

		SubScriptionMgm.clickProfileButton(username);

	}

	@Given("User is on the Her Balance Dashboard with the Profile submenu expanded")
	public void user_is_on_the_her_balance_dashboard_with_the_profile_submenu_expanded() {
		boolean isExpanded = SubScriptionMgm.isProfileMenuExpanded();
		Assert.assertTrue(isExpanded, "The profile submenu was not expanded!");

	}

	@When("User clicks on Subscription")
	public void user_clicks_on_subscription() {
		
		SubScriptionMgm.clickSubscriptionMenu();

	}

	@Then("Subscription Management should be visible")
	public void subscription_management_should_be_visible() {
		boolean isVisible = SubScriptionMgm.isSubscriptionHeaderVisible();
		Assert.assertTrue(isVisible, "FAILED: 'Subscription Management' heading is not visible on the page.");

	}

	@Then("exactly {int} sections should be visible")
	public void exactly_sections_should_be_visible(Integer expectedCount) {
		int actualCount = SubScriptionMgm.getMainSectionCount();

		LoggerFactory.getLogger().info("Found " + actualCount + " main sections.");

		Assert.assertEquals(actualCount, (int) expectedCount,
				"The page displayed " + actualCount + " sections but we expected " + expectedCount);

	}

	@Then("the following section headings should be displayed")
	public void the_following_section_headings_should_be_displayed(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedHeadings = dataTable.asList();
		List<String> actualHeadings = SubScriptionMgm.getSectionHeadings();

		for (String expected : expectedHeadings) {
			Assert.assertTrue(actualHeadings.contains(expected),
					"Heading '" + expected + "' was not found on the page! Found: " + actualHeadings);
		}
	}

	@Then("the following header texts should be visible")
	public void the_following_header_texts_should_be_visible(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedTexts = dataTable.asList();

		String actualHeader = SubScriptionMgm.getHeaderText();
		String actualSubText = SubScriptionMgm.getSubText();

		Assert.assertTrue(SubScriptionMgm.areHeadersDisplayed(), "Header not visible");
		Assert.assertEquals(expectedTexts.get(0), actualHeader);
		Assert.assertEquals(expectedTexts.get(1), actualSubText);
	}

	@Then("the following subheaders should be visible")
	public void the_following_subheaders_should_be_visible(io.cucumber.datatable.DataTable dataTable) {
		List<String> expectedSubheaders = dataTable.asList();

		for (String expectedText : expectedSubheaders) {

			String actualText = SubScriptionMgm.getSubheaderText(expectedText);
			Assert.assertEquals(actualText, expectedText, "Subheader text mismatch!");

			
		}
	}

	@Then("the following subscription information fields should be visible")
	public void the_following_subscription_information_fields_should_be_visible(
			io.cucumber.datatable.DataTable dataTable) {

		List<String> fields = dataTable.asList();

		for (String field : fields) {
			boolean isVisible = SubScriptionMgm.isFieldVisible(field);

			Assert.assertTrue(isVisible, "Field '" + field + "' was not visible on the page");
		}
	}

	@Then("Go to Dashboard button should be visible")
	public void go_to_dashboard_button_should_be_visible() {
		boolean isVisible = SubScriptionMgm.isDashboardButtonVisible();
		Assert.assertTrue(isVisible);
	}

	@Then("Go to Dashboard button should be enabled")
	public void go_to_dashboard_button_should_be_enabled() {
		boolean isEnabled = SubScriptionMgm.isDashboardButtonEnabled();

		// JUnit Order: (Message, Condition)
		Assert.assertTrue(isEnabled, "The 'Go to Dashboard' button is disabled, but was expected to be enabled");

	}

	@Then("{string} is displayed")
	public void is_displayed(String expectedText) {
		String actualText = SubScriptionMgm.getElementText(expectedText);

		Assert.assertEquals(actualText, expectedText, "The text was not found on the page!");
	}

	@Then("Exactly {int} sections should be visible on page")
	public void exactly_sections_should_be_visible_on_page(Integer expectedCount) {
		int actualCount = SubScriptionMgm.getVisibleSectionCount();
		Assert.assertEquals(actualCount, (int)expectedCount, "The number of visible sections does not match!");
	}

	@Then("{string} and {string} sections should be displayed")
	public void and_sections_should_be_displayed(String section1, String section2) {
		boolean isFirstVisible = SubScriptionMgm.isSectionVisible(section1.trim());
		boolean isSecondVisible = SubScriptionMgm.isSectionVisible(section2.trim());

		Assert.assertTrue(isFirstVisible, section1 + " section is not displayed");
		Assert.assertTrue(isSecondVisible, section2 + " section is not displayed");
	}

	@Then("{string}, {string}, {string}, {string} should be visible")
	public void should_be_visible(String dateRange, String cost, String transId, String paymentMethod) {
		String[] details = { dateRange, cost, transId, paymentMethod };

	    for (String detail : details) {
	        boolean isVisible = SubScriptionMgm.isHistoryDetailVisible(detail);
	        Assert.assertTrue(isVisible, "The following transaction detail was not found or visible: " + detail);
	    }
	}

	@Given("User is on Subscription Management Page")
	public void user_is_on_subscription_management_page() {
		SubScriptionMgm.clickSubscriptionMenu();
	    
	    String currentUrl = SubScriptionMgm.getCurrentPageUrl(); 

	    Assert.assertTrue(currentUrl.contains("/subscription"),
	            "User is not on the Subscription page! Current URL found was: " + currentUrl);
	}

	@When("User Clicks on Go to Dashboard in Current Subscription section")
	public void user_clicks_on_go_to_dashboard_in_current_subscription_section() {
		SubScriptionMgm.clickSubscriptionDashboardBtn();
	}

	@Then("User should navigate to Free Plan Dashboard page")
	public void user_should_navigate_to_free_plan_dashboard_page() {
		String currentUrl = new String();
		boolean isCorrectUrl = currentUrl.contains("/dashboard");
		Assert.assertTrue(isCorrectUrl, "Navigation failed: URL does not end with /dashboard");
		boolean isHeaderVisible = SubScriptionMgm.isFreePlanDashboardHeaderVisible();
		Assert.assertTrue(isHeaderVisible, "Free Plan Dashboard header is not visible");
	}

	@When("User Clicks on Go to Dashboard in Subscription History section")
	public void user_clicks_on_go_to_dashboard_in_subscription_history_section() {
		SubScriptionMgm.clickSubscriptionDashboardBtn();
	}
}
