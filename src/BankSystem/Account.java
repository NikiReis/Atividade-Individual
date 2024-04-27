package BankSystem;

import Person.Person;

public class Account {
    private double balance;
    private final String accountNumber;
    private final String Agency;
    private final Bank bank;

    public Account(int balance, String accountNumber, String Agency, Bank bank, Person owner) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.Agency = Agency;
        this.bank = bank;
    }

    public synchronized void deposit(double balance) {
        this.balance += balance;
    }

    public synchronized void withdraw(double balance) {
        this.balance -= balance;
    }

    public double getBalance() {
        return balance;
    }

}