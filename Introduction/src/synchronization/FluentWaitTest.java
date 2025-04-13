package synchronization;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

public class FluentWaitTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Open the URL
		driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		//Creating object of class wait for implementing Fluent Wait
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		
		//Defining on what object we need to put timeout and on what object we need to put polling
		//Adding until so that it will wait till the element is found. Since we don't have a pre-defined method, so we need to custom build one.
		//wait.until is a method of the interface Wait
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			//This will wait until the method 'apply' will return a type WebElement. It also takes an input of WebDriver.
			//This is the main function/method
			public WebElement apply(WebDriver driver) {
				//This will check if the element is visible on the screen or not. Only return the web element when the element is visible on the screen
				//Else will return null which will show that the element is not yet found and we need to wait.
				if(driver.findElement(By.xpath("//div[@id='finish'] //h4")).isDisplayed()) {
					return driver.findElement(By.xpath("//div[@id='finish'] //h4"));
				}
				else {
					return null;
				}
			}
		});
		System.out.println(driver.findElement(By.xpath("//div[@id='finish'] //h4")).isDisplayed());
		System.out.println(foo.getText());
	}

}
