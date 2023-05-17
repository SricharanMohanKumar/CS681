package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Aircraft
{

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Position position;

    public Aircraft(Position position)
    {
        this.position = position;
    }

    public Position getPosition()
    {
        lock.readLock().lock();
        try
        {
            return position;
        }
        finally
        {
            lock.readLock().unlock();
        }
    }

    public void setPosition(double latitude, double longitude, double altitude)
    {
        lock.writeLock().lock();
        try
        {
            position = new Position(latitude, longitude, altitude);
        }
        finally
        {
            lock.writeLock().unlock();
        }
    }
}