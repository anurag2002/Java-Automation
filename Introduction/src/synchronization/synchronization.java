package synchronization;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class synchronization {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Open the URL
		driver.get("https://rahulshettyacademy.com/seleniumPractise");
		
		//Adding Wait Globally - Implicit Wait
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		/*
		Case 1:
			Add item to Cart
		Case 2:
			Add item from array to Cart
		Case 3:
			Stop executing once all items are added
		*/
		
		//Amazon Question:
			//Everyday new products are getting added into Amazon marketplace. We need to check if all the buttons are working fine or not after adding the new element
		
		//Add Cucumber to Cart
		System.out.println("Print Inside::");
		List<String> productList = new ArrayList<String>();
		productList.add("Brocolli");
		productList.add("Carrot");
		productList.add("Tomato");
		productList.add("Potato");
		productList.add("Beetroot");
		addItems(driver,productList);	//made the method static because we are calling the method without creating the object for this object.
										//This is also an utility
		//explicit wait
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(5));
		checkout(driver,w);
		
	}
	
	private static void checkout(WebDriver driver, WebDriverWait w) {
		// TODO Auto-generated method stub
		driver.findElement(By.className("cart-icon")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.className("promoBtn")).click();
		driver.manage().timeouts();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='promoInfo']")));
		String promoMessage = driver.findElement(By.xpath("//span[@class='promoInfo']")).getText();
		System.out.println(promoMessage);
		driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		
		
		
		
	}

	public static void addItems(WebDriver driver,List<String> productList) {
		List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='product']/h4[@class='product-name']"));
		int i = 0;
		int cartSize = 0;

		for(WebElement productName : productNames)
		{
			String product = productName.getText().split("-")[0].trim();
			System.out.println("Product Name: "+product);
			if(productList.contains(product))
			{
				//click on the button
				driver.findElement(By.xpath("//div[@class='product']["+(i+1)+"]/h4[@class='product-name']/following-sibling::div[@class='product-action']")).click();
				System.out.println(product+" added into cart.");
				cartSize++;
				//Alternative effective way ->
				//driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
				//Issue with this text locator in this case is once the product is added, ADD TO CART button text changes to ADDED. So it will change the index for the remaining buttons.
			}
			if(cartSize == productList.size()) {
				System.out.println("All items added.");
				break;
			}
			i++;
		}
	}

}
