package miscellaneous;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");

		List<WebElement> footerLinks = driver.findElements(By.xpath("//table[@class='gf-t'] //a"));
		
		SoftAssert sa = new SoftAssert();
		
		for(WebElement link : footerLinks) {
			HttpURLConnection conn = (HttpURLConnection)new URL(link.getAttribute("href")).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			sa.assertTrue(conn.getResponseCode()<400, link.getAttribute("href")+" is broken with status code: "+conn.getResponseCode());
			
//			if(conn.getResponseCode() > 400) {
//				System.out.println(link.getAttribute("href")+" is broken with status code: "+conn.getResponseCode());
//			}
		}
		sa.assertAll();
	}

}
