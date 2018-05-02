package General;

import PageFactory.homecleaning;
import PageFactory.login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MainCall {
    public static ChromeDriver driver;

    @BeforeMethod()
    public void StartDriver() {
       driver = WebDriverFactory.getInstance();
    }

    @AfterMethod(alwaysRun = true)
    public void QuitDriver() {
//            driver.quit();
    }
}
