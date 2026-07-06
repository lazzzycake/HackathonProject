package TestCases;

import Base.BaseTest;
import Pages.HomePage;
import Pages.WebDevelopment;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SearchWebDevelopment extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    HomePage homePage;
    WebDevelopment webDevelopment;

    @Test(priority = 1)
    public void verifyWebsiteLaunch(){
        logger.info("Starting verifyWebsiteLaunch");
        softAssert.assertTrue(driver.getTitle().contains("Coursera"), "Coursera website is not launched");
        softAssert.assertAll();
        logger.info("Verified verifyWebsiteLaunch");
    }

    @Test(priority = 2,dependsOnMethods = "verifyWebsiteLaunch")
    public void verifySearchForWebDevelopmentCourses(){
        logger.info("starting verify SearchForWebDevelopmentCourses");
        homePage = new HomePage(driver);
        homePage.search("Web Development");
        softAssert.assertTrue(driver.getPageSource().toLowerCase().contains("web development"),"The search results are Web Development courses.");
        softAssert.assertAll();
        logger.info("verified SearchForWebDevelopmentCourses");
    }

    @Test(priority = 3, dependsOnMethods = "verifySearchForWebDevelopmentCourses", groups = "filters")
    public void verifyBeginnerLevel(){
        logger.info("starting BeginnerLevel");
        webDevelopment = new WebDevelopment(driver);
        webDevelopment.setLevel();
        softAssert.assertTrue(driver.getPageSource().toLowerCase().contains("beginner"),"The level is set to beginner.");
        logger.info("verified verifyBeginnerLevel");
    }

    @Test(priority = 4,dependsOnMethods = "verifySearchForWebDevelopmentCourses", groups = "filters")
    public void verifyLanguageLevel(){
        logger.info(" starting verifyLanguageLevel");
        webDevelopment = new WebDevelopment(driver);
        webDevelopment.setLanguage();
        softAssert.assertTrue(driver.getPageSource().toLowerCase().contains("english"),"The language is set to English.");
        softAssert.assertAll();
        logger.info("verified verifyLanguageLevel");
    }

    @Test( dependsOnGroups = "filters")
    public void verifyNamesExtracted(){
        logger.info("starting verifyNamesExtracted");
        webDevelopment = new WebDevelopment(driver);
        List<String> names = webDevelopment.getCourseName();
        softAssert.assertEquals(names.size(), 2);
        softAssert.assertAll();
        logger.info("verified verifyNamesExtracted");
    }

    @Test(dependsOnGroups = "filters")
    public void verifyRatingExtracted(){
        logger.info("starting verifyRatingExtracted");
        webDevelopment = new WebDevelopment(driver);
        List<String> ratingList = webDevelopment.getRatings();
        softAssert.assertEquals(ratingList.size(),2);
        System.out.println(ratingList.get(0));
        System.out.println(ratingList.get(1));
        softAssert.assertAll();
        logger.info("verified verifyRatingExtracted");
    }

    @Test(dependsOnGroups = "filters")
    public void verifyDurationOfCourse(){
        logger.info("starting verifyDurationOfCourse");
        webDevelopment = new WebDevelopment(driver);
        List<String> duration = webDevelopment.getLearningHours();
        softAssert.assertEquals(duration.size(),2);
        System.out.println(duration.get(0));
        System.out.println(duration.get(1));
        softAssert.assertAll();
        logger.info("verified verifyDurationOfCourse");
    }
}
