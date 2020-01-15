package com.roy.selext.testngsel.base;

import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.roy.selext.testngsel.ExcelReader;

public class TestBase {

	private WebDriver driver;
	ExcelReader excel;

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		default:
			log.info("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		log.info("Launching google chrome with new profile..");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	// Load all the data in the String[][] using the getDataFromSheet method of
	public String[][] getData(String sheetname, String excelname) {
		String path = System.getProperty("user.dir") + "/src/main/resources/data/" + excelname;
		excel = new ExcelReader(path);
		String[][] data = excel.getDataFromSheet(sheetname, excelname);
		return data;

	}

	// Wait for the visibility of the element for a certain amount of time
	public void waitforElement(long timeoutseconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Wait for the element to be refreshed after a time
	public void waitforElementToBeAvailable(long timeoutseconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
	}

	// Wait for the element to be clickable after a certain amount of time
	public void waitforElementToBeClickable(long timeoutseconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutseconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// Method retrieves all the windows open for a driver in a iterator
	public Iterator<String> getAllWindows() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itr = windows.iterator();
		return itr;
	};

	// Logging method so that the same log is added in logger as well as syso
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}