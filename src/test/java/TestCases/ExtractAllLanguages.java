package TestCases;

import Pages.HomePage;
import Base.BaseTest;
import Pages.LanguageLearning;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ExtractAllLanguages extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    HomePage homePage;
    LanguageLearning learning;


    @Test(priority = 1)
    public void verifySearch() {
        logger.info("starting verifySearch");
        homePage = new HomePage(driver);
        homePage.search("Language Learning");
        softAssert.assertTrue(driver.getPageSource().toLowerCase().contains("language"), "the search was not displayed");
        logger.info("verified verifySearch");
    }

    @Test(priority = 2, dependsOnMethods = "verifySearch")
    public void verifyLanguageExtracted() throws IOException {
        logger.info("starting verifyLanguageExtracted");
        learning  = new LanguageLearning(driver);
        learning.openLanguageSection();
        learning.displayLanguages();
        softAssert.assertTrue(learning.getLanguageSize() > 0 , "languages were not extracted");
        softAssert.assertAll();
        logger.info("verified verifyLanguageExtracted");
    }

   @Test(priority =  3 , dependsOnMethods = "verifySearch")
   public void verifyLevelExtracted() throws IOException {
       logger.info("starting verifyLevelExtracted");
       learning  = new LanguageLearning(driver);
       learning.openLevelSection();
       learning.displayLevels();
       softAssert.assertTrue(learning.getLevelCount() > 0, "levels not extracted");
       softAssert.assertAll();
       logger.info("verified verifyLevelExtracted");
   }
}
