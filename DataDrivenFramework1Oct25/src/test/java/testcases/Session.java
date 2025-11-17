package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import keywords.ApplicationKeywords;
import testbase.BaseTest;

public class Session extends BaseTest {
	
	@Test
	public void doLogin(ITestContext context) 
	{
		System.out.println("In doLogin");
		app.openExistingBrowser("Chrome");
		app.navigate("https://app.snov.io/");
	}

}
