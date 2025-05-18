package introduction;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Introduction_To_TestNG_5 {
//	@Parameters({"username","password"})
//	@Test
//	public void printGlobalVariable(String usrName, String pass) {
//		System.out.println("Username: " + usrName);
//		System.out.println("Password: " + pass);
//	}
	
	@Test(dataProvider="getData")
	public void printData(String user, String pass) {
		System.out.println("Username: " + user);
		System.out.println("Password: " + pass);
	}
	
	@Test
	public void testFailure() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods= {"printData"})
	public void print() {
		System.out.println("Print from class 5");
	}
	
	@Parameters({"localUsername","localPassword"})
	@Test
	public void printLocalVariable(String localUsrName, String localPass) {
		System.out.println("Username: " + localUsrName);
		System.out.println("Password: " + localPass);
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[3][2];
		data[0][0] = "ankit.pathak";
		data[0][1] = "Edifecs1234";
		data[1][0] = "chesta.gupta";
		data[1][1] = "Alabama1234";
		data[2][0] = "ankita.goyal";
		data[2][1] = "Manager1234";
		
		return data;
	}

}
