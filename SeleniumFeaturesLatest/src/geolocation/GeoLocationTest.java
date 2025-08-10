package geolocation;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class GeoLocationTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// Chromium Driver initialization
		ChromeDriver driver = new ChromeDriver();

		// DevTools Initialization
		DevTools devtools = driver.getDevTools();

		// Initialize Session
		devtools.createSession();
		
		Map coordinates = new HashMap();
		coordinates.put("latitude", 40);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);
		
		
		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
		Thread.sleep(30000);
		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
		String title = driver.findElement(By.xpath("//div//h1")).getText();
		System.out.println(title);

	}

}
