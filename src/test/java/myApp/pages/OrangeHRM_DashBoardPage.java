package myApp.pages;

import myApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRM_DashBoardPage {

    public OrangeHRM_DashBoardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "oxd-userdropdown-tab")
    public WebElement userProfile;

    @FindBy (linkText = "Logout")
    public WebElement logoutOption;





}
