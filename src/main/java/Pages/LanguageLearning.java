package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LanguageLearning {

    WebDriver driver;
    HomePage page;
    Actions actions;
    public LanguageLearning(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions=new Actions(driver);
    }

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
        actions.scrollToElement(levelDropdown).perform();
        levelDropdown.click();
    }

    public void displayLanguages(){

        for(WebElement language : languageList){

            String text = language.getText();

            if(!text.isEmpty()){
                System.out.println(text);
            }
        }
    }

    public void displayLevels(){

        for(WebElement level : levelList){

            String text = level.getText();

            if(!text.isEmpty()){
                System.out.println(text);
            }
        }
    }

    public int getLanguageSize(){
        return languageList.size();
    }

    public int getLevelCount() {
        return levelList.size();
    }


}