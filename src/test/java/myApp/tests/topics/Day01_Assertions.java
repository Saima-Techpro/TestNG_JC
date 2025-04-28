package myApp.tests.topics;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.*;

public class Day01_Assertions {

    @Test
    public void hardAssertTest(){
        System.out.println("Checking how Hard Assertion works...");
        assertEquals(12, 12);
        System.out.println("Line 13 ... ");
        assertTrue("Selenium".contains("e"));
        System.out.println("Line 15 ... ");
        assertEquals("Java", "java"); // this assertion fails
        System.out.println("Line 17 ... "); // This and following lines of codes will not be printed because of Hard Assertion
        assertFalse("Java".contains("S"));
    }

    @Test
    public void javaAssertTest(){
        System.out.println("Checking how Java Assertion works...");
        assert 3 < 5;
        System.out.println("Line 25 ... ");
        assert "Apple".contains("p");
        System.out.println("Line 27 ... ");
        assert "Selenium".toLowerCase().startsWith("s");
        System.out.println("Line 29 ... ");
        assert "Java" == "java"; // Failed
        System.out.println("Line 31 ... "); // This and following lines won't be printed
        assert 6 > 1;
        System.out.println("Line 33 ... ");

        // Java assertions work the same way as HARD ASSERTION from JUnit or TestNG works
        // We as QAs should not be using Java assertion. We should always use JUnit or TestNG assertions
    }


    @Test
    public void softAssertTest(){
        /*
        How to use Soft Assertion in TestNG?
        1. Create an object from SoftAssert class
        2. Use this object wherever you need it
        3. assertAll() MUST be used as a last step. (works the same way as perform() works in Actions class)

         */
        System.out.println("Checking how Soft Assertion works...");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Java", "java");
        System.out.println("Line 54... ");
        softAssert.assertTrue("Selenium".contains("S"));
        System.out.println("Line 56... ");
        softAssert.assertNotEquals("Selenium", "SQL");
        System.out.println("Line 58 ... ");
        softAssert.assertTrue("Apple".toLowerCase().startsWith("A"));
        System.out.println("Line 60... ");
        softAssert.assertAll(); // last step => MANDATORY

    }


     /*
    In JUNIT, we only have HARD ASSERTION

    HARD ASSERTION: If assertion fails, it stops executing the rest of the test and throws exception straightaway.

    In TestNG, we have two types of assertions:
    1. HARD ASSERTION: it works the same way as in JUNIT
    2. Soft ASSERTION: It is different in a sense that it doesn't stop executing the test if assertion fails at any point
                       rather, it carries on until the last step and shows in end where assertion failed.
                       But it won't work without assertAll() in the end.

                       assertAll() is MANDATORY.

     How to use Soft ASSERTION?

    1. Create an object of Soft ASSERTION
    2. Use it wherever you need it
    3. assertAll() as a last step (works the same way as perform() works in Actions class)

     */


}
