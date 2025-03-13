package ExtentReportPractice4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Demo1 {
	
	ExtentReports extent;
	
	@BeforeTest
	public void config()
	{
		// ExtentReport
		// ExtentSparkReport :  responsible to create a html file and do some configuration
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		// ExtentReport :  so this is our main class, it is responsible to drive all your reporting execution
		// 
		extent = new ExtentReports();
		extent.attachReporter(reporter);	// now main class will have knowledge of onject
		extent.setSystemInfo("Tester", "Rahul Shetty");
		
		
	}
	
	@Test
	public void initialDemo()
	{
		ExtentTest test = extent.createTest("Initial Demo");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
	//	test.fail("Result do not match");
		extent.flush();
	}
}
