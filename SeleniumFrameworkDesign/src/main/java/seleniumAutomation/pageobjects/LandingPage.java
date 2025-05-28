package seleniumAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	// Initializing driver object for local class
	WebDriver driver;

	// bringing the driver from test case to be used
	public LandingPage(WebDriver driver) {
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		return new ProductCatalogue(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
	}

}
