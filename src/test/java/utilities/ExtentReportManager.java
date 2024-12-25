package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Ensure the reports directory exists
        new File("src/test/resources/reports").mkdirs();

        sparkReporter = new ExtentSparkReporter("src/test/resources/reports/" + repName);

        sparkReporter.config().setDocumentTitle("SwagLabs Automation Report");
        sparkReporter.config().setReportName("SwagLabs Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Swag Labs");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os != null ? os : "Unknown OS");

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser != null ? browser : "Unknown Browser");

        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (includedGroups != null && !includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.PASS, result.getName() + " got successfully executed");
        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");

        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath, "Screenshot on Failure");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathOfExtentReport = "src/test/resources/reports/" + repName;
        File extentReport = new File(pathOfExtentReport);

        if (extentReport.exists()) {
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(extentReport.toURI());
                } else {
                    System.out.println("Desktop not supported. Report at: " + extentReport.getAbsolutePath());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Report file not found at: " + extentReport.getAbsolutePath());
        }
    }
}
