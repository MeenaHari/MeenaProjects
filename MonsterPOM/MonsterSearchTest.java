package webAppTestingAssign3MonsterPOM;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import webAppTestingAssign3MonsterPOM.MonsterHomePage;
import webAppTestingAssign3MonsterPOM.MonsterSearchResult;


public class MonsterSearchTest {

private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void testBookSearch() {
		MonsterHomePage home = new MonsterHomePage(driver);
		home.get();
		MonsterSearchResult result = home.getSearch().search("Selenium", "San Jose, CA");
		
		// verify first job title
		assertEquals("Software Engineer in Test - Selenium, Java, Team Lead", result.getFirstJobTitle());
		
		// verify first job company name
		assertEquals("CyberCoders", result.getFirstCompanyName());
		
		// verify first job location
		assertEquals("Palo Alto ,CA", result.getFirstCompanyLocation());
	}
}
