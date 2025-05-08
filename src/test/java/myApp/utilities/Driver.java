package myApp.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ThreadGuard;

import java.time.Duration;

public class Driver {

    // PARALLEL TESTING
//    private static WebDriver driver; // We don't need this variable anymore for Parallel Testing

//    1. Create private constructor (private constructor + ThreadLocal = Best practice to create WebDriver for Parallel Testing )
//    2.Use ThreadLocal WebDriver for Parallel Testing
//    3. Driver.getDriver() will be used in test classes
//    4. Initialise the WebDriver instance through another method
//    5. close the driver

//    NOTE: All of our current codes should still work. Because this driver should work the same for SINGLETON (sequential)
//    or Parallel Testing

//    1. Create private constructor
    private Driver(){

    }

//    2.Use ThreadLocal WebDriver for Parallel Testing
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    // This will return a copy of the driver instance for each thread (browser)


//    3. Driver.getDriver() will be used in test classes
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            // initialise the driver by a method call
            initializeDriver();
        }
        return driver.get();
    }


    //    4. Initialise the WebDriver instance through another method
    public static void initializeDriver() {
        switch (ConfigReader.getProperty("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect( new ChromeDriver()));
                // using ThreadGuard is to provide extra layer of protection.
                // It ensures that each copy of this driver is used in different thread.
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(ThreadGuard.protect(new EdgeDriver()));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(ThreadGuard.protect((new FirefoxDriver())));
                break;
            case "safari":
                WebDriverManager.safaridriver().setup();
                driver.set(ThreadGuard.protect((new SafariDriver())));
                break;
//            case "opera":
//                WebDriverManager.operadriver().setup();
//                driver.set(ThreadGuard.protect((new OperaDriver())));
//                break;
            case "chrome-headless":
                WebDriverManager.chromedriver().setup();
                driver.set(ThreadGuard.protect(new ChromeDriver(new ChromeOptions().addArguments("--headless=new"))));
                break;
            default:
                throw new RuntimeException(ConfigReader.getProperty("browser") + " browser is not supported!" );
        }

        // driver = driver.get()

        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.get().manage().window().maximize();


    }

//    5. close the driver
    public static void closeDriver(){
        if (driver != null){
            driver.get().quit();
            driver.remove();

        }
    }

}
