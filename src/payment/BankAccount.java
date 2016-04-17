package payment;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BankAccount {
	
	@Id
	private String cardNumber;
	private String cardholderName;
	int cvc;
	private int expiryMonth;
	int expiryYear;
	double balance;
	
	public BankAccount() {
		
	}

	public BankAccount(String cardNumber, String cardholderName, int cvc, int expiryMonth, int expiryYear,
			double balance) {
		super();
		this.cardNumber = cardNumber;
		this.cardholderName = cardholderName;
		this.cvc = cvc;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.balance = balance;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public int getExpiryYear() {
		return expiryYear;
	}

	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
