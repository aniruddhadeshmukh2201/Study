package com.example.Interview_practice;

public class BankAccount {

    private double balance;

    public synchronized boolean withdraw(double amount) {
        if(balance >= amount) {
            balance -= amount;
            return true;
        } 
        return false;
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }

    public synchronized double checkBalance() {
        return balance;
    }
}
