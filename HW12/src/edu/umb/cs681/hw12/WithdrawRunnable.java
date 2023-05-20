package edu.umb.cs681.hw12;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable
{
    public AtomicBoolean done = new AtomicBoolean(false);
    private final BankAccount account;
    private ReentrantLock lock = new ReentrantLock();

    public WithdrawRunnable(BankAccount account)
    {
        lock.lock();
        try
        {
            this.account = account;
        }
        finally
        {
            lock.unlock();
        }

    }

    public void setDone()
    {
        done.set(true);
    }

    public void run()
    {
        while (true)
        {
            if (done.get())
            {
                System.out.println(Thread.currentThread().getName() + " Money deposited successfully!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Money depositing in progress");
            account.withdraw(300.00);
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException ex)
            {

            }
        }
    }
}

