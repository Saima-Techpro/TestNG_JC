package myApp.tests.topics;

import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import myApp.utilities.LoggerUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day02_ConfigReaderTest {

    @Test (groups = "Regression Suite")
    public static void configReaderTest(){

        // Navigate to google homepage
        // driver.get("https://www.amazon.com"); // JUNIT
        // Driver.getDriver().get("https://www.amazon.com"); // TestNG

        // TestNG - advanced, dynamic, so it is RECOMMENDED
        LoggerUtils.info("User is navigating to the url");
        Driver.getDriver().get(ConfigReader.getProperty("amazon_url"));


        // Get the page title
        String title = Driver.getDriver().getTitle();
        System.out.println("title = " + title);
        LoggerUtils.warn("Be careful about Amazon title!!");

        // Assertion
        try {
            assertEquals(title, ConfigReader.getProperty("amazon_title"));
            assertTrue(title.contains(ConfigReader.getProperty("amazon_title")));

        }catch (Throwable t){
        LoggerUtils.error("Assertion failed because titles don't match!!");
        }


        Driver.closeDriver();


    }

}
