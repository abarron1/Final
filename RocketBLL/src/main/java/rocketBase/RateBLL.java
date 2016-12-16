package rocketBase;

import org.apache.poi.ss.formula.functions.*;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	static double getRate(int GivenCreditScore) 
	{
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		rates = _RateDAL.getAllRates();
		
		double RightRate = 0;
		return RightRate;
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return FinanceLib.pmt(r, n, p, f, t);
	}
}
