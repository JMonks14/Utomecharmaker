package tome.tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tome.pages.AccountPage;
import tome.pages.Login;
import tome.pages.UTomeIndex;

import org.junit.After;
public class AccountCreate {
	
	private WebDriver driver;
	private UTomeIndex home;
	private Login login;
	private AccountPage account;
	
	@Before
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        home = PageFactory.initElements(driver, UTomeIndex.class);
        login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
        driver.get(this.home.getUrl());
	}

	@Test
	public void aRegisterAccount() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
		this.home.clickNavToggle();
	    this.home.clickAccountButton();
	    this.login.getfNameReg().sendKeys("Selenium");
	    this.login.getlNameReg().sendKeys("Gomez");
	    this.login.getuNameReg().sendKeys("SGomez");
	    this.login.getpWord1Reg().sendKeys("thisisatest");
	    this.login.getpWord2Reg().sendKeys("thisisatest");
	    Thread.sleep(2000);
	    this.login.clickReg();
//	    Thread.sleep(5000);
	    Thread.sleep(2000);
	    assertEquals("Account Information",this.account.getaInfoHead().getText());
	    assertEquals("Your Username: SGomez", this.account.getuNameDisp().getText());
	    
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

	
	
}