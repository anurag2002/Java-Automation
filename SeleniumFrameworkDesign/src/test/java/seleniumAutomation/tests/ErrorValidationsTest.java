package seleniumAutomation.tests;

import seleniumAutomation.TestComponents.BaseTest;
import seleniumAutomation.pageobjects.ProductCatalogue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"Error Handling"})
	public void loginErrorValidation() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// Landing Page handling
		// Enter email and password to login
		landingPage.loginApplication("test@test.cc", "Alert12345");
		
		Assert.assertEquals(landingPage.errorMessage(), "Incorrect email or password.");
	}
	
	@Test
	public void productPageErrorValidation() {
		String productName = "ADIDAS ORIGINALS";
		// Landing Page handling
		// Enter email and password to login
		// Product Catalog Page Handling
		ProductCatalogue productCatalogue = landingPage.loginApplication("test@test.cc", "Alert1234");

		// Using Java Streams
		productCatalogue.printAllProduct();

		// Add Product
		// Check if toast has appeared or not
		productCatalogue.addProductToCard(productName);
		
		Assert.assertFalse(productCatalogue.verifyProduct(productName));
		
		System.out.println("Product added");
	}

}
