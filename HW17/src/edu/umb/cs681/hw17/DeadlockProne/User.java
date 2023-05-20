package edu.umb.cs681.hw17.DeadlockProne;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class User {
    private String name;
    private Lock lock;

    public User(String name) {
        this.name = name;
        this.lock = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    public void send(String message) {
        System.out.println(name + " sent: " + message);
    }

    public void receive(String message) {
        System.out.println(name + " received: " + message);
    }
}



