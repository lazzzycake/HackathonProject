package Utilities;

import Base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class screenShotUtil {

    public static void captureScreenShot(String testName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination = new File("./Screenshots/" + testName + "_" + System.currentTimeMillis() + ".png");
        FileUtils.copyFile(source, destination);

    }
}