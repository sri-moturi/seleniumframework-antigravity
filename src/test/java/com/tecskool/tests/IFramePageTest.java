package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.LoginPage;
import com.tecskool.pages.IFramePage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test for the Wikipedia IFrame section.
 * Verifies the iframe is present, switches into it, clicks the Blue‑billed white tern image,
 * clicks the download button in the dialog, and verifies the attribution alert.
 */
public class IFramePageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(IFramePageTest.class);

    @Test
    public void testIFrameImageDownload() throws Exception {
        logger.info("Starting testIFrameImageDownload...");

        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        IFramePage iFramePage = new IFramePage();
        // Scroll to the iframe section and verify visibility
        iFramePage.scrollToIFrameSection();
        Assert.assertTrue(iFramePage.isIFrameCardDisplayed(), "IFrame card should be displayed");
        Assert.assertEquals(iFramePage.getIFrameTitleText(), "Wikipedia IFrame", "IFrame title mismatch");

        // Switch to the Wikipedia iframe
        iFramePage.switchToWikipediaIframe();

        // Click the image inside the iframe to open the download dialog
        iFramePage.clickBlueBilledWhiteTernImage();
        logger.info("Clicked the image, waiting for download dialog.");

        // Use the page object to click the download button inside the dialog
        iFramePage.clickDownloadButtonInDialog();
        logger.info("Clicked the download button via page object.");

        // Retrieve the download dialog using the page object's helper method
        org.openqa.selenium.WebElement dialog = iFramePage.getDownloadDialog();
            // Verify the Attribution header inside the dialog (exact static text)
            org.openqa.selenium.WebElement header = dialog.findElement(By.xpath(
                ".//p[contains(@class,'cdx-dialog__header__title') and normalize-space(.)='Attribution']"
            ));
            String headerText = header.getText();
            logger.info("Attribution header displayed with text: {}", headerText);
            org.testng.Assert.assertEquals(headerText, "Attribution", "Attribution header text mismatch");

        // Switch back to the main page context
        iFramePage.switchToDefaultContent();

        logger.info("testIFrameImageDownload completed successfully.");
    }
}
