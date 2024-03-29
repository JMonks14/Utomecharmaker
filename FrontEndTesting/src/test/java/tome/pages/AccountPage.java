package tome.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage {
	
	private String url;
	
	public AccountPage() {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/Account.html";
	}
	
	@FindBy(xpath = "//*[@id=\"col1\"]/h5")
	private WebElement aInfoHead;
	
	@FindBy(xpath = "//*[@id=\"namepara\"]")
	private WebElement myNameDisp;
	
	@FindBy(xpath = "//*[@id=\"usernamepara\"]")
	private WebElement uNameDisp;
	
	@FindBy(xpath = "//*[@id=\"updatenamebutton\"]")
	private WebElement nameUpButon;
	
	@FindBy(xpath = "//*[@id=\"firstnameupdate\"]")
	private WebElement fNameEnter;
	
	@FindBy(xpath = "//*[@id=\"lastnameupdate\"]")
	private WebElement lNameEnter;
	
	@FindBy(xpath = "//*[@id=\"subnamebutton\"]")
	private WebElement nameUpSubButton;
	
	@FindBy(xpath = "//*[@id=\"updateusernamebutton\"]")
	private WebElement uNameUpButton;
	
	@FindBy(xpath = "//*[@id=\"unameupdate\"]")
	private WebElement uNameEnter;
	
	@FindBy(xpath = "//*[@id=\"subusernamebutton\"]")
	private WebElement uNameUpSubmit;
	
	@FindBy(xpath ="//*[@id=\"changepasswordbutton\"]")
	private WebElement chPwButton;
	
	@FindBy(xpath="//*[@id=\"oldpassword\"]")
	private WebElement oPwEnter;
	
	@FindBy(xpath="//*[@id=\"newpassword\"]")
	private WebElement nPwEnter1;
	
	@FindBy(xpath="//*[@id=\"newpasswordcon\"]")
	private WebElement nPwEnter2;
	
	@FindBy(xpath="//*[@id=\"subnamebutton\"]")
	private WebElement pwSubButton;
	
	@FindBy(xpath = "//*[@id=\"createcharbutton\"]")
	private WebElement createButton;
	
	@FindBy(xpath="//*[@id=\"viewcharbutton\"]")
	private WebElement viewChar;
	
	public WebElement getViewChar() {
		return viewChar;
	}

	@FindBy(xpath = "//*[@id=\"charnamespace\"]")
	private WebElement charName;

	public WebElement getCharName() {
		return charName;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public WebElement getChPwButton() {
		return chPwButton;
	}

	public WebElement getoPwEnter() {
		return oPwEnter;
	}

	public WebElement getnPwEnter1() {
		return nPwEnter1;
	}

	public WebElement getnPwEnter2() {
		return nPwEnter2;
	}

	public WebElement getPwSubButton() {
		return pwSubButton;
	}

	public WebElement getuNameDisp() {
		return uNameDisp;
	}

	public String getUrl() {
		return url;
	}

	public WebElement getaInfoHead() {
		return aInfoHead;
	}

	public WebElement getMyNameDisp() {
		return myNameDisp;
	}

	public WebElement getNameUpButon() {
		return nameUpButon;
	}

	public WebElement getfNameEnter() {
		return fNameEnter;
	}

	public WebElement getlNameEnter() {
		return lNameEnter;
	}

	public WebElement getNameUpSubButton() {
		return nameUpSubButton;
	}

	public WebElement getuNameUpButton() {
		return uNameUpButton;
	}

	public WebElement getuNameEnter() {
		return uNameEnter;
	}

	public WebElement getuNameUpSubmit() {
		return uNameUpSubmit;
	}
	
	public void clickSubName() {
		this.nameUpSubButton.click();
	}
	
	public void clickSubUName() {
		this.uNameUpSubmit.click();
	}
	
}
