package tome.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewChar {
	
	private String url;
	
	public ViewChar() {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/viewchar.html";
	}
	
	@FindBy(xpath="//*[@id=\"resetXPbutton\"]")
	private WebElement resetButton;
	
	@FindBy(xpath="//*[@id=\"buyskillbutton\"]")
	private WebElement buySkillButton;
	
	@FindBy(xpath="//*[@id=\"backtoaccount\"]")
	private WebElement accountButton;
	
	@FindBy(xpath="//*[@id=\"retirecharbutton\"]")
	private WebElement killButton;
	
	@FindBy(xpath="//*[@id=\"skilltable\"]/tr[1]/th")
	private WebElement skill1;
	
	@FindBy(xpath="//*[@id=\"spelltable\"]/tr[1]/th")
	private WebElement spell1;
	
	@FindBy(xpath="//*[@id=\"msgspace\"]")
	private WebElement nospace;

	public WebElement getNospace() {
		return nospace;
	}

	public WebElement getSpell1() {
		return spell1;
	}

	public WebElement getSkill1() {
		return skill1;
	}

	public String getUrl() {
		return url;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public WebElement getBuySkillButton() {
		return buySkillButton;
	}

	public WebElement getAccountButton() {
		return accountButton;
	}

	public WebElement getKillButton() {
		return killButton;
	}

}
