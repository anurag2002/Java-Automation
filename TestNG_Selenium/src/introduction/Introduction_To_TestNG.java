package introduction;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Introduction_To_TestNG {
	@Test
	public void firstTest() {
		System.out.println("First Test in class 1");
	}
	
	@Test
	public void test2()
	{
		System.out.println("Second Test case in class 1");
	}
	
	@BeforeTest
	public void runBeforeTest() {
		System.out.println("Before Test Method executed before test started in class 1");
	}
	
	@AfterTest
	public void runAfterTest() {
		System.out.println("After Test Method executed after test ended in class 1");
	}

}
