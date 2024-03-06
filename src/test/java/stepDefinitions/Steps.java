package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.CarLoan;
import pageObject.HomeLoanEmiCalculator;
import pageObject.LoanCalculator;

public class Steps {
	WebDriver driver;
	CarLoan cl;
	LoanCalculator lc;
	HomeLoanEmiCalculator hl;
	@Given("The user need to navigate to webpage")
	public void the_user_need_to_navigate_to_webpage() {
		driver=new ChromeDriver();
		driver.get("https://emicalculator.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@When("Data need to be filled on Car loan window")
	public void data_need_to_be_filled_on_car_loan_window() {	
		cl=new CarLoan(driver);
		Assert.assertEquals(driver.getTitle(),"EMI Calculator for Home Loan, Car Loan & Personal Loan in India");
		cl.carLoanAmount("1500000");
		cl.interestRate("9.5");
		cl.loanTenure("1");
	}

	@Then("Printing data on console window")
	public void printing_data_on_console_window() {
		System.out.println("Principle amount for one month: "+cl.principleAmount());
		System.out.println("Interest amount for one month: "+cl.interestAmount());
	}

	@When("The user clicks on Home loan EMI calculator")
	public void the_user_clicks_on_home_loan_emi_calculator() {
		cl.navHomeLoanEmiCalculator();
	}

	@Then("Title and UI validation need to be performed")
	public void title_and_ui_validation_need_to_be_performed() {
		Assert.assertEquals(driver.getTitle(),"Home Loan EMI Calculator with Prepayments, Taxes & Insurance");
		hl=new HomeLoanEmiCalculator(driver);
		hl.marginValidation();
		hl.loanTenureValidation();
		hl.loanFeesValidation();
		hl.expensesValidation();
		hl.taxesValidation();
		hl.insuranceValidation();
	}

	@Then("Data filling need to be performed")
	public void data_filling_need_to_be_performed() {
		hl.setValues("8000000", "30", "1000000","12","1","0.5");
	}

	@When("The User clicks on Loan calculator")
	public void the_user_clicks_on_loan_calculator() {
		hl.navLoanCalculator();
	}

	@Then("Title Validation need to be performed")
	public void title_validation_need_to_be_performed() {
		Assert.assertEquals(driver.getTitle(),"Loan Calculator — Calculate EMI, Affordability, Tenure & Interest Rate");
	}

	@Then("Slider Validation need to be followed")
	public void slider_validation_need_to_be_followed() {
	    lc=new LoanCalculator(driver);
		System.out.println("EMI Calculator");
		System.out.println("Loan Amount: "+lc.loanAmountSliderMovement(600));
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(300));
		System.out.println("Loan Tenure for year: "+lc.yearLoanTermSliderMovement(400));
		System.out.println("Loan Tenure for month: "+lc.monthLoanTermSliderMovement(400));
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(200));
	}

	@When("The User clicks on Loan amount calculator")
	public void the_user_clicks_on_loan_amount_calculator() {
		Assert.assertEquals("Loan Amount\n"+"Calculator",lc.clickLoanAmountCalculator());
	}

	@Then("Title and slider validation need to be performed")
	public void title_and_slider_validation_need_to_be_performed() {
		System.out.println("Loan Amount Calculator");
		System.out.println("EMI: "+lc.loanEmiSliderMovement(700));
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(200));
		System.out.println("Loan Tenure for year: "+lc.yearLoanTermSliderMovement(300));
		System.out.println("Loan Tenure for month: "+lc.monthLoanTermSliderMovement(300));
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(100));

	}

	@When("The User clicks on Loan Tenure Calculator")
	public void the_user_clicks_on_loan_tenure_calculator() {
		Assert.assertEquals("Loan Tenure\n"+"Calculator",lc.clickLoanTenureCalculator());
	}

	@Then("Title and slider validation need to performed")
	public void title_and_slider_validation_need_to_performed() {
		System.out.println("Loan Tenure Calculator");
		System.out.println("Loan Amount: "+lc.loanAmountSliderMovement(400));
		System.out.println("EMI: "+lc.loanEmiSliderMovement(100));
		System.out.println("Interest Rate: "+lc.interestRateSliderMovement(100));
		System.out.println("Fees&Charges: "+lc.loanFeesSliderMovement(50));
		driver.quit();
	}
}
