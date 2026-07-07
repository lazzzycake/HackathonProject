package listener;

import Base.BaseTest;
import Utilities.ScreenShotUtil;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TestListener implements ITestListener {

private static final Logger logger = LogManager.getLogger(TestListener.class);


@Override
public void onTestStart(ITestResult result){
logger.info("starting Test :" + result.getName());
}

@Override
public void onTestSuccess(ITestResult result){
logger.info("test successfully completed :" + result.getName());
}

@Override
public void onTestFailure(ITestResult result) {
 Object currentClass = result.getInstance();

    WebDriver driver =
            ((BaseTest) currentClass).driver;

logger.error("test failed :" + result.getName());
try{
ScreenShotUtil.captureScreenShot(driver,result.getName());
logger.info("Screenshot captured");

}
catch(Exception e){
 logger.error("Unable to capture Screenshot:" , e);
}

}
@Override
public void onTestSkipped(ITestResult result) {
    logger.warn("test case skipped :" + result.getName());
}

}
