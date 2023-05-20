package edu.umb.cs681.hw17.NonDeadLockProne;

import java.util.concurrent.locks.ReentrantLock;

public class User
{
    private static int idCounter = 0;
    private int id;
    private String name;
    ReentrantLock lock = new ReentrantLock();

    public User(String name)
    {
        this.id = ++idCounter;
        this.name = name;
        this.lock = new ReentrantLock();
    }

    public int getId() {
        return id;
    }

    public void send(String message) {
        System.out.println(name + " sent: " + message);
    }

    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}
