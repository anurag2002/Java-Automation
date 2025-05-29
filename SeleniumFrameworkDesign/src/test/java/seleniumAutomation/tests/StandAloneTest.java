package seleniumAutomation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import seleniumAutomation.pageobjects.LandingPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String productName = "ADIDAS ORIGINAL";
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		
		
		
		//Enter email and password to login
		driver.findElement(By.id("userEmail")).sendKeys("test@test.cc");
		driver.findElement(By.id("userPassword")).sendKeys("Alert1234");
		driver.findElement(By.id("login")).click();
		
		//grab all the products
		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='card-body']"));
		
		/*
		List<String> productName = new ArrayList<>();
		
		for(int i = 1; i <= productList.size(); i++) {
			productName.add(driver.findElement(By.xpath("(//div[@class='card-body'])["+i+"]/h5/b")).getText());
		}
		
		System.out.println("Product Names: " + productName);
		
		*/
		//Using Java Streams
		productList.stream().forEach(product -> System.out.println(product.findElement(By.tagName("b")).getText()));
		
				
		//Add Product
		WebElement prod = productList.stream().filter(product -> product.findElement(By.tagName("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);		
		prod.findElement(By.xpath(".//button[text()=' Add To Cart']")).click();
		System.out.println("Product added");
		
		//Check if toast has appeard or not
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role='alertdialog']"))));
		
//		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alertdialog']")).getText(),"Product Added To Cart");
		
		//open cart
		driver.findElement(By.xpath("//button[text()='  Cart ']")).click();
		
		//Verify the products added
		List<WebElement> productsAdded = driver.findElements(By.xpath("//div[@class='cart'] //h3"));
		
		/*
		Boolean productFound = false;
		for(WebElement product : productsAdded) {
			productFound = product.getText().equalsIgnoreCase(productName);
		}
		*/
		
		Assert.assertTrue(productsAdded.stream().allMatch(product -> product.getText().equalsIgnoreCase(productName)));
		
		//checkout
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		//select country
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("india");
		
		List<WebElement> countries = driver.findElements(By.xpath("//button/span"));
		
		/*
		for(WebElement country : countries) {
			if(country.getText().trim().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}
		*/
		
		countries.stream().filter(country -> country.getText().trim().equalsIgnoreCase("India")).limit(1).forEach(country -> country.click());
		
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		
		System.out.println(driver.findElement(By.className("hero-primary")).getText());
		
		String[] orders = driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText().split("\\|");
		
		for(String order : orders) {
			System.out.println(order.trim());
		}
		
		Assert.assertEquals("THANKYOU FOR THE ORDER.", driver.findElement(By.className("hero-primary")).getText());
		
		Thread.sleep(2000);
		driver.close();
		
	}

}
