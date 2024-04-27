import BankSystem.*;
import Exceptions.InsufficientFundException;
import Person.*;
import Store.*;

public class Main {
    public static void main(String[] args) throws InsufficientFundException {
        // Creating the bank that will be used
        Bank bank_used = new Bank("BSB Bank", "7675843", "Asa Sul - Brasília, DF");

        // Creating and initializing arrays of people
        String[] names = {"Jack Brabham", "Wolfgang von Trips", "Graham Hill", "John Surtees", "Jim Clark", "Gilles Villeneuve", "Jacky Ickx", "Jackie Stewart", "Colin Chapman"};
        String[] ages = {"51", "27", "46", "67", "32", "32", "19", "36", "26"};
        Person[] persons = new Person[names.length];
        for (int i = 0; i < names.length; i++) {
            persons[i] = new Person(names[i], ages[i]);
        }

        // Creating and initializing arrays of employees
        Employee[][] employees = new Employee[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Account account = new Account(0, "54" + i + j, "1", bank_used, persons[i * 2 + j]);
                Account invest_account = new Account(1, "55" + i + j, "1", bank_used, persons[i * 2 + j]);
                employees[i][j] = new Employee(persons[i * 2 + j].getNamePerson(), persons[i * 2 + j].getAge(), account, invest_account);
            }
        }

        // Creating and initializing arrays of stores
        Store[] stores = new Store[2];
        for (int i = 0; i < 2; i++) {
            Account store_account = new Account(0, "543" + i, "1", bank_used, null);
            stores[i] = new Store("Bijuteria Express", employees[i], store_account);
        }

        // Creating and initializing arrays of clients
        Client[] clients = new Client[5];
        for (int i = 0; i < 5; i++) {
            Account client_account = new Account(1000, "62" + i, "1", bank_used, persons[i + 4]);
            clients[i] = new Client(persons[i + 4].getNamePerson(), persons[i + 4].getAge(), client_account, bank_used, stores);
        }

        // Starting client threads
        for (Client client : clients) {
            client.start();
        }

        // Waiting for client threads to finish
        for (Client client : clients) {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Paying store employees
        for (Store store : stores) {
            store.payEmployees();
        }

        // Displaying final balance of clients
        bank_used.ShowFundsClients(clients);
        bank_used.ShowFundsStores(stores);
        for (Employee employee : employees[1]) {
            employee.showAccountDetails();
        }
    }
}
