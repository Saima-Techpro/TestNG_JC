package myApp.tests.topics;

import myApp.utilities.Driver;
import org.testng.annotations.Test;

public class Day02_DriverTest {

    @Test
    public void driverTest() throws InterruptedException {
        // In JUnit, we used to call driver
        // driver.get("https://www.amazon.com"); //JUnit
        // Driver.getDriver().get("https://www.amazon.com") // Testng
        // Driver.getDriver() returns driver instance

        Driver.getDriver().get("https://www.google.com");

        Thread.sleep(2000);
        Driver.closeDriver();




    }
}
