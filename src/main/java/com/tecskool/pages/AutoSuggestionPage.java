package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AutoSuggestionPage extends BasePage {

    // Locators at the top of the page class
    private static final By searchInput = By.id("programming-lang");
    private static final By suggestionsContainer = By.id("suggestions");
    private static final By suggestionItems = By.className("suggestion-item");

    public AutoSuggestionPage() {
        super();
    }

    public void typeSearchKeyword(String keyword) {
        WebElement element = WaitUtils.waitForVisibility(searchInput);
        element.click();
        element.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        element.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        element.sendKeys(keyword);
        applyDelay();
    }

    public List<String> getSuggestionsTextList() {
        WaitUtils.waitForVisibility(suggestionsContainer);
        List<WebElement> elements = driver.findElements(suggestionItems);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectSuggestionByText(String suggestionText) {
        WaitUtils.waitForVisibility(suggestionsContainer);
        List<WebElement> elements = driver.findElements(suggestionItems);
        for (WebElement element : elements) {
            if (element.getText().equalsIgnoreCase(suggestionText)) {
                element.click();
                applyDelay();
                return;
            }
        }
        throw new RuntimeException("Suggestion '" + suggestionText + "' not found in auto suggestions!");
    }

    public String getInputValue() {
        return getAttribute(searchInput, "value");
    }

    public boolean isSuggestionsBoxDisplayed() {
        return isDisplayed(suggestionsContainer);
    }
}
