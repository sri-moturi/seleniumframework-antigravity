package com.tecskool.base;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    public void click(By locator) {
        logger.info("Clicking on element: {}", locator);
        WaitUtils.waitForClickable(locator).click();
        applyDelay();
    }

    public void type(By locator, String text) {
        logger.info("Typing '{}' in element: {}", text, locator);
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
        applyDelay();
    }

    public String getText(By locator) {
        logger.info("Getting text from element: {}", locator);
        return WaitUtils.waitForVisibility(locator).getText();
    }

    public String getAttribute(By locator, String attributeName) {
        logger.info("Getting attribute '{}' from element: {}", attributeName, locator);
        return WaitUtils.waitForVisibility(locator).getAttribute(attributeName);
    }

    public boolean isDisplayed(By locator) {
        logger.info("Checking visibility of element: {}", locator);
        try {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(0));
            java.util.List<WebElement> elements = driver.findElements(locator);
            if (elements.isEmpty()) {
                return false;
            }
            return elements.get(0).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element visibility check threw exception for: {}. Error: {}", locator, e.getMessage());
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(com.tecskool.utils.ConfigReader.getImplicitWait()));
        }
    }

    public boolean isSelected(By locator) {
        logger.info("Checking selection state of element: {}", locator);
        try {
            return WaitUtils.waitForPresence(locator).isSelected();
        } catch (Exception e) {
            logger.warn("Failed to check selection state for element: {}. Exception: {}", locator, e.getMessage());
            return false;
        }
    }

    public void clear(By locator) {
        logger.info("Clearing text in element: {}", locator);
        WaitUtils.waitForVisibility(locator).clear();
        applyDelay();
    }

    public void scrollToElement(By locator) {
        logger.info("Scrolling to element: {}", locator);
        try {
            org.openqa.selenium.WebElement element = WaitUtils.waitForPresence(locator);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            applyDelay();
        } catch (Exception e) {
            logger.warn("Failed to scroll to element: {}. Exception: {}", locator, e.getMessage());
        }
    }

    protected void applyDelay() {
        int delay = com.tecskool.utils.ConfigReader.getStepDelay();
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Interrupted during step delay", e);
            }
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
}
