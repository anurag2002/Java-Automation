package seleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class PaymentPage extends AbstractComponents{
	
	//Initializing driver object for local class
	WebDriver driver;
	
	//bringing the driver from test case to be used
	public PaymentPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(xpath="//button/span")
	List<WebElement> countryList;
	
	@FindBy(xpath="//div[@class='actions']/a")
	WebElement placeOrderBtn;
	
	By countryListBy = By.xpath("//button/span");
	
	public void enterCountry(String country) {
		countryField.sendKeys(country);
	}
	
	public List<WebElement> getCountry(){
		return countryList;
	}
	
	public void selectCountry(String countryInput) throws InterruptedException {
		enterCountry(countryInput);
		System.out.println("Wait Start");
		waitForElementToAppear(countryListBy);
		List<WebElement> countries = getCountry();
		countries.stream().filter(country -> country.getText().trim().equalsIgnoreCase(countryInput)).limit(1)
		.forEach(country -> country.click());
	}
	
	public LastPage placeOrder() {
		placeOrderBtn.click();
		return new LastPage(driver);
	}

}
