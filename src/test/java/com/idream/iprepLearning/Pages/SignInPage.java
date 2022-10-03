package com.idream.iprepLearning.Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.idream.iprepLearning.pageConstants.SignInPageConstants;

public class SignInPage {
	
	WebDriver driver;
	String dashBoardURL = "https://learn.iprep.in/Dashboard";
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=SignInPageConstants.EMAIL_INPUT)
	public WebElement username;
	
	@FindBy(className =SignInPageConstants.PASSWORD_INPUT)
	public WebElement password;
	
	@FindBy(tagName =SignInPageConstants.LOGIN_BUTTON)
	public WebElement login;
	
	@FindBy(xpath =SignInPageConstants.REMEMBER_ME)
	public WebElement rememberMe;
	
/*
 * Verify if a user will be able to Sign Up with a valid username and valid password.
 */
	public void validSignUp() throws InterruptedException {
		username.sendKeys("Shubhangi13");
		password.sendKeys("Shubhs@1234");
		login.click();
		Thread.sleep(5000);
	    Assert.assertEquals(dashBoardURL, driver.getCurrentUrl());
		System.out.println("Actual URL is : "+driver.getCurrentUrl());
		System.out.println("Expected URL is : "+dashBoardURL);
	}

	/*
	 * Verify if a user will not be able to Sign Up with a invalid username and invalid password.
	 */
	
	public void invalidSignUp() throws InterruptedException {
		username.sendKeys("Shubhangi");
		password.sendKeys("Shubhs12");
		login.click();
		Thread.sleep(5000);
	    Assert.assertNotEquals(dashBoardURL, driver.getCurrentUrl());
		System.out.println("Actual URL is : "+driver.getCurrentUrl());
		System.out.println("Expected URL is : "+dashBoardURL);
	}
	
	/*
	 * Verify if a user will be able to Sign Up with a valid Gmail account.
	 */
	public void gmailSignUp() throws InterruptedException {
		username.sendKeys("shubhs1331@gmail.com");
		password.sendKeys("Shubhs@1234");
		login.click();
		Thread.sleep(5000);
	    Assert.assertEquals(dashBoardURL, driver.getCurrentUrl());
		System.out.println("Actual URL is : "+driver.getCurrentUrl());
		System.out.println("Expected URL is : "+dashBoardURL);
	}
	
	/*
	 * Checking for the Remember me option
	 */
	public void validateRememberMe() {
		Assert.assertEquals(true,rememberMe.isSelected());
	}
}
