package webapp.databaseDAO;

import webapp.model.Account;
import webapp.model.Bank;
import webapp.model.BankCard;
import webapp.model.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static webapp.model.Bank.accountGenerator;

public class ConnectToDatabase {

    public static final String DB_URL = "jdbc:h2:mem:clientDatabase";
    public static final String DB_Driver = "org.h2.Driver";
    public static final String DB_USER = "Admin";
    public static final String DB_PASSWORD = "12345";
    public static Connection connection;

    public static void databaseConnection() {


        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); ;//соединениесБД
            System.out.println("Connecting to database success");
            Scanner scanner = new Scanner(Objects.requireNonNull(ConnectToDatabase.class.getClassLoader().getResourceAsStream("tables.sql")));
            Statement statement = connection.createStatement();

            while (scanner.hasNextLine()) {
                statement.execute(scanner.nextLine());
            }
            initialData();

        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // обработка ошибки  Class.forName
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initialData() throws SQLException, IOException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("CustomersData.csv");
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        for (String line; (line = reader.readLine()) != null;) {
            String[] data = line.split(";");
            Customer customer = new Customer(data[0], data[1], data[2], Long.parseLong(data[3]));
            CustomersDAO.insert(connection, customer);
        }
        is.close();
        streamReader.close();
        reader.close();

        List<Customer> customerList = CustomersDAO.readAllData(connection);

        for (Customer customer : customerList) {
            int random;
            do {
                random = (Math.random() > 0.5 ? 1 : 2);
                AccountsDAO.insert(connection, accountGenerator(customer)); ;
            } while (random != 1);
        }

        List<Account> accounts = AccountsDAO.readAllData(connection);

        for (Account account : accounts) {
            System.out.println(account);
        }

        for (Account account : accounts) {
            int random = (Math.random() > 0.7 ? 1 : 2);
            if (account.getAccountNumber().substring(0, 3).equals("423")) {
                continue;
            } else if (random == 1){
                do {
                    random = (Math.random() > 0.5 ? 1 : 2);
                    BankCardsDAO.insert(connection, Bank.cardGenerator(account));
                } while (random != 1);
            }
        }

        List<BankCard> bankCardList = BankCardsDAO.readAllData(connection);
        for (BankCard bankCard : bankCardList) {
            System.out.println(bankCard);
        }



//        for (int i = 0; i < csvFiles.length; i++) {
//
//
//
//            if (i == 0) {
//                for (String line; (line = reader.readLine()) != null;) {
//                    String[] data = line.split(";");
//                    Account account = new Account(data[0], Double.parseDouble(data[1]), Long.parseLong(data[2]));
//                    AccountsDAO.insert(connection, account);
//                }
//            } else if (i == 1) {
//                for (String line; (line = reader.readLine()) != null;) {
//                    String[] data = line.split(";");
//                    BankCard bankCard = new BankCard(data[0], Integer.parseInt(data[1]), Long.parseLong(data[2]), Long.parseLong(data[3]));
//                    BankCardsDAO.insert(connection, bankCard);
//                }
//            } else {
//                for (String line; (line = reader.readLine()) != null;) {
//                    String[] data = line.split(";");
//                    Customer customer = new Customer(data[0], data[1], data[2], Long.parseLong(data[3]));
//                    CustomersDAO.insert(customer, connection);
//                }
//            }
//        }
    }
}