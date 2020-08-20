package tome.tests;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import tome.pages.AccountPage;
import tome.pages.CharCreater;
import tome.pages.Login;

public class CharCreate {
	
	private WebDriver driver;
	private Login login;
	private AccountPage account;
	private CharCreater charCreate;
	
	@Before
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        charCreate = PageFactory.initElements(driver, CharCreater.class);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(this.login.getUrl());
        this.login.getuNameLogin().sendKeys("CucSand");
        this.login.getPwLogin().sendKeys("changetest");
        this.login.getLoginbutton().click();
	    
	}
	
	@Test
	public void createChar() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		Thread.sleep(1500);
		this.account.getCreateButton().click();
		this.charCreate.getCharNamein().sendKeys("Bohan the Barbarian");
//		this.charCreate.getRaceDrop().selectByValue("Tulani");
//		this.charCreate.getOriginDrop().selectByValue("Baran");
		this.charCreate.getBgBox().sendKeys("I used to be a developer like you, then I took an error to the knee");
		Thread.sleep(2500);
		this.charCreate.getSubButton().click();
		Thread.sleep(2000);
		assertEquals("Bohan the Barbarian",this.account.getCharName().getText());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
