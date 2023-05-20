package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class threadsafecofeeshop {

    private final int numBaristas;
    private final List<Order> orders = new ArrayList<>();
    private final List<Barista> baristas = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition ordersAvailable = lock.newCondition();

    public threadsafecofeeshop(int numBaristas) {
        this.numBaristas = numBaristas;

        // Create baristas
        for (int i = 0; i < numBaristas; i++) {
            baristas.add(new Barista("Barista " + (i + 1)));
        }
    }

    public void addOrder(Order order) {
        lock.lock();
        try {
            orders.add(order);
            ordersAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void start() throws InterruptedException {
        // Start all baristas
        for (Barista barista : baristas) {
            barista.start();
        }

        // Wait for all baristas to finish
        for (Barista barista : baristas) {
            barista.join();
        }
    }

    private class Barista extends Thread {
        public Barista(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("Thread-Safe");
            while (!isInterrupted()) { // Check for interruption
                Order order = null;

                // Find an order to work on
                lock.lock();
                try {
                    while (orders.isEmpty()) {
                        try {
                            ordersAvailable.await();
                        } catch (InterruptedException e) {
                            // Restore interrupted status and exit the thread
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    order = orders.remove(0);
                } finally {
                    lock.unlock();
                }

                // Prepare and serve the order
                System.out.println(getName() + " is preparing " + order.getDrinkName() + " for customer " + order.getCustomerName());
                try {
                    Thread.sleep(order.getPreparationTime());
                } catch (InterruptedException e) {
                    return;
                }
                Thread.currentThread().interrupt();
                System.out.println(getName() + " is serving " + order.getDrinkName() + " to customer " + order.getCustomerName());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        threadsafecofeeshop coffeeShop = new threadsafecofeeshop(2);
        coffeeShop.addOrder(new Order("Alice", "Latte", 5000));
        coffeeShop.addOrder(new Order("Bob", "Cappuccino", 3000));
        coffeeShop.addOrder(new Order("Charlie", "Americano", 4000));
        coffeeShop.start();
    }
}
