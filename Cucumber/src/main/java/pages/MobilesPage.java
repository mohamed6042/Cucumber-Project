package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MobilesPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By sortByButton = By.id("selectOpt");
    String selectedOption = "Price(lowest first)";
    private By xiaomiRedmiMobile = By.xpath("//span[contains(text(),'Xiaomi Redmi 10C Ram 4G+64GB')]");

    public MobilesPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }



    public void selectFromDropDown(){
        Select selectOption = new Select(driver.findElement(sortByButton));
        selectOption.selectByVisibleText(selectedOption);
    }

    public MobileDetailsPage clickOnMobile(){
        wait.until(ExpectedConditions.elementToBeClickable(xiaomiRedmiMobile));
        driver.findElement(xiaomiRedmiMobile).click();
        return new MobileDetailsPage(driver);
    }


}
