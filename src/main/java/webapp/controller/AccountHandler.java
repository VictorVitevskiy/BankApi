package webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import webapp.service.BalanceService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.SQLException;

public class AccountHandler {

    public static void accountHandle(HttpExchange httpExchange) throws SQLException, IOException {

        String[] getContext = httpExchange.getRequestURI().getPath().split("/");
        //httpExchange.

        if("GET".equals(httpExchange.getRequestMethod())) {

            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();

            if (getContext[5].equals("balance")) {
                if (getContext[6].equals("add")) {

//                    System.out.println(getContext[5]);
                    BalanceService.addToBalance(getContext[4],Double.parseDouble(getContext[7]));

                    httpExchange.sendResponseHeaders(200, "Add to balance successful".length());
                    OutputStream output = httpExchange.getResponseBody();
                    output.write("Add to balance successful".getBytes());
                    output.close();

//                    httpExchange.sendResponseHeaders(200, writer.toString().length());
//                    OutputStream output = httpExchange.getResponseBody();
//                    output.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(account.getBalance()));
//                    output.close();
                }
                if (getContext[6].equals("get")) {

                    Double balance = BalanceService.getBalance(getContext[4]);
                    mapper.writeValue(writer,balance);

                    httpExchange.sendResponseHeaders(200, writer.toString().length());
                    OutputStream output = httpExchange.getResponseBody();
                    output.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(balance));
                    output.close();
                }

            }

        }
//        else if("POST".equals(httpExchange)) {
//            //requestParamValue = handlePostRequest(httpExchange);
//        }
    }

}
