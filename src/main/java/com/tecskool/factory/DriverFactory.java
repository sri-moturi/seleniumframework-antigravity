package com.tecskool.factory;

import com.tecskool.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static WebDriver driver;

    public static WebDriver initDriver() {
        if (driver == null) {
            String browserName = ConfigReader.getBrowser();
            if (browserName == null) {
                browserName = "chrome";
            }
            browserName = browserName.trim().toLowerCase();
            
            logger.info("Initializing WebDriver for browser: {}", browserName);
            
            switch (browserName) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    logger.warn("Browser '{}' is not supported. Defaulting to Chrome.", browserName);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            
            int implicitWait = ConfigReader.getImplicitWait();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.manage().window().maximize();
            logger.info("WebDriver initialized successfully with {} seconds implicit wait", implicitWait);
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            logger.info("WebDriver quit successfully");
        }
    }
}
