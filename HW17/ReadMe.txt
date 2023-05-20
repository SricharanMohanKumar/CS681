
>>> What kind of app you consider

The Application that I have considered is Chat Application that allows users to send and receive messages to each other.
It consists of a ChatApplication class that manages the users and facilitates message sending between them,
and a User class that represents individual users.



>>> Why the original code is not thread-safe

In the current implementation, when a message is being sent between two users, the locks are acquired.
However, in the main method, two threads are created: thread1 and thread2, which execute chatApp.sendMessage concurrently.

Consider the following scenario:
thread1 executes chatApp.sendMessage(user1, user2, "Hello from Alice") and acquires the lock on user1.
thread2 executes chatApp.sendMessage(user2, user1, "Hi from Bob") and acquires the lock on user2.

Now, both threads are waiting for the other's lock to be released, resulting in a deadlock.
Neither thread can proceed further, and the program hangs indefinitely.



>>> How you revised it to be thread-safe.

The ChatApplication class introduces an ReentrantLock.
By acquiring this lock before acquiring the locks of the sender and receiver, we prevent multiple threads from entering the
critical section simultaneously.

By acquiring locks and using the ReentrantLock implementation, we ensure that threads do not enter the critical section if
another thread already holds any of the required locks.
This prevents deadlocks and ensures that only one thread can execute the critical section at a time.
