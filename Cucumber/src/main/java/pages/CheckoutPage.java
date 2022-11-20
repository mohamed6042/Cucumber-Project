package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By selectCityChoice = By.xpath("(//div/select)[1]");
    private By selectAreaChoice = By.xpath("(//div/select)[2]");
    private By deliverToAddress = By.xpath("//div[contains(@class,'DelivaryToAddress')]");
    private By streetName = By.xpath("//input[@placeholder='Street Name']");
    private By buildingNumber = By.xpath("//input[@formcontrolname='buildingNumber']");
    private By floorNumber = By.xpath("//input[@formcontrolname='floorNumber']");
    private By flatNumber = By.xpath("//input[@formcontrolname='appartmentNumber']");
    private By confirmDelivery = By.xpath("(//button[@type='submit'])[3]");
    private By fullName = By.xpath("//input[@placeholder='Full Name']");
    private By email = By.xpath("//input[@placeholder='Email']");
    private By mobileNumber = By.xpath("//input[@formcontrolname='phoneNum']");
    private By confirmPersonalInfo = By.id("shippingAddressContinue");
    private By confirmPaymentMethod = By.id("paymentMethodsBtnId");
    private By payInCash = By.id("exampleRadio0");
    private By submitButton = By.id("checkout-submit");

    public CheckoutPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    public void fillDeliveryOptions(String city,String area,String street,String buildNo,String floorNo,String flatNo){
        wait.until(ExpectedConditions.elementToBeClickable(selectCityChoice));
        Select selectCity = new Select(driver.findElement(selectCityChoice));
        selectCity.selectByVisibleText(city);
        Select selectArea = new Select(driver.findElement(selectAreaChoice));
        selectArea.selectByVisibleText(area);
        driver.findElement(deliverToAddress).click();
        driver.findElement(streetName).sendKeys(street);
        driver.findElement(buildingNumber).sendKeys(buildNo);
        driver.findElement(floorNumber).sendKeys(floorNo);
        driver.findElement(flatNumber).sendKeys(flatNo);
        driver.findElement(confirmDelivery).submit();
    }

    public void fillPersonalInfo(String name,String emailOf,String no){
        wait.until(ExpectedConditions.elementToBeClickable(fullName));
        driver.findElement(fullName).sendKeys(name);
        driver.findElement(email).sendKeys(emailOf);
        driver.findElement(mobileNumber).sendKeys(no);
        driver.findElement(confirmPersonalInfo).click();
    }

    public void choosePaymentMethod(){
        driver.findElement(payInCash).click();
        driver.findElement(confirmPaymentMethod).click();
    }

    public String finalStepToCompletePurchase(){
       return driver.findElement(submitButton).getText();
    }
}
