package mobileEmulator;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.emulation.Emulation;

public class MobileEmulatorTest {

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
		// Mobile Emulation
		devtools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));

		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(3000);
		driver.findElement(By.linkText("Library")).click();

		driver.quit();

	}

}
