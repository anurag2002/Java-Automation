package seleniumAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumAutomation.pageobjects.CartPage;
import seleniumAutomation.pageobjects.OrderHistoryPage;

public class AbstractComponents {

	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='  Cart ']")
	WebElement cartBtn;
	
	@FindBy(xpath = "//button[text()='  ORDERS']")
	WebElement orderBtn;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToAppear(WebElement findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(findBy));
	}

	public void waitForElementToDisappear(WebElement findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(findBy));
	}

	public CartPage goToCartPage() {
		cartBtn.click();
		return new CartPage(driver);
	}
	
	public OrderHistoryPage goToOrderPage() {
		orderBtn.click();
		return new OrderHistoryPage(driver);
	}

}
