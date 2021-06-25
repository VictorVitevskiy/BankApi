package webapp.databaseDAO;

import webapp.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomersDAO {

    public void insert(Connection connection, Customer customer) throws SQLException {

        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO Customers (fullName, phoneNumber, email, passportSeriesNumber) VALUES (?, ?, ?, ?)");
        insertStatement.setString(1, customer.getFullName());
        insertStatement.setString(2, customer.getPhoneNumber());
        insertStatement.setString(3, customer.getEmail());
        insertStatement.setLong(4, customer.getPassportSeriesNumber());
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    public List<Customer> readAllData(Connection connection) throws SQLException {

        List<Customer> customersList = new ArrayList<>();
        PreparedStatement readAllStatement = connection.prepareStatement("SELECT * FROM Customers");
        ResultSet resultSet = readAllStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("fullName"),
                    resultSet.getString("phoneNumber"),
                    resultSet.getString("email"), resultSet.getLong("passportSeriesNumber"));
            customer.setCustomerId(resultSet.getLong("id"));
            customersList.add(customer);
        }
        readAllStatement.close();
        resultSet.close();
        return customersList;
    }

    public Customer readData(Connection connection, Long customerID) throws SQLException {

        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Customers;");
        ResultSet resultSet = readStatement.executeQuery();
        Customer customer = null;
        while (resultSet.next()) {
            if (resultSet.getLong("id") == customerID) {
                customer = new Customer(resultSet.getString("fullName"),
                        resultSet.getString("phoneNumber"), resultSet.getString("email"),
                        resultSet.getLong("passportSeriesNumber"));
                customer.setCustomerId(resultSet.getLong("id"));
            }
        }
        readStatement.close();
        resultSet.close();
        return customer;
    }

    public void update(Connection connection, Customer customer) throws SQLException {

        PreparedStatement updateStatement = connection.prepareStatement(
                "UPDATE Customers SET fullName = ?, phoneNumber = ?, email = ?, passportSeriesNumber = ? WHERE id = ?");

        updateStatement.setString(1, customer.getFullName());
        updateStatement.setString(2, customer.getPhoneNumber());
        updateStatement.setString(3, customer.getEmail());
        updateStatement.setLong(4, customer.getPassportSeriesNumber());
        updateStatement.setLong(5, customer.getCustomerId());
        updateStatement.executeUpdate();
        updateStatement.close();
    }

    public void deleteData(Connection connection, Customer customer) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Customers WHERE id = ?");
        deleteStatement.setLong(1, customer.getCustomerId());
        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
}
