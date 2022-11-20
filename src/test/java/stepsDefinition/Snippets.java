package stepsDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;
import pages.CheckoutPage;
import pages.MainPage;
import pages.MyCartPage;

import java.util.Collections;
import java.util.List;

public class Snippets
{
    protected MyCartPage myCartPage;
    protected CheckoutPage checkoutPage;
    protected MainPage mainPage;
    protected WebDriver driver;
    protected SoftAssert softAssert = new SoftAssert();
    @Before
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(infoBar());
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
    }
    private ChromeOptions infoBar(){
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        return options;
    }

    @Given("user on main page of Vodafone shop")
    public void user_on_main_page_of_vodafone_shop() {
        driver.get("https://eshop.vodafone.com.eg/shop/home");

    }
    @When("choose the products and procedd to checkout")
    public void choose_the_products_and_proceed_to_checkout() {
        var mobilesPage =  mainPage.clickOnViewAllMobiles();
        mobilesPage.selectFromDropDown();
        var mobileDetailsPage = mobilesPage.clickOnMobile();
        myCartPage =mobileDetailsPage.clickOnAddToCart();
        var accessories =myCartPage.clickOnAccessories();
        var headphoneDetails =accessories.clickOnHeadphone();
        softAssert.assertEquals(headphoneDetails.AvailabilityOfProduct(),"Available","this product is unavailable");
        headphoneDetails.clickOnAddtoCart();
        var searchResultsPage= myCartPage.searchForProduct("smart");
        var smartDetails =searchResultsPage.clickOnSilverSmartWatch();
        softAssert.assertEquals(smartDetails.AvailabilityOfProduct(),"Available","this product is unavailable");
        smartDetails.clickToCompletePurchaseProcess();
    }
    @And("enter all necessary info")
    public void enter_all_necessary_info(DataTable table) {
        List<List<String>> data = table.asLists(String.class);;
        checkoutPage =myCartPage.clickOnProceedToCheckOutButton();
        checkoutPage.fillDeliveryOptions(data.get(0).get(0),data.get(0).get(1),data.get(0).get(2),data.get(0).get(3),data.get(0).get(4),data.get(0).get(5));
        checkoutPage.fillPersonalInfo(data.get(0).get(6),data.get(0).get(7),data.get(0).get(8));
        checkoutPage.choosePaymentMethod();

    }
    @Then("will can complete by press submit button")
    public void will_can_complete_by_press_submit_button() {

        softAssert.assertEquals(checkoutPage.finalStepToCompletePurchase(),"Submit","it's a wrong name button");
        softAssert.assertAll();
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
