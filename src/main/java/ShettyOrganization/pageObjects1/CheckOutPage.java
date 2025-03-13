package ShettyOrganization.pageObjects1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class CheckOutPage extends AbstractComponent
{

	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// act.sendKeys(driver.findElement(By.xpath("//input[@placeholder=\"Select Country\"]")), "india").build().perform();
	
	@FindBy(xpath = "//input[@placeholder=\"Select Country\"]")
	WebElement country;
	
	// driver.findElement(By.cssSelector(".action__submit")).click();
	@FindBy(css = ".action__submit")
	private WebElement submit;
	
	// driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectcountry;
	
	By results = By.xpath("//section[@class=\"ta-results list-group ng-star-inserted\"]");
	
	public void selectCountry(String countryName) throws InterruptedException
	{
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		
		waitForElementToAppear(By.xpath("//section[@class=\"ta-results list-group ng-star-inserted\"]"));
		
		selectcountry.click(); 
		submit.click();	                        
	}
	
	public confirmationPage submitOrder()
	{
		submit.click();
		return new confirmationPage(driver);
	}
	
	
	
	
	
}
