package seleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	// Initializing driver object for local class
	WebDriver driver;

	// bringing the driver from test case to be used
	public CartPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(xpath = "//div[@class='cart'] //h3")
	List<WebElement> productAdded;

	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkout;

	public List<WebElement> getProductAddedList() {
		return productAdded;
	}

	public boolean verifyProducts(String productName) {
		List<WebElement> productsAdded = getProductAddedList();
		boolean allProductMatch = productsAdded.stream()
				.allMatch(product -> product.getText().equalsIgnoreCase(productName));
		return allProductMatch;
	}

	public PaymentPage checkout() {
		checkout.click();
		return new PaymentPage(driver);
	}

}
