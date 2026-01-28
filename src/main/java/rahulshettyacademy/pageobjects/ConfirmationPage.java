package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(css="tr[class='ng-star-inserted']")
	WebElement printOrderId;
	
	@FindBy(css=".hero-primary")
	WebElement confirmedMessage;
	
	
	public void orderIdPrint() {
		String orderId = printOrderId.getText();
		System.out.println(orderId);	
	}
	
	public String getOrderConfirmedMessage() {
		
		return confirmedMessage.getText();
		
	}
	
	
	

}
