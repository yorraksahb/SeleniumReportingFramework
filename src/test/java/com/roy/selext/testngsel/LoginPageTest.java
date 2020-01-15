package com.roy.selext.testngsel;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.roy.selext.testngsel.base.TestBase;
import com.roy.selext.testngsel.pages.LoginPage;

import junit.framework.Assert;

public class LoginPageTest extends TestBase {
	
	public static final Logger log = Logger.getLogger(LoginPageTest.class.getName());
	
	WebDriver driver;
	LoginPage loginPage;
	
	@BeforeClass
	@BeforeMethod
	public void setUp() {
		driver = getDriver();
	}
	
	@DataProvider(name = "login")
	public String[][] getTestData() {
		String[][] testRecords = getData("Login", "TestData.xlsx");
		return testRecords;
	}
	
	@Test(dataProvider= "login")
	public void loginTest(String run, String username, String password) {
		loginPage = new LoginPage(driver);
		Assert.assertEquals("Yahoo Mail", loginPage.getTitle());
		loginPage.clickSignIn();
	}

}
