package practicalProblems;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Grids {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//creating object for executing JS
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//execute javascript
		js.executeScript("window.scrollBy(0,700)");
		
		//Grid handling
		List<WebElement> values = driver.findElements(By.xpath("//div[@class='tableFixHead'] //td[4]"));
		
		int sum = 0;
		for(WebElement value : values) {
			sum += Integer.parseInt(value.getText());
		}
		System.out.println("Sum is: "+sum);
		
		int displayCount = Integer.parseInt(driver.findElement(By.xpath("//div[@class='totalAmount']")).getText().split(": ")[1]);
		
		
		Assert.assertEquals(displayCount, sum);
		
		System.out.println("Assertion successfull");
		driver.close();
	}

}
