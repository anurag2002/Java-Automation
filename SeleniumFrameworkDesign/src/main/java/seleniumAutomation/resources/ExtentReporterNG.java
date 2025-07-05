package seleniumAutomation.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
		
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		//Doing basic config for reports
		reporter.config().setReportName("Web Automation Results");	//Updating the Report Name
		reporter.config().setDocumentTitle("Test Results");		//Updating the page title
		
		//Creating the ExtentReports class and object for Reports creation
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);	//Attaching Report
		extent.setSystemInfo("Tester", "Anurag Pandey");	//Adding tester name
		return extent;
	}

}
