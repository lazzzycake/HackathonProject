package Base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;

    @BeforeClass
    @Parameters({"os","browser"})
    public void setup(String os, String browser) throws IOException {
        FileReader fileReader = new FileReader("./src/test/resources/config.properties");
        properties=new Properties();
        properties.load(fileReader);

        logger= LogManager.getLogger(this.getClass());

        switch(browser.toLowerCase()){
            case "chrome":
                driver=new ChromeDriver();
                break;

            case "firefox":
                driver=new FirefoxDriver();
                break;

            case "edge":
                driver=new EdgeDriver();
                break;

            default:
                System.out.println("Invalid Browser");
                return;
        }

        driver.get(properties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
