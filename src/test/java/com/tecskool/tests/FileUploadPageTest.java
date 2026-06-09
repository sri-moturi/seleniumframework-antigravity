package com.tecskool.tests;

import com.tecskool.base.BaseTest;
import com.tecskool.pages.FileUploadPage;
import com.tecskool.pages.LoginPage;
import com.tecskool.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Paths;

public class FileUploadPageTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(FileUploadPageTest.class);

    @Test
    public void testFileUpload() {
        logger.info("Starting testFileUpload...");
        // Login first
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.getUsername(), ConfigReader.getPassword());

        FileUploadPage fileUploadPage = new FileUploadPage();
        fileUploadPage.scrollToFileUploadSection();
        Assert.assertTrue(fileUploadPage.isFileUploadCardDisplayed(), "File upload card should be displayed");

        // Verify static texts
        Assert.assertEquals(fileUploadPage.getFileUploadTitleText(), "File Upload", "File upload title mismatch");
        Assert.assertEquals(fileUploadPage.getFileUploadLabelText(), "Choose File", "File upload label mismatch");

        // Prepare a sample file path (absolute)
        String sampleFilePath = Paths.get("src", "test", "resources", "sample.txt").toAbsolutePath().toString();
        logger.info("Uploading sample file: {}", sampleFilePath);
        fileUploadPage.uploadFile(sampleFilePath);

        // Verify that a status message appears after upload
        String status = fileUploadPage.getFileStatusText();
        logger.info("File upload status text: {}", status);
        Assert.assertTrue(status != null && !status.trim().isEmpty(), "File upload status should not be empty after uploading a file");

        logger.info("testFileUpload completed successfully.");
    }
}
