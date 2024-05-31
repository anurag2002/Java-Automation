package introduction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SelfIntroduction {

	public static void main(String[] args) {
		
//Invoking Browser
		
//			Step 1: Create object for ChromeDriver class so that its methods can be used.
//		System.setProperty("webdriver.chrome.driver", "D:/apps/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://alertenterprise.com/company/careers/");
		System.out.println(driver.getTitle());
		driver.quit();
	}

}
