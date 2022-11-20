package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By silverSmartWatch = By.xpath("//a[contains(*,'Silver')]");


    public SearchResultsPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public SmartWatchDetailsPage clickOnSilverSmartWatch(){
        wait.until(ExpectedConditions.elementToBeClickable(silverSmartWatch));
        driver.findElement(silverSmartWatch).click();
        return new SmartWatchDetailsPage(driver);
    }


}
