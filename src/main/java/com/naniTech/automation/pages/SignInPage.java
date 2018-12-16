package com.naniTech.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.naniTech.automation.testbase.TestBase;

public class SignInPage extends TestBase {

	public SignInPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@id='createAccountSubmit']")
	WebElement createAccountBtn;
	
	@FindBy(xpath = "//input[@type='email']")
	WebElement uName;
	
	@FindBy(xpath = "//input[@id='continue']")
	WebElement continueBtn;
	
	@FindBy(xpath = "//input[@type='password']")
	WebElement passwd;
	
	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement signInBtn;
	
	@FindBy(xpath = "//div[@id='nav-tools']/a[@id='nav-link-yourAccount']/span[1]")
	WebElement welcomeNote;
	
	@FindBy(xpath = "//a[@id='nav-item-signout-sa' and ./span[contains(.,'Sign Out')]]")
	WebElement signOutLink;
	
	
	public String getTitleSignIn() {
		
		return driver.getTitle();
	}
	
	public RegistrationPage getCreateAccountBtn() {
		
		createAccountBtn.click();
		return new RegistrationPage();
	}
	
	public boolean verifyCreateAccountBtn() {
		
		return createAccountBtn.isDisplayed();
	}
	
	public IntroPage loginAmazon(String username, String password) {
		
		uName.sendKeys(username);
		continueBtn.click();
		passwd.sendKeys(password);
		signInBtn.click();
		return new IntroPage();
		
	}
	
	public String successfulLogin() {
		String actualNote = welcomeNote.getText();
		return actualNote;
		
	}
	
	public void getLogoutBtn() {
		
		Actions action = new Actions(driver);
		WebElement yourOrderLink = driver.findElement(By.xpath("//div[@id='nav-tools']/a[@id='nav-link-yourAccount']"));
		action.moveToElement(yourOrderLink).build().perform();
		signOutLink.click();
	}
	
}
