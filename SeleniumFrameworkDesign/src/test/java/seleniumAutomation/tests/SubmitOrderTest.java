package seleniumAutomation.tests;

import seleniumAutomation.TestComponents.BaseTest;
import seleniumAutomation.pageobjects.CartPage;
import seleniumAutomation.pageobjects.LastPage;
import seleniumAutomation.pageobjects.OrderHistoryPage;
import seleniumAutomation.pageobjects.PaymentPage;
import seleniumAutomation.pageobjects.ProductCatalogue;
import seleniumAutomation.pageobjects.RegistrationPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for submitting an order and verifying order history.
 * Uses data-driven approach with TestNG DataProvider.
 */
public class SubmitOrderTest extends BaseTest {

    /**
     * Submits an order after registering a new user.
     * Steps:
     * 1. Register a new user.
     * 2. Log in with the registered user.
     * 3. Add a product to the cart.
     * 4. Proceed to checkout and place the order.
     * 5. Verify order confirmation.
     *
     * @param map Test data for registration, login, and product selection.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test(dataProvider = "getData", groups = { "Purchase" })
    public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {
        // TODO Auto-generated method stub

        // At the top, import RegistrationPage
        // Inside your test method, before login:
        // Navigate to registration page if needed, e.g., driver.get("your_registration_url");
        driver.get("your_registration_url"); // Replace with actual registration page URL

        RegistrationPage registrationPage = new RegistrationPage(driver);

        // Call registration (replace values as needed)
        registrationPage.completeRegistration(
                map.get("firstName"),
                map.get("lastName"),
                map.get("email"),
                map.get("phone"),
                map.get("occupation"),
                map.get("gender"),
                map.get("password"),
                map.get("confirmPassword")
        );

        // Landing Page handling
        // Enter email and password to login
        // Product Catalog Page Handling
        ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));

        // Using Java Streams to print all products
        productCatalogue.printAllProduct();

        // Add Product to cart and check if toast has appeared or not
        productCatalogue.addProductToCard(map.get("productName"));
        System.out.println("Product added");

        // Open cart page
        CartPage cart = productCatalogue.goToCartPage();

        // Verify the products added to cart
        Assert.assertTrue(cart.verifyProducts(map.get("productName")));

        // Proceed to checkout
        PaymentPage payPage = cart.checkout();

        // Select country for shipping
        payPage.selectCountry("india");

        // Place the order
        LastPage last = payPage.placeOrder();

        // Print order confirmation heading and order ID
        last.printHeading();
        last.printOrderId();

        // Verify the order confirmation heading
        Assert.assertTrue(last.headingCheck("THANKYOU FOR THE ORDER."));
    }

    /**
     * Verifies that the ordered product appears in the order history.
     * Depends on successful order submission.
     *
     * @param map Test data for login and product verification.
     */
    @Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData", groups = { "Purchase" })
    public void orderHistory(HashMap<String, String> map) {
        // Log in and navigate to order history page
        ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));
        OrderHistoryPage orderHist = productCatalogue.goToOrderPage();

        // Assert that the ordered product is present in order history
        Assert.assertTrue(orderHist.verifyOrder(map.get("productName")));
    }

    /**
     * DataProvider for test methods.
     * Reads test data from JSON and returns it as an array of HashMaps.
     *
     * @return Object[][] containing test data maps.
     * @throws IOException
     */
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> map = getJsonDataToMap(
                "\\src\\test\\java\\seleniumAutomation\\data\\PurchaseOrder.json");

        // Fixed the missing parenthesis here
        return new Object[][] { { map.get(0) }, { map.get(1) } };
    }

}