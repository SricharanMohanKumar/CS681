package edu.umb.cs681.hw14;

import java.text.BreakIterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor
{

    private int currentVisitors;
    private ReentrantReadWriteLock rWriteLock = new ReentrantReadWriteLock();
    private Condition sufficientVisitors = rWriteLock.writeLock().newCondition();
    AdmissionMonitor(int a )
    {
        currentVisitors = a;
    }

    public void enter()
    {
        rWriteLock.writeLock().lock();
        try
        {
            if (currentVisitors < 15)
            {
                System.out.println("Current Visitors: "+currentVisitors);
                currentVisitors++;
            }
            else
            {
                System.out.println("There are more visitors please try after some time" );
                try
                {
                    sufficientVisitors.await();
                }
                catch (InterruptedException ex)
                {
                }
            }

        }
        finally
        {
            rWriteLock.writeLock().unlock();
        }
    }

    public void exit()
    {
        rWriteLock.writeLock().lock();
        try
        {
            if(currentVisitors > 0)
            {
                currentVisitors--;
                sufficientVisitors.signalAll();
            }
            else
            {

            }
        }
        finally
        {
            rWriteLock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors()
    {
        rWriteLock.readLock().lock();
        try
        {
            System.out.println("Current Visitors: " + currentVisitors);
            return currentVisitors;
        }
        finally
        {
            rWriteLock.readLock().unlock();
        }
    }

}
