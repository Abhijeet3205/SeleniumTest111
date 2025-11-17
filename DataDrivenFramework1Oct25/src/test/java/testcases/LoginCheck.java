package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginCheck {
	
	@Test(dataProvider = "getData")
	public void login(String username, String password) 
	{
		System.out.println(username + " - " + password);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[3][2];
		
		data[0][0]="U1";
		data[0][1]="p1";
		
		data[1][0]="U2";
		data[1][1]="p2";
		
		data[2][0]="U3";
		data[2][1]="p3";
		
		return data;
	}
	
	@Test
	public void verifyLogin() 
	{
		System.out.println("verifying login");
	}

}
