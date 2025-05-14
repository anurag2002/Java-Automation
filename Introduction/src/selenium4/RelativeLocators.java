package selenium4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

public class RelativeLocators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		System.out.println(driver.findElement(with(By.tagName("label")).above(driver.findElement(By.xpath("//input[@name='name']")))).getText());
		
		driver.findElement(with(By.tagName("input")).below(driver.findElement(By.xpath("//label[@for='dateofBirth']")))).click();
		
		driver.findElement(with(By.tagName("input")).toLeftOf(driver.findElement(By.xpath("//label[text()='Check me out if you Love IceCreams!']")))).click();
		
		driver.findElement(with(By.tagName("input")).toRightOf(driver.findElement(By.xpath("//label[text()='Student']")))).click();
	}

}
