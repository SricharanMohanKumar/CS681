package edu.umb.cs681.hw15;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T>
{
    private LinkedList<Observer<T>> observers = new LinkedList<>();
    private ReentrantLock lock = new ReentrantLock();
    private boolean changed = false;

    public void addObserver(Observer<T> ob)
    {
        lock.lock();
        try
        {
            observers.add(ob);
        }
        finally
        {
            lock.unlock();
        }
    }

    public void clearObservers()
    {
        lock.lock();
        try
        {
            observers.clear();
        }
        finally
        {
            lock.unlock();
        }
    }
    public List<Observer<T>> getObservers()
    {
        return observers;
    }

    public int countObservers()
    {
        lock.lock();
        try
        {
            return observers.size();
        }
        finally
        {
            lock.unlock();
        }
    }
    public void removeObserver(Observer<T> ob)
    {
        lock.lock();
        try
        {
            observers.remove(ob);
        }
        finally
        {
            lock.unlock();
        }
    }

    public void setChanged()
    {
        lock.lock();
        try
        {
            changed = true;
        }
        finally
        {
            lock.unlock();
        }
    }


    public void notifyObservers(T e)
    {
        LinkedList<Observer<T>> observersCopy = new LinkedList<>();
        lock.lock();
        try
        {
            observersCopy = (LinkedList<Observer<T>>) observers.clone();
        }
        finally
        {
            lock.unlock();
        }
        observersCopy.forEach( (observer)->{observer.update(this, e);} );
    }

}
