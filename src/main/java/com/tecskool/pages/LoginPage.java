package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    // Locators at the top of the page class
    private static final By usernameInput = By.id("username");
    private static final By passwordInput = By.id("password");
    private static final By loginButton = By.id("login-button");
    private static final By errorMessage = By.id("error-message");
    private static final By logoutButton = By.id("logout-btn");

    public LoginPage() {
        super();
    }

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(logoutButton);
    }

    public void clickLogout() {
        click(logoutButton);
    }
}
