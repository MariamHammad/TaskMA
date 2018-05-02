package TestCases;
import General.Helper;
import General.Main;
import General.MainCall;
import PageFactory.homeObjects;
import PageFactory.homecleaning;
import PageFactory.login;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PageFactory.windowcleaning;


public class Testcases_windowcleaning extends MainCall {

    login login;
    homeObjects homeObjects;
    windowcleaning windowcleaning;
    homecleaning homecleaning4;


    @BeforeMethod
    public void testInit() {
        login = PageFactory.initElements(driver, login.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
        windowcleaning = PageFactory.initElements(driver, windowcleaning.class);
        homecleaning4 = PageFactory.initElements(driver, homecleaning.class);
    }

    @Test
    public void Windowcleaning_Tab_verification_flow() throws InterruptedException {

        login.flow();
        homecleaning4.clickbookingTab();
        homecleaning4.clickOpenTab();
        windowcleaning.clickWindowcleaningTab();
        Thread.sleep(1000);
        Assert.assertTrue(driver.getPageSource().contains("Window Cleaning Bookings"));
    }

    @Test
    public void Accept_Window_cleaning_Booking_flow() throws InterruptedException {

        login.flow();
        homecleaning4.clickbookingTab();
        homecleaning4.clickOpenTab();
        Thread.sleep(1000);
        windowcleaning.clickWindowcleaningTab();
        Thread.sleep(1000);
        windowcleaning.clickfirstrow();
        Thread.sleep(1000);
        windowcleaning.clickAcceptButton();
        Assert.assertTrue(driver.getPageSource().contains("Your window cleaning booking is confirmed!"));
    }

    @Test
    public void Reject_Window_cleaning_Booking_flow() throws InterruptedException {

        login.flow();
        homecleaning4.clickbookingTab();
        homecleaning4.clickOpenTab();
        Thread.sleep(1000);
        windowcleaning.clickWindowcleaningTab();
        Thread.sleep(1000);
        windowcleaning.clickfirstrow();
        Thread.sleep(1000);
        windowcleaning.getRefID();
        windowcleaning.ClickRejectButton();
        Thread.sleep(500);
        windowcleaning.RejectReason();
        windowcleaning.ClickYes();
        Thread.sleep(1500);
        homecleaning4.clickRejectedlink();
        Thread.sleep(500);
        homecleaning4.clickRejectedBookingslink();
        Thread.sleep(1000);
        homecleaning4.clickBookingRef(Helper.refId);
        String Actualtext = driver.findElement(By.xpath("/html/body/app/main/pages/div/div/forms/basic-tables/div/div[2]/ba-card/div/div/ng2-smart-table/div/table/tbody/tr[1]/td[10]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
        System.out.print(Actualtext);
        Assert.assertEquals(Helper.refId,Actualtext,"Matched");
    }
}