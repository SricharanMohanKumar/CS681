package edu.umb.cs681.hw12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientFundsCondition = lock.newCondition();
	private Condition belowUpperLimitFundsCondition = lock.newCondition();
	
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() +
					" (d): current balance: " + balance);
			while(balance >= 300){
				System.out.println(Thread.currentThread().getId() +
						" (d): await(): Balance exceeds the upper limit.");
				belowUpperLimitFundsCondition.await();
			}
			balance += amount;
			System.out.println(Thread.currentThread().getId() +
					" (d): new balance: " + balance);
			sufficientFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println(Thread.currentThread().getId() +
					" (w): current balance: " + balance);
			while(balance <= 0){
				System.out.println(Thread.currentThread().getId() +
						" (w): await(): Insufficient funds");
				sufficientFundsCondition.await();
			}
			balance -= amount;
			System.out.println(Thread.currentThread().getId() +
					" (w): new balance: " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally
		{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance()
	{
		return this.balance;
	}

	public static void main(String[] args)
	{
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
		List<DepositRunnable> dRunnables = new ArrayList<DepositRunnable>();
		List<WithdrawRunnable> wRunnables = new ArrayList<WithdrawRunnable>();
		List<Thread> DThreads = new ArrayList<Thread>();
		List<Thread> WThreads = new ArrayList<Thread>();

		for (int i = 0; i < 3; i++)
		{
			DepositRunnable dRunnable = new DepositRunnable(bankAccount);
			dRunnables.add(dRunnable);
			Thread depositThread = new Thread(dRunnable);
			DThreads.add(depositThread);
			depositThread.start();
		}

		for (int i = 0; i < 3; i++)
		{
			WithdrawRunnable wRunnable = new WithdrawRunnable(bankAccount);
			wRunnables.add(wRunnable);
			Thread withdrawThread = new Thread(wRunnable);
			WThreads.add(withdrawThread);
			withdrawThread.start();
		}

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException ex)
		{
			ex.printStackTrace();
		}

		for (DepositRunnable depositRunnable : dRunnables)
		{
			depositRunnable.setDone();
		}

		for (WithdrawRunnable withdrawRunnable : wRunnables)
		{
			withdrawRunnable.setDone();
		}

		// interrupt all threads
		for (Thread depositThread : DThreads)
		{
			depositThread.interrupt();
		}

		for (Thread withdrawThread : WThreads)
		{
			withdrawThread.interrupt();
		}

		for (Thread depositThread : DThreads) {
			try
			{
				depositThread.join();
			} catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

		for (Thread withdrawThread : WThreads)
		{
			try
			{
				withdrawThread.join();
			} catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

	}
}
