package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.AlertsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AlertsPageTest {
    private static final Logger logger = LogManager.getLogger(AlertsPageTest.class);
    private LoginPage loginPage;
    private AlertsPage alertsPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        alertsPage = new AlertsPage();
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
    public void testScrollToSection() {
        logger.info("Scrolling to Alert Controls section...");
        alertsPage.scrollToAlertsSection();
        Assert.assertFalse(alertsPage.isAlertResultDisplayed(), "Result message should not be visible initially!");
    }

    @Test(priority = 2, dependsOnMethods = "testScrollToSection")
    public void testSimpleAlert() {
        logger.info("Clicking Simple Alert button...");
        alertsPage.clickSimpleAlertButton();

        logger.info("Verifying simple alert text...");
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "This is a simple alert!", "Simple alert text mismatch!");

        logger.info("Accepting simple alert...");
        alertsPage.acceptAlert();

        logger.info("Verifying simple alert result container message...");
        Assert.assertTrue(alertsPage.isAlertResultDisplayed(), "Alert result text should be displayed!");
        Assert.assertEquals(alertsPage.getAlertResultText(), "Simple alert was shown", "Result message mismatch for Simple Alert!");
    }

    @Test(priority = 3, dependsOnMethods = "testSimpleAlert")
    public void testConfirmAlertAccept() {
        logger.info("Clicking Confirm Alert button for Acceptance...");
        alertsPage.clickConfirmAlertButton();

        logger.info("Verifying confirm alert text...");
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "Do you want to continue?", "Confirm alert text mismatch!");

        logger.info("Accepting confirm alert...");
        alertsPage.acceptAlert();

        logger.info("Verifying confirm alert accept result message...");
        Assert.assertEquals(alertsPage.getAlertResultText(), "Confirm result: true", "Result message mismatch for Confirm Alert Accept!");
    }

    @Test(priority = 4, dependsOnMethods = "testConfirmAlertAccept")
    public void testConfirmAlertDismiss() {
        logger.info("Clicking Confirm Alert button for Dismissal...");
        alertsPage.clickConfirmAlertButton();

        logger.info("Verifying confirm alert text...");
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "Do you want to continue?", "Confirm alert text mismatch!");

        logger.info("Dismissing confirm alert...");
        alertsPage.dismissAlert();

        logger.info("Verifying confirm alert dismiss result message...");
        Assert.assertEquals(alertsPage.getAlertResultText(), "Confirm result: false", "Result message mismatch for Confirm Alert Dismiss!");
    }

    @Test(priority = 5, dependsOnMethods = "testConfirmAlertDismiss")
    public void testPromptAlertWithInput() {
        logger.info("Clicking Prompt Alert button for input...");
        alertsPage.clickPromptAlertButton();

        logger.info("Verifying prompt alert text...");
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "Please enter your name:", "Prompt alert text mismatch!");

        logger.info("Entering text 'Selenium Tester' in prompt alert...");
        alertsPage.typeTextInPrompt("Selenium Tester");

        logger.info("Accepting prompt alert...");
        alertsPage.acceptAlert();

        logger.info("Verifying prompt alert success result message...");
        Assert.assertEquals(alertsPage.getAlertResultText(), "Prompt result: Selenium Tester", "Result message mismatch for Prompt Alert with input!");
    }

    @Test(priority = 6, dependsOnMethods = "testPromptAlertWithInput")
    public void testPromptAlertDismiss() {
        logger.info("Clicking Prompt Alert button for Dismissal...");
        alertsPage.clickPromptAlertButton();

        logger.info("Verifying prompt alert text...");
        String alertText = alertsPage.getAlertText();
        Assert.assertEquals(alertText, "Please enter your name:", "Prompt alert text mismatch!");

        logger.info("Dismissing prompt alert...");
        alertsPage.dismissAlert();

        logger.info("Verifying prompt alert dismiss result message...");
        Assert.assertEquals(alertsPage.getAlertResultText(), "Prompt result: No input provided", "Result message mismatch for Prompt Alert Dismiss!");
    }
}
