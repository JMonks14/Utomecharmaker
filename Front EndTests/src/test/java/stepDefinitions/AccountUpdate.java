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

public class AccountUpdate {
	
	private WebDriver driver;
	private Login login;
	private AccountPage account;
	
	@Given("^I am on the account page$")
	public void i_am_on_the_account_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "src/test/resources//functionalTests/chromedriver.exe");
		driver = new ChromeDriver();
		login = PageFactory.initElements(driver, Login.class);
        account = PageFactory.initElements(driver, AccountPage.class);
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(this.login.getUrl());
        this.login.getuNameLogin().sendKeys("SGomez");
        this.login.getPwLogin().sendKeys("thisisatest");
        this.login.getLoginbutton().click();
	    
	}

	@When("^I click \"([^\"]*)\"$")
	public void i_click(String arg1) throws Throwable {
		Thread.sleep(2000);
	    this.account.getNameUpButon().click();
	    
	}

	@When("^I submit a new name$")
	public void i_submit_a_new_name() throws Throwable {
	    this.account.getfNameEnter().sendKeys("Cucumber");
	    this.account.getlNameEnter().sendKeys("Sandwich");
	    Thread.sleep(2000);
	    this.account.getNameUpSubButton().click();
	    
	}

	@Then("^My new name will be displayed$")
	public void my_new_name_will_be_displayed() throws Throwable {
		assertEquals("Your Name: Cucumber Sandwich",this.account.getNameDisp().getText());
	    Thread.sleep(2000);
	}

	@When("^I submit a new password$")
	public void i_submit_a_new_password() throws Throwable {
		Thread.sleep(2000);
	    this.account.getChPwButton().click();
	    this.account.getoPwEnter().sendKeys("thisisatest");
	    this.account.getnPwEnter1().sendKeys("changetest");
	    this.account.getnPwEnter2().sendKeys("changetest");
	    Thread.sleep(2000);
	    this.account.getPwSubButton().click();
	}

	@Then("^the password changed successfully alert appears$")
	public void the_password_changed_successfully_alert_appears() throws Throwable {
		Thread.sleep(2000);
	    driver.switchTo().alert().accept();
	}
	
	@When("^I submit a new username$")
	public void i_submit_a_new_username() throws Throwable {
	    this.account.getuNameUpButton().click();
	    this.account.getuNameEnter().sendKeys("CucSand");
	    Thread.sleep(2000);
	    this.account.getuNameUpSubmit().click();
	}
	
	@Then("^My new username will be displayed$")
	public void my_new_username_will_be_displayed() throws Throwable {
	    Thread.sleep(2000);
	    assertEquals("Your Username: CucSand", this.account.getuNameDisp().getText());
	    throw new PendingException();
	}

	@Then("^I will return to the account page$")
	public void i_will_return_to_the_account_page() throws Throwable {
		assertEquals("Account Information",this.account.getaInfoHead().getText());
	}
	@After
	void tearDown() {
        driver.quit();
    }

}
