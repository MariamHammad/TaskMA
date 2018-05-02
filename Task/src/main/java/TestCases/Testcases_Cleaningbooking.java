package TestCases;

import General.Helper;
import General.MainCall;
import PageFactory.homecleaning;
import PageFactory.login;
import PageFactory.homeObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;


public class Testcases_Cleaningbooking extends MainCall {

    login login;
    homecleaning homecleaning ;
    homeObjects homeObjects;


@BeforeMethod
    public void testInit() {
        // Load the page in the browser
    login = PageFactory.initElements(driver, login.class);
    homecleaning = PageFactory.initElements(driver, homecleaning.class);
    homeObjects = PageFactory.initElements(driver, homeObjects.class);
}

    @Test
    public void Newbooking_Tab_verification_flow() throws InterruptedException {
        login.flow();
        homecleaning.clickbookingTab();
        homecleaning.clickOpenTab();
        homecleaning.clickcleaningbookingTab();
        Thread.sleep(2000);
       Assert.assertTrue(driver.getPageSource().contains("Cleaning Bookings"));
    }

    @Test
    public void Booking_Acceptance_without_maid_flow() throws InterruptedException {
        login.flow();
        homecleaning.clickbookingTab();
        homecleaning.clickOpenTab();
        homecleaning.clickcleaningbookingTab();
        Thread.sleep(1500);
        homecleaning.clickfirstbooking();
        Thread.sleep(500);
        homecleaning.getRefID();
        Thread.sleep(1000);
        homecleaning.clickAcceptButton();
        Thread.sleep(5000);
        Assert.assertTrue(driver.findElement(By.className("modal-header")).isDisplayed(),"Cleaning booking confirmed! Please select cleaners to assign");
        homecleaning.clickDismiss();
    }

    @Test
    public void Booking_Rejection_flow() throws InterruptedException {
        login.flow();
        homecleaning.clickbookingTab();
        homecleaning.clickOpenTab();
        homecleaning.clickcleaningbookingTab();
        Thread.sleep(1000);
        homecleaning.clickfirstbooking();
        Thread.sleep(500);
        homecleaning.getRefID();
        homecleaning.clickRejectButton();
        Thread.sleep(1000);
        homecleaning.clickFirstReason();
        homecleaning.clickYesButton();
        Thread.sleep(3000);
        homecleaning.clickRejectedlink();
        Thread.sleep(500);
        homecleaning.clickRejectedBookingslink();
        homecleaning.clickBookingRef(Helper.refId);
        String Actualtext = driver.findElement(By.xpath("/html/body/app/main/pages/div/div/forms/basic-tables/div/div[2]/ba-card/div/div/ng2-smart-table/div/table/tbody/tr[1]/td[10]/ng2-smart-table-cell/table-cell-view-mode/div/div")).getText();
        System.out.print(Actualtext);
        Assert.assertEquals(Helper.refId,Actualtext,"Matched");
    }


}
