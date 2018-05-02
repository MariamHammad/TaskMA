package TestCases;

import General.MainCall;
import PageFactory.login;
import PageFactory.homeObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class testcases_login extends MainCall {

    login login;
    homeObjects homeObjects;


    @BeforeMethod
    public void testInit() {
        login = PageFactory.initElements(driver, login.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
    }

    @Test
    public void flow() throws InterruptedException {

        login.EnterUserName("perfecthelp");
        login.Enterpassword("test");
        login.clicksigninbutton();
        Thread.sleep(1500);
        Assert.assertTrue(driver.getPageSource().contains("New Requests"));

    }

    @Test
    public void negative_flow() throws InterruptedException{

     login.EnterUserName("test");
     login.Enterpassword("test");
     login.clicksigninbutton();
     Thread.sleep(2000);
     Assert.assertTrue(driver.findElement(By.id("login-error-msg")).isDisplayed(),"Incorrect username or password entered. Please try again.");
     }

}













