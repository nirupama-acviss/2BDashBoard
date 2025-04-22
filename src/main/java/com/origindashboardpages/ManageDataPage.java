package com.origindashboardpages;

import java.io.File;
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
	@FindBy(xpath="//*[@role='option']")
	List<WebElement> optionlist;
	@FindBy(xpath="//input[@placeholder='Invoice No']")
	WebElement invoiceNo;
	@FindBy(xpath="//div[contains(@class,'model-buttons d-flex')]/child::*")
	WebElement submitbutton;
	@FindBy(xpath="//p[contains(text(), 'Invoice Number')]/following-sibling::p")
	WebElement invoicedetails;
	@FindBy(xpath="//button[contains(text(), 'Generate Box Data')]")
	WebElement GenerateBoxData;
	@FindBy(xpath="(//div[@class='MuiFormControl-root MuiTextField-root css-14xf3ln' ])[1]")
	WebElement logtype;
	@FindBy(xpath="//*[@ role='listbox']")
	List<WebElement> logtypeList;
	@FindBy(xpath="//input[contains(@id,'numberOfBoxes')]")
	WebElement numberofboxes;
	@FindBy(xpath="(//div[@class='MuiFormControl-root MuiTextField-root css-14xf3ln' ])[3]")
	WebElement testingphone;
	@FindBy(xpath="//button[text()='Save']")
	WebElement save;
	
  
	public 	ManageDataPage() {
		PageFactory.initElements(driver, this);
	}
	
	/*public void addNewInvoice(HashMap<String,String> testdata) throws InterruptedException {

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
		

	}*/
	
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
	
	public void getInvoiceDetails(HashMap<String,String> testdata) throws InterruptedException   {
		CommonMethods.click(mamagedata);
		CommonMethods.click(selecttype);
		CommonMethods.dynamicDropDown(optionlist,testdata.get("InvoiceType"));
	CommonMethods.sendKeysToElement(invoiceNo, testdata.get("InvoiceNumber"));
	Thread.sleep(3000);
	CommonMethods.click(submitbutton);

	if(testdata.get("InvoiceNumber").equals(invoicedetails.getText())) 
		System.out.println(invoicedetails);
	}
	
	
	public void generateBoxData(HashMap<String,String> testdata) throws InterruptedException {
		CommonMethods.click(mamagedata);
		CommonMethods.click(logtype);
		CommonMethods.dynamicDropDown(logtypeList,testdata.get("Logtype"));
		CommonMethods.sendKeysToElement(numberofboxes, testdata.get("NoOfBoxes"));
		CommonMethods.click(testingphone);
		CommonMethods.click(save);
		  String downloadPath = System.getProperty("user.home") + "\\Downloads";
	        File downloadedFile =CommonMethods.waitForLatestFile(downloadPath, "pdf", 30); // wait up to 30s for a PDF file

	        if (downloadedFile != null && downloadedFile.exists()) {
	            System.out.println("✅ File downloaded: " + downloadedFile.getName());
	        } else {
	            System.out.println("❌ File not found in the Downloads folder.");
	        }
		
	}
	
	
	
	
	
	//fetching toaster msg
	  public String getToasterMessage() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement toastMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class, 'go946087465')]")));
	        return toastMessageElement.getText();
	    }	
	
}
