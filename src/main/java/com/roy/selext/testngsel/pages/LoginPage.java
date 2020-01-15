package com.roy.selext.testngsel.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	WebDriver driver;
	public static final Logger log = Logger.getLogger(LoginPage.class.getName());
	
	@FindBy(css=".fuji-button-link.fuji-button-text.fuji-button-inverted")
	private WebElement bannerSignInBtn;
	
	@FindBy(id="login-username")
	private WebElement usernameInput;
	
	@FindBy(id="login-signin")
	private WebElement signInButton;
	
	@FindBy(id="createacc")
	private WebElement createAccountButton;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);	
	}

	public WebElement getUsernameInput() {
		return usernameInput;
	}

	public WebElement getSignInButton() {
		return signInButton;
	}

	public WebElement getCreateAccountButton() {
		return createAccountButton;
	}
	
	public String getTitle() {
		return this.driver.getTitle();
	}
	
	public void clickSignIn() {
		this.bannerSignInBtn.click();
	}
	
	

}
