package PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage  {
    //encapsulation
    WebDriver driver;
    public AdminPage(WebDriver classDriver){
        this.driver = classDriver;
    }
    // Define the locator for the welcome text
    public By welcomeTestLocator = By.xpath("//div[@class='welcomeTxt']");
    // Ensure this is defined correctly
    public WebElement getWelcomeText() {
        return driver.findElement(welcomeTestLocator);
    }
    /*public WebElement welcomeText = driver.findElement(welcomeTestLocator);

    @FindBy(xpath="/div[@class='welcomeTxt']")
    public WebElement welcomeText;
    public By welcomeTestLocator = By.xpath("/div[@class='welcomeTxt']");
    public AdminPage(WebDriver classDriver) {
        this.driver = classDriver;
        PageFactory.initElements(driver, this);
    } */

}