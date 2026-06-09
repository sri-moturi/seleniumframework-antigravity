package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(LoginTest.class);

    @Test(priority = 1)
    public void testValidLogin() {
        logger.info("Executing testValidLogin...");
        LoginPage loginPage = new LoginPage();
        
        // Login with valid credentials
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        logger.info("Asserting that logout button is visible on the dashboard...");
        Assert.assertTrue(loginPage.isLogoutButtonDisplayed(), "Logout button was not displayed after successful login!");
        logger.info("testValidLogin passed.");
    }

    @Test(priority = 2)
    public void testInvalidLogin() {
        logger.info("Executing testInvalidLogin...");
        LoginPage loginPage = new LoginPage();
        
        // Login with invalid credentials
        loginPage.login("invalid_user@tecskool.com", "invalid_password");
        
        logger.info("Asserting that the error message text matches...");
        String expectedError = "Invalid credentials! Use: " + ConfigReader.getUsername() + " / " + ConfigReader.getPassword();
        String actualError = loginPage.getErrorMessage();
        
        Assert.assertEquals(actualError, expectedError, "Login error message is incorrect or not displayed!");
        logger.info("testInvalidLogin passed.");
    }
}
