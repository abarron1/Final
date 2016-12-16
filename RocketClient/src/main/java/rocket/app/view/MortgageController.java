package rocket.app.view;

import eNums.eAction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;
	
	private double txtIncome;
	private double txtExpenses;
	private int txtCreditScore;
	private int txtHouseCost;
	private int loanTerm;
	private String error;
	private boolean button;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		lq.setdIncome(txtIncome);
		lq.setiTerm(loanTerm);
		lq.setdExpenses(txtExpenses);
		lq.setiCreditScore(txtCreditScore);
		lq.setdAmount(txtHouseCost);

		a.setLoanRequest(lq);
		
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		{
			double Payment1 = lRequest.getIncome() * .28;
			double Payment2 = (lRequest.getIncome() * .36 - lRequest.getExpenses());
			double lastPayment;
			if (Payment1 < Payment2) {
				lastPayment = Payment1;
			} else {
				lastPayment = Payment2;
			}
			double payment = lRequest.getdPayment();
			if (payment > lastPayment) {
				paymentException.setText(payment + lastPayment + "The Payment is to high");
			} else {
				lblMortgagePayment.setText("Monthly Mortgage Payment is " + cf.format(lRequest.getdPayment()));
			}

		}
	}
}
