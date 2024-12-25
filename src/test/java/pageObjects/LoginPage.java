package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"user-name\"]")
    WebElement txtusername;

    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement txtpw;

    @FindBy(xpath = "//*[@id=\"login-button\"]")
    WebElement btnlogin;

    public void setUsername(String Uname) {
        txtusername.sendKeys(Uname);
    }

    public void setpw(String pw) {
        txtpw.sendKeys(pw);
    }

    public void clickLogin() {
        btnlogin.click();
    }
}
