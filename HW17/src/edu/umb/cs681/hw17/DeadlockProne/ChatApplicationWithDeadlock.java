package edu.umb.cs681.hw17.DeadlockProne;
import java.util.ArrayList;
import java.util.List;

public class ChatApplicationWithDeadlock
{
    private List<User> users;

    public ChatApplicationWithDeadlock()
    {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void sendMessage(User sender, User receiver, String message) {
        if (users.contains(sender) && users.contains(receiver)) {
            sender.getLock().lock();
            receiver.getLock().lock();
            sender.send(message);
            receiver.receive(message);
            receiver.getLock().unlock();
            sender.getLock().unlock();
        }
        else
        {
            System.out.println("Error: Invalid sender or receiver");
        }
    }
}