package networkLogs;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.network.Network;
import org.openqa.selenium.devtools.v138.network.model.Request;
import org.openqa.selenium.devtools.v138.network.model.Response;

public class NetworkLogActivities {

	public static void main(String[] args) {
		// TODO Auto-generated method 

		ChromeDriver driver = new ChromeDriver();
		
		//initiate session
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		
		//Enable the network logs
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty()));
		
		//Receive Response - Events
		//The event needs to be triggered when response is received. This will be handled using Listeners. 
		devTools.addListener(Network.responseReceived(), response -> {
			Response res = response.getResponse();
//			System.out.println(res.getUrl());
			
			if(res.getStatus().toString().startsWith("4")) {
				System.out.println(res.getUrl() + " is failing with error code " + res.getStatus());
			}
		});
		
		//Request sent - Events
		//This event will get triggered when the request is about to be sent.
		devTools.addListener(Network.requestWillBeSent(), request -> {
			Request req = request.getRequest();
			System.out.println(req.getUrl());
//			System.out.println(req.getHeaders());
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo");
		
		driver.findElement(By.xpath("//button[text()=' Virtual Library ']")).click();
		
		
	}

}
