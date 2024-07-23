package PageElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id="j_username")
    public WebElement username;

    @FindBy(id="j_password")
    public WebElement password;

    @FindBy(xpath="//*[text()='Login']")
    public WebElement loginButton;

}