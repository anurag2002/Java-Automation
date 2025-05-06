package mouseKeyboardInteraction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com/");
		driver.findElement(By.linkText("Nested Frames")).click();
		
		//Switch frame to parent frame
		driver.switchTo().frame(driver.findElement(By.name("frame-top")));
		
		//Switch frame to child frame
		driver.switchTo().frame(driver.findElement(By.name("frame-middle")));
		
		//Print frame text
		String frameText = driver.findElement(By.id("content")).getText();
		System.out.println(frameText);
	}

}
