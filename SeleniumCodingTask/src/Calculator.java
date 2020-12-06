
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import static org.junit.Assert.assertEquals;

import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Calculator {
	static WebDriver driver;
	CalculatorPagePOM calcPage = new CalculatorPagePOM(driver);
	Logger log = new Logger();
	
	@Rule
    public TestWatcher watchman = new TestWatcher() {
        @Override
        protected void failed(Throwable e,Description d) {
        	Logger.log("FAILED: "+d.getClassName()+"."+d.getMethodName());        
        }

	    @Override
	    protected void succeeded(Description d) {
	    	Logger.log("PASSED: "+d.getClassName()+"."+d.getMethodName());
	       
    	}
    };
	
	@BeforeClass
	public static void initDriver() {
		
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Drivers\\chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		// Pass the argument 1 to allow and 2 to block
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://web2.0calc.com/");	
	}
		
	@Test 
	public void t1_verifyPlusAction() throws InterruptedException {	
	
		calcPage.clickOnBtn("2");
		calcPage.clickOnBtn("+");
		calcPage.clickOnBtn("3");
		calcPage.clickOnBtn("=");
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		WebElement inpElement = driver.findElement(By.id("input"));
		String value = (String) js.executeScript("return arguments[0].value", inpElement);	
		
		assertEquals("5", value);
		calcPage.clickOnBtn("clear");
	}
	
	@Test 
	public void t2_verifyMinusAction() throws InterruptedException {	
	
		calcPage.clickOnBtn("1");
		calcPage.clickOnBtn("0");
		calcPage.clickOnBtn("-");
		calcPage.clickOnBtn("2");
		calcPage.clickOnBtn("=");
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		WebElement inpElement = driver.findElement(By.id("input"));
		String value = (String) js.executeScript("return arguments[0].value", inpElement);			
		
		assertEquals("8", value);	
		calcPage.clickOnBtn("clear");		
	}
	
	@Test 
	public void t3_verifyMinusCalcAction() throws InterruptedException {	
	
		calcPage.clickOnBtn("(");
		calcPage.clickOnBtn("1");
		calcPage.clickOnBtn("0");
		calcPage.clickOnBtn("-");
		calcPage.clickOnBtn("2");
		calcPage.clickOnBtn(")");
		calcPage.clickOnBtn("*");
		calcPage.clickOnBtn("2");
		calcPage.clickOnBtn("=");
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		WebElement inpElement = driver.findElement(By.id("input"));
		String value = (String) js.executeScript("return arguments[0].value", inpElement);			
		
		Assert.assertNotSame("20", value);
		calcPage.clickOnBtn("clear");		
	}
	
	@Test 
	public void t4_verifySinAction() throws InterruptedException {	
	
		calcPage.clickOnBtn("sin");		
		calcPage.clickOnBtn("3");
		calcPage.clickOnBtn("0");
		calcPage.clickOnBtn(")");
		calcPage.clickOnBtn("=");
		Thread.sleep(1000);
		
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		WebElement inpElement = driver.findElement(By.id("input"));
		String value = (String) js.executeScript("return arguments[0].value", inpElement);			
		
		assertEquals("0.5", value);
		calcPage.clickOnBtn("clear");		
	}
	
	@Test	
	public void t5_checkHistory() throws InterruptedException {	
	
		assertEquals(4, calcPage.getHistoryListSize());			
	}
	
	
	
		
	
		
	@AfterClass
	public static void tearDown() {
		driver.quit();
	}
		
			
		
		
}	
		
		
		
		
	
	


