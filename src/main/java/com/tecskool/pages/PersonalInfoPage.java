package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

public class PersonalInfoPage extends BasePage {

    // Locators at the top of the page class
    private static final By fullNameInput = By.id("full-name");
    private static final By dobInput = By.id("dob");
    private static final By emailInput = By.id("email");
    private static final By nameError = By.id("name-error");
    private static final By emailError = By.id("email-error");

    public PersonalInfoPage() {
        super();
    }

    public void enterFullName(String fullName) {
        type(fullNameInput, fullName);
    }

    public void enterDob(String dob) {
        type(dobInput, dob);
    }

    public void enterEmail(String email) {
        type(emailInput, email);
    }

    public String getFullName() {
        return getAttribute(fullNameInput, "value");
    }

    public String getDob() {
        return getAttribute(dobInput, "value");
    }

    public String getEmail() {
        return getAttribute(emailInput, "value");
    }

    public String getNameErrorMessage() {
        return getText(nameError);
    }

    public String getEmailErrorMessage() {
        return getText(emailError);
    }

    public void clearFullName() {
        clear(fullNameInput);
    }

    public void clearDob() {
        clear(dobInput);
    }

    public void clearEmail() {
        clear(emailInput);
    }
}
