package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeadphoneDeatilsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By confirmSign = By.xpath("//div/p[contains(text(),'Available')][@class='fnt-size-18']");
    private By addToCart = By.xpath("//div[@class='addToBasket-btn']/button");

    public HeadphoneDeatilsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public String AvailabilityOfProduct(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmSign));
        return   driver.findElement(confirmSign).getText();
    }

    public MyCartPage clickOnAddtoCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        driver.findElement(addToCart).click();
        return new MyCartPage(driver);
    }


}
