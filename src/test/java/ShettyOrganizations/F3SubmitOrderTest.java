package ShettyOrganizations;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ShettyOrganization.pageObjects1.CheckOutPage;
import ShettyOrganization.pageObjects1.F2LandingPage;
import ShettyOrganization.pageObjects1.F4ProductCatalogue;
import ShettyOrganization.pageObjects1.F5CartPage;
import ShettyOrganization.pageObjects1.F8OrderPage;
import ShettyOrganization.pageObjects1.confirmationPage;
import TestComponents.BaseTest;

public class F3SubmitOrderTest extends BaseTest {

	String prodName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws Throwable 
	{

		F4ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password")); // user

		List<WebElement> products = productCatalogue.getProductList(); // product list
		productCatalogue.addProductToCart(input.get("product")); // add to cart // spinner
		F5CartPage cartPage = productCatalogue.goToCartPage();

		boolean itemMatched = cartPage.verifyProductDisplay(input.get("product"));

		Assert.assertTrue(itemMatched);

		CheckOutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		confirmationPage confirmPage = checkoutPage.submitOrder();
		String confirmMessage = confirmPage.getConfirmMessage();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	// to verify zara coat 3 is displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		// ZARA COAT 3
		F4ProductCatalogue productCatalogue = landingPage.loginApplication("keshri21197@gmail.com", "Selenium@123");
		F8OrderPage orderPage = productCatalogue.goToOrderPage();
		boolean orderMatched = orderPage.verifyOrderDisplay(prodName);
		Assert.assertTrue(orderMatched);

	}


	// 2 dimensional array :  3rd method using json file
	@DataProvider
	public Object[][] getData() throws IOException
	{
	
		List<HashMap<String,String>> data =  getJsonDataToHashMap("C:\\\\Users\\\\keshr\\\\eclipse-workspace\\\\E25\\\\SeleniumFramework\\\\src\\\\test\\\\java\\\\F9Data\\\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
	
	}
	
	
	// 2 dimensional array :  2nd method using hash map
/*	@DataProvider
	public Object[][] getData()
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "keshri21197@gmail.com");
		map.put("password", "Selenium@123");
		map.put("Product", "ZARA COAT 3");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "shetty@gmail.com");
		map1.put("password", "Iamking@000");
		map1.put("Product", "ADIDAS ORIGINAL");
		
		return new Object[][] {{map},{map1}};
	}
*/
	
	// 2 dimensional array : 1st method 
	
/*	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"keshri21197@gmail.com", "Selenium@123", "ZARA COAT 3"},{"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL"}};
	}
*/	

}
