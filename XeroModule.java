package XeroModularFrameWork;



import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class XeroModule extends XeroReUsableMethod{
	
	public static void login() throws IOException{
		// Read test data
				
		        String data_Path = "C:/Users/meena_2/Desktop/tek/TestData/XERO/NavigatetoXero.xls";
				
		        String[][] recData = XeroReUsableMethod.readSheet(data_Path, "Sheet1");
				
				String URL = recData[1][1];
				
				driver.get(URL);
				
				driver.manage().window().maximize();
	}
	

	
	public static void logout() throws IOException{
		WebElement logoutButton = driver.findElement(By.linkText("Logout"));
		
		clickButton(logoutButton,"Logout Button");
	}
}