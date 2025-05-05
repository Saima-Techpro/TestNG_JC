package myApp.tests.smoketests;

import myApp.pages.CarRental_HomePage;
import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertTrue;

public class Day03_CarRental_LoginTest {

    /*
    When user navigates to https://www.speedyli.com/ homepage
    Then User clicks on login option
    And User enters email address
    And User enters password
    And User clicks on Login button
    Verify the login in successful

     */

    CarRental_HomePage carRentalHomePage = new CarRental_HomePage();

    @Test (groups = "Regression Suite")
    public void carRentalTest(){
        // System.out.println("BeforeMethod: getting driver");
//        When user navigates to https://www.speedyli.com/ homepage
        Driver.getDriver().get("https://www.speedyli.com/");

//        Then User clicks on login option
        carRentalHomePage.loginOption.click();
        waitFor(1);

//        And User enters email address
        carRentalHomePage.emailInput.sendKeys(ConfigReader.getProperty("carRental_email"));
        waitFor(1);
//        And User enters password
        carRentalHomePage.passwordInput.sendKeys(ConfigReader.getProperty("carRental_password"));
        waitFor(1);
//        And User clicks on Login button
        carRentalHomePage.loginButton.click();
        waitFor(1);

//        Verify the login in successful
        assertTrue(carRentalHomePage.profileName.isDisplayed());
        waitFor(1);

        // Close the tab
        Driver.closeDriver();
    }

    @Test
    public void carRentalTest2(){
        //System.out.println("BeforeMethod: getting driver");
//        When user navigates to https://www.speedyli.com/ homepage
        Driver.getDriver().get("https://www.speedyli.com/");

//        Then User clicks on login option
        carRentalHomePage.loginOption.click();
        waitFor(1);

//        And User enters email address
        carRentalHomePage.emailInput.sendKeys(ConfigReader.getProperty("carRental_email"));
        waitFor(1);
//        And User enters password
        carRentalHomePage.passwordInput.sendKeys(ConfigReader.getProperty("carRental_password"));
        waitFor(1);
//        And User clicks on Login button
        carRentalHomePage.loginButton.click();
        waitFor(1);

//        Verify the login in successful
        assertTrue(carRentalHomePage.profileName.isDisplayed());
        waitFor(1);

        // Close the tab
        Driver.closeDriver();
    }
}
