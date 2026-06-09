package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.HoverPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoverPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(HoverPageTest.class);

    @Test
    public void testHoverOption3Selection() {
        logger.info("Executing testHoverOption3Selection...");
        LoginPage loginPage = new LoginPage();
        
        // Log in to access the practice page
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        HoverPage hoverPage = new HoverPage();
        
        logger.info("Scrolling to Hover Controls section...");
        hoverPage.scrollToHoverSection();
        
        logger.info("Verifying dropdown is not visible initially...");
        Assert.assertFalse(hoverPage.isDropdownDisplayed(), "Dropdown menu should not be displayed initially!");
        
        logger.info("Hovering over trigger button...");
        hoverPage.hoverOverTrigger();
        
        logger.info("Verifying dropdown is visible after hover...");
        Assert.assertTrue(hoverPage.isDropdownDisplayed(), "Dropdown menu should be displayed after hovering!");
        
        logger.info("Selecting Option 3...");
        hoverPage.clickOption3();
        
        logger.info("Verifying selected option result message contains 'Option 3'...");
        String selectedText = hoverPage.getSelectedOptionText();
        logger.info("Result message: {}", selectedText);
        Assert.assertTrue(selectedText.contains("Option 3"), "Selected option text does not contain 'Option 3'!");
        
        logger.info("testHoverOption3Selection completed successfully.");
    }
}
