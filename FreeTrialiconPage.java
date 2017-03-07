package xeroPageObjectModel;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FreeTrialiconPage extends XeroReUsableMethod1 {
	
	public void clickTrialButton() throws IOException{
		//Click Trial login button
		//WebElement loginButton = driver.findElement(By.id("Login"));
		//WebElement loginButton = driver.findElement(By.cssSelector("button[id='submitButton']"));
		WebElement trialButton = driver.findElement(By.cssSelector(" a[href*='signup']"));
		clickButton(trialButton, "Trial Button");
	}
	
	//Enter first name
	public void enterFirstName(String textVal) throws IOException{
		WebElement firstname = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[name='FirstName']"));
		
		enterText(firstname, textVal, objName);
		
	}
	//Enter LAST name
	public void enterLastName(String textVal) throws IOException{
		WebElement lastname = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[name='LastName']"));
		
		enterText(lastname, textVal, objName);
		
	}

	//Enter email id
	public void enterEmailid(String textVal) throws IOException{
		WebElement emailId = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[name='EmailAddress']"));

		enterText(emailId, textVal, objName);

	}

	//Enter phone number
	public void enterPhoneNum(String textVal) throws IOException{
		WebElement PhoneNum = XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("input[name='PhoneNumber']"));

		enterText(PhoneNum, textVal, objName);

	}
	
	
	//Select Country Name
		public void selectCountryName(String textVal) throws IOException{
			WebElement  selectCountryName= XeroAutomationScriptsDriver1.driver.findElement(By.cssSelector("select[class='form-input']"));
			Select country = new Select(selectCountryName);
			country.selectByVisibleText(textVal);
			System.out.println("country name:" + textVal);
			enterText(selectCountryName, textVal, objName);

		}
		
		
   // Check Check box
		public void checkBox() throws IOException{
			
			/* switch to iframe*/
			WebElement checkBox = driver.findElement(By.xpath("html/body/div[6]/main/div[1]/div/div/form/div[8]/div/div/iframe"));
			driver.switchTo().frame(checkBox);
			WebElement frameWindow = driver.findElement(By.xpath("//div[@class='rc-anchor-content']//span"));
			enterText1(frameWindow, "iFrame");
			
			
			selectCheckBox(frameWindow, " CheckBox");
			driver.switchTo().defaultContent();
		}
		
		
		// Check Terms and Agree Check box
				public void termsCheckBox() throws IOException{
					
					WebElement termsCheckBox = driver.findElement(By.cssSelector("input[name='TermsAccepted']"));
					
					selectCheckBox(termsCheckBox, "Terms checkBox");
				}
	/*public void enterPwd(String textval) throws IOException{
		Enter Password
		WebElement password = driver.findElement(By.cssSelector("input[id='password']"));
		enterText(password,textval , "Password");
	}*/
	
	public void getStartedButton() throws IOException{
		
		WebElement getStartedButton = driver.findElement(By.cssSelector("span[class='g-recaptcha-submit']"));
		
		clickButton(getStartedButton, "GetStarted Button");
	}
	
   //Validate Successful sign up by seeing inbox page
	public void validateInboxPage(String expectedInboxPageMsg) throws IOException{
		String actualInboxPageMsg = driver.findElement(By.cssSelector("p[class='email-dynamic']")).getText().trim();

		if(expectedInboxPageMsg.equals(actualInboxPageMsg))
		{
			
			Update_Report("Pass", "ValidateInboxPageMessage","Expected InboxPage message is matching to actual InboxPage message. ");
		}
		else
		{
			Update_Report("Fail", "ValidateInboxPageMessage", "Expected InboxPage message is NOT matching to actual InboxPage message.");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
