package seleniumAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumAutomation.pageobjects.CartPage;
import seleniumAutomation.pageobjects.LandingPage;
import seleniumAutomation.pageobjects.LastPage;
import seleniumAutomation.pageobjects.PaymentPage;
import seleniumAutomation.pageobjects.ProductCatalogue;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Landing Page handling
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();

		// Enter email and password to login
		// Product Catalog Page Handling
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@test.cc", "Alert1234");

		// Using Java Streams
		productCatalogue.printAllProduct();

		// Add Product
		// Check if toast has appeared or not
		productCatalogue.addProductToCard(productName);
		System.out.println("Product added");

		// open cart
		CartPage cart = productCatalogue.goToCartPage();

		// Verify the products added
		Assert.assertTrue(cart.verifyProducts(productName));

		// checkout
		PaymentPage payPage = cart.checkout();

		// select country
		payPage.selectCountry("india");

		// Place order
		LastPage last = payPage.placeOrder();

		// Print Heading
		last.printHeading();

		// Print Order Id
		last.printOrderId();

		// Heading Check
		Assert.assertTrue(last.headingCheck("THANKYOU FOR THE ORDER."));

		Thread.sleep(2000);
		driver.quit();

	}

}
