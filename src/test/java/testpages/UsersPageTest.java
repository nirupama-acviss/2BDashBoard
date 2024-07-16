package testpages;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.reusableComponents.ExcelOperations;
import com.testBase.TestBase;

public class UsersPageTest extends TestBase {
	
	ExcelOperations excel=new ExcelOperations("C:\\Users\\Nirupama Nayak\\eclipse-workspace\\Testing\\resources\\testData\\AddNewInvoice.xlsx","Sheet2");
	@Test(dataProvider="getData")
	public void addNewInvoice(Object obj) throws Exception {
		loginpage.login();
		Thread.sleep(4000);
		HashMap<String ,String> testData=(HashMap<String,String>)obj;
		userspage.creaeNewUser(testData);
		Thread.sleep(2000);
	System.out.println(	userspage.getToasterMessage());
	
				
				
         
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
