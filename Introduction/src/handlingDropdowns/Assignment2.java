package handlingDropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Assignment2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		//Open the link
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.manage().window().maximize();
		
		//Enter value in name field
		driver.findElement(By.xpath("//div[@class='form-group'] /input[@name='name']")).sendKeys("Anurag Pandey");
		
		//Enter value in email field
		driver.findElement(By.xpath("//div[@class='form-group'] /input[@name='email']")).sendKeys("anurag@mailinator.com");
		
		//Enter the password
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("Alert1234");
		
		//check the checkbox
		if(!driver.findElement(By.id("exampleCheck1")).isSelected())
		{
			driver.findElement(By.id("exampleCheck1")).click();
		}
		
		//Select Gender
		Select dropdown = new Select(driver.findElement(By.id("exampleFormControlSelect1")));
		dropdown.selectByVisibleText("Male");
		
		//Select Employment status
		driver.findElement(By.xpath("//div[@class=\"form-check form-check-inline\"] /input[@value='option2']")).click();
		
		//Enter DOB
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys("22Jan");
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.TAB);
		driver.findElement(By.xpath("//input[@type='date']")).sendKeys("2002");
		
		//Click on Submit
		driver.findElement(By.xpath("//input[@class = 'btn btn-success']")).click();
		
		//Get the Alert Text
		String alertText = driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText();
		System.out.println(alertText.split("\\n")[1]);

	}

}
