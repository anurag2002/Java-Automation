package networkLogs;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.ConnectionType;

public class NetworkSpeed {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method

		ChromeDriver driver = new ChromeDriver();

		// initiate session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Enable Fetch Domain
		// Creating pattern so that it fetches only particular call.
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));

		// Emulate network condition - Change network speed and latency
		devTools.send(Network.emulateNetworkConditions(true, 300, 20000, 100000, Optional.of(ConnectionType.ETHERNET),
				Optional.empty(), Optional.empty(), Optional.empty()));
		
		//Logs error if the loading fails at any point of time
		devTools.addListener(Network.loadingFailed(), loadingFailed -> {
			System.out.println(loadingFailed.getErrorText());
			System.out.println(loadingFailed.getTimestamp());
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo");

		driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();

	}

}
