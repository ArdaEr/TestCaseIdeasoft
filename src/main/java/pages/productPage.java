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
        WebElement quantityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityDropDown));
        Select quantitySelect = new Select(quantityElement);
        quantitySelect.selectByValue(String.valueOf(quantity));
    }

    public void addToCart() {
        WebElement addToCartElement = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartElement.click();
    }

    public boolean isConfirmationMessageDisplayed() {
        WebElement confirmationMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return confirmationMessageElement.isDisplayed();
    }
}
