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
import tome.pages.Login;
import tome.pages.ViewChar;

public class KillChar {
	
	private WebDriver driver;
	private Login login;
	private AccountPage account;
	private ViewChar viewChar;
	
	
	@Before
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        viewChar = PageFactory.initElements(driver, ViewChar.class);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(this.login.getUrl());
        this.login.getuNameLogin().sendKeys("CucSand");
        this.login.getPwLogin().sendKeys("changetest");
        this.login.getLoginbutton().click();
        this.account.getViewChar().click();
        Thread.sleep(3000);
	}
	
	@Test
	public void killChar() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		this.viewChar.getKillButton().click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		assertEquals("You do not have an active character at present.",this.account.getCharName().getText());
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
