package PageFactory;
import General.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;

public class maids extends Page {

    public maids(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Maids")
    public static WebElement ByMaidsTab;

    public void clickMaidsTab() {
        ByMaidsTab.click();
    }

    @FindBy(linkText = "Maids List")
    public static WebElement ByMaidslistTab;

    public void clickMaidslistTab() {
        ByMaidslistTab.click();
    }

    @FindBy(xpath = "//tr[@class='ng2-smart-row selected']")
    public  static WebElement Byclickfirstmaid;

    public void clickfirstmaid (){
        Byclickfirstmaid.click();
    }


    @FindBy(linkText = "Register a Maid")
    public static WebElement ByRegisterMaidTab;

    public void clickRegisterMaidTab() {
        ByRegisterMaidTab.click();
    }

    @FindBy(id = "nickanme")
    public static WebElement Bynickname;

    public void enterNickName(String nickname) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        driver.findElement(By.id("nickanme")).sendKeys("Jenny"+randomInt);

    }

    @FindBy(id = "firstname")
    public static WebElement Byfirstname;

    public void enterFirstName(String firstname) {
        Byfirstname.sendKeys(firstname);
    }

    @FindBy(id = "lastanme")
    public static WebElement Bylastname;

    public void enterLasttName(String lastname) {
        Bylastname.sendKeys(lastname);
    }

    @FindBy(id = "maid-profile")
    public static WebElement ByUploadpic;

    public void uploadMaidpic (String path){
        driver.findElement(By.id("maid-profile")).click();
        ByUploadpic.sendKeys (path);
    }

    @FindBy(xpath = "//button[@class='btn btn-success right-separator-medium-for-register-btn']")
    public static WebElement ByclickRegister;

    public void clickRegister() {
        ByclickRegister.click();
    }



    /*Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(1000);
     driver.findElement(By.id("input-email")).sendKeys("Umairtesting"+ "+test"+randomInt +"@gmail.com");
*/
}



