
package xeroPageObjectModel;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

//import modularDrivenFrameWork.ReUsableMethod;


public class XeroAutomationScripts1 extends XeroReUsableMethod1{

	// Page object model --> POM
	

	public static void IncorrectEmail() throws IOException, InterruptedException{
		// Get test Data sheet
		

		String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/IncorrectEmail.xls";

		String[][] recData = XeroReUsableMethod1.readSheet(data_Path, "Sheet1");
		
		String URL = recData[1][1];
		String UN = recData[1][2];
		String PWD = recData[1][3] ;
		String expectedErrorMsg = recData[1][4];
		XeroHomePage xeroLandPage = new XeroHomePage();
		
		xeroLandPage.lauchApp(URL);
		
		
		
		XeroLoginPage xeroLoginPage = new XeroLoginPage();
		
		xeroLoginPage.enterUserName(UN);
		xeroLoginPage.enterPwd(PWD);
		Thread.sleep(5000);
		xeroLoginPage.clickButton();
		Thread.sleep(5000);
		xeroLoginPage.validateErrorMessage(expectedErrorMsg);
		
	}


	public static void SignUpToxero()throws IOException, InterruptedException{
		
		// Get test Data sheet
		
		String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/SignUpToxero.xls";

		String[][] recData = XeroReUsableMethod1.readSheet(data_Path, "Sheet1");
		
		String URL = recData[1][1];
		String FN = recData[1][2];
		String LN = recData[1][3] ;
		String EmailId = recData[1][4] ;
		String PhNo = recData[1][5] ;
		String CN = recData[1][6] ;
		String expectedInboxPageMsg = recData[1][7];
		XeroHomePage SFLandPage = new XeroHomePage();
		
		SFLandPage.lauchApp(URL);
		
		FreeTrialiconPage Trial = new FreeTrialiconPage();
		Thread.sleep(5000);
		Trial.clickTrialButton();
		Thread.sleep(5000);
		Trial.enterFirstName(FN);
		Trial.enterLastName(LN);
		Trial.enterEmailid(EmailId);
		Trial.enterPhoneNum(PhNo);
		Trial.selectCountryName(CN);
		Thread.sleep(5000);
		Trial.checkBox();
		Thread.sleep(5000);
		Trial.termsCheckBox();
		Thread.sleep(5000);
		Trial.getStartedButton();
		Thread.sleep(7000);
		Trial.validateInboxPage(expectedInboxPageMsg);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	/*public static void CheckRemeberMe() throws InterruptedException{
	
	}*/

	
	
}
