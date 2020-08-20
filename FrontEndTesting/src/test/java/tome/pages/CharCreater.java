package tome.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CharCreater {
	
	private String url;
	
	public CharCreater() {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/Createchar.html";
	}
	
	@FindBy(xpath= "//*[@id=\"charnameinput\"]")
	private WebElement charNamein;
	
	@FindBy(xpath= "//*[@id=\"chooserace\"]")
	private Select raceDrop;
	
	@FindBy(xpath ="//*[@id=\"regionselect\"]")
	private Select originDrop;
	
	@FindBy(xpath ="//*[@id=\"backgroundentry\"]")
	private WebElement bgBox;
	
	@FindBy(xpath = "//*[@id=\"submitchar\"]")
	private WebElement subButton;

	public String getUrl() {
		return url;
	}

	public WebElement getCharNamein() {
		return charNamein;
	}

	public Select getRaceDrop() {
		return raceDrop;
	}

	public Select getOriginDrop() {
		return originDrop;
	}

	public WebElement getBgBox() {
		return bgBox;
	}

	public WebElement getSubButton() {
		return subButton;
	}
	
	

}
