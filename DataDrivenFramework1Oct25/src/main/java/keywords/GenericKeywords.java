package keywords;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class GenericKeywords {
	public WebDriver driver = null;
	public Properties prop;
	public Properties envProp;
	public ExtentTest test;
	public SoftAssert softAssert;
	
	public void openBrowser(String browserName) 
	{
		if(browserName.equals("Chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-notifications");
			options.addArguments("--ignore-certificate-errors");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("Firefox")) 
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("Edge")) 
		{
			driver = new EdgeDriver();
		}
	}
	public void openExistingBrowser(String browserName) 
	{
		test.log(Status.INFO, "Opening Existing Browser: "+browserName);
		if(browserName.equals("Chrome")) 
		{
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9090");
			driver = new ChromeDriver(options);
		}
		else if(browserName.equals("Firefox")) 
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equals("Edge")) 
		{
			driver = new EdgeDriver();
		}
		
	}
	public void navigate(String url) 
	{
		test.log(Status.INFO, "Opening URL: "+url);
		driver.get(url);
	}
	public void click(String locator) 
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.findElement(By.xpath(locator)).click();
		getElement(locator).click();
	}
	public void clearText(String locatorKey) 
	{
//		driver.findElement(By.xpath(locator)).clear();
//		getElement(locatorKey).clear();
		
		WebElement ele = getElement(locatorKey);
		ele.sendKeys(Keys.CONTROL + "a");
		ele.sendKeys(Keys.DELETE);
	}
	public void type(String locatorKey, String data) 
	{
//		driver.findElement(By.xpath(locator)).sendKeys(data);
		getElement(locatorKey).sendKeys(data);
	}
	public void select(String locatorKey, String data) 
	{
		getElement(locatorKey).sendKeys(data);
	}
	public String getText(String locatorKey) 
	{
		String text= getElement(locatorKey).getText();
		return text;
	}
	
	public WebElement getElement(String locatorKey) 
	{
		// check the presence
		// check the visibility
		if(!isElementPresent(locatorKey)) 
		{
			//report failure
		}
		if(!isElementVisible(locatorKey)) 
		{
			//report failure
		}
		WebElement e = driver.findElement(getLocator(locatorKey));
		
		
		return e;
	}
	
	public boolean isElementVisible(String locatorKey) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));			
		}
		catch(Exception e) 
		{
			return false;
		}
		return true;
	}
	
	public boolean isElementPresent(String locatorKey) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		try 
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));			
		}
		catch(Exception e) 
		{
			return false;
		}
		return true;
	}
	
	public By getLocator(String locatorKey) 
	{
		By by = null;
		
		if(locatorKey.endsWith("_xpath")) 
		{
			by = By.xpath(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_id")) 
		{
			by = By.id(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_name")) 
		{
			by = By.name(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_className")) 
		{
			by = By.className(envProp.getProperty(locatorKey));
		}
		else if(locatorKey.endsWith("_cssSelector")) 
		{
			by = By.cssSelector(envProp.getProperty(locatorKey));
		}
		
		return by;
	}
	
	public void reportFailure(String failureMsg, boolean stopOnFailure) 
	{
		System.out.println(failureMsg);
		test.log(Status.FAIL, failureMsg);
		softAssert.fail(failureMsg);
		if(stopOnFailure) 
		{
			assertAll();
		}
	}
	
	public void assertAll() 
	{
		softAssert.assertAll();
	}
	
	public void takeScreenShot() 
	{
		Date d = new Date();
		String screenshotFile=d.toString().replaceAll(":", "_").replaceAll(" ", "_")+".png";
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
			test.log(Status.INFO, "Screenshot: "+test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+ screenshotFile));
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void quit() 
	{
		driver.quit();
	}

	
}
