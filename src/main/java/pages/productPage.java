package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class productPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By quantityDropDown = By.id("qty-input");
    private By addToCartButton = By.cssSelector("[data-selector='add-to-cart']");
    private By confirmationMessage = By.cssSelector(".shopping-information-cart-inside");

    public productPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectQuantity(int quantity) {
        // Wait for the quantity drop-down to be visible and select the specified quantity
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityDropDown));
        Select quantitySelect = new Select(quantityElement);
        quantitySelect.selectByValue(String.valueOf(quantity));
    }

    public void addToCart() {
        // Wait for the add-to-cart button to be clickable and click it
        WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartElement.click();
    }

    public boolean isConfirmationMessageDisplayed() {
        // Wait for the confirmation message to be visible
        WebElement confirmationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return confirmationMessageElement.isDisplayed();
    }
}