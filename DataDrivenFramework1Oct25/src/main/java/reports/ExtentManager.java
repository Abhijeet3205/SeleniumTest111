package reports;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	static ExtentReports reports;
	public static String screenshotFolderPath;
	public static ExtentReports getReports() 
	{
		if(reports == null) 
		{
			String path = System.getProperty("user.dir");
			Date d = new Date();
			String path1 = d.toString().replaceAll(":", "_").replaceAll(" ", "_");
			reports = new ExtentReports();
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path + "\\reports\\" + path1 +"\\");
			screenshotFolderPath = path + "\\reports\\" + path1 +"\\" +"\\screenshots";
			File f = new File(screenshotFolderPath);
			f.mkdirs();
			sparkReporter.config().setReportName("Testing of website");
			sparkReporter.config().setDocumentTitle("Automation Reports");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setEncoding("utf-8");
			reports.attachReporter(sparkReporter);
		}
		
		return reports;
	}

}
