package com.tecskool.utils;

import com.tecskool.factory.DriverFactory;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtils {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        
        File directory = new File("screenshots");
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                logger.debug("Created screenshots directory.");
            }
        }
        
        String destinationPath = "screenshots/" + fileName;
        File destinationFile = new File(destinationPath);
        
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File sourceFile = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(sourceFile, destinationFile);
            logger.info("Screenshot saved for test '{}' at: {}", testName, destinationFile.getAbsolutePath());
            return destinationFile.getAbsolutePath();
        } catch (IOException e) {
            logger.error("Failed to save screenshot file for test '{}'", testName, e);
            return null;
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test '{}'", testName, e);
            return null;
        }
    }
}
