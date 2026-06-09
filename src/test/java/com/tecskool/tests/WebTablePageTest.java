package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.WebTablePage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for the Web Data Table page.
 * Verifies that clicking the Edit button shows an alert with the correct row number.
 */
public class WebTablePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(WebTablePageTest.class);

    @Test
    public void testEditRows() {
        logger.info("Starting testEditRows...");
        // Login
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        WebTablePage webTablePage = new WebTablePage();
        // Scroll to the table section
        webTablePage.scrollToWebTableSection();
        Assert.assertTrue(webTablePage.isWebTableCardDisplayed(), "Web table card should be displayed");
        Assert.assertEquals(webTablePage.getWebTableTitleText(), "Web Data Table", "Web table title mismatch");

        String[] names = {"Mike Johnson", "David Brown"};
        for (String name : names) {
            int expectedRow = webTablePage.editRowByName(name);
            String modalTitle = webTablePage.getEditModalTitle();
            logger.info("Edit modal title for {}: {}", name, modalTitle);
            int actualRow = Integer.parseInt(modalTitle.replaceAll("[^0-9]", ""));
            Assert.assertEquals(actualRow, expectedRow, "Alert should indicate editing correct row for " + name);
        }

        logger.info("testEditRows completed successfully.");
    }
}

