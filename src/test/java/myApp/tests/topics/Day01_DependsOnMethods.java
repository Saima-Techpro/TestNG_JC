package myApp.tests.topics;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Day01_DependsOnMethods {

    @Test
    public void loginTest(){
        System.out.println("Logging in to the app ... ");
        assertTrue(true);
    }

    @Test (dependsOnMethods = "loginTest")
    public void searchTest(){
        System.out.println("Searching for a book named....");
        assertTrue(false);
    }

    @Test (dependsOnMethods = "searchTest()")
    public void cartTest(){
        System.out.println("Adding the book to the cart.... ");
    }
}
