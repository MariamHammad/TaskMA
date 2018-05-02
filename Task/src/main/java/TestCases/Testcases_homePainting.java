package TestCases;

import General.Helper;
import General.Main;
import General.MainCall;
import PageFactory.homePainting;
import PageFactory.homeObjects;
import PageFactory.homecleaning;
import PageFactory.login;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testcases_homePainting extends MainCall {

    login login;
    homeObjects homeObjects;
    homePainting homepainting;
    homecleaning homecleaning3;

    @BeforeMethod
    public void testInit(){
        login = PageFactory.initElements(driver, login.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);
        homepainting = PageFactory.initElements(driver, homePainting.class);
        homecleaning3 = PageFactory.initElements(driver, homecleaning.class);
    }

    @Test
    public void NewHomePainting_Tab_verification_flow() {
        login.flow();
        homecleaning3.clickbookingTab();
        homecleaning3.clickOpenTab();
        homepainting.clickhomepaintingTab();
        Assert.assertTrue(driver.getPageSource().contains("Painting Bookings"));

    }

    @Test
    public void Accept_home_painting_Booking_flow() throws InterruptedException {
        login.flow();
        homecleaning3.clickbookingTab();
        Thread.sleep(500);
        homecleaning3.clickOpenTab();
        Thread.sleep(1000);
        homepainting.clickhomepaintingTab();
        Thread.sleep(1000);
        homepainting.clickfirstpaintingbooking();
        Thread.sleep(500);
        homecleaning3.clickAcceptButton();
        Thread.sleep(1000);
        homepainting.enterAcceptTime("10:00");
        Thread.sleep(500);
        homepainting.clickAcceptbutton();
        Assert.assertTrue(driver.getPageSource().contains("Your painting booking is confirmed!"));

    }

    @Test
    public void Painting_Booking_Rejection_flow() throws InterruptedException {
        login.flow();
        homecleaning3.clickbookingTab();
        homecleaning3.clickOpenTab();
        Thread.sleep(1000);
        homepainting.clickhomepaintingTab();
        Thread.sleep(1000);
        homepainting.clickfirstpaintingbooking();
        Thread.sleep(1000);
        homepainting.getRefID();
        homepainting.clickRejectbutton();
        Thread.sleep(1000);
        homepainting.clickfirstreason();
        homepainting.clickYesbutton();
        Thread.sleep(1000);
        homecleaning3.clickRejectedlink();
        Thread.sleep(500);
        homecleaning3.clickRejectedBookingslink();
        Thread.sleep(500);
        homepainting.clickBookingRef(Helper.refId);
        String Actualtext = driver.findElement(By.xpath("/html/body/app/main/pages/div/div/forms/basic-tables/div/div[2]/ba-card/div/div/ng2-smart-table/div/table/tbody/tr[1]/td[10]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
        System.out.print(Actualtext);
        Assert.assertEquals(Helper.refId,Actualtext,"Matched");
    }
}