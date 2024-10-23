package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		//Boolean match = cartPage.verifyProductDisplay(productName);
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));	
		System.out.println("productN"+(input.get("product")));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();		
		String ConfMessage = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(ConfMessage, "THANKYOU FOR THE ORDER.");

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("manikantajk@gmail.com","Pass@word1");
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}
	

		//Extent Reports - 
	@DataProvider
	public Object[][] getData() throws IOException{
				
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	/*@DataProvider
	public Object[][] getData(){
		
		return new Object[][] {{"manikantajk@gmail.com","Pass@word1","ZARA COAT 3"},{"trishika@gmail.com","IamKing@000","ZARA COAT 3"}};
		
	}*/
	
	
	/*
	 * HashMap<String,String> map = new HashMap<String,String>(); map.put("email",
	 * "manikantajk@gmail.com"); map.put("password", "Pass@word1");
	 * map.put("Product", "ZARA COAT 3");
	 * 
	 * HashMap<String,String> map1 = new HashMap<String,String>(); map1.put("email",
	 * "trishika@gmail.com"); map1.put("password", "IamKing@000");
	 * map1.put("Product", "ADIDAS ORIGINAL");
	 */

}
