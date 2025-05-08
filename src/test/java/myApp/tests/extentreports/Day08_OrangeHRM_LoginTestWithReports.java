package myApp.tests.extentreports;

import myApp.pages.OrangeHRM_DashBoardPage;
import myApp.pages.OrangeHRM_HomePage;
import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import myApp.utilities.ExtentReportUtils;
import myApp.utilities.LoggerUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertTrue;

public class Day08_OrangeHRM_LoginTestWithReports {

     /*
    When user navigates to https://opensource-demo.orangehrmlive.com/web/index.php/auth/login homepage
    And User enters Username
    And User enters password
    And User clicks on Login button
    Verify the login is successful
    And User logs out
    Verify the logout is successful

     */

    /*
    ADD EXTENT REPORTS CAPABILITY
    1. use createTestReport(String name,String description) first for creating extent report capability
    2. then use any method to mark as pass, info, fail
    3. Then use flush to generate the report

    ADD LOGGER REPORT CAPABILITY
    1. use any method from LoggerUtils directly.

 */


    protected WebDriver driver;

    OrangeHRM_HomePage orangeHRMHomePage;
    OrangeHRM_DashBoardPage orangeDashBoardPage;

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        orangeHRMHomePage = new OrangeHRM_HomePage();
        orangeDashBoardPage = new OrangeHRM_DashBoardPage();
    }

    @Test
    public void orangeHRMTest(){

        // Using Logger Utils
        LoggerUtils.info("Test case begins...");

        // Using ExtentReportUtils
        // 1. Call the createTestReport() to start the process
        ExtentReportUtils.createTestReport("Smoke test Report", "User Login");

        // 2.
        ExtentReportUtils.info("Test case begins...");

        //3.
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        ExtentReportUtils.pass("User is on the Homepage");

        //4.
//        And User enters Username
        orangeHRMHomePage.usernameInput.sendKeys(ConfigReader.getProperty("orange_username"));
        waitFor(1);
        ExtentReportUtils.info("User enters Username");
//        And User enters password
        orangeHRMHomePage.passwordInput.sendKeys(ConfigReader.getProperty("orange_pass"));
        waitFor(1);
        ExtentReportUtils.info("User enters password");
//        And User clicks on Login button
        orangeHRMHomePage.loginButton.click();
        waitFor(1);
        ExtentReportUtils.info("User clicks on login button");


//        Verify the login is successful
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
        waitFor(1);
        ExtentReportUtils.pass("Log in is successful!");

//        And User logs out
        orangeDashBoardPage.userProfile.click();
        waitFor(1);
        ExtentReportUtils.info("User clicks on profile button");

        // We made the test fail at logoutOption.click() step ON PURPOSE by giving wrong locator

        try{
            orangeDashBoardPage.logoutOption.click();
        }catch (NoSuchElementException e){
            waitFor(1);
            ExtentReportUtils.fail("User clicks on logout button");
            ExtentReportUtils.flush();
            e.getMessage();
        }



//        Verify the logout is successful
//        assertTrue(orangeHRMHomePage.loginButton.isDisplayed());
//        assertTrue(driver.getCurrentUrl().contains("login"));
//
//        ExtentReportUtils.pass("Log out is successful!");
//
//        // MANDATORY STEP:
//        ExtentReportUtils.flush();
//
//        LoggerUtils.info("Test completed!");


    }

    @AfterMethod
    public void tearDown(){
        System.out.println("AfterMethod: closing driver");
        Driver.closeDriver();
    }

}
