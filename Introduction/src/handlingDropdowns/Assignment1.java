package handlingDropdowns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Assignment1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//Question 1
		driver.findElement(By.id("checkBoxOption1")).click();
		System.out.println("Checkbox check status :: " + driver.findElement(By.id("checkBoxOption1")).isSelected());
		Assert.assertTrue(driver.findElement(By.id("checkBoxOption1")).isSelected());
		driver.findElement(By.id("checkBoxOption1")).click();
		Assert.assertFalse(driver.findElement(By.id("checkBoxOption1")).isSelected());
		System.out.println("Checkbox check status :: " + driver.findElement(By.id("checkBoxOption1")).isSelected());
		
		
		//Question 2
		List<WebElement> checkBoxes = driver.findElements(By.xpath("//div[@id='checkbox-example'] //fieldset //label"));
		System.out.println("No of checkboxes :: " + checkBoxes.size());
	}

}
