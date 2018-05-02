package PageFactory;

import org.openqa.selenium.WebDriver;

public abstract class Page {

    protected WebDriver driver;

    protected Page(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
