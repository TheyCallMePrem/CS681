package hw12;

public class ThreadUnsafeBankAccount implements BankAccount{
	private double balance = 0;

	public void deposit(double amount){
		System.out.print("Current balance (d): " + balance);
		balance = balance + amount;
		System.out.println(", New balance (d): " + balance);
	}
	
	public void withdraw(double amount){
		System.out.print("Current balance (w): " + balance);
		balance = balance - amount;
		System.out.println(", New balance (w): " + balance);
	}

	public double getBalance() { return this.balance; }
	
	public static void main(String[] args){
		ThreadUnsafeBankAccount bankAccount = new ThreadUnsafeBankAccount();		
		new Thread( new DepositRunnable(bankAccount) ).start();
		new Thread( new WithdrawRunnable(bankAccount) ).start();
	}
}
