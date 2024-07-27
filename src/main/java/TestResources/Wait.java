package TestResources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Wait {
    private WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }
        public void VisibilityOfElement(By locator, int waitInSec) {
            if (driver == null) {
                throw new IllegalStateException("WebDriver instance is null");
            }
            try {
                System.out.println("Waiting for visibility of element located by: " + locator.toString());
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitInSec));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                System.out.println("Element is visible: " + locator.toString());
            }
            catch  (Exception e) {
                System.err.println("Error occurred while waiting for element visibility: " + e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }

        public void StaticWait(int waitInSec){
            try {
                Thread.sleep(Duration.ofSeconds(waitInSec).toMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

}
