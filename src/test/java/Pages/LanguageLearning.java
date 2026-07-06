package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LanguageLearning extends BasePage{

//    WebDriver driver;
    HomePage page;
//    Actions actions;

    public LanguageLearning(WebDriver driver){
        super(driver);
//        actions=new Actions(driver);
    }
    @FindBy(xpath = "//button[@data-testid = 'filter-view-button']")
    WebElement viewButton;
    @FindBy(xpath = "//button[@data-testid='filter-dropdown-language']")
    WebElement languageDropdown;

    @FindBy(xpath = "//div[contains(text(),'Level')]")
    WebElement levelDropdown;

    // Update these locators after inspecting the page
    @FindBy(xpath = "//div[contains(@data-testid,'language:')]")
    List<WebElement> languageList;

    @FindBy(xpath = "//div[contains(@data-testid,'productDifficultyLevel')]")
    List<WebElement> levelList;



    public void openLanguageSection(){
        actions.scrollToElement(languageDropdown).perform();
        languageDropdown.click();

    }

    public void openLevelSection(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor  js =  (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);" ,levelDropdown);
        wait.until(ExpectedConditions.elementToBeClickable(levelDropdown));
        levelDropdown.click();
    }

    private int languageCount;

    public void displayLanguages(){

        languageCount = languageList.size();

        for(WebElement language : languageList){

            String text = language.getText();

            if(!text.isEmpty()){
                System.out.println(text);
            }
        }

        viewButton.click();
    }

    private int levelCount;
    public void displayLevels(){

        levelCount = levelList.size();

        for(WebElement level : levelList){

            String text = level.getText();

            if(!text.isEmpty()){
                System.out.println(text);
            }
        }
    }



    public int getLanguageSize(){
        return languageCount;
    }


    public int getLevelCount() {
        return levelCount;
    }


}