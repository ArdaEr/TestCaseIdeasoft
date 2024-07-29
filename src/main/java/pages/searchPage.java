package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class searchPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By firstProduct = By.cssSelector("a[title='Ürün']");

    public searchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectFirstProduct() {
        try {
            // Wait for the product element to be visible and scroll into view
            WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProduct));
            // Click on the product element
            productElement.click();
        } catch (Exception e) {
            // Print the exception message if the product is not found
            System.out.println("Product not found: " + e.getMessage());
            throw e;
        }
    }
}
