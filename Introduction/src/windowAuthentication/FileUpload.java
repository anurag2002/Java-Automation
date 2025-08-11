package windowAuthentication;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		//Defining the download path of the file
		String downloadPath = System.getProperty("user.dir");
		HashMap<String,Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups",0);
		chromePrefs.put("download.default_directory",downloadPath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs",chromePrefs);
		
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://the-internet.herokuapp.com/");	
		driver.findElement(By.linkText("File Download")).click();		
		driver.findElement(By.linkText("Score Report.pdf")).click();
		
		Thread.sleep(5000);
		
		//Checking if the file got downloaded or not
		File f = new File(downloadPath+"/Score Report.pdf");
		if(f.exists()) {
			System.out.println("File Found");
			if(f.delete()) {
				System.out.println("File Deleted");
			}
		}
		else {
			System.out.println("File not Found");
		}
		
		//Call exe file 
		//Runtime.getRuntime().exec("G:\\SELF\\Java Automation\\Java-Automation\\Introduction\\src\\windowAuthentication\\fileupload.exe");

	}

}
