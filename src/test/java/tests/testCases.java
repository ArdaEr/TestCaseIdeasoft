package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.homePage;
import pages.searchPage;
import pages.productPage;
import pages.cartPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class testCases {
    private WebDriver driver;
    private homePage homePage;
    private searchPage searchPage;
    private productPage productPage;
    private cartPage cartPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new homePage(driver);
        searchPage = new searchPage(driver);
        productPage = new productPage(driver);
        cartPage = new cartPage(driver);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void AllTests() {
        try {
            // Step 1: Visit the website
            homePage.open();

            // Verify the logo is displayed
            assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on the homepage.");

            // Step 2: Search for a product
            homePage.searchForProduct("ürün");

            // Step 3: Select a product from the search results
            searchPage.selectFirstProduct();

            // Step 4: Add the product to the cart
            productPage.selectQuantity(5);
            productPage.addToCart();

            // Step 5: Verify "SEPETİNİZE EKLENMİŞTİR" message
            assertTrue(productPage.isConfirmationMessageDisplayed(), "\"SEPETİNİZE EKLENMİŞTİR\" yazısı görünmüyor.");

            // Step 6: Go to the cart page
            driver.get("https://testcase.myideasoft.com/sepet");

            // Verify the cart title
            assertTrue(cartPage.isCartTitleDisplayed(), "Sepet sayfası açılmadı.");

            // Verify the quantity of the product in the cart
            int quantity = cartPage.getProductQuantity();
            assertEquals(quantity, 5, "Sepetteki ürün adedi yanlış.");
        } catch (Exception e) {
            takeScreenshot(driver, "testAddProductToCart");
            throw e;
        }
    }

    private void takeScreenshot(WebDriver driver, String fileName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Path destinationPath = Paths.get("screenshots", fileName + "_" + timestamp + ".png");
        try {
            Files.createDirectories(destinationPath.getParent());
            Files.copy(screenshot.toPath(), destinationPath);
            System.out.println("Screenshot taken: " + destinationPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
