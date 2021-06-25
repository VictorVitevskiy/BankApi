package webapp.model;

import org.junit.jupiter.api.*;
import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.ConnectToDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountTest {

    private static Account account;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();

        AccountsDAO.insert(connection,
                new Account("40817810078398700000",1761037.76,1L));
        account = AccountsDAO.readData(connection, "40817810078398700000");
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    @Order(1)
    void getId() {
        Assertions.assertNotNull(account.getAccountId());
    }

    @Test
    @Order(2)
    void getAccountNumber() {
        Assertions.assertEquals(account.getAccountNumber(), "40817810078398700000");
    }

    @Test
    @Order(3)
    void getBalance() {
        Assertions.assertEquals(account.getBalance(), 1761037.76);
    }

    @Test
    @Order(4)
    void getCustomerId() {
        Assertions.assertEquals(account.getCustomerId(), 1L);
    }

    @Test
    @Order(5)
    void setId() {
        account.setAccountId(10L);
        Assertions.assertEquals(account.getAccountId(), 10L);
    }

    @Test
    @Order(6)
    void setBalance() {
        account.setBalance(2000.0);
        Assertions.assertEquals(account.getBalance(), 2000.0);
    }

    @Test
    @Order(7)
    void setCustomerId() {
        account.setCustomerId(18L);
        Assertions.assertEquals(account.getCustomerId(), 18L);
    }

    @Test
    @Order(8)
    void getCardList() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        account.setCardList(bankCardList);
        Assertions.assertNotNull(account.getCardList());
    }

    @Test
    @Order(9)
    void setCardList() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        account.setCardList(bankCardList);
        Assertions.assertNotNull(account.getCardList());
    }
}