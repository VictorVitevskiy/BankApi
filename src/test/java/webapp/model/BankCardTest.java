package webapp.model;

import org.junit.jupiter.api.*;
import webapp.databaseDAO.BankCardsDAO;
import webapp.databaseDAO.ConnectToDatabase;

import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankCardTest {

    private static BankCard bankCard;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();

        BankCardsDAO.insert(connection,
                new BankCard("3469-5400-0000-0011",329,2L,2L));
        bankCard = BankCardsDAO.readData(connection, "3469-5400-0000-0011");
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    @Order(1)
    void getCardId() {
        Assertions.assertNotNull(bankCard.getCardId());
    }

    @Test
    @Order(2)
    void setCardId() {
        bankCard.setCardId(10L);
        Assertions.assertEquals(bankCard.getCardId(), 10L);
    }

    @Test
    @Order(3)
    void getCardNumber() {
        Assertions.assertEquals(bankCard.getCardNumber(), "3469-5400-0000-0011");
    }

    @Test
    @Order(4)
    void getAccountId() {
        Assertions.assertEquals(bankCard.getAccountId(), 2L);
    }

    @Test
    @Order(5)
    void getCvc2cvv2() {
        Assertions.assertEquals(bankCard.getCvc2cvv2(), 329);
    }

    @Test
    @Order(6)
    void getCustomerId() {
        Assertions.assertEquals(bankCard.getCustomerId(), 2L);
    }
}