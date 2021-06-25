package webapp.databaseDAO;

import org.junit.jupiter.api.*;
import webapp.model.BankCard;

import java.sql.SQLException;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BankCardsDAOTest {

    private static BankCard bankCard;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
        bankCard = new BankCard("3469-5400-0000-0011",329,2L,2L);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    void insert() throws SQLException {
        BankCardsDAO.insert(connection, bankCard);
        BankCard cardTest = BankCardsDAO.readData(connection, "3469-5400-0000-0011");
        Assertions.assertEquals(cardTest.getCardNumber(), "3469-5400-0000-0011");
    }

    @Test
    void readAllData() throws SQLException {
        List<BankCard> bankCardList = BankCardsDAO.readAllData(connection);
        Assertions.assertNotNull(bankCardList);
    }

    @Test
    void readData() throws SQLException {
        BankCardsDAO.insert(connection, bankCard);
        BankCard cardTest = BankCardsDAO.readData(connection, "3469-5400-0000-0011");
        Assertions.assertEquals(cardTest.getCvc2cvv2(), 329);
    }

    @Test
    @Order(1)
    void deleteData() throws SQLException {
        BankCardsDAO.insert(connection, bankCard);
        BankCard cardTest = BankCardsDAO.readData(connection, "3469-5400-0000-0011");
        BankCardsDAO.deleteData(connection,cardTest);

        Assertions.assertNull(BankCardsDAO.readData(connection, "3469-5400-0000-0011"));
    }
}