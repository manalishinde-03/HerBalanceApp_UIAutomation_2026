package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webElementActions.ElementActions;

public class SignUpPage {

	private WebDriver driver;
	private ElementActions util;
	private By emailId = By.xpath("//input[@type='email']");
	private By password = By.xpath("//input[@name='password']");
	private By confirmPassword = By.xpath("//input[@name='confirmPassword']");
	private By registerButton = By.xpath("//button[@type ='submit']");
	private By TnCcheckBox = By.xpath("//button[@id=':r8:-form-item']");
	private By errorLocator = By.xpath("//p[contains(@class,'text-destructive')]");
	private By passwordEyeIcon = By.xpath("//div[@id=':r6:-form-item']//button[@type='button']");
	private By confirmEyeIcon = By.xpath("//div[@id=':r7:-form-item']//button[@type='button']");	
	private By loginTabBtn = By.xpath("//button[text()='Login' or text()='Log In']");
	private By termsLink = By.xpath("//button[@data-testid='button-read-terms']");
	private By commonEyeIcon = By.xpath("//form//button[contains(@id, 'show')] | //form//i[contains(@class, 'eye')]");
	private By checkboxLocator = By.xpath("//button[@role='checkbox' or @data-testid='input-terms']");
	private By totalInputs = By.xpath("//form//input[@type='email' or @type='password' or @type='text']");

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementActions(this.driver);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public Object registerWithValidCredentials(String email, String password, String confirmPassword) {
		Object obj = null;
		util.doSendKeys(this.emailId, email);
		util.doSendKeys(this.password, password);
		util.doSendKeys(this.confirmPassword, confirmPassword);
		util.doClick(TnCcheckBox);
		util.doClick(registerButton);
		obj = new OnboardingPage(driver);
		return obj;
	}

	public void registerWithValidData(String email, String passwrd, String confirmPass, String acceptTerms) {
		util.sendKeysMethod(emailId, email);
		util.sendKeysMethod(password, passwrd);
		util.sendKeysMethod(confirmPassword, confirmPass);
		if ("Yes".equalsIgnoreCase(acceptTerms)) {
			util.elementVisibleClick(TnCcheckBox);
		}
	}

	public void clickRegisterBtn() {
		util.scrollWaitAndClick(registerButton);
	}

	public List<String> getAllErrorMessages() {
		return util.getTxtWhenVisible(errorLocator, 5);
	}

	public void navigateToOnboardpage() {
		driver.getCurrentUrl().contains("onboarding");
	}

	public void enterPswrd(String pwd, String confPwd) {
		util.sendKeysMethod(password, pwd);
		util.sendKeysMethod(confirmPassword, confPwd);
	}

	public void clickEyeIcon(String fieldName) {
		By eyeIcon = fieldName.toLowerCase().contains("confirm") ? confirmEyeIcon : passwordEyeIcon;
		util.elementVisibleClick(eyeIcon);
	}

	public boolean isFieldTypeCorrect(String fieldName, String expectedType) {
		By locator = fieldName.toLowerCase().contains("confirm") ? confirmPassword : password;
		return util.attributeChangeMethod(locator, fieldName, expectedType, 5);
	}

	public void verifyLogin() {
		util.waitForUrlToContain("auth");
		util.elementVisibleClick(loginTabBtn);
	}

	public boolean isPasswordVisible() {
		String type = util.getElement(password).getAttribute("type");
		return type.equals("text");
	}

	public boolean isShowpwdVisible() {
		WebElement eye = util.waitAndScroll(commonEyeIcon);
		return eye.isDisplayed();
	}

	public boolean isTermTxtVisible() {
		WebElement element = util.waitAndScroll(termsLink);
		return element.isDisplayed();
	}

	public int getTotalCount() {
		util.waitForElement(confirmPassword);
		List<WebElement> textInputs = driver
				.findElements(totalInputs);
		return textInputs.size();
	}

	public boolean isRdioBtnTermVisible() throws InterruptedException {
		WebElement ChkVisible = util.waitForElementWithoutScroll(checkboxLocator);
		return ChkVisible.isDisplayed();

	}

}
