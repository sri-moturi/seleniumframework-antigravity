package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.ConfigReader;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AlertsPage extends BasePage {

    // Locators at the top of the page class (using unique HTML IDs)
    private static final By simpleAlertBtn = By.id("simple-alert");
    private static final By confirmAlertBtn = By.id("confirm-alert");
    private static final By promptAlertBtn = By.id("prompt-alert");
    private static final By alertResult = By.id("alert-result");

    public AlertsPage() {
        super();
    }

    public void scrollToAlertsSection() {
        scrollToElement(simpleAlertBtn);
    }

    public void clickSimpleAlertButton() {
        click(simpleAlertBtn);
    }

    public void clickConfirmAlertButton() {
        click(confirmAlertBtn);
    }

    public void clickPromptAlertButton() {
        click(promptAlertBtn);
    }

    private Alert waitForAlert() {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getExplicitWait()))
                .until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText() {
        Alert alert = waitForAlert();
        return alert.getText();
    }

    public void acceptAlert() {
        Alert alert = waitForAlert();
        alert.accept();
        applyDelay();
    }

    public void dismissAlert() {
        Alert alert = waitForAlert();
        alert.dismiss();
        applyDelay();
    }

    public void typeTextInPrompt(String text) {
        Alert alert = waitForAlert();
        alert.sendKeys(text);
    }

    public String getAlertResultText() {
        return getText(alertResult);
    }

    public boolean isAlertResultDisplayed() {
        return isDisplayed(alertResult);
    }
}
