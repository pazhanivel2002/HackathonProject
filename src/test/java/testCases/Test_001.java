package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.CarLoan;
import pageObject.HomeLoanEmiCalculator;
import pageObject.LoanCalculator;

public class Test_001 extends BaseClass {
	CarLoan cl;
	LoanCalculator lc;
	HomeLoanEmiCalculator hl;
	
	@Test(priority=1,groups= {"sanity","regression"})
	public void verifyValidCarWebPage(){
		logger.info("*****Test_001 Has Started*****");
		cl=new CarLoan(driver);
		Assert.assertEquals(driver.getTitle(),"EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		logger.info("Validation of WebPage Title is done");
		captureScreen("img_titleValidationEmiCalculator");
	}
	@Test(priority=2,groups= {"regression"})
	public void dataFillingInCarLoan() {
		logger.info("Filling of data on car loan Window Started");
		cl.carLoanAmount("1500000");
		cl.interestRate("9.5");
		cl.loanTenure("1");
		logger.info("Filling of data on car loan Window Done");
		captureScreen("img_dataFillingCarLoan");
		logger.info("Printing one month principle and interest Started");
		System.out.println("Principle amount for one month: "+cl.principleAmount());
		System.out.println("Interest amount for one month: "+cl.interestAmount());
		logger.info("Printing one month principle and interest Done");
	}
	@Test(priority=3,groups= {"sanity","regression"})
	public void verifyValidHomeWebPage() {
		logger.info("Navigation of Home Loan Emi Calculator page Started");
		cl.navHomeLoanEmiCalculator();
		logger.info("Navigation of Home Loan Emi Calculator page Done");
		logger.info("Validation of web page title started");
		Assert.assertEquals(driver.getTitle(),"Home Loan EMI Calculator with Prepayments, Taxes & Insurance");
		logger.info("Validation of web page title done");
		captureScreen("img_titleValidationHomeLoan");
	}
	@Test(priority=4,groups= {"sanity","regression"})
	public void uiHomeLoanEmiCalculator() {
		hl=new HomeLoanEmiCalculator(driver);
		logger.info("User Interface Checking on Home Loan Emi Calculator Started");
		hl.marginValidation();
		hl.loanTenureValidation();
		hl.loanFeesValidation();
		logger.info("User Interface Checking on Home Loan Emi Calculator Done");
		captureScreen("img_uiHomeLoan");
	}
	@Test(priority=5,groups= {"sanity","regression"})
	public void uiHomeOwnerExpenses() {
		logger.info("User Interface Checking on Home owner expenses Started");
		hl.expensesValidation();
		hl.taxesValidation();
		hl.insuranceValidation();
		logger.info("User Interface Checking on Home owner expenses Done");
		captureScreen("img_uiHomeOwner");
	}
	@Test(priority=6,groups= {"regression"})
	public void dataFillingInHomeLoan() throws IOException {
		logger.info("Filling Data on Home Loan Emi Calculator Started");
		hl.setValues("8000000", "30", "1000000","12","1","0.5");
		hl.setExcelData();
		logger.info("Filling Data on Home Loan Emi Calculator Done");
		captureScreen("img_dataFillingHomeLoan");
	}
	@Test(priority=7,groups= {"sanity","regression"})
	public void verifyValidLoanWebPage() {
		hl.navLoanCalculator();
		logger.info("Validation of web page title started");
		Assert.assertEquals(driver.getTitle(),driver.getTitle());
		logger.info("Validation of WebPage Title is done");
		captureScreen("img_titleValidationLoanCalculator");
	}
	@Test(priority=8,groups= {"sanity","regression"})
	public void ecLoanAmount() {
		lc=new LoanCalculator(driver);
		logger.info("Validation of Emi Calculator Started");
		System.out.println("EMI Calculator");
		System.out.println("Loan Amount: "+lc.loanAmountSliderMovement(600));
		captureScreen("img_ecLoanAmount");
	}
	@Test(priority=9,groups= {"regression"})
	public void ecInterest() {
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(300));
		captureScreen("img_ecInterest");
	}
	
	@Test(priority=10,groups= {"sanity","regression"})
	public void ecLoanTenure() {
		logger.info("Changing the Loan Tenure for year and month in Emi Calculator Started");
		System.out.println("Loan Tenure for year: "+lc.yearLoanTermSliderMovement(400));
		System.out.println("Loan Tenure for month: "+lc.monthLoanTermSliderMovement(400));
		logger.info("Changing the Loan Tenure for year and month in Emi Calculator Done");
		captureScreen("img_ecLoanTenure");

	}
	@Test(priority=11,groups= {"regression"})
	public void ecFeesAndCharge() {
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(200));
		logger.info("Validation of Emi Calculator Started");
		captureScreen("img_ecFeesAndCharge");
	}
	@Test(priority=12,groups= {"sanity","regression"})
	public void verifyValidLoanAmountCalculator() {
		logger.info("Validation of Loan Amount Calculator Started");
		Assert.assertEquals("Loan Amount\n"+"Calculator",lc.clickLoanAmountCalculator());
		captureScreen("img_titleValidationLoanAmount");
	}
	@Test(priority=13,groups= {"regression"})
	public void lacEmi()  {
		System.out.println("Loan Amount Calculator");
		System.out.println("EMI: "+lc.loanEmiSliderMovement(700));
		captureScreen("img_lacEmi");
	}
	@Test(priority=14,groups= {"regression"})
	public void lacInterest() {
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(200));
		captureScreen("img_lacInterest");
	}
	@Test(priority=15,groups= {"sanity","regression"})
	public void lacLoanTenure() {
		logger.info("Changing the Loan Tenure for year and month in Loan amount Calculator Started");
		System.out.println("Loan Tenure for year: "+lc.yearLoanTermSliderMovement(300));
		System.out.println("Loan Tenure for month: "+lc.monthLoanTermSliderMovement(300));
		logger.info("Changing the Loan Tenure for year and month in Loan amount Calculator Done");
		captureScreen("img_lacLoanTenure");
	}
	@Test(priority=16,groups= {"regression"})
	public void lacFeesAndCharge() {
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(100));
		logger.info("Validation of Loan Amount Calculator Done");
		captureScreen("img_lacFeesAndCharge");
	}
	@Test(priority=17,groups= {"sanity","regression"})
	public void verifyValidLoanTenureCalculator() {
		logger.info("Validation of Loan Tenure Calculator Started");
		Assert.assertEquals("Loan Tenure\n"+"Calculator",lc.clickLoanTenureCalculator());
		captureScreen("img_titleValidationLoanTenure");
	}
	@Test(priority=18,groups= {"regression"})
	public void ltcLoanAmount() {
		System.out.println("Loan Tenure Calculator");
		System.out.println("Loan Amount: "+lc.loanAmountSliderMovement(400));
		captureScreen("img_ltcLoanAmount");
	}
	@Test(priority=19,groups= {"regression"})
	public void ltcEmiAndInterest() {
		System.out.println("EMI: "+lc.loanEmiSliderMovement(100));
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(100));
		captureScreen("img_ltcEmiAndInterest");
	}
	@Test(priority=20,groups= {"regression"})
	public void ltcFees() {
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(50));
		logger.info("Validation of Loan Tenure Calculator Done");
		captureScreen("img_ltcFees");
		logger.info("*****Test_001 Has Finished*****");
	}
}
