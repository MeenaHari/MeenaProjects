package webAppTestingAssign3MonsterPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import webAppTestingAssign3MonsterPOM.MonsterSearchResult;

// MonsterSeach class
public class MonsterSearch {
	
	private WebDriver driver;
	
	// search job title element
	@FindBy(how = How.XPATH, using = "//div[@class='input-group input-group-sm']")
	private WebElement searchJob;
	
	// search location element
	@FindBy(how = How.XPATH, using = "//div[@class='form-group top-search location']")
	private WebElement searchLocation;
	
	// search button element
	@FindBy(how = How.ID, using = "doQuickSearch")
	private WebElement searchButton;
	
	public MonsterSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public MonsterSearchResult search(String query1, String query2) {
		System.out.println();
/*		
  		System.out.print(searchJob.getAttribute("class"));
		System.out.println();
		System.out.print(searchJob.getAttribute("placeholder"));
		System.out.println();
		System.out.print(searchJob.isDisplayed());
		System.out.println();
		System.out.print(searchJob.isEnabled());
		System.out.println();
		System.out.println("query1: " + query1);
		System.out.println("query2: " + query2);
*/
				
		// create actions to focus the searchJob element and send keys
		Actions builder = new Actions(driver);
		// send query1 for job title 
		builder.moveToElement(searchJob).click().sendKeys(query1).build().perform();
		// send query2 for job location
		builder.moveToElement(searchLocation).click().sendKeys(query2).build().perform();
		// click search submit button
		searchButton.click();
		
		// return search result
		return new MonsterSearchResult(driver, query1, query2);
	}
}
