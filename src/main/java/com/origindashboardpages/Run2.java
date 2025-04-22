package com.origindashboardpages;
import java.io.File;
import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Run2 {


	

		    public static void main(String[] args) throws InterruptedException {
		        WebDriverManager.chromedriver().setup();
		    	WebDriver  driver=new ChromeDriver();
		        driver.get("https://fmc-origin.acviss.co/login");
		        driver.manage().window().maximize();
		        Thread.sleep(2000);

		        // Login steps
		        driver.findElement(By.xpath("//input[@id='mobile']")).sendKeys("6372988763");
		        driver.findElement(By.xpath("//input[@placeholder='PIN']")).sendKeys("1234");
		        driver.findElement(By.xpath("//button[@type='submit']")).click();
		        Thread.sleep(5000);

		        // Export steps
		        driver.findElement(By.xpath("//button[normalize-space()='Export To Csv']")).click();
		        driver.findElement(By.xpath("//li[contains(text(),\"Select custom date\")]")).click();
		        driver.findElement(By.xpath("//span[normalize-space()='Previous Month']")).click();
		        driver.findElement(By.xpath("//button[@class='button-save']")).click();

		        // Download verification
		        String downloadPath = System.getProperty("user.home") + "\\Downloads"; 
		        File downloadedFile = waitForLatestFile(downloadPath, "csv", 20); // Wait for 20 seconds

		        if (downloadedFile != null && downloadedFile.exists()) {
		            System.out.println("File downloaded successfully: " + downloadedFile.getAbsolutePath());
		        } else {
		            System.out.println("File download failed as there is no data.");
		        }

		        driver.quit(); // Close the browser after execution
		    }

		    /**
		     * Waits for the latest file with the given extension to appear in the directory.
		     */
		    public static File waitForLatestFile(String directory, String extension, int timeoutSeconds) throws InterruptedException {
		        File latestFile = null;
		        int elapsedSeconds = 0;

		        while (elapsedSeconds < timeoutSeconds) {
		            latestFile = getLatestFile(directory, extension);
		            if (latestFile != null && latestFile.exists()) {
		                return latestFile; // Return the latest file if found
		            }
		            Thread.sleep(1000); // Wait 1 second before checking again
		            elapsedSeconds++;
		        }
		        return null; // Return null if no file is found after timeout
		    }

		    /**
		     * Retrieves the most recent file with the given extension from the directory.
		     */
		    public static File getLatestFile(String directory, String extension) {
		        File dir = new File(directory);
		        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith("." + extension));

		        if (files != null && files.length > 0) {
		            return Arrays.stream(files)
		                    .max(Comparator.comparingLong(File::lastModified))
		                    .orElse(null);
		        }
		        return null;
		    }
		


	}


