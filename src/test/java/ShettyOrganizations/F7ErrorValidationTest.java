package ShettyOrganizations;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ShettyOrganization.pageObjects1.CheckOutPage;
import ShettyOrganization.pageObjects1.F4ProductCatalogue;
import ShettyOrganization.pageObjects1.F5CartPage;
import ShettyOrganization.pageObjects1.confirmationPage;
import TestComponents.BaseTest;
import TestComponents.Retry;

public class F7ErrorValidationTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws Throwable {

		String prodName = "ZARA COAT 3";
		landingPage.loginApplication("keshri1197@gmail.com", "Selenium@123"); // user

		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws Throwable {

		String prodName = "ZARA COAT 3";
		F4ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000"); // user

		List<WebElement> products = productCatalogue.getProductList(); // product list
		productCatalogue.addProductToCart(prodName); // add to cart // spinner
		F5CartPage cartPage = productCatalogue.goToCartPage();

		boolean itemMatched = cartPage.verifyProductDisplay("ZARA COAT 33");

		Assert.assertFalse(itemMatched);

	}
}
