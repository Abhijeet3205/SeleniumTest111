package testcases;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import keywords.ApplicationKeywords;
import testbase.BaseTest;

public class LeadManagement extends BaseTest {
	
	
	@Test
	public void createLead(ITestContext context) throws InterruptedException 
	{
		System.out.println("In create Lead");	
		Thread.sleep(3000);
		app.click("username_xpath");
		app.clearText("username_xpath");
		app.type("username_xpath", "spektumpms@gmail.com");
//		Thread.sleep(3000);
		app.click("password_xpath");
		app.clearText("password_xpath");
		app.type("password_xpath", "Pass@1234");
		//1
		app.reportFailure("some critical failure1", false);
		//2
		app.reportFailure("some critical failure2", false);
		//3
		app.reportFailure("some non critical failure3",true);
		app.assertAll();
	}
	
	@Test
	public void verifyLead(ITestContext context) throws InterruptedException 
	{
		System.out.println("In verify Lead");
	}
	
	@Test
	public void daleteLead(ITestContext context) throws InterruptedException 
	{
		System.out.println("In delete Lead");
	}

}
