package payment;

import domain.Database;

public class BankManager {
	
	public static void main(String[] args) {
		test();
		
	}

	private static void test() {
		Database db = new Database();
		//BankAccount testAcc = new BankAccount("123456", "mar kru", 123, 1, 2017, 100.00);
		//db.persist(testAcc);
		db.updateBankAccountBalance("123456", 150.0);
		BankAccount receivedAcc = db.getBankAccount("123456");
		System.out.println("balance: " + receivedAcc.getBalance());
	}
	
	public int verifyPayment(String cardNo, int cvc) {
		Database db = new Database();
		BankAccount account = db.getBankAccount(cardNo);
		if (account == null) {
			return -1; // no account found with such a card number
		} else if (account.getCvc() != cvc) {
			return -2;
		}
		return 1; // account verified successfully
	}

	public int deductBalance(String cardNo, double deduction) {
		Database db = new Database();
		BankAccount account = db.getBankAccount(cardNo);
		if (account.getBalance() < deduction) {
			return -1; // not enough money on account
		} else {
			double newBalance = account.getBalance() - deduction;
			db.updateBankAccountBalance(cardNo, newBalance);
			return 1; // successfully deducted money
		}
	}
	
}
