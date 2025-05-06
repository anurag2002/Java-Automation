package mouseKeyboardInteraction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Frames {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://jqueryui.com/droppable/");
		
		//switch to frame
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		System.out.println("Switched to Frame");
		
		//perform drag and drop operation
		Actions a = new Actions(driver);
		
		//Assign source and destination web elements
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement destination = driver.findElement(By.id("droppable"));
		System.out.println("Source and Destination assigned");
		
		//actual drag and drop
		//Note: always use .build().perform() with every action
		a.dragAndDrop(source,destination).build().perform();
		System.out.println("Drag and Drop");
		
		//Switch back to default window
		driver.switchTo().defaultContent();

	}
}
