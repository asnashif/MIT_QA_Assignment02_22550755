package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddtoCartPage extends BasePage {
    public AddtoCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-backpack\"]")
    WebElement addprd1;

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-bike-light\"]")
    WebElement addprd2;

    @FindBy(xpath = "//*[@id=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")
    WebElement addprd3;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    WebElement cart;

    @FindBy(css = ".cart_item .inventory_item_name")
    List<WebElement> cartItems;

    public void clikprd1() {
        addprd1.click();
    }

    public void clikprd2() {
        addprd2.click();
    }

    public void clikprd3() {
        addprd3.click();
    }

    public void clickcart() {
        cart.click();
    }

    public boolean isProductInCart(String productName) {
        for (WebElement item : cartItems) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }
}
