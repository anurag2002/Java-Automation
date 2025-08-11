package windowAuthentication;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowPopUp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://admin:admin@the-internet.herokuapp.com/");	
		driver.findElement(By.linkText("Basic Auth")).click();
		
		System.out.println(driver.findElement(By.tagName("p")).getText());
		
	}

}
