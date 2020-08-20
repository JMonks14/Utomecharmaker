package tome.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpendXPpage {
	
	private String url;
	
	public SpendXPpage() {
		this.url="file:///C:/Users/james/Documents/Git/Utomecharmaker/htmlpages/spendXP.html";
	}
	@FindBy(xpath="/html/body/div/div/div[1]/div/div[1]/div/div[1]/button")
	private WebElement chooseTree;
	
	@FindBy(xpath="//*[@id=\"bodydrop\"]")
	private WebElement bodyDrop;
	
	@FindBy(xpath="//*[@id=\"magicdrop\"]")
	private WebElement magicDrop;
	
	@FindBy(xpath="//*[@id=\"chooseskillbutt\"]")
	private WebElement chooseSkill;
	
	@FindBy(xpath="//*[@id=\"skillget_15\"]")
	private WebElement intenseTrain;
	
	@FindBy(xpath="//*[@id=\"confirmbuy\"]")
	private WebElement confirm;
	
	@FindBy(xpath="//*[@id=\"skillget_17\"]")
	private WebElement extremeTrain;
	
	@FindBy(xpath="//*[@id=\"skillget_31\"]")
	private WebElement cantrips;
	
	@FindBy(xpath="//*[@id=\"spellget_6\"]")
	private WebElement fling;
	
	@FindBy(xpath="//*[@id=\"skillget_32\"]")
	private WebElement broadSpirit;
	
	@FindBy(xpath="//*[@id=\"skillget_34\"]")
	private WebElement expSpirit;
	
	@FindBy(xpath="//*[@id=\"skillget_35\"]")
	private WebElement enSpirit;
	
	@FindBy(xpath="//*[@id=\"choosespellbutt\"]")
	private WebElement chooseSpell;

	public WebElement getChooseSpell() {
		return chooseSpell;
	}

	public String getUrl() {
		return url;
	}

	public WebElement getChooseTree() {
		return chooseTree;
	}

	public WebElement getBodyDrop() {
		return bodyDrop;
	}

	public WebElement getMagicDrop() {
		return magicDrop;
	}

	public WebElement getChooseSkill() {
		return chooseSkill;
	}

	public WebElement getIntenseTrain() {
		return intenseTrain;
	}

	public WebElement getConfirm() {
		return confirm;
	}

	public WebElement getExtremeTrain() {
		return extremeTrain;
	}

	public WebElement getCantrips() {
		return cantrips;
	}

	public WebElement getFling() {
		return fling;
	}

	public WebElement getBroadSpirit() {
		return broadSpirit;
	}

	public WebElement getExpSpirit() {
		return expSpirit;
	}

	public WebElement getEnSpirit() {
		return enSpirit;
	}
	
}
