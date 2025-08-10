package networkLogs;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Predicate;

import java.net.URI;

public class BasicAuthentication {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method

		ChromeDriver driver = new ChromeDriver();

		// Predicates helps to filter out data
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");

		// Selenium has introduced HasAuthentication and UsernameAndPassword classes to
		// handle authentications. We cannot handle this normally as this is a window
		// pop up.
		((HasAuthentication) driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
		driver.get("https://httpbin.org/basic-auth/foo/bar");

	}

}
