package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage {

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"react-burger-menu-btn\"]")
    WebElement lnkmenu; // click menu

    @FindBy(xpath = "//*[@id=\"logout_sidebar_link\"]")
    WebElement lnklogout;

    public void clickmenu() {
        lnkmenu.click();
    }

    public void clicklogout() {
        lnklogout.click();
    }
}
