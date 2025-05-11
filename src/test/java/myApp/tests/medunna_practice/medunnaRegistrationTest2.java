package myApp.tests.medunna_practice;

import myApp.pages.MedunnaHomePage;
import myApp.pages.MedunnaRegistrationPage;
import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import myApp.utilities.ExtentReportsListener;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;

@Listeners(ExtentReportsListener.class)
public class medunnaRegistrationTest2 {


    protected WebDriver driver;
    MedunnaHomePage medunnaHomePage;
    MedunnaRegistrationPage medunnaRegistrationPage;


    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        medunnaHomePage = new MedunnaHomePage();
        medunnaRegistrationPage = new MedunnaRegistrationPage();

        // Start the process of generating Extent Reports

        // Test steps common for ALL tests can be executed through  @BeforeMethod
        // User navigates to Medunna Homepage
        Driver.getDriver().get(ConfigReader.getProperty("medunna_url"));

        ExtentReportsListener.extentTestPass("User navigates to Medunna Homepage");
        //ExtentReportsListener.addScreenShotToReport(); // error => extentTest object hasn't been initialized yet. @BeforeMethod runs before onTestStart() (where extentTest gets created) So extentTest is still null during setUp()

        // User clicks on userIcon
        medunnaHomePage.userIcon.click();
        waitFor(1);
        // User chooses register option
        medunnaHomePage.registerOption.click();
        waitFor(1);
    }

    @AfterMethod
    public void tearDown() {
         Driver.closeDriver();

    }

    @Test
    public void medunnaRegistrationTest1(){
        //        AC001: There should be a valid SSN respecting the "-" after 3rd and 5th digits, it should be 9 digits long
        //        AC001TC01: User enters the ssn 22255-5432, there should be "Your SSN is invalid" message

        ExtentReportsListener.addScreenShotToReport();

        // User enters ssn number
        medunnaRegistrationPage.ssnInputField.sendKeys("22255-5432");
        waitFor(2);

        ExtentReportsListener.extentTestPass("User enters ssn number : 22255-5432");

        ExtentReportsListener.addScreenShotToReport();

        // Logic: We need to click at some other element to see if the error message is displayed or not
        //1st way: click on another webElement
        medunnaRegistrationPage.firstNameInputField.click();
        waitFor(2);
        //ExtentReportUtils.info("User clicks on the next input field");
        // 2nd way: Use Keys.TAB
        // medunnaRegistrationPage.ssnInputField.sendKeys("22255-5432", Keys.TAB);
        try {
            Assert.assertFalse(medunnaRegistrationPage.invalidSsnMessage.isDisplayed());
            waitFor(2);
        }catch (Throwable t) {
            ExtentReportsListener.addScreenShotToReport();
            String errorMessage = medunnaRegistrationPage.invalidSsnMessage.getText();

            // TO REPORT FAIL
            ExtentReportsListener.extentTestFail(errorMessage + "! error message is displayed");
        }
    }



}
