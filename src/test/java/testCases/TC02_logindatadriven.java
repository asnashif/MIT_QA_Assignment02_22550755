package testCases;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.LogoutPage;
import pageObjects.header;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.dataProviders;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TC02_logindatadriven extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = dataProviders.class)
    public void verify_logindd(String username, String password, String exp) {
        logger.info("**Starting TC02_Login Data Driven Test****");

        try {
            LoginPage lp = new LoginPage(driver);

            driver.manage().deleteAllCookies();

            lp.setUsername(username);
            lp.setpw(password);
            lp.clickLogin();

            // Add explicit wait for the homepage element or error element
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            header hpg = new header(driver);
            boolean targetpage=hpg.isHomePageExists();

            LogoutPage logt = new LogoutPage(driver);

            if (exp.equalsIgnoreCase("Valid")) {
                if(targetpage==true)
                {
                    logger.info("**successfully logged in with valid creds****");

                    // Take screenshot for passed test
                    captureScreen("TC02_Login_Passed");
                    logt.clickmenu();
                    logt.clicklogout();
                    Assert.assertTrue(true);
                }
                else {
                    Assert.assertTrue(false);
                }
            }
            if(exp.equalsIgnoreCase("Invalid")){
                if(targetpage==true){

                    // Take screenshot for passed test
                    captureScreen("TC02_Logged in for Invalid Creds");
                    logt.clickmenu();
                    logt.clicklogout();
                    Assert.assertTrue(false);
                }
                else {
                    Assert.assertTrue(true);

                    // Take screenshot for passed test
                    captureScreen("TC02_Login_Passed For Invalid Creds");
                }
            }
        } catch (Exception e) {

            // Take screenshot for passed test
            try {
                captureScreen("TC02_Login_Failed");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Assert.fail("Test case failed due to exception: " + e.getMessage());
            logger.error("Test 02 failed", e);


        }

        logger.info("** Executed TC02_Login Data Driven Test****");
    }


}
