package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class homePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By logo = By.cssSelector("[aria-label='Logo']");
    private By searchBox = By.name("q");

    public homePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://testcase.myideasoft.com/");
    }

    public boolean isLogoDisplayed() {
        WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
        return logoElement.isDisplayed();
    }

    public void searchForProduct(String productName) {
        WebElement searchBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        searchBoxElement.sendKeys(productName + "\n");  // Simulates pressing Enter key after entering the search term
    }
}
