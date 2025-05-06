package mouseKeyboardInteraction;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WindowHandling {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Go to rahulshettyacademy assignment 3 page. 
		//CLick on link. switch to new window. print the paragraph. extract the email form the paragraph and return to parent window
		//enter the email into the username field. 
		
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/loginpagePractise/");
		driver.manage().window().maximize();
		
		Actions a = new Actions(driver);
		
		driver.findElement(By.linkText("Free Access to InterviewQues/ResumeAssistance/Material")).click();
		
		//Switch to another window
		//Get all the windows
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		
		//Switch focus to child window
		driver.switchTo().window(childWindowId);
		
		String userName = driver.findElement(By.xpath("//p /strong /a")).getText();
		System.out.println("Paragraph Text :: \n"+userName);
		
		driver.switchTo().window(parentWindowId);
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.name("username")).sendKeys(userName);
		driver.findElement(By.name("password")).sendKeys("learning");
		driver.findElement(By.xpath("//input[@value='user']")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.id("okayBtn")));
		driver.findElement(By.id("okayBtn")).click();
		
		WebElement staticDropdown = driver.findElement(By.tagName("select"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByValue("consult");
		while(!driver.findElement(By.xpath("//input[@id='terms']")).isSelected()) {
			a.moveToElement(driver.findElement(By.xpath("//input[@id='terms']"))).click().build().perform();
		}
		driver.findElement(By.id("signInBtn")).click();
	}
}
