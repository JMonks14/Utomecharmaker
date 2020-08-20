package tome.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UTomeIndex {
	
	private String url;
	@FindBy(xpath = "/html/body/nav/button")
	private WebElement navToggle;
	
	@FindBy(xpath = "//*[@id=\"navbarNav\"]/ul/li[2]/a")
	private WebElement accountButton;
	
	public UTomeIndex() {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/index.html";
	}

	public String getUrl() {
		return url;
	}

	public WebElement getNavToggle() {
		return navToggle;
	}

	public WebElement getAccountButton() {
		return accountButton;
	}
	
	public void clickNavToggle() {
		this.navToggle.click();
	}
	
	public void clickAccountButton() {
		this.accountButton.click();
	}

}
