package TestScripts;
import PageElement.AdminPage;
import PageElement.LoginPage;
import TestResources.BaseTest;
import TestResources.Wait;
import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
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
        TakeScreenShot("Login Page is visible");
        LoginPage lp = new LoginPage(driver);
        AdminPage ap = new AdminPage(driver);
        JSONArray validCreds = getCredentials("loginWithValidCred");

//        for (int i = 0; i < validCreds.length(); i++) {
//            JSONObject validUser = validCreds.getJSONObject(i);
//            String username = validUser.getString("username");
//            String password = validUser.getString("password");
//            System.out.println("Valid Username: " + username + ", Password: " + password);
//
//            lp.username.sendKeys(username);
//            TakeScreenShot("Username Entered");
//
//
//            lp.password.sendKeys(password);
//            TakeScreenShot("Password Entered");
//
//            lp.loginButton.click();
//            TakeScreenShot("Login to Admin");
//
//            Wait wait = new Wait(driver);
//            wait.VisibilityOfElement(ap.welcomeTestLocator, 5);
//
//            String text = ap.getWelcomeText().getText();
//            Assert.assertTrue(text.equals("Welcome sneha verma"), "Valid cred not working");
//
//            test.info("Successfully logged in with valid credentials");
//            TakeScreenShot("Admin Home Page");
//        }
//        String username = validCred.getString("username");
//        String password = validCred.getString("password");

//        lp.username.sendKeys(username);
//        TakeScreenShot("Username Entered");
//
//
//        lp.password.sendKeys(password);
//        TakeScreenShot("Password Entered");


        }


//    @Test(priority = 2,enabled = false)
//    public void loginWithInvalidCred() throws IOException {
//        ExtentTest test = getTest();
//        LoginPage lp =new LoginPage(driver);
//
//        JSONArray validCred = getCredentials("loginWithInvalidCred");
////        String username = validCred.getString("username");
////        String password = validCred.getString("password");
//
////            lp.username.sendKeys(username);
////            TakeScreenShot("Invalid Username Entered");
////
////            lp.password.sendKeys(password);
////            TakeScreenShot("Invalid Password Entered");
//
//            lp.loginButton.click();
//            TakeScreenShot("Not able to Login");
//
//            Wait wait = new Wait(driver);
//            wait.StaticWait(2);
//            Assert.assertTrue(GetUrl().contains("BadCredentialsException"), "Invalid cred are not working");
//
//            test.info("Attempted login with invalid credentials");
//            TakeScreenShot("loginWithInvalidCred");
//
//    }
}