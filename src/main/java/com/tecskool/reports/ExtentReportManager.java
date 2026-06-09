package com.tecskool.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getReporterObject() {
        if (extent == null) {
            String reportPath = "test-output/ExtentReports/ExtentReport.html";
            File directory = new File("test-output/ExtentReports");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Practice Automation Execution Report");
            spark.config().setReportName("Login Functional Tests");
            spark.config().setTheme(Theme.DARK);
            
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Framework Type", "Page Object Model (POM)");
            extent.setSystemInfo("Environment", "Practice QA");
        }
        return extent;
    }
}
