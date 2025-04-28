package myApp.tests.topics;

import org.testng.annotations.*;

public class Day01_Annotations {

     /*

    @Test : the most commonly used annotation - works the same way in TestNG as in JUnit

    Hierarchy in TestNG : suite > test > group > class > method

    @Before and @After : there are 5 sets of  @Before and @After in TestNG

    In TestNG, we have can set priority, we can enable, disable or ignore test methods using these annotations:

    (priority = 3)      => run the test method on 3rd number
    @Ignore             => ignore that test method
    (enabled = false)   => method won't run

    NOTE: If there's a method with no priority mentioned, that will run FIRST.
          If there are more one such methods, they'll be run in alphabetical order.

    By default, tests run from top to bottom  .. following ascending / alphabetical order
    So if we want to run any particular test first, we have the option to mention priority for that test case

    @Test(priority = 3) will run this test case at number 3.
    We can also use negative numbers; but that's not common and recommended

    We can also group tests together by passing group = "name of the group" in @Test parameter to run all tests under one group
    and mentioning that group name in @BeforeGroup and @AfterGroup method

     */

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Before suite .... ");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("After suite....");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("Before tests... ");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("After tests... ");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Before class... ");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("After class... ");
    }

    @BeforeGroups (groups = "smoke test group")
    public void beforeGroups(){
        System.out.println("Before groups... ");
    }

    @AfterGroups (groups = "smoke test group")
    public void afterGroups(){
        System.out.println("After groups... ");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Before method... ");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("After method... ");
    }


    @Test (priority = 6)
    public void test1(){
        System.out.println("Test 1... ");
    }

    @Test (groups = "smoke test group")
    public void test2(){
        System.out.println("Test 2... ");
    }
    @Test (priority = 5)
    public void test3(){
        System.out.println("Test 3... ");
    }

    @Test (priority = 2 , enabled = false)
    public void test4(){
        System.out.println("Test 4... ");
    }
    @Test (priority = 1)
    public void test5(){
        System.out.println("Test 5... ");
    }
    @Test (priority = 3) @Ignore
    public void test6(){
        System.out.println("Test 6... ");
    }


}
