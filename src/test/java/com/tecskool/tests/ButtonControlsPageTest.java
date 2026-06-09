package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.ButtonControlsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ButtonControlsPageTest {
    private static final Logger logger = LogManager.getLogger(ButtonControlsPageTest.class);
    private LoginPage loginPage;
    private ButtonControlsPage buttonControlsPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        buttonControlsPage = new ButtonControlsPage();
    }

    @AfterClass
    public void tearDownClass() {
        logger.info("Logging out and quitting browser...");
        try {
            if (loginPage.isLogoutButtonDisplayed()) {
                loginPage.clickLogout();
                logger.info("Logout button clicked successfully.");
            }
        } catch (Exception e) {
            logger.warn("Logout failed or logout button not displayed. Error: {}", e.getMessage());
        } finally {
            DriverFactory.quitDriver();
        }
    }

    @Test(priority = 1)
    public void testTargetButtonInitiallyDisabled() {
        logger.info("Starting testTargetButtonInitiallyDisabled...");
        Assert.assertFalse(buttonControlsPage.isTargetButtonEnabled(), "Target button should be initially disabled!");
        Assert.assertEquals(buttonControlsPage.getTargetButtonText().toLowerCase(), "target button", 
            "Target button initial text mismatch!");
        logger.info("testTargetButtonInitiallyDisabled completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testTargetButtonInitiallyDisabled")
    public void testEnableButton() {
        logger.info("Starting testEnableButton...");
        logger.info("Clicking Enable Button...");
        buttonControlsPage.clickEnableButton();

        logger.info("Verifying Target Button is enabled...");
        Assert.assertTrue(buttonControlsPage.isTargetButtonEnabled(), "Target button should be enabled after clicking Enable button!");

        logger.info("Verifying text changed to 'Enabled Button'...");
        Assert.assertEquals(buttonControlsPage.getTargetButtonText().toLowerCase(), "enabled button", 
            "Target button text should be 'Enabled Button' after enabling!");

        logger.info("Verifying color is green...");
        String buttonClass = buttonControlsPage.getTargetButtonClass();
        String bgColor = buttonControlsPage.getTargetButtonBackgroundColor();
        logger.info("Target Button class: {}, background-color: {}", buttonClass, bgColor);
        
        Assert.assertTrue(buttonClass.contains("btn-success"), "Target button should have success (green) class!");
        Assert.assertTrue(
            (bgColor.contains("40") && bgColor.contains("167") && bgColor.contains("69")) ||
            (bgColor.contains("39") && bgColor.contains("174") && bgColor.contains("96")),
            "Target button background color should be green (rgb(40,167,69) or rgb(39,174,96)), but got: " + bgColor);
        logger.info("testEnableButton completed successfully.");
    }

    @Test(priority = 3, dependsOnMethods = "testEnableButton")
    public void testDisableButton() {
        logger.info("Starting testDisableButton...");
        logger.info("Clicking Disable Button...");
        buttonControlsPage.clickDisableButton();

        logger.info("Verifying Target Button is disabled...");
        Assert.assertFalse(buttonControlsPage.isTargetButtonEnabled(), "Target button should be disabled after clicking Disable button!");

        logger.info("Verifying text changed to 'Target Button'...");
        Assert.assertEquals(buttonControlsPage.getTargetButtonText().toLowerCase(), "target button", 
            "Target button text should be 'Target Button' after disabling!");

        logger.info("Verifying color is returned to default (no green color)...");
        String buttonClass = buttonControlsPage.getTargetButtonClass();
        String bgColor = buttonControlsPage.getTargetButtonBackgroundColor();
        logger.info("Target Button class: {}, background-color: {}", buttonClass, bgColor);
        
        Assert.assertTrue(
            (bgColor.contains("189") && bgColor.contains("195") && bgColor.contains("199")) ||
            (bgColor.contains("108") && bgColor.contains("117") && bgColor.contains("125")),
            "Target button background color should be grey/default disabled color, but got: " + bgColor);
        logger.info("testDisableButton completed successfully.");
    }

    @Test(priority = 4, dependsOnMethods = "testDisableButton")
    public void testLoadContent() {
        logger.info("Starting testLoadContent...");
        logger.info("Clicking Load Content button...");
        buttonControlsPage.clickLoadContentButton();

        logger.info("Waiting for message to appear and verifying text...");
        String msgText = buttonControlsPage.getLoadedContentMessageText();
        Assert.assertEquals(msgText, "Content loaded successfully! 🎉", "Loaded content message text mismatch!");
        Assert.assertTrue(buttonControlsPage.isLoadedContentMessageDisplayed(), "Loaded content message should be displayed!");
        logger.info("testLoadContent completed successfully.");
    }

    @Test(priority = 5, dependsOnMethods = "testLoadContent")
    public void testLoadedContentAfterEnableClick() {
        logger.info("Starting testLoadedContentAfterEnableClick...");
        logger.info("Clicking Enable Button while message is displayed...");
        buttonControlsPage.clickEnableButton();

        logger.info("Checking if message still shows...");
        boolean isDisplayed = buttonControlsPage.isLoadedContentMessageDisplayed();
        
        // This is expected to fail because the website doesn't hide the message when Enable is clicked.
        // We assert that the message is NOT displayed. If it is displayed, the assertion fails and the test fails,
        // which meets the user requirement: "if the msg Content loaded successfully! 🎉 still shows - fail the test".
        Assert.assertFalse(isDisplayed, "Fail: Loaded content message still shows after clicking Enable button!");
        logger.info("testLoadedContentAfterEnableClick completed successfully.");
    }
}
