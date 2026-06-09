package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.FileUploadPage; // placeholder import not needed
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.WindowTabControlPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowTabControlPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(WindowTabControlPageTest.class);

    @Test
    public void testWindowAndTabControls() {
        logger.info("Starting testWindowAndTabControls...");

        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        WindowTabControlPage windowPage = new WindowTabControlPage();
        // Scroll to the window controls section
        windowPage.scrollToWindowSection();
        Assert.assertTrue(windowPage.isWindowCardDisplayed(), "Window card should be displayed");
        Assert.assertEquals(windowPage.getWindowTitleText(), "Window Tab Controls", "Window title mismatch");

        // Store original window handle
        String originalWindow = driver.getWindowHandle();
        Set<String> existingHandles = driver.getWindowHandles();

        // Open new window and verify
        windowPage.clickOpenNewWindow();
        // Wait for new window handle
        Set<String> afterWindowHandles = driver.getWindowHandles();
        afterWindowHandles.removeAll(existingHandles);
        Assert.assertEquals(afterWindowHandles.size(), 1, "A new window should have opened");
        String newWindowHandle = afterWindowHandles.iterator().next();
        driver.switchTo().window(newWindowHandle);
        logger.info("Switched to new window: {}", driver.getCurrentUrl());
        // Verify that a new window was opened; URL may vary depending on implementation
        Assert.assertNotNull(driver.getCurrentUrl(), "New window URL should not be null");
        // Close the new window and switch back
        driver.close();
        driver.switchTo().window(originalWindow);

        // Open new tab and verify
        existingHandles = driver.getWindowHandles();
        windowPage.clickOpenNewTab();
        Set<String> afterTabHandles = driver.getWindowHandles();
        afterTabHandles.removeAll(existingHandles);
        Assert.assertEquals(afterTabHandles.size(), 1, "A new tab should have opened");
        String newTabHandle = afterTabHandles.iterator().next();
        driver.switchTo().window(newTabHandle);
        logger.info("Switched to new tab: {}", driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("google.com") || driver.getCurrentUrl().contains("practice.tecskool.com"), "New tab URL should contain expected site");
        // Close the tab and switch back
        driver.close();
        driver.switchTo().window(originalWindow);

        logger.info("testWindowAndTabControls completed successfully.");
    }
}
