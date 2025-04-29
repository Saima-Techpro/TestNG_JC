package myApp.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    // This Driver class is following SINGLETON PATTERN => creating single driver instance used by all in the test suite
    // This cannot be used for parallel testing
    // We will make it more dynamic later (for parallel testing)

    private static WebDriver driver;



    public static WebDriver getDriver(){
        if (driver == null){
            System.out.println("Instantiating WebDriver...");

            switch (ConfigReader.getProperty("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "chrome-headless":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless", "new");
                    driver = new ChromeDriver(options);
                    break;
//                case "opera":
//                    WebDriverManager.operadriver().setup();
//                    driver = new OperaDriver();
//                    break;
                default:
                    throw new RuntimeException(ConfigReader.getProperty("browser") + " browser is not supported!" );
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
            driver.manage().window().maximize();

        }
        return driver;
    }


    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
