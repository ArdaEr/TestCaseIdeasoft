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
        // Set up WebDriver and initialize page objects
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
        // Quit the driver after tests are completed
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testVisitWebsite() {
        try {
            // Step 1: Visit the website
            homePage.open();

            // Verify the logo is displayed to confirm the homepage is loaded
            assertTrue(homePage.isLogoDisplayed(), "Logo is not displayed on the homepage.");
        } catch (Exception e) {
            // Take a screenshot if an exception occurs
            takeScreenshot(driver, "testVisitWebsite");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"testVisitWebsite"})
    public void testSearchProduct() {
        try {
            // Step 2: Search for a product
            homePage.searchForProduct("ürün");
        } catch (Exception e) {
            // Take a screenshot if an exception occurs
            takeScreenshot(driver, "testSearchProduct");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"testSearchProduct"})
    public void testSelectProduct() {
        try {
            // Step 3: Select a product from the search results
            searchPage.selectFirstProduct();
        } catch (Exception e) {
            // Take a screenshot if an exception occurs
            takeScreenshot(driver, "testSelectProduct");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"testSelectProduct"})
    public void testAddProductToCart() {
        try {
            // Step 4: Add the product to the cart
            productPage.selectQuantity(5);
            productPage.addToCart();

            // Verify "SEPETİNİZE EKLENMİŞTİR" message is displayed
            assertTrue(productPage.isConfirmationMessageDisplayed(), "\"SEPETİNİZE EKLENMİŞTİR\" yazısı görünmüyor.");
        } catch (Exception e) {
            // Take a screenshot if an exception occurs
            takeScreenshot(driver, "testAddProductToCart");
            throw e;
        }
    }

    @Test(dependsOnMethods = {"testAddProductToCart"})
    public void testVerifyCart() {
        try {
            // Step 5: Go to the cart page
            driver.get("https://testcase.myideasoft.com/sepet");

            // Verify the cart title is displayed
            assertTrue(cartPage.isCartTitleDisplayed(), "Sepet sayfası açılmadı.");

            // Verify the quantity of the product in the cart is 5
            int quantity = cartPage.getProductQuantity();
            assertEquals(quantity, 5, "Sepetteki ürün adedi yanlış.");
        } catch (Exception e) {
            // Take a screenshot if an exception occurs
            takeScreenshot(driver, "testVerifyCart");
            throw e;
        }
    }

    private void takeScreenshot(WebDriver driver, String fileName) {
        // Take a screenshot and save it with a timestamp
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
