package ShettyOrganization.AbstractComponents2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ShettyOrganization.pageObjects1.F5CartPage;
import ShettyOrganization.pageObjects1.F8OrderPage;

// this is base parent class
public class AbstractComponent {
	WebDriver driver;
	

	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public void waitForElementToAppear(By findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement findby)
	{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public void waitForElementToDisappear(WebElement ele) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	//	Thread.sleep(3000);
	}
	
	// driver.findElement(By.xpath("//button[@routerlink=\"/dashboard/cart\"]")).click();
	
	@FindBy(xpath = "//button[@routerlink=\"/dashboard/cart\"]")
	WebElement cartHeader;
	
	public F5CartPage goToCartPage()
	{
		cartHeader.click();
		F5CartPage cartPage = new F5CartPage(driver);
		return cartPage;
	}
	
	// routerlink="/dashboard/myorders"
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement myOrderHeader;
	
	public F8OrderPage goToOrderPage()
	{
		myOrderHeader.click();
		F8OrderPage orderPage = new F8OrderPage(driver);
		return orderPage;
	}
}
