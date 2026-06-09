package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TogglePage extends BasePage {

    // Locators for the Toggle Elements section
    private static final By toggleCard = By.id("toggle-card");
    private static final By toggleTitle = By.id("toggle-title");
    private static final By toggleLabel = By.id("toggle-label");
    private static final By toggleSwitch = By.id("toggle-switch");
    private static final By toggleLabelSwitch = By.cssSelector("label.toggle-switch");
    private static final By toggleStatus = By.id("toggle-status");

    public TogglePage() {
        super();
    }

    /**
     * Scrolls the page so that the toggle card is in view.
     */
    public void scrollToToggleSection() {
        scrollToElement(toggleCard);
    }

    /**
     * Checks whether the toggle card container is displayed.
     */
    public boolean isToggleCardDisplayed() {
        return isDisplayed(toggleCard);
    }

    /**
     * Returns the title text of the toggle section.
     */
    public String getToggleTitleText() {
        return getText(toggleTitle);
    }

    /**
     * Returns the label text that precedes the toggle switch.
     */
    public String getToggleLabelText() {
        return getText(toggleLabel);
    }

    /**
     * Returns the current status text (e.g., "Status: Enabled" or "Status: Disabled").
     */
    public String getToggleStatusText() {
        return getText(toggleStatus);
    }

    /**
     * Returns true if the underlying checkbox input is selected (i.e., toggle is ON).
     */
    public boolean isToggleOn() {
        return isSelected(toggleSwitch);
    }

    /**
     * Turns the toggle ON if it is not already.
     */
    public void toggleOn() {
        if (!isToggleOn()) {
            click(toggleLabelSwitch);
            applyDelay();
        }
    }

    /**
     * Turns the toggle OFF if it is not already.
     */
    public void toggleOff() {
        if (isToggleOn()) {
            click(toggleLabelSwitch);
            applyDelay();
        }
    }
}
