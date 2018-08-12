package com.naniTech.automation.testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import com.naniTech.automation.utils.TestUtils;
import com.relevantcodes.extentreports.*;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
	public TestBase() {
		try {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\padampullaiah\\eclipse-workspace\\AmazonAutomation\\src"
				+ "\\main\\java\\com\\naniTech\\automation\\config\\config.properties");
		prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void intilization() {
		String browserType = prop.getProperty("browser");
		if(browserType.equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", "E:\\Sairam\\Selenium Drivers\\chromedriver.exe");
			driver = new ChromeDriver();	
		}
		else if(browserType.equals("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "E:\\Sairam\\Selenium Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_TIMEOUT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}
	
	public static void takeScreenshotFailed(String testMethodName) throws IOException {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:\\Users\\padampullaiah\\eclipse-workspace"
				+ "\\AmazonAutomation\\screenshots\\"+testMethodName+".jpg"));
	}
	
	public static void setExtentReport() {
		extent = new ExtentReports(System.getProperty("user.dir")+"\\test-output\\ExtentReports.html", true);
		extent.addSystemInfo("Host Name", "SaiRam D");
		extent.addSystemInfo("OS", "Windows 7");
		extent.addSystemInfo("Environment", "QA");
	}
	
	public static void endExtentReport() {
		extent.flush();
		extent.close();
	}
	
	public static String screenShotExtentReport(WebDriver driver,String screenshotName) throws IOException {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"\\FailedTestsScreenshots\\"
		+screenshotName+dateName+".png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}
	
	public static void attachStatusToExtentReport(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			
			logger.log(LogStatus.FAIL, "Test case Failed"+result.getName());
			logger.log(LogStatus.FAIL,"Test case Failed"+result.getThrowable());
			String screenshotpath = TestBase.screenShotExtentReport(driver, result.getName());
			logger.log(LogStatus.FAIL,logger.addScreenCapture(screenshotpath));
		}
		extent.endTest(logger);//ending test and ends the current test and prepare to create html report
	}
	
}
