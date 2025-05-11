package scope;

import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Assignment6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//open the page
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		driver.manage().window().maximize();
		
		//Get a random number
		Random random = new Random();
		int randomNum = random.nextInt(3);
		randomNum++;
		
		//Limit the scope to the checkbox column
		WebElement chkboxColumn = driver.findElement(By.id("checkbox-example"));
		
		//tick the checkbox
		chkboxColumn.findElement(By.xpath("(//input[@type='checkbox'])["+randomNum+"]")).click();
		
		//Get the text of the checkbox
		String chkBoxText = driver.findElement(By.xpath("(//div[@id='checkbox-example'] //label)["+randomNum+"]")).getText();
		System.out.println("Checkbox Text :: " + chkBoxText);
		
		//Select option from dropdown
		WebElement chkbox = driver.findElement(By.id("dropdown-class-example"));
		Select dropdown = new Select(chkbox);
		dropdown.selectByVisibleText(chkBoxText);
		
		//Enter the name in 
		driver.findElement(By.id("name")).sendKeys(chkBoxText);	
		
		//Verify text in alert
		driver.findElement(By.id("alertbtn")).click();
		
		Alert alertPopUp = driver.switchTo().alert();
		String alertText = alertPopUp.getText();
		
		Assert.assertTrue(alertText.contains(chkBoxText));
		
		System.out.println("Text Present in Alert");
		
	}

}
