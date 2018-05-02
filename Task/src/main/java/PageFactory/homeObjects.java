package PageFactory;

import General.Main;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class homeObjects extends Page{

    public homeObjects(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

//    Nav bar
//    MOVING AND STORAGE

//    Moving & Storage in nav bar
    @FindBy(css=".nav-menu>li:nth-child(7)")
    public static WebElement byMovingAndStorage;

//    Moving in nav bar
    @FindBy(partialLinkText="Moving")
    public static WebElement byMoving;
//    Click on Moving & Storage
    public void clickMovingAndStorage(){
        Actions action = new Actions(driver);
        action.moveToElement(byMovingAndStorage).moveToElement(byMoving).click().build().perform();
    }

//    International moving in nav bar
    @FindBy(partialLinkText="International moving")
    public static WebElement byInternationalMoving;
//    Click on International moving
    public void clickInternationalMoving(){
        Actions action = new Actions(driver);
        action.moveToElement(byMovingAndStorage).moveToElement(byInternationalMoving).click().build().perform();
    }

//    Search bar
//    Get Started button
    @FindBy(css=".text-left > button")
    public static WebElement byGetStarted ;
//    Click on Get Started
    public void clickGetStarted() {
        byGetStarted.click();
    }


    @FindBy(css=".text-left > button")
    public static WebElement byBookingTab ;
    //    Click on Get Started
    public void clickBookingTab() {
        byBookingTab.click();
    }

}
