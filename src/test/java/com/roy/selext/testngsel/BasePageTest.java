package com.roy.selext.testngsel;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.roy.selext.testngsel.base.ExtentTestManager;
import com.roy.selext.testngsel.base.TestBase;
import com.roy.selext.testngsel.pages.BasePage;

public class BasePageTest extends TestBase {

	private WebDriver driver;

	public static final Logger log = Logger.getLogger(BasePageTest.class.getName());
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
	}

	@Test
	public void verifyHomePage() {
		System.out.println("Home page test...");
		log.info("------Base Page Test ------");
		BasePage basePage = new BasePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(), "Home page title doesn't match");
	}

	@Test
	public void baseTest1() {
		ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test1");
		System.out.println("Hey im in test1 test");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 1");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 2");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 3");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 4");
	}

	@Test
	public void baseTest2() throws InterruptedException {
		ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test2");
		System.out.println("Hey im in test2 test");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test2 1");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test2 2");
	}

	@Test
	public void baseTest3() {
		ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test3");
		System.out.println("Hey im in test3 test");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test3 1");
		ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test3 2");
	}

}