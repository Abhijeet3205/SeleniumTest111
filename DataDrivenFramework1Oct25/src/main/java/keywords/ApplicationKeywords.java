package keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords {
	
	public ApplicationKeywords() 
	{
		String path = System.getProperty("user.dir")+"//src//test//resources//env.properties";
		prop = new Properties();
		envProp = new Properties();
		try 
		{
			FileInputStream fs = new  FileInputStream(path);
			prop.load(fs);
			String environment = prop.getProperty("env");
			path = System.getProperty("user.dir")+"//src//test//resources//"+environment+".properties";
			System.out.println("path is: "+path);
			fs = new FileInputStream(path);
			envProp.load(fs);
		}
		catch(Exception e)
		{
			System.out.println("error in handling propertie file: ");
			e.printStackTrace();
		}
		System.out.println(envProp.getProperty("url"));
		System.out.println(envProp.getProperty("username_xpath"));
		softAssert = new SoftAssert();
	}
	
	public void login() 
	{
		
	}
	public void selectDateFromCalendar() 
	{
		
	}
	public void setReport(ExtentTest test) 
	{
		this.test = test;
	}
}
