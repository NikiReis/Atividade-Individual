package Person;

import BankSystem.Account;
import BankSystem.Bank;
import Exceptions.InsufficientFundException;
import Store.Store;

public class Client extends Person {
    private final Account account;
    private final Bank bank;
    private final Store[] stores;

    public Client(String name, String age, Account account, Bank bank, Store[] stores) {
        super(name, age);
        this.account = account;
        this.bank = bank;
        this.stores = stores;
    }


    @Override
    public void run() {
        while (account.getBalance() > 0) {
            Store store = stores[(int) (Math.random() * stores.length)];
            double purchase_value = Math.random() < 0.5 ? 100 : 200;

            // Check balance before acquiring store lock
            if (account.getBalance() >= purchase_value) {
                try {
                    synchronized (store) {
                        bank.transfer(account, store.getAccount(), purchase_value);
                        System.out.println("\n" + Name + " made a purchase of R$" + purchase_value + " in " + store.getNameStore() + ". Remaining found: R$ " + account.getBalance());
                    }
                } catch (InsufficientFundException e) {
                    // Re-throw the original exception
                    throw new RuntimeException(e);
                }
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getNamePerson() + " ran out of balance. Purchases have ended.\n");
    }

    public Account getAccount() {
        return account;
    }
}

