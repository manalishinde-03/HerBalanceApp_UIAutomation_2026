package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webElementActions.ElementActions;

public class ActivityInsightsMenuWTPage {

	WebDriver driver;
    WebDriverWait wait;
    private ElementActions util;
       
    public ActivityInsightsMenuWTPage(WebDriver driver) {
    	 this.driver = driver;
         this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         this.util = new ElementActions(driver);
	}
 // --- Authentication ---
    public ActivityInsightsMenuWTPage loginWithValidCredentials(String email, String pwd) {
        util.doSendKeys(this.emailIdTxt, email);
        util.doSendKeys(this.password, pwd);
        util.doClick(loginBtn);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        return this;
    }
    
  //Login
    private By emailIdTxt = By.xpath("//input[@type='email']");
    private By password = By.xpath("//input[@name='password']");
    private By loginBtn = By.xpath("//*[contains(text(),'LogIn')]");
    
  //Weight Tracking UI Verification
    private By activityInsightsMenu = By.xpath("//span[contains(text(), 'Activity Insights')]");
    private By trackWeightSubMenu = By.xpath("//a[contains(@href, '/track/weight')]"); 
    private By pageHeader = By.xpath("//h1[text()='Weight Tracking']");
    private By pageSubHeader = By.xpath("//p[contains(@class, 'text-sm text-gray-600 capitalize')]");
    private By backToDashboardBtn = By.xpath("//button[contains(text(),'Back to Dashboard')]");
    private By threecards = By.xpath("//div[contains(@class, 'grid grid-cols-1 md:grid-cols-3 gap-4')]");
    private By startingWeightCard = By.xpath("//div[contains(@class, 'grid grid-cols-1 md:grid-cols-3 gap-4')]/div[1]");
    private By currentWeightCard = By.xpath("//div[contains(@class, 'grid grid-cols-1 md:grid-cols-3 gap-4')]/div[2]");
    private By goalWeightCard = By.xpath("//div[contains(@class, 'grid grid-cols-1 md:grid-cols-3 gap-4')]/div[3]");
    private By startingWeightValue = By.xpath("//p[contains(@class, 'text-xl font-bold text-blue-600')]");
    private By currentWeightValue = By.xpath("//p[contains(@class, 'text-xl font-bold text-green-600')]");
    private By goalWeightValue = By.xpath("//p[contains(@class, 'text-xl font-bold text-purple-600')]");
    private By progressOverviewheader = By.xpath("//h3[contains(text(),'Progress Overview')]");
    private By weightLossProgressSubText = By.xpath("//span[text()='Weight Loss Progress']");
    private By completionPercentage = By.xpath("//span[contains(@class, 'text-sm font-medium text-violet-600')]");
    private By progressBar = By.xpath("//div[@role='progressbar']");
    private By weightLost = By.xpath("//p[text()='Weight Lost']");   
    private By remainingWeight = By.xpath("//p[text()='Remaining']");
    private By graphHeader = By.xpath("//h3[contains(text(),'Weight Progression')]");
    private By weightTrackingChart = By.xpath("//*[local-name()='svg' and @class='recharts-surface']");
    private By xAxis = By.xpath("//*[local-name()='g' and contains(@class, 'recharts-xAxis')]//*[local-name()='tspan']");
    private By yAxis = By.xpath("//*[local-name()='g'][contains(@class, 'recharts-layer recharts-cartesian-axis recharts-xAxis xAxis')]");
    private By goalWeight = By.xpath("//*[local-name()='text']//*[local-name()='tspan' and text()='Goal weight']");
    private By logTodaysWeight = By.xpath("//div[contains(text(), 'Log Today's Weight')]");
    private By weightInputField = By.xpath("//input[@placeholder='Enter your weight' or @name='weight']");
    private By bmiInputField = By.xpath("//input[contains(@id,'bmi')]");
    private By logWeightBtn = By.xpath("//button[text()='Log Weight']");
    private By noteValue = By.xpath("//textarea[contains(@id, 'note')]");
  
    
 
    
    
  //Weight Tracking functional verification
    private By errorMessage = By.xpath("//div[text()='Please enter a valid weight value.']");
    private By successMessage = By.xpath("//div[text()='Weight Logged Successfully']");
    private By weightLostValue = By.xpath("//p[contains(@class, 'text-lg font-bold text-green-600')]");
    private By remainingValue = By.xpath("//p[contains(@class, 'text-lg font-bold text-orange-600')]");
    private By getNoteTextValue = By.xpath("//p[contains(@class, 'text-sm text-gray-600 mt-1 break-all')]");
  
 
  
    public void clickaAtivityInsightsMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(activityInsightsMenu)).click();
    }
    public void clickTrackWeight() {
        wait.until(ExpectedConditions.elementToBeClickable(trackWeightSubMenu)).click();
    }
    public String getHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader)).getText();
    }
    public String getSubHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageSubHeader)).getText();
    }
    public boolean isBackToDashboardBtnDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(backToDashboardBtn)).isDisplayed();
    }
    public int getThreeCardsCount() {
        return driver.findElements(threecards).size();
    }
    
    public boolean weightCardsDisplayed() {
        return 
        wait.until(ExpectedConditions.visibilityOfElementLocated(startingWeightCard)).isDisplayed() &&
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentWeightCard)).isDisplayed() &&
        wait.until(ExpectedConditions.visibilityOfElementLocated(goalWeightCard)).isDisplayed();
    }
   
    public boolean weightValuesCenterAligned() {
    	WebElement weightCard = driver.findElement(threecards);
    	List<WebElement> cards  = weightCard.findElements(By.cssSelector(".flex items-center space-x-3"));
        
        for (WebElement card : cards) {
        	String alignItems = card.getCssValue("align-items");
            if (!alignItems.equals("center")) {
                return false;
            }
        }
        return true;
    }
    
    public boolean getStartingWeightValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(startingWeightValue)).isDisplayed();
    }
     public boolean isCurrentWeightValueDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentWeightValue)).isDisplayed();
    }
     
    public boolean getGoalWeightValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(goalWeightValue)).isDisplayed();
    }

    public String getProgressOverviewHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(progressOverviewheader)).getText();
    }
    public String getWeightLossProgressSubText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(weightLossProgressSubText)).getText();
    }
    public String getCompletionPercentage () {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completionPercentage)).getText();
    }
    public boolean isProgressBarDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(progressBar)).isDisplayed();
    }
    public boolean isWeightLostDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(weightLost)).isDisplayed();
    }
    public boolean isRemainingWeightDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(remainingWeight)).isDisplayed();
    }
    public String getGraphHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(graphHeader)).getText();
    }
    public List<String> getXAxisDays() {
        List<WebElement> days = driver.findElements(xAxis);
        return days.stream()
                     .map(WebElement::getText)
                     .map(String::trim)
                     .collect(Collectors.toList());
    }

    public List<String> getYAxisWeightKgs() {
        List<WebElement> labels = driver.findElements(yAxis);
        return labels.stream()
                  .map(WebElement::getText)
                  .filter(text -> !text.isEmpty())
                  .collect(Collectors.toList());
 }
    public boolean isGoalWeightDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(goalWeight)).isDisplayed();
    }
    public boolean isLogTodaysWeightDisplayed(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logTodaysWeight)).isDisplayed();
    }
    
    public boolean isAllLogWeightLabelsDisplayed() {
    	boolean weightVisible = driver.findElement(weightInputField).isDisplayed();
        boolean bmiVisible = driver.findElement(bmiInputField).isDisplayed();
        boolean noteVisible = driver.findElement(noteValue).isDisplayed();
                
        return weightVisible && bmiVisible && noteVisible;
    }
    /*
   
    //public Section should indicate the current tracking day (e.g., "Day 7 of 7")
    public String getTrackingDayText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(progressIndicator));
        return element.getText().trim();
    }
    
*/
    public void enterWeightValue(String weight) {
        WebElement weightInput = wait.until(ExpectedConditions.visibilityOfElementLocated(weightInputField));
        weightInput.clear();
        weightInput.sendKeys(weight);
        weightInput.sendKeys(org.openqa.selenium.Keys.TAB);
    }

    public String getBMIValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(bmiInputField))
                   .getAttribute("value");
    }
    
    public boolean isBMIFieldNotEmpty() {
        String value = getBMIValue();
        return value != null && !value.isEmpty();
    }
      
    public boolean isLogWeightButtonDisabled() {
        return !driver.findElement(logWeightBtn).isEnabled();
    }
    public boolean isLogWeightButtonEnabled() {
        return driver.findElement(logWeightBtn).isEnabled();
    }
 
    
    //Weight Tracking functional verification
    
    public void enterWeight(String weight) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logWeightBtn)).sendKeys(weight);
    }

    public void clickLogWeighButtont() {
        driver.findElement(logWeightBtn).click();
    }

    public boolean isChartDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(weightTrackingChart)).isDisplayed();        
    }
    
    public boolean isErrorMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).isDisplayed();        
    }
    
    public boolean issuccessMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();        
    }    
    public String getCurrentWeightValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentWeightValue)).getText();
    }
    public String getWeightLostValue(){
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(weightLostValue));
        return element.getText();
    }
    
    public String getRemainingValue(){
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(weightLostValue));
        return element.getText();
    }
    public void setNoteValue(String note) {
    	wait.until(ExpectedConditions.visibilityOfElementLocated(noteValue)).sendKeys(note);
    }
    public String getNoteTextValue() {
    	return wait.until(ExpectedConditions.visibilityOfElementLocated(getNoteTextValue)).getText();
    }
 
    
   
}
    

