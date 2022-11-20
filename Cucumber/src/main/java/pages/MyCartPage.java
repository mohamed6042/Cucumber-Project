package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyCartPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By myBasket = By.xpath("//p[text()='Your basket']");
    private By shopButton = By.xpath("//a[text()='Shop']");
    private By phonesAndDevicesChoice = By.xpath("//a[text()='Phones and Devices']");
    private By accessories = By.linkText("Accessories");
    private By searchBox = By.id("search-q");
    private By showSearchResultsButton = By.id("searchBtn");
    private By proceedCheckout = By.xpath("//button[contains(text(),'Proceed to Checkout')]");


    public MyCartPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public AccessoriesPage clickOnAccessories (){
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(shopButton)).moveToElement(driver.findElement(phonesAndDevicesChoice)).perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(accessories)));
        driver.findElement(accessories).click();
        return new AccessoriesPage(driver);
    }

    public SearchResultsPage searchForProduct(String productName){
        driver.findElement(searchBox).sendKeys(productName);
        driver.findElement(showSearchResultsButton).click();
        return new SearchResultsPage(driver);
    }

    public CheckoutPage clickOnProceedToCheckOutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedCheckout));
        driver.findElement(proceedCheckout).click();
        return new CheckoutPage(driver);
    }




}
