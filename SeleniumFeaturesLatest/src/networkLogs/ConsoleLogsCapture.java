package networkLogs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

public class ConsoleLogsCapture {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method

		ChromeDriver driver = new ChromeDriver();
		
		//Failures are captured using Listeners - OnTestFailures
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");

		driver.findElement(By.xpath("//a[text()='Browse Products']")).click();
		driver.findElement(By.xpath("//a[text()='Selenium']")).click();
		driver.findElement(By.xpath("//button[text()='Add to Cart']")).click();
		System.out
				.println(driver.findElement(By.xpath("//p[text()='This Product is already added to Cart']")).getText());
		
		driver.findElement(By.linkText("Cart")).click();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");
		
		//Get Log Entry object using selenium inbuilt logging tool
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);
		
		//getAll() method returns logs in a list of LogEntry object type
		List<LogEntry> logs = entry.getAll();
		
		//Printing all the logs
		for(LogEntry e : logs) {
			System.out.println(e.getMessage());		//Extract this message into Log4j file
		}
	}

}
