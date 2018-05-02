package General;

import PageFactory.login;
import TestCases.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.*;
import org.testng.collections.Lists;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.text;
import static org.testng.TestNGAntTask.Mode.testng;

public class Main {
    public static WebDriver driver;
    //    public static Actions action;
    public static Random rand = new Random();



  /*  testcases_login Logintestcase;
    Testcases_Cleaningbooking cleaningbooking;
    Testcases_poolcleaning poolcleaning;
    Testcases_homePainting hompainting;
    Testcases_windowcleaning windowcleaning;
    Testcases_pendingconfirmation pendingconfirmation;
    Testcases_leadRequest leadRequest;
    Testcases_maids maids;


  /*  @BeforeTest
    public void beforeWebdriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions op = new ChromeOptions();
        op.addArguments("--start-maximized");
        driver = new ChromeDriver(op);

        driver.get("https://uat1-operations.servicemarket.com");

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);


    }

    @Ignore
    public void t001_Logincase() {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

    }

    @Test
    public void t002_homecleaning() throws InterruptedException {

        Logintestcase = new testcases_login();
        Logintestcase.flow();
        cleaningbooking = new Testcases_Cleaningbooking();
        cleaningbooking.Newbooking_Tab_verification_flow();
    }

    @Ignore
    public void t003_homecleaning1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        cleaningbooking = new Testcases_Cleaningbooking();
        cleaningbooking.Booking_Acceptance_without_maid_flow();

    }

    @Ignore
    public void t004_homecleaning2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        cleaningbooking = new Testcases_Cleaningbooking();
        cleaningbooking.Booking_Rejection_flow();
    }

    @Test
    public void t005_poolcleaning() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        poolcleaning = new Testcases_poolcleaning();
        poolcleaning.Newpoolcleaning_Tab_verification_flow();
    }

    @Ignore
    public void t006_poolcleaning1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        poolcleaning = new Testcases_poolcleaning();
        poolcleaning.Accept_Pool_cleaning_Booking_flow();
    }

    @Ignore
    public void t007_poolcleaning2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        poolcleaning = new Testcases_poolcleaning();
        poolcleaning.Reject_Pool_cleaning_Booking_flow();
    }

    @Ignore
    public void t008_homepainting() throws InterruptedException {

        Logintestcase = new testcases_login();
        Logintestcase.flow();
        hompainting = new Testcases_homePainting();
        hompainting.NewHomePainting_Tab_verification_flow();
    }

    @Ignore
    public void t009_hompainting1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        hompainting = new Testcases_homePainting();
        hompainting.Accept_home_painting_Booking_flow();
    }

    @Ignore
    public void t010_hompainting2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        hompainting = new Testcases_homePainting();
        hompainting.Painting_Booking_Rejection_flow();
    }

    @Ignore
    public void t011_windowcleaning() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        windowcleaning = new Testcases_windowcleaning();
        windowcleaning.Windowcleaning_Tab_verification_flow();
    }


    @Ignore
    public void t012_windowcleaning1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        windowcleaning = new Testcases_windowcleaning();
        windowcleaning.Accept_Window_cleaning_Booking_flow();
    }

    @Ignore
    public void t013_windowcleaning2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        windowcleaning = new Testcases_windowcleaning();
        windowcleaning.Reject_Window_cleaning_Booking_flow();
    }

    @Ignore
    public void t014_pendingconfirmation() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        pendingconfirmation = new Testcases_pendingconfirmation();
        pendingconfirmation.Pending_Confirmation_Tab_verification_flow();
    }

    @Ignore
    public void t015_pendingconfirmation1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        pendingconfirmation = new Testcases_pendingconfirmation();
        pendingconfirmation.Mark_selected_as_delivered_flow();
    }

    @Ignore
    public void t016_pendingconfirmation2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        pendingconfirmation = new Testcases_pendingconfirmation();
        pendingconfirmation.Mark_selected_as_delivered_from_detailpage();
    }

    @Ignore
    public void t017_leadRequest() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        leadRequest = new Testcases_leadRequest();
        leadRequest.New_Request_Tab_flow();
    }

    @Ignore
    public void t018_leadRequest1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        leadRequest = new Testcases_leadRequest();
        leadRequest.Accept_New_Request_flow();
    }

    @Ignore
    public void t019_leadRequest2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        leadRequest = new Testcases_leadRequest();
        leadRequest.Survey_Required_flow();
    }

    @Ignore
    public void t020_leadRequest3() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        leadRequest = new Testcases_leadRequest();
        leadRequest.Reject_Request_flow();
    }

    @Ignore
    public void t021_maid() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        maids = new Testcases_maids();
        maids.View_maid_list_flow();
    }

    @Ignore
    public void t022_maid1() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        maids = new Testcases_maids();
        maids.View_maid_details_flow();
    }

    @Test
    public void t023_maid2() throws InterruptedException {
        Logintestcase = new testcases_login();
        Logintestcase.flow();

        maids = new Testcases_maids();
        maids.Register_maid_flow();


    }

    /*@AfterMethod
    public void afterWebdriver()
        {
        driver.close();
    }
    */
}