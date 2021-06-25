package webapp.model;

import org.junit.jupiter.api.*;
import webapp.databaseDAO.ConnectToDatabase;
import webapp.databaseDAO.CustomersDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerTest {

    private static Customer customer;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();

        CustomersDAO.insert(connection,
                new Customer("Витевский Виктор Денисович","8-927-737-17-67","vitevskiy@mail.ru",3333659843L));
        customer = CustomersDAO.readData(connection, 21L);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    @Order(1)
    void getCustomerId() {
        Assertions.assertNotNull(customer.getCustomerId());
    }

    @Test
    @Order(2)
    void setCustomerId() {
        customer.setCustomerId(47L);
        Assertions.assertEquals(customer.getCustomerId(), 47L);
    }

    @Test
    @Order(3)
    void getFullName() {
        Assertions.assertEquals(customer.getFullName(), "Витевский Виктор Денисович");
    }

    @Test
    @Order(4)
    void setFullName() {
        customer.setFullName("Петров Петр Петрович");
        Assertions.assertEquals(customer.getFullName(), "Петров Петр Петрович");
    }

    @Test
    @Order(5)
    void getPhoneNumber() {
        Assertions.assertEquals(customer.getPhoneNumber(), "8-927-737-17-67");
    }

    @Test
    @Order(6)
    void setPhoneNumber() {
        customer.setPhoneNumber("8-927-737-30-67");
        Assertions.assertEquals(customer.getPhoneNumber(), "8-927-737-30-67");
    }

    @Test
    @Order(7)
    void getEmail() {
        Assertions.assertEquals(customer.getEmail(), "vitevskiy@mail.ru");
    }

    @Test
    @Order(8)
    void setEmail() {
        customer.setEmail("bobik@mail.ru");
        Assertions.assertEquals(customer.getEmail(), "bobik@mail.ru");
    }

    @Test
    @Order(9)
    void getPassportSeriesNumber() {
        Assertions.assertEquals(customer.getPassportSeriesNumber(), 3333659843L);
    }

    @Test
    @Order(10)
    void setPassportSeriesNumber() {
        customer.setPassportSeriesNumber(555555555L);
        Assertions.assertEquals(customer.getPassportSeriesNumber(), 555555555L);
    }

    @Test
    @Order(11)
    void getBankCardsList() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        customer.setBankCardsList(bankCardList);
        Assertions.assertNotNull(customer.getBankCardsList());
    }

    @Test
    @Order(12)
    void setBankCardsList() {
        List<BankCard> bankCardList = new ArrayList<>();
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCardList.add(new BankCard("3469-5400-0000-0011",329,2L,2L));
        customer.setBankCardsList(bankCardList);
        Assertions.assertNotNull(customer.getBankCardsList());
    }

    @Test
    @Order(13)
    void getAccountsList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        customer.setAccountsList(accountList);
        Assertions.assertNotNull(customer.getAccountsList());
    }

    @Test
    @Order(14)
    void setAccountsList() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        accountList.add(new Account("40817810078398700000",1761037.76,1L));
        customer.setAccountsList(accountList);
        Assertions.assertNotNull(customer.getAccountsList());
    }
}