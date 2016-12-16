package exceptions;

public class RateException extends Exception {
	public RateDomainModel model;
	public RateException(RateDomainModel m) {
		model = m;
	}
	
	public RateDomainModel getModel() {
		return model;
	}
}