package F10StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import ShettyOrganization.pageObjects1.CheckOutPage;
import ShettyOrganization.pageObjects1.F2LandingPage;
import ShettyOrganization.pageObjects1.F4ProductCatalogue;
import ShettyOrganization.pageObjects1.F5CartPage;
import ShettyOrganization.pageObjects1.confirmationPage;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{

	public F2LandingPage landingPage;
	public F4ProductCatalogue productCatalogue;
	public F5CartPage cartPage;
	public confirmationPage confirmPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApplication(username,password);
	}
	
	 @When("^I add product (.+) to Cart$")
	 public void  i_add_product_to_Cart(String productName) throws InterruptedException, Throwable
	 {
		 List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void Checkout_productName_and_submit_the_order(String productName) throws InterruptedException
	 {
		 cartPage = productCatalogue.goToCartPage();
		 boolean itemMatched = cartPage.verifyProductDisplay(productName);
		 Assert.assertTrue(itemMatched);
		 CheckOutPage checkoutPage = cartPage.goToCheckout();
		 checkoutPage.selectCountry("india");
		 confirmPage = checkoutPage.submitOrder();
	 }
	 
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_on_ConfirmationPage(String string)
	 {
		String confirmMessage = confirmPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	 }
	 
	 @Then("{string} message is displayed")
	 public void incorrect_message_displayed(String string)
	 {
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 driver.close();
	 }
	 
}
