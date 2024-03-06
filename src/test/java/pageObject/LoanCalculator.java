package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class LoanCalculator extends BasePage {
	JavascriptExecutor js;
	WebDriver driver;
	public LoanCalculator(WebDriver driver) {
		super(driver);
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	@FindBy(xpath="//div[@id='loanamountslider']/div")
	WebElement loanAmountSlider;
	@FindBy(id="loanamount")
	WebElement loanAmount;
	@FindBy(xpath="//div[@id='loaninterestslider']/div")
	WebElement interestSlider;
	@FindBy(id="loaninterest")
	WebElement loanInterest;
	@FindBy(xpath="//*[@id=\"loantermslider\"]/div")
	WebElement loanTermSlider;
	@FindBy(id="loanterm")
	WebElement loanTerm;
	@FindBy(xpath="//*[@id=\"ltermwrapper\"]/div[1]/div/div/div/div/div/label[2]")
	WebElement monthButton;
	@FindBy(xpath="//*[@id=\"loanfeesslider\"]/div")
	WebElement loanFeesSlider;
	@FindBy(id="loanfees")
	WebElement loanFees;
	
	@FindBy(xpath="//*[@id=\"loan-amount-calc\"]/a[1]")
	WebElement loanAmountCalculator;
	
	@FindBy(xpath="//div[@id='loanemislider']/div")
	WebElement loanEmiSlider;
	@FindBy(id="loanemi")
	WebElement loanEmi;
	
	@FindBy(xpath="//*[@id=\"loan-tenure-calc\"]/a[1]")
	WebElement loanTenureCalculator;
	
	public void sliderMovement(WebElement element,int x) {
		Actions move=new Actions(driver);
     	move.clickAndHold(element)
     	.moveByOffset(x, 0)
     	.release().perform();
     	x=0;
	}
	
	public String loanAmountSliderMovement(int x) {
		sliderMovement(loanAmountSlider,x);
		return loanAmount.getAttribute("value");
	}
	public String interestRateSliderMovement(int x) {
		sliderMovement(interestSlider,x);
		return loanInterest.getAttribute("value");
	}
	public String yearLoanTermSliderMovement(int x) {
		sliderMovement(loanTermSlider,x);
		return loanTerm.getAttribute("value");
	}
	public String monthLoanTermSliderMovement(int x) {
		monthButton.click();
		sliderMovement(loanTermSlider,x);
		return loanTerm.getAttribute("value");
	}
	public String loanFeesSliderMovement(int x) {
		sliderMovement(loanFeesSlider,x);
		return loanFees.getAttribute("value");
	}
	
	public String clickLoanAmountCalculator() {
		loanAmountCalculator.click();
		return loanAmountCalculator.getText();
	}
	
	public String loanEmiSliderMovement(int x) {
		sliderMovement(loanEmiSlider,x);
		return loanEmi.getAttribute("value");
	}
	
	public String clickLoanTenureCalculator() {
		loanTenureCalculator.click();
		return loanTenureCalculator.getText();
	}
}
