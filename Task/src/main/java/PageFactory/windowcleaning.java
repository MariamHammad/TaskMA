package PageFactory;
import General.Helper;
import General.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class windowcleaning extends Page {

    public windowcleaning(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Window Cleaning Bookings")
    public static WebElement BywindowcleaningTab;

    public void clickWindowcleaningTab() {
        BywindowcleaningTab.click();
    }

    @FindBy(xpath = "//tr[@class='ng2-smart-row selected']")
    public static WebElement Byclickfirstrow;

    public void clickfirstrow() {
        Byclickfirstrow.click();
    }

    @FindBy(css = "body > app > main > pages > div > div > forms > booking-view > div > div:nth-child(2) > div > booking-detail-window-cleaning > ba-card > div > div.card-body > div > div:nth-child(2) > div")
    public static WebElement ByRefID;
    public void getRefID() {
        Helper.refId =ByRefID.getText();
    }

    @FindBy(className = "btn-success")
    public static WebElement ByAcceptButton;

    public void clickAcceptButton() {
        ByAcceptButton.click();
    }

    @FindBy(className = "btn-danger")
    public static WebElement ByRejectbutton;

    public void ClickRejectButton (){
        ByRejectbutton.click();
    }

    @FindBy(xpath = "//html//modal[@class='modal fade in']//span[1]/input[1]")
    public static WebElement Byfirstreason;

    public void RejectReason (){
        Byfirstreason.click();
    }

    @FindBy(xpath = "//button[@class='btn btn-info'][contains(text(),'Yes')]")
    public static WebElement Byclickyes;

    public void ClickYes (){
        Byclickyes.click();
    }




}