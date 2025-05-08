package myApp.pages;

import myApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MedunnaHomePage {

    /*
    1. Create Constructor
    2. Use PageFactory to initialise the webElements
    3. Locate and store webElements
     */

    //1.
    public MedunnaHomePage(){
        //2.
        PageFactory.initElements(Driver.getDriver(), this);

    }

    //3.
    @FindBy(id = "account-menu")
    public WebElement userIcon;

    @FindBy(xpath = "//span[.='Register']")
    public WebElement registerOption;


}
