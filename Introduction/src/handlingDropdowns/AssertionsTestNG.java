package handlingDropdowns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AssertionsTestNG {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
//		System.setProperty("webdriver.chrome.driver", "D:/apps/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.spicejet.com/");
		
		//Assertion
//		Assert.assertFalse(false)
		
		driver.manage().window().maximize();
//		Thread.sleep(3000);
		driver.findElement(By.className("css-1cwyjr8")).click();
		
		//Select Source and Destination
		driver.findElement(By.xpath("//div[contains(text(),'MAA')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[contains(text(),'IXC')]")).click();
		
		//Close Date selection popup
		driver.findElement(By.xpath("//div[contains(text(),'Welcome aboard')]")).click();
		
		//Select No of Passengers
		driver.findElement(By.cssSelector("div[class='css-1dbjc4n'] div div[class='css-1dbjc4n r-14lw9ot r-11u4nky r-z2wwpe r-1phboty r-rs99b7 r-1loqt21 r-13awgt0 r-ymttw5 r-5njf8e r-1otgn73'] div[class='css-1dbjc4n r-1awozwy r-18u37iz r-1wtj0ep']")).click();
		driver.findElement(By.cssSelector("div[data-testid='Adult-testID-plus-one-cta']")).click();
		driver.findElement(By.cssSelector("div[data-testid='Adult-testID-plus-one-cta']")).click();
		driver.findElement(By.cssSelector("div[data-testid='Adult-testID-plus-one-cta']")).click();
//		driver.findElement(By.cssSelector("div[data-testid='home-page-travellers-done-cta']")).click();
		driver.findElement(By.xpath("//div[@data-testid='home-page-travellers-done-cta']")).click();
		
		
		//Select the radio buttons
		System.out.println("No of radio buttons: " + driver.findElements(By.xpath("//div[@class='css-76zvg2 r-cqee49 r-1enofrn r-1ozqkpa']")).size());
		
		List<WebElement> options = driver.findElements(By.xpath("//div[@class='css-76zvg2 r-cqee49 r-1enofrn r-1ozqkpa']"));
		
		//Search for the required option and select if not already selected.
		for(WebElement option : options)
		{
			Assert.assertFalse(option.isSelected());
			if(option.getText().equalsIgnoreCase("senior citizen") && !option.isSelected())
			{
				System.out.println("Option Selected::");
				option.click();
				break;
			}
		}
		
		//Search Button
		driver.findElement(By.cssSelector("div[data-testid='home-page-flight-cta']")).click();
		
		Thread.sleep(3000);
		
		WebElement checkbox = driver.findElement(By.cssSelector("div[class='css-1dbjc4n r-1tf5bf9 r-1777fci r-1ww30s9']"));
		checkbox.click();		
		
		WebElement continueBtn = driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-1awozwy r-z2wwpe r-1loqt21 r-18u37iz r-1777fci r-d9fdf6 r-1w50u8q r-ah5dr5 r-1otgn73']"));
		System.out.println("Continue Button::" + continueBtn.isEnabled());
		continueBtn.click();
//		Thread.sleep(1000);
		
	}

}
