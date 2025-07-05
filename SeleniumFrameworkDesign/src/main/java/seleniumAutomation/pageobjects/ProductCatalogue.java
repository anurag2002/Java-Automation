package seleniumAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumAutomation.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	// Initializing driver object for local class
	WebDriver driver;

	// bringing the driver from test case to be used
	public ProductCatalogue(WebDriver driver) {
		// initialization
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	@FindBy(xpath = "//div[@class='card-body']")
	List<WebElement> productList;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By addToCart = By.xpath(".//button[text()=' Add To Cart']");
	By alertPopupMessage = By.xpath("//div[@role='alert']");
	By productName = By.tagName("b");

	public List<WebElement> getProductList() {
		return productList;
	}

	public WebElement getProductByName(String productName) {
		WebElement prod = productList.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;
	}

	public void addProductToCard(String productName) {
		System.out.println("Product Name:: " + productName);
		WebElement prod = getProductByName(productName);
		if (verifyProduct(productName)) {
			prod.findElement(addToCart).click();
			waitForElementToAppear(alertPopupMessage);
//		waitForElementToDisappear(spinner);
		}
	}
	
	public boolean verifyProduct(String productName) {
		WebElement prod = getProductByName(productName);
		return (prod != null);
	}

	public void printAllProduct() {
		productList.stream().forEach(product -> System.out.println(product.findElement(productName).getText()));
	}

}
