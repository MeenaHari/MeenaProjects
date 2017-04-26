package appiumday3;
//calculator app
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

public class calci1 {
	
	
	WebDriver dr;
	@Test
	public void testApp1() throws InterruptedException, MalformedURLException
	{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setCapability("deviceName","ZX1D329JMD");
		capabilities.setCapability("platformVersion","6.0");
		capabilities.setCapability("platformName","Android");
		
		capabilities.setCapability("appPackage","com.android.calculator2");
		capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
		
		dr=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
		
		dr.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		dr.findElement(By.id("com.android.calculator2:id/digit_5")).click(); // 5   resource id --> id
		
		dr.findElement(By.name("+")).click(); // +  text -->find by name
		
		dr.findElement(By.xpath("//android.widget.Button[@text='9']")).click(); // 9
		
		dr.findElement(By.id("com.android.calculator2:id/eq")).click();  // =
		
		dr.findElement(By.xpath("//android.widget.Button[@text='del']")).click(); //del
		
        dr.findElement(By.id("com.android.calculator2:id/digit_9")).click(); // 9   resource id --> id
		
		dr.findElement(By.id("com.android.calculator2:id/op_sub")).click(); // -  text -->find by name
		
		dr.findElement(By.xpath("//android.widget.Button[@text='5']")).click(); // 5
		
		Thread.sleep(5000);
		dr.quit();
	}

}
