package edu.umb.cs681.hw12;

import java.util.ArrayList;
import java.util.List;

public class Main {
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
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }

        for (Thread withdrawThread : WThreads)
        {
            try
            {
                withdrawThread.join();
            }
            catch (InterruptedException exception)
            {
                exception.printStackTrace();
            }
        }

    }
}
