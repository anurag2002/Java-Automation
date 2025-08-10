package jdbcConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {
		
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");

		// Connect to DB
		String host = "localhost";
		String port = "3306";
		Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/auto_db", "root",
				"Alert@1234");

		// Execute Query
		Statement s = conn.createStatement();
		ResultSet rs = s.executeQuery("SELECT * FROM employee_info");

		while (rs.next()) {
			

			// Print Output
			driver.findElement(By.id("username")).sendKeys(rs.getString("name"));
			driver.findElement(By.id("password")).sendKeys(rs.getString("id"));
		}
		
		
	}

}
