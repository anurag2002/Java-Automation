package seleniumAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class LastPage extends AbstractComponents {

	// Initializing driver object for local class
	WebDriver driver;

	// bringing the driver from test case to be used
	public LastPage(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	WebElement orderIds;

	@FindBy(className = "hero-primary")
	WebElement thankYouHeading;

	public String[] getOrderIdArray() {
		return orderIds.getText().split("\\|");
	}

	public void printHeading() {
		System.out.println(thankYouHeading.getText());
	}

	public void printOrderId() {
		for (String order : getOrderIdArray()) {
			System.out.println(order.trim());
		}
	}

	public boolean headingCheck(String heading) {
		if (thankYouHeading.getText().equalsIgnoreCase(heading)) {
			return true;
		}
		return false;
	}

}
