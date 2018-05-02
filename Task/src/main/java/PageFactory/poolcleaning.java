package PageFactory;
import General.Helper;
import General.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class poolcleaning extends Page{

    public poolcleaning(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Pool Cleaning Bookings")
    public static WebElement BypoolcleaningTab;

    public void clickpoolcleaningTab() {
        BypoolcleaningTab.click();
    }

    @FindBy(xpath = "//tr[@class='ng2-smart-row selected']")
    public static WebElement Byfirstbooking;
    public void clickfirstbooking() {
        Byfirstbooking.click();

    }

    @FindBy(css = "body > app > main > pages > div > div > forms > booking-view > div > div:nth-child(2) > div > booking-detail-pool-cleaning > ba-card > div > div.card-body > div > div:nth-child(2) > div")
    public static WebElement ByRefID;
    public void getRefID() {
        Helper.refId =ByRefID.getText();
    }

    @FindBy(className = "btn-danger")
    public static WebElement ByRejectButton;

    public static void clickRejectButton() {
        ByRejectButton.click();
    }
}
