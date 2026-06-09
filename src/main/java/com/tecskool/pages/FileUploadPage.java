package com.tecskool.pages;

import com.tecskool.base.BasePage;
import com.tecskool.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FileUploadPage extends BasePage {

    // Locators for the File Upload section
    private static final By uploadCard = By.id("file-upload-card");
    private static final By uploadTitle = By.id("file-title");
    private static final By fileInput = By.id("file-input");
    private static final By fileLabel = By.id("file-label");
    private static final By fileStatus = By.id("file-status");

    public FileUploadPage() {
        super();
    }

    /**
     * Scrolls the page so that the file upload card is in view.
     */
    public void scrollToFileUploadSection() {
        scrollToElement(uploadCard);
    }

    /**
     * Checks whether the file upload card container is displayed.
     */
    public boolean isFileUploadCardDisplayed() {
        return isDisplayed(uploadCard);
    }

    /**
     * Returns the title text of the file upload section.
     */
    public String getFileUploadTitleText() {
        return getText(uploadTitle);
    }

    /**
     * Returns the label text for the file input.
     */
    public String getFileUploadLabelText() {
        return getText(fileLabel);
    }

    /**
     * Uploads a file by sending the absolute file path to the hidden input element.
     *
     * @param absoluteFilePath the absolute path to the file to be uploaded
     */
    public void uploadFile(String absoluteFilePath) {
        WebElement input = WaitUtils.waitForPresence(fileInput);
        // Selenium can upload directly by sending the file path to the input element
        input.sendKeys(absoluteFilePath);
        applyDelay();
    }

    /**
     * Returns the status message displayed after a file upload operation.
     */
    public String getFileStatusText() {
        return getText(fileStatus);
    }
}
