package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class Enterprise extends BasePage {

   public Enterprise(WebDriver driver){
       super(driver);
   }

   Select select;

   @FindBy(linkText = "For Businesses")
   WebElement enterprise;

   @FindBy(id = "Email")
   WebElement Email;

   @FindBy(id= "FirstName")
   WebElement firstName;

   @FindBy(id="LastName")
   WebElement lastName;

   @FindBy(xpath = "//input[@id='Phone']")
   WebElement phone;

   @FindBy(id = "rentalField9")
   WebElement organizationType;

   @FindBy(xpath = "//input[@id='Title']")
   WebElement jobTitle;

   @FindBy(xpath = "//input[@id='Company']")
   WebElement company;

   @FindBy(xpath = "//select[@id='Employee_Range__c']")
   WebElement size;

   @FindBy(xpath = "//select[@id='Self_Reported_Needs__c']")
   WebElement describe;

   @FindBy(xpath ="//select[@id='Country']" )
   WebElement Country;

   @FindBy(xpath = "//button[@type='submit']")
   WebElement submit;

   @FindBy(xpath = "//div[@id='ValidMsgEmail']")
   WebElement emailErrorMessage;

   public void clickEnterprise(){
       actions.moveToElement(enterprise).click().perform();
   }

   public void enterFirstName(String fname){
       firstName.sendKeys(fname);
   }

   public void enterLastName(String lname){
       lastName.sendKeys(lname);
   }

   public void enterEmail(String email){
       Email.sendKeys(email);
   }

   public void enterPhoneNumber(String phoneNo){
       phone.sendKeys(phoneNo);
   }

   public void enterOrganizationType(String orgType){
       select = new Select(organizationType);
       select.selectByValue(orgType);
   }

   public void enterJobTitle(String title){
       jobTitle.sendKeys(title);
   }

   public void enterCompanyName(String cmpName){
       company.sendKeys(cmpName);
   }

   public void enterCompanySize(String cmpSize){
       select = new Select(size);
       select.selectByValue(cmpSize);
   }

   public void enterDescription(String desc){
       select = new Select(describe);
       select.selectByValue(desc);
   }

   public void enterCountry(String country){
       select = new Select(Country);
       select.selectByValue(country);
   }

//    public void formFill(String fn , String ln , String email, String phoneNo , String organization, String Title, String companyName , String companySize , String need, String country) {
//
//       actions.scrollToElement(firstName).perform();
//       firstName.sendKeys(fn);
//       lastName.sendKeys(ln);
//       Email.sendKeys(email);
//       phone.sendKeys(phoneNo);
//
//       Select select  = new Select(organizationType);
//       select.selectByValue(organization);
//
//       jobTitle.sendKeys(Title);
//       company.sendKeys(companyName);
//
//       select  = new Select(size);
//       select.selectByVisibleText(companySize);
//
//       select = new Select(describe);
//       select.selectByVisibleText(need);
//
//       select = new Select(Country);
//       select.selectByVisibleText(country);
//
//   }

   public void submitForm(){
       actions.scrollToElement(submit).perform();
       js.executeScript("arguments[0].click();", submit);
   }

   public String errorMessage(){
       return emailErrorMessage.getText();
   }

}
