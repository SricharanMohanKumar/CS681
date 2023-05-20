package edu.umb.cs681.hw17.NonDeadLockProne;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class ChatApplicationWithOutDeadlock
{
    private List<User> users;
    private ReentrantLock lock = new ReentrantLock();

    public ChatApplicationWithOutDeadlock() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void sendMessage(User sender, User receiver, String message) {
        User firstUser = sender.getId() < receiver.getId() ? sender : receiver;
        User secondUser = sender.getId() < receiver.getId() ? receiver : sender;

        firstUser.lock.lock();
        secondUser.lock.lock();
        lock.lock();
        try
        {
            sender.send(message);
            receiver.receive(message);
        }
        finally
        {
            firstUser.lock.unlock();
            secondUser.lock.unlock();
            lock.unlock();
        }
    }
}
