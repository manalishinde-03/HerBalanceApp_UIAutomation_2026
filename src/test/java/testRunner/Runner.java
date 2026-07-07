package testRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"hooks", "stepDefinitions"},
    tags ="@TC1",
        plugin = {
        "pretty",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
    },
    monochrome = true,
    dryRun = false
)

public class Runner extends AbstractTestNGCucumberTests {

    public static ThreadLocal<String> threadLocalBrowser = new ThreadLocal<>();

    @BeforeClass
    @Parameters({"browserType"})
    public void defineBrowser(@Optional("chrome") String browser) {
        threadLocalBrowser.set(browser);
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
