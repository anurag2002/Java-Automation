package calendar;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class HandlingCalendar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		String month = "10", day = "7", year = "2039";
		int dayInt = Integer.parseInt(day),yearInt = Integer.parseInt(year);
		
		//Open Calendar
		driver.findElement(By.xpath("//div[@class='react-date-picker__wrapper'] //button[2]")).click();
		
		//Open months
		driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
		//Open Years
		driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
		//Open Years range
		driver.findElement(By.xpath("//button[@class='react-calendar__navigation__label']")).click();
		
		//Select Year range
		int yearRange = (yearInt/10)%10;
		yearRange = yearInt%10==0 ? yearRange : ++yearRange;
		
		driver.findElement(By.xpath("//div[@class='react-calendar__century-view__decades'] //button["+yearRange+"]")).click();
		
		//Select Year - my approach
//		int yearNo = yearInt%10 == 0 ? 10 : yearInt%10;
//		driver.findElement(By.xpath("//div[@class='react-calendar__decade-view__years'] //button["+yearNo+"]")).click();		

		//Optimized approach:
		driver.findElement(By.xpath("//button[text()='"+year+"']")).click();
		
		//Select Month
		driver.findElement(By.xpath("//div[@class='react-calendar__year-view__months'] //button["+month+"]")).click();
				
		//Select Date - my approach
//		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='react-calendar__month-view__days'] //button"));
//		
//		for(WebElement date : dates) {
//			if(date.getText().equalsIgnoreCase("1"))
//			{
//				break;
//			}
//			dayInt++;
//		}
//		
//		driver.findElement(By.xpath("//div[@class='react-calendar__month-view__days'] //button["+dayInt+"]")).click();
		
		//Optimized Approach
		driver.findElement(By.xpath("//abbr[text()='"+day+"']")).click();
		
		//Get Display Date
		String enteredDate = driver.findElement(By.xpath("//div[@class='react-date-picker__inputGroup'] //input[1]")).getAttribute("value");
		System.out.println("Entered Date: " + enteredDate);
		String inputDate = year+"-"+month+"-"+(Integer.parseInt(day) < 10 ? ("0"+day) : day);
		System.out.println("Input Date: "+inputDate);
		
		//Assertion on input date and display date
		Assert.assertTrue(inputDate.equalsIgnoreCase(enteredDate));
		
	}

}
