package edu.umb.cs681.hw15;


public interface Observer<T>
{
    public void update(Observable<T> s, T e);
}
