package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StepDefinitionImplementation extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog pc;
	public ConfirmationPage confirmPage;
	
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException {
		
		landingPage = launchApplication();
		
	}
	
	@Given("^Loggedin with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password) {
		
		pc = lp.login(username, password);
		
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		
		
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		
	}
	
	//we can use @When also below as this step is coming from when, depending upoon context we can use When also
	//or else we can just use @And as we came up in feature file
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cp = pc.goToCart();
		boolean match = cp.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cp.goToCheckOut();
		checkOutPage.enterTheCountryName("India");
		checkOutPage.desiredCountrySelectionUponEnteringTheCountryName("India");
		confirmPage = checkOutPage.placeOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_displayed_confirmationPage(String string) {
		
		confirmPage.orderIdPrint();
		String confirmMessage = confirmPage.getOrderConfirmedMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, lp.getErrorMessage());	
		driver.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
