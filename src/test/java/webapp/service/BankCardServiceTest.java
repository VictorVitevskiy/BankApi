package webapp.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.BankCardsDAO;
import webapp.databaseDAO.ConnectToDatabase;
import webapp.model.Account;
import webapp.model.BankCard;

import java.sql.SQLException;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

class BankCardServiceTest {

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
    void createNewBankCard() throws SQLException {
        Account accountTest = AccountsDAO.readData(connection, "40817810078398700000");
        BankCardService.createNewBankCard(accountTest.getAccountNumber());
        List<BankCard> bankCardList = BankCardsDAO.readAllData(connection);
        BankCard cardTest = null;
        for (BankCard bankCard : bankCardList) {
            if (accountTest.getAccountId().equals(bankCard.getAccountId())) {
                cardTest = bankCard;
            }
        }
        Assertions.assertNotNull(cardTest);
    }
}