package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductListPage extends BasePage {

    private By productsList = By.className("inventory_item");
    private By productImage = By.className("inventory_item_img");
    private By productName = By.className("inventory_item_label");
    private By productPrice = By.className("inventory_item_price");

    public ProductListPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productsList);
    }

    public WebElement getProductImage(WebElement product) {
        return product.findElement(productImage);
    }

    public WebElement getProductName(WebElement product) {
        return product.findElement(productName);
    }

    public WebElement getProductPrice(WebElement product) {
        return product.findElement(productPrice);
    }

    // New methods to check visibility
    public boolean isProductImageDisplayed(WebElement product) {
        return getProductImage(product).isDisplayed();
    }

    public boolean isProductNameDisplayed(WebElement product) {
        return getProductName(product).isDisplayed();
    }

    public boolean isProductPriceDisplayed(WebElement product) {
        return getProductPrice(product).isDisplayed();
    }
}
