package PageFactory;
import General.Main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class leadRequest extends Page {

    public leadRequest(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Quotes Requests")
    public static WebElement ByQuoteRequest;

    public void clickQuoteRequest () {
        ByQuoteRequest.click();
    }

    @FindBy(linkText = "New Requests")
    public static WebElement ByNewRequest;

    public void clickNewRequest () {
        ByNewRequest.click();
    }

    @FindBy(xpath ="//tr[@class='ng2-smart-row selected']/td[1]" )
    public static WebElement Byfirstrow;

    public void clickfirstrow () {
        Byfirstrow.click();

    }

    @FindBy(className = "btn-success")
    public static WebElement ByAcceptRequest;

    public void AcceptRequest (){
        ByAcceptRequest.click();
    }

    @FindBy(xpath="//input[@class='form-control sm-small quote-textfield ng-untouched ng-pristine ng-valid']")
    public static WebElement byQuote ;

    public void EnterQuote(String Quote) {
        byQuote.sendKeys(Quote);
    }

    @FindBy(id = "survey")
    public static WebElement Bysurvey;

    public void SurveyRequired (){
        Bysurvey.click();
    }

    @FindBy(className = "btn-danger")
    public static WebElement ByRejectrequest;

    public void RejectRequest (){
        ByRejectrequest.click();
    }

    @FindBy(xpath = "//html//span[1]/input[1]")
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





