package myApp.tests.topics;

import myApp.utilities.ConfigReader;
import myApp.utilities.Driver;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day02_ConfigReaderTest {

    @Test
    public static void configReaderTest(){

        // Navigate to google homepage
        // driver.get("https://www.amazon.com"); // JUNIT
        // Driver.getDriver().get("https://www.amazon.com"); // TestNG

        // TestNG - advanced, dynamic, so it is RECOMMENDED

        Driver.getDriver().get(ConfigReader.getProperty("amazon_url"));


        // Get the page title
        String title = Driver.getDriver().getTitle();
        System.out.println("title = " + title);

        // Assertion
        assertEquals(title, ConfigReader.getProperty("amazon_title"));
        assertTrue(title.contains(ConfigReader.getProperty("amazon_title")));

        


    }

}
