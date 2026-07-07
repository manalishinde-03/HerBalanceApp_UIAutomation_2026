package pageObjects;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webElementActions.ElementActions;

public class WorkOutPage {

	private WebDriver driver;
	private ElementActions util;

	public WorkOutPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementActions(this.driver);
	}

	// Locators
	private By mainDashBoard = By.xpath("//h1[text()='Free Plan Dashboard']");
	private By workOut = By.xpath("//a[text()='Workout']");
	private By dailyPersonalisedWorkOut = By.xpath("//h1[text()='Daily Personalized Workouts']");
	private By backToDashBoard = By.xpath("//span[text()='Back to Dashboard']");
	private By dailyWorkOutPlan = By.xpath("//h2[text()='Daily Workout Plan']");
	private By freshAI = By.xpath("//p[text()='Fresh AI-powered workout tailored to your current cycle phase']");
	private By noWorkOutPlan = By.xpath("//h3[text()='No workout plan found']");
	//private By noWorkoutMessageStrong = By.xpath("//p/strong[text()='No workout plan found']");
	private By noWorkoutMessageDetail = By.xpath("//p[contains(text(),'Generate a personalized daily workout')]");
	private By generateWorkoutBtn = By.xpath("//button[text()='Generate Workout Plan']");
	private By follicularPhaseSection = By.xpath("//h2[text()='Your Follicular Phase Workouts']");
	private By follicularPhaseDescription = By.xpath("//div[@class='section']/p[contains(text(),'follicular phase')]");
	private By benefitsSection = By.xpath("//h3[text()='Benefits of Cycle-Synced Workouts']");
	private By sevenDayPlanDescription = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div[4]/p");
	private By workoutPlanOptionsText = By.xpath("//div/h4");

	private boolean isDisplayed(By locator) {
		return util.isElementDisplayed(locator);
	}

	private String getText(By locator) {
		return util.getElementText(locator);
	}

//	public String getMainDashBoardUrl() {
//		return util.getPageURL();
//	}

	public boolean isMainDashBoardDisplayed() {
		return isDisplayed(mainDashBoard);
	}

	public void clickOnWorkOut() {
		util.doClick(workOut);
	}

	public boolean isDailyPersonalisedDisplayed() {
		return isDisplayed(dailyPersonalisedWorkOut);
	}

	public String getDailyPersonalisedText() {
		return getText(dailyPersonalisedWorkOut);
	}

	public boolean isBacktoDashBoardDisplayed() {
		return isDisplayed(backToDashBoard);
	}

	public String getBacktoDashBoardText() {
		return getText(backToDashBoard);
	}

	public boolean isDailyWorkOutPlanDisplayed() {
		return isDisplayed(dailyWorkOutPlan);
	}

	public String getDailyWorkOutPlanText() {
		return getText(dailyWorkOutPlan);
	}

	public boolean isFreshAIDisplayed() {
		return isDisplayed(freshAI);
	}

	public String getFreshAIText() {
		return getText(freshAI);
	}

	public boolean isNoWorkOutPlanDisplayed() {
		return isDisplayed(noWorkOutPlan);
	}

	public String getNoWorkOutPlanText() {
		return getText(noWorkOutPlan);
	}

	public boolean isNoWorkoutMessageDisplayed() {
		return util.isElementDisplayed(noWorkoutMessageDetail);
	}

	public String getNoWorkoutMessageText() {
		String detailText = util.getElementText(noWorkoutMessageDetail);
		return detailText;
	}

	public String getNoWorkoutMessageAlignment() {
		return util.getCssValue(noWorkoutMessageDetail, "text-align");
	}
	
	public boolean isGenerateWorkoutButtonDisplayed() {
	    return util.isElementDisplayed(generateWorkoutBtn);
	}

	public String getGenerateWorkoutButtonText() {
	    return util.getElementText(generateWorkoutBtn);
	}
	public boolean isFollicularPhaseSectionDisplayed() {
		
		util.waitAndScroll(follicularPhaseSection);
	    return util.isElementDisplayed(follicularPhaseSection);
	}

	public String getFollicularPhaseSectionText() {
	   // return util.getElementText(follicularPhaseSection);
		return util.waitAndScroll(follicularPhaseSection).getText();
	   
	}
	
	public boolean isFollicularPhaseDescriptionDisplayed() {
	    return util.isElementDisplayed(follicularPhaseDescription);
	}

	public String getFollicularPhaseDescriptionText() {
	    return util.waitAndScroll(follicularPhaseDescription).getText();
	}
	public boolean isBenefitsSectionDisplayed() {
	    return util.isElementDisplayed(benefitsSection);
	}

	public String getBenefitsSectionText() {
	    return util.getElementText(benefitsSection);
	}
	public boolean isSevenDayPlanDescriptionDisplayed() {
	    return util.isElementDisplayed(sevenDayPlanDescription);
	}

	public String getSevenDayPlanDescriptionText() {
	    return util.getElementText(sevenDayPlanDescription);
	}

	public Collection<String> workoutPlanOptionsText() {
		List<WebElement> texts = util.getElements(workoutPlanOptionsText);
		List<String> actualOptions = texts.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Actual options -" + actualOptions.toString());
		return actualOptions;
	}
}
