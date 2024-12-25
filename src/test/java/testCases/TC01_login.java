package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LogoutPage;
import pageObjects.header;
import pageObjects.LoginPage;
import testBase.BaseClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TC01_login extends BaseClass {

    @Test
    public void verifyLogin() {
        logger.info("**Starting TC01_Login****");

        try {
            LoginPage lp = new LoginPage(driver);

            lp.setUsername(p.getProperty("username"));
            lp.setpw(p.getProperty("password"));
            lp.clickLogin();


            // Take screenshot for passed test
            captureScreen("TC01_Login_Passed");

            // Add explicit wait for the homepage element to be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement homePageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header_container")));

            header hpg = new header(driver);
            boolean targetpage = (homePageElement != null && hpg.isHomePageExists());

            // Assert that login is successful
            Assert.assertTrue(targetpage, "Login Passed");


            // Take screenshot for passed test
            captureScreen("TC01_Login_Verfiied");

            LogoutPage logt = new LogoutPage(driver);

            logt.clickmenu();
            logt.clicklogout();
        } catch (Exception e) {
            logger.error("Test 01 failed", e);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }

        logger.info("**Executed TC01_Login Test****");
    }


}
