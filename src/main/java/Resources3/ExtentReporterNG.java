package Resources3;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	

	public static ExtentReports getReportObject() {
		// ExtentReport
		// ExtentSparkReport : responsible to create a html file and do some configuration
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// ExtentReport : so this is our main class, it is responsible to drive all your
		// reporting execution
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); // now main class will have knowledge of onject
		extent.setSystemInfo("Tester", "Rahul Shetty");
		return extent;
		
		
	}
}
