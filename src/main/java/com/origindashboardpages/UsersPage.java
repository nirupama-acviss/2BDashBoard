package com.origindashboardpages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.reusableComponents.CommonMethods;
import com.testBase.TestBase;

public class UsersPage extends TestBase {
	@FindBy(xpath="//li[normalize-space()='Users']")
	WebElement users;
	@FindBy(xpath="//button[normalize-space()='Create a New User']")
	WebElement createnewuser;
	@FindBy(xpath="//input[@ id='firstName']")
	WebElement firstname;
	@FindBy(xpath="//input[@ id='lastName']")
	WebElement lastname;
	@FindBy(xpath="//input[@ id='email']")
	WebElement emailaddress;
	@FindBy(xpath="//input[@ id='mobile']")
	WebElement mobilenumber;
	@FindBy(xpath="//div[@ id='organization']")
	WebElement organization;
	@FindBy(xpath="//li[@role='option' ]")
	List<WebElement>organizationlist;
	@FindBy(xpath="//div[@id='role']")
	WebElement role;
	@FindBy(xpath="//li[@ role='option' ]")
	List<WebElement>rolelist;
	@FindBy(xpath="//div[@id='company']")
	WebElement company;
	@FindBy(xpath="//li[@ role='option' ]")
	List<WebElement>companylist;
	@FindBy(xpath="//div[@id='warehouse']")
	WebElement warehouse;
	@FindBy(xpath="//li[@ role='option']")
	List<WebElement>warehouselist;
	@FindBy(xpath="//div[@id='location']")
	WebElement location;
	@FindBy(xpath="//li[@role='option']")
	List<WebElement>locationlist;
	@FindBy(xpath="//button[@ class='button-save']")
	WebElement savebtn;
	
	
	public 	UsersPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void creaeNewUser(HashMap<String,String> testdata) throws InterruptedException {
		CommonMethods.click(users);
		CommonMethods.click(createnewuser);
		CommonMethods.sendKeysToElement(firstname, testdata.get("firstName"));
		CommonMethods.sendKeysToElement(lastname, testdata.get("lastName"));
		CommonMethods.sendKeysToElement(emailaddress, testdata.get("email"));
		CommonMethods.sendKeysToElement(mobilenumber, testdata.get("mobileNumber"));
		CommonMethods.click(organization);
		CommonMethods.dynamicDropDown(organizationlist, testdata.get("organization"));
		CommonMethods.click(role);
		CommonMethods.dynamicDropDown(rolelist, testdata.get("role"));
		CommonMethods.click(company);
		CommonMethods.dynamicDropDown(companylist, testdata.get("company"));
		CommonMethods.click(warehouse);
		CommonMethods.dynamicDropDown(warehouselist, testdata.get("Warehouse"));
		CommonMethods.click(location);
		CommonMethods.dynamicDropDown(locationlist, testdata.get("location"));
		Thread.sleep(2000);
		CommonMethods.scrollDown(driver);
		CommonMethods.click(savebtn);
		
}
	
	 public String getToasterMessage() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement toastMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class, 'go946087465')]")));
	        return toastMessageElement.getText();
	    }
}
