package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.TestComponents.Retry;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ErrorValidationTest extends BaseTest {
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String ProductName = "ZARA COAT 3";
		landingPage.loginApplication("manikantajk@gmail.com","Pass@@word1");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String ProductName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("manikantajk@gmail.com","Pass@word1");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(ProductName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		Boolean match = cartPage.verifyProductDisplay(ProductName);		
		Assert.assertFalse(match);
	}

}
