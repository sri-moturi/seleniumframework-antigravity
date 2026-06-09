package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.PersonalInfoPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PersonalInfoPageTest {
    private static final Logger logger = LogManager.getLogger(PersonalInfoPageTest.class);
    private LoginPage loginPage;
    private PersonalInfoPage personalInfoPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        personalInfoPage = new PersonalInfoPage();
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
    public void testFillAndEraseInfo() {
        logger.info("Starting Scenario 1: Fill valid personal details and erase them...");
        String testName = "Jane Doe";
        String dobInputVal = "12/12/1990";
        String expectedDobVal = "1990-12-12";
        String testEmail = "jane.doe@example.com";

        logger.info("Entering details: Name='{}', DOB='{}', Email='{}'", testName, dobInputVal, testEmail);
        personalInfoPage.enterFullName(testName);
        personalInfoPage.enterDob(dobInputVal);
        personalInfoPage.enterEmail(testEmail);

        logger.info("Verifying form values...");
        Assert.assertEquals(personalInfoPage.getFullName(), testName, "Full Name value mismatch!");
        Assert.assertEquals(personalInfoPage.getDob(), expectedDobVal, "Date of Birth value mismatch!");
        Assert.assertEquals(personalInfoPage.getEmail(), testEmail, "Email value mismatch!");

        logger.info("Erasing (clearing) all entered data...");
        personalInfoPage.clearFullName();
        personalInfoPage.clearDob();
        personalInfoPage.clearEmail();
        
        // Assert fields are empty
        Assert.assertEquals(personalInfoPage.getFullName(), "", "Full Name field was not cleared!");
        Assert.assertEquals(personalInfoPage.getDob(), "", "DOB field was not cleared!");
        Assert.assertEquals(personalInfoPage.getEmail(), "", "Email field was not cleared!");
        logger.info("Scenario 1 passed.");
    }

    @Test(priority = 2, dependsOnMethods = "testFillAndEraseInfo")
    public void testNameValidation() {
        logger.info("Starting Scenario 2: Validate short name error message...");
        logger.info("Entering 1-character name...");
        personalInfoPage.enterFullName("A");
        
        // Trigger blur event by moving focus to DOB field
        personalInfoPage.enterDob("");

        String expectedError = "Name must be at least 2 characters long";
        String actualError = personalInfoPage.getNameErrorMessage();

        logger.info("Asserting name validation error message...");
        Assert.assertEquals(actualError, expectedError, "Name validation error message mismatch!");

        logger.info("Clearing Full Name input...");
        personalInfoPage.clearFullName();
        logger.info("Scenario 2 passed.");
    }

    @Test(priority = 3, dependsOnMethods = "testNameValidation")
    public void testEmailValidation() {
        logger.info("Starting Scenario 3: Validate invalid email error message...");
        logger.info("Entering invalid email...");
        personalInfoPage.enterEmail("invalidemail");

        String expectedError = "Email must contain @ and . (example: user@domain.com)";
        String actualError = personalInfoPage.getEmailErrorMessage();

        logger.info("Asserting email validation error message...");
        Assert.assertEquals(actualError, expectedError, "Email validation error message mismatch!");

        logger.info("Clearing Email input...");
        personalInfoPage.clearEmail();
        logger.info("Scenario 3 passed.");
    }
}
