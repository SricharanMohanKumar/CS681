package edu.umb.cs681.hw16;

public class Order {
    private final String customerName;
    private final String drinkName;
    private final int preparationTime;

    public Order(String customerName, String drinkName, int preparationTime)
    {
        this.customerName = customerName;
        this.drinkName = drinkName;
        this.preparationTime = preparationTime;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public int getPreparationTime() {
        return preparationTime;
    }
}
