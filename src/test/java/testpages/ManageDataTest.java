package testpages;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.reusableComponents.ExcelOperations;
import com.testBase.TestBase;

public class ManageDataTest extends TestBase{

	//ExcelOperations excel=new ExcelOperations(
	//System.getProperty("user.dir") + "src\\test\\resources\\testData\\AddNewInvoice.xlsx)","Sheet1" );
ExcelOperations excel=new ExcelOperations("C:\\Users\\Nirupama Nayak\\eclipse-workspace\\Basics\\resources\\testData\\AddNewInvoice.xlsx","Sheet3");


/*@Test(dataProvider="getData")
public void addNewInvoice(Object obj) throws Exception {
loginpage.login();
Thread.sleep(4000);
HashMap<String ,String> testData=(HashMap<String,String>)obj;
managedatapage.addNewInvoice(testData);
Thread.sleep(2000);
System.out.println(	managedatapage.getToasterMessage());
Assert.assertTrue((managedatapage.getToasterMessage()).equals("Invoice number added successfully") || (managedatapage.getToasterMessage()).equals("Invoice Number already exist"),
  "The message should be either a success or error message.");
//managedatapage.verifyInvoiceDetails(testData);
}*/

@Test(dataProvider="getData")
public void addNewInvoice(Object obj) throws Exception {
loginpage.login();
Thread.sleep(4000);
HashMap<String ,String> testData=(HashMap<String,String>)obj;
managedatapage.addNewInvoice(testData);
Thread.sleep(2000);
System.out.println(	managedatapage.getToasterMessage());
Assert.assertTrue((managedatapage.getToasterMessage()).equals("Invoice number added successfully") || (managedatapage.getToasterMessage()).equals("Invoice Number already exist"),
  "The message should be either a success or error message.");
}

@Test(dataProvider="getData")
public void verifyInvoiceDetails(Object obj) throws Exception {
	loginpage.login();
	Thread.sleep(4000);
	HashMap<String ,String> testData=(HashMap<String,String>)obj;
	managedatapage.getInvoiceDetails(testData);
	Thread.sleep(2000);
}

public void generateBoxDatatest(Object obj) throws Exception {
	loginpage.login();
	HashMap<String ,String> testData=(HashMap<String,String>)obj;
	managedatapage.generateBoxData(testData);
}



@DataProvider(name="getData")
public Object[][] getTestData() throws Exception{
Object[][] obj=new Object[excel.getRowCount()][1];
for(int i=1;i<=excel.getRowCount();i++) {
	HashMap<String,String> testData=excel.getTestDataInMap(i);
	obj[i-1][0]=testData;
}
return obj;
}	
	
	
	
}
