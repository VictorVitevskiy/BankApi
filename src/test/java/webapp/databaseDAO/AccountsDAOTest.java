package webapp.databaseDAO;

import org.junit.jupiter.api.*;
import webapp.model.Account;

import java.sql.SQLException;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountsDAOTest {

    private static Account account;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
        account = new Account("40817810078398700000",1761037.76,1L);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    void insert() throws SQLException {
        AccountsDAO.insert(connection, account);
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        Assertions.assertEquals(account.getAccountNumber(), accountTest.getAccountNumber());
    }

    @Test
    void readAllData() throws SQLException {
        List<Account> accountList = AccountsDAO.readAllData(connection);
        Assertions.assertNotNull(accountList);
    }

    @Test
    void readData() throws SQLException {
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        Assertions.assertEquals(accountTest.getBalance(), 1761037.76);
    }

    @Test
    void update() throws SQLException {
        account = AccountsDAO.readData(connection, "40817810078398700000");
        account.setBalance(99999999.0);
        AccountsDAO.update(connection, account);
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        Assertions.assertEquals(accountTest.getBalance(), 99999999.0);
    }

    @Test
    @Order(1)
    void deleteData() throws SQLException {
        AccountsDAO.insert(connection, account);
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        AccountsDAO.deleteData(connection,accountTest);

        Assertions.assertNull(AccountsDAO.readData(connection, "40817810078398700000"));
    }
}