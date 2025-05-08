package myApp.pages;

import myApp.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MedunnaRegistrationPage {

       /*
    1. Create Constructor
    2. Use PageFactory to initialise the webElements
    3. Locate and store webElements
     */

    public MedunnaRegistrationPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "ssn")
    public WebElement ssnInputField;

    @FindBy(name = "firstName")
    public WebElement firstNameInputField;


    @FindBy(xpath = "//div[.='Your SSN is invalid']")
    public WebElement invalidSsnMessage;

}
