package webElementActions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {

	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		js = (JavascriptExecutor) driver;
	}

	public void clickAction(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public WebElement waitAndScroll(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement elements = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		((JavascriptExecutor) driver)
				.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'})", elements);
		return elements;
	}

	public void scrollWaitAndClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'})", element);
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

	}

	public int getElementCount(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements.size();
	}

	public void clickElementByText(By locator, String text) {
		List<WebElement> elements = driver.findElements(locator);

		for (WebElement element : elements) {
			if (element.getText().trim().equalsIgnoreCase(text.trim())) {
				element.click();
				return;
			}
		}
		throw new RuntimeException("Element with text not found: " + text);
	}

	public boolean isElementPresentByText(By locator, String text) {
		List<WebElement> elements = driver.findElements(locator);
		if (elements == null || elements.isEmpty()) {
			return false;
		}

		for (WebElement element : elements) {
			String elementText = element.getText();
			if (elementText != null && elementText.trim().equalsIgnoreCase(text.trim())) {
				return true;
			}
		}
		return false;
	}

	public boolean waitForCondition(java.util.function.Supplier<Boolean> condition, int timeoutInSeconds) {
		WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		try {
			return customWait.until(d -> condition.get());
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement waitForElementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean isElementDisplayd(By locator) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return ele.isDisplayed();
		} catch (Exception e) {
			System.out.println("Element not found or not visible: " + locator);
			return false;
		}
	}

	public boolean isElementVisibleAfterScroll(By locator) {
		try {
			List<WebElement> elements = driver.findElements(locator);
			if (!elements.isEmpty()) {
				WebElement element = elements.get(0);
				js.executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
				wait.until(ExpectedConditions.visibilityOf(element));
				return element.isDisplayed();
			}
		} catch (Exception e) {
			System.out.println("Exception while scrolling to element: " + e.getMessage());
		}
		return false;
	}

	public boolean isElementDisplayed(By locator) {
		boolean flag = false;
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(60))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if (ele.isDisplayed()) {
				flag = true;
			} else {
				throw new Exception("Element is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean isElementTextEquals(By locator, String expectedText) {
		if (expectedText == null) {
			return false;
		}
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			String actualText = element.getText().trim();
			return actualText.equalsIgnoreCase(expectedText.trim());
		} catch (Exception e) {
			return false;
		}
	}

	public WebElement elementWithFluentWaitLocated(By locator, int timeOutInSeconds, int pollingIntervalInMillis) {
		WebElement ele = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingIntervalInMillis)).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public String getElementText(By locator) {
		elementWithFluentWaitLocated(locator, 20, 100);
		return getElement(locator).getText();
	}

	public String getText(By locator) {
		try {
			WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return element.getText().trim();
		} catch (Exception e) {
			return null;
		}
	}

	public boolean isElementEnabled(By locator) {
		elementWithFluentWaitLocated(locator, 20, 100);
		return getElement(locator).isEnabled();
	}

	public void waitForUrlToContain(String partialUrl) {
		wait.until(ExpectedConditions.urlContains(partialUrl));
	}

	public void getUrlToContain(String partialUrl) {
		driver.get(partialUrl);
	}

	public void elementVisibleClick(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	public WebElement waitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
		return element;
	}

	public WebElement waitForElementWithoutScroll(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void sendKeysMethod(By locator, String text) {
		WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
		ele.clear();
		ele.sendKeys(text);
	}

	public boolean attributeChangeMethod(By locator, String attribute, String expectedType, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.attributeContains(locator, "type", expectedType));
	}

	public List<String> getTxtWhenVisible(By locator, int timeout) {
		List<String> messages = new ArrayList<>();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(d -> {
			List<WebElement> elements = d.findElements(locator);
			return !elements.isEmpty() && !elements.get(0).getText().trim().isEmpty();
		});
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		List<WebElement> elements = driver.findElements(locator);
		for (WebElement el : elements) {
			String txt = el.getText().trim();
			if (!txt.isEmpty()) {
				messages.add(txt);
			}
		}
		return messages;
	}

	public void sendKeys(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	public String javaScriptValidation(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return (String) js.executeScript("return arguments[0].validationMessage;", element);
	}

	public void doClick(By locator) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(3000))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));
			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {
					try {
						ele.click();
					} catch (Exception e) {
					}
				} else {
					throw new Exception("Element is not enabled");
				}
			} else {
				throw new Exception("Element is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doSendKeys(By locator, Keys keys) {
		WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(getElement(locator)));
		ele.sendKeys(keys);
	}

	public void doSendKeys(By locator, String text) {
		try {
			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(120))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));
			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {
					try {
						ele.click();
						ele.clear();
						ele.sendKeys(text);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					throw new Exception("Element is not enabled");
				}
			} else {
				throw new Exception("Element is not displayed");
			}
		} catch (Exception e) {
		}
	}

	public void scrollToView(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
	}

	public void waitForInvisibility(By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public int getElementCount(List<WebElement> element) {
		return element.size();
	}

	public String getCssValue(By locator, String propertyName) {
		String value = null;
		try {
			WebElement ele = driver.findElement(locator);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed()) {
				try {
					value = ele.getCssValue(propertyName);
				} catch (Exception e) {
					System.err.println("Error fetching CSS value for property: " + propertyName);
					e.printStackTrace();
				}
			} else {
				throw new Exception("Element is not displayed");
			}
		} catch (Exception e) {
			System.err.println(" Exception in getCssValue: " + e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	public void doClick(WebElement ele) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
			wait.until(ExpectedConditions.visibilityOf(ele));
			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {
					try {
						ele.click();
					} catch (Exception e) {
					}
				} else {
					throw new Exception("Element is not enabled");
				}
			} else {
				throw new Exception("Element is not displayed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getAttributeVal(By locator, String attributeName) {
		return elementWithFluentWaitLocated(locator, 10, 100).getAttribute(attributeName);
	}

	public List<WebElement> getElements(By locator) {
		elementWithFluentWaitLocated(locator, 30, 100);
		return driver.findElements(locator);
	}
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	public List<WebElement> getAllElements(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		return elements;
		
	}
	

}
