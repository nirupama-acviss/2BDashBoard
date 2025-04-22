package com.origindashboardpages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.reusableComponents.CommonMethods;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Run1 {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver  driver=new ChromeDriver();
		driver.get("https://fmc-origin.acviss.co/login");
driver.manage().window().maximize();
Thread.sleep(2000);
driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("6372988763");
driver.findElement(By.xpath("//input[@placeholder='PIN']")).sendKeys("1234");
driver.findElement(By.xpath("//button[@type='submit']")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//input[@ placeholder='Company']")).click();
List<WebElement> companylist=driver.findElements(By.xpath("//li[@ role='option']"));
driver.findElement(By.xpath("//button[normalize-space()='Export To Csv']")).click();
driver.findElement(By.xpath("//li[contains(text(),\"Export today's data\")]")).click();


String downloadPath = System.getProperty("user.home") + "\\Downloads"; 
Thread.sleep(4000);

File downloadedFile = CommonMethods.getLatestFile(downloadPath, "csv");
if (downloadedFile != null && downloadedFile.exists()) {
    System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
    
    moveFileToTestData(downloadedFile);
    } else {
        System.out.println("File download failed as there is no data.");
    }
	}
	  public static void moveFileToTestData(File file) {
	        // Get the project root directory dynamically
	        String projectRoot = System.getProperty("user.dir");

	        // Construct relative path to resources/testData
	        String testDataPath = projectRoot + File.separator + "resources" + File.separator + "testData";
	        File testDataDir = new File(testDataPath);

	        // Ensure the testData folder exists
	        if (!testDataDir.exists()) {
	            testDataDir.mkdirs();
	        }

	        // Destination file path
	        File destinationFile = new File(testDataDir, file.getName());

	        try {
	            Files.move(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            System.out.println("File moved successfully to: " + destinationFile.getAbsolutePath());
	        } catch (IOException e) {
	            System.out.println("Failed to move file: " + e.getMessage());
	        }
	    }

}



 

	

	


	



	
	
	


