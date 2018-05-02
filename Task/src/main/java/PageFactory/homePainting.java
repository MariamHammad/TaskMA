package PageFactory;
import General.Helper;
import General.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homePainting extends Page {

    public homePainting(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Painting Bookings")
    public static WebElement ByhomepaintingTab;

    public void clickhomepaintingTab() {
        ByhomepaintingTab.click();
    }


    @FindBy(xpath = "//tr[@class='ng2-smart-row selected']")
    public static WebElement Byfirstbooking;

    public void clickfirstpaintingbooking() {
        Byfirstbooking.click();

    }


    @FindBy(id = "acceptance-time")
    public static WebElement ByAcceptTime;

    public void enterAcceptTime(String AcceptTime) {
        ByAcceptTime.sendKeys(AcceptTime);

    }

    @FindBy(css = "body > modal.modal.fade.in > div > div > modal-footer > div > button.btn.btn-success")
    public static WebElement ByAcceptbookingbutton;

    public void clickAcceptbutton() {
        ByAcceptbookingbutton.click();
    }

    @FindBy(css = "body > app > main > pages > div > div > forms > booking-view > div > div:nth-child(2) > div > booking-detail-painting > ba-card > div > div.card-body > div > div:nth-child(2) > div")
    public static WebElement ByRefID;
    public void getRefID() {
        Helper.refId =ByRefID.getText();
    }

    @FindBy(className = "btn-danger")
    public static WebElement ByRejectingbutton;

    public void clickRejectbutton() {
        ByRejectingbutton.click();
    }

    @FindBy(css = "body > modal.modal.fade.in > div > div > modal-body > div > span:nth-child(2) > input")
    public static WebElement Byfirstreason;

    public void clickfirstreason() {
        Byfirstreason.click();
    }

    @FindBy(css = "body > modal.modal.fade.in > div > div > modal-footer > div > button:nth-child(1)\n")
    public static WebElement ByYesbutton;

    public void clickYesbutton() {
        ByYesbutton.click();
    }

    @FindBy(xpath = "/html[1]/body[1]/app[1]/main[1]/pages[1]/div[1]/div[1]/forms[1]/basic-tables[1]/div[1]/div[2]/ba-card[1]/div[1]/div[1]/ng2-smart-table[1]/div[1]/table[1]/thead[1]/tr[2]/th[10]/ng2-smart-table-filter[1]/div[1]/input[1]")
    public static WebElement ByBookingRefID;

    public static void clickBookingRef(String s) {
        ByBookingRefID.sendKeys(s);
    }
}


