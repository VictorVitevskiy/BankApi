package webapp.databaseDAO;

import webapp.model.BankCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BankCardsDAO {

    public void insert(Connection connection, BankCard bankCard) throws SQLException {

        PreparedStatement insertStatement = connection.prepareStatement(
                "INSERT INTO Bank_Cards (cardNumber, cvc2cvv2, accountId, customerId) VALUES (?, ?, ?, ?)");
        insertStatement.setString(1, bankCard.getCardNumber());
        insertStatement.setInt(2, bankCard.getCvc2cvv2());
        insertStatement.setLong(3, bankCard.getAccountId());
        insertStatement.setLong(4, bankCard.getCustomerId());
        insertStatement.executeUpdate();
        insertStatement.close();
    }

    public List<BankCard> readAllData(Connection connection) throws SQLException {

        List<BankCard> bankCardList = new ArrayList<>();
        PreparedStatement readAllStatement = connection.prepareStatement("SELECT * FROM Bank_Cards");
        ResultSet resultSet = readAllStatement.executeQuery();

        while (resultSet.next()) {
            BankCard bankCard = new BankCard(resultSet.getString("cardNumber"),
                    resultSet.getInt("cvc2cvv2"),
                    resultSet.getLong("accountId"), resultSet.getLong("customerId"));
            bankCard.setCardId(resultSet.getLong("id"));
            bankCardList.add(bankCard);
        }
        readAllStatement.close();
        resultSet.close();
        return bankCardList;
    }

    public BankCard readData(Connection connection, String cardNumber) throws SQLException {

        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM Bank_Cards");
        ResultSet resultSet = readStatement.executeQuery();
        BankCard bankCard = null;
        while (resultSet.next()) {
            if (cardNumber.equals(resultSet.getString("cardNumber"))) {
                bankCard = new BankCard(resultSet.getString("cardNumber"),
                        resultSet.getInt("cvc2cvv2"), resultSet.getLong("accountId"),
                        resultSet.getLong("customerId"));
                bankCard.setCardId(resultSet.getLong("id"));
            }
        }
        readStatement.close();
        resultSet.close();
        return bankCard;
    }

    public static void update(Connection connection, BankCard bankCard) throws SQLException {

        PreparedStatement updateStatement = connection
                .prepareStatement("UPDATE Bank_Cards SET cardNumber = ?, cvc2cvv2 = ?, accountId = ?, customerId = ? WHERE id = ?");

        updateStatement.setString(1, bankCard.getCardNumber());
        updateStatement.setInt(2, bankCard.getCvc2cvv2());
        updateStatement.setLong(3, bankCard.getAccountId());
        updateStatement.setLong(4, bankCard.getCustomerId());
        updateStatement.setLong(5, bankCard.getCardId());
        updateStatement.executeUpdate();
        updateStatement.close();
    }

    public void deleteData(Connection connection, BankCard bankCard) throws SQLException {
        PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Bank_Cards WHERE id = ?");
        deleteStatement.setLong(1, bankCard.getCardId());
        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
}
