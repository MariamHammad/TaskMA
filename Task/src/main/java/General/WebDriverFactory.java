package General;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    static ChromeDriver driver;

public static ChromeDriver getInstance(){

    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    ChromeOptions op = new ChromeOptions();
    op.addArguments("--start-maximized");
    driver = new ChromeDriver(op);
    driver.get("https://uat1-operations.servicemarket.com");
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    return driver;
}

}
