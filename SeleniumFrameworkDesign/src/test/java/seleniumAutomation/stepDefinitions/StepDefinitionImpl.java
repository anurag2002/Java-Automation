package seleniumAutomation.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumAutomation.TestComponents.BaseTest;
import seleniumAutomation.pageobjects.CartPage;
import seleniumAutomation.pageobjects.LandingPage;
import seleniumAutomation.pageobjects.LastPage;
import seleniumAutomation.pageobjects.PaymentPage;
import seleniumAutomation.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	// Declaring objects publicly so that it can be accessed by all the methods.
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cart;
	public PaymentPage payPage;
	public LastPage last;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) from Cart$")
	public void i_add_product_to_cart(String productName) {
		productCatalogue.addProductToCard(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName) throws InterruptedException {
		cart = productCatalogue.goToCartPage();
		Assert.assertTrue(cart.verifyProducts(productName));
		payPage = cart.checkout();
		payPage.selectCountry("india");
		last = payPage.placeOrder();
		last.printHeading();
		last.printOrderId();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationpage(String string) {
		Assert.assertTrue(last.headingCheck(string));
		driver.close();
	}
	
	@Then("{string} message is displayed.")
	public void message_is_displayed(String string) {
		Assert.assertEquals(landingPage.errorMessage(), string);
		driver.close();
	}

}
