package com.tecskool.pages;

import com.tecskool.base.BasePage;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    // Locators at the top of the page class
    private static final By countryDropdown = By.id("country");
    private static final By skillsDropdown = By.id("skills");

    public DropdownPage() {
        super();
    }

    private Select getSelect(By locator) {
        return new Select(driver.findElement(locator));
    }

    public void selectCountryByVisibleText(String countryText) {
        getSelect(countryDropdown).selectByVisibleText(countryText);
        applyDelay();
    }

    public String getFirstSelectedCountry() {
        return getSelect(countryDropdown).getFirstSelectedOption().getText();
    }

    public List<String> getAllCountryOptions() {
        return getSelect(countryDropdown).getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectSkillByVisibleText(String skillText) {
        getSelect(skillsDropdown).selectByVisibleText(skillText);
        applyDelay();
    }

    public void deselectSkillByVisibleText(String skillText) {
        getSelect(skillsDropdown).deselectByVisibleText(skillText);
        applyDelay();
    }

    public List<String> getAllSelectedSkills() {
        return getSelect(skillsDropdown).getAllSelectedOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
