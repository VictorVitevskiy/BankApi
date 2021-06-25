package webapp.databaseDAO;

import webapp.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AccountsDAO {

    public void insert(Connection connection, Account account) throws SQLException {

        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO Accounts (account, balance, customerId) VALUES (?, ?, ?)");
        insertStatement.setString(1, account.getAccountNumber());
        insertStatement.setDouble(2, account.getBalance());
        insertStatement.setLong(3, account.getCustomerId());
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    public List<Account> readAllData(Connection connection) throws SQLException {

        List<Account> accountsList = new ArrayList<>();
        PreparedStatement readAllStatement = connection.prepareStatement("SELECT * FROM Accounts");
        ResultSet resultSet = readAllStatement.executeQuery();
        while (resultSet.next()) {

            Account account = new Account(resultSet.getString("account"),
                    resultSet.getDouble("balance"), resultSet.getLong("customerId"));
            account.setAccountId(resultSet.getLong("id"));
            accountsList.add(account);
        }
        readAllStatement.close();
        resultSet.close();
        return accountsList;
    }

    public Account readData(Connection connection, String accountNumber) throws SQLException {

        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Accounts");
        ResultSet resultSet = readStatement.executeQuery();
        Account account = null;
        while (resultSet.next()) {
            if (accountNumber.equals(resultSet.getString("account"))) {
                account = new Account(resultSet.getString("account"),
                        resultSet.getDouble("balance"), resultSet.getLong("customerId"));
                account.setAccountId(resultSet.getLong("id"));
            }
        }
        readStatement.close();
        resultSet.close();
        return account;
    }

    public void update(Connection connection, Account account) throws SQLException {

        PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE Accounts SET account = ?, balance = ?, customerId = ? WHERE id = ?");

        updateStatement.setString(1, account.getAccountNumber());
        updateStatement.setDouble(2, account.getBalance());
        updateStatement.setLong(3, account.getCustomerId());
        updateStatement.setLong(4, account.getAccountId());
        updateStatement.executeUpdate();
        updateStatement.close();
    }

    public void deleteData(Connection connection, Account account) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Accounts WHERE id = ?");
        deleteStatement.setLong(1, account.getAccountId());
        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
}
