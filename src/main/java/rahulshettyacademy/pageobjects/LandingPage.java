package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver; 

	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
		//PageFactory

	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement passWordEle;
	
	@FindBy(id="login")
	WebElement submitBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;


	public ProductCatalogue loginApplication(String username, String password) {
		userName.sendKeys(username);
		passWordEle.sendKeys(password);
		submitBtn.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		}
	
}
