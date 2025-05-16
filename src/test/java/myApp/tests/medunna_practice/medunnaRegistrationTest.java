package myApp.tests.medunna_practice;

import myApp.pages.MedunnaHomePage;
import myApp.pages.MedunnaRegistrationPage;
import myApp.utilities.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static myApp.utilities.WaitUtils.waitFor;


public class medunnaRegistrationTest {

    /*

//US001: Registration should be available using SSN, Firstname and Lastname
//        AC001: There should be a valid SSN respecting the "-" after 3rd and 5th digits, it should be 9 digits long
//            AC001TC01: User enters the ssn 22255-5432, there should be "Your SSN is invalid" message
//            AC001TC02: User enters the ssn 222-555432, there should be "Your SSN is invalid" message
//            AC001TC03: User enters the ssn 222-55-543, there should be "Your SSN is invalid" message
//            AC001TC04: User enters the ssn 222-55-543a, there should be "Your SSN is invalid" message
//            AC001TC05: User enters the ssn 222-55-5432, there should not be any error message
//        AC002: SSN cannot be left blank
//            AC002TC01: User leaves the ssn blank, there should be "Your SSN is required." message
//            AC002TC02: User enters the ssn only space, there should be "Your SSN is required." message
//            AC002TC03: User enters the ssn 222-55-5432, there should not be any error message
//        AC003: There should be a valid name that contains any chars and cannot be blank
//            AC003TC01: User leaves the FirstName blank, there should be "Your FirstName is required." message
//            AC003TC02: User enters the FirstName only space, there should be "Your FirstName is required." message
//            AC003TC03: User enters the name that contains any chars, there should not be any error message
//        AC004: There should be a valid lastname that contains any chars and it is a required field
//            AC004TC01: User leaves the lastname blank, there should be "Your LastName is required." message
//            AC004TC02: User enters the lastname only space, there should be "Your LastName is required." message
//            AC004TC03: User enters the lastname that contains any chars, there should not be any error message

     */

    protected WebDriver driver;
    MedunnaHomePage medunnaHomePage;
    MedunnaRegistrationPage medunnaRegistrationPage;


    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        medunnaHomePage = new MedunnaHomePage();
        medunnaRegistrationPage = new MedunnaRegistrationPage();
        ExtentReportUtils.createTestReport("Medunna", "Registration Page");

        // Start the process of generating Extent Reports
        //ExtentReportUtils.createTestReport("Registration Test", "Testing the behaviour of SSN input field.");
        // Test steps common for ALL tests can be executed through  @BeforeMethod
        // User navigates to Medunna Homepage
        Driver.getDriver().get(ConfigReader.getProperty("medunna_url"));
        ExtentReportUtils.passAndCaptureScreenshot("User navigates to Medunna Homepage");
        // User clicks on userIcon
        medunnaHomePage.userIcon.click();
        waitFor(1);
        // User chooses register option
        medunnaHomePage.registerOption.click();
        waitFor(1);
    }

    @AfterMethod
    public void tearDown() {
        ExtentReportUtils.flush();  // MANDATORY for report generation
         Driver.closeDriver();

    }

    @Test
    public void medunnaRegistrationTest1(){
        //        AC001: There should be a valid SSN respecting the "-" after 3rd and 5th digits, it should be 9 digits long
        //        AC001TC01: User enters the ssn 22255-5432, there should be "Your SSN is invalid" message

        // User enters ssn number
        medunnaRegistrationPage.ssnInputField.sendKeys("22255-5432");
        waitFor(2);
        ExtentReportUtils.passAndCaptureScreenshot("User enters ssn number : 22255-5432");

        // Logic: We need to click at some other element to see if the error message is displayed or not
        //1st way: click on another webElement
        medunnaRegistrationPage.firstNameInputField.click();
        waitFor(2);
        //ExtentReportUtils.info("User clicks on the next input field");
        // 2nd way: Use Keys.TAB
        // medunnaRegistrationPage.ssnInputField.sendKeys("22255-5432", Keys.TAB);

        Assert.assertTrue(medunnaRegistrationPage.invalidSsnMessage.isDisplayed());
        waitFor(2);

        String errorMessage = medunnaRegistrationPage.invalidSsnMessage.getText();
        ExtentReportUtils.passAndCaptureScreenshot(errorMessage+ "! error message is displayed");



    }



    @Test
    public void medunnaRegistrationTest2(){
        //        AC001: There should be a valid SSN respecting the "-" after 3rd and 5th digits, it should be 9 digits long
        //        AC001TC02: User enters the ssn 222-555432, there should be "Your SSN is invalid" message


        // User enters ssn number
        medunnaRegistrationPage.ssnInputField.sendKeys("222-555432");
        waitFor(2);
        ExtentReportUtils.passAndCaptureScreenshot("User enters ssn number : 222-555432");

        // Logic: We need to click at some other element to see if the error message is displayed or not
        //1st way: click on another webElement
//        medunnaRegistrationPage.firstNameInputField.click();
//        waitFor(1);

        // 2nd way: Use Keys.TAB
        medunnaRegistrationPage.ssnInputField.sendKeys("222-555432", Keys.TAB);
        ExtentReportUtils.info("User clicks on the next input field");
        waitFor(2);

        Assert.assertTrue(medunnaRegistrationPage.invalidSsnMessage.isDisplayed());
        waitFor(2);

        String errorMessage = medunnaRegistrationPage.invalidSsnMessage.getText();
        ExtentReportUtils.passAndCaptureScreenshot(errorMessage+ "! error message is displayed");



    }

    @Test
    public void medunnaRegistrationTest3(){
        //        AC001: There should be a valid SSN respecting the "-" after 3rd and 5th digits, it should be 9 digits long
        //        AC001TC03: User enters the ssn 222-55-543, there should be "Your SSN is invalid" message


        // User enters ssn number
        medunnaRegistrationPage.ssnInputField.sendKeys("222-55-543");
        waitFor(2);
        ExtentReportUtils.passAndCaptureScreenshot("User enters ssn number : 222-55-543");

        // Logic: We need to click at some other element to see if the error message is displayed or not
        //1st way: click on another webElement
        medunnaRegistrationPage.firstNameInputField.click();
        waitFor(2);

        // 2nd way: Use Keys.TAB
        // medunnaRegistrationPage.ssnInputField.sendKeys("222-55-543", Keys.TAB);

        ExtentReportUtils.info("User clicks on the next input field");
        waitFor(2);


        Assert.assertTrue(medunnaRegistrationPage.invalidSsnMessage.isDisplayed());
        waitFor(2);

        String errorMessage = medunnaRegistrationPage.invalidSsnMessage.getText();
        ExtentReportUtils.passAndCaptureScreenshot(errorMessage+ "! error message is displayed");



    }

}
