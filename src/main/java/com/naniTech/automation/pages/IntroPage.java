package com.naniTech.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naniTech.automation.testbase.TestBase;

public class IntroPage extends TestBase {
	
	public IntroPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='nav-logo']/child::a[1][@class='nav-logo-link']")
	@CacheLookup
	WebElement logoAmazon;
	
	@FindBy(xpath = "//div[@id='nav-tools']/child::a[@id='nav-link-yourAccount']")
	WebElement signInBtn;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Go']")
	WebElement searchBtn;
	
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchInput;
	
	@FindBy(xpath = "//a[@id='nav-link-shopall']")
	WebElement linkAllProductsBtn;
	
	public String getTitle() {
		
		return driver.getTitle();
		
	}
	
	public boolean verifyAmazonLogo() {
		
		return logoAmazon.isDisplayed();
	}
	
	public SignInPage navToSignInPage() {
		
		signInBtn.click();
		return new SignInPage();
	}
	
	public ProductsPage searchProduct(String productName) {
		
		searchInput.sendKeys(productName);
		searchBtn.click();
		return new ProductsPage();
	}
	
	public void navSelectedProduct(String mainProduct, String subProduct) {
		Actions action = new Actions(driver);
		WebElement category = driver.findElement(By.xpath("//a[@id='nav-link-shopall']"));
		action.moveToElement(category).build().perform();
		WebElement mainProductLink = driver.findElement(By.xpath("//div[@id='nav-flyout-shopAll']/child::div[2]"
				+ "/span[@aria-label='"+ mainProduct +"']"));
		
		action.moveToElement(mainProductLink).build().perform();
		WebElement subProductLink = driver.findElement(By.xpath("//div[@id='nav-flyout-shopAll']/div[3]/descendant::span[text()='"+ subProduct +"'] | //div[@id='nav-flyout-shopAll']/child"
					+ "::div[3]/descendant::span//span[text() = '"+ subProduct +"']"));
		subProductLink.click();
		
	}

}
