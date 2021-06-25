package webapp.controller;

import com.sun.net.httpserver.HttpExchange;
import webapp.service.BankCardService;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class BankCardHandler {

    public static void cardHandler (HttpExchange httpExchange) throws SQLException, IOException {

        String[] getContext = httpExchange.getRequestURI().getPath().split("/");

        if("GET".equals(httpExchange.getRequestMethod())) {

            System.out.println(getContext[4]);

            if (getContext[4].equals("new")) {
                System.out.println(getContext[5]);
                BankCardService.createNewBankCard(getContext[5]);
                httpExchange.sendResponseHeaders(200, "Card created".length());
                OutputStream output = httpExchange.getResponseBody();
                output.write("Card created".getBytes());
                output.close();
            } //else if (getContext[4].equals("all")) {
//                System.out.println(getContext[5]);
//                CustomersService.getCustomersBankCards(Long.parseLong(getContext[5]));
//                httpExchange.sendResponseHeaders(200, "Card created".length());
//                OutputStream output = httpExchange.getResponseBody();
//                output.write("Card created".getBytes());
//                output.close();
//            }


        } else if("POST".equals(httpExchange)) {
            //requestParamValue = handlePostRequest(httpExchange);
        }
    }
}
