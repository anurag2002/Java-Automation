package locators;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Locators {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/apps/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("http://rahulshettyacademy.com/locatorspractice/");
		driver.findElement(By.id("inputUsername")).sendKeys("anurag");
		driver.findElement(By.name("inputPassword")).sendKeys("Alert1234");
		driver.findElement(By.className("signInBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		
			driver.findElement(By.linkText("Forgot your password?")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys("Anurag");
			driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("anurag@gg.cc");
			driver.findElement(By.cssSelector("input[placeholder='Phone Number']")).sendKeys("161555");
			driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
//			driver.findElement(By.tagName("button")).click();
			String response = driver.findElement(By.cssSelector("p.infoMsg")).getText().split("\'")[1];
			System.out.println(response);
			driver.findElement(By.cssSelector("button.go-to-login-btn")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("inputUsername")).sendKeys("anurag");
			driver.findElement(By.name("inputPassword")).sendKeys(response);
			driver.findElement(By.className("signInBtn")).click();

		

	}

}
