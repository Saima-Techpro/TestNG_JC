package myApp.pages;

import myApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarRental_HomePage {

    public CarRental_HomePage(){ // Constructor
        PageFactory.initElements(Driver.getDriver(), this);

    }

    /*
    SYNTAX of finding elements in TestNG  == driver.findElement() (JUnit)
    @FindBy ()
    public WebElement loginButton;

     */

    @FindBy (xpath = "//a[@href='/auth']")
    public WebElement loginOption;

    // Or by using cssSelector
//   @FindBy (css = "#basic-navbar-nav > div > a:nth-child(5)")
//    public WebElement loginButton;


    @FindBy(name = "email")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy (xpath = "//a[@role='button']")
    public WebElement profileName;


}
