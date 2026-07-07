package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import webElementActions.ElementActions;

public class DashboardPage {

	private static final Logger logger =
            LoggerFactory.getLogger(DashboardPage.class);
	
	private By dashboardTitle = By.xpath("//h1[text()='Free Plan Dashboard']");
	private By profileName = By.xpath("//span[contains(@class,'text-[#6A5ACD] group-hover:text-[#9370DB] font-medium')]");

	private By profileIcon = By.xpath("//button[@aria-haspopup='menu']");
	
	private By profileDropdown = By.xpath("//div[@role='menuitem']");
	private By bellIcon = By.xpath("//button[contains(@class,'relative p-2 rounded-full bg-white')]");
	private By searchBar = By.xpath("//input[@type='text' and contains(@class,'rounded-full')]");

	private By MainText = By.xpath("//p[text()='Free Plan • Access to Basic Features']");
	private By SubText = By.xpath("//p[contains(text(),'Enjoy our free plan')]");
	
	private By MetricSecDisplay = By.xpath("//span[contains(@class,'text-xs text-gray-500')]");
	private By BMIReference = By.xpath("//h3[text()='BMI Reference Guide']");
	private By BodyMetricHeading = By.xpath("//span[text()='Weight & Body Metrics']");
	private By HealthConditionHeading = By.xpath("//span[text()='Health Conditions']");
	private By BloodReportHeading = By.xpath("//h3[text()='Blood Report Insights']");
	private By MenustrualHeading = By.xpath("//h3[text()='Menstrual Cycle Insights']");
	private By SubcriptionHeading = By.xpath("//span[text()='Subscription Information']");
	private By ExpectedWeight = By.xpath("//span[text()='Current']/ancestor::div//p");
	private By ExpectedHeight = By.xpath("//div[contains(@class,'bg-[#F5F0FF] p-3 rounded-lg')]/p/span[text()='cm']");
	
	private By StartingWeight = By.xpath("//div[contains(@class,'bg-[#F5F0FF] p-3 rounded-lg')]/p/span[text()='kg']");
	private By GoalWeight = By.xpath("//span[contains(@class,'text-sm font-medium') and contains(text(),'Goal: ')]");
	private By dailyWeight = By.xpath("//p[text()='Morning, before food']");
	private By slidebar = By.xpath("//div[contains(@class,'w-7 h-7 bg-white border-2 border-black rounded-full flex')]");
	private By BmiReference = By.xpath("//h3[text()='BMI Reference Guide']");
	private By infoTextVisibility = By.xpath("//span[text()='Info']");
	private By freePlan7dayText = By.xpath("//div[contains(@class,'text-xs text-gray-500 mt-1 italic text-center')]");
	
	private By menstrualLog = By.xpath("//a[@href='/track/menstrual-cycle']/div[contains(text(),'Menstrual Phase Logs')]");
	

	private By uploadBloodReport = By.xpath("//div[contains(@class,'flex items-center mb-1.5')]");
	private By joinedDateText = By.xpath("//p[text()='Joined Date']");
	private By joinedDate = By.xpath("//div[contains(@class,'bg-[#F5F0FF] p-3 rounded-lg')]/p[contains(@class,'text-sm font-medium')]");
	private By todayDate = By.xpath("//p[text()=\"Today's Date\"]");
	private By todayDatevalue = By.xpath("//p[text()=\"Today's Date\"]/following::p[1]");

	private By DaysLeft = By.xpath("//div[contains(@class,'bg-white border border-gray')]/div/span");
	private By freePlan = By.xpath("//div[contains(@class,'flex items-center')]/span[text()='Free Plan']");
		
	private By activityInsight = By.xpath("//button[.//span[text()='Activity Insights']]");
	private By  activityInsightDropdown = By.xpath("//div[@role='menuitem']/a");

	private By DietPlan = By.xpath("//a[@href='/diet-plan']");
	
	private By DietplanNavigationPage = By.xpath("//h3[contains(text(),'Create Your Diet Plan')]");
	private By Workout = By.xpath("//a[@href='/workouts']");
	private By WorkoutNavigationPage = By.xpath("//h2[contains(text(),'Daily Workout Plan')]");
	private By WaterTracker = By.xpath("//a[@href='/water-tracker']");
	private By  WaterTrackerNavigationPage = By.xpath("//h1[contains(text(),'Water Tracker')]");
	private By FullcycleBtn = By.xpath("//button[contains(.,'View Full Cycle Details')]");
	private By MenstruralPage = By.xpath("//h1[contains(text(),'Menstrual Cycle Tracker')]");
	private By UploadBloodReportBtn = By.xpath("//button[contains(.,'Upload Blood Report')]");
	private By UpgradePremiumBtn = By.xpath("//button[contains(.,'Upgrade to Premium')]");
	private By premiumSubscriptionPage = By.xpath("//h1[text()='Choose Your Transformation Journey']");
	private By HomepageNavigation = By.xpath("//p[contains(@class,'text-gray-600 mb-4')]");
	private By EditProfilePage = By.xpath("//h1[text(),'Edit Your Profile']");
	private By SubcriptionPage = By.xpath("//h1[text(),'Subscription Management']");
	private By Generate7Dayplan = By.xpath("//button[contains(@class,'bg-white text-green-600 px-6')]");
	private By Generate7Dayplanpage = By.xpath("//h3[text()='Create Your Diet Plan']");
	private By PremiumPlan = By.xpath("//button[contains(@class,'bg-white text-[#6A5ACD] px-6 py-2 rounded-full font')]");
	private By PremiumPlanpage = By.xpath("//h1[text()='Choose Your Transformation Journey']");
	private By logoutPage = By.xpath("//p[text()='Enter your credentials to access your account']");
	private By MenstrualPhase = By.xpath("//span[contains(@class,'font-medium') and contains( text(),'Phase')]");
	private By CycledaysLeft = By.xpath("//div[contains(@class,'text-sm flex items')]/span");
	private By Cycle = By.xpath("//div[contains(@class,'flex justify-between text-xs text-gray-500')]/span[text()='Cycle: ']");
	private By DayCount = By.xpath("//div[contains(@class,'flex justify-between text-xs text-gray-500')]/span[text()='Day ']");
	private By loginBtn = By.xpath("//button[text()='LogIn']");
	private By homeloginBtn = By.xpath(("//button[text()='Log In']"));
	WebDriver driver;
	ElementActions elementactions;
	WebDriverWait wait;
	private String appURL = "url";
	public DashboardPage(WebDriver driver) {
		
		this.driver=driver;
		this.elementactions = new ElementActions(driver);
		 this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	
	
	
	 public String getDashboardTitle() {
	     
		 String title = elementactions.getText(dashboardTitle);
		 logger.info("Title text displayed", title);
		
		 
		return title;
	    }

	    public boolean isProfileNameVisible() {
	   
	    	String profilename = elementactions.getText(profileName);
	    	
	    	 logger.info("Profilename Visible", profilename);
	    	return elementactions.isElementDisplayed(profileName);
	    	
	    }

	    public boolean isProfileIconVisible() {
	     
	    	return elementactions.isElementDisplayed(profileIcon);
	    }

	    public boolean isNotificationBellVisible() {
	        
	        return elementactions.isElementDisplayed(bellIcon);
	    }
	    
	    public boolean isSearchBarVisible() {
	      
	    	return elementactions.isElementDisplayed(searchBar);
	    }
	    public By getHeadingLocator(String heading) {

	        switch (heading) {
	            case "Activity Insights":
	                return activityInsight;
	            case "Diet Plan":
	                return DietPlan;
	            case "Workout":
	                return Workout;
	            case "Water Tracker":
	                return WaterTracker;
	            case "View Full Cycle Details":
	                return FullcycleBtn;
	            case "Upload Blood Report":
	                return UploadBloodReportBtn;
	            case "Upgrade to Premium":
	                return UpgradePremiumBtn;
	            case "Generate 7-Day Plan":
	                return Generate7Dayplan;
	            case "See Premium Plans":
	                return PremiumPlan;
	            default:
	                throw new RuntimeException("Invalid heading: " + heading);
	        }
	    }
	    public boolean isHeadingVisible(String heading) {
	        By locator = getHeadingLocator(heading);
	       
	        return elementactions.isElementDisplayed(locator);
	    }




	    public String MainText() {
	  
	       String maintext = elementactions.getText(MainText);
	       logger.info("main text", maintext);
	       return maintext;
	     
	                     
	    }

	    public String SubText() {
	    
	    	String subtext = elementactions.getText(SubText);
	    	logger.info("sub text", subtext);
		       return subtext;
	    }
	    
	    public boolean isMainTextVisible() {
	        return elementactions.isElementDisplayed(MainText);
	        		}

	    public boolean isSubTextVisible() {
	        return elementactions.isElementDisplayed(SubText);
	    }

	    
	    public boolean areBothTextsVisible() {
	        return isMainTextVisible() && isSubTextVisible();
	    }


	    
	    public boolean isSubTextCentered() {
	    	 WebElement element =  elementactions.getElement(SubText); 
	    	    String alignment = element.getCssValue("text-align");

	    	    if (alignment.equals("center"))
	    	    {
	    	        return true;
	    	    } else 
	    	    {
	    	        return false;
	    	    }


	        
	    }


	    public boolean isSectionHeadingVisible(String heading) {

	        By locator = null;

	        switch (heading) {

	            case "Weight & Body Metrics":
	                locator = BodyMetricHeading;
	                break;

	            case "Health Conditions":
	                locator = HealthConditionHeading;
	                break;

	            case "Blood Report Insights":
	                locator = SubcriptionHeading;   
	                break;

	            case "Menstrual Cycle Insights":
	                locator = MenustrualHeading;
	                break;

	            case "Subscription Information":
	                locator = BloodReportHeading;   
	                break;

	            default:
	                return false;
	        }

	        return driver.findElement(locator).isDisplayed();
	    }


	    

	    public List<String> getAllMetrics() {

	        List<String> metrics = new ArrayList<>();

	       
	        List<WebElement> gridMetrics = driver.findElements(MetricSecDisplay);
	        for (WebElement e : gridMetrics)
	        {
	            String metrictext = e.getText().trim();
	            logger.info("Metrics Text "+metrictext);
	            metrics.add(metrictext);
	        }
  
	        WebElement bmiRef = driver.findElement(BMIReference);
	        metrics.add(bmiRef.getText().trim());
	       
	        return metrics;
	    }


	    public boolean isMetricDisplayed(String expectedMetric)
	    {

	        List<String> metrics = getAllMetrics();

	        for (String m : metrics)
	        {
	            if (m.contains(expectedMetric))
	            {
	                return true;
	            }
	        }
	        return false;
	    }





	    public double getWeight()
	    {
	    	
	       WebElement weightele= wait.until(ExpectedConditions.visibilityOfElementLocated(ExpectedWeight));
	        String weighttext = weightele.getText(); 
	        logger.info("Weight Text "+weighttext);
	        return Double.parseDouble(weighttext.replaceAll("[^0-9.]", ""));
	    }
	    
	    public double getHeight()
	    {

	        WebElement heightele = wait.until(ExpectedConditions.visibilityOfElementLocated(ExpectedHeight));
	        String heighttext = heightele.getText(); 
	        logger.info("Height Text "+heighttext);
	        return Double.parseDouble(heighttext.replaceAll("[^0-9.]", ""));
	    }
	    

	    
	    public boolean isWeightVisible() {
	      
	    	return elementactions.isElementDisplayed(StartingWeight);
	    }
	    
	    public boolean isGoalWeightVisible() {
	       
	    	return elementactions.isElementDisplayed(GoalWeight);
	    	
	    }
	    
	    public String dailyWeightIn()
	    {
	    	String DailyWeightText =  elementactions.getText(dailyWeight); 
	    	 logger.info("Dailyweightin "+DailyWeightText);
	    	return DailyWeightText;
	    }

	    public boolean isBMIVisible() {
	       
	    	return elementactions.isElementDisplayed(BmiReference);
	    }

	    public boolean isBMISliderPresent() {
	        
	    	return elementactions.isElementDisplayed(slidebar);
	    }
	    public boolean isSliderNonInteractive() {
	    	  try {
	    	        return elementactions.isElementEnabled(slidebar);
	    	      
	    	    } 
	    	  catch (Exception e)
	    	  {
	    	        return false;
	    	    }
	    	}
	    public boolean isInfoLabelVisible()
	    {

	    	return elementactions.isElementDisplayed(infoTextVisibility);
	    }

	    public boolean isFreePlan7DayTextVisible() {
	     
	    	return elementactions.isElementDisplayed(freePlan7dayText);
	    }

	    
	    public String getJoinedDate() {

	        List<WebElement> labels = driver.findElements(joinedDateText);
	        List<WebElement> values = driver.findElements(joinedDate);

	        for (int i = 0; i < labels.size(); i++) {

	            String labelText = labels.get(i).getText().trim();

	            if (labelText.equalsIgnoreCase("Joined Date")) {
	            	
	                return values.get(i).getText().trim();   
	            }
	        }

	        throw new RuntimeException("Joined Date not found");
	    }

	    public String gettodayDate() 
	    {
	    	String dateValue=wait.until(ExpectedConditions.visibilityOfElementLocated(todayDatevalue)).getText().trim();
	    	logger.info("TodayDate"+dateValue);
	    	return dateValue;
	       
	    }
	    

	  
	    public boolean isUpgradeToPremiumButtonVisible() {
	      
	    	return elementactions.isElementDisplayed(UpgradePremiumBtn);
	    }

	    public boolean isUploadBloodReportButtonVisible() {
	     
	    	return elementactions.isElementDisplayed(UploadBloodReportBtn);
	    }

	    public boolean isUploadBloodReportButtonEnabled() {
	       	    	
	    	return elementactions.isElementEnabled(UploadBloodReportBtn);
	    }
	    
	    public void clickprofile()
	    {
	    	
	    	wait.until(ExpectedConditions.elementToBeClickable(profileIcon)).click();
	    	 
	    }
	    
	    public boolean isProfileOptionPresent(String expectedOption) {

	        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(profileDropdown));

	        for (WebElement option : options)
	        {

	            String actualText = option.getText().trim();
	            
	            logger.info("Profile menu option: "+actualText);
	            if (actualText.equalsIgnoreCase(expectedOption))
	            {
	                return true;
	            }
	        }
	        return false;
	    }
	    


	    public void clickActivity()
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(activityInsight)).click();
	    }

	    public boolean clickActivityInsights(String expectedOption) 
	    {
	    	
	    List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(activityInsightDropdown));

	    	for (WebElement option : options)
	    	{
	    		String optiontext = option.getText().trim();
	    		logger.info("ActivityInsight options"+optiontext);

	        if (optiontext.equalsIgnoreCase(expectedOption)) 
	        {
	            return true;
	        }
	    }
	    	return false;
	    }  	
	    	
	   public void clickDietPlan()
	   {
	    	  wait.until(ExpectedConditions.elementToBeClickable(DietPlan)).click();
	    }
	   
	    public String IsDietplanPageNavigated()
	    {
	    	WebElement DietPlan = wait.until(ExpectedConditions.visibilityOfElementLocated(DietplanNavigationPage));
	    	logger.info("DietPlanNavigation"+DietPlan.getText());
	    	return DietPlan.getText();
	    }
	    

	    
	    public void clickWorkout()
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(Workout)).click();
	    	
	    }

	    public String IsWorkoutPageNavigated()
	    {
	    	WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(WorkoutNavigationPage));
	    	logger.info("WorkoutPageNavigation"+heading.getText());
		    return heading.getText();

	    }
	    
	    public void clickWaterTracker()
	    {
	    	wait.until(ExpectedConditions.elementToBeClickable(WaterTracker)).click();
	    }
	    
	    public String IsWaterTrackerPageNavigated()
	    {
	    	WebElement watertracker= wait.until(ExpectedConditions.visibilityOfElementLocated(WaterTrackerNavigationPage));
	    	logger.info("waterTracker"+watertracker.getText());
	    	return watertracker.getText();
	    }
	    
	    public void clickFullCycleDetails()
	    {
	    	 wait.until(ExpectedConditions.elementToBeClickable(FullcycleBtn)).click();
	    }
	    
	    public String IsMenstrualPageNavigated()
	    {
	    	WebElement menstrualpage = wait.until(ExpectedConditions.visibilityOfElementLocated(MenstruralPage));
	    	logger.info("MenstrualPage Navigation"+ menstrualpage.getText());
	    	 return menstrualpage.getText();
	    	
	    }
	    
	    public void clickUploadBloodReport()
	    {
	      	elementactions.clickAction(UploadBloodReportBtn);
	    }
	   
	    public void cancelUpload() 
	    {
	       driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
	    }

	    public void clickUpgradeToPremium()
	    {
	       	elementactions.clickAction(UpgradePremiumBtn);
	    }
	    
	    public String SubscribtionPageNavigated()
	    {
	    	WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(premiumSubscriptionPage));
	    	logger.info("UpgradeToPremiunNavigation"+el.getText());
	    	return el.getText();
	    	
	    }
	   
	    public void clickGenerate7DayPlan() 
	    {
	    	elementactions.clickAction(Generate7Dayplan);
	    }
	    
	    public String clickGenerate7DayPlanPageNavigated()
	    {
	    	 WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(Generate7Dayplanpage));
	    	 logger.info("7DayPlanNavigation"+el.getText());
	    	 return el.getText().trim();
	    }
	   
	   
	    public void clickSeePremiumPlans() {
	    	elementactions.clickAction(PremiumPlan);
	  
	    }
	    public String clickSeePremiumPlansPageNavigated()
	    {
	    	 WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(PremiumPlanpage));
	    	 logger.info("PremimPage Navigation"+el.getText());
	    	 return el.getText();

	    }
	  
	   


}
