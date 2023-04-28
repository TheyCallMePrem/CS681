package hw12;


public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	private volatile boolean stopRequested = false;
	
	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			int i = 0;
			while (!stopRequested && i < 10){
				account.withdraw(100);
				Thread.sleep( 1000 );
				i++;
			}
		}catch(InterruptedException exception){}
	}
	
	public void stop(){
		stopRequested = true;
	}
}
