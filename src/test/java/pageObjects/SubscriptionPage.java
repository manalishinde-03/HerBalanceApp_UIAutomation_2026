package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webElementActions.ElementActions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SubscriptionPage {
	WebDriver driver;
    WebDriverWait wait;
    private ElementActions util;
       
    public SubscriptionPage(WebDriver driver) {
    	 this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         this.util = new ElementActions(driver);
	}
 // --- Authentication ---
    public SubscriptionPage loginWithValidCredentials(String email, String pwd) {
        util.doSendKeys(this.emailIdTxt, email);
        util.doSendKeys(this.password, pwd);
        util.doClick(loginBtn);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        return this;
    }
    
    private By emailIdTxt = By.xpath("//input[@type='email']");
    private By password = By.xpath("//input[@name='password']");
    private By loginBtn = By.xpath("//*[contains(text(),'LogIn')]");

   
  //x-path
    private By header = By.xpath("//h1[contains(text(),'Choose Your Transformation Journey')]");
    private By subHeader = By.xpath("//p[contains(@class, 'text-[#A9A9A9] max-w-3xl mx-auto text-lg')]"); 
    private By planTiles = By.xpath("//div[contains(@class, 'grid grid-cols')]/div[contains(@style,'opacity: 1; transform: none;')]"); 

    
    private By freePlanTile = By.xpath("(//h2[contains(@class, 'text-lg font-poppins')])[1]");
    private By monthlyPlanTile = By.xpath("(//h2[contains(@class, 'text-lg font-poppins')])[2]");
    private By threeMonthPlanTile = By.xpath("(//h2[contains(@class, 'text-lg font-poppins')])[3]");
    
    private By freePlanSubTile = By.xpath("(//p[contains(@class, 'text-sm text-[#A9A9A9]')])[1]");
    private By monthlyPlanSubTile = By.xpath("(//p[contains(@class, 'text-sm text-[#A9A9A9]')])[2]");
    private By threeMonthPlanSubTile = By.xpath("(//p[contains(@class, 'text-sm text-[#A9A9A9]')])[3]");
    
    private By freeHeader = By.xpath("(//p[contains(@class, 'text-3xl font-semibold')])[1]");
    private By monthlyHeader = By.xpath("(//p[contains(@class, 'text-3xl font-semibold')])[2]");
    private By threeMonthHeader = By.xpath("(//p[contains(@class, 'text-3xl font-semibold')])[3]");
    
    private By freeSubHeader = By.xpath("(//p[contains(@class, 'text-xs text-[#A9A9A9]')])[1]");
    private By monthlySubHeader = By.xpath("(//p[contains(@class, 'text-xs text-[#A9A9A9]')])[2]");
    private By threeMonthSubHeader = By.xpath("(//p[contains(@class, 'text-xs text-[#A9A9A9]')])[3]");
    
    private By freeContains = By.xpath("(//ul[contains(@class, 'space-y-2')])[1]");
    private By monthlyContains = By.xpath("(//ul[contains(@class, 'space-y-2')])[2]");
    private By threeMonthContains = By.xpath("(//ul[contains(@class, 'space-y-2')])[3]");
    
    private By activePlanBtn = By.xpath("//button[contains(text(),'Active Plan')]");
    private By subscribeNowBtn = By.xpath("//button[contains(text(),'Subscribe Now')]");
    private By getStartedBtn = By.xpath("//button[contains(text(),'Get Started')]");
    
    private By mostPopularBadge= By.xpath("(//div[contains(@class,'inline-flex ')])[1]");
    private By bestValueBadge= By.xpath("(//div[contains(@class,'inline-flex ')])[2]");

    
    
  //Subcription- Page Header Section
    public String getPageHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }
    public String getSubHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subHeader)).getText();
    }
    public int getPlanTileCount() {
        return driver.findElements(planTiles).size();
    }

    
  //Subcription- Free Plan tile
    public String getFreePlanTile() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(freePlanTile)).getText();
    }
    public String getFreePlanSubTile() {
    	 return wait.until(ExpectedConditions.visibilityOfElementLocated(freePlanSubTile)).getText();
    }
    public String getFreeHeader() {
   	 return wait.until(ExpectedConditions.visibilityOfElementLocated(freeHeader)).getText();
   }
    public String getFreeSubHeader() {
      	 return wait.until(ExpectedConditions.visibilityOfElementLocated(freeSubHeader)).getText();
   }
    public boolean isFreeContainsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(freeContains)).isDisplayed();
   }
    public boolean isActivePlanButtonDisplayed(){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(activePlanBtn)).isDisplayed();
   }
    public boolean isActivePlanButtonEnabled() {
        return driver.findElement(activePlanBtn).isEnabled();
   }

    
    
  //Subcription- Monthly Plan tile
    public boolean isMostPopularBadgeDisplayed() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mostPopularBadge)).isDisplayed();
      }
    public String getMonthlyPlanTile() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyPlanTile)).getText();
    }
    
    public String getMonthlyPlanSubTile() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyPlanSubTile)).getText();
    }
    public String getMonthlyHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyHeader)).getText();
    }
    public String getMonthlySubHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(monthlySubHeader)).getText();
    }
    public boolean isMonthlyContainsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(monthlyContains)).isDisplayed();
    }
    public boolean isSubscribeNowBtnDisplayed() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(subscribeNowBtn)).isDisplayed();
    }
    public boolean isSubscribeNowButtonEnabled() {
        return driver.findElement(subscribeNowBtn).isEnabled();
    }
    
    
    
    
    
  //Subcription- 3 Month Plan tile
    public boolean isBestValueBadgeDisplayed() {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(bestValueBadge)).isDisplayed();
    }
    public String getThreeMonthPlanTile() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(threeMonthPlanTile)).getText();
    }
    public String getThreeMonthPlanSubTile() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(threeMonthPlanSubTile)).getText();
    }
    public String getThreeMonthHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(threeMonthHeader)).getText();
    }
    public String getThreeMonthSubHeader() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(threeMonthSubHeader)).getText();
    }
    public boolean isThreeMonthContainsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(threeMonthContains)).isDisplayed();
    }
    public boolean isGetStartedButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(getStartedBtn)).isDisplayed();
    }
   
}
    

