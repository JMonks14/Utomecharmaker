package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login {
	
	private String url;
	
	public Login () {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/login.html";
	}
	
	@FindBy(xpath = "//*[@id=\"firstnameregister\"]")
	private WebElement fNameReg;
	
	@FindBy(xpath = "//*[@id=\"lastnameregister\"]")
	private WebElement lNameReg;
	
	@FindBy(xpath = "//*[@id=\"usernameregister\"]")
	private WebElement uNameReg;
	
	@FindBy(xpath = "//*[@id=\"Passwordregister\"]")
	private WebElement pWord1Reg;
	
	@FindBy(xpath = "//*[@id=\"confirmPasswordregister\"]")
	private WebElement pWord2Reg;
	
	@FindBy(xpath = "//*[@id=\"registerbutton\"]")
	private WebElement regButton;
	
	@FindBy(xpath = "//*[@id=\"usernamelogin\"]")
	private WebElement uNameLogin;
	
	@FindBy(xpath = "//*[@id=\"passwordlogin\"]")
	private WebElement pwLogin;
	
	@FindBy(xpath= "//*[@id=\"loginbutton\"]")
	private WebElement Loginbutton;
	
	public WebElement getRegButton() {
		return regButton;
	}

	public WebElement getuNameLogin() {
		return uNameLogin;
	}

	public WebElement getPwLogin() {
		return pwLogin;
	}

	public WebElement getLoginbutton() {
		return Loginbutton;
	}

	public void clickReg() {
		this.regButton.click();
	}

	public String getUrl() {
		return url;
	}

	public WebElement getfNameReg() {
		return fNameReg;
	}

	public WebElement getlNameReg() {
		return lNameReg;
	}

	public WebElement getuNameReg() {
		return uNameReg;
	}

	public WebElement getpWord1Reg() {
		return pWord1Reg;
	}

	public WebElement getpWord2Reg() {
		return pWord2Reg;
	}
	
}
