package tome.tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tome.pages.AccountPage;
import tome.pages.Login;

public class PasswordUpdate {
	
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
        this.login.getuNameLogin().sendKeys("CucSand");
        this.login.getPwLogin().sendKeys("thisisatest");
        this.login.getLoginbutton().click();
	    
	}
	
	@Test
	public void dchangePassword() throws Throwable {
		Thread.sleep(2000);
	    this.account.getChPwButton().click();
	    this.account.getoPwEnter().sendKeys("thisisatest");
	    this.account.getnPwEnter1().sendKeys("changetest");
	    this.account.getnPwEnter2().sendKeys("changetest");
	    Thread.sleep(2000);
	    this.account.getPwSubButton().click();
	    Thread.sleep(1000);
	    driver.switchTo().alert().accept();
	    
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
