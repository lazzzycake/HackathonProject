package TestCases;

import Base.BaseTest;
import Pages.Enterprise;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EnterpriseForm extends BaseTest {

    SoftAssert softassert = new SoftAssert();
    Enterprise enterprise;

    @Test(priority = 1)
    public void verifyEnterprise(){
        logger.info("starting verifyEnterprise");
        enterprise = new Enterprise(driver);
        enterprise.clickEnterprise();
        softassert.assertTrue( driver.getCurrentUrl().contains("business"), "For business page not displayed");
        softassert.assertAll();
        logger.info("verified verifyEnterprise");
    }

    @Test(priority = 2 , dependsOnMethods = "verifyEnterprise")
    public void verifyFormFill() throws IOException {

        FileReader fileReader = new FileReader("./src/test/resources/config.properties");
        properties=new Properties();
        properties.load(fileReader);
        enterprise.enterFirstName(properties.getProperty("firstName"));
        enterprise.enterLastName(properties.getProperty("lastName"));
        enterprise.enterEmail(properties.getProperty("Email"));
        enterprise.enterPhoneNumber(properties.getProperty("phone"));
        enterprise.enterOrganizationType(properties.getProperty("organizationType"));
        enterprise.enterJobTitle(properties.getProperty("jobTitle"));
        enterprise.enterCompanyName(properties.getProperty("company"));
        enterprise.enterCompanySize(properties.getProperty("size"));
        enterprise.enterDescription(properties.getProperty("describe"));
        enterprise.enterCountry(properties.getProperty("Country"));
        enterprise.submitForm();
    }

    @Test(priority = 3 , dependsOnMethods = "verifyFormFill")
    public void verifyerrorMsg(){

        String actualMsg =   enterprise.errorMessage();
        System.out.println("actual error msg is" + actualMsg);
        softassert.assertTrue(actualMsg.contains("Must be valid email"),
                "Correct validation message not displayed");
        softassert.assertAll();

    }
}
