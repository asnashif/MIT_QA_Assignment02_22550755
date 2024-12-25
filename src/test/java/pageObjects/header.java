package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class header extends BasePage{
    public header(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[1]/div[2]/div")
    WebElement pageheader;

    public boolean isHomePageExists() {
        try {
            return driver.findElement(new By.ByXPath("//*[@id=\"header_container\"]/div[1]/div[2]/div")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
