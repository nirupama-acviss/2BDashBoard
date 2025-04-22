package testpages;


	
	
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;

import com.reusableComponents.ExcelOperations;

import java.util.HashMap;

	public class manageTest {

	    ExcelOperations excel = new ExcelOperations(
	            System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\AddNewInvoice.xlsx", "Sheet1");

	    @Test(dataProvider = "getData")
	    public void addNewInvoice(Object obj) throws Exception {
	        loginpage.login();
	        HashMap<String, String> testData = (HashMap<String, String>) obj;
	        managedatapage.addNewInvoice(testData);

	        // Assert toaster message content
	        String toasterMessage = managedatapage.getToasterMessage();
	        Assert.assertTrue(
	                toasterMessage.equals("Invoice number added successfully") ||
	                        toasterMessage.equals("Invoice Number already exist"),
	                "The message should be either a success or error message."
	        );
	    }

	    @Test(dataProvider = "getData")
	    public void verifyInvoiceDetails(Object obj) throws Exception {
	        loginpage.login();
	        HashMap<String, String> testData = (HashMap<String, String>) obj;
	        managedatapage.getInvoiceDetails(testData);

	        // Example assertion (customize to verify data as needed)
	        Assert.assertEquals(managedatapage.invoicedetails.getText(), testData.get("InvoiceNumber"),
	                "Invoice number does not match expected details.");
	    }

	    @DataProvider(name = "getData")
	    public Object[][] getTestData() throws Exception {
	        int rowCount = excel.getRowCount();
	        Object[][] obj = new Object[rowCount][1];
	        for (int i = 1; i <= rowCount; i++) {
	            HashMap<String, String> testData = excel.getTestDataInMap(i);
	            obj[i - 1][0] = testData;
	        }
	        return obj;
	    }
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
