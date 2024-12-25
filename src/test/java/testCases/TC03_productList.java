package testCases;

import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.ProductListPage;
import testBase.BaseClass;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class TC03_productList extends BaseClass {

    @Test
    public void verifyProductList() {
        logger.info("**Starting TC03_ProductList**");

        try {
            // Login steps
            LoginPage lp = new LoginPage(driver);
            lp.setUsername(p.getProperty("username"));
            lp.setpw(p.getProperty("password"));
            lp.clickLogin();

            // Initialize ProductListPage
            ProductListPage productPage = new ProductListPage(driver);

            // Verify product list is displayed
            List<WebElement> products = productPage.getProducts();
            if (products.size() > 0) {
                logger.info("**TC03_Product list is displayed**");
                captureScreen("TC03_Product List is Displayed");
            } else {
                logger.info("**TC03_No products are displayed on the page.**");
                captureScreen("TC03_Product List is not Displayed");
                return; // Exit test as no products are displayed
            }

            // Verify each product's details (image, name, price)
            for (WebElement product : products) {
                // Verify product details through methods in ProductListPage
                verifyProductDetails(productPage, product);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("**Error occurred in TC03_ProductList**", e);
        }
    }

    private void verifyProductDetails(ProductListPage productPage, WebElement product) {
        try {
            // Verify product image
            if (productPage.isProductImageDisplayed(product)) {
                logger.info("**TC03_Product image is displayed.**");
                captureScreen("TC03_Product image is Displayed");
            } else {
                logger.info("**TC03_Product image is not displayed.**");
                captureScreen("TC03_Product Image is not Displayed");
            }

            // Verify product name
            if (productPage.isProductNameDisplayed(product)) {
                logger.info("**TC03_Product name is displayed.**");
                captureScreen("TC03_Product Name is Displayed");
            } else {
                logger.info("**TC03_Product name is not displayed.**");
                captureScreen("TC03_Product Name is not Displayed");
            }

            // Verify product price
            if (productPage.isProductPriceDisplayed(product)) {
                logger.info("**TC03_Product price is displayed.**");
                captureScreen("TC03_Product Price is Displayed");
            } else {
                logger.info("**TC03_Product price is not displayed.**");
                captureScreen("TC03_Product Price is not Displayed");
            }
        } catch (IOException e) {
            logger.error("IOException occurred while capturing the screenshot", e);
        }
    }

}
