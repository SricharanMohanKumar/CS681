package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

public class AccessCounter
{
    private static AccessCounter instance = null;
    private ConcurrentHashMap<Path, Integer> HM = new ConcurrentHashMap<>();

    private AccessCounter() {}

    public static AccessCounter getInstance()
    {
        if (instance == null)
        {
            synchronized (AccessCounter.class)
            {
                if (instance == null)
                {
                    instance = new AccessCounter();
                }
            }
        }
        return instance;
    }

    public void increment(Path path)
    {
        HM.compute(path, (key, value) -> (value == null) ? 1 : value + 1);
    }

    public int getCount(Path path)
    {
        return HM.getOrDefault(path, 0);
    }
}
