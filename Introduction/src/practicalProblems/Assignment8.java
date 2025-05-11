package practicalProblems;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment8 {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		//Adding wait as dropdown takes some time before appearing on screen
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		driver.findElement(By.id("autocomplete")).sendKeys("ind");
		
		List<WebElement> dropdown = driver.findElements(By.xpath("//li[@class='ui-menu-item'] //div"));
		
		for(WebElement item : dropdown) {
			if(item.getText().equalsIgnoreCase("India")) {
				item.click();
				break;
			}
		}

	}

}
