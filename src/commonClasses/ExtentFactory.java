package commonClasses;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentFactory {
	
	public static ExtentReports getInstance(){
		
		ExtentReports extent;
		String path = "./reports/logintest.html";
		extent = new ExtentReports(path, false); //replace = true
		
		extent
		.addSystemInfo("Platform", "Android")
		.addSystemInfo("Selenium", "3.0");
		
		return extent;
		
	}

}
