package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.TogglePage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TogglePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(TogglePageTest.class);

    @Test
    public void testToggleElements() {
        logger.info("Starting testToggleElements...");
        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        TogglePage togglePage = new TogglePage();
        // Scroll to the toggle section
        togglePage.scrollToToggleSection();
        Assert.assertTrue(togglePage.isToggleCardDisplayed(), "Toggle card should be displayed");

        // Verify static texts
        Assert.assertEquals(togglePage.getToggleTitleText(), "Toggle Element", "Toggle title mismatch");
        Assert.assertEquals(togglePage.getToggleLabelText(), "Enable Notifications:", "Toggle label mismatch");
        Assert.assertEquals(togglePage.getToggleStatusText(), "Status: Disabled", "Initial toggle status text mismatch");

        // Turn ON and verify
        togglePage.toggleOn();
        Assert.assertTrue(togglePage.isToggleOn(), "Toggle should be ON after toggleOn()");
        Assert.assertTrue(togglePage.getToggleStatusText().contains("Enabled"), "Toggle status should indicate enabled");

        // Turn OFF and verify
        togglePage.toggleOff();
        Assert.assertFalse(togglePage.isToggleOn(), "Toggle should be OFF after toggleOff()");
        Assert.assertTrue(togglePage.getToggleStatusText().contains("Disabled"), "Toggle status should indicate disabled");

        logger.info("testToggleElements completed successfully.");
    }
}
