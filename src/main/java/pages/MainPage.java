package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    private By viewAllMobilesButton = By.id("cat10021");
    private By language = By.linkText("English");


    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    private void selectEnglishLanguage(){
        driver.findElement(language).click();
    }

    public MobilesPage clickOnViewAllMobiles(){
        selectEnglishLanguage();
        driver.findElement(viewAllMobilesButton).click();
        return new MobilesPage(driver);
    }
}
