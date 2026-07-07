package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WebDevelopment extends BasePage{

    public WebDevelopment(WebDriver driver){
        super(driver);
    }

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

    @FindBy(xpath = "//div//a//h3")
    List<WebElement> courses;

    @FindBy(xpath = "//*[contains(text(),'4.')]")
    List<WebElement> ratings;

    @FindBy(xpath = "//div[@class='cds-CommonCard-metadata']/p")
    List<WebElement> timeData;

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

    public List<String> getCourseName(){
        List<String> courseNames = new ArrayList<>();
        for(int i=0;i<2;i++){
            String name = courses.get(i).getText();
            courseNames.add(name);
        }
        return courseNames;
    }

    public List<String> getRatings(){
        List<String> rating = new ArrayList<>();
        for(int i=0;i<2;i++){
            String rate=ratings.get(i).getText();
            rating.add(rate);
        }
        return rating;
    }

    public List<String> getLearningHours(){
        List<String> timeTaken = new ArrayList<>();
        for(int i=0;i<2;i++){
            String[] parts = timeData.get(i).getText().split("·");
            String time = parts[parts.length - 1].trim();
            timeTaken.add(time);
        }
        return timeTaken;
    }
}
