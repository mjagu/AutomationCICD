package rahulshettyacademy.Tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test (groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
	
		lp.login("tonybash@gmail.com", "P@ss1");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());		

	}
	
	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog pc = lp.login("tonybash1@gmail.com", "P@ssword1");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCart();
		boolean match = cp.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	} 

}
