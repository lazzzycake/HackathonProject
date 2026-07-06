package Pages;

import Utilities.ExcelUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.util.List;

public class LanguageLearning extends BasePage {

    HomePage page;
    private int languageCount;
    private int levelCount;

    public LanguageLearning(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-testid = 'filter-view-button']")
    WebElement viewButton;

    @FindBy(xpath = "//button[@data-testid='filter-dropdown-language']")
    WebElement languageDropdown;

    @FindBy(xpath = "//div[contains(text(),'Level')]")
    WebElement levelDropdown;

    @FindBy(xpath = "//div[contains(@data-testid,'language:')]")
    List<WebElement> languageList;

    @FindBy(xpath = "//div[contains(@data-testid,'productDifficultyLevel')]")
    List<WebElement> levelList;

    public void openLanguageSection() {
        actions.scrollToElement(languageDropdown).perform();
        languageDropdown.click();
    }

    public void displayLanguages() throws IOException {
        languageCount = languageList.size();

        String filePath = System.getProperty("user.dir") + "/DataRetrieved/Languages.xlsx";
        ExcelUtility excel = new ExcelUtility(filePath);

        // Write headers
        excel.writeCell("Sheet1", 0, 0, "Language");
        excel.writeCell("Sheet1", 0, 1, "Course Count");

        int rowNum = 1;
        for (WebElement language : languageList) {
            String text = language.getText().trim();
            if (!text.isEmpty()) {

                String name = text;
                String count = "";

                if (text.contains("(") && text.contains(")")) {
                    name  = text.substring(0, text.indexOf("(")).trim();
                    count = text.substring(text.indexOf("(") + 1, text.indexOf(")")).trim();
                }

                excel.writeCell("Sheet1", rowNum, 0, name);   // Column A → English
                excel.writeCell("Sheet1", rowNum, 1, count);  // Column B → 11,410
                rowNum++;
            }
        }
        viewButton.click();
    }

    public int getLanguageSize() {
        return languageCount;
    }

    public void openLevelSection() {
        js.executeScript("arguments[0].scrollIntoView(false);", levelDropdown);
        levelDropdown.click();
    }

    public void displayLevels() throws IOException {
        levelCount = levelList.size();

        String filePath = System.getProperty("user.dir") + "/DataRetrieved/Levels.xlsx";
        ExcelUtility excel = new ExcelUtility(filePath);

        excel.writeCell("Sheet1", 0, 0, "Level");
        excel.writeCell("Sheet1", 0, 1, "Course Count");

        int rowNum = 1;
        for (WebElement level : levelList) {
            String text = level.getText().trim();
            if (!text.isEmpty()) {
                String name = text;
                String count = "";

                if (text.contains("(") && text.contains(")")) {
                    name  = text.substring(0, text.indexOf("(")).trim();
                    count = text.substring(text.indexOf("(") + 1, text.indexOf(")")).trim();
                }

                excel.writeCell("Sheet1", rowNum, 0, name);
                excel.writeCell("Sheet1", rowNum, 1, count);
                rowNum++;
            }
        }
    }

    public int getLevelCount() {
        return levelCount;
    }
}