package com.roy.selext.testngsel.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	public WebDriverEventListener eventListener;

	public static final Logger log= Logger.getLogger(TestListener.class.getName());

	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	/*
	 * public void onTestFailure(ITestResult result) {
	 * System.out.println("*** Test execution " + result.getMethod().getMethodName()
	 * + " failed..."); ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	 * }
	 */

	public void onTestFailure(ITestResult result) {
		log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
		log.info((result.getMethod().getMethodName() + " failed!"));
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
//		EventFiringWebDriver dr = new EventFiringWebDriver(driver);
		String targetLocation = null;

		String testClassName = (result.getInstanceName()).trim();
		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + formater.format(calendar.getTime()) + ".png";
		String fileSeperator = System.getProperty("file.separator");
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		log.info("Screen shots reports path - " + reportsPath);
		log.info("test class name -> " + testClassName);
		try {
			File file = new File(reportsPath + fileSeperator + testClassName); // Set ScreenShot Folder
			log.info("File creation "  + file);
			if (!file.exists()) {
				if (file.mkdirs()) {
					log.info("Directory: " + file.getAbsolutePath() + " is created!");
				} else {
					log.info("Failed to create directory: " + file.getAbsolutePath());
				}

			}

			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			log.info("1 " + screenshotFile);
			
			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define location
			log.info("2 " + targetLocation);
			
			File targetFile = new File(targetLocation);
			log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
			log.info("Target File location - " + targetFile.getAbsolutePath());
			
			FileHandler.copy(screenshotFile, targetFile);

		} catch (FileNotFoundException e) {
			log.info("File not found exception occurred while taking screenshot " + e.getMessage());
		} catch (Exception e) {
			log.info("An exception occurred while taking screenshot " + e.getCause());
		}

		// attach screenshots to report
		try {
			ExtentTestManager.getTest().fail("Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
		} catch (IOException e) {
			log.info("An exception occured while taking screenshot " + e.getCause());
		}
		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}