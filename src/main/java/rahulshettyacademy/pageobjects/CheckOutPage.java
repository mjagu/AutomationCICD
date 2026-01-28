package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[placeholder='Select Country']")
	private WebElement selectCountry;

	private By buttonEle = By.cssSelector("[type='button']");

	@FindBy(css = "[type='button']")
	private List<WebElement> countryLst;

	@FindBy(xpath = "//a[text()='Place Order ']")
	private WebElement plceOrder;
	
	

	public void enterTheCountryName(String country) {
		selectCountry.sendKeys(country);
		waitForElementToAppear(buttonEle);
	}

	public void desiredCountrySelectionUponEnteringTheCountryName(String country) {

		for (int i = 0; i < countryLst.size(); i++) {
			String desiredCntry = countryLst.get(i).getText();
			if (desiredCntry.contentEquals(country)) {
				countryLst.get(i).click();
				break;
			}

		}
	}

	public ConfirmationPage placeOrder() {
		plceOrder.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
