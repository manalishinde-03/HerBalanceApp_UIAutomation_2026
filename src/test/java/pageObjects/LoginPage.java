package pageObjects;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webElementActions.ElementActions;

public class LoginPage {
	private WebDriver driver;
	private ElementActions util;
	private By emailIdTxt = By.xpath("//input[@type='email']");
	private By password = By.xpath("//input[@name='password']");
	private By showPassword = By.xpath("//form//button[contains(@id, 'show')] | //form//i[contains(@class, 'eye')]");
	private By loginBtn = By.xpath("//*[contains(text(),'LogIn')]");
	private By forgetPwdBtn = By.xpath("//button[normalize-space()='Forgot password?']");
	private By allErrors = By
			.xpath("//li[@role='status']//div[contains(@class, 'text-base')] | //p[contains(@class, 'destructive')]");
	private By eyeIcon = By.xpath("//div[@class='relative']//button");
	private By loginBtnTab = By
			.xpath("//button[role='tab' and contains(normalize-space(), 'Login')] | //button[text()='Login']");
	private By signUpBtnTab = By
			.xpath("//button[role='tab' and contains(normalize-space(), 'Sign Up')] | //button[text()='Sign Up']");
	private By logo = By.xpath("//header//img[@alt='HerBalance Logo']");
	private By displayAllCard = By.xpath("//div[@class='flex items-start']//h3");
	private By totalTabs = By.xpath("//div[@role='tablist']//button");
	private By btnSignUp = By.xpath("//*[contains(translate(text(), 'SIGNUP', 'signup'), 'sign up')]");
	private By passwordType = By.xpath("//input[@type='password']");
	private By commonEyeIcon = By.xpath("//form//button[contains(@id, 'show')] | //form//i[contains(@class, 'eye')]");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementActions(this.driver);
	}

	public void registerWithValidData(String email, String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(emailIdTxt));
		emailField.clear();
		emailField.sendKeys(email);
		WebElement passField = wait.until(ExpectedConditions.elementToBeClickable(password));
		passField.clear();
		passField.sendKeys(pwd);

	}

	public void clickLoginBtn() {
		WebElement btn = util.getElement(loginBtn);
		util.scrollWaitAndClick(loginBtn);
	}

	public void clickSignUp() {
		WebElement btn = util.getElement(btnSignUp);
		btn.click();
	}

	public List<String> getAllErrorMessages() {
		return util.getTxtWhenVisible(allErrors, 5);
	}

	public void enterPswrd(String pwd) {
		util.getElement(password).sendKeys(pwd);
	}

	public boolean verifyPasswordVisibility() {
		util.elementVisibleClick(showPassword);
		String paswrdField = util.getElement(password).getAttribute("type");
		return paswrdField.equals("text");
	}

	public boolean isPasswordVisible() {
		String type = util.getElement(password).getAttribute("type");
		return type.equals("text");
	}

	public void clickEyeIcon() {
		util.getElement(eyeIcon).click();
	}

	public void clickForgotPassword() {
		util.getElement(forgetPwdBtn).click();
	}

	public void clickSignUpTab() {
		util.getElement(signUpBtnTab).click();
	}

	public boolean navigateToForgotPwdPage() {
		return util.getCurrentUrl().contains("forgot-password");
	}

	public boolean navigateToSignUpPage() {
		return util.getCurrentUrl().contains("auth");
	}

	public boolean isHerLogoVisible() {
		return util.getElement(logo).isDisplayed();
	}

	public boolean isloginBtnTabVisible() {
		boolean isLoginVisible = util.waitAndScroll(loginBtnTab).isDisplayed();
		return isLoginVisible;
	}

	public boolean issignUpBtnTabVisible() {
		boolean isSignUpVisible = util.waitAndScroll(signUpBtnTab).isDisplayed();
		return isSignUpVisible;
	}

	public String isLoginTabActive() {
		String activeLogin = util.getAttributeVal(loginBtnTab, "data-state");
		return activeLogin;
	}

	public String verifyEmailIdPresences() {
		String emailIdPresence = util.getAttributeVal(emailIdTxt, "placeholder");
		return emailIdPresence;
	}

	public String verifyPasswrdPresences() {
		util.waitForUrlToContain("signup");
		WebElement element = util.elementWithFluentWaitLocated(passwordType, 5, 500);
		return element.getAttribute("type");

	}

	public boolean isSignUpPageLoaded() {
		util.waitForUrlToContain("auth");
		return util.elementWithFluentWaitLocated(password, 5, 500).isDisplayed();
	}

	public boolean isShowpwdVisible() {
		WebElement eye = util.waitAndScroll(commonEyeIcon);
		return eye.isDisplayed();
	}

	public boolean isloginBtnEnabled() {
		WebElement btn = util.elementWithFluentWaitLocated(loginBtn, 10, 500);
		return btn.isDisplayed() && btn.isEnabled();
	}

	public boolean isForgotpwdVisible() {
		WebElement link = util.waitAndScroll(forgetPwdBtn);
		return link.isDisplayed();
	}

	public String isSignUpBtnTabActive() {
		String activeLogin = util.getAttributeVal(signUpBtnTab, "data-state");
		return activeLogin;
	}

	public List<String> getAllCards() {
		return util.getTxtWhenVisible(displayAllCard, 5);
	}

	public boolean getTestimonialTxt(int age) {
		By testimonialTxt = By
				.xpath("//*[contains(normalize-space(), 'Sarah') and contains(normalize-space(), '" + age + "')]");
		WebElement element = util.waitAndScroll(testimonialTxt);
		return element.isDisplayed();
	}

	public int getTotalTabs() {		
		List<WebElement> tabs = util.getAllElements(totalTabs);
		return tabs.size();
	}

	public int getTotalInputFields() {		
		int emailTxtCount = util.getAllElements(emailIdTxt).size();
		int pwdTxtCount = util.getAllElements(emailIdTxt).size();
		return emailTxtCount + pwdTxtCount;
	}

	public Object loginWithValidCredentials(String email, String password) {
		Object obj = null;
		util.doSendKeys(this.emailIdTxt, email);
		util.doSendKeys(this.password, password);
		util.doClick(loginBtn);
		obj = new OnboardingPage(driver);
		return obj;
	}
}
