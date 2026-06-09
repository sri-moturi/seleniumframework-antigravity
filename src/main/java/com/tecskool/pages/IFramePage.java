package com.tecskool.pages;

import com.tecskool.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Page Object Model for the Wikipedia IFrame section.
 * All element locators are defined as private static final By fields.
 * Interactions are performed using reusable methods from BasePage.
 */
public class IFramePage extends BasePage {

    // ---------- Locators ----------
    private static final By IFRAME_CARD = By.id("iframe-card");
    private static final By IFRAME_TITLE = By.id("iframe-title");
    private static final By WIKIPEDIA_IFRAME = By.id("wikipedia-frame");
    private static final By BLUE_BILLED_WHITE_TERN_IMG = By.xpath("//img[contains(@alt, 'Blue-billed white tern')]");
    private static final By DOWNLOAD_BUTTON = By.cssSelector("a.mw-mmv-download-button[title='Download this file']");
    private static final By DOWNLOAD_DIALOG = By.cssSelector("div.mw-mmv-dialog.mw-mmv-download-dialog");

    public IFramePage() {
        super();
    }

    /** Scrolls the page so that the IFrame card is in view. */
    public void scrollToIFrameSection() {
        scrollToElement(IFRAME_CARD);
    }

    /** Checks whether the IFrame card container is displayed. */
    public boolean isIFrameCardDisplayed() {
        return isDisplayed(IFRAME_CARD);
    }

    /** Returns the title text of the IFrame section. */
    public String getIFrameTitleText() {
        return getText(IFRAME_TITLE);
    }

    /** Switches Selenium's context to the Wikipedia iframe. */
    public void switchToWikipediaIframe() {
        // Ensure the iframe element is present before switching
        scrollToElement(WIKIPEDIA_IFRAME);
        WebElement iframe = driver.findElement(WIKIPEDIA_IFRAME);
        driver.switchTo().frame(iframe);
        applyDelay();
    }

    /** Switches back to the default content (main page). */
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
        applyDelay();
    }

    /** Clicks the target image inside the Wikipedia iframe. */
    public void clickBlueBilledWhiteTernImage() {
        click(BLUE_BILLED_WHITE_TERN_IMG);
    }

    /** Clicks the "Download this file" button inside the download dialog. */
    public void clickDownloadButtonInDialog() {
        // Wait for the button to be clickable and then click it
        click(DOWNLOAD_BUTTON);
        logger.info("Clicked the download button inside the dialog.");
    }

    /** Returns the download dialog element after it appears. */
    public WebElement getDownloadDialog() {
        // Wait for the dialog container to be present and return it
        return driver.findElement(DOWNLOAD_DIALOG);
    }
}
