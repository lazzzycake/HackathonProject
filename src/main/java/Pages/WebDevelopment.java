package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class WebDevelopment {
    public WebDriver driver;

    public WebDevelopment(WebDriver driver){
        this.driver=driver;
    }

    Actions actions = new Actions(driver);

    @FindBy(xpath="//div[contains(text(),'Level')]")
    WebElement levelDropdown;

    @FindBy(xpath = "//span[text()='View']")
    WebElement view ;

    @FindBy(xpath="//span[text()='Beginner']")
    WebElement beginnerBtn;

    @FindBy(xpath = "//div[contains(text(),'Language')]")
    WebElement langBtn;

    @FindBy(xpath = "//span[text()='English']")
    WebElement englishBtn;

    public void setLevel(){
        actions.scrollToElement(levelDropdown).perform();
        levelDropdown.click();
        beginnerBtn.click();
        view.click();
    }

    public void setLanguage(){
        langBtn.click();
        englishBtn.click();
        view.click();
    }
}
