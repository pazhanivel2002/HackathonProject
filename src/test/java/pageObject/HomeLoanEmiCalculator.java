package pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.Excel;

public class HomeLoanEmiCalculator extends BasePage {
	
	public HomeLoanEmiCalculator(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="homeprice")
	WebElement homeValue;
	@FindBy(id="downpayment")
	WebElement downPayment;
	@FindBy(id="homeloaninsuranceamount")
	WebElement loanInsurance;
	@FindBy(id="homeloaninterest")
	WebElement homeInterest;
	@FindBy(id="homeloanterm")
	WebElement loanTenure;
	@FindBy(id="loanfees")
	WebElement loanFees;
	@FindBy(id="monthlyprincipalandinterestterm")
	WebElement simpleClick;
	@FindBy(id="menu-item-dropdown-2696")
	WebElement calculators;
	@FindBy(xpath="//*[@id=\"menu-item-2423\"]/a")
	WebElement loanCalculator;
	
	@FindBy(xpath="//table[@class='noextras']//th")
	List<WebElement> header;
	@FindBy(xpath="//tr[@class='row no-margin yearlypaymentdetails']")
	List<WebElement> row;
	
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[2]/div[2]/div/div/div/div/label[1]")
	WebElement marginPercentageBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[2]/div[2]/div/div/div/div/label[2]")
	WebElement marginRupeesBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[3]/div[2]/div/div/div/div/label[1]")
	WebElement loanTenureYearBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[3]/div[2]/div/div/div/div/label[2]")
	WebElement loanTenureMonthBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[3]/div[3]/div/div/div/div/label[1]")
	WebElement feesPercentageBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[1]/div[3]/div[3]/div/div/div/div/label[2]")
	WebElement feesRupeesBtn;
	
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[1]/div/div/div/div/label[1]")
	WebElement expensesPercentageBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[1]/div/div/div/div/label[2]")
	WebElement expensesRupeesBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[2]/div/div/div/div/label[1]")
	WebElement taxesPercentageBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[2]/div/div/div/div/label[2]")
	WebElement taxesRupeesBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[3]/div/div/div/div/label[1]")
	WebElement insurancePercentageBtn;
	@FindBy(xpath="//*[@id=\"homeloanemicalculatorformInner\"]/div[1]/div[2]/div[2]/div[3]/div/div/div/div/label[2]")
	WebElement insuranceRupeesBtn;
	
	public void marginValidation() {
		marginRupeesBtn.click();
		marginPercentageBtn.click();
	}
	public void loanTenureValidation() {
		loanTenureMonthBtn.click();
		loanTenureYearBtn.click();
	}
	public void loanFeesValidation() {
		feesRupeesBtn.click();
		feesPercentageBtn.click();
	}
	public void expensesValidation() {
		expensesRupeesBtn.click();
		expensesPercentageBtn.click();
	}
	public void taxesValidation() {
		taxesRupeesBtn.click();
		taxesPercentageBtn.click();
	}
	public void insuranceValidation() {
		insuranceRupeesBtn.click();
		insurancePercentageBtn.click();
	}
	public void setValues(String hv,String dp,String li,String lin,String lt,String lf) {
		clearTextBox(homeValue);
		homeValue.sendKeys(hv);
		clearTextBox(downPayment);
		downPayment.sendKeys(dp);
		clearTextBox(loanInsurance);
		loanInsurance.sendKeys(li);
		clearTextBox(homeInterest);
		homeInterest.sendKeys(lin);
		clearTextBox(loanTenure);
		loanTenure.sendKeys(lt);
		clearTextBox(loanFees);
		loanFees.sendKeys(lf);
		simpleClick.click();
	}
	public void setExcelData() throws IOException {
		Excel e=new Excel();
		for(int i=1;i<=row.size();i++) {
			String yearid=driver.findElement(By.xpath("//tr[@class='row no-margin yearlypaymentdetails']["+i+"]/td[1]")).getAttribute("id");
			driver.findElement(By.xpath("//*[@id='"+yearid+"']")).click();
			driver.findElement(By.xpath("//tr[@class='row no-margin yearlypaymentdetails']["+i+"]")).click();
			for(int j=1;j<=header.size();j++) {
				String data=driver.findElement(By.xpath("//tr[@class='row no-margin yearlypaymentdetails']["+i+"]/td["+j+"]")).getText();
				e.excel(data,1,--j,"Sheet"+i);
				++j;
				String data1=driver.findElement(By.xpath("//table[@class=\"noextras\"]//th["+j+"]")).getText();
				e.excel(data1,0,--j,"Sheet"+i);
				++j;
				if(j==7) {
					String monthid=driver.findElement(By.xpath("//table[@class='noextras']//tr[@class='row no-margin monthlypaymentdetails']["+i+"]")).getAttribute("id");
					List<WebElement> InTable=driver.findElements(By.xpath("//*[@id='"+monthid+"']/td/div/table/tbody/tr"));
					for(int k=1;k<=InTable.size();k++) {
						for(int l=1;l<=header.size();l++) {
							String data2=driver.findElement(By.xpath("//*[@id='"+monthid+"']/td/div/table/tbody/tr["+k+"]/td["+l+"]")).getText();
							e.excel(data2,++k,--l,"Sheet"+i);
							--k;
							++l;
						}
					}
				}
			}
			
		}
	}
	
	public void navLoanCalculator() {
		calculators.click();
		loanCalculator.click();
	}
	
}
