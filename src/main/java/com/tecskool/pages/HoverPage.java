package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HoverPage extends BasePage {

    // Locators at the top of the page class (using unique HTML IDs)
    private static final By hoverTrigger = By.id("hover-trigger");
    private static final By dropdownMenu = By.id("dropdown-menu");
    private static final By option3 = By.id("option3");
    private static final By selectedOption = By.id("selected-option");

    public HoverPage() {
        super();
    }

    public void scrollToHoverSection() {
        scrollToElement(hoverTrigger);
    }

    public void hoverOverTrigger() {
        WebElement triggerElement = WaitUtils.waitForVisibility(hoverTrigger);
        new Actions(driver).moveToElement(triggerElement).perform();
        applyDelay();
    }

    public boolean isDropdownDisplayed() {
        return isDisplayed(dropdownMenu);
    }

    public void clickOption3() {
        WebElement optionElement = WaitUtils.waitForPresence(option3);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
        applyDelay();
    }

    public String getSelectedOptionText() {
        scrollToElement(selectedOption);
        return getText(selectedOption);
    }
}
