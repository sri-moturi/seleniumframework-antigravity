package com.tecskool.tests;

import com.tecskool.factory.DriverFactory;
import com.tecskool.utils.ConfigReader;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.DragDropPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DragDropPageTest {
    private static final Logger logger = LogManager.getLogger(DragDropPageTest.class);
    private LoginPage loginPage;
    private DragDropPage dragDropPage;

    @BeforeClass
    public void setUpClass() {
        logger.info("Initializing browser session and logging in...");
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getBaseUrl());
        
        loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());
        dragDropPage = new DragDropPage();
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
    public void testInitialState() {
        logger.info("Scrolling to drag and drop section...");
        dragDropPage.scrollToDragDropSection();
        logger.info("Checking initial state of Drag & Drop page...");
        Assert.assertEquals(dragDropPage.getDropTargetText().trim(), "Drop Here", "Initial drop target text mismatch!");
        Assert.assertFalse(dragDropPage.isDragItemInsideDropTarget(), "Drag item should not be inside drop target initially!");
    }

    @Test(priority = 2, dependsOnMethods = "testInitialState")
    public void testDragAndDrop() {
        logger.info("Performing drag and drop operation...");
        // In modern HTML5, Selenium Actions dragAndDrop sometimes fails to trigger drop events.
        // We will try standard Actions first; if it doesn't drop, we will use JS executor.
        dragDropPage.dragAndDropElement();
        
        if (!dragDropPage.isDragItemInsideDropTarget()) {
            logger.info("Standard Actions dragAndDrop did not complete the drop. Retrying using JS executor...");
            dragDropPage.dragAndDropElementUsingJS();
        }

        logger.info("Verifying drag-and-drop success...");
        Assert.assertTrue(dragDropPage.isDragItemInsideDropTarget(), "Drag item should be inside drop target after drag and drop!");
        String targetText = dragDropPage.getDropTargetText();
        Assert.assertTrue(targetText.contains("Drop Here"), "Target text should still contain initial text!");
        Assert.assertTrue(targetText.contains("Drag Me!"), "Target text should now contain draggable element's text!");
        logger.info("testDragAndDrop completed successfully.");
    }
}
