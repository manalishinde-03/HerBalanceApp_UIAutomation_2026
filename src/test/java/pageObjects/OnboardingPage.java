package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import webElementActions.ElementActions;

public class OnboardingPage {

	private WebDriver driver;
	private ElementActions util;

	public OnboardingPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementActions(this.driver);
	}

	private By onboardingPageTitle = By.xpath("//*[contains(text(),'Upload Your Recent Blood Work')]");
	private By supportingTextOnboardingStep1 = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/p");
	private By onboardingProgressBarStep1 = By.xpath("//*[@id=\"root\"]/div[1]/div[1]");
	private By progressStepText = By.xpath("//div[@class='flex justify-between text-xs text-black mb-1' and contains(., '1 of 10')]");
	private By progressStepTextStep3 = By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div[1]");
	private By progressStepTextStep4 = By
			.xpath("//div[@class='flex justify-between text-xs text-black mb-1' and contains(., '4 of 10')]");
	private By progressStepTextStep5 = By
			.xpath("//div[@class='flex justify-between text-xs text-black mb-1' and contains(., '5 of 10')]");
	private By onboardingProgressBarStep6 = By.xpath("//*[@id=\"root\"]/div[1]/div[1]");
	private By progressStepTextStep6 = By.xpath("//div[@class='flex justify-between text-xs text-black mb-1' and contains(., '6 of 10')]");
	private By progressStepTextStep7 = By
			.xpath("//div[@class='flex justify-between text-xs text-black mb-1' and contains(., '7 of 10')]");
	private By uploadPDFButton = By.xpath("//button[text()='Upload PDF']");
	private By uploadInput = By.xpath("//*[@id='root']//input[@type='file']");
	private By analyzeReportBtn = By.xpath("//button[text()='Analyze Report']");
	private By uploadPDFAnalysisProgressBar = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/div[1]/div[3]/div/div");
	private By invalidFileUploadError = By.xpath("//*[@id=\"root\"]/div[2]/ol/li/div/div");
	private By fileInput = By.id("bloodWorkUpload");

	private By step3SubTitleText = By.xpath("//p[text()='Want to manually enter any key conditions or comorbidities?']");
	private By backButton = By.xpath("//button[text()='Back']");
	private By continueButton = By.xpath("//button[text()='Continue']");
	private By radioOptions = By.xpath("//input[@type='checkbox']");
	private By radioOptionsText = By.xpath("//div/label");
	private By noteStep3Text = By.xpath("//p[@class='text-sm']");

	private By PDFFilesOnlyText = By.xpath("//p[text()='PDF files only (Max 10MB)']");
	private By skipForNowText = By.xpath("//h3[text()='Skip for Now']");
	private By skipExplanationText = By.xpath(
			"//p[text()='You can continue without uploading lab results and add this information later for more personalized recommendations.']");
	private By continueWithoutReportBtn = By.xpath("//button[text()='Continue Without Report']");
	private By dataSecurityText = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/div[2]/div[2]");

	private By OnboardingPageStep3Title = By.xpath("//h2[text()='Health Conditions']");
	private By OnboardingPageStep4SubTitle = By.xpath("//p[@class='text-black mb-6']");
	private By OnboardingPageStep4Title = By.xpath("//h2[text()='Personal Details']");
	private By onboardingPageStep5Title = By.xpath("//h2[text()='Menstrual Cycle Awareness']");
	private By OnboardingPageStep6Title = By.xpath("//h2[text()='Last Period Date']");
	private By OnboardingPageStep7Title = By.xpath("//h2[text()='Current Weight and Height']");

	private By inputBoxes = By.xpath("//input");
	private By inputFieldLabelsStep4 = By.xpath("//label");
	private By helperTextsInputFieldStep4 = By.xpath("//p[@class='text-xs text-black mt-1']");
	private By radioButtonsStep4 = By
			.xpath("//div[@class='flex items-center p-3 border border-[#D8BFD8] rounded-lg cursor-pointer']");
	private By radioOptionsStep4 = By
			.xpath("//div[@class='flex items-center p-3 border border-[#D8BFD8] rounded-lg cursor-pointer']/span");

	private By firstNameField = By.xpath("//input[@id='name']");
	private By ageField = By.xpath("//input[@id='age']");
	private By bpStatusOptions = By.xpath("//div/label");
	private By firstNameErrorMsg = By.xpath("//div/label");
	private By invalidAgeErrorMsg = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/form/div[1]/div[2]/p[1]");
	private By emptyFirstNameErrorMsg = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/form/div[1]/div[1]/p[1]");
	private By emptyAgeErrorMsg = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/form/div[1]/div[2]/p[1]");
	private By emptyBPStatusErrorMsg = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/form/div[1]/div[3]/p[1]");
	private By emptyLastPeriodDateErrorMsg = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/div[1]/div[1]/p");
	

	private By OnboardingPageStep5SubTitle = By.xpath("//p[@class='text-black mb-6']");
	private By Step5QuestionText = By.xpath("//div/h3");
	private By radioButtonsStep5 = By.xpath("//input[@type='radio']");
	private By cycleInfoOptions = By.xpath("//div/label");
	private By OnboardingPageStep6SubTitle = By.xpath("//p[@class='text-black mb-6']");
	private By Step6QuestionText = By.xpath("//h3");
	private By Step6CalendarIcon = By.xpath("//input[@type='date']");
	private By Step6Calendar_dateField = By.xpath("//div[@class='mb-6']/input[@type='date']");
	private By Step6LabelDateField = By.xpath("//div/label[@for='cycle-length']");
	private By Step6CycleLengthSlider = By.xpath("//div/input[@id='cycle-length']");
	private By Step6DefaultCycleLengthSlider = By.xpath("//div/span[text()='28']");
	private By Step6InformationText = By.xpath("//*[@id=\"root\"]/div[1]/div[2]/div/div/div[1]/div[2]/p");
	private By cyclePhaseMessage = By.xpath("//h4[contains(text(),'Based on your last period')]");

	public String getPageTitle() {

		return driver.getTitle();
	}

	public String getOnboardingPageTitle() {

		return util.getElementText(onboardingPageTitle);
	}

	public String getOnboardingPageStep3SubTitle() {

		return util.getElementText(step3SubTitleText);
	}

	public String getSupportingTextStep1() {

		return util.getElementText(supportingTextOnboardingStep1);
	}

	public boolean isOnboardingProgressBarDisplayed() {

		return util.isElementDisplayed(onboardingProgressBarStep1);

	}
	public boolean isOnboardingProgressBarStep6Displayed() {

		return util.isElementDisplayed(onboardingProgressBarStep6);

	}

	public boolean isOnboardingProgressBar1of10Displayed() {

		return util.isElementDisplayed(progressStepText);

	}

	public boolean isOnboardingProgressBar3of10Displayed() {

		return util.isElementDisplayed(progressStepTextStep3);

	}

	public boolean isOnboardingProgressBar5of10Displayed() {

		return util.isElementDisplayed(progressStepTextStep5);

	}

	public boolean isUploadPDFButtonDisplayed() {

		return util.isElementDisplayed(uploadPDFButton);
	}

	public boolean isUploadPDFButtonEnabled() {

		return util.isElementEnabled(uploadPDFButton);
	}

	public boolean isPDFFilesOnlyTextDisplayed() {

		return util.isElementDisplayed(PDFFilesOnlyText);
	}

	public boolean isSkipForNowTextDisplayed() {

		return util.isElementDisplayed(skipForNowText);
	}

	public boolean isExplanationTextDisplayed() {

		return util.isElementDisplayed(skipExplanationText);
	}

	public String getSkipExplanationText() {
		return util.getElementText(skipExplanationText);
	}

	public boolean isContinueWithoutReportBtnDisplayed() {

		return util.isElementDisplayed(continueWithoutReportBtn);
	}

	public boolean isContinueWithoutReportBtnEnabled() {

		return util.isElementEnabled(continueWithoutReportBtn);
	}

	public boolean isDataSecurityTextDisplayed() {

		return util.isElementDisplayed(dataSecurityText);
	}

	public String getPDFFilesOnlyTextStep1() {

		return util.getElementText(PDFFilesOnlyText);
	}

	public String getDataSecurityTextStep1() {

		return util.getElementText(dataSecurityText);
	}

	public void clickOnContinueWithoutReportBtn() {
		util.doClick(continueWithoutReportBtn);

	}

	public String getOnboardingPageStep3Title() {

		return util.getElementText(OnboardingPageStep3Title);
	}

	public void clickOnUploadPDFBtn() {
		util.doClick(uploadPDFButton);

	}

	public void uploadBloodReport(String fileName) {

		String filePath = System.getProperty("user.dir") + "/src/test/resources/testData/" + fileName;

		By uploadInput = By.cssSelector("input[type='file']");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(uploadInput));

		((JavascriptExecutor) driver).executeScript("arguments[0]", input);
		input.sendKeys(filePath);

	}

	public boolean fileUploadDialogIsDisplayed() {

		return util.isElementDisplayed(fileInput);

	}

	public int radioButtonsSize() {

		List<WebElement> radioButtons = util.getElements(radioOptions);

		return radioButtons.size();

	}

	public int inputBoxCountStep4() {

		List<WebElement> inputBox = util.getElements(inputBoxes);

		return inputBox.size();

	}

	public boolean isBackButtonStep3Displayed() {

		return util.isElementDisplayed(backButton);
	}

	public boolean isBackButtonStep3Enabled() {

		return util.isElementEnabled(backButton);
	}

	public boolean isContinueButtonStep3Displayed() {

		return util.isElementDisplayed(continueButton);
	}

	public boolean isContinueButtonStep3Enabled() {

		return util.isElementEnabled(continueButton);
	}

	public Collection<String> radioButtonsOptionsText() {

		List<WebElement> texts = util.getElements(radioOptionsText);
		List<String> actualOptions = texts.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Actual options -" + actualOptions.toString());
		return actualOptions;
	}

	public String getNoteTextStep3() {

		String text = util.getElementText(noteStep3Text);
		return text;
	}

	public void selectHealthConditionAndClickOnContinue(String condition) {
		WebElement healthConditionRadioButton = driver.findElement(
				By.xpath("//label[normalize-space()='" + condition + "']/preceding-sibling::input[@type='checkbox']"));
		util.doClick(healthConditionRadioButton);

		util.doClick(continueButton);

	}
	
	public void selectCycleInfoAndClickOnContinue(String cycleInfo) {
		WebElement cycleInfoRadioButton = driver.findElement(
				By.xpath("//label[normalize-space()='"+cycleInfo+"']/ancestor::div[input[@type='radio']][1]//input[@type='radio']"));
		util.doClick(cycleInfoRadioButton);

		util.doClick(continueButton);

	}

	public String getOnboardingPageStep4Title() {

		return util.getElementText(OnboardingPageStep4Title);
	}

	public String getOnboardingPageStep4SubTitle() {

		return util.getElementText(OnboardingPageStep4SubTitle);
	}

	public List<String> getActualInputFieldLabels() {

		List<WebElement> actualLabelElements = util.getElements(inputFieldLabelsStep4);

		List<String> actualLabels = new ArrayList<>();
		for (WebElement label : actualLabelElements) {
			actualLabels.add(label.getText().trim());
		}
		System.out.println("Input Field labels --" + actualLabels.toString());
		return actualLabels;
	}

	public List<String> getActualHelperTexts() {

		List<WebElement> actualHelperTextElements = util.getElements(helperTextsInputFieldStep4);
		List<String> actualHelperTexts = new ArrayList<>();
		for (WebElement label : actualHelperTextElements) {
			actualHelperTexts.add(label.getText().trim());
		}
		System.out.println("Actual helper texts --" + actualHelperTexts.toString());
		return actualHelperTexts;
	}

	public int radioButtonsStep4Size() {

		List<WebElement> radioButtonsOnStep4 = util.getElements(radioButtonsStep4);

		return radioButtonsOnStep4.size();

	}

	public Collection<String> getActualBPDescriptionOptions() {

		List<WebElement> texts = util.getElements(radioOptionsStep4);
		List<String> actualOptions = texts.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Actual options -" + actualOptions.toString());
		return actualOptions;

	}

	public boolean isOnboardingProgressBar4of10Displayed() {

		return util.isElementDisplayed(progressStepTextStep4);

	}

	public void clickOnBackBtnStep4() {
		util.doClick(backButton);
	}

	public void clickOnContinueBtnStep4() {
		util.doClick(continueButton);

	}

	public String getOnboardingPageStep5Title() {

		return util.getElementText(onboardingPageStep5Title);
	}

	public void enterPersonalDetailsStep4(String name, String age) {

		util.doSendKeys(firstNameField, name);

		util.doSendKeys(ageField, age);

	}

	public void selectBloodPressureStatus(String BPStatus) {

		List<WebElement> bpStatusRadioButtons = util.getElements(radioOptionsStep4);
		for (WebElement option : bpStatusRadioButtons) {
			if (option.getText().trim().equalsIgnoreCase(BPStatus)) {
				option.click();
				break;
			}
		}
	}

	public Collection<String> getActualRadioOptionsMenstrualCycleAwareness() {

		List<WebElement> texts = util.getElements(cycleInfoOptions);
		List<String> actualOptions = texts.stream().map(e -> e.getText().trim()).collect(Collectors.toList());

		System.out.println("Actual options -" + actualOptions.toString());
		return actualOptions;

	}

	public int radioButtonsStep5Size() {

		List<WebElement> radioButtonsOnStep5 = util.getElements(radioButtonsStep5);

		return radioButtonsOnStep5.size();

	}

	public String getOnboardingPageStep5SubTitle() {

		return util.getElementText(OnboardingPageStep5SubTitle);
	}

	public String getOnboardingPageStep5QuestionText() {

		return util.getElementText(Step5QuestionText);
	}

	public void selectCycleInfoOption(String cycleInfo) {

		List<WebElement> cycleInfoRadioButtons = util.getElements(cycleInfoOptions);
		for (WebElement option : cycleInfoRadioButtons) {
			if (option.getText().trim().equalsIgnoreCase(cycleInfo)) {
				option.click();
				break;
			}
		}
	}

	public String getOnboardingPageStep6Title() {

		return util.getElementText(OnboardingPageStep6Title);
	}

	public String getOnboardingPageStep6SubTitle() {

		return util.getElementText(OnboardingPageStep6SubTitle);
	}

	public String getOnboardingPageStep6QuestionText() {

		return util.getElementText(Step6QuestionText);
	}

	public boolean isCalendarIconStep6Displayed() {

		return util.isElementDisplayed(Step6CalendarIcon);
	}

	public boolean verifyDateFieldPlaceholderText() {

		String type = util.getAttributeVal(Step6Calendar_dateField, "type");

		return type.equals("date");
	}

	public String getDefaultCycleLength() {

		return util.getElementText(Step6DefaultCycleLengthSlider);
	}

	public boolean isCycleLengthLabelDisplayed() {

		return util.isElementDisplayed(Step6LabelDateField);
	}

	public boolean isCycleLengthSliderDisplayed() {

		return util.isElementDisplayed(Step6CycleLengthSlider);
	}

	public String getInfoTextStep6() {

		return util.getElementText(Step6InformationText);
	}

	public boolean isOnboardingProgressBar6of10Displayed() {

		return util.isElementDisplayed(progressStepTextStep6);

	}

	public boolean isUploadFilePercentageProgressBarDisplayed() {
		return util.isElementDisplayed(uploadPDFAnalysisProgressBar);
	}

	public void clickOnAnalyzeReportBtn() {

		util.doClick(analyzeReportBtn);

	}

	public String getInvalidFileUploadErrorText() {

		return util.getElementText(invalidFileUploadError);
	}

	public String getinvalidFirstNameErrorMessage() {

		return util.getElementText(firstNameErrorMsg);
	}

	public String getInvalidAgeErrorMessage() {

		return util.getElementText(invalidAgeErrorMsg);
	}

	public String getInvalidAgeWithLetterErrorMessage() {
		WebElement activeElement = driver.switchTo().activeElement();
		String messageStr = activeElement.getAttribute("validationMessage");
		return messageStr;
	}

	public String getEmptyFirstNameErrorMessage() {

		return util.getElementText(emptyFirstNameErrorMsg);
	}

	public String getEmptyAgeErrorMessage() {

		return util.getElementText(emptyAgeErrorMsg);
	}

	public String getinvalidBPStatusErrorMessage() {

		return util.getElementText(emptyBPStatusErrorMsg);
	}
	
	public String getEmptyLastPeriodDateErrorMessage() {

		return util.getElementText(emptyLastPeriodDateErrorMsg);
	}
	
	public String getCyclePhaseMessage() {
	    return util.getElementText(cyclePhaseMessage).trim();
	}
	
	public boolean verifyMessageForSelectedDate(String LastMenstrualDate) {
		
		WebElement dateInput = driver.findElement(Step6Calendar_dateField);
		dateInput.sendKeys(LastMenstrualDate);
		
		String selectedDate = dateInput.getAttribute("value");
	    LocalDate date =
		        LocalDate.parse(selectedDate);
		
		String expectedPhase = calculatePhase(date);
		System.out.println("Expected Phase -->>"+expectedPhase);
		

		String formattedDate =
		        date.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
		
		System.out.println("Formatted Date -->>"+formattedDate);
		
		String expectedMessage =
		        "Based on your last period starting on " +
		        formattedDate +
		        ", you're currently in the " +
		        expectedPhase +
		        ".";
		
		String actualMessage = util.getElementText(cyclePhaseMessage).trim();
		
		Assert.assertTrue(actualMessage.contains(formattedDate));
		Assert.assertTrue(actualMessage.contains(expectedPhase));
		Assert.assertTrue(actualMessage.startsWith("Based on your last period starting on"));
		
		Assert.assertEquals(actualMessage, expectedMessage,
		        "Cycle phase message mismatch");
		return false;
	}

	public void selectLastMenstrualDate(String LastMenstrualDate) {
		
		WebElement dateInput = driver.findElement(Step6Calendar_dateField);
		dateInput.sendKeys(LastMenstrualDate);
		
		String selectedDate = dateInput.getAttribute("value");
		System.out.println("Selected Date :"+selectedDate);
		
	}
	
	public static String calculatePhase(LocalDate lastPeriodDate) {

	    long daysSince = ChronoUnit.DAYS.between(lastPeriodDate, LocalDate.now()) + 1;
	    int cycleDay = (int) (daysSince % 28);
	    if (cycleDay == 0) cycleDay = 28;

	    if (cycleDay <= 5) {
	        return "Menstrual Phase";
	    } else if (cycleDay <= 13) {
	        return "Follicular Phase";
	    } else if (cycleDay == 14) {
	        return "Ovulation Phase";
	    } else {
	        return "Luteal Phase";
	    }
	}
	
	public String getOnboardingPageStep7Title() {

		return util.getElementText(OnboardingPageStep7Title);
	}

	public boolean isOnboardingProgressBar7of10Displayed() {
		
		return util.isElementDisplayed(progressStepTextStep7);
	}

}
