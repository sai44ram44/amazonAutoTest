package com.naniTech.automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naniTech.automation.testbase.TestBase;

public class ProductsPage extends TestBase {
	
	IntroPage intropage;
	
	public ProductsPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='low-price']")
	WebElement minValue;
	
	@FindBy(xpath = "//input[@id='high-price']")
	WebElement maxValue;
	
	@FindBy(xpath = "//span[@id='a-autoid-19']/span/input[@type='submit' and @class='a-button-input']")
	WebElement clickGo;
	
	@FindBy(xpath="//input[@type='checkbox' and @name='s-ref-checkbox-10440599031']")
	WebElement primeCheck;
	
	public void selectProductByFilter(String mainProduct, String subProduct) {
		
		intropage = new IntroPage();
		intropage.navSelectedProduct(mainProduct, subProduct);
		//minValue.sendKeys(min);	//input MinValue
		//maxValue.sendKeys(max);	//input MaxValue
		//clickGo.click();
		//driver.switchTo().fr
		boolean isCheckPrime = primeCheck.isSelected();
		if(!isCheckPrime) {
			primeCheck.click();
		} else {
			
			System.out.println("PrimeCheck is already checked");
		}
		
		
	}
}
