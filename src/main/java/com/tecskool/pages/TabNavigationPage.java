package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

/**
 * Page Object Model for the Tab Navigation section.
 * Provides actions to interact with the three tabs (Profile, Settings, About).
 */
public class TabNavigationPage extends BasePage {

    // Locators for the Tab Navigation card and its title
    private static final By tabCard = By.id("tab-card");
    private static final By tabNavTitle = By.id("tab-nav-title");

    // Tab button locators
    private static final By tabButtonProfile = By.id("tab1-btn");
    private static final By tabButtonSettings = By.id("tab2-btn");
    private static final By tabButtonAbout = By.id("tab3-btn");

    // Content locators for each tab
    private static final By profileTitle = By.id("profile-title");
    private static final By profileText = By.id("profile-text");
    private static final By profileInput = By.id("profile-input");

    private static final By settingsTitle = By.id("settings-title");
    private static final By settingsText = By.id("settings-text");
    private static final By notificationsCheckbox = By.id("notifications-setting");

    private static final By aboutTitle = By.id("about-title");
    private static final By aboutText = By.id("about-text");

    public TabNavigationPage() {
        super();
    }

    /** Scrolls the page so that the Tab Navigation card is in view. */
    public void scrollToTabNavigationSection() {
        scrollToElement(tabCard);
    }

    /** Checks whether the Tab Navigation card container is displayed. */
    public boolean isTabCardDisplayed() {
        return isDisplayed(tabCard);
    }

    /** Returns the title text of the Tab Navigation section. */
    public String getTabNavigationTitleText() {
        return getText(tabNavTitle);
    }

    /** Clicks the "Profile" tab button. */
    public void clickProfileTab() {
        scrollToElement(tabButtonProfile);
        click(tabButtonProfile);
    }

    /** Clicks the "Settings" tab button. */
    public void clickSettingsTab() {
        scrollToElement(tabButtonSettings);
        click(tabButtonSettings);
    }

    /** Clicks the "About" tab button. */
    public void clickAboutTab() {
        scrollToElement(tabButtonAbout);
        click(tabButtonAbout);
    }

    // ----- Profile tab helpers -----
    public String getProfileTitle() {
        return getText(profileTitle);
    }

    public String getProfileText() {
        return getText(profileText);
    }

    public void typeIntoProfileInput(String text) {
        scrollToElement(profileInput);
        type(profileInput, text);
    }

    public String getProfileInputValue() {
        return getAttribute(profileInput, "value");
    }

    // ----- Settings tab helpers -----
    public String getSettingsTitle() {
        return getText(settingsTitle);
    }

    public String getSettingsText() {
        return getText(settingsText);
    }

    public void toggleNotificationsCheckbox() {
        scrollToElement(notificationsCheckbox);
        click(notificationsCheckbox);
    }

    public boolean isNotificationsChecked() {
        return driver.findElement(notificationsCheckbox).isSelected();
    }

    // ----- About tab helpers -----
    public String getAboutTitle() {
        return getText(aboutTitle);
    }

    public String getAboutText() {
        return getText(aboutText);
    }
}
