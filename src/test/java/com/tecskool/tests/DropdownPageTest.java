package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.DropdownPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class DropdownPageTest {
    private static final Logger logger = LogManager.getLogger(DropdownPageTest.class);
    private LoginPage loginPage;
    private DropdownPage dropdownPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dropdownPage = new DropdownPage();
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
    public void testCountryDropdown() {
        logger.info("Starting testCountryDropdown scenario...");
        
        logger.info("Verifying default selected country...");
        String defaultCountry = dropdownPage.getFirstSelectedCountry();
        Assert.assertEquals(defaultCountry, "Select Country", "Default country selection mismatch!");
        
        logger.info("Selecting India...");
        dropdownPage.selectCountryByVisibleText("India");
        
        String selectedCountry = dropdownPage.getFirstSelectedCountry();
        Assert.assertEquals(selectedCountry, "India", "Failed to select India from country dropdown!");
        
        logger.info("testCountryDropdown completed successfully.");
    }

    @Test(priority = 2, dependsOnMethods = "testCountryDropdown")
    public void testSkillsDropdown() {
        logger.info("Starting testSkillsDropdown scenario...");
        
        logger.info("Verifying initial selected skills is empty...");
        List<String> initialSkills = dropdownPage.getAllSelectedSkills();
        Assert.assertTrue(initialSkills.isEmpty(), "Skills dropdown should not have any default selection!");
        
        logger.info("Selecting skills: Java, Selenium...");
        dropdownPage.selectSkillByVisibleText("Java");
        dropdownPage.selectSkillByVisibleText("Selenium");
        
        List<String> selectedSkills = dropdownPage.getAllSelectedSkills();
        Assert.assertTrue(selectedSkills.contains("Java"), "Java skill not selected!");
        Assert.assertTrue(selectedSkills.contains("Selenium"), "Selenium skill not selected!");
        Assert.assertEquals(selectedSkills.size(), 2, "Expected 2 selected skills!");
        
        logger.info("Deselecting skill: Java...");
        dropdownPage.deselectSkillByVisibleText("Java");
        
        List<String> remainingSkills = dropdownPage.getAllSelectedSkills();
        Assert.assertFalse(remainingSkills.contains("Java"), "Java skill should have been deselected!");
        Assert.assertTrue(remainingSkills.contains("Selenium"), "Selenium skill should remain selected!");
        Assert.assertEquals(remainingSkills.size(), 1, "Expected exactly 1 selected skill remaining!");
        
        logger.info("testSkillsDropdown completed successfully.");
    }
}
