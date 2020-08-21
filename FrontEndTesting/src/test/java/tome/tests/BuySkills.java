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
import tome.pages.SpendXPpage;
import tome.pages.ViewChar;

public class BuySkills {
	
	private WebDriver driver;
	private Login login;
	private AccountPage account;
	private ViewChar viewChar;
	private SpendXPpage buyPage;
	
	@Before
	public void setup() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        viewChar = PageFactory.initElements(driver, ViewChar.class);
        buyPage = PageFactory.initElements(driver, SpendXPpage.class);
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(this.login.getUrl());
        this.login.getuNameLogin().sendKeys("CucSand");
        this.login.getPwLogin().sendKeys("changetest");
        this.login.getLoginbutton().click();
        this.account.getViewChar().click();
        
	}
	
	@Test
	public void buyskill1() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		this.viewChar.getBuySkillButton().click();
		Thread.sleep(3000);
		this.buyPage.getChooseTree().click();
		Thread.sleep(1000);
		this.buyPage.getBodyDrop().click();
		Thread.sleep(1000);
		this.buyPage.getChooseSkill().click();
		Thread.sleep(1000);
		this.buyPage.getIntenseTrain().click();
		Thread.sleep(1000);
		this.buyPage.getConfirm().click();
		assertEquals("Intense Training", this.viewChar.getSkill1().getText());
		
	}
	
	@Test
	public void buyskill2() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		this.viewChar.getBuySkillButton().click();
		Thread.sleep(1000);
		this.buyPage.getChooseTree().click();
		Thread.sleep(1000);
		this.buyPage.getBodyDrop().click();
		Thread.sleep(1000);
		this.buyPage.getChooseSkill().click();
		Thread.sleep(1000);
		this.buyPage.getExtremeTrain().click();
		Thread.sleep(1000);
		this.buyPage.getConfirm().click();
	}
	
//	@Test
//	public void buyskill3() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//		this.viewChar.getBuySkillButton().click();
//		Thread.sleep(1500);
//		this.buyPage.getChooseTree().click();
//		Thread.sleep(1000);
//		this.buyPage.getMagicDrop().click();
//		Thread.sleep(1000);
//		this.buyPage.getCantrips().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseSpell().click();
//		Thread.sleep(1000);
//		this.buyPage.getFling().click();
//		Thread.sleep(1000);
//		this.buyPage.getConfirm().click();
//		Thread.sleep(1000);
//		assertEquals("Fling", this.viewChar.getSpell1().getText());
//	}
//	
//	@Test
//	public void buyskill4() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//		this.viewChar.getBuySkillButton().click();
//		Thread.sleep(1500);
//		this.buyPage.getChooseTree().click();
//		Thread.sleep(1000);
//		this.buyPage.getMagicDrop().click();
//		Thread.sleep(1000);
//		this.buyPage.getBroadSpirit().click();
//		Thread.sleep(1000);
//		this.buyPage.getConfirm().click();
//	
//	}
//	
//	@Test
//	public void buyskill5() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//		this.viewChar.getBuySkillButton().click();
//		this.buyPage.getChooseTree().click();
//		Thread.sleep(1000);
//		this.buyPage.getMagicDrop().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseSkill().click();
//		Thread.sleep(1000);
//		this.buyPage.getExpSpirit().click();
//		Thread.sleep(1000);
//		this.buyPage.getConfirm().click();
//		Thread.sleep(1000);
//	}
//	
//	@Test
//	public void buyskill6() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//		this.viewChar.getBuySkillButton().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseTree().click();
//		Thread.sleep(1000);
//		this.buyPage.getMagicDrop().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseSkill().click();
//		Thread.sleep(1000);
//		this.buyPage.getEnSpirit().click();
//		Thread.sleep(1000);
//		this.buyPage.getConfirm().click();
//		
//	}
//	@Test
//	public void buyskill7() throws InterruptedException {
//		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
//		this.viewChar.getBuySkillButton().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseTree().click();
//		Thread.sleep(1000);
//		this.buyPage.getMagicDrop().click();
//		Thread.sleep(1000);
//		this.buyPage.getChooseSkill().click();
//		Thread.sleep(1000);
//		this.buyPage.getEnSpirit().click();
//		Thread.sleep(1000);
//		this.buyPage.getConfirm().click();
//		
//	} 
	
	
	
	
	@After
	public void tearDown() {
		driver.quit();
	}

}
