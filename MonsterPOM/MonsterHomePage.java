package webAppTestingAssign3MonsterPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import webAppTestingAssign3MonsterPOM.MonsterHomePage;
import webAppTestingAssign3MonsterPOM.MonsterSearch;

public class MonsterHomePage extends LoadableComponent<MonsterHomePage> {
	
	private WebDriver driver;
	
	private String url = "https://www.monster.com";
	
	public MonsterHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!driver.getTitle().startsWith("Monster Jobs")) {
			throw new Error("page cannot be loaded");
		}
	}

	@Override
	protected void load() {
		driver.get(url);
	}
	
	public MonsterSearch getSearch() {
		return new MonsterSearch(driver);
	}
	

}
