package mouseKeyboardInteraction;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment4 {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();

		driver.findElement(By.linkText("Click Here")).click();
		
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentWindow = it.next();
		String childWindow = it.next();
		
		driver.switchTo().window(childWindow);
		
		System.out.println(driver.findElement(By.tagName("h3")).getText());
		
		driver.switchTo().window(parentWindow);
		
		System.out.println(driver.findElement(By.tagName("h3")).getText());
	}

}
