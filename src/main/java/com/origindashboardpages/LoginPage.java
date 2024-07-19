package com.origindashboardpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reusableComponents.CommonMethods;
import com.reusableComponents.PropertiesOperations;
import com.testBase.TestBase;

public class LoginPage extends TestBase {
	
	
	@FindBy(xpath="//input[@id='mobile']")
	WebElement MobileNumber;
	@FindBy(xpath="//input[@placeholder='PIN']")
	WebElement Pin;
	@FindBy(xpath="//button[@type='submit']")
	WebElement submit;
	@FindBy(xpath="//div[contains(text(),'Successfully logged in')]")
	WebElement successfulmsg;
	@FindBy(xpath="//div[contains(text(),'Phone number not registered')]")
	WebElement errormsg;
	
	


	// constructor - //Initializing the page  objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	//please help me nirupama

	public boolean login() throws Exception {
		CommonMethods.sendKeysToElement(MobileNumber,PropertiesOperations.getPropertyValueByKey("mobileno"));
		CommonMethods.sendKeysToElement(Pin,PropertiesOperations.getPropertyValueByKey("pin"));
		submit.click();
		Thread.sleep(3000);
		return successfulmsg.isDisplayed();
	}
	
public String loginPageTitle() {
	return driver.getTitle();
	
}
//login with wrong mobileno & wrong pin
public boolean InvalidLogin() throws Exception {
	CommonMethods.sendKeysToElement(MobileNumber,PropertiesOperations.getPropertyValueByKey("invalidmobileNo"));
	CommonMethods.sendKeysToElement(Pin,PropertiesOperations.getPropertyValueByKey("invalidpin"));
	submit.click();
	Thread.sleep(3000);
	return errormsg.isDisplayed();
}

/*public boolean InvalidLoginwithWrongPin() throws Exception {
	CommonMethods.sendKeysToElement(MobileNumber,PropertiesOperations.getPropertyValueByKey("mobileno"));
	CommonMethods.sendKeysToElement(Pin,PropertiesOperations.getPropertyValueByKey("invalidpin"));
	submit.click();
	Thread.sleep(3000);
	return errormsg.isDisplayed();
}*/
	
	

}
