package TestCases;

import General.Helper;
import General.MainCall;
import PageFactory.homecleaning;
import PageFactory.login;
import PageFactory.pendingconfirmation;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import PageFactory.homeObjects;

public class Testcases_pendingconfirmation extends MainCall{
    login login;
    homecleaning homecleaning5 ;
    homeObjects homeObjects;
    pendingconfirmation pendingconfirmation;


    @BeforeMethod
    public void testInit() {
        // Load the page in the browser
        login = PageFactory.initElements(driver, login.class);
        homecleaning5 = PageFactory.initElements(driver, homecleaning.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
        pendingconfirmation = PageFactory.initElements(driver, pendingconfirmation.class);
    }

    @Test
    public void Pending_Confirmation_Tab_verification_flow() throws InterruptedException {

        login.flow();
        homecleaning5.clickbookingTab();
        Thread.sleep(500);
        pendingconfirmation.clickPendingconfirmation();
        Assert.assertTrue(driver.getPageSource().contains("Pending Confirmation"));
    }

    @Test
    public void Mark_selected_as_delivered_flow() throws InterruptedException {

        login.flow();
        homecleaning5.clickbookingTab();
        Thread.sleep(500);
        pendingconfirmation.clickPendingconfirmation();
        Thread.sleep(6000);
        pendingconfirmation.clickfirstcheckbox();
        Thread.sleep(200);
        pendingconfirmation.clickMarkSelectedasDelivered();
        Thread.sleep(500);
        pendingconfirmation.clickYesbutton();
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
        Assert.assertTrue(driver.findElement(By.id("success-message")).isDisplayed(), "Success! Booking marked as delivered.");


    }

    @Test
    public void Mark_selected_as_delivered_from_detailpage() throws InterruptedException {

        login.flow();
        homecleaning5.clickbookingTab();
        Thread.sleep(500);
        pendingconfirmation.clickPendingconfirmation();
        Thread.sleep(6000);
        pendingconfirmation.clickfirstrecord();
        Thread.sleep(1000);
        pendingconfirmation.clickMarkSelectedasDelivered();
        Thread.sleep(500);
        pendingconfirmation.clickYesbutton();
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
       Assert.assertTrue(driver.findElement(By.id("success-message")).isDisplayed(), "Success! Booking marked as delivered.");
    }
}


