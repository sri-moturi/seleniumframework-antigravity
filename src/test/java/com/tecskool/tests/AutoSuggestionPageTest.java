package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.AutoSuggestionPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class AutoSuggestionPageTest {
    private static final Logger logger = LogManager.getLogger(AutoSuggestionPageTest.class);
    private LoginPage loginPage;
    private AutoSuggestionPage autoSuggestionPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        autoSuggestionPage = new AutoSuggestionPage();
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
    public void testSuggestionsMatching() {
        logger.info("Starting testSuggestionsMatching scenario...");
        
        logger.info("Typing search keyword 'J'...");
        autoSuggestionPage.typeSearchKeyword("J");
        
        logger.info("Verifying suggestions list contains JavaScript and Java...");
        List<String> suggestions = autoSuggestionPage.getSuggestionsTextList();
        Assert.assertTrue(suggestions.contains("JavaScript"), "JavaScript is missing from suggestions!");
        Assert.assertTrue(suggestions.contains("Java"), "Java is missing from suggestions!");
        Assert.assertEquals(suggestions.size(), 2, "Expected exactly 2 suggestions for keyword 'J'!");
        
        logger.info("testSuggestionsMatching completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testSuggestionsMatching")
    public void testSelectSuggestion() {
        logger.info("Starting testSelectSuggestion scenario...");
        
        logger.info("Typing search keyword 'J'...");
        autoSuggestionPage.typeSearchKeyword("J");
        
        logger.info("Selecting 'Java' from suggestions...");
        autoSuggestionPage.selectSuggestionByText("Java");
        
        logger.info("Verifying input value matches selected item...");
        String value = autoSuggestionPage.getInputValue();
        Assert.assertEquals(value, "Java", "Selected suggestion was not populated in search input field!");
        
        logger.info("Verifying that suggestions box is hidden...");
        Assert.assertFalse(autoSuggestionPage.isSuggestionsBoxDisplayed(), "Suggestions box is still visible after selection!");
        
        logger.info("testSelectSuggestion completed successfully.");
    }
}
