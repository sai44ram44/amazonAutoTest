package com.naniTech.automation.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.naniTech.automation.pages.IntroPage;
import com.naniTech.automation.pages.SignInPage;
import com.naniTech.automation.testbase.TestBase;

public class SignInPageTest extends TestBase {

	public SignInPageTest() {
		
		super();
	}
	
	SignInPage signinpage;
	
	@BeforeTest
	public void setExtend() {
		TestBase.setExtentReport();
	}
	
	@AfterTest
	public void endReport() {
		TestBase.endExtentReport();
	}
	
	
	@BeforeMethod
	public void setUp() {		
		intilization();
		signinpage = new SignInPage();
		//IntroPage.navToSignInPage();
		driver.findElement(By.xpath("//div[@id='nav-tools']/child::a[@id='nav-link-yourAccount']")).click();
	}
	
	@Test(priority = 1)
	public void checkCreateAccountBtnTest() {
		TestBase.logger = TestBase.extent.startTest("checkCreateAccountBtnTest");
		boolean actualValue = signinpage.verifyCreateAccountBtn();
		Assert.assertTrue(actualValue);
		signinpage.getCreateAccountBtn();
	}
	
	@Test(priority = 2)
	public void loginLogoutAmazonTest() {
		TestBase.logger = TestBase.extent.startTest("loginLogoutAmazonTest");
		signinpage.loginAmazon(prop.getProperty("username", "9030399872"),prop.getProperty("password", "nani44@44"));
		//Assert.assertEquals(signinpage.successfulLogin(), "Hello, "+prop.getProperty("username"));
		signinpage.getLogoutBtn();
	}
	
	/*@Test(priority = 3, dependsOnMethods = "loginAmazonTest")
	public void verifyLoginSuccessfulTest() {
		
		Assert.assertEquals(signinpage.successfulLogin(), "Hello, "+prop.getProperty("username"));
	}
	
	@Test(priority = 4, dependsOnMethods = "loginAmazonTest")
	public void logoutAmazonTest() {
		
		signinpage.getLogoutBtn();
	}*/
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		TestBase.attachStatusToExtentReport(result);
		driver.quit();
	}
}
