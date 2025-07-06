package seleniumAutomation.tests;

import seleniumAutomation.TestComponents.BaseTest;
import seleniumAutomation.pageobjects.CartPage;
import seleniumAutomation.pageobjects.LastPage;
import seleniumAutomation.pageobjects.OrderHistoryPage;
import seleniumAutomation.pageobjects.PaymentPage;
import seleniumAutomation.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		// Landing Page handling
		// Enter email and password to login
		// Product Catalog Page Handling
		ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));

		// Using Java Streams
		productCatalogue.printAllProduct();

		// Add Product
		// Check if toast has appeared or not
		productCatalogue.addProductToCard(map.get("productName"));
		System.out.println("Product added");

		// open cart
		CartPage cart = productCatalogue.goToCartPage();

		// Verify the products added
		Assert.assertTrue(cart.verifyProducts(map.get("productName")));

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

	}

	@Test(dependsOnMethods = { "submitOrder" }, dataProvider = "getData", groups = { "Purchase" })
	public void orderHistory(HashMap<String, String> map) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(map.get("email"), map.get("password"));
		OrderHistoryPage orderHist = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderHist.verifyOrder(map.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> map = getJsonDataToMap(
				"\\src\\test\\java\\seleniumAutomation\\data\\PurchaseOrder.json");

		return new Object[][] { { map.get(0) }, { map.get(1) } };
	}

}
