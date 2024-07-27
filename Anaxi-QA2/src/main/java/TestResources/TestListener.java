package TestResources;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener, IInvokedMethodListener {

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before each test method invocation
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After each test method invocation
        Object testClass = testResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        try {
            CaptureScreenshot(driver, method.getTestMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // On test start
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // On test success
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // On test failure
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        try {
            CaptureScreenshot(driver, result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // On test skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // On test failed but within success percentage
    }

    @Override
    public void onStart(ITestContext context) {
        // Before the start of any test method
    }

    @Override
    public void onFinish(ITestContext context) {
        // After all test methods have run
    }

    private void CaptureScreenshot(WebDriver driver, String screenshotName) throws IOException {
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatter);
        Path destinationPath = Paths.get("C:\\Users\\SV110696\\Documents\\Anaxi-QA2\\screenshots",screenshotName+formattedDateTime+".png");
        Files.copy(srcFile.toPath(),destinationPath);
    }
}
