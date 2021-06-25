package webapp.service;

import webapp.databaseDAO.CustomersDAO;
import webapp.model.BankCard;
import webapp.model.Customer;

import java.sql.SQLException;
import java.util.List;

import static webapp.databaseDAO.BankCardsDAO.readAllData;
import static webapp.databaseDAO.ConnectToDatabase.connection;
public class CustomerService {

    public static void getAllCustomer() {

    }
    public static void createNewCustomer() {

    }
    public static void getCustomerCustomer() {

    }
    public static Customer getCustomersBankCards(Long customerId) throws SQLException {
        Customer customer = CustomersDAO.readData(connection, customerId);
        List<BankCard> bankCardsList = readAllData(connection);
        bankCardsList.removeIf(bankCard -> !bankCard.getCustomerId().equals(customerId));
        customer.setBankCardsList(bankCardsList);
        return customer;
    }
    public static void removeCustomer() {

    }
}
