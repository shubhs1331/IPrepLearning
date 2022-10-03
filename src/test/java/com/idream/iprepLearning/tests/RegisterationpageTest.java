package com.idream.iprepLearning.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.idream.iprepLearning.Pages.BrowserPage;
import com.idream.iprepLearning.Pages.DashBoardPage;
import com.idream.iprepLearning.Pages.RegistrationPage;
import com.idream.iprepLearning.Pages.SignInPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class RegisterationpageTest {
	
	private WebDriver driver;
	RegistrationPage rp;
	static String pageURL = "https://learn.iprep.in/Registration";
	static String signUpURL = "https://learn.iprep.in/";
	public ExtentReports extent;
	public ExtentTest extentTest;

	@BeforeTest
	public void setup() {
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true);
		extent.addSystemInfo("HostName","DESKTOP-9IJIBQ");
		extent.addSystemInfo("Username","Shubhangi Goel");
		extent.addSystemInfo("Environment","QA");
		
	}
	
	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}
	
	public static String getScreenshot(WebDriver driver,String screenshotName) throws IOException {
		String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot sc = (TakesScreenshot)driver;
		File source= sc.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir")+"/failedScreenshots"+screenshotName+date+".png";
		//String dest = System.getProperty("user.dir")+"/failedScreenshots"+screenshotName+date+".mp4";
		File finalDest = new File(dest);
		FileUtils.copyFile(source, finalDest);
		return dest;		
	}
	
	@BeforeMethod
	public void loginSetup() {
		BrowserPage browser = new BrowserPage();
		driver = browser.launchBrowser();
	}
	
	@Test(priority=1)
	public void register() {
		extentTest = extent.startTest("register user");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.validateSignUp();
	}

	@Test(priority=2)
	public void validateCred() {
		extentTest = extent.startTest("Invalid Credentials");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.invalidCredentials();
	}
	
	@Test(priority=3)
	public void validCredentials() throws InterruptedException {
		extentTest = extent.startTest("Valid Credentials");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.validateCredentials();
	}
	
	@Test(priority=4)
	public void verifyOTP() throws InterruptedException {
		extentTest = extent.startTest("Verify OTP");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.otpVerification();
	}
	
	@Test(priority=5)
	public void validatePasswordBar() throws InterruptedException {
		extentTest = extent.startTest("Validate Password Progress Bar");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.passwordBar();
	}
	
	@Test(priority=6)
	public void validateUsername() throws InterruptedException {
		extentTest = extent.startTest("Validate Username");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.validateUsername();
	}
	
	@Test(priority=7)
	public void signUp() throws InterruptedException {
		extentTest = extent.startTest("Validate User Login");
		driver.get(signUpURL);
		SignInPage sign = new SignInPage(driver);
		sign.validSignUp();
	}
	
	@Test(priority=8)
	public void invalidSignUp() throws InterruptedException {
		extentTest = extent.startTest("Invalid Login attempt");
		driver.get(signUpURL);
		SignInPage sign = new SignInPage(driver);
		sign.invalidSignUp();
	}
	
	@Test(priority=8)
	public void validateGmailSignUp() throws InterruptedException {
		extentTest = extent.startTest("Login with Gmail");
		driver.get(signUpURL);
		SignInPage sign = new SignInPage(driver);
		sign.gmailSignUp();
	}
	
	@Test(priority=9)
	public void validateRememberMeCheckBox() {
		extentTest = extent.startTest("Validate Remember Me Checkbox");
		driver.get(signUpURL);
		SignInPage sign = new SignInPage(driver);
		sign.validateRememberMe();
	}
	
	@Test(priority=10)
	public void validateChangeBoard() throws InterruptedException {
		extentTest = extent.startTest("Validate Board");
		driver.get(signUpURL);
		SignInPage sign = new SignInPage(driver);
		sign.validSignUp();
		DashBoardPage db = new DashBoardPage(driver);
		db.changeBoard();
	}

	@Test(priority=11)
	public void onBoard() throws InterruptedException {
		extentTest = extent.startTest("Validate Board");
		driver.get(pageURL);
		rp = new RegistrationPage(driver);
		rp.validateCredentials();
		driver.navigate().to("https://learn.iprep.in/signup");
		DashBoardPage db = new DashBoardPage(driver);
		db.onBoardFlow();
	}

	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED : "+result.getName());
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED : "+result.getThrowable());
			
			String screenshotPath = RegisterationpageTest.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); // to add screenshot in extent report
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "TEST CASE SKIPPED : "+result.getName());
		}
		
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "TEST CASE PASSED : "+result.getName());
			String passScreenshotPath = RegisterationpageTest.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.PASS, extentTest.addScreenCapture(passScreenshotPath));
			//extentTest.log(LogStatus.PASS, extentTest.addScreencast(passScreenshotPath)); // to add screenshot in extent report
		}
		extent.endTest(extentTest);
		driver.quit();
	}
}
