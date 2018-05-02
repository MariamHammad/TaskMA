package TestCases;

import General.Helper;
import General.Main;
//import PageFactory.homePainting;
import General.MainCall;
import PageFactory.homeObjects;
//import PageFactory.homecleaning;
import PageFactory.leadRequest;
import PageFactory.login;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testcases_leadRequest extends MainCall {
    login login;
    homeObjects homeObjects;
    leadRequest leadrequest;


    @BeforeMethod
    public void testInit() {
        login = PageFactory.initElements(driver, login.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
        leadrequest = PageFactory.initElements(driver, leadRequest.class);
    }

    @Test
    public void New_Request_Tab_flow() throws InterruptedException {
        login.flow();
        leadrequest.clickNewRequest();
        Assert.assertTrue(driver.getPageSource().contains("New Requests"));
    }

    @Test
    public void Accept_New_Request_flow() throws InterruptedException{
        login.flow();
        leadrequest.clickNewRequest();
        Thread.sleep(3000);
        leadrequest.clickfirstrow();
        Thread.sleep(500);
        leadrequest.AcceptRequest();
        Thread.sleep(500);
        leadrequest.EnterQuote("1000");
        leadrequest.AcceptRequest();
        Thread.sleep(1000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
        String getText, textToBeMatch;
        boolean foundText;

        getText = driver.findElement(By.className("success")).getText();

        textToBeMatch = "Thank you!";
        foundText = getText.contains(textToBeMatch);

        System.out.println(getText);
        System.out.println(getText.contains("Thank you!"));

        Assert.assertTrue(foundText);
    }

    @Test
    public void Survey_Required_flow() throws InterruptedException {
        login.flow();
        leadrequest.clickNewRequest();
        Thread.sleep(3000);
        leadrequest.clickfirstrow();
        Thread.sleep(500);
        leadrequest.AcceptRequest();
        Thread.sleep(500);
        leadrequest.SurveyRequired();
        leadrequest.AcceptRequest();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
        String getText, textToBeMatch;
        boolean foundText;
        getText = driver.findElement(By.className("success")).getText();

        textToBeMatch = "Thank you!";
        foundText = getText.contains(textToBeMatch);

        System.out.println(getText);
        System.out.println(getText.contains("Thank you!"));

        Assert.assertTrue(foundText);
    }

    @Test
    public void Reject_Request_flow() throws InterruptedException {
        login.flow();
        leadrequest.clickNewRequest();
        Thread.sleep(3000);
        leadrequest.clickfirstrow();
        Thread.sleep(500);
        leadrequest.RejectRequest();
        Thread.sleep(500);
        leadrequest.RejectReason();
        leadrequest.ClickYes();
        Thread.sleep(2000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,-1000)", "");
        String getText, textToBeMatch;
        boolean foundText;
        getText = driver.findElement(By.className("success")).getText();

        textToBeMatch = "Request has been processed successfully";
        foundText = getText.contains(textToBeMatch);

        System.out.println(getText);
        System.out.println(getText.contains("Request has been processed successfully"));

        Assert.assertTrue(foundText);
    }

}