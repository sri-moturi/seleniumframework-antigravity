package com.tecskool.base;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        logger.info("Setting up test execution...");
        driver = DriverFactory.initDriver();
        String baseUrl = ConfigReader.getBaseUrl();
        logger.info("Opening URL: {}", baseUrl);
        driver.get(baseUrl);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Tearing down test execution...");
        DriverFactory.quitDriver();
    }
}
