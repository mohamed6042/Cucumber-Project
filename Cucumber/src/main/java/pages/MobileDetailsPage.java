package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobileDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By confirmSign = By.xpath("//p[@class='fnt-size-18 fontLightEnAr']");
    private By addToCartButton = By.xpath("//div[@class='addToBasket-btn']/button");

    public MobileDetailsPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public String AvailabilityOfProduct(){
       return driver.findElement(confirmSign).getText();
    }

    public MyCartPage clickOnAddToCart(){
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
        return new MyCartPage(driver);
    }


}
