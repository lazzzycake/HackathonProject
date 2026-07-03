package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    By search = By.name("query");
    By searchIcon = By.xpath("//button[@type='submit']//*[name()='svg']");

    public void search(String searchText){
        WebElement searchBox = driver.findElement(search);
        searchBox.sendKeys(searchText);
        driver.findElement(searchIcon).click();
    }
}
