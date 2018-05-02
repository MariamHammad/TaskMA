package PageFactory;
import General.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class pendingconfirmation extends Page {

    public pendingconfirmation(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Pending Confirmation")
    public static WebElement BypendingconfirmationTab;

    public void clickPendingconfirmation() {
        BypendingconfirmationTab.click();
    }

    @FindBy(xpath = "//html//tr[1]/td[1]/input[1]")
    public static WebElement Byclickfirstbox;

    public void clickfirstcheckbox (){
            Byclickfirstbox.click();
}

    @FindBy(className = "btn-success")
    public static WebElement ByMarkasDelivered;

    public void  clickMarkSelectedasDelivered (){
        ByMarkasDelivered.click();
    }

    @FindBy(xpath = "//button[@class='btn btn-info'][contains(text(),'Yes')]")
    public static WebElement ByclickYes;

    public void clickYesbutton(){
        ByclickYes.click();
    }

    @FindBy(className = "ng2-smart-row")
    public static WebElement Byfirstrecord;

    public void clickfirstrecord () {
        Byfirstrecord.click();
    }

}



