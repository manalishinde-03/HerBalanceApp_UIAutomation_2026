package pageObjects;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webElementActions.ElementActions;
public class SubScriptionMgm {
	  private WebDriver driver;
	    private WebDriverWait wait;
	    private ElementActions util;

	    public SubScriptionMgm(WebDriver driver) {
	    	this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        this.util = new ElementActions(this.driver);
	    }

	    // --- Profile & Navigation ---
	    private By expandedMenuContainer = By.xpath("//div[@data-state='open' and @role='menu']");
	    private By subscriptionMenuOption = By.xpath("//div[@role='menuitem']//span[contains(text(), 'Subscription')]");
	    private By subscriptionMenuItem = By.xpath("//div[contains(text(), 'Subscription')]");
	    private By subscriptionPageHeading = By.xpath("//h1[text()='Subscription Management']");

	    // --- Section Headers & Containers ---
	    private By mainSectionHeaders = By.xpath("//h3[contains(@class, 'tracking-tight') and following-sibling::p]");
	    private By sectionHeaders = By.xpath("//h3[contains(@class, 'tracking-tight')]");
	    private By sectionContainers = By.xpath("//div[contains(@class, 'rounded-lg') and .//h3]");
	    
	    // --- Current Subscription Section ---
	    private By headerLocator = By.xpath("//h3[text()='Current Subscription']");
	    private By subTextLocator = By.xpath("//p[text()='Your active subscription plan details']");
	    private By currentSectionDashboardBtn = By.xpath("//h3[contains(text(),'Current Subscription')]/ancestor::div[contains(@class,'p-6')]//button[contains(.,'Go to Dashboard')]");

	    // --- Subscription History Section ---
	    private By historyHeader = By.xpath("//h3[text()='Subscription History']");
	    private By historySubText = By.xpath("//p[text()='Your subscription payment history']");
	    private By historySectionDashboardBtn = By.xpath("//h3[text()='Subscription History']/ancestor::div[contains(@class,'p-6')]//button[contains(.,'Go to Dashboard')]");

	    // --- Dashboard Page ---
	    private By dashboardHeader = By.xpath("//h1[text()='Free Plan Dashboard']");
	    private By goToDashboardBtn = By.xpath("//button[normalize-space()='Go to Dashboard']");

	    // --- Dynamic Locators (Helper Methods) ---
	    private By getUserProfileLocator(String username) {
	        return By.xpath("//button[.//span[contains(text(), '" + username + "')]]");
	    }

	    // ==========================================
	    //                METHODS
	    // ==========================================

	    // --- Navigation & Profile Actions ---

	    public void clickProfileButton(String username) {
	        By profileButton = getUserProfileLocator(username);
	        try {
	            wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
	        } catch (StaleElementReferenceException e) {
	            driver.findElement(profileButton).click();
	        }
	    }

	    public void navigateToSubscription() {
	        wait.until(ExpectedConditions.elementToBeClickable(subscriptionMenuOption)).click();
	    }

	    public void clickSubscriptionMenu() {
	        wait.until(ExpectedConditions.elementToBeClickable(subscriptionMenuItem)).click();
	    }

	    public boolean isProfileMenuExpanded() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(expandedMenuContainer)).isDisplayed();
	        } catch (Exception e) { return false; }
	    }

	    // --- Header & Section Verifications ---

	    public boolean isSubscriptionHeaderVisible() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(subscriptionPageHeading)).isDisplayed();
	        } catch (Exception e) { return false; }
	    }

	    public String getHeaderText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerLocator)).getText();
	    }

	    public String getSubText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(subTextLocator)).getText();
	    }

	    public boolean areHeadersDisplayed() {
	        return driver.findElement(headerLocator).isDisplayed() && 
	               driver.findElement(subTextLocator).isDisplayed();
	    }

	    public int getMainSectionCount() {
	        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(mainSectionHeaders)).size();
	    }

	    public int getVisibleSectionCount() {
	        return driver.findElements(sectionContainers).size();
	    }

	    public List<String> getSectionHeadings() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeaders));
	        List<WebElement> elements = driver.findElements(sectionHeaders);
	        List<String> headings = new ArrayList<>();
	        for (WebElement e : elements) {
	            String text = e.getText().trim();
	            if (!text.isEmpty()) { headings.add(text); }
	        }
	        return headings;
	    }

	    // --- Field & Sub-header Verifications ---

	    public String getSubheaderText(String expectedHeader) {
	        By locator = By.xpath("//h3[normalize-space()='" + expectedHeader + "']");
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
	    }

	    public boolean isFieldVisible(String fieldName) {
	        return driver.findElement(By.xpath("//div[text()='" + fieldName + ":']")).isDisplayed();
	    }

	    public boolean isSectionVisible(String sectionText) {
	        String xpath = "//*[(self::h3 or self::button) and contains(normalize-space(), '" + sectionText.trim() + "')]";
	        return driver.findElement(By.xpath(xpath)).isDisplayed();
	    }

	    public boolean isHistoryDetailVisible(String detailText) {
	        // contains is safer for strings with hyphens or special punctuation
	        String xpath = "//*[contains(normalize-space(), '" + detailText + "')]";
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    public String getElementText(String textToFind) {
	        try {
	            By dynamicLocator = By.xpath("//*[normalize-space()='" + textToFind + "']");
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicLocator)).getText().trim();
	        } catch (Exception e) {
	            return "ELEMENT_NOT_VISIBLE: " + textToFind;
	        }
	    }

	    // --- Dashboard & Button Actions ---

	    public boolean isDashboardButtonVisible() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(goToDashboardBtn)).isDisplayed();
	        } catch (Exception e) { return false; }
	    }

	    public boolean isDashboardButtonEnabled() {
	        return wait.until(ExpectedConditions.presenceOfElementLocated(goToDashboardBtn)).isEnabled();
	    }

	    public void clickSubscriptionDashboardBtn() {
	        wait.until(ExpectedConditions.elementToBeClickable(currentSectionDashboardBtn)).click();
	    }

	    public void scrollToAndClickHistoryDashboardBtn() {
	        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(historySectionDashboardBtn));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
	        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
	    }

	    public boolean isFreePlanDashboardHeaderVisible() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
	    }

	    public String getCurrentPageUrl() {
	        wait.until(ExpectedConditions.urlContains("/subscription"));
	        return driver.getCurrentUrl();
	    }
	}

