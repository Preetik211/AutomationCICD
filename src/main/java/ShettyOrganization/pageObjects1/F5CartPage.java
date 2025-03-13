package ShettyOrganization.pageObjects1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class F5CartPage extends AbstractComponent {

	WebDriver driver;
	
	public F5CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> cartProd = driver.findElements(By.cssSelector(".cartSection h3"));

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;
	
	// just matching prod is available in cart or not
	// boolean itemMatched = cartProd.stream().anyMatch(cardProduct->cardProduct.getText().equalsIgnoreCase(prodName));

	public boolean verifyProductDisplay(String productName)
	{
		boolean itemMatched = productTitles.stream().anyMatch(cardProduct->cardProduct.getText().equalsIgnoreCase(productName));
		return itemMatched;
	}
	
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;
	
	public CheckOutPage goToCheckout()
	{
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
	
}
