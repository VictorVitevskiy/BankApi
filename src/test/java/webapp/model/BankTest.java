package webapp.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webapp.databaseDAO.ConnectToDatabase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import static webapp.databaseDAO.ConnectToDatabase.connection;

class BankTest {

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
    }
    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    void cardGenerator() {
        Account account = new Account("40817810078398700000",1761037.76,1L);
        BankCard bankCard = Bank.cardGenerator(account);
        Assertions.assertNotNull(bankCard);
    }

    @Test
    void accountGenerator() {
        Customer customer = new Customer("Мельникова Ксения Витальевна","8-910-635-99-09",
                "melnikova@mail.ru",75048533257L);
        Account account = Bank.accountGenerator(customer);
        Assertions.assertNotNull(account);
    }

    @Test
    void cardNumberGenerator() {

        Bank bank = new Bank();
        try {
            Method method = bank.getClass().getDeclaredMethod("cardNumberGenerator");
            method.setAccessible(true);
            String s = (String) method.invoke(bank);
            Assertions.assertNotNull(s);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    void accountNumberGenerator() {

        Bank bank = new Bank();
        try {
            Class<?>[] paramTypes;
            Object[] args = new Object[] {(int) 1, (int)1};
            paramTypes = new Class[] {int.class, int.class};
            Method method = bank.getClass().getDeclaredMethod("accountNumberGenerator", paramTypes);
            method.setAccessible(true);

            String s = (String) method.invoke(bank,args);
            Assertions.assertNotNull(s);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}