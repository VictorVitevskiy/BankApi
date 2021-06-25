package webapp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.BankCardsDAO;
import webapp.databaseDAO.ConnectToDatabase;
import webapp.databaseDAO.CustomersDAO;
import webapp.model.Account;
import webapp.model.Bank;
import webapp.model.BankCard;
import webapp.model.Customer;

import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

class CustomerServiceTest {

    private static Customer customer;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
        customer = new Customer("Витевский Виктор Денисович",
                "8-927-737-17-67", "vitevskiy@mail.ru", 6666666666L);
        CustomersDAO.insert(connection, customer);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    void getCustomersBankCards() throws SQLException {
        Customer customerTest = CustomersDAO.readData(connection, 21L);
        Account accountTest = Bank.accountGenerator(customerTest);
        AccountsDAO.insert(connection, accountTest);
        BankCard cardTest = Bank.cardGenerator(AccountsDAO.readData(connection, accountTest.getAccountNumber()));
        BankCardsDAO.insert(connection, cardTest);
        customerTest = CustomerService.getCustomersBankCards(customerTest.getCustomerId());
        Assertions.assertNotNull(customerTest.getBankCardsList());
    }
}