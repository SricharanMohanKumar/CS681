package edu.umb.cs681.hw12;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable {
    public AtomicBoolean done = new AtomicBoolean(false);
    private final BankAccount myaccount;
    private ReentrantLock lock = new ReentrantLock();

    public DepositRunnable(BankAccount account)
    {
        lock.lock();
        try
        {
            this.myaccount = account;
        }
        finally
        {
            lock.unlock();
        }

    }

    public void setDone()
    {
        this.done.set(true);
    }

    public void run()
    {
        while (true)
        {
            if (done.get())
            {
                System.out.println(Thread.currentThread().getName() + "Money deposited successfully!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Money depositing in progress");
            myaccount.deposit(500.00);

            try
            {
                Thread.sleep(2500);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }
}


