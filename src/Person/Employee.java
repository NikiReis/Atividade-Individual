package Person;

import BankSystem.Account;

public class Employee extends Person {
    private final Account account;
    private final Account investmentAccount;
    private static final double employeeSalary = 1400;

    public Employee(String name, String age, Account account, Account investmentAccount) {
        super(name, age);
        this.account = account;
        this.investmentAccount = investmentAccount;
    }

    @Override
    public void run() {
        synchronized (account) {
            account.deposit(employeeSalary);
            double InvestValue = employeeSalary * 0.2;
            investmentAccount.deposit(InvestValue);
            System.out.println(getNamePerson() + " received R$" + employeeSalary + " and invested U$" + InvestValue + "\n");
        }
    }

    public static double getSalary() {
        return employeeSalary;
    }

    public Account getAccount() {
        return account;
    }

    public Account getInvestmentAccount() {
        return investmentAccount;
    }

    public void showAccountDetails() {
        System.out.println("\n Account Owner: " + getNamePerson() + " - Balance: " + account.getBalance());
        System.out.println("Investment Account Balance: " + investmentAccount.getBalance() + "\n");
    }

}
