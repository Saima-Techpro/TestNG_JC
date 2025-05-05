package myApp.tests.smoketests;

import myApp.pages.OrangeHRM_DashBoardPage;
import myApp.pages.OrangeHRM_HomePage;
import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertTrue;

public class Day03_OrangeHRM_LoginDynamicTest {

     /*
    When user navigates to https://opensource-demo.orangehrmlive.com/web/index.php/auth/login homepage
    And User enters Username
    And User enters password
    And User clicks on Login button
    Verify the login is successful
    And User logs out
    Verify the logout is successful

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
    public void orangeHRMDynamicTest(){
//        When user navigates to the homepage ( @BeforeMethod )
        driver.get(ConfigReader.getProperty("orange_url"));
//        And User enters Username
//        And User enters password
//        And User clicks on Login button

        orangeHRMHomePage.login(ConfigReader.getProperty("orange_username"), ConfigReader.getProperty("orange_pass"));

//        Verify the login is successful
        assertTrue(driver.getCurrentUrl().contains("dashboard"));
        waitFor(1);

//        And User logs out
        orangeDashBoardPage.userProfile.click();
        waitFor(1);
        orangeDashBoardPage.logoutOption.click();
        waitFor(1);
//        Verify the logout is successful
        assertTrue(orangeHRMHomePage.loginButton.isDisplayed());
        assertTrue(driver.getCurrentUrl().contains("login"));


    }

    @AfterMethod
    public void tearDown(){
        System.out.println("AfterMethod: closing driver");
         Driver.closeDriver();
    }

}
