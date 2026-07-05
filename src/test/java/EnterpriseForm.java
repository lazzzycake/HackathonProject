import Base.BaseTest;
import Pages.Enterprise;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EnterpriseForm extends BaseTest {

    SoftAssert softassert = new SoftAssert();
    Enterprise enterprise;

    @Test(priority = 1)
    public void verifyEnterprise(){
    enterprise = new Enterprise(driver);
    enterprise.clickEnterprise();
    softassert.assertTrue( driver.getCurrentUrl().contains("business"), "For business page not displayed");
        softassert.assertAll();
    }

    @Test(priority = 2 , dependsOnMethods = "verifyEnterprise")
    public void verifyFormFill(){
    enterprise.formFill("Aditi" , "kushwaha" , "123" , "6768904323","Business" ,"Tester" , "Cognizant", "501-1000","Courses for myself", "India");
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
