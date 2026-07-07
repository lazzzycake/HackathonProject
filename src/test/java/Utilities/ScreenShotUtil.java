package Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

    public static String captureScreenShot(WebDriver driver, String testName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destPath = System.getProperty("user.dir") + "/Screenshots/"
                + testName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(destPath);
        FileUtils.copyFile(source, destination);

        return destPath;
    }
}