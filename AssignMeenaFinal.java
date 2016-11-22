package webAppTestingAssign1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AssignMeenaFinal {

	@Test
	public void testAmazonSearchResult(){
		System.setProperty("webdriver.chrome.driver", "./drivers/chromeDriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");

		WebElement input = driver.findElement(By.id("twotabsearchtextbox"));
		input.sendKeys("Selenium WebDriver");
		WebElement searchIcon = driver.findElement(By.className("nav-input"));
		searchIcon.click();

		// Now I am in the result page
		// Number Of books listed in this page
		/*
	       String result = driver.findElement(By.id("s-result-count")).getText().split(" ")[0];
	       String numpage = result.split("-")[1];
	       System.out.println("Number Of books listed in this page:" + numpage);
	       //assertEquals("1-16 of 75 results for \"Selenium WebDriver\"",status.getText());
		 */



		//List of booktitles
		//WebElement booktitles = driver.findElement("By.id("s-results-list-atf")");
		List<WebElement> booklist = driver.findElements(By.xpath("//ul[@id='s-results-list-atf']/li"));

		System.out.println();
		System.out.println("Number of books listed in this page: " + booklist.size());
		System.out.println("Book title and its paper back price\n");
		for (WebElement book : booklist){

			WebElement booktitle = book.findElement(By.xpath(".//h2"));
			System.out.println(booktitle.getText());
			try {
				//WebElement paperback = book.findElement(By.xpath(".//h3[@data-attribute='Paperback']"));
				WebElement paperback = book.findElement(By.xpath(".//a[@title='Paperback']"));
				//WebElement parent = paperback.findElement(By.xpath("../.."));
				WebElement price = paperback.findElement(By.xpath("../following-sibling::*"));
				//WebElement price = price.findElement(By.xpath(".//span[@class='a-size-base a-color-price s-price a-text-bold']"));
				System.out.println(price.getText());
			} catch (org.openqa.selenium.NoSuchElementException e) {
				System.out.println("No paperback price");
			}
			System.out.println();
		}
		driver.quit();
	}
}
