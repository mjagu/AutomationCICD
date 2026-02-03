package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args){
		
		
		String productName = "ZARA COAT 3";
		String country = "India";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();


		
		//created this object to create a constructor in the other class which can use the driver object from here
                //Adding a comment to test cicd change (ngrok, jenkins etc)
		//LandingPage lp = new LandingPage(driver);
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("tonybash@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("P@ssword1");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		 driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='button']")));
		
		
		List<WebElement> countryList = driver.findElements(By.cssSelector("[type='button']"));
		//streams approach, but disadvantage is it may not handle null pointer exceptions.
		//WebElement c = countryList.stream().filter(cntry->cntry.getText().contentEquals(country)).findFirst().orElse(null);
		//c.click();
		
		for(int i =0; i<countryList.size(); i++) {
			String desiredCountry = countryList.get(i).getText();
			if(desiredCountry.contentEquals(country)) {
				countryList.get(i).click();
				break;
			}
		} 
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String orderId = driver.findElement(By.cssSelector("tr[class='ng-star-inserted']")).getText();
		System.out.println(orderId);
		String confirmedMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
		

	}

}
