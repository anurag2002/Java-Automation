package introduction;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenersTestNG implements ITestListener{
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Failed for test: " + result.getName());
	}
	
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Success");
	}
}
