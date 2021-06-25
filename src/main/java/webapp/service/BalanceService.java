package webapp.service;

import webapp.databaseDAO.AccountsDAO;
import webapp.model.Account;

import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

public class BalanceService {

    public static Double getBalance(String accountNumber) throws SQLException {

        Account account = AccountsDAO.readData(connection, accountNumber);
        return account.getBalance();
    }
    public static void addToBalance(String accountNumber, Double balance) throws SQLException {

        Account account = AccountsDAO.readData(connection, accountNumber);
//        System.out.println(account.getBalance());
        account.setBalance(account.getBalance() + balance);
//        System.out.println(account.getBalance());
        AccountsDAO.update(connection, account);
    }
    public static void removeFromBalance() {

    }
}
