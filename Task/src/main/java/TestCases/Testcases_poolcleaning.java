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
import PageFactory.poolcleaning;


public class Testcases_poolcleaning extends MainCall {

    login login;
    homeObjects homeObjects;
    poolcleaning poolCleaning;
    homecleaning homecleaning2;


    @BeforeMethod
    public void testInit() {
        login = PageFactory.initElements(driver, login.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
        poolCleaning = PageFactory.initElements(driver, poolcleaning.class);
        homecleaning2 = PageFactory.initElements(driver, homecleaning.class);
    }

    @Test
    public void Newpoolcleaning_Tab_verification_flow() {
        login.flow();
        homecleaning2.clickbookingTab();
        homecleaning2.clickOpenTab();
        poolCleaning.clickpoolcleaningTab();
        Assert.assertTrue(driver.getPageSource().contains("Pool Cleaning Bookings"));

    }

    @Test
    public void Accept_Pool_cleaning_Booking_flow() throws InterruptedException {
        login.flow();
        homecleaning2.clickbookingTab();
        homecleaning2.clickOpenTab();
        Thread.sleep(1000);
        poolCleaning.clickpoolcleaningTab();
        Thread.sleep(1000);
        poolCleaning.clickfirstbooking();
        Thread.sleep(1000);
        homecleaning2.clickAcceptButton();
        Assert.assertTrue(driver.getPageSource().contains("Your pool cleaning booking is confirmed!"));

    }

    @Test
    public void Reject_Pool_cleaning_Booking_flow() throws InterruptedException {
        login.flow();
        homecleaning2.clickbookingTab();
        homecleaning2.clickOpenTab();
        Thread.sleep(1000);
        poolCleaning.clickpoolcleaningTab();
        Thread.sleep(1000);
        poolCleaning.clickfirstbooking();
        Thread.sleep(1000);
        poolCleaning.getRefID();
        poolCleaning.clickRejectButton();
        Thread.sleep(500);
        homecleaning2.clickFirstReason();
        homecleaning2.clickYesButton();
        Thread.sleep(1500);
        homecleaning2.clickRejectedlink();
        Thread.sleep(500);
        homecleaning2.clickRejectedBookingslink();
        Thread.sleep(1000);
        homecleaning2.clickBookingRef(Helper.refId);
        String Actualtext = driver.findElement(By.xpath("/html/body/app/main/pages/div/div/forms/basic-tables/div/div[2]/ba-card/div/div/ng2-smart-table/div/table/tbody/tr[1]/td[10]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
        System.out.print(Actualtext);
        Assert.assertEquals(Helper.refId,Actualtext,"Matched");
    }
}