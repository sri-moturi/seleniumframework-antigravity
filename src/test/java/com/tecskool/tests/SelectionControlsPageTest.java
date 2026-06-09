package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.SelectionControlsPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SelectionControlsPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(SelectionControlsPageTest.class);

    @Test
    public void testSelectionControls() {
        logger.info("Executing testSelectionControls...");
        LoginPage loginPage = new LoginPage();
        
        // Log in to access the selection controls page
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        
        SelectionControlsPage selectionControlsPage = new SelectionControlsPage();
        
        logger.info("Selecting Male radio button...");
        selectionControlsPage.selectMale();
        Assert.assertTrue(selectionControlsPage.isMaleSelected(), "Male radio button is not selected!");
        Assert.assertFalse(selectionControlsPage.isFemaleSelected(), "Female radio button is selected!");
        
        logger.info("Selecting Female radio button...");
        selectionControlsPage.selectFemale();
        Assert.assertTrue(selectionControlsPage.isFemaleSelected(), "Female radio button is not selected!");
        Assert.assertFalse(selectionControlsPage.isMaleSelected(), "Male radio button is selected!");
        
        logger.info("Checking interest checkboxes...");
        selectionControlsPage.checkCoding();
        selectionControlsPage.checkDesign();
        selectionControlsPage.checkTesting();
        
        Assert.assertTrue(selectionControlsPage.isCodingChecked(), "Coding checkbox is not checked!");
        Assert.assertTrue(selectionControlsPage.isDesignChecked(), "Design checkbox is not checked!");
        Assert.assertTrue(selectionControlsPage.isTestingChecked(), "Testing checkbox is not checked!");
        
        logger.info("Unchecking Design checkbox...");
        selectionControlsPage.uncheckDesign();
        Assert.assertFalse(selectionControlsPage.isDesignChecked(), "Design checkbox is still checked!");
        Assert.assertTrue(selectionControlsPage.isCodingChecked(), "Coding checkbox got unchecked!");
        
        logger.info("testSelectionControls completed successfully.");
    }
}
