package PageFactory;

import General.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homecleaning extends Page {


    public homecleaning(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //  this.action = action;
    }

    @FindBy(linkText = "Booking Requests")
    public static WebElement BybookingTab;

    public static void clickbookingTab() {
        BybookingTab.click();
    }

    @FindBy(linkText = "Open")
    public static WebElement ByOpenTab;

    public static void clickOpenTab() {
        ByOpenTab.click();
    }

    @FindBy(linkText = "Cleaning Bookings")
    public static WebElement BycleaningbookingTab;

    public static void clickcleaningbookingTab() {
        BycleaningbookingTab.click();
    }

    @FindBy(xpath = "//tr[@class='ng2-smart-row selected']")
    public static WebElement Byfirstbooking;

    public static void clickfirstbooking() {
        Byfirstbooking.click();
    }

    @FindBy(css = "body > app > main > pages > div > div > forms > booking-view > div > div:nth-child(2) > div > booking-detail-cleaning > ba-card > div > div.card-body > div > div:nth-child(2) > div")
    public static WebElement ByRefID;
    public void getRefID() {
        Helper.refId =ByRefID.getText();
    }

    @FindBy(className = "btn-success")
    public static WebElement ByAcceptButton;

    public static void clickAcceptButton() {
        ByAcceptButton.click();
    }


    @FindBy(xpath = "/html/body/modal/div/div/modal-footer/div/button[2]")
    public static WebElement ByDismiss;

    public static void clickDismiss() {
        ByDismiss.click();
    }

    @FindBy(linkText = "Accepted & Unassigned")
    public static WebElement ByAccepetedUnassignedTab;

    public static void clickAcceptedUnassignedTab() {
        ByAccepetedUnassignedTab.click();
    }

    @FindBy(xpath = "/html/body/app/main/pages/div/div/forms/basic-tables/div/div[2]/ba-card/div/div/ng2-smart-table/div/table/thead/tr[2]/th[15]/ng2-smart-table-filter/div/input")
    public static WebElement BycleaningRefID;

    public static void clickcleaningRefID (String m){
        BycleaningRefID.sendKeys(m);
    }

    @FindBy(className = "btn-info")
    public static WebElement ByRejectButton;

    public static void clickRejectButton() {
        ByRejectButton.click();
    }

    @FindBy(xpath = "//html//modal[@class='modal fade in']//span[1]/input[1]")
    public static WebElement ByFirstReason;

    public static void clickFirstReason() {
        ByFirstReason.click();
    }

    @FindBy(xpath = "//button[@class='btn btn-info'][contains(text(),'Yes')]")
    public static WebElement ByclickYes;

    public static void clickYesButton() {
        ByclickYes.click();
    }

    @FindBy(linkText = "Rejected")
    public static WebElement ByRejectedlink;

    public static void clickRejectedlink() {
        ByRejectedlink.click();
    }

    @FindBy(linkText = "Rejected Bookings")
    public static WebElement ByRejectedBookingslink;

    public static void clickRejectedBookingslink() {
        ByRejectedBookingslink.click();
    }

    @FindBy(xpath = "/html[1]/body[1]/app[1]/main[1]/pages[1]/div[1]/div[1]/forms[1]/basic-tables[1]/div[1]/div[2]/ba-card[1]/div[1]/div[1]/ng2-smart-table[1]/div[1]/table[1]/thead[1]/tr[2]/th[10]/ng2-smart-table-filter[1]/div[1]/input[1]")
    public static WebElement ByBookingRefID;


    public static void clickBookingRef(String s) {
        ByBookingRefID.sendKeys(s);
    }
}