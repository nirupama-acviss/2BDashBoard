package com.testBase;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.origindashboardpages.LoginPage;
import com.origindashboardpages.ManageDataPage;
import com.origindashboardpages.UsersPage;
import com.reusableComponents.PropertiesOperations;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public LoginPage loginpage;
	public ManageDataPage managedatapage;
	public UsersPage userspage;
	
	


	public void LaunchBrowserAndNavigate() throws Exception {

	
		String browser = PropertiesOperations.getPropertyValueByKey("browser");

		String url = PropertiesOperations.getPropertyValueByKey("url");
		System.out.println(browser);
		/*if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless")*/
		 
	        /*options.addArguments("--headless");
	        options.addArguments("--disable-gpu");
	        options.addArguments("--window-size=1920,1080");*/

	//	if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options =new ChromeOptions();
			options .addArguments("headless");
			driver=new ChromeDriver(options);
		
	/*else if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			 driver = new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			 driver = new InternetExplorerDriver();
		}*/

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.get(url);
		driver.navigate().refresh();
		Thread.sleep(1000);
	}

	@BeforeMethod /// it will get execute before each test method within current class
	public void setupMethod() throws Exception {
		LaunchBrowserAndNavigate();
		loginpage=new LoginPage();
		managedatapage=new ManageDataPage();
		userspage=new UsersPage();
		
	}

	@AfterMethod
	public void cleanUp() {
		 driver.quit();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
   // protected static WebDriver driver;

/*    @BeforeClass
    public void setUp() {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowserAndNavigate() throws Exception {
    	

		String browser = PropertiesOperations.getPropertyValueByKey("browser");

		String url = PropertiesOperations.getPropertyValueByKey("url");
		System.out.println(browser);
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
     //   driver.navigate().to("url");  // Change URL as required
        driver.get(url);
        
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
	*/
	

}
