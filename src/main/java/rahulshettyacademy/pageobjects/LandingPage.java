package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	//constructor creation as shown below after creating an object in the other class (standAloneTest)
	//this keyword is used to point/refer to the locally created variable driver with the main webdriver from the other class ()standAloneTest)
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//other approach
	//WebElement email  = driver.findElement(By.id("userEmail"));
	
	//Page factory approach
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement error;
	
	public ProductCatalog login(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalog pc = new ProductCatalog(driver);
		return pc;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(error);
		return error.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
		//driver.get("https://google.com");
	}


}
