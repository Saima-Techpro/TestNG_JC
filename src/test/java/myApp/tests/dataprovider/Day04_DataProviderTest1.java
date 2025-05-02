package myApp.tests.dataprovider;

import myApp.utilities.DataProviderUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day04_DataProviderTest1 {

    // Data Provider Method = should be 2D ARRAY

    @DataProvider
    public Object[][] productListProvider(){
        Object[][] productList = {{"Honda"}, {"Tesla"} , {"BYD"}, {"Geely"}};
        return productList;
    }

    // Now connect this dataProvider with a test method
    @Test ( dataProvider = "productListProvider")
    public void test1(String data){
        System.out.println(data);
    }


    // Data Provider Method 2 (giving a custom name to the method name = "employee_credentials")
    @DataProvider (name = "employee_credentials")
    public Object[][] credentialProvider(){
        Object[][] credentials = {
                {"admin1", "pass1", "QA", "34"},
                {"admin2", "pass2", "CEO", "55"},
                {"admin3", "pass3", "Test Lead", "21"} ,
                {"admin4", "pass4", "Dev", "39"} };

        return credentials;
    }
    // Now connect this dataProvider with a test method
//    @Test (dataProvider = "credentialProvider")
//    public void test2(String userName, String password, String role, String id){
//        System.out.println(userName);
//    }
    // This shows error because dataProvider method has a customised name => employee_credentials
    // Whenever dataProvider method has a customised name, we MUST use the customised name

    @Test (dataProvider = "employee_credentials")
    public void test2(String userName, String password, String role, String id){
        System.out.println(userName +" | "+ password +" | "+ role +" | "+ id);
    }


    // NOTE: we can store data provider methods in UTILS class as well to implement DRY rule
    // DRY => Don't Repeat Yourself
    // When we use data provider methods from UTILS class, we need to give the address of that class

    @Test(dataProvider = "employeeCredentialsProvider", dataProviderClass = DataProviderUtils.class)
    public void test3(String email, String password){
        System.out.println(email +" | "+ password);
    }



    // BEST PRACTICE => DataProvider + Excel
    @Test (dataProvider = "excelEmployeeData" , dataProviderClass = DataProviderUtils.class)
    public void text4(String email, String password){
        System.out.println(email +" | "+ password);
    }



}
