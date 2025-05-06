package mouseKeyboardInteraction;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class actionsClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.amazon.in/");
		Actions a = new Actions(driver);
		
		a.moveToElement(driver.findElement(By.xpath("//div[@id='nav-link-accountList']"))).contextClick().build().perform();
		a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).keyDown(Keys.SHIFT).sendKeys("hello").doubleClick().build().perform();
	}

}
