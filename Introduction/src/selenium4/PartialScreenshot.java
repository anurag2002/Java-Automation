package selenium4;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class PartialScreenshot {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parentId = it.next();
		String childId = it.next();
		
		driver.switchTo().window(childId);
		
		driver.get("https://rahulshettyacademy.com/");
		
		String text = driver.findElement(By.xpath("(//div[@id='courses-block'] //div)[1] //h2 //a")).getText();
		
		driver.switchTo().window(parentId);
		
		WebElement name = driver.findElement(By.xpath("//input[@name='name']"));
		name.sendKeys(text);
		
		File file = name.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(file,new File("C:\\Users\\dell\\Desktop\\partial_screenshot.png"));
		

	}

}
