package com.idream.iprepLearning.Pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.idream.iprepLearning.pageConstants.RegistrationPageConstants;

public class RegistrationPage {
	
	String expectedError;
	String actualError;
	String expectedResult;
	String actualResult;
	String color;
	WebDriver driver;
	WebDriverWait wait;
	String verifyOtpUrl = "https://learn.iprep.in/verifyOTP";
	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id=RegistrationPageConstants.USERNAME)
	public WebElement username;
	
	@FindBy(id=RegistrationPageConstants.USERMAIL)
	public WebElement usermail;
	
	@FindBy(id=RegistrationPageConstants.PASSWORD)
	public WebElement password;
	
	@FindBy(className =RegistrationPageConstants.SIGNUPBUTTON)
	public WebElement signUp;
	
	@FindBy(className =RegistrationPageConstants.SIGNINWITHPHONE)
	public WebElement signIn;
	
	
	public void validateSignUp() {
		expectedError = "all fields are required";

		//click on signUp  button
		signUp.click();

		//Error encountered
		actualError = driver.findElement(By.xpath("//div[@class='formErrors formNameErrors']"))
				.getText();
		System.out.println("Actual Error : "+actualError+"\nExpected Error : "+expectedError);

		//validate actual error and expected Error
		Assert.assertEquals(expectedError,actualError);  
	}
	
	/*
	 * Verify if a user will be able to Sign Up with a valid username and invalid password.
	 */
	public void invalidCredentials() {
		username.clear();
		username.sendKeys("Shubhangi Goel");
		usermail.clear();
		usermail.sendKeys("shubhs21@gmail.com");
		password.clear();
		password.sendKeys("shubs1308");
		signUp.click();
		System.out.println("Actual Result is : "+driver.getCurrentUrl());
		System.out.println("Expected Result is : "+verifyOtpUrl);
		Assert.assertNotEquals(verifyOtpUrl,driver.getCurrentUrl());
	}
	
	/*
	 * Verify if a user will be able to Sign Up with a valid username and valid password.
	 */

	public void validateCredentials() throws InterruptedException {
		expectedResult="Enter OTP";
		Thread.sleep(5000);

		//Will Enter User details on SignUp Page
		String random = RandomStringUtils.randomAlphanumeric(10);
		username.clear();
		username.sendKeys("Shubhs Goel");
		usermail.clear();
		usermail.sendKeys(random+"@gmail.com");
		password.clear();
		password.sendKeys("Shub@13058");
		signUp.click();

		//wait for the verify otp page to load after successful submit of valid credentials
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.urlToBe(verifyOtpUrl));

		//find the text on verify OTP page
		actualResult = driver.findElement(By.xpath("//h2[text()='Enter OTP']")).getText();

		//validate actual text and expected Text
		Assert.assertEquals(actualResult, expectedResult);
		System.out.println("Actual Result is : "+actualResult);
		System.out.println("Expected Result is : "+expectedResult);

	}
	
	/*
	 * Verify if user can do onboard without OTP verification
	 */
	public void otpVerification() throws InterruptedException {
		validateCredentials();
		actualError = "Otp field must not be empty";
		signIn = driver.findElement(By.id("signinwithphone"));
		signIn.click();
		String expectedError = driver.findElement(By.className("otpErrorContainer")).getText();
		Assert.assertEquals(expectedError, actualError );
		System.out.println("Actual Error is : "+expectedError);
		System.out.println("Expected Error is : "+actualError);
		
	}
	
	/*
	 * Testing for Password Progress Bar
	 */

	public void passwordBar() throws InterruptedException {
		password.sendKeys("S");
		color = verifyColor(1);
		System.out.println(color);
		Thread.sleep(500);
		password.sendKeys("goel");
		color = verifyColor(2);
		System.out.println(color);
		Thread.sleep(500);
		password.sendKeys("@");
		color = verifyColor(3);
		System.out.println(color);
		Thread.sleep(500);
		password.sendKeys("1");
		color = verifyColor(4);
		System.out.println(color);
		Thread.sleep(500);
		password.sendKeys("2");
		color = verifyColor(5);
		System.out.println(color);
		Thread.sleep(500);
	}
	
	public String verifyColor(int i) {
		String basePath = "//div[@class='passStrength']";
		String completePath;
		completePath = basePath + "/div[" + i + "]";
		color =driver.findElement(By.xpath(completePath)).getCssValue("background-color");
		return color;
	}
	
	/*
	 * Alphanumeric Testing in the username Field test
	 */
	
	public void validateUsername() {

		expectedError = "Please Enter a valid username";
		usermail.clear();
		usermail.sendKeys("shubhsgoel@12");
		actualError = driver.findElement(By.xpath("//div[@class='formErrors formMailErrors']"))
				.getText();
		Assert.assertEquals(actualError,expectedError);
		System.out.println("Actual Error is : "+expectedError);
		System.out.println("Expected Error is : "+actualError);
	}
	
	
	
}
