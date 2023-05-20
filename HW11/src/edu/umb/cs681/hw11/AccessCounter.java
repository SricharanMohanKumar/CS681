package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter
{
    private static AccessCounter instance = null;
    private ReentrantLock NSlock = new ReentrantLock();
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
        NSlock.lock();
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
            NSlock.unlock();
        }
    }
    public int getCount(Path path)
    {
        NSlock.lock();
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
            NSlock.unlock();
        }
    }

}