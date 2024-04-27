package Store;

import BankSystem.Account;
import Exceptions.InsufficientFundException;
import Person.Employee;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    String Name;
    private final Employee[] employees;
    private final Account account;
    private final Lock paymentLock = new ReentrantLock();
    int StoreBalance = 0;

    public Store(String Name, Employee[] employees, Account account) {
        this.Name = Name;
        this.employees = employees;
        this.account = account;
    }

    public String getNameStore() {
        return Name;
    }

    public Account getAccount() {
        return account;
    }

    public void payEmployees() throws InsufficientFundException {
        paymentLock.lock();
        try {
            System.out.println("\nPaying employees of " + Name);
            synchronized (account) {
                double totalSalary = 0;
                for (Employee employee : employees) {
                    totalSalary += Employee.getSalary();
                }

                // Check whether there are sufficient funds in the store account to pay wages
                if (account.getBalance() >= totalSalary) {
                    for (Employee employee : employees) {
                        account.withdraw(Employee.getSalary());
                        System.out.println("Employee " + employee.getNamePerson() + " from store " + Name + " received salary of R$" + Employee.getSalary() + "\n");
                    }
                } else {
                    // Calculate the salary share for each employee based on the available balance
                    for (Employee employee : employees) {
                        double salaryShare = (account.getBalance() / totalSalary) * Employee.getSalary();
                        account.withdraw(salaryShare);
                        System.out.println("Employee " + employee.getNamePerson() + " from store " + Name + " received salary of R$" + salaryShare + "\n");
                    }
                }
                StoreBalance = 0;
            }
        } finally {
            paymentLock.unlock();
        }
    }
}