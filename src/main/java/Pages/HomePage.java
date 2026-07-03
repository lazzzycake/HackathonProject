package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

//    By search = By.name("query");
//    By searchIcon = By.xpath("//button[@type='submit']//*[name()='svg']");

    @FindBy(name = "query")
    WebElement searchBox;

    @FindBy(xpath = "//button[@type='submit']//*[name()='svg']")
    WebElement searchIcon;

    public void search(String searchText){
//        WebElement searchBox = driver.findElement(search);
        searchBox.sendKeys(searchText);
        searchIcon.click();
    }
}
