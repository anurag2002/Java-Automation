package cdpCommands;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CDPCommandsTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		// Chromium Driver initialization
		ChromeDriver driver = new ChromeDriver();

		// DevTools Initialization
		DevTools devtools = driver.getDevTools();

		// Initialize Session
		devtools.createSession();

		// send commands to CDP Methods -> CDP Methods will invoke and get access to
		// ChromeDevTools
		// Mobile Emulation - by directly executing the CDP Command
		Map<String, Object> deviceMetrics = new HashMap<String, Object>();
		deviceMetrics.put("width",600);
		deviceMetrics.put("height", 1000);
		deviceMetrics.put("deviceScaleFactor", 50);
		deviceMetrics.put("mobile", true);
		
		driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();
		
		driver.quit();
		

	}

}
