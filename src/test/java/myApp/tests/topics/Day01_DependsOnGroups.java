package myApp.tests.topics;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;


public class Day01_DependsOnGroups {

    @Test (groups = "Smoke Suite")
    public void homePageTest(){
        System.out.println("Navigating to HomePage... ");
    }

    @Test (groups = "Smoke Suite")
    public void loginTest(){
        System.out.println("Logging in to the app ... ");
        assertTrue(false);
    }

    @Test (groups = "Regression Suite")
    public void searchTest(){
        System.out.println("Searching for a book named....");

    }

    @Test (dependsOnGroups = "Smoke Suite")
    public void cartTest(){
        System.out.println("Adding the book to the cart.... ");
    }
    // NOTE: cartTest() method depends on the tests that are grouped under "Smoke Suite"
    // If any test method fails from that group, cartTest() method will be skipped.

    @Test (dependsOnGroups = "Regression Suite")
    public void logoutTest(){
        System.out.println("Logging out ... ");
    }
}
