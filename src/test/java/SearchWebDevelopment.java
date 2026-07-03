import Base.BaseTest;
import Pages.HomePage;
import Pages.WebDevelopment;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchWebDevelopment extends BaseTest {

    SoftAssert softAssert;
    HomePage homePage;
    WebDevelopment webDevelopment;

    @Test(priority = 1)
    public void verifyWebsiteLaunch(){
        Assert.assertTrue(driver.getTitle().contains("Coursera"),"Coursera website is launched");
    }

    @Test(priority = 2,dependsOnMethods = "verifyWebsiteLaunch")
    public void verifySearchForWebDevelopmentCourses(){
        homePage = new HomePage(driver);
        homePage.search("Web Development");
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("web development"),"The search results are Web Development courses.");
    }

    @Test(priority = 3, dependsOnMethods = "verifySearchForWebDevelopmentCourses")
    public void verifyBeginnerLevel(){
        webDevelopment = new WebDevelopment(driver);
        webDevelopment.setLevel();
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("beginner"),"The level is set to beginner.");
    }

    @Test(priority = 4,dependsOnMethods = "verifySearchForWebDevelopmentCourses")
    public void verifyLanguageLevel(){
        webDevelopment = new WebDevelopment(driver);
        webDevelopment.setLanguage();
        Assert.assertTrue(driver.getPageSource().toLowerCase().contains("english"),"The language is set to English.");
    }


}
