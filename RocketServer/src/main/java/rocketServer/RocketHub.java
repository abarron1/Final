package rocketServer;

import java.io.IOException;

import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			double payment = RateBLL.getPayment(lq.getdRate()/100,lq.getiTerm()*12,lq.getdAmount()-lq.getiDownPayment(), 0.0, false);
			lq.setdPayment(payment);
			
			try {
				int creditScore = lq.getiCreditScore();
				lq.setdRate(RateBLL.getRate(creditScore));
			} catch (RateException e) {
				sendToAll(e);
				System.out.println("Credit Score invalid");
			}
			sendToAll(lq);
		}
	}
}

