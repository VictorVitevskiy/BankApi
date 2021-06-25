package webapp.service;

import org.junit.jupiter.api.*;
import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.ConnectToDatabase;
import webapp.model.Account;

import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BalanceServiceTest {

    private static Account account;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
        account = new Account("40817810078398700000",1761037.76,1L);
        AccountsDAO.insert(connection, account);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    @Order(1)
    void getBalance() throws SQLException {
        Double balanceTest = BalanceService.getBalance(account.getAccountNumber());
        Assertions.assertEquals(balanceTest, 1761037.76);
    }

    @Test
    @Order(2)
    void addToBalance() throws SQLException {
        BalanceService.addToBalance(account.getAccountNumber(), 192754.0);
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        Assertions.assertNotEquals(accountTest.getBalance(), 1761037.76);

    }
}