package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CarLoan extends BasePage {
	JavascriptExecutor js;
	public CarLoan(WebDriver driver) {
		super(driver);
	    js=(JavascriptExecutor)driver;
	}
	@FindBy(id="car-loan")
	WebElement carLoanNavi;
	@FindBy(id="loanamount")
	WebElement loanAmount;
	@FindBy(id="loaninterest")
	WebElement loanInterest;
	@FindBy(id="loanterm")
	WebElement loanTerm;
	@FindBy(id="year2024")
	WebElement carMonth;
	@FindBy(xpath="//label[normalize-space()='EMI in Arrears']")
	WebElement simpleClick;
	@FindBy(xpath="//*[@id=\"monthyear2024\"]/td/div/table/tbody/tr[1]/td[2]")
	WebElement principleAmount;
	@FindBy(xpath="//*[@id=\"monthyear2024\"]/td/div/table/tbody/tr[1]/td[3]")
	WebElement interestAmount;
	@FindBy(id="menu-item-dropdown-2696")
	WebElement calculators;
	@FindBy(xpath="//a[@title='Home Loan EMI Calculator']")
	WebElement homeLoanEmiCalculator;
	public void carLoanAmount(String amount) {
		carLoanNavi.click();
		clearTextBox(loanAmount);
		loanAmount.sendKeys(amount);
	}
	public void interestRate(String interest) {
		clearTextBox(loanInterest);
		loanInterest.sendKeys(interest);
	}
	public void loanTenure(String tenure) {
		clearTextBox(loanTerm);
		loanTerm.sendKeys(tenure);	
		simpleClick.click();
	}
	public String principleAmount() {
		js.executeScript("arguments[0].scrollIntoView(true)", carMonth);
		Actions act=new Actions(driver);
		act.doubleClick(carMonth).perform();
		return principleAmount.getText();
	}
	public String interestAmount() {
		return interestAmount.getText();
	}
	public void navHomeLoanEmiCalculator() {
		calculators.click();
		homeLoanEmiCalculator.click();
	}
}
