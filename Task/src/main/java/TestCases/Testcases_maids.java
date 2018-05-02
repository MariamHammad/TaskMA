package TestCases;

import General.Helper;
import General.MainCall;
import PageFactory.maids;
import PageFactory.login;
import PageFactory.homeObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;


public class Testcases_maids extends MainCall {
    login login;
    maids maids ;
    homeObjects homeObjects;
    String nickname;

    @BeforeMethod
    public void testInit() {
        // Load the page in the browser
        login = PageFactory.initElements(driver, login.class);
        maids = PageFactory.initElements(driver, maids.class);
        homeObjects = PageFactory.initElements(driver, homeObjects.class);

    }

        @Test
        public void View_maid_list_flow() throws InterruptedException {

        login.flow();
        maids.clickMaidsTab();
        Thread.sleep(1000);
        maids.clickMaidslistTab();
        Assert.assertTrue(driver.getPageSource().contains("Maids List"));

    }

    @Test
    public void View_maid_details_flow() throws InterruptedException {

        login.flow();
        maids.clickMaidsTab();
        Thread.sleep(500);
        maids.clickMaidslistTab();
       Thread.sleep(2000);
       maids.clickfirstmaid();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.className("breadcrumb-item")).isDisplayed(),"Maid Details");
    }



    @Test
    public void Register_maid_flow() throws InterruptedException{

        login.flow();
        maids.clickMaidsTab();
        Thread.sleep(500);
        maids.clickRegisterMaidTab();
        Thread.sleep(1000);
        maids.enterNickName(nickname);
        maids.enterFirstName("Jellyy1");
        maids.enterLasttName("William1");
        maids.uploadMaidpic("C:\\Users\\Venturedive\\Desktop\\Screenshots\\bug.jpg");
        Thread.sleep(3000);
        maids.clickRegister();
        Assert.assertTrue(driver.getPageSource().contains("Register a Maid"));

    }


}
