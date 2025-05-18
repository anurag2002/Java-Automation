package introduction;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Introduction_To_TestNG_4 {
	@Test(dependsOnMethods= {"test3ClassNew","test2ClassNew"})
	public void firstTestClassNew() {
		System.out.println("First Test in class 4");
	}
	
	@Test(groups= {"executeGroup"},dependsOnMethods= {"test3ClassNew"})
	public void test2ClassNew()
	{
		System.out.println("Second Test case in class 4");
	}
	
	@Test
	public void test3ClassNew()
	{
		System.out.println("Third Test case in class 4");
	}
	
	@Parameters({"username","password"})
	@Test
	public void printGlobalVariable(String usrName, String pass) {
		System.out.println("Username: " + usrName);
		System.out.println("Password: " + pass);
	}

}
