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

import org.junit.After;
public class AccountUpdate {
	
	private WebDriver driver;
	private Login login;
	private AccountPage account;
	
	@Before
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(this.login.getUrl());
        this.login.getuNameLogin().sendKeys("SGomez");
        this.login.getPwLogin().sendKeys("thisisatest");
        this.login.getLoginbutton().click();
	    
	}

	@Test
	public void updateName() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		Thread.sleep(2000);
	    this.account.getNameUpButon().click();
	    this.account.getfNameEnter().sendKeys("Cucumber");
	    this.account.getlNameEnter().sendKeys("Sandwich");
	    Thread.sleep(2000);
	    this.account.clickSubName();
	    Thread.sleep(2000);
	    assertEquals("Your name: Cucumber Sandwich",this.account.getMyNameDisp().getText());
	    Thread.sleep(2000);
	}

	
	@Test
	public void updateUsername() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		this.account.getuNameUpButton().click();
	    this.account.getuNameEnter().sendKeys("CucSand");
	    Thread.sleep(2000);
	    this.account.clickSubUName();
	    Thread.sleep(2000);
	    assertEquals("Your Username: CucSand", this.account.getuNameDisp().getText());
	}

	@After
	public void tearDown() {
		driver.quit();
	}
	
}
