package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider= "getData", groups = {"purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
	
		//instead of creating a product catalog object here in the test case class like we did for lp above, we are doing the below approach
		//where we create the object
		//in the login method itself and then returning it, so that no need to create here, go check the login method
		//product catalog page creation inside
		ProductCatalog pc = lp.login(input.get("email"), input.get("password"));
		
		
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));

		//Thread.sleep(2000);
		//same thing we did for cart page also
		//instead of creating a cart page object here in this test case class, we are doing the below approach where we create the object
	    //in the login method itself and then returning it, so that no need to create here, go check the login method
		//cart page
		CartPage cp = pc.goToCart();
		boolean match = cp.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		
		//checkout page object creation inside
		CheckOutPage checkOutPage = cp.goToCheckOut();
		checkOutPage.enterTheCountryName(country);
		checkOutPage.desiredCountrySelectionUponEnteringTheCountryName(country);
		
		//confirmation page object creation inside
		ConfirmationPage confirmPage = checkOutPage.placeOrder();
		confirmPage.orderIdPrint();
		String confirmMessage = confirmPage.getOrderConfirmedMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
	
	@Test (dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalog pc = lp.login("tonybash@gmail.com", "P@ssword1");
		OrderPage oPage = pc.goToOrdersPage();
		Assert.assertTrue(oPage.verifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
			
	}
	
	//method 1 to use data provider using HashMap	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "tonybash@gmail.com");
//	map.put("password", "P@ssword1");
//	map.put("product", "ZARA COAT 3");
//HashMap<String, String> map1= new HashMap<String, String>();
//	map1.put("email", "tonybash1@gmail.com");
//	map1.put("password", "P@ssword1");
//	map1.put("product", "ADIDAS ORIGINAL");
	
	//method 2 to use dataProvider - basic approach where we have to pass String email, String password in the test method
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"tonybash@gmail.com","P@ssword1","ZARA COAT 3"},{"tonybash1@gmail.com","P@ssword1","ADIDAS ORIGINAL"}};
	} */

}
