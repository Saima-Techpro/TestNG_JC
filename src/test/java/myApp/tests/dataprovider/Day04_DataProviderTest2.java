package myApp.tests.dataprovider;

import myApp.pages.DataTablesPage;
import myApp.utilities.DataProviderUtils;
import myApp.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertTrue;

public class Day04_DataProviderTest2 {

    /*

When user go to https://editor.datatables.net/
Click on the new button
When user enters all fields
When user clicks on ‘create’ button
And search for the first name
Then verify the name field contains first name

    PLAN:  Use data provider method to send the testing data

*/

    protected WebDriver driver;
    DataTablesPage dataTablesPage;


    @BeforeMethod
    public void setUp(){
        this.driver = Driver.getDriver();
        dataTablesPage = new DataTablesPage();
    }

    @Test (dataProvider = "userData" , dataProviderClass = DataProviderUtils.class , groups = "Regression Suite")
    public void dataProviderTest2(String firstName , String lastName, String position, String office, String extension, String start_date, String salary){

//        When user go to https://editor.datatables.net/
        driver.get("https://editor.datatables.net/");
//        Click on the new button
        dataTablesPage.newButton.click();
        waitFor(1);

//        When user enters all fields
        dataTablesPage.firstNameInput.sendKeys(firstName);
        dataTablesPage.lastNameInput.sendKeys(lastName);
        dataTablesPage.positionInput.sendKeys(position);
        dataTablesPage.officeInput.sendKeys(office);
        dataTablesPage.extensionInput.sendKeys(extension);
        dataTablesPage.startDateInput.sendKeys(start_date);
        dataTablesPage.salaryInput.sendKeys(salary);

//        When user clicks on ‘create’ button
        dataTablesPage.createButton.click();
        waitFor(1);
//        And search for the first name
        dataTablesPage.searchButton.sendKeys(firstName);
        waitFor(1);

//        Then verify the name field contains first name
        assertTrue(dataTablesPage.nameField.getText().contains(firstName));


    }



    @AfterMethod
    public void tearDown(){
        waitFor(1);
        Driver.closeDriver();
    }



}
