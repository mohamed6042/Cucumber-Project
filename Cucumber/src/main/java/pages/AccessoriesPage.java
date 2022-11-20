package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccessoriesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By headphone = By.xpath("//span[contains(text(),'JBL Charge')]");

    public AccessoriesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait( driver,Duration.ofSeconds(10));
    }

    public HeadphoneDeatilsPage clickOnHeadphone(){
        wait.until(ExpectedConditions.elementToBeClickable(headphone));
        driver.findElement(headphone).click();
        return new HeadphoneDeatilsPage(driver);
    }


}
