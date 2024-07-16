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

public class ManageDataPage extends TestBase {
	
	
	
	@FindBy(xpath="//li[normalize-space()='Manage Data']")
	WebElement mamagedata;
	@FindBy(xpath="//button[normalize-space()='ADD NEW INVOICE']")
	WebElement addNewInvoice;
	@FindBy(xpath="//input[@id='invoiceNumber']")
	WebElement invoiceNumber;
	@FindBy(xpath="//input[@id='fromEntity']")
	WebElement fromEntity;
	@FindBy(xpath="//input[@id='toEntity']")
	WebElement toEntity;
	@FindBy(xpath="//div[@id='location']")
	WebElement warehouse;
	@FindBy(xpath="//*[@role='option']")
	List<WebElement>warehouselist;
	@FindBy(xpath="//input[@id='batchNo']")
	WebElement batchNo;
	@FindBy(xpath="//input[@id='noOfBoxes']")
	WebElement noOfBoxes;
	@FindBy(xpath="//button[@class=\"button-save\"][1]")
	WebElement submit;
	@FindBy(xpath="//div[@role='combobox']")
	WebElement selecttype;
	
  
	public 	ManageDataPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void addNewInvoice(HashMap<String,String> testdata) {

		CommonMethods.click(mamagedata);
		CommonMethods.click(addNewInvoice);
		CommonMethods.sendKeysToElement(invoiceNumber, testdata.get("InvoiceNumber"));
		CommonMethods.sendKeysToElement(fromEntity, testdata.get("FromEntity"));
		CommonMethods.sendKeysToElement(toEntity, testdata.get("ToEntity"));
		CommonMethods.click(warehouse);
		CommonMethods.dynamicDropDown(warehouselist,testdata.get("WareHouse"));
		CommonMethods.sendKeysToElement(batchNo, testdata.get("BatchNo"));
		CommonMethods.sendKeysToElement(noOfBoxes, testdata.get("NoOfBoxes"));
		CommonMethods.click(submit);
		

	}
	
	//fetching toaster msg
	  public String getToasterMessage() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement toastMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class, 'go946087465')]")));
	        return toastMessageElement.getText();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
