package edu.umb.cs681.hw12;

public interface BankAccount
{
    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
}