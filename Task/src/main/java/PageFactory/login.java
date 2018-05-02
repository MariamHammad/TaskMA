package PageFactory;

import General.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class login extends Page {

    public login(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

  @FindBy(id="username")
    public static WebElement byUserName ;
    public static void EnterUserName(String Username) {
        byUserName.sendKeys(Username);
    }

    @FindBy(id="inputPassword3")
    public static WebElement byPassword ;
    public static void Enterpassword(String password) {
        byPassword.sendKeys(password);
    }

    @FindBy(css="body > app > main > login > div > div.auth-block > form > div:nth-child(3) > div > button")
    public static WebElement bysigninbutton ;
    public static void clicksigninbutton() {
        bysigninbutton.click();
    }

    public static void flow() {
       EnterUserName("perfecthelp");
        Enterpassword("test");
       clicksigninbutton();
    }
}
