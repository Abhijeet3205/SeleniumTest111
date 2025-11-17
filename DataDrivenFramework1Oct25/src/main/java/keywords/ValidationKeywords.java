package keywords;

public class ValidationKeywords extends GenericKeywords {
	public void validationTitle() 
	{
		
	}
	public boolean validateText(String expectedTextLocatorKey, String locatorKey) 
	{
		String actualText = getText(locatorKey);
		String expectedText = envProp.getProperty(expectedTextLocatorKey);
		if(expectedText.equals(actualText)) 
		{
			return true;
		}
		return false;
	}
	public boolean validateElementPresent(String locator) 
	{
		boolean result = false;
		result = isElementPresent(locator);
		return result;
	}
}
