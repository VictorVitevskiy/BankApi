package webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import webapp.model.BankCard;
import webapp.service.CustomerService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

public class CustomerHandler {

    public static void customerHandler (HttpExchange httpExchange) throws SQLException, IOException {

        String[] getContext = httpExchange.getRequestURI().getPath().split("/");

        if("GET".equals(httpExchange.getRequestMethod())) {

            //System.out.println(getContext[4]);
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();

            if (getContext[5].equals("cards")) {

                List<BankCard> bankCardList = CustomerService.getCustomersBankCards(Long.parseLong(getContext[4])).getBankCardsList();
//                mapper.writeValue(writer,bankCardList);
                httpExchange.sendResponseHeaders(200, writer.toString().length());
                OutputStream output = httpExchange.getResponseBody();
                output.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(bankCardList));
                output.close();
            }

        } else if("POST".equals(httpExchange)) {
            //requestParamValue = handlePostRequest(httpExchange);
        }
    }
}
