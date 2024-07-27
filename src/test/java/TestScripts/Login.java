package TestScripts;
import PageElement.AdminPage;
import PageElement.LoginPage;
import TestResources.BaseTest;
import TestResources.TestListener;
import TestResources.Wait;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class Login extends BaseTest {

    @Test(priority = 1)
    public void loginWithValidCred() throws IOException {
        ExtentTest test = getTest();
        //TakeScreenshot("Login Page");
        LoginPage lp = new LoginPage(driver);
        AdminPage ap = new AdminPage(driver);
        JSONArray validCreds = getCredentials("loginWithValidCred");

        for (int i = 0; i < validCreds.length(); i++)
        {
            JSONObject validUser = validCreds.getJSONObject(i);
            driver.get("https://adm-rgs.qa2-anaxi-fusion.ig.aristocrat-interactive.com/controller/auth/login");
            TakeScreenshot("Login Page");
            String username = validUser.getString("username");
            String password = validUser.getString("password");
            System.out.println("Valid Username: " + username + ", Password: " + password);

            lp.username.sendKeys(username);
            TakeScreenshot("Username Entered");

            lp.password.sendKeys(password);
            TakeScreenshot("Password Entered");

            lp.loginButton.click();
            TakeScreenshot("Final Step");

            //Wait wait = new Wait(driver);
            //wait.VisibilityOfElement(ap.welcomeTestLocator, 5);

            //String text = ap.getWelcomeText().getText();
            //Assert.assertTrue(text.equals("Welcome sneha verma"), "Valid cred not working");

            test.info("Successfully logged in with valid credentials");
            TakeScreenshot("Admin Home Page");
        }
    }


    @Test(priority = 2)
    public void loginWithInvalidCred() throws IOException {
        ExtentTest test = getTest();

        LoginPage lp = new LoginPage(driver);
        JSONArray inValidCreds = getCredentials("loginWithInvalidCred");

        for (int i = 0; i < inValidCreds.length(); i++) {
            driver.get("https://adm-rgs.qa2-anaxi-fusion.ig.aristocrat-interactive.com/controller/auth/login");
            TakeScreenshot("Login Page");
            JSONObject inValidUser = inValidCreds.getJSONObject(i);
            String username = inValidUser.getString("username");
            String password = inValidUser.getString("password");
            System.out.println("Invalid Username: " + username + ", Password: " + password);

            lp.username.sendKeys(username);
            TakeScreenshot("Invalid Username Entered");

            lp.password.sendKeys(password);
            TakeScreenshot("Invalid Password Entered");

            lp.loginButton.click();
            TakeScreenshot("Not able to Login");

            Wait wait = new Wait(driver);
            wait.StaticWait(2);
            Assert.assertTrue(GetUrl().contains("BadCredentialsException"), "Invalid cred are not working");

            test.info("Attempted login with invalid credentials");
            TakeScreenshot("loginWithInvalidCred");

        }
    }
}