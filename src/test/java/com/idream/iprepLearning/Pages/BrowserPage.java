package com.idream.iprepLearning.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserPage {
	 WebDriver driver;
	 
	 //Launch Chrome Browser

	public WebDriver launchBrowser() {

		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	//close Browser
	public void closeBrowser() {
		driver.quit();
	}

}
