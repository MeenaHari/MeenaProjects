package XeroModularFrameWork;
////Modular Driven Approach or framework - Reading test data from excel sheet
//It is combination of Data driven framework and module

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class XeroAutomationScripts extends XeroReUsableMethod{

	//public static WebDriver driver;
	public static String baseUrl;
	
	
	public static void NavigatetoXero() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		//driver = new FirefoxDriver();
		
		// Read test data
		
		String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/NavigatetoXero.xls";

		String[][] recData = XeroReUsableMethod.readSheet(data_Path, "Sheet1");
		
		//String URL = recData[1][1];
		String UN = recData[1][2];
		String PWD = recData[1][3] ;
		String expectedTitle = recData[1][4];
		String expectedDUN= recData[1][5];
		//driver.get(URL);
		//driver.manage().window().maximize();

		//Get xero url from module class
		XeroModule.login();
		
		/* Enter User Name*/
		WebElement userName = driver.findElement(By.xpath("//input[@id = 'email']"));
		enterText(userName, UN, "UserName");

		/* Enter Password*/
		WebElement password = driver.findElement(By.xpath(".//input[@id='password']"));
		enterText(password,PWD , "Password");
		
		Thread.sleep(5000);
		WebElement loginButton = driver.findElement(By.xpath("//div/button"));
		//loginButton.click();
		clickButton(loginButton,"Login Button");
		
		/*//To check User's Home Page is displayed
		Thread.sleep(5000);
		System.out.print("Actual Title");
		System.out.println(driver.getTitle());
		Thread.sleep(5000);
		String actualTitle = driver.getTitle();

		if(expectedTitle.equals(actualTitle))
		{
			//System.out.println("Expected error message is matching to actual error message.");
			Update_Report("Pass", "Validate Title","Expected Title is matching to actual Title. ");
		}
		else
		{
			Update_Report("Fail", "Validate Title", "Expected Title is NOT matching to actual Title.");
		}*/
		
		Thread.sleep(5000);
		//To click DashBoard UserName
		WebElement DashBoardUserName = driver.findElement(By.xpath("//div/a[@class='username']"));
		String ActualDashBoardUserName = DashBoardUserName.getText();
	//	System.out.println("CheckPoint:Actual Dash Board UserName:"+ActualDashBoardUserName);
		//WebElement DashBoardUserName = driver.findElement(By.cssSelector("a.username"));
		//DashBoardUserName.click();
		
		//enterText(DashBoardUserName, DUN, "Dash Board UserName");
		Actions actions = new Actions(driver);
		actions.moveToElement(DashBoardUserName);
		actions.click();
		System.out.println("CheckPoint:Expected UserName:"+expectedDUN);
		actions.sendKeys(expectedDUN);
		actions.build().perform();
		
		//To validate UserName
		System.out.print("Actual Dash Board UserName:"+DashBoardUserName.getText());
		
		//Thread.sleep(5000);
	
		if(expectedDUN.equals(ActualDashBoardUserName))
		{
			//System.out.println("Expected error message is matching to actual error message.");
			Update_Report("Pass", "Validate Dash Board user Name","Expected Dash Board user Name is matching to actual Dash Board user Name. ");
		}
		else
		{
			Update_Report("Fail", "Validate Dash Board user Name", "Expected Dash Board user Name is NOT matching to actual Dash Board user Name.");
		}
		
		//WebElement pageTitle = driver.findElement(By.xpath("//div[@id = 'page_title']"));
		//checkElement(pageTitle, "Page title");
		
	}

	public static void IncorrectPassword() throws IOException, InterruptedException{
		// Read test data
		
		String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/IncorrectPassword.xls";

		String[][] recData = XeroReUsableMethod.readSheet(data_Path, "Sheet1");
		
		//String URL = recData[1][1];
		String UN = recData[1][2];
		String PWD = recData[1][3] ;
		//String expectedErrorMsg = "Please enter your password.";
		String expectedErrorMsg = recData[1][4];
		
		//driver = new FirefoxDriver();
		//driver.get(URL);
		//driver.manage().window().maximize();
		
		//Get salesforce url from module class
		XeroModule.login();
		
		//Enter user name
		//WebElement userName = driver.findElement(By.id("username"));
		WebElement userName = driver.findElement(By.xpath("//input[@id = 'email']"));
		enterText(userName,UN , "UserName");
		
		
		//Enter Password
		//WebElement password = driver.findElement(By.id("password"));
		WebElement password = driver.findElement(By.xpath(".//input[@id='password']"));
		enterText(password,PWD , "Password");
		//password.clear();
		
		//WebElement loginButton = driver.findElement(By.id("Login"));
		
		//loginButton.click();
		Thread.sleep(5000);
		WebElement loginButton = driver.findElement(By.xpath("//div/button"));
		clickButton(loginButton,"Login Button");
		
		Thread.sleep(5000);
		String actualErrorMsg = driver.findElement(By.xpath("//div[@class='x-boxed warning']/p")).getText().trim();

		if(expectedErrorMsg.equals(actualErrorMsg))
		{
			//System.out.println("Expected error message is matching to actual error message.");
			Update_Report("Pass", "Validate Error Message","Expected error message is matching to actual error message. ");
		}
		else
		{
			Update_Report("Fail", "Validate Error Message", "Expected error message is NOT matching to actual error message.");
		}
	}


	


	public static void  ForgotPassword() throws InterruptedException, IOException{
		// TODO Auto-generated method stub
		//driver = new FirefoxDriver();
		// Read test data
				String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/ForgotPassword.xls";

				String[][] recData = XeroReUsableMethod.readSheet(data_Path, "Sheet1");
				
				//String URL = recData[1][1];
				String UN = recData[1][2];
				String PWD = recData[1][3] ;
				String ExpectedPasswordresetmessage = recData[1][4] ;
		//driver.get(URL);
		//driver.manage().window().maximize();
				
		//Get salesforce url from module class
		XeroModule.login();

		//WebElement userName = driver.findElement(By.id("username"));
		//userName.sendKeys("meesadeas@gmail.com");
		WebElement userName = driver.findElement(By.xpath("//input[@id = 'email']"));
		enterText(userName, UN, "UserName");

		//WebElement password = driver.findElement(By.id("password"));
		//password.sendKeys("easwar2102");
		//enterText(password, "easwar2102", "Password");
		WebElement password = driver.findElement(By.xpath(".//input[@id='password']"));
		enterText(password, PWD , "Password");
   
		
		//click forgot password link
		Thread.sleep(5000);
		WebElement loginButton = driver.findElement(By.xpath("//a[@class= 'forgot-password-advert']"));
		clickButton(loginButton,"Login Button");

		/* Enter email id*/
		Thread.sleep(5000);
		WebElement emailID = driver.findElement(By.xpath("//input[@id = 'UserName']"));
		enterText(emailID, UN, "Email ID");

		Thread.sleep(5000);
		WebElement sendLinkButton = driver.findElement(By.xpath("//div[@class='actions']/div[@id='submitButton']"));
		clickButton(sendLinkButton,"Send Link Button");
		
		Thread.sleep(8000);
		String actualPasswordresetmessage = driver.findElement(By.xpath("html/body/div[2]/div/div/div/p[1]")).getText().trim();

		//Check password reset message display
		
		if(ExpectedPasswordresetmessage.equals(actualPasswordresetmessage))
		{
			//System.out.println("Expected error message is matching to actual error message.");
			Update_Report("Pass", "Validate Password reset message","Expected Password reset message is matching to actual Password resetmessage. ");
		}
		else
		{
			Update_Report("Fail", "Validate Password reset message", "Expected Password reset message is NOT matching to actual Password reset message.");
		}
	
		//WebElement logoutButton = driver.findElement(By.linkText("Logout"));
		//logoutButton.click();
		//clickButton(logoutButton,"Logout Button");
		//XeroModule.logout();
		
	}


}

