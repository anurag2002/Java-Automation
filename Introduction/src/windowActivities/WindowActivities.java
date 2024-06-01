package windowActivities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowActivities {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "D:/apps/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://google.com");
		driver.get("https://alertenterprise.com");
		driver.navigate().back();
		driver.navigate().forward();

	}

}
