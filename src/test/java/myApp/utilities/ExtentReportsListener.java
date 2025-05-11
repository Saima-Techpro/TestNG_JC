package myApp.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import org.testng.annotations.ITestAnnotation;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * ExtentReportsListener class provides reporting, retry mechanism, and screenshot capture for test cases using TestNG.
 */
public class ExtentReportsListener implements ITestListener, IRetryAnalyzer, IAnnotationTransformer {

    private static ExtentReports extentReports;
    private static ExtentHtmlReporter extentHtmlReporter;
    private static ExtentTest extentTest;

    /**
     * onStart => Called only once before all tests.
     * This method initializes the reporting system when tests begin.
     *
     * @param context TestNG context object
     */
    @Override
    public void onStart(ITestContext context) {
        String reportName = context.getCurrentXmlTest().getName();
        if (extentReports == null) {
            // Create and configure the report
            extentReports = new ExtentReports();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            String path = "target/extentReport/" + reportName + " " + date + " htmlReport.html";
            extentHtmlReporter = new ExtentHtmlReporter(path);
            extentReports.attachReporter(extentHtmlReporter);

            extentHtmlReporter.config().setDocumentTitle("Batch 333/QA-JC");
            extentHtmlReporter.config().setReportName(reportName);

            extentReports.setSystemInfo("Environment:", " QA");
            extentReports.setSystemInfo("Browser:", " Chrome");
            extentReports.setSystemInfo("Test Automation Engineer:", " Ali Can");
        }
    }

    /**
     * onTestStart => Called once before each @Test method.
     * This method starts the report section for each individual test method.
     *
     * @param result Test result object
     */
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        try {
            extentTest = extentReports.createTest(
                    "<span style='color:blue; font-weight:bold'> " + testName + " </span>",
                    "<span style='color:blue; font-weight:bold'> " + description + " </span>");
        } catch (Exception e) {
            extentTest = extentReports.createTest(
                    "<span style='color:blue; font-weight:bold'> " + testName + " </span>",
                    "<span style='color:blue; font-weight:bold'> " + result.getName() + " </span>");
        }
    }

    /**
     * Called when a test passes.
     *
     * @param message Success message
     */
    public static void extentTestPass(String message) {
        if (extentTest != null) {
            extentTest.pass(message);
        }
    }

    /**
     * Called when a test fails.
     *
     * @param message Failure message
     */
    public static void extentTestFail(String message) {
        if (extentTest != null) {
            extentTest.fail(message);
        }
    }

    /**
     * Adds info-level message to the report.
     *
     * @param message Info message
     */
    public static void extentTestInfo(String message) {
        if (extentTest != null) {
            extentTest.info(message);
        }
    }

    /**
     * onTestSuccess => Called once only after successful tests.
     *
     * @param result Test result object
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        String passSymbol = "&#9989";
        extentTestPass("<span style='color:green; font-weight:bold'>" + result.getName() + " test completed successfully. </span>" + passSymbol);
    }

    /**
     * onTestFailure => Called once only after failed tests.
     *
     * @param result Test result object
     */
    @Override
    public void onTestFailure(ITestResult result) {
        String failSymbol = "&#10060";
        extentTestFail("<span style='color:red; font-weight:bold'>" + result.getName() + " test failed! </span>" + failSymbol);
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            Files.write(Paths.get("target/screenShots/image " + date + ".jpeg"), ts.getScreenshotAs(OutputType.BYTES));
            extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/target/screenShots/image " + date + ".jpeg");
            Driver.closeDriver();  // Close browser after failure
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * onTestSkipped => Called once only after skipped tests.
     *
     * @param result Test result object
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        String skipIcon = "<img src='URL_TO_IMAGE' alt='skip_icon' style='width:16px;height:16px;'>";
        String message = "<span style='color:orange; font-weight:bold'> Test skipped due to error! \n" + result.getName() + " will be rerun.</span>" + skipIcon;
        if (extentTest != null) {
            extentTest.skip(message);
        }
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            Files.write(Paths.get("target/screenShots/image " + date + ".jpeg"), ts.getScreenshotAs(OutputType.BYTES));
            extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/target/screenShots/image " + date + ".jpeg");
            Driver.closeDriver();  // Close browser after skip
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * onFinish => Called only once after all tests finish.
     *
     * @param context TestNG context object
     */
    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null) {
            extentReports.flush();
        }
    }

    /**
     * Adds a screenshot to the report manually.
     */
    public static void addScreenShotToReport() {
        try {
            Files.createDirectories(Paths.get("target/screenShots"));
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            String date = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss").format(LocalDateTime.now());
            Files.write(Paths.get("target/screenShots/image " + date + ".jpeg"), ts.getScreenshotAs(OutputType.BYTES));
            extentTest.addScreenCaptureFromPath(System.getProperty("user.dir") + "/target/screenShots/image " + date + ".jpeg");
        } catch (IOException e) {
            Driver.closeDriver();
            throw new RuntimeException(e);
        }
    }

    // Keeps track of retry attempts for each test method
    private static Map<String, Integer> retryCounts = new HashMap<>();
    // Maximum number of retries allowed
    private static final int maxRetryCount = 1;

    /**
     * Retry logic: retries the test if it fails, up to maxRetryCount.
     *
     * @param result Test result
     * @return true if retrying, false if not
     */
    @Override
    public boolean retry(ITestResult result) {
        String testMethodName = result.getMethod().getMethodName();
        retryCounts.putIfAbsent(testMethodName, 0);
        int retryCount = retryCounts.get(testMethodName);
        if (retryCount < maxRetryCount) {
            retryCount++;
            retryCounts.put(testMethodName, retryCount);
            return true;
        }
        return false;
    }

    /**
     * Assigns the retry analyzer to every test method using TestNG’s IAnnotationTransformer.
     */
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(ExtentReportsListener.class);
    }
}
