package introduction;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Introduction_To_TestNG_3 {
	@Test
	public void firstTestClassNew() {
		System.out.println("First Test in class 3");
	}
	
	@Test(groups= {"executeGroup"})
	public void test2ClassNew()
	{
		System.out.println("Second Test case in class 3");
	}
	
	@Test
	public void test3ClassNew()
	{
		System.out.println("Third Test case in class 3");
	}
	
	@BeforeMethod
	public void executeBeforeMethod() {
		System.out.println("Before Method executed before any test module executed - class 3");
	}
	
	@AfterMethod
	public void executeAfterMethod() {
		System.out.println("After Method executed after all test module executed - class 3");
	}

}
