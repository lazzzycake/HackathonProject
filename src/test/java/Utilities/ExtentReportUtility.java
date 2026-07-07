package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtility implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;

    String repName;

    // ================== onStart ==================
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);

        sparkReporter.config().setDocumentTitle("Coursera Automation Report");
        sparkReporter.config().setReportName("Hackathon Test Reports");
        sparkReporter.config().setTheme(Theme.DARK);

        reports = new ExtentReports();
        reports.attachReporter(sparkReporter);
        reports.setSystemInfo("Application", "Coursera");
        reports.setSystemInfo("Module", "Admin");
        reports.setSystemInfo("UserId", System.getProperty("user.name"));
        reports.setSystemInfo("Environment", "QEA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        reports.setSystemInfo("Operating System", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        reports.setSystemInfo("Browser", browser);
    }

    // ================== onTestSuccess ==================
    public void onTestSuccess(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getMethodName());
        test.log(Status.PASS, result.getName() + " passed");
    }

    // ================== onTestFailure ==================
    public void onTestFailure(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        // ---- Screenshot capture + attach to report ----
        try {
            WebDriver driver = getDriverFromTest(result);
            if (driver != null) {
                String screenshotPath = captureScreenShot(driver, result.getName());
                test.addScreenCaptureFromPath(screenshotPath);
            }
        } catch (Exception e) {
            test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
        }
    }

    // ================== onTestSkipped ==================
    public void onTestSkipped(ITestResult result) {
        test = reports.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " skipped");
        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    // ================== onFinish ==================
    public void onFinish(ITestContext context) {
        reports.flush();
    }

    // ================== Screenshot helper (from ScreenShotUtil) ==================
    public static String captureScreenShot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destPath = System.getProperty("user.dir") + "/Screenshots/"
                + testName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(destPath);
        FileUtils.copyFile(source, destination);

        return destPath;
    }

    // ================== Reflection helper to get driver from test class ==================
    private WebDriver getDriverFromTest(ITestResult result) {
        try {
            Object currentClass = result.getInstance();
            Class<?> clazz = currentClass.getClass();

            // Walk up the class hierarchy (BaseTest usually holds the driver)
            while (clazz != null) {
                for (Field field : clazz.getDeclaredFields()) {
                    if (WebDriver.class.isAssignableFrom(field.getType())) {
                        field.setAccessible(true);
                        return (WebDriver) field.get(currentClass);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            System.out.println("Could not extract driver: " + e.getMessage());
        }
        return null;
    }
}