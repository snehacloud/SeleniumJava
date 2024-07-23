package TestResources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest
{
    @Getter @Setter
    protected WebDriver driver;
    private ExtentReports extent;
    private ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @BeforeSuite
    public void InitializeReport() {
        extent = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        extent.attachReporter(sparkReporter);
    }
    @BeforeMethod
    public void InitializeDriver(Method method) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            //test.log(Status.INFO, "Starting test case");
            driver.manage().window().maximize();
            //test.pass("Maximize has done");
            driver.get("https://www.google.com/");
            //TakeScreenShot("Login Page is visible");
            //test.pass("Navigate to Admin Login page");

        // Create an ExtentTest instance for the current method
        ExtentTest extentTest = extent.createTest(method.getName(), "Test description for " + method.getName());
        test.set(extentTest);
    }

    @AfterMethod
    public void QuitDriver() {
        if (driver != null) {
            driver.quit();
        }
        // No need to flush here; flush will be called in @AfterSuite
    }

    @AfterSuite
    public void CloseReport() {
        if (extent != null) {
            extent.flush();
        }
    }


    public String GetUrl(){
        String url = driver.getCurrentUrl();
        return url;
    }

    public ExtentTest getTest() {
        return test.get();
    }
    //test.info("test completed");

    public String captureScreenshot(String screenshotName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("screenshots", screenshotName + ".png");
        try {
            Files.createDirectories(destinationPath.getParent());
            Files.copy(srcFile.toPath(), destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationPath.toString();
    }
    public void TakeScreenShot(String message){
        String screenshotPathUN = captureScreenshot(message);
        getTest().info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPathUN).build());
    }

    public static JSONArray getCredentials(String key) throws IOException {
        File file = new File("C:\\Users\\SV110696\\Documents\\Anaxi-QA2\\src\\main\\java\\TestResources\\Login.json");
        FileReader fileReader = new FileReader(file);
        StringBuilder stringBuilder = new StringBuilder();
        int ch;
        while ((ch = fileReader.read()) != -1) {
            stringBuilder.append((char) ch);
        }
        fileReader.close();
        return new JSONObject(stringBuilder.toString()).getJSONArray(key);
    }
















    @DataProvider(name = "allLoginDataProvider")
    public Object[][] allLoginDataProvider() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode credentialsNode = null;
        List<Object[]> data = new ArrayList<>();

        try {
            credentialsNode = mapper.readTree(new File("C:\\Users\\SV110696\\Documents\\Anaxi-QA2\\src\\main\\java\\TestResources\\Login.json"));
            Iterator<String> fieldNames = credentialsNode.fieldNames();

            while (fieldNames.hasNext()) {
                String testCase = fieldNames.next();
                JsonNode testCaseNode = credentialsNode.get(testCase);

                for (JsonNode cred : testCaseNode) {
                    data.add(new Object[]{testCase, cred});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][0]);
    }

}
