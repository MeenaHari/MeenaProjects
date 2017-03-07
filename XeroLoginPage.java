package xeroPageObjectModel;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class XeroLoginPage extends XeroReUsableMethod1 {

	public void enterUserName(String textVal) throws IOException{
		WebElement username = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[id='email']"));
		
		enterText(username, textVal, objName);
		
	}
	
	
	public void enterPwd(String textval) throws IOException{
		/*Enter Password*/
		WebElement password = driver.findElement(By.cssSelector("input[id='password']"));
		enterText(password,textval , "Password");
	}
	
	public void clickButton() throws IOException{
		
		WebElement loginButton = driver.findElement(By.xpath("//div/button"));
		clickButton(loginButton, "Login Button");
	}
	
	
	
	
	
	public void validateErrorMessage(String expectedErrorMsg) throws IOException{
		String actualErrorMsg = driver.findElement(By.cssSelector("div[class='x-boxed warning']")).getText().trim();

		if(expectedErrorMsg.equals(actualErrorMsg))
		{
			//System.out.println("Expected error message is matching to actual error message.");
			Update_Report("Pass", "ValidateErrorMessage","Expected error message is matching to actual error message. ");
		}
		else
		{
			Update_Report("Fail", "ValidateErrorMessage", "Expected error message is NOT matching to actual error message.");
		}
	}
}
