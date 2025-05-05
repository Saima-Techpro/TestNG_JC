package myApp.tests.listeners;

import myApp.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static myApp.utilities.WaitUtils.waitFor;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
    /*
    We can link Test class with Listeners in two ways:
    1. By using @Listeners annotation in the class that you want to link
    2. Use <Listeners> in xml file - RECOMMENDED
     */

//@Listeners (myApp.utilities.ListenerUtils.class)
// Instead of above annotation, we run it from xml file
public class Day05_ListenersTest {


    protected WebDriver driver;

    @BeforeMethod
    public void setUp(){
       driver =  Driver.getDriver();
    }


    @Test
    public void test1(){
        driver.get("https://www.techproeducation.com/");
        String title = driver.getTitle();
        System.out.println("title = " + title);
        assertTrue(title.contains("TechPro Education"));
        assertEquals(title, "TechPro Education IT Programs");
    }

    @Test
    public void test2(){
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        System.out.println("title = " + title);
        assertTrue(title.contains("Google"));

    }

    @Test
    public void test3(){
        System.out.println("Test SKIPPED");
        throw new SkipException("Skipping test on purpose");

    }


    @Test
    public void test4(){
        driver.get("https://www.google.com/");
        String title = driver.getTitle();
        System.out.println("title = " + title);

        // Failing the following step on purpose by giving wrong className

        driver.findElement(By.className("gL")).sendKeys("iPhone 16"); // NoSuchElementException
    }




    @AfterMethod
    public void tearDown(){
        waitFor(1);
        Driver.closeDriver();
    }
}
