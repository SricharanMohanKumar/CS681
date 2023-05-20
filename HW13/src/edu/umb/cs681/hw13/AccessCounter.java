package edu.umb.cs681.hw13;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter
{
    private static AccessCounter instance = null;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static ReentrantLock Slock = new ReentrantLock();
    HashMap<Path, Integer> Hmap = new HashMap<Path, Integer>();
    public static AccessCounter getInstance()
    {
        Slock.lock();
        try
        {
            if (instance == null)
            {
                instance = new AccessCounter();
            }
            return instance;
        }
        finally
        {
            Slock.unlock();
        }
    }

    public void increment(Path path)
    {
        rwLock.writeLock().lock();
        try
        {
            if (Hmap.containsKey(path))
            {
                Hmap.put(path, Hmap.get(path)+1);
            }
            else
            {
                Hmap.put(path, 1);
            }
        }
        finally
        {
            rwLock.writeLock().unlock();
        }
    }
    public int getCount(Path path)
    {
        rwLock.readLock().lock();
        try
        {
            if (Hmap.containsKey(path))
            {
                return Hmap.get(path);
            }
            else
            {
                return 0;
            }
        }
        finally
        {
            rwLock.readLock().unlock();
        }
    }

}