package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.SliderPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SliderPageTest {
    private static final Logger logger = LogManager.getLogger(SliderPageTest.class);
    private LoginPage loginPage;
    private SliderPage sliderPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        sliderPage = new SliderPage();
        sliderPage.scrollToSliderSection();
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
    public void testDefaultSliderValue() {
        logger.info("Starting testDefaultSliderValue...");
        
        Assert.assertTrue(sliderPage.isSliderCardDisplayed(), "Slider card is not displayed!");
        Assert.assertEquals(sliderPage.getSliderTitleText(), "Slide Controls", "Slider title mismatch!");
        Assert.assertEquals(sliderPage.getSliderLabelText(), "Color Intensity:", "Slider label mismatch!");
        
        int defaultValText = sliderPage.getSliderValue();
        int defaultValInput = sliderPage.getSliderInputValue();
        
        logger.info("Default displayed value: {}, Input attribute value: {}", defaultValText, defaultValInput);
        Assert.assertEquals(defaultValText, 128, "Default display value is not 128!");
        Assert.assertEquals(defaultValInput, 128, "Default input value is not 128!");
        
        String bgStyle = sliderPage.getColorDisplayBgColor();
        logger.info("Default background style of display box: {}", bgStyle);
        Assert.assertTrue(bgStyle.contains("128"), "Color display background color doesn't contain default value 128!");
    }

    @Test(priority = 2, dependsOnMethods = "testDefaultSliderValue")
    public void testSetSliderValue() {
        logger.info("Starting testSetSliderValue...");
        
        int targetVal = 200;
        logger.info("Setting slider value using keys to: {}", targetVal);
        sliderPage.setSliderValue(targetVal);
        
        Assert.assertEquals(sliderPage.getSliderValue(), targetVal, "Displayed slider value did not update to target value!");
        Assert.assertEquals(sliderPage.getSliderInputValue(), targetVal, "Input value attribute did not update to target value!");
        
        String bgStyle = sliderPage.getColorDisplayBgColor();
        logger.info("Background style after setting to 200: {}", bgStyle);
        Assert.assertTrue(bgStyle.contains("200"), "Color display background color doesn't update to reflect 200!");
    }

    @Test(priority = 3, dependsOnMethods = "testSetSliderValue")
    public void testSliderMinMax() {
        logger.info("Starting testSliderMinMax...");
        
        // Test Min Value (0)
        logger.info("Setting slider value to min: 0");
        sliderPage.setSliderValueJS(0);
        Assert.assertEquals(sliderPage.getSliderValue(), 0, "Displayed slider value did not update to 0!");
        Assert.assertEquals(sliderPage.getSliderInputValue(), 0, "Input value attribute did not update to 0!");
        Assert.assertTrue(sliderPage.getColorDisplayBgColor().contains("0"), "Color display background color doesn't update to reflect 0!");
        
        // Test Max Value (255)
        logger.info("Setting slider value to max: 255");
        sliderPage.setSliderValueJS(255);
        Assert.assertEquals(sliderPage.getSliderValue(), 255, "Displayed slider value did not update to 255!");
        Assert.assertEquals(sliderPage.getSliderInputValue(), 255, "Input value attribute did not update to 255!");
        Assert.assertTrue(sliderPage.getColorDisplayBgColor().contains("255"), "Color display background color doesn't update to reflect 255!");
    }
}
