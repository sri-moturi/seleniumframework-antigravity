package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class SliderPage extends BasePage {

    // Locators at the top of the page class (using unique HTML IDs)
    private static final By sliderCard = By.id("slider-card");
    private static final By sliderTitle = By.id("slider-title");
    private static final By sliderLabel = By.id("intensity-label");
    private static final By sliderInput = By.id("color-intensity");
    private static final By colorDisplay = By.id("color-display");
    private static final By intensityValue = By.id("intensity-value");

    public SliderPage() {
        super();
    }

    public void scrollToSliderSection() {
        scrollToElement(sliderCard);
    }

    public boolean isSliderCardDisplayed() {
        return isDisplayed(sliderCard);
    }

    public String getSliderTitleText() {
        return getText(sliderTitle);
    }

    public String getSliderLabelText() {
        return getText(sliderLabel);
    }

    public int getSliderValue() {
        String text = getText(intensityValue);
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse slider display value text: '" + text + "'", e);
        }
    }

    public int getSliderInputValue() {
        String valueAttr = getAttribute(sliderInput, "value");
        try {
            return Integer.parseInt(valueAttr.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse slider input value attribute: '" + valueAttr + "'", e);
        }
    }

    public String getColorDisplayBgColor() {
        return WaitUtils.waitForPresence(colorDisplay).getCssValue("background-color");
    }

    public void setSliderValue(int targetValue) {
        WebElement slider = WaitUtils.waitForPresence(sliderInput);
        int currentValue = getSliderInputValue();
        int attempts = 0;
        
        while (currentValue != targetValue && attempts < 300) {
            if (currentValue < targetValue) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            } else {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
            currentValue = getSliderInputValue();
            attempts++;
        }
        applyDelay();
    }

    public void setSliderValueJS(int value) {
        WebElement slider = WaitUtils.waitForPresence(sliderInput);
        String script = "arguments[0].value = arguments[1];" +
                        "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                        "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";
        ((JavascriptExecutor) driver).executeScript(script, slider, value);
        applyDelay();
    }
}
