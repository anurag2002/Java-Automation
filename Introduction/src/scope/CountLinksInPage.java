package scope;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class CountLinksInPage {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "D:\\apps\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions a = new Actions(driver);
		
		//Count no of links in page
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		List<WebElement> linksInPage = new ArrayList<WebElement>();
		linksInPage = driver.findElements(By.tagName("a"));
		
		System.out.println("Total Number of Links in Page :: "+linksInPage.size());
		
		//Count no of links in footer section
		List<WebElement> linksInFooter = new ArrayList<WebElement>();
		linksInFooter = driver.findElements(By.xpath("//li[@class='gf-li']"));
		
		System.out.println("Total Number of Links in Footer :: "+linksInFooter.size());
		
		//Limiting WebDriver scope
		WebElement footerDriver = driver.findElement(By.id("gf-BIG"));
		
		System.out.println("Total no of Links in Footer with scope limited driver :: " + footerDriver.findElements(By.tagName("a")).size());
		
		//Find the links of only 1st column
		WebElement firstCol = footerDriver.findElement(By.xpath("//table[@class='gf-t'] //td[1]"));
		System.out.println("Total No of links in first Columns :: " + firstCol.findElements(By.tagName("a")).size());
		
		//Click on Each link on the column and check if the pages are opening
		//Opening links in separate tabs
		String linkClick = Keys.chord(Keys.CONTROL,Keys.ENTER);
		
		List<WebElement> links = firstCol.findElements(By.tagName("a"));
		for(int i = 1; i < links.size(); i++) {
			links.get(i).sendKeys(linkClick);
			Thread.sleep(5000L);
		}
		
		//Get the title from all the pages
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String window = it.next();	
		
				
		for(int i = 0; i < windows.size();) {
			if(it.hasNext()) {
				window = it.next();
				driver.switchTo().window(window);
				System.out.println("Title of Link is :: " + driver.getTitle());	
				driver.close();
			}
			else {
				break;
			}
		}

	}

}
