package BankSystem;
import Store.*;
import Exceptions.InsufficientFundException;
import Person.Client;
import Person.Employee;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final Lock lock = new ReentrantLock();
    String BankName;
    String Cnpj;
    String Address;
    private double value_transfered = 0;

    public Bank(String BankName, String Cnpj, String Address) {
        this.BankName = BankName;
        this.Cnpj = Cnpj;
        this.Address = Address;
    }

    public double Total_Transfered() {
        return value_transfered;
    }

    public void transfer(Account origin_account, Account destiny_account, double value) throws InsufficientFundException {
        lock.lock();
        try {
            if (origin_account.getBalance() >= value) {
                synchronized (origin_account) {
                    origin_account.withdraw(value);
                }
                synchronized (destiny_account) {
                    destiny_account.deposit(value);
                }
                value_transfered += value;
                System.out.println("\n Transfer of R$" + value + " carried out successfully.");
            } else {
                throw new InsufficientFundException("Insufficient found to withdraw: " + value);
            }
        } finally {
            lock.unlock();
        }
    }

    public void ShowFundsClients(Client[] clients) {
        System.out.println("\nFinal balance:");
        for (Client client : clients) {
            System.out.println("\n Account Owner: " + client.getNamePerson() + " - Balance: " + client.getAccount().getBalance());
        }
    }

    public void ShowFundsEmployess(Employee[] employees) {
        System.out.println("\nFinal balance:");
        for (Employee employee : employees) {
            System.out.println("\n Account Owner: " + employee.getNamePerson() + " - Balance: " + employee.getAccount().getBalance());
            System.out.println("Investment Account  Balance: " + employee.getAccount().getBalance() + "\n");
        }
    }

    public void ShowFundsStores(Store[] stores) {
        System.out.println("\nFinal balance:");
        for (Store store : stores) {
            System.out.println("\n Account Owner: " + store.getNameStore() + " - Balance: " + store.getAccount().getBalance());
        }
    }
}