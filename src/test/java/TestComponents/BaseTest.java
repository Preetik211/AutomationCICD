package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ShettyOrganization.pageObjects1.F2LandingPage;

public class BaseTest {

	public WebDriver driver;
	public F2LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException
	{
		
		// properties class
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\Resources3\\GlobalData.properties");
		pro.load(fis);
		
		// to get the browser value from maven terminal or from Global data using ternary operator
		// in terminal, need to write : 
		// mvn test -PPurchase -Dbrowser=firefox		// -P -> Which testng xml profile u want to choose
														// -D -> Parameter u want to use, either chrome, firsfox 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : pro.getProperty("browser");
//		pro.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			// headless mode
			ChromeOptions opt = new ChromeOptions();
			if(browserName.contains("headless"))
			{
			opt.addArguments("headless");
			}
			 driver = new ChromeDriver(opt);
			 driver.manage().window().setSize(new Dimension(1440,990));		// to run in full screen
		}
		
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver = new FirefoxDriver();
			
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			 driver = new EdgeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public F2LandingPage launchApplication() throws IOException
	{
		driver =  initializeDriver();
		landingPage = new F2LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) throws IOException
	{
		// read json to string 
		String jsonContent =  FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8); // encoding format to convert to string
		
		// string to hash map
	    // dependecy: jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		
		return data;
		
		// {map, map1}
		// and now finally we have list of hash maps
	}
	
	// take screenshot
	
		public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot)driver;
			File src =  ts.getScreenshotAs(OutputType.FILE);
			File file = new File("C:\\Users\\keshr\\eclipse-workspace\\E25\\SeleniumFramework\\" + testCaseName + ".png" );
			FileUtils.copyFile(src, file);
			return "C:\\Users\\keshr\\eclipse-workspace\\E25\\SeleniumFramework\\" + testCaseName + ".png";
		}
		
		// extent reports :- to rcv execution result of all our testcases
}
