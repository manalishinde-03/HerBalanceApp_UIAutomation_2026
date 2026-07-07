package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.TestState;
import webElementActions.ElementActions;

public class MenstrualPhaselogPage {


    private WebDriver driver;
    private WebDriverWait wait;
    private ElementActions util;

    // --- Locators ---
    private By emailIdTxt = By.xpath("//input[@type='email']");
    private By password = By.xpath("//input[@name='password']");
    private By loginBtn = By.xpath("//*[contains(text(),'LogIn')]");
    private By sectionHeading = By.xpath("//h3[text()='Current Cycle Status']");
    private By phaseLabels = By.xpath("//div[contains(@class, 'flex justify-between mt-2 text-sm')]//div");
    private By cycleProgressText = By.xpath("//div[contains(normalize-space(), 'of your') and contains(normalize-space(), 'day cycle')]");
    private By currentPhaseBadge = By.xpath("//div[contains(text(), 'Current Phase')]/following-sibling::div");
    private By luthalPhaseBadge = By.xpath("//span[contains(@class, 'text-[#6A5ACD]') and contains(text(), 'Phase')]");
    private By phaseDetailsHeading = By.xpath("//div[contains(@class, 'bg-gray-50')]/div[1]");
    private By phaseDescriptionText = By.xpath("//div[contains(@class, 'bg-gray-50')]//p");
    
    // Upcoming Phases Locators
    private By headingLocator = By.xpath("//h3[text()='Upcoming Phases']");
    private By subtextLocator = By.xpath("//*[contains(text(),'Upcoming Phases')]/following::p[1]");
    private By phaseCardsLocator = By.xpath("//div[@class='space-y-4']/div[//span[contains(text(),'Phase')]]");
    private By phaseHeadingsLocator = By.cssSelector(".phase-card h4");
    private By activePhaseLocator = By.cssSelector(".space-y-4 span.font-medium");
    private By phaseDateLocator = By.className("phase-date");

    public MenstrualPhaselogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.util = new ElementActions(driver);
    }

    // --- Authentication ---
    public MenstrualPhaselogPage loginWithValidCredentials(String email, String pwd) {
        util.doSendKeys(this.emailIdTxt, email);
        util.doSendKeys(this.password, pwd);
        util.doClick(loginBtn);
        wait.until(ExpectedConditions.urlContains("dashboard"));
        return this;
    }

    // --- Navigation & Helper ---
    public void safeClick(By locator) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            } catch (ElementClickInterceptedException e) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                attempts++;
            }
        }
    }

    public void navigateToMenstrualLogs() {
        safeClick(By.xpath("//span[contains(text(), 'Activity Insights')]"));
        safeClick(By.xpath("//div[contains(text(), 'Menstrual Phase Logs')]"));
    }

    // --- Dynamic Visibility Check (Matches your DataTable Logic) ---
    public boolean isElementVisible(String elementName) {
        By locator;
        switch (elementName) {
            case "Current Cycle Status":
            case "Upcoming Phases":
            case "Recommended Activities":
            case "Nutrition Tips":
            case "Next Period":
            case "Cycle Overview":
            case "Menstrual Calendar":
            case "Period History":
                locator = By.xpath("//*[self::h1 or self::h2 or self::h3 or self::button[@role='tab']][contains(normalize-space(), '" + elementName + "')]");
                break;
            case "Update Cycle Information":
            case "Back to Dashboard":
            case "Add Period Log":
                locator = By.xpath("//*[contains(@class, 'button') or self::span or self::a][contains(normalize-space(), '" + elementName + "')]");
                break;
            case "Cycle Overview Tab":
            case "Menstrual Calendar Tab":
            case "Period History Tab":
                String cleanName = elementName.replace(" Tab", "").trim();
                locator = By.xpath("//button[@role='tab'][contains(normalize-space(), '" + cleanName + "')]");
                break;
            default:
                locator = By.xpath("//*[contains(normalize-space(), '" + elementName + "')]");
                break;
        }
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

//     --- Data Extraction Methods ---
    public String getComponentValue(String component) {
    	
        switch (component) {
        case "Cycle Progress Text":
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(cycleProgressText));
            
            // 2. Scroll it into view (in case it's below the fold)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
             wait.until(d -> element.getText().length() > 0);
             case "Section Heading":
                return wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeading)).getText();
            
            case "Phase Labels":
                List<WebElement> labels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(phaseLabels));
                return labels.stream().map(WebElement::getText).filter(t -> !t.trim().isEmpty()).collect(Collectors.joining(", "));
            case "Last period started":
            case "Next period expected":
                By dateLocator = By.xpath("//div[text()='" + component + "']/following-sibling::div");
                return wait.until(ExpectedConditions.visibilityOfElementLocated(dateLocator)).getText();
            default:
                return "Unknown Component";
        }
    }

    public String getPhaseName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(currentPhaseBadge)).getText().replace("Phase", "").trim();
    }

    public String getPhaseDescription() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phaseDescriptionText)).getText();
    }

    // --- Validation Methods ---
    public boolean verifyDatesAgainstSourceData(LocalDate lmpDate, int cycleLength) {
        if (lmpDate == null) lmpDate = LocalDate.now().minusDays(5);
        Map<String, LocalDate> expectedPhases = new HashMap<>();
        expectedPhases.put("Menstrual Phase", lmpDate);
        expectedPhases.put("Follicular Phase", lmpDate.plusDays(5));
        expectedPhases.put("Ovulation Phase", lmpDate.plusDays(13));
        expectedPhases.put("Luteal Phase", lmpDate.plusDays(16));

        List<WebElement> phaseCards = driver.findElements(phaseCardsLocator);
        List<WebElement> headings = driver.findElements(phaseHeadingsLocator);

        for (int i = 0; i < headings.size(); i++) {
            String phaseName = headings.get(i).getText().trim();
            String actualUiText = phaseCards.get(i).getText();
            LocalDate expectedDate = expectedPhases.get(phaseName);
            if (expectedDate != null) {
                String expectedStr = expectedDate.format(DateTimeFormatter.ofPattern("MMM dd", Locale.ENGLISH));
                if (!actualUiText.contains(expectedStr)) return false;
            }
        }
        return true;
    }

    private boolean isValidFormat(String value, String format) {
        try {
            DateTimeFormatter.ofPattern(format + " yyyy", Locale.ENGLISH);
            LocalDate.parse(value + " " + LocalDate.now().getYear(), DateTimeFormatter.ofPattern(format + " yyyy", Locale.ENGLISH));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
        
        
    }
    public List<String> getPhaseLabelTexts() {
		List<WebElement> elements = driver.findElements(phaseLabels);
		List<String> texts = new ArrayList<>();
		for (WebElement e : elements) {
			texts.add(e.getText().trim());
		}
		return texts;
    }

	    public boolean areInfoLabelsVisible(String l1, String l2, String l3) {
	        String pageSource = driver.getPageSource();
	        return pageSource.contains(l1) && pageSource.contains(l2) && pageSource.contains(l3);
	    }
	    public String getCurrentPhaseName() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
			String fullText = wait.until(ExpectedConditions.visibilityOfElementLocated(currentPhaseBadge)).getText();
			
			return fullText.replace("Phase", "").trim();
		}
	    public String getHeadingText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(headingLocator))
	                   .getText().trim();
	    }
	    public String getSubtext() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(subtextLocator))
	                   .getText().trim();
	    }
	 

	    public int getSubsectionCount() {
	        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(phaseCardsLocator));
	        return driver.findElements(phaseCardsLocator).size();
	    }

	    public List<String> getAllSubsectionHeadings() {
	     
	        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(phaseHeadingsLocator));
	        
	        return elements.stream()
	                .map(WebElement::getText)
	                .map(String::trim)
	                .filter(text -> !text.isEmpty()) // Ensure we don't grab empty spans
	                .collect(Collectors.toList());
	    }
	    public boolean isCurrentPhaseHighlighted() {
	        try {
	            return wait.until(ExpectedConditions.visibilityOfElementLocated(activePhaseLocator)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	    public String getSubsectionHeading() {
			WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(phaseDetailsHeading));
			return heading.getText();
		}
	    public String getLuthalPhaseName() {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			String fullText = wait.until(ExpectedConditions.visibilityOfElementLocated(luthalPhaseBadge)).getText();
			return fullText.replace("Phase", "").trim();
		}

	    // --- Updated Action Methods ---

	   

	    public boolean validateUpcomingDateFormat(String expectedFormat) {
	        // prefix = "Starts", pattern = "MMM dd"
	        String prefix = expectedFormat.split(" ")[0]; 
	        List<WebElement> phaseCards = driver.findElements(phaseCardsLocator);

	        for (WebElement phase : phaseCards) {
	            String fullText = phase.getText();
	            if (fullText.contains(prefix)) {
	                String datePart = fullText.replace(prefix, "").trim();
	                if (!isValidFormat(datePart, "MMM dd")) return false;
	            }
	        }
	        return true;
	    }

	    public boolean validateCurrentPhaseDateFormat(String expectedFormat) {
	        try {
	            WebElement activeCard = wait.until(ExpectedConditions.visibilityOfElementLocated(activePhaseLocator));
	            String actualText = activeCard.findElement(phaseDateLocator).getText().trim();
	            
	            String prefix = expectedFormat.split(" ")[0]; // "Started"
	            String datePattern = expectedFormat.replace(prefix, "").trim(); // "MMM dd"

	            if (!actualText.startsWith(prefix)) return false;

	            String datePart = actualText.replace(prefix, "").trim();
	            return isValidFormat(datePart, datePattern);
	        } catch (Exception e) {
	            return false;
	        }
	    }
//	    upcoming Phases
	    private By upcomingPhasesContainer = By.xpath("//div[contains(@class, 'p-6') and .//h3[text()='Upcoming Phases']]");

	 public String getUpcomingPhases(String component) {
		    switch (component) {
		        case "Upcoming Phases":
		            // Targets the h3 with the exact classes and text from your snippet
		            By locator = By.xpath("//h3[contains(@class, 'tracking-tight') and text()='Upcoming Phases']");
		            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();

		       
		        case "Plan ahead with your cycle phases":
		            return wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//p[contains(@class, 'text-gray-500') and contains(text(), 'Plan ahead with your cycle phases')]")))
		                .getText().trim(); 
		        case "Section Heading":
		             return wait.until(ExpectedConditions.visibilityOfElementLocated(sectionHeading)).getText().trim();
		        default:
		            return "Unknown Component: " + component; 
		    }
		    
		
		}
	 public List<String> getAllPhaseLabels() {
		   
		    By phaseTitlesLocator = By.xpath("//div[contains(@class, 'p-3')]//span[contains(@class, 'font-medium')]");
		  
		    List<WebElement> phaseElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(phaseTitlesLocator));
		   
		    return phaseElements.stream()
		            .map(WebElement::getText)
		            .map(String::trim)
		            .collect(Collectors.toList());
		}
	   
	 
	 public boolean areAllPhaseDatesVisible() {
		
		    By dateLabels = By.xpath("//div[contains(@class, 'text-sm') and contains(@class, 'text-gray-500')]/span");
		    
		    List<WebElement> dates = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateLabels));
		    
		    for (WebElement date : dates) {
		        String text = date.getText();
		        if (text.isEmpty() || (!text.contains("Starts") && !text.contains("Started"))) {
		            return false;
		        }
		    }
		    return dates.size() >= 4; // Ensure at least the 4 main phases have dates
		}
	 public boolean verifyAllDatesMatchFormat(String expectedFormat) {
		    // Locator for the date spans: e.g., "Starts Feb 8"
		    By dateLocators = By.xpath("//div[contains(@class, 'text-sm') and contains(@class, 'text-gray-500')]/span");
		    List<WebElement> dateElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(dateLocators));

		    // Regex explanation: (Starts|Started) [Jan-Dec] [1-31]
		    // This matches: "Starts Feb 8" or "Started Jan 27"
		    String dateRegex = "(Starts|Started) (Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec) \\d{1,2}";

		    for (WebElement element : dateElements) {
		        String text = element.getText().trim();
		        if (!text.isEmpty() && !text.matches(dateRegex)) {
		            System.out.println("Format Mismatch Found: " + text);
		            return false;
		        }
		    }
		    return true;
		}
	 
	
		public String getHighlightedPhaseName() {
		    
		    By activeCardHeader = By.xpath("//div[contains(@class, 'bg-[#F5F0FF]')]//span[contains(@class, 'font-medium')]");
		    
		    try {
		        WebElement activeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activeCardHeader));
		        return activeElement.getText().trim();
		    } catch (Exception e) {
		        return "None"; // No phase is currently highlighted
		    }
		}
		

	    private By tipsContainer = By.className("space-y-1.5");


	    public String getTipsContent() {
	        return driver.findElement(tipsContainer).getText();
	    }
	    private By headerTitle = By.cssSelector("h3.text-2xl.font-semibold");
	    private By activitiesList = By.cssSelector(".space-y-2"); // Adjust based on your UI

	  

	    public String getHeaderText() {
	        return driver.findElement(headerTitle).getText();
	    }

	    public String getActivitiesContent() {
	        return driver.findElement(activitiesList).getText();
	    }

	    public String getPhaseSpecificContent(String component) {
	        switch (component) {
	            case "Nutrition Tips":
	            case "Recommended Activities":
	                
	                By locator = By.xpath("//h3[text()='" + component + "']/parent::div/following-sibling::div");
	                
	                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
	            
	            default:
	                return getUpcomingPhases(component); // Fallback to your existing logic
	        }
	    }
	        
	 
	    public String detectCurrentPhaseFromUI() {
	        // 1. Get the full heading text (e.g., "Follicular Phase Details")
	        String headingText = getSubsectionHeadingText(); 
	        
	        // 2. Extract the phase name
	        // We remove " Phase Details" and " Details" just in case the word 'Phase' is missing
	        String detectedPhase = headingText
	            .replace("Phase Details", "")
	            .replace("Details", "")
	            .trim();
	        
	        // 3. Save to TestState for use in your @Then steps
	        TestState.setCurrentPhase(detectedPhase);
	        
	        // Log it so you can see it in your console if the test fails later
	        System.out.println("Detected Phase from UI: " + detectedPhase);
	        
	        return detectedPhase;
	    }
	 // Locators section
	    private By subsectionHeading = By.cssSelector("div.text-lg.font-medium.mb-4");

	    // Method section
	    public String getSubsectionHeadingText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(subsectionHeading))
	                   .getText()
	                   .trim();
	    }
	    
	 // Locator for the Date (February 28, 2026)
	    private By nextPeriodDate = By.cssSelector("div.text-\\[\\#FFC0CB\\].font-medium");

	    // Locator for the countdown (21 days remaining)
	    private By daysRemaining = By.cssSelector("div.text-sm.text-gray-600");

	    public String getNextPeriodDateText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(nextPeriodDate)).getText().trim();
	    }

	    public String getDaysRemainingText() {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(daysRemaining)).getText().trim();
	    }
}