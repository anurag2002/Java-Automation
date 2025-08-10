package networkLogs;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.fetch.Fetch;
import org.openqa.selenium.devtools.v138.network.model.Request;

public class NetworkMocking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method

		ChromeDriver driver = new ChromeDriver();

		// initiate session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();

		// Enable Fetch Domain
		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

		// Call Paused
		devTools.addListener(Fetch.requestPaused(), request -> {
			Request req = request.getRequest();
			if (req.getUrl().contains("=shetty")) {
				String newUrl = req.getUrl().replace("=shetty", "=BadGuy");
				System.out.println(newUrl);

				// Call Continued
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newUrl),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			} else {
				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(req.getUrl()),
						Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
			}
		});

		driver.get("https://rahulshettyacademy.com/angularAppdemo");

		driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();
		Thread.sleep(3000);
		System.out.println(driver.findElement(By.cssSelector("p")).getText());

	}

}
