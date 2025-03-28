package handlingDropdowns;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoSuggestiveDropdown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		
		driver.findElement(By.id("autosuggest")).sendKeys("aus");
		Thread.sleep(3000);
		List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));	//Creating the list of web elements to get all the items returned by the list
		
		for(WebElement option : options)
		{
			if(option.getText().equalsIgnoreCase("Austria"))
			{
				option.click();
				break;
			}
		}
	}

}
