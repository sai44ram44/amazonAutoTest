package com.naniTech.automation.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.naniTech.automation.pages.IntroPage;
import com.naniTech.automation.pages.SignInPage;
import com.naniTech.automation.testbase.TestBase;
import com.naniTech.automation.utils.CustomListner;

@Listeners(CustomListner.class)
public class IntroPageTest extends TestBase{
	
	public IntroPageTest() {
		
		super();
	}
	
	IntroPage intropage;
	SignInPage signinpage = new SignInPage();
	
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
		intropage = new IntroPage();
	}
	
	@Test(priority = 1)
	public void getTitleTest() {
		TestBase.logger = TestBase.extent.startTest("getTitleTest");
		String title = intropage.getTitle();
		Assert.assertEquals(title, "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		//Assert.assertTrue(false);
	}
	
	@Test(priority = 2)
	public void checkAmazonLogoTest() {
		TestBase.logger = TestBase.extent.startTest("checkAmazonLogoTest");
		Boolean logo = intropage.verifyAmazonLogo();
		Assert.assertTrue(logo);
	}
	
	@Test(priority = 4)
	public void searchProductTest() {
		TestBase.logger = TestBase.extent.startTest("searchProductTest");
		intropage.searchProduct("apple iphone x");
		//Assert.assertTrue(true);
	}
	
	@Test(priority = 3)
	public void signInPageTest() {
		TestBase.logger = TestBase.extent.startTest("signInPageTest");
		intropage.navToSignInPage();
		String titleSignin = signinpage.getTitleSignIn();
		Assert.assertEquals(titleSignin, "Amazon Sign In");
	}
	
	@Test(priority = 5)
	public void naviageToProducts() {
		TestBase.logger = TestBase.extent.startTest("naviageToProducts");
		intropage.navSelectedProduct("Beauty, Health, Grocery", "Make-up");
		//Assert.assertTrue(true);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		TestBase.attachStatusToExtentReport(result);
		driver.quit();
	}
}
