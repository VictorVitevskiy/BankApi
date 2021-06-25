package webapp.service;

import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.BankCardsDAO;
import webapp.model.Bank;
import webapp.model.BankCard;

import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

public class BankCardService {
    public static void getAllBankCards(Long customerID) {

    }
    public static void createNewBankCard(String account) throws SQLException {

        BankCard bankCard = Bank.cardGenerator(AccountsDAO.readData(connection, account));
        BankCardsDAO.insert(connection, bankCard);
    }
    public static void removeBankCard() {

    }
}
