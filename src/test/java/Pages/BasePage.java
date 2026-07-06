package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    WebDriver driver;
    Actions actions;
    JavascriptExecutor js;
    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }
}
