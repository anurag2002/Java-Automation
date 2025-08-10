package networkLogs;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import com.google.common.collect.ImmutableList;

public class BlockNetworkRequest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method

		ChromeDriver driver = new ChromeDriver();

		// initiate session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Enable Fetch Domain
		// Creating pattern so that it fetches only particular call.
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

		// Block URLs
		devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));

		driver.get("https://rahulshettyacademy.com/angularAppdemo");

		driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
		driver.findElement(By.xpath("//a[text()='Selenium']")).click();
		driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
		System.out
				.println(driver.findElement(By.xpath("//p[text()='This Product is already added to Cart']")).getText());
	}

}
