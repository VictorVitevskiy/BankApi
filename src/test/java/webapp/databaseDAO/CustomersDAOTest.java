package webapp.databaseDAO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webapp.model.Customer;

import java.sql.SQLException;
import java.util.List;

import static webapp.databaseDAO.ConnectToDatabase.connection;

class CustomersDAOTest {

    private static Customer customer;

    @BeforeAll
    public static void beforeAll() throws SQLException {
        ConnectToDatabase.databaseConnection();
        customer = new Customer("Витевский Виктор Денисович",
                "8-927-737-17-67", "vitevskiy@mail.ru", 6666666666L);
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        connection.close();
    }

    @Test
    void insert() throws SQLException {
        CustomersDAO.insert(connection, customer);
        Customer customerTest = CustomersDAO.readData(connection, 21L);
        Assertions.assertEquals(customerTest.getFullName(), "Витевский Виктор Денисович");
    }

    @Test
    void readAllData() throws SQLException {
        List<Customer> customerList = CustomersDAO.readAllData(connection);
        Assertions.assertNotNull(customerList);
    }

    @Test
    void readData() throws SQLException {
        Customer customerTest = CustomersDAO.readData(connection, 21L);
        Assertions.assertEquals(customerTest.getEmail(), "vitevskiy@mail.ru");
    }

    @Test
    void update() throws SQLException {
        customer = CustomersDAO.readData(connection, 21L);
        customer.setEmail("vit@mail.ru");
        CustomersDAO.update(connection, customer);
        Customer customerTest = CustomersDAO.readData(connection, 21L);
        Assertions.assertEquals(customerTest.getEmail(), "vit@mail.ru");
    }

    @Test
    void deleteData() throws SQLException {
        CustomersDAO.insert(connection, customer);
        Customer customerTest = CustomersDAO.readData(connection, 21L);
        CustomersDAO.deleteData(connection,customerTest);

        Assertions.assertNull(CustomersDAO.readData(connection, 21L));
    }
}