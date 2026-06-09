package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;

public class ButtonControlsPage extends BasePage {

    // Locators at the top of the page class (using unique HTML element IDs)
    private static final By enableBtn = By.id("enable-btn");
    private static final By targetBtn = By.id("target-btn");
    private static final By disableBtn = By.id("disable-btn");
    private static final By loadBtn = By.id("load-btn");
    private static final By msgDiv = By.id("loaded-content");

    public ButtonControlsPage() {
        super();
    }

    public void clickEnableButton() {
        click(enableBtn);
    }

    public void clickDisableButton() {
        click(disableBtn);
    }

    public void clickLoadContentButton() {
        click(loadBtn);
    }

    public boolean isTargetButtonEnabled() {
        return WaitUtils.waitForPresence(targetBtn).isEnabled();
    }

    public String getTargetButtonText() {
        return getText(targetBtn);
    }

    public String getTargetButtonClass() {
        return getAttribute(targetBtn, "class");
    }

    public String getTargetButtonBackgroundColor() {
        return WaitUtils.waitForPresence(targetBtn).getCssValue("background-color");
    }

    public String getLoadedContentMessageText() {
        return getText(msgDiv);
    }

    public boolean isLoadedContentMessageDisplayed() {
        return isDisplayed(msgDiv);
    }
}
