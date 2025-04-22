package com.reusableComponents;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.testBase.TestBase;

public class CommonMethods  extends TestBase{
	// common method to select dd value
	public void selectDropdownOption(WebElement element, String valueToBeSelected) throws Exception {
		Select os = new Select(element);
		try {
			os.selectByVisibleText(valueToBeSelected);
		} catch (Exception e) {
			throw new Exception("Value is not present in dropdown for WebElement: " + element
					+ "Value to be selected is: " + valueToBeSelected);
		}
	}

	public void selectRadioButtonValue(List<WebElement> element, String valueToBeSelected) {
		for (WebElement ref : element) {
			if (ref.getText().equalsIgnoreCase(valueToBeSelected)) {
				ref.click();
				break;
			}

		}
	}

	// alert message handling

	public void handleAlert() {

		TestBase.driver.switchTo().alert().accept();

	}

	public void editAlert() {
		TestBase.driver.switchTo().alert().sendKeys("150");

	}

	// selecting check boxes
	public void selectCheckBoxes(List<WebElement> element, String checks) {
		String[] checksArray = checks.split(",");
		for (String str : checksArray) { // speeding, Other
			for (WebElement ele : element) {
				if (ele.getText().equalsIgnoreCase(str)) {
					ele.click();
					break;
				}
			}
		}

	}

	// get dropdown options as list of string for compare
	public List<String> getDropDownOptionsAsList(WebElement element) {
		Select os = new Select(element);
		List<WebElement> list_webElement_model = os.getOptions();
		List<String> actualContents = new ArrayList<String>();

		for (WebElement ref : list_webElement_model) {
			actualContents.add(ref.getText());
		}
		return actualContents;
	}

	// Common Method to Upload a file using Robot Class
	public static void selectFileUpLoad(WebElement element, String filePath, WebDriver driver) throws Exception {
		try {
			// file path passed as parameter to StringSelection
			System.out.println(filePath);
			StringSelection s = new StringSelection(filePath);
			// Clipboard copy
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);

			// identify element and click
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].click();", element);

			// Robot object creation
			Robot r = new Robot();

			r.delay(250);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(150);
			r.keyRelease(KeyEvent.VK_ENTER);

		} catch (Exception e) {
			throw new Exception("File Path not found " + filePath);
		}
	}
	
	
		// Send text to the input elements
		public static void sendKeysToElement(WebElement element, String text) {
			element.clear();
			element.sendKeys(text);
		}
		
		// Click Method
		public static void click(WebElement ele) {
			ele.click();
		}
		
		
		//Click Method using javascript
		
		
		public static void clickUsingJS(WebElement e) {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click()",e);
		}
		
		// Div element dropdown
		public static void dynamicDropDown(List<WebElement> ele, String option) {
			for (WebElement e : ele) {
				String text = e.getText();
				if (text.equals(option)) {
					e.click();
					break;
				}
			}

		}
		
		 public static void scrollDown(WebDriver driver) {
			  
			 JavascriptExecutor js=(JavascriptExecutor)driver;
			   
			    js.executeScript("window.scrollBy(0, 300);");}


		// Safe parsing method to avoid NumberFormatException.
public static int parseSafe(WebElement element) {
    try {
        String text = element.getText().replaceAll("[^0-9]", "").trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    } catch (Exception e) {
        System.out.println("Error parsing number from element: " + element);
        return 0;
    }
}

/*public static File getLatestFile(String directory, String extension) {
    File dir = new File(directory);
    
    // Get the current system date and time formatted as required
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M_d_yyyy, h_m_s a");
    String currentDateTime = LocalDateTime.now().format(formatter);

    // Expected filename pattern: "Activity MM_DD_YYYY, HH_MM_SS AM/PM.csv"
    String expectedFileName = "Activity " + currentDateTime + "." + extension;

    File[] files = dir.listFiles((dir1, name) -> name.equalsIgnoreCase(expectedFileName));

    if (files != null && files.length > 0) {
        return files[0]; // Since we are matching with system time, only one file should match
    }
    return null;
}

*/
 //Waits for the latest file with the given extension to appear in the directory.
 
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

//Retrieves the most recent file with the given extension from the directory.
 
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

public static void refreshProject() {
    try {
        File projectDir = new File(System.getProperty("user.dir"));
        new ProcessBuilder("cmd.exe", "/c", "dir " + projectDir.getAbsolutePath()).start();
        System.out.println("Project refreshed successfully.");
    } catch (IOException e) {
        System.out.println("Failed to refresh project: " + e.getMessage());
    }
}
public static void moveFileToTestData(File file) {
    String projectRoot = System.getProperty("user.dir");
    String testDataPath = projectRoot + File.separator + "resources" + File.separator + "testData";
    File testDataDir = new File(testDataPath);

    if (!testDataDir.exists()) {
        testDataDir.mkdirs();
    }

    File destinationFile = new File(testDataDir, file.getName());

    try {
        Files.move(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved successfully to: " + destinationFile.getAbsolutePath());
    } catch (IOException e) {
        System.out.println("Failed to move file: " + e.getMessage());
    }
}




}

