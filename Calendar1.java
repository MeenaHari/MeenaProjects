package appiumDay2;
//calender app
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;


public class  Calendar1{
	WebDriver dr;
	@Test
	public void testApp2() throws MalformedURLException, InterruptedException
	{
	
		
	DesiredCapabilities capabilities=new DesiredCapabilities();
	capabilities.setCapability("deviceName","ZX1D329JMD");
	capabilities.setCapability("platformVersion","6.0");
	capabilities.setCapability("platformName","Android");
	
	capabilities.setCapability("appPackage","com.google.android.calendar");
	capabilities.setCapability("appActivity","com.android.calendar.AllInOneActivity");
	
	

	dr=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
	
	dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	//dr.findElement(By.className("android.widget.ImageButton")).click();
	 
	List<WebElement> androidElements = dr.findElements(By.className("android.widget.ImageButton"));
	System.out.println("To get number of elements with same class name:"+androidElements.size());
	
	for(WebElement s:androidElements){
		
	System.out.println("To get name of elements with same class name:"+s.getText());
	}
	//To click"+" on calender app
	dr.findElement(By.id("com.google.android.calendar:id/coordinator_layout")).click();
	
	Thread.sleep(4000);
	dr.quit();
	
	
}
}