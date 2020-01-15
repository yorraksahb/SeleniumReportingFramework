package com.roy.selext.testngsel.base;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;

public class ExtentTestManager {
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	static ExtentReports extent = ExtentManager.getInstance();

	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public static synchronized void endTest() {
		extent.flush();
	}

	public static synchronized ExtentTest startTest(String testName) {
		ExtentTest test = extent.createTest(testName);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}
	
	public static synchronized void updateStepResult(String childNodeDesc, Object actual, Object expected) {
	    ExtentTest test = extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	    Test a = test.getModel();
	    ExtentTest cn = test.createNode(childNodeDesc);

	    try {
	        assertEquals(actual, expected);
	        cn.log(Status.PASS, "Pass");
	    } catch (AssertionError e) {
	        cn.log(Status.FAIL, "Fail");
	    }
	}
}