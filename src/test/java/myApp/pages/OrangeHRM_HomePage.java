package myApp.pages;

import myApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static myApp.utilities.WaitUtils.waitFor;

public class OrangeHRM_HomePage {

    public OrangeHRM_HomePage(){ // Constructor
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy (name = "username")
    public WebElement usernameInput;

    @FindBy (name = "password")
    public WebElement passwordInput;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement loginButton;


    // Reusable Login method
    public void login(String username, String password){
        usernameInput.sendKeys(username);
        waitFor(1);
        passwordInput.sendKeys(password);
        waitFor(1);
        loginButton.click();
        waitFor(1);
    }


}
