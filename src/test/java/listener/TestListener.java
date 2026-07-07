package listener;

import Base.BaseTest;
import Utilities.ScreenShotUtil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    public ExtentSparkReporter sparkReporter;
    public ExtentReports reports;
    public ExtentTest test;

    String repName;

    // ================== onStart ==================
    @Override
    public void onStart(ITestContext testContext) {
        logger.info("Test Suite started: " + testContext.getName());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);

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

    // ================== onTestStart ==================
    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting Test: " + result.getName());
        test = reports.createTest(result.getTestClass().getName() + " :: " + result.getName());
    }

    // ================== onTestSuccess ==================
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test successfully completed: " + result.getName());
        test.assignCategory(result.getMethod().getMethodName());
        test.log(Status.PASS, result.getName() + " passed");
    }

    // ================== onTestFailure ==================
    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName());
        test.assignCategory(result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        // ---- Screenshot ----
        try {
            Object currentClass = result.getInstance();
            WebDriver driver = ((BaseTest) currentClass).driver;

            String screenshotPath = ScreenShotUtil.captureScreenShot(driver, result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
            logger.info("Screenshot captured");
        } catch (Exception e) {
            logger.error("Unable to capture Screenshot: ", e);
            test.log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
        }
    }

    // ================== onTestSkipped ==================
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test case skipped: " + result.getName());
        test.assignCategory(result.getMethod().getMethodName());
        test.log(Status.SKIP, result.getName() + " skipped");
        if (result.getThrowable() != null) {
            test.log(Status.INFO, result.getThrowable().getMessage());
        }
    }

    // ================== onFinish ==================
    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite finished: " + context.getName());
        reports.flush();
    }
}