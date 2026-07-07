package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import webElementActions.ElementActions;

public class LaunchPage {
	private WebDriver driver;
	private ElementActions elementActions;
	private By cycleTrackingTitle = By.xpath("//h3[contains(text(),'Cycle Tracking App')]");
	private By cycleTrackingFeature = By.xpath("//p[contains(text(),'Track your cycle and receive')]");
	private By btnLogIn = By.xpath("//button[contains(normalize-space(), 'Log')] | //a[contains(@href, 'login')]");
	private By btnSignUp = By.xpath("//button[contains(normalize-space(), 'Sign')] | //a[contains(@href, 'register')]");
	private By imgInSync = By.xpath("//div//img[@alt='Workout Exercises for Women']");
	private By EmpowerTitle = By.xpath("//div[@class='text-center mb-12']//h1");
	private By EmpowerWeightTxt = By.xpath("//div[@class='text-center mb-12']//p");
	private By sectionPath = By.xpath("//body/div[1]");
	private By getStartedBtn = By.xpath("//button[contains(text(),'Get Started Now')]");
	private By startYourPersonalizeBtn = By.xpath("//button[contains(text(),'Start Your Personalized Journey')]");
	private By signUpBtnTab = By.xpath("//button[contains(text(),'Sign Up')]");
	private By loginBtnTab = By.xpath("//button[(text()='Login')]");

	public LaunchPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
	}

	public void clickOnSignUpButton() {
		elementActions.getElement(btnSignUp).isDisplayed();
		elementActions.getElement(btnSignUp).click();
	}

	public List<String> displayCardsIsVisible(String phase){
		By displayCards = By.xpath("//h3[contains(text(),'" + phase + "')]");
		List<String> displayPhaseName = new ArrayList<>();
		elementActions.waitAndScroll(displayCards);
		List<WebElement> elements = elementActions.getElements(displayCards);
		for (WebElement card : elements) {
			displayPhaseName.add(card.getText().trim());
		}
		return displayPhaseName;
	}

	public String isCycleTrackingTitleVisible() {
		return elementActions.waitAndScroll(cycleTrackingTitle).getText().trim();
	}

	public String isCycleTrackingFeatureVisible() {
		return elementActions.getElement(cycleTrackingFeature).getText();
	}

	public boolean isbtnLogInVisible() {
		return elementActions.waitAndScroll(btnLogIn).isDisplayed();
	}

	public boolean isbtnSignUpVisible() {
		elementActions.waitForUrlToContain("register");
		return true;
	}

	public boolean isImgInSyncVisible() {
		return elementActions.waitAndScroll(imgInSync).isDisplayed();
	}

	public boolean isEmpowerTitleVisible() {
		return elementActions.getElement(EmpowerTitle).isDisplayed();
	}

	public String getEmpowerWeightTxt() {
		return elementActions.getElement(EmpowerWeightTxt).getText();
	}

	public String getBackroundColour() {
		WebElement body = elementActions.waitForElementWithoutScroll(By.tagName("body"));
		WebElement mainDiv = driver.findElement(sectionPath);
		String bkgcolor = mainDiv.getCssValue("background-color");
		if (bkgcolor.contains("0, 0, 0, 0")) {
			bkgcolor = body.getCssValue("background-color");
		}
		return bkgcolor;
	}

	public String isGetStartedbtnVisible() {
		return elementActions.waitAndScroll(getStartedBtn).getText();
	}

	public String isStartYourPersonalizebtnVisible() {
		return elementActions.waitAndScroll(startYourPersonalizeBtn).getText();
	}

	public void clickLoginInBtn() {
		elementActions.getElement(btnLogIn).click();
	}

	public boolean loginPageTabVisible() {
		return elementActions.getElement(loginBtnTab).isDisplayed();
	}

	public void clickSignUpBtn() {
		elementActions.getElement(btnSignUp).click();
	}

	public boolean signUpPageTabVisible() {
		return elementActions.getElement(signUpBtnTab).isDisplayed();
	}

	public void clickGetStartedBtn() {
		elementActions.getElement(getStartedBtn).click();
	}

	public void clickStartYourPersonalizeBtn() {
		elementActions.getElement(startYourPersonalizeBtn).click();
	}

	public SignUpPage navigateToRegisterPage() {
		elementActions.elementVisibleClick(btnSignUp);
		return new SignUpPage(driver);
	}

	public LoginPage navigateToAuthPage() {
		elementActions.getElement(btnLogIn).click();
		return new LoginPage(driver);
	}

}
