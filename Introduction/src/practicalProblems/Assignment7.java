package practicalProblems;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Assignment7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		//Count no of rows
		int totalRow = driver.findElements(By.xpath("//table[@name='courses'] //tr[1] //th")).size();
		System.out.println("Total No of Rows: "+totalRow);
		
		//Count no of columns
		int totalCol = driver.findElements(By.xpath("//table[@name='courses'] //tr")).size();
		System.out.println("Total No of Columns: "+totalCol);
		
		//2nd Row content
		List<WebElement> row2 = driver.findElements(By.xpath("//table[@name='courses'] //tr[3] //td"));
		int col = 1;
		for(WebElement data : row2) {
			System.out.println("Data in Column "+col+": "+data.getText());
		}

	}

}
