# SeleniumReportingFramework
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This is a Maven based framework using Selenium, TestNG and Extent Report for automation of the Web Application. The user input is taken from the excel sheet. Each of the tests are annotated and once the tests runs it creates a html based Report using Extent Report jar. In the report, the Failed steps will have the screenshot as well. 
This framework follows the `Page Object Model` where each webpage's element are mentioned in one java file. 

More on Page Object Model can be found here -> [https://www.toolsqa.com/selenium-webdriver/page-object-model/](url)

## Pre-requisite
- The driver `.exe` files should be downloaded and kept in the `drivers` folder at the project level.
- The input is taken from the Excel Sheet, which is under `src/java/resource/data`.


## How to run
- Once the pre-requisite are met, run `mvn run test`
- This will look for the driver and launch the app to run the sample tests written. 

## How to use
- For each web page, write the page class in the package `com.roy.selext.testngsel.pages` under `src/main/java`
- The test cases will be written the package `com.roy.selext.testngsel` under `scr/test/java`
- In order to run the test, `testng.xml` needs to be updated 
```
<parameter name="browserType" value="chrome" /> <!-- The browser type goes here -->
<parameter name="appURL" value="http://mail.yahoo.com" /> <!-- The App URL goes here -->
```
```
<test name="Admin Tests"> <!-- Mention The TestSuite Name here -->
	<classes> <!-- The classes which needs to be run -->
		<class name="com.roy.selext.testngsel.LoginPageTest" />
	</classes>
</test>
```




