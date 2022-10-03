package com.idream.iprepLearning.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.idream.iprepLearning.pageConstants.DashBoardPageConstants;

public class DashBoardPage {
	
	WebDriver driver;
	String dashBoardURL = "https://learn.iprep.in/Dashboard";
	
	public DashBoardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath=DashBoardPageConstants.BOARD)
	public WebElement board;
	
	@FindBy(xpath=DashBoardPageConstants.SELECT_BOARD)
	public WebElement selectBoard;
	
	@FindBy(xpath=DashBoardPageConstants.SELECT_LANG)
	public WebElement language;

	@FindBy(xpath=DashBoardPageConstants.CONTINUE)
	public WebElement cont;
	
	@FindBy(xpath=DashBoardPageConstants.ED_BOARD)
	public WebElement edBoard;
	
	@FindBy(xpath=DashBoardPageConstants.NEXT)
	public WebElement next;
	
	@FindBy(xpath=DashBoardPageConstants.CLASS)
	public WebElement selectClass;
	
	@FindBy(xpath=DashBoardPageConstants.NAME)
	public WebElement name;
	
	@FindBy(xpath=DashBoardPageConstants.MOB_NO)
	public WebElement mobileNo;
	
	@FindBy(id=DashBoardPageConstants.NEXT_ONE)
	public WebElement nextOne;
	
	@FindBy(xpath=DashBoardPageConstants.SUB)
	public WebElement selectStream;
	
	@FindBy(xpath=DashBoardPageConstants.LEARN_NOW)
	public WebElement learnNow;
	
	
	
	
	
	public void changeBoard() throws InterruptedException {
		
		board.click();
		System.out.println("clicked on Board");
		Thread.sleep(3000);
		Select select = new Select(driver.findElement(By.xpath("//select[contains(@class,'PopupSelect')]")));
		select.selectByValue("1");
		System.out.println(select.getFirstSelectedOption().getText());
		//selectBoard.sendKeys("CBSE");
		String boardText = select.getFirstSelectedOption().getText();
		
		Assert.assertEquals(boardText, "CBSE");

	}
	
	public void onBoardFlow() throws InterruptedException {
		//language.click();
		Thread.sleep(2000);
		cont.click();
		Thread.sleep(2000);
		//edBoard.click();
		next.click();
		Thread.sleep(2000);
		//name.sendKeys("Shubhangi13");
		mobileNo.sendKeys("9810674523");
		//selectClass.click();
		nextOne.click();
		Thread.sleep(2000);
		//selectStream.click();
		learnNow.click();
		Thread.sleep(2000);
		Assert.assertEquals(driver.getCurrentUrl(), dashBoardURL);
		
	}

}
