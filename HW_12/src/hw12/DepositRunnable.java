package hw12;


public class DepositRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean stopRequested = false;
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			int i = 0;
			while (!stopRequested && i < 10){
				account.deposit(100);
				Thread.sleep( 1000 );
				i++;
			}
		}catch(InterruptedException exception){}
	}
	
	public void stop(){
		stopRequested = true;
	}
}
