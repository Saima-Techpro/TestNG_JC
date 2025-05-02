package myApp.tests.excelautomation;

import myApp.pages.DataTablesPage;
import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import myApp.utilities.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Day04_ExcelAutomationTest {
    /*
When user go to https://editor.datatables.net/
Click on the new button
When user enters all fields
When user clicks on ‘create’ button
And search for the first name
Then verify the name field contains first name

     */

    protected WebDriver driver;
    DataTablesPage  dataTablesPage;
    ExcelUtils excelUtils;
    List<Map<String, String>> dataList;


    @BeforeMethod
    public void setUp(){
        driver = Driver.getDriver();
        dataTablesPage = new DataTablesPage();
    }

    @Test
    public void testWithoutExcel(){

//        When user go to https://editor.datatables.net/
        driver.get(ConfigReader.getProperty("dataTables_url"));

//        Click on the new button

        dataTablesPage.newButton.click();
        waitFor(1);

//        When user enters all fields
        dataTablesPage.firstNameInput.sendKeys("Bob");
        dataTablesPage.lastNameInput.sendKeys("Smith");
        dataTablesPage.positionInput.sendKeys("SDET");
        dataTablesPage.officeInput.sendKeys("Sydney");
        dataTablesPage.extensionInput.sendKeys("4521");
        dataTablesPage.startDateInput.sendKeys("2025-01-01");
        dataTablesPage.salaryInput.sendKeys("95000");

//        When user clicks on ‘create’ button
        dataTablesPage.createButton.click();
        waitFor(1);

//        And search for the first name
        dataTablesPage.searchButton.sendKeys("Bob");
        waitFor(1);

//        Then verify the name field contains first name
        assertTrue(dataTablesPage.nameField.getText().contains("Bob"));
        // OR
        assertEquals(dataTablesPage.nameField.getText(), "Bob Smith");



    }

    @Test
    public void testWithExcel(){

        // Path of the excel sheet
        String pathOfFile = "resources/data_sheet.xlsx";
        excelUtils = new ExcelUtils(pathOfFile, "user_data");
        dataList = excelUtils.getDataList();
        System.out.println(dataList);

        //LOOP BEGINS


        for (Map<String, String> eachData : dataList){

 //        When user go to https://editor.datatables.net/
            driver.get(ConfigReader.getProperty("dataTables_url"));
//        Click on the new button
            dataTablesPage.newButton.click();
            waitFor(1);
//        When user enters all fields
        dataTablesPage.firstNameInput.sendKeys(eachData.get("first_name"));
        dataTablesPage.lastNameInput.sendKeys(eachData.get("last_name"));
        dataTablesPage.positionInput.sendKeys(eachData.get("position"));
        dataTablesPage.officeInput.sendKeys(eachData.get("office"));
        dataTablesPage.extensionInput.sendKeys(eachData.get("extension"));
        dataTablesPage.startDateInput.sendKeys(eachData.get("start_date"));
        dataTablesPage.salaryInput.sendKeys(eachData.get("salary"));

//        When user clicks on ‘create’ button
        dataTablesPage.createButton.click();
        waitFor(1);

//        And search for the first name
        dataTablesPage.searchButton.sendKeys(eachData.get("first_name"));
        waitFor(1);

//        Then verify the name field contains first name
        assertTrue(dataTablesPage.nameField.getText().contains(eachData.get("first_name")));
        // OR
        assertEquals(dataTablesPage.nameField.getText(), (eachData.get("first_name") +" "+eachData.get("last_name")));


        }  // LOOP ENDS


    }



    @AfterMethod
    public void tearDown(){
        waitFor(1);
        Driver.closeDriver();
    }



}
