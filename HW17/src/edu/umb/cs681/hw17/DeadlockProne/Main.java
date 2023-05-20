package edu.umb.cs681.hw17.DeadlockProne;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Deadlock Prone app\n");
        ChatApplicationWithDeadlock chatApp = new ChatApplicationWithDeadlock();
        User user1 = new User("Alice");
        User user2 = new User("Bob");
        User user3 = new User("Charlie");

        chatApp.addUser(user1);
        chatApp.addUser(user2);
        chatApp.addUser(user3);

        Thread thread1 = new Thread(() -> chatApp.sendMessage(user1, user2, "Hello from Alice"));
        Thread thread2 = new Thread(() -> chatApp.sendMessage(user2, user1, "Hi from Bob"));

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
