package webapp;

import webapp.controller.MyHttpServer;
import webapp.databaseDAO.ConnectToDatabase;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            ConnectToDatabase.databaseConnection();
            MyHttpServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        http://localhost:8080/api/bank/card/new/40817810085844377764
//        http://localhost:8080/api/bank/user/11/cards
//        http://localhost:8080/api/bank/account/40817810085844377764/balance/add/15837
//        http://localhost:8080/api/bank/account/40817810085844377764/balance/get
    }
}
