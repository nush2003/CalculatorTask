import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CalculatorPagePOM {
	WebDriver driver;
	
	By num0 = By.id("Btn0");
	By num1 = By.id("Btn1");
	By num2 = By.id("Btn2");
	By num3 = By.id("Btn3");	
	By num5 = By.id("Btn5");	
	By num8 = By.id("Btn8");

	By minusBtn = By.id("BtnMinus");
	By plusBtn = By.id("BtnPlus");
	By calcBtn = By.id("BtnCalc");
	By multBtn = By.id("BtnMult");
	By paranLBtn = By.id("BtnParanL");
	By paranRBtn = By.id("BtnParanR");
	By sinBtn = By.id("BtnSin");
	By clearBtn = By.id("BtnClear");
	
	By historyBtn = By.xpath("//*[@id=\"hist\"]/button[2]");
	By historyList = By.xpath("//*[@id=\"histframe\"]/ul/li");		

	
	
	public CalculatorPagePOM(WebDriver driver) {		
		this.driver = driver;		
	}
	
	public void clickOnBtn(String btmStr) throws InterruptedException {
		By btm = null;
		switch(btmStr) {
			case "0":
			    btm = num0;
			    break;
			case "1":
			    btm = num1;
			    break;
			case "2":
			    btm = num2;
			    break;
			case "3":
			    btm = num3;
			    break;
			case "5":
			    btm = num5;
			    break;
			case "8":
			    btm = num8;
			    break;
			case "=":
			    btm = calcBtn;
			    break;
			case "*":
			    btm = multBtn;
			    break;
			case "(":
			    btm = paranLBtn;
			    break;
			case ")":
			    btm = paranRBtn;
			    break;
			case "-":
			    btm = minusBtn;
			    break;
			case "+":
			    btm = plusBtn;
			    break;
			case "sin":
			    btm = sinBtn;
			    break;
			case "clear":
			    btm = clearBtn;
			    break;
			   			 
		}

		driver.findElement(btm).click();
		Thread.sleep(100);
	}
	
	public int getHistoryListSize() {
		driver.findElement(historyBtn).click();
		int historyListSize = driver.findElements(historyList).size();
		return historyListSize;
	}
	
	

}
