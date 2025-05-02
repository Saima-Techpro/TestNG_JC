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

public class Day03_OrangeHRM_LoginTest {

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

    OrangeHRM_HomePage orangeHRMHomePage = new OrangeHRM_HomePage();
    OrangeHRM_DashBoardPage orangeDashBoardPage = new OrangeHRM_DashBoardPage();

    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
    }

    @Test
    public void orangeHRMTest(){
//        When user navigates to the homepage ( @BeforeMethod )
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        And User enters Username
        orangeHRMHomePage.usernameInput.sendKeys(ConfigReader.getProperty("orange_username"));
        waitFor(1);
//        And User enters password
        orangeHRMHomePage.passwordInput.sendKeys(ConfigReader.getProperty("orange_pass"));
        waitFor(1);
//        And User clicks on Login button
        orangeHRMHomePage.loginButton.click();
        waitFor(1);
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
        Driver.closeDriver();
    }

}
