

package testpages;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.testBase.TestBase;

public class LoginPageTest extends TestBase{
	@Test(priority=1)
	public void login() throws Exception   {
		boolean msg=loginpage.login();
		Assert.assertTrue(msg);
	}
	
	@Test(priority=2,dependsOnMethods="login")
	public void validateLoginPageTitle() {
		String title=loginpage.loginPageTitle();
		Assert.assertEquals(title, "Login-Origin");
	}
	@Test
	public void invalidLogin() throws Exception {
		boolean errormsg=loginpage.InvalidLogin();
		Assert.assertTrue(errormsg);
	}
	/*@Test
	public void loginWithInvalidPin() throws Exception {
		boolean errormsg1=loginpage.InvalidLoginwithWrongPin();
		Assert.assertTrue(errormsg1);
	}*/
}
