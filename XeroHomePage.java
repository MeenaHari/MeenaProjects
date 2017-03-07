package xeroPageObjectModel;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import modularDrivenFrameWork.ReUsableMethod;


public class XeroHomePage extends XeroReUsableMethod1{
	
	
	
	public void lauchApp(String textVal){
		driver.get(textVal);
		
		driver.manage().window().maximize();
	}
	
	/*public  void clickLogin() throws IOException{
		
		//WebElement webObj = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[id='email']"));
		
		clickButton(webObj, objName);
		
	}*/

}


