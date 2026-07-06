package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(name = "query")
    WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']//*[name()='svg']")
    WebElement searchIcon;

    public void search(String searchText){
        searchBox.sendKeys(searchText);
        searchIcon.click();
    }
}
