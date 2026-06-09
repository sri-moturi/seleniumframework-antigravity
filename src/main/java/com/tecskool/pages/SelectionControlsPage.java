package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

public class SelectionControlsPage extends BasePage {

    // Locators at the top of the page class (static ID locators used because the HTML elements have unique IDs)
    private static final By maleRadio = By.id("male");
    private static final By femaleRadio = By.id("female");
    private static final By codingCheckbox = By.id("coding");
    private static final By designCheckbox = By.id("design");
    private static final By testingCheckbox = By.id("testing");

    public SelectionControlsPage() {
        super();
    }

    public void selectMale() {
        click(maleRadio);
    }

    public void selectFemale() {
        click(femaleRadio);
    }

    public boolean isMaleSelected() {
        return isSelected(maleRadio);
    }

    public boolean isFemaleSelected() {
        return isSelected(femaleRadio);
    }

    public void checkCoding() {
        if (!isCodingChecked()) {
            click(codingCheckbox);
        }
    }

    public void uncheckCoding() {
        if (isCodingChecked()) {
            click(codingCheckbox);
        }
    }

    public boolean isCodingChecked() {
        return isSelected(codingCheckbox);
    }

    public void checkDesign() {
        if (!isDesignChecked()) {
            click(designCheckbox);
        }
    }

    public void uncheckDesign() {
        if (isDesignChecked()) {
            click(designCheckbox);
        }
    }

    public boolean isDesignChecked() {
        return isSelected(designCheckbox);
    }

    public void checkTesting() {
        if (!isTestingChecked()) {
            click(testingCheckbox);
        }
    }

    public void uncheckTesting() {
        if (isTestingChecked()) {
            click(testingCheckbox);
        }
    }

    public boolean isTestingChecked() {
        return isSelected(testingCheckbox);
    }
}
