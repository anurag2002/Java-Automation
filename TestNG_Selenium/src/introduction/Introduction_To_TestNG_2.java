package introduction;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Introduction_To_TestNG_2 {
	@Test
	public void firstTestClassNew() {
		System.out.println("First Test in class 2");
	}
	
	@Test(enabled=false)
	public void test2ClassNew()
	{
		System.out.println("Second Test case in class 2");
	}
	
	@Test(groups= {"executeGroup"})
	public void test3ClassNew()
	{
		System.out.println("Third Test case in class 2");
	}
	
	@BeforeSuite
	public void executeBeforeSuite() {
		System.out.println("Before Suite executed before any test module executed - class 2");
	}
	
	@AfterSuite
	public void executeAfterSuite() {
		System.out.println("After Suite executed after all test module executed - class 2");
	}
	
	@BeforeClass
	public void executeBeforeClass() {
		System.out.println("Before Class executed before any test module executed - class 2");
	}
	
	@AfterClass
	public void executeAfterClass() {
		System.out.println("After Class executed after all test module executed - class 2");
	}

}
