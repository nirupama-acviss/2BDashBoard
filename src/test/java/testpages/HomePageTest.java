package testpages;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.origindashboardpages.HomePage;
import com.testBase.TestBase;

public class HomePageTest extends TestBase{

		   @Test
		    public void verifyNoOfBoxesInvoicesLogsTest() throws Exception {
		        loginpage.login();
		        Thread.sleep(3000);

		        // Call the method from the HomePage class that compares the values
		        boolean areBoxesCorrect = homepage.verifyNoOfBoxesInvoicesLogs();

		        // Assert that the boxes are correctly calculated and match the total
		        Assert.assertTrue(areBoxesCorrect, "The total number of boxes is incorrect!");
		    }
		  @Test
		   public void verifyFileTest() throws Exception {
			  loginpage.login();
			  homepage.verifyfile(); 
		   }
		  @Test
		public void verifyfileByCustomDateTest() throws Exception {
			 loginpage.login();
			  homepage.verifyfileByCustomDate(); 
		}
		
		
	}

	


