package ShettyOrganization.pageObjects1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class F2LandingPage extends AbstractComponent{

	WebDriver driver;

	public F2LandingPage(WebDriver driver) // constructor and // we initialize driver from standalone test class file
	{
		super(driver);
		// initialization
		this.driver = driver;

		PageFactory.initElements(driver, this); // this refers to the current class file
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));	// below we can write in different way

	// page factory

	@FindBy(id = "userEmail") // page factory means : what attribute you want to find , i want to find id here
	WebElement userEmail;

//	driver.findElement(By.id("userPassword"))

	@FindBy(id = "userPassword")
	WebElement userPass;

//	driver.findElement(By.id("login"));

	@FindBy(id = "login")
	WebElement login;
	
//	class="ng-tns-c4-3 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error"
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	
	// action methods 
	
	public F4ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPass.sendKeys(password);
		login.click();
		F4ProductCatalogue  productCatalogue = new F4ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

	
}
