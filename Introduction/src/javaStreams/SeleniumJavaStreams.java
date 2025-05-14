package javaStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class SeleniumJavaStreams {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		//click on column name to sort the column
		driver.findElement(By.xpath("//table //thead //tr //th[1]")).click();
		
		//get the name of all elements from column
		List<WebElement> weList = driver.findElements(By.xpath("//tr/td[1]"));
		List<String> finalName = weList.stream().map(s->s.getText()).toList(); 
		
		System.out.println(finalName);
		
		List<String> streamSortedList = finalName.stream().sorted().collect(Collectors.toList());
		
		Assert.assertEquals(finalName, streamSortedList,"Both are equal");
		
		//scan the name column for the particular vegetable 
		List<String> price = new ArrayList<String>(); 
				
		do {
			List<WebElement> weList1 = driver.findElements(By.xpath("//tr/td[1]"));
			price.addAll(weList1.stream().filter(s->s.getText().equalsIgnoreCase("Rice")).map(s->getPrice(s)).toList());
			price.forEach(a->System.out.println(a));
			if(price.size() <1)
			driver.findElement(By.xpath("//a[@aria-label='Next']")).click();
		}
		while(price.size()<1);
	}

	private static String getPrice(WebElement s) {
		// TODO Auto-generated method stub
		
		return s.findElement(By.xpath("following-sibling::td[1]")).getText(); 
	}

}
