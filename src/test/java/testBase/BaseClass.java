package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Properties p;
    public Logger logger;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    private String reportFileName;

    @BeforeSuite
    public void setUp() {
        // Create a unique file name for the Extent Report
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = "ExtentReport_" + timestamp + ".html"; // Default report name

        // Set up ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./src/test/resources/reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Set a custom name for the Extent Report.
     * @param customName The custom name for the report.
     */
    public void setReportName(String customName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFileName = customName + "_" + timestamp + ".html";

        // Reinitialize ExtentSparkReporter with the new name
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    /**
     * Start a test in Extent Report with a given name.
     *
     * @param testName Name of the test case.
     */
    public void startTest(String testName) {
        test = extent.createTest(testName);
    }

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setup(String os, String br) {

        logger = LogManager.getLogger(this.getClass());

        // Loading config.properties file
        try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
            p = new Properties();
            p.load(file);

            switch (br.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "edge":
                    driver = new EdgeDriver(); // Edge does not support headless mode as of some versions
                    break;
                default:
                    System.out.println("Invalid browser name");
                    return;
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://www.saucedemo.com/");
            driver.manage().window().maximize();
        } catch (IOException e) {
            System.out.println("Error loading config file: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public String captureScreen(String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotPath = System.getProperty("user.dir") + "/src/test/resources/screenshots/" + testName + "_" + timeStamp + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(screenshotPath);
        FileHandler.copy(srcFile, destFile);
        return screenshotPath;
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
