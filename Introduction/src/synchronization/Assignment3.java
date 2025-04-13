package synchronization;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Open the URL
		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.findElement(By.name("username")).sendKeys("rahulshettyacademy");
		driver.findElement(By.name("password")).sendKeys("learning");
		driver.findElement(By.xpath("//input[@value='user']")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();
		
		WebElement staticDropdown = driver.findElement(By.tagName("select"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByValue("consult");
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("signInBtn")).click();
		
		
		addItems(driver,w);
		checkout(driver);
		
	}

	private static void checkout(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath("//a[@class='nav-link btn btn-primary']")).click();
		
	}

	private static void addItems(WebDriver driver, WebDriverWait w) {
		// TODO Auto-generated method stub
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-info']")));
		List<WebElement> addBtns = driver.findElements(By.xpath("//button[@class='btn btn-info']"));
		for(WebElement addBtn : addBtns)
		{
			addBtn.click();
		}
	}

}
