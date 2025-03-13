package ShettyOrganization.pageObjects1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShettyOrganization.AbstractComponents2.AbstractComponent;

public class F4ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public F4ProductCatalogue(WebDriver driver) // constructor and // we initialize driver from standalone test class file
	{
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this); // this refers to the current class file
	}

	// page factory
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));	// below we can write in different way

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	// product list
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() 
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	// WebElement prod = products.stream().filter(product->
	//product.findElement(By.cssSelector("b")).getText().equals(prodName)).findFirst().orElse(null);
	
	public WebElement getProductByName(String productName) 
	{
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
	}
	
	// prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	
	
	public void addProductToCart(String productName) 
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
	
	
	
	
	
	
	
}
