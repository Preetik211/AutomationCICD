package ShettyOrganization.pageObjects1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class F8OrderPage extends AbstractComponent {

	WebDriver driver;
	
	public F8OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> cartProd = driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	// just matching prod is available in order or not
	// boolean itemMatched = cartProd.stream().anyMatch(cardProduct->cardProduct.getText().equalsIgnoreCase(prodName));

	public boolean verifyOrderDisplay(String productName)
	{
		boolean itemMatched = productNames.stream().anyMatch(OrderProduct->OrderProduct.getText().equalsIgnoreCase(productName));
		return itemMatched;
	}
	
	
}
