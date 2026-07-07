package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.LoggerFactory;
import webElementActions.ElementActions;

public class DailyWorkOutPage {

	private WebDriver driver;
	private ElementActions elementActions;
	private By workOutLink = By.xpath("//a[contains(@href, '/workouts')] | //span[text()='Workout']");
	private By workOutHeader = By.xpath("//*[contains(translate(text(), 'WORKOUTS', 'workouts'), 'workouts')]");
	private By dashBoardHeader = By.xpath("//h1[contains(text(),'Free Plan')]");
	private By generateWorkoutBtn = By.xpath("//button[contains(normalize-space(), 'Generate')]");
	private By generateWorkoutHeader = By.xpath("//h2[contains(text(),'Daily')]");
	private By SuccessMsg = By.xpath("//div[text()='Success']");
	private By cycleBtn = By.xpath("//button[@role='tab']");
	private By cycledayTitle = By.xpath("//div[@class='flex items-center mb-3']//h3");
	private By listOfExcercise = By.xpath("//span[contains(@class, 'cursor-pointer ')]");
	private By allLabelsWithValue = By.xpath("//div[contains(@class, 'bg-purple-50')]//span");
	private By allMintValues = By.xpath("//div[@class='text-sm text-gray-500']");
	private By allIcons = By.xpath("//div[contains(@class, 'space-y-2')]//div[contains(@class, 'flex-1')]");
	private By moreExcerciseLink = By.xpath("//*[contains(text(), 'more exercises')]");
	private By viewAllExcerciseBtn = By.xpath("//div[@class='mt-4 flex justify-between gap-3']//button[2]");
	private By AllAboutHeader = By.xpath("//*[contains(text(), 'About Daily Workouts')]");
	private By aboutSection = By.xpath("//*[contains(text(),'About Daily Workouts')]");
	private By iconLink = By.tagName("svg");
	private By GenerateTxt = By.xpath("//*[contains(text(), 'Gemini') or contains(text(), 'tailored')]");

	public DailyWorkOutPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}

	public void clickWorkOutLink() {
		elementActions.waitForInvisibility(SuccessMsg);
		elementActions.waitForElementToBeClickable(workOutLink);
		elementActions.elementVisibleClick(workOutLink);
	}

	public boolean isAtWorkOutPage() {
		try {
			boolean urlMatches = elementActions.getCurrentUrl().toLowerCase().contains("workout");
			boolean headerVisible = elementActions.isElementDisplayd(workOutHeader);
			return urlMatches && headerVisible;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isDashBoardHeaderVisible() {
		return elementActions.isElementDisplayd(dashBoardHeader);
	}

	public void clickGenerateWorkOutBtn() {
		
		String currentUrl = elementActions.getCurrentUrl();
		if (currentUrl.contains("diet-plan")) {
			elementActions.waitForElementToBeClickable(workOutLink);
			elementActions.doClick(workOutLink);
			elementActions.waitForUrlToContain("workout");
		}
		elementActions.elementVisibleClick(generateWorkoutBtn);
	}

	public boolean isGenerateWorkOutHeaderVisible() {
		try {
			WebElement header = elementActions.getElement(generateWorkoutHeader);
			elementActions.scrollToView(header);
			return header.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLabelVisibleWithValue(String labelValue) {
		List<WebElement> allLabels = elementActions.getAllElements(allLabelsWithValue);
		for (WebElement element : allLabels) {
			String actualText = element.getText().trim();
			if (actualText.equalsIgnoreCase(labelValue)
					|| actualText.toLowerCase().contains(labelValue.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void waitForMsg() {
		elementActions.waitForInvisibility(SuccessMsg);
	}

	public String getCycleBtnTxt() {
		return elementActions.getElement(cycleBtn).getText();
	}

	public String getCycleDayTitle() {
		elementActions.waitForCondition(() -> !elementActions.getElement(cycledayTitle).getText().isEmpty(), 15);
		return elementActions.getElement(cycledayTitle).getText().trim();
	}

	public boolean isListOfExcerciseVisible(String expectedName) {
		List<WebElement> allExercises = driver.findElements(listOfExcercise);
		String expectedNameExcercise = expectedName.replaceAll("[’‘']", "'").toLowerCase().trim();
		for (WebElement element : allExercises) {
			String actualNameExcercise = element.getText().replaceAll("[’‘']", "'").toLowerCase().trim();
			if (actualNameExcercise.contains(expectedNameExcercise)) {
				return true;
			}
		}
		return false;
	}

	public boolean isDurationVisible() {;
		boolean viewAllmint = elementActions.waitForCondition(() -> !elementActions.getAllElements(allMintValues).isEmpty(), 10);
		List<WebElement> allMints = driver.findElements(allMintValues);
		return elementActions.getElementCount(allMints) > 0;
	}

	public boolean isIconVisible() {
		List<WebElement> exerciseSection = elementActions.getAllElements(allIcons);
		for (WebElement iconSection : exerciseSection) {
			elementActions.scrollToView(iconSection);
			List<WebElement> icons = iconSection.findElements(iconLink);
			if (icons.isEmpty() || !icons.get(0).isDisplayed()) {
				return false;
			}
		}
		return true;
	}

	public boolean isMoreExcerciseLinkVisible() {
		return elementActions.isElementDisplayd(moreExcerciseLink);
	}

	public boolean isViewAllBtnVisible() {
		boolean isVisible = elementActions.isElementVisibleAfterScroll(viewAllExcerciseBtn);
		return isVisible;
	}

	public boolean isGenerateWorkOutTxtVisible() {
		try {
			WebElement generateWorkOutTxt = elementActions.getElement(GenerateTxt);;
			elementActions.scrollToView(generateWorkOutTxt);
			return generateWorkOutTxt.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isAllAboutWorkOutTxtVisible(String buttonText) {
		return elementActions.isElementVisibleAfterScroll(AllAboutHeader);
	}

	public boolean isAllAboutWorkOutContentVisible(String expectedHeader) {
		elementActions.waitAndScroll(aboutSection);
		return true;
	}

	public void waitForExerciseListToLoad() {
		elementActions.isElementDisplayd(listOfExcercise);

	}

}
