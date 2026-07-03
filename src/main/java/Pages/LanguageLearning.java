package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LanguageLearning {

    WebDriver driver;
    HomePage page;
    public LanguageLearning(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(),'Language')]")
    WebElement languageDropdown;

    @FindBy(xpath = "//div[contains(text(),'Level')]")
    WebElement levelDropdown;

    // Update these locators after inspecting the page
    @FindBy(xpath = "//div[contains(@data-testid,'language:')]")
    List<WebElement> languageList;

    @FindBy(xpath = "//div[contains(@data-testid,'productDifficultyLevel')]")
    List<WebElement> levelList;



    public void openLanguageSection(){
        languageDropdown.click();
    }

    public void openLevelSection(){
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