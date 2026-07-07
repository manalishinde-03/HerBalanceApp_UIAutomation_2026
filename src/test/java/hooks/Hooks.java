package hooks;

import java.io.ByteArrayInputStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import testRunner.Runner;
import utilities.ConfigReader;
import utilities.LoggerFactory;

public class Hooks {

	private ConfigReader configReader;
	private String browser;
	private WebDriver driver;

	@Before
	public void beforeScenario() {
		configReader = new ConfigReader();
		configReader.loadProperties();
		browser = Runner.threadLocalBrowser.get();
		if (browser == null) {
			browser = ConfigReader.getBrowserType();
		}
		LoggerFactory.info("Launching browser: " + browser);
		DriverFactory.initBrowser(browser);
		driver = DriverFactory.getDriver();
		String appUrl = ConfigReader.getAppUrl();
		driver.manage().deleteAllCookies();
		driver.get(appUrl);

	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Screenshot on Failure: " + scenario.getName());
				Allure.addAttachment(scenario.getName(), new ByteArrayInputStream(screenshot));
			} catch (Exception e) {
				System.err.println("Failed to take screenshot: " + e.getMessage());
			}
		}
		if (DriverFactory.getDriver() != null) {
			DriverFactory.getDriver().quit();
			LoggerFactory.info("Browser closed");
		}
	}

}
