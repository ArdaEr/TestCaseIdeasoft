package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class cartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By cartTitle = By.id("cart-content");
    private By productQuantity = By.cssSelector("[data-selector='qty']");

    public cartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isCartTitleDisplayed() {
        WebElement cartTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle));
        return cartTitleElement.isDisplayed();
    }

    public int getProductQuantity() {
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantity));
        String quantityText = quantityElement.getAttribute("value");
        return Integer.parseInt(quantityText);
    }
}

