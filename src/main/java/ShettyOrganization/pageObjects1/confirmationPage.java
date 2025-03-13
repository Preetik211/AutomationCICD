package ShettyOrganization.pageObjects1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class confirmationPage extends AbstractComponent
{
WebDriver driver;
	
	public confirmationPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	// String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

	@FindBy(css = ".hero-primary")
	WebElement confirmMessage;
	
	//	Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	public String getConfirmMessage()
	{
		return confirmMessage.getText();
	}
	
}