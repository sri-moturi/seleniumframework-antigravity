package com.tecskool.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tecskool.reports.ExtentReportManager;
import com.tecskool.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);
    private final ExtentReports extent = ExtentReportManager.getReporterObject();
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite '{}' execution started.", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite '{}' execution completed. Writing report...", context.getName());
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Running test method: {}", result.getMethod().getMethodName());
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test method '{}' PASSED.", result.getMethod().getMethodName());
        test.log(Status.PASS, "Test execution completed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test method '{}' FAILED. Exception: {}", result.getMethod().getMethodName(), result.getThrowable());
        test.log(Status.FAIL, "Test failed: " + result.getThrowable());
        
        String path = ScreenshotUtils.captureScreenshot(result.getName());
        if (path != null) {
            test.addScreenCaptureFromPath(path, result.getName());
            logger.info("Screenshot successfully linked to Extent Report.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test method '{}' SKIPPED. Reason: {}", result.getMethod().getMethodName(), result.getThrowable());
        test.log(Status.SKIP, "Test execution skipped: " + result.getThrowable());
    }
}
