package edu.umb.cs681.hw16;
import java.util.ArrayList;
import java.util.List;

public class Nonthreadsafecofeeshop {

    private final int numBaristas;
    private final List<Order> orders = new ArrayList<>();
    private final List<Barista> baristas = new ArrayList<>();

    public Nonthreadsafecofeeshop(int numBaristas) {
        this.numBaristas = numBaristas;

        // Create baristas
        for (int i = 0; i < numBaristas; i++) {
            baristas.add(new Barista("Barista " + (i + 1)));
        }
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void start() {
        // Start all baristas
        for (Barista barista : baristas) {
            barista.start();
        }

        // Wait for all baristas to finish
        for (Barista barista : baristas) {
            try {
                barista.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Barista extends Thread {
        public Barista(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("Non-Thread-Safe");
            while (!isInterrupted()) { // Check for interruption
                Order order = null;

                // Find an order to work on
                if (!orders.isEmpty()) {
                    order = orders.remove(0);
                }

                if (order != null) {
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
    }

    public static void main(String[] args) {
        Nonthreadsafecofeeshop coffeeShop = new Nonthreadsafecofeeshop(2);
        coffeeShop.addOrder(new Order("Alice", "Latte", 5000));
        coffeeShop.addOrder(new Order("Bob", "Cappuccino", 3000));
        coffeeShop.addOrder(new Order("Charlie", "Americano", 4000));
        coffeeShop.start();
    }
}
