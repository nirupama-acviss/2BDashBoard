package com.origindashboardpages;

import java.io.File;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.reusableComponents.CommonMethods;
import com.testBase.TestBase;

public class HomePage extends TestBase {
	
	 String downloadPath = System.getProperty("user.home") + "\\Downloads";
	
	@FindBy(xpath="//li[@class='active']")
	WebElement home;
	@FindBy(xpath="//input[@ placeholder='Company']")
	WebElement company;
	@FindBy(xpath="//li[@ role='option']")
	List<WebElement> listOfCompanies;
	@FindBy(xpath="//input[@id=':r1:']")
	WebElement warehouse;
	@FindBy(xpath="//li[@ role='option']")
	List<WebElement>listOfWarehouses;
	@FindBy(xpath="//div[contains(@class,'boxes')]/div[contains(@class,'flex-column')]/p[1]")
	WebElement assignedBoxes;
	@FindBy(xpath="//div[contains(@class,'boxes')]/div[contains(@class,'flex-column')]/p[2]")
	WebElement unassignedBoxes;
	@FindBy(xpath="//div[contains(@class,'flex-column')]//p[@class='value']")
	WebElement NoOfBoxes;
	@FindBy(xpath="//div[contains(@class,'logs')]/div[contains(@class,'flex-column')]/p[1]")
	WebElement inWardLogs;
	@FindBy(xpath="//div[contains(@class,'logs')]/div[contains(@class,'flex-column')]/p[2]")
	WebElement outWardLogs;
	@FindBy(xpath="//div[contains(@class,'logs')]//p[@class='value']")
	WebElement noOfLogs;
	@FindBy(xpath="//div[contains(@class,'invoice')]/div[contains(@class,'flex-column')]/p[1]")
	WebElement invoices;
	@FindBy(xpath="//p[normalize-space()='No. of Invoices']")
	WebElement noOfInvoices;
	@FindBy(xpath="//button[normalize-space()='Export To Csv']")
	WebElement ExportToCsv;
	@FindBy(xpath="//li[@role='menuitem']")
	List<WebElement> listOfDataByDate;
	@FindBy(xpath="//li[contains(text(),\"Export today's data\")]")
	WebElement ExportTodaydata;
	@FindBy(xpath="//li[contains(text(),\"Export yesterday's data\")]")
	WebElement ExportYesterdaydata;
	@FindBy(xpath="//li[contains(text(),\"Select custom date\")]")
	WebElement ExportByCustomdata;	
	@FindBy(xpath="//span[normalize-space()='Previous Month']")
	WebElement previousmonth;
	@FindBy(xpath="//button[@class='button-save']")
	WebElement savebtn;
	@FindBy(xpath="//div[@id='notistack-snackbar']")
	WebElement errorMsg;
	
	
	public 	HomePage() {
		PageFactory.initElements(driver, this);
	}
	public boolean verifyNoOfBoxesInvoicesLogs() throws InterruptedException {
	    // Select company and warehouse
	    CommonMethods.click(company);
	    CommonMethods.dynamicDropDown(listOfCompanies, "Test Company");
	    CommonMethods.click(warehouse);
	    CommonMethods.dynamicDropDown(listOfWarehouses, "Bengaluru");
	    Thread.sleep(3000);

	    // Fetch values safely using parseSafe() to avoid NumberFormatException
	    int noOfAssignedBoxes = CommonMethods.parseSafe(assignedBoxes);
	    int noOfUnassignedBoxes = CommonMethods.parseSafe(unassignedBoxes);
	    int noOfTotalBoxe =CommonMethods.parseSafe(NoOfBoxes);
	    int expectedTotalBoxes = noOfAssignedBoxes + noOfUnassignedBoxes;

	    int noOfInwardLogs = CommonMethods.parseSafe(inWardLogs);
	    int noOfOutWardLogs = CommonMethods.parseSafe(outWardLogs);
	    int totalLogs = CommonMethods.parseSafe(noOfLogs);
	    int expectedTotalLogs = noOfInwardLogs + noOfOutWardLogs;

	    
		int	invoicesCount = CommonMethods.parseSafe(invoices);
		
	   // int noOfInvoicesCount=Integer.parseInt(noOfInvoices.getText().replaceAll("[^0-9]", ""));
	    int noOfInvoicesCount =CommonMethods.parseSafe(noOfInvoices);

	    // Debugging output
	    System.out.println("Expected Total Boxes: " + expectedTotalBoxes + ", Actual: " + noOfTotalBoxe);
	    System.out.println("Expected Total Logs: " + expectedTotalLogs + ", Actual: " + totalLogs);
	   // System.out.println("Expected Invoices: " + noOfInvoicesCount + ", Actual: " + invoicesCount);
	    System.out.println(invoicesCount);

	    // Return true only if all three conditions match
	    return (expectedTotalBoxes == noOfTotalBoxe) && 
	           (expectedTotalLogs == totalLogs);
	}

	
	public void verifyfile() throws InterruptedException {
		
		  CommonMethods.click(company);
		    CommonMethods.dynamicDropDown(listOfCompanies, "Test Company");
		    CommonMethods.click(warehouse);
		    CommonMethods.dynamicDropDown(listOfWarehouses, "Bengaluru");
		    Thread.sleep(3000);
		    CommonMethods.click(ExportToCsv); 
		    CommonMethods.click(ExportTodaydata); 
		   // CommonMethods.getLatestFile(downloadPath, "csv");
		    
		    
		    String downloadPath = System.getProperty("user.home") + "\\Downloads"; 
		    File downloadedFile = CommonMethods.getLatestFile(downloadPath, "csv");
		    if (downloadedFile != null && downloadedFile.exists()) {
		        System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
		    } else {
		        System.out.println("File download failed as there is no data.");
		    }
		
	}
	
	public void verifyfileByCustomDate() throws InterruptedException {
		
		  CommonMethods.click(company);
		    CommonMethods.dynamicDropDown(listOfCompanies, "Test Company");
		    CommonMethods.click(warehouse);
		    CommonMethods.dynamicDropDown(listOfWarehouses, "Bengaluru");
		    Thread.sleep(3000);
		    CommonMethods.click(ExportToCsv); 
		    CommonMethods.click(ExportByCustomdata); 
		    Thread.sleep(2000);
		    CommonMethods.click(previousmonth);
		    CommonMethods.click(savebtn);
		   
		    
		    // CommonMethods.getLatestFile(downloadPath, "csv");
		    String downloadPath = System.getProperty("user.home") + "\\Downloads"; 
	        File downloadedFile = CommonMethods.waitForLatestFile(downloadPath, "csv", 20); // Wait for 20 seconds
	       
	        if (downloadedFile != null && downloadedFile.exists()) {
	            System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
	            // Move file to dynamically determined resource folder
	           CommonMethods.moveFileToTestData(downloadedFile);
	           Thread.sleep(3000);
	           // Refresh project folder
	           CommonMethods.refreshProject();
	        } else {
	            System.out.println("File download failed as there is no data.");
	        }
	}
	}
	 

	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


