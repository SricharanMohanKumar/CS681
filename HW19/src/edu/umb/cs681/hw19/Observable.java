package edu.umb.cs681.hw19;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable<T> {
    private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();
    private boolean changed = false;

    public void addObserver(Observer<T> ob) {
        observers.add(ob);
    }

    public void clearObservers() {
        observers.clear();
    }

    public List<Observer<T>> getObservers() {
        return new ArrayList<>(observers);
    }

    public int countObservers() {
        return observers.size();
    }

    public void removeObserver(Observer<T> ob) {
        observers.remove(ob);
    }

    public void setChanged() {
        changed = true;
    }

    public void notifyObservers(T e)
    {
        List<Observer<T>> observersCopy = new ArrayList<>(observers);
        observersCopy.forEach((observer) -> {
            observer.update(this, e);
        });
    }
}
