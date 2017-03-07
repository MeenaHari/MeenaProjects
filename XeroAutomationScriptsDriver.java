package XeroModularFrameWork;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class XeroAutomationScriptsDriver {
	public static WebDriver driver;
	public static int reportflag = 0;
	
	public static void main(String[] args) throws InterruptedException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		
		String dataPath = "C:/Users/meena_2/Desktop/tek/XLSFile/XERO/XeroTestSuite2.xls";

		String[][] recData = XeroReUsableMethod.readSheet(dataPath, "Sheet1");
		for(int i = 1; i <recData.length; i++){

			if(recData[i][1].equalsIgnoreCase("Y")){


				/*Firefox*/

				if(recData[i][3].equalsIgnoreCase("Y")){
					driver = new FirefoxDriver();
					reportflag = 0 ;//Reset
					String testScript = recData[i][2];
					XeroReUsableMethod.startReport(testScript, "C:/Users/meena_2/Desktop/tek/Report/");

					/*Java Reflection or Reflexive API*/
					Method ts = XeroAutomationScripts.class.getMethod(testScript);
					ts.invoke(ts);

					driver.quit();
					System.out.println();

					if(reportflag == 0){
						System.out.println("CheckPoint1");
						XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 3, "Pass");
					}
					else{
						System.out.println("CheckPoint2");
						XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 3, "Fail");
					}


				}else{
					System.out.println("Row number :" +i+ " script is not executed..");
					System.out.println();
					XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 3, "NA");
				}


				if(recData[i][4].equalsIgnoreCase("Y")){
					/*Chrome*/
					System.setProperty("webdriver.chrome.driver", "./drivers/chromeDriver.exe");
				       driver = new ChromeDriver();
				       driver.manage().window().maximize();
					reportflag = 0 ;//Reset
					String testScript = recData[i][2];
					XeroReUsableMethod.startReport(testScript, "C:/Users/meena_2/Desktop/tek/Report/");

					/*Java Reflection or Reflexive API*/
					Method ts = XeroAutomationScripts.class.getMethod(testScript);
					ts.invoke(ts);

					driver.quit();
					System.out.println();

					if(reportflag == 0){
						System.out.println("CheckPoint4");
						XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 4, "Pass");
					}
					else{
						XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 4, "Fail");
					}

				  }else{
						System.out.println("Row number :" +i+ " script is not executed..");
						System.out.println();
						XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 4, "NA");
				  }
				
				}else{
					System.out.println("Row number :" +i+ " script is not executed..");
					System.out.println();
					System.out.println("CheckPoint5");
					XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 3, "NA");
					System.out.println("CheckPoint6");
					XeroReUsableMethod.writeExcel(dataPath, "Sheet1", i, 4, "NA");
					System.out.println("CheckPoint7");
				}

			System.out.println("CheckPoint8");
			XeroReUsableMethod.bw.close();





			}






			


		}


}