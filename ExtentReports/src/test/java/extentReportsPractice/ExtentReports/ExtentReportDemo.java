package extentReportsPractice.ExtentReports;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportDemo {
	ExtentReports extent;
	
	@BeforeTest
	public void config() {
		//ExtentReports, ExtentSparkReporter
		
		//Creating the ExtentSparkReporter class object for Reports Config
		String path = System.getProperty("user.dir") + "\\\\report\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//Doing basic config for reports
		reporter.config().setReportName("Web Automation Results");	//Updating the Report Name
		reporter.config().setDocumentTitle("Test Results");		//Updating the page title
		
		//Creating the ExtentReports class and object for Reports creation
		extent = new ExtentReports();
		extent.attachReporter(reporter);	//Attaching Report
		extent.setSystemInfo("Tester", "Anurag Pandey");	//Adding tester name
		
	}

	@Test
	public void initialDemo() {
		//Report created
		ExtentTest test = extent.createTest("Initial Demo");		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://rahulshettyacademy.com/");
		System.out.println(driver.getTitle());
		driver.close();
		test.fail("Data do not match");
		extent.flush();
		//Report Ended.

	}

}
