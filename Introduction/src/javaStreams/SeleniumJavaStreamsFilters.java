package javaStreams;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SeleniumJavaStreamsFilters {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		driver.findElement(By.id("search-field")).sendKeys("Che");
		
		//get the name of all elements from column
		List<WebElement> weList = driver.findElements(By.xpath("//tr/td[1]"));
		
		Assert.assertTrue(weList.stream().allMatch(s->s.getText().contains("Ch")));
	}


}
