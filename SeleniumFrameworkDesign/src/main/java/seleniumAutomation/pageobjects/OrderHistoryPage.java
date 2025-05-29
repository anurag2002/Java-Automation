package seleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class OrderHistoryPage extends AbstractComponents {

	// Initializing driver object for local class
	WebDriver driver;

	// bringing the driver from test case to be used
	public OrderHistoryPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	@FindBy(xpath = "//tr //td[2]")
	List<WebElement> allOrders;
	
	public boolean verifyOrder(String productName) {		
		return allOrders.stream().anyMatch(order -> order.getText().equalsIgnoreCase(productName));
	}
}
