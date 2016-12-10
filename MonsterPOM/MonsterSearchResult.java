package webAppTestingAssign3MonsterPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import webAppTestingAssign3MonsterPOM.MonsterSearchResult;

public class MonsterSearchResult extends LoadableComponent<MonsterSearchResult> {
	
	private WebDriver driver;
	private String query1,query2;
	
	// List of jobs in the search result
	//@FindBy( how = How.XPATH, using = "//*[@id='resultsWrapper']")
	@FindBy( how = How.XPATH, using = "//*[@id='resultsWrapper']//*[@class='js_result_row']")
	private WebElement resultList;
	
	public MonsterSearchResult(WebDriver driver, String query1,String query2) {
		//System.out.println("Check point1");
		//System.out.println(query1);
		//System.out.println(query2);
		
		this.driver = driver;
		this.query1 = query1;
		this.query2 = query2;
		
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!driver.getTitle().equals("Monster.com: " + query1)) {
			throw new Error("page cannot be loaded");
		}
		if (!driver.getTitle().equals("Monster.com: " + query2)) {
			throw new Error("page cannot be loaded");
		}
	}
	
	@Override
	protected void load() { }
	
	public String getFirstJobTitle() {
		//System.out.println("Check point2");
		System.out.println("The job title, company name and the location of the first returned result");
		
		// job title element
		WebElement JobTitle =  resultList.findElement(By.xpath("//div[@class='jobTitle']/h2//span"));
		System.out.println(JobTitle.getText());
		return JobTitle.getText();
	}
	
	public String getFirstCompanyName() {
		// company name element
		WebElement companyName = resultList.findElement(By.xpath("//div[@class='company']//span")); 
		System.out.println(companyName.getText());
		return companyName.getText();
	}

	public String getFirstCompanyLocation() {
		// company location element
		WebElement companyLocation = resultList.findElement(By.xpath("//div[@class='location']//span"));
		System.out.println(companyLocation.getText());
		return companyLocation.getText();
	}
	

}
