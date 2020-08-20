package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AccountPage;
import pages.Login;
import pages.UTomeIndex;

public class Account {
	
	private WebDriver driver;
	private UTomeIndex home;
	private Login login;
	private AccountPage account;
	
	@Given("^I am on the home page$")
	public void i_am_on_the_home_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources//functionalTests/chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
        home = PageFactory.initElements(driver, UTomeIndex.class);
        login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.get(this.home.getUrl());
	}

	@When("^I navigate to the login page$")
	public void i_navigate_to_the_login_page() throws Throwable {
	    this.home.clickNavToggle();
	    this.home.clickAccountButton();
	}

	@When("^Enter some account details$")
	public void enter_some_account_details() throws Throwable {
	    this.login.getfNameReg().sendKeys("Selenium");
	    this.login.getlNameReg().sendKeys("Gomez");
	    this.login.getuNameReg().sendKeys("SGomez");
	    this.login.getpWord1Reg().sendKeys("thisisatest");
	    this.login.getpWord2Reg().sendKeys("thisisatest");
	    Thread.sleep(2000);
	    this.login.getRegButton().click();
	}

	@When("^login$")
	public void login() throws Throwable {
	    this.login.clickReg();
	    
	}

	@Then("^I will be taken to the account page$")
	public void i_will_be_taken_to_the_account_page() throws Throwable {
	    assertEquals("Account Information",this.account.getaInfoHead().getText());
	}

	@Then("^my username will be correctly displayed$")
	public void my_username_will_be_correctly_displayed() throws Throwable {
	    assertEquals("Your Username: SGomez", this.account.getuNameDisp().getText());
	    Thread.sleep(2000);
	}
	
	@After
	void tearDown1() {
        driver.quit();
    }
	
	
}