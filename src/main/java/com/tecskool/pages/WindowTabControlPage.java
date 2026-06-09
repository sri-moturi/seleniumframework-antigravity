package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

public class WindowTabControlPage extends BasePage {

    // Locators for the Window Tab Controls section
    private static final By windowCard = By.id("window-card");
    private static final By windowTitle = By.id("window-title");
    private static final By newWindowBtn = By.id("new-window-btn");
    private static final By newTabBtn = By.id("new-tab-btn");

    public WindowTabControlPage() {
        super();
    }

    /**
     * Scrolls the page so that the window control card is in view.
     */
    public void scrollToWindowSection() {
        scrollToElement(windowCard);
    }

    /**
     * Checks whether the window control card container is displayed.
     */
    public boolean isWindowCardDisplayed() {
        return isDisplayed(windowCard);
    }

    /**
     * Returns the title text of the window tab controls section.
     */
    public String getWindowTitleText() {
        return getText(windowTitle);
    }

    /**
     * Clicks the "Open New Window" button.
     */
    public void clickOpenNewWindow() {
        click(newWindowBtn);
    }

    /**
     * Clicks the "Open New Tab" button.
     */
    public void clickOpenNewTab() {
        click(newTabBtn);
    }
}
