package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AddtoCartPage;
import testBase.BaseClass;
import pageObjects.LoginPage;

import java.time.Duration;

public class TC04_AddProducttoCart extends BaseClass {

    @Test
    public void verifyAddProductToCart() {
        logger.info("**Starting TC04_AddProductToCart**");

        try {
            // Login step
            LoginPage lp = new LoginPage(driver);
            lp.setUsername(p.getProperty("username"));
            lp.setpw(p.getProperty("password"));
            lp.clickLogin();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Add products to the cart
            AddtoCartPage cart = new AddtoCartPage(driver);
            cart.clikprd1();
            logger.info("Product 1 added to cart.");
            cart.clikprd2();
            logger.info("Product 2 added to cart.");
            cart.clikprd3();
            logger.info("Product 3 added to cart.");

            // Navigate to the cart
            cart.clickcart();
            captureScreen("TC04_View Cart");
            logger.info("Cart Opened Successfully");

            // Verification logic
            Assert.assertTrue(cart.isProductInCart("Sauce Labs Backpack"), "Product 1 not found in the cart.");
            Assert.assertTrue(cart.isProductInCart("Sauce Labs Bike Light"), "Product 2 not found in the cart.");
            Assert.assertTrue(cart.isProductInCart("Sauce Labs Bolt T-Shirt"), "Product 3 not found in the cart.");
            logger.info("All products verified in the cart successfully.");

        } catch (Exception e) {
            logger.error("Test TC04_AddProductToCart failed", e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("**Executed TC04_AddProductToCart Test**");
    }
}
