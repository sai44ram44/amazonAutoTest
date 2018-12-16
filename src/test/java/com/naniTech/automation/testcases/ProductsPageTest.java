package com.naniTech.automation.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.naniTech.automation.pages.ProductsPage;
import com.naniTech.automation.testbase.TestBase;
import com.naniTech.automation.utils.CustomListner;

@Listeners(CustomListner.class)
public class ProductsPageTest extends TestBase {
	
	public ProductsPageTest() {
		super();
	}
	
	ProductsPage productspage = new ProductsPage();

	@BeforeTest
	public void setExtent() {
		TestBase.setExtentReport();
	}
	
	@AfterTest
	public void endExtent() {
		TestBase.endExtentReport();
	}
	
	@BeforeMethod
	public void setUp() {
		intilization();		
	}
	
	@Test
	public void selectProductByFilterTest() {
		
		productspage.selectProductByFilter("Mobiles, Computers", "Tablets");
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		TestBase.attachStatusToExtentReport(result);
		driver.quit();
	}
	
}
