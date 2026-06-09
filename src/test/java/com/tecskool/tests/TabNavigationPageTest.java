package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.TabNavigationPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for the Tab Navigation section on the practice page.
 * Verifies tab switching, content visibility and basic interactions.
 */
public class TabNavigationPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(TabNavigationPageTest.class);

    @Test
    public void testTabNavigation() {
        logger.info("Starting testTabNavigation...");

        // Log in first to reach the main page
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        TabNavigationPage tabPage = new TabNavigationPage();
        // Scroll to the Tab Navigation card
        tabPage.scrollToTabNavigationSection();
        Assert.assertTrue(tabPage.isTabCardDisplayed(), "Tab navigation card should be displayed");
        Assert.assertEquals(tabPage.getTabNavigationTitleText(), "Tab Navigation", "Tab navigation title mismatch");

        // ---- Profile Tab ----
        tabPage.clickProfileTab();
        Assert.assertEquals(tabPage.getProfileTitle(), "User Profile", "Profile tab title mismatch");
        Assert.assertEquals(tabPage.getProfileText(), "This is the profile tab content. Here you can view and edit your personal information.", "Profile tab text mismatch");
        String sampleBio = "Automation enthusiast";
        tabPage.typeIntoProfileInput(sampleBio);
        Assert.assertEquals(tabPage.getProfileInputValue(), sampleBio, "Profile input value should match typed text");

        // ---- Settings Tab ----
        tabPage.clickSettingsTab();
        Assert.assertEquals(tabPage.getSettingsTitle(), "Settings", "Settings tab title mismatch");
        Assert.assertEquals(tabPage.getSettingsText(), "Configure your application settings and preferences here.", "Settings tab text mismatch");
        // Verify checkbox can be toggled
        boolean initiallyChecked = tabPage.isNotificationsChecked();
        tabPage.toggleNotificationsCheckbox();
        Assert.assertNotEquals(tabPage.isNotificationsChecked(), initiallyChecked, "Notifications checkbox should toggle state");

        // ---- About Tab ----
        tabPage.clickAboutTab();
        Assert.assertEquals(tabPage.getAboutTitle(), "About", "About tab title mismatch");
        String aboutText = tabPage.getAboutText();
        Assert.assertTrue(aboutText.contains("Tecskool Practice Page v1.0"), "About text should contain version info");

        logger.info("testTabNavigation completed successfully.");
    }
}
