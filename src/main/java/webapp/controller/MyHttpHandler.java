package webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.sql.SQLException;

public class MyHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();

        String[] getContext = httpExchange.getRequestURI().getPath().split("/");

        if (getContext[3].equals("card")) {
            try {
                BankCardHandler.cardHandler(httpExchange);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (getContext[3].equals("account")) {
            try {
                AccountHandler.accountHandle(httpExchange);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else if (getContext[3].equals("user")) {
            try {
                CustomerHandler.customerHandler(httpExchange);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

//        httpExchange.getRequestURI()

        // сама сериализация: 1-куда, 2-что
        //mapper.writeValue(writer, cat);

        //преобразовываем все записанное во StringWriter в строку
//        String result = writer.toString();
//        System.out.println(result);

        if("GET".equals(httpExchange.getRequestMethod())) {

//            if (httpExchange.getRequestURI().toString().equals("/api/bank/card")) {
//
//
//
//            } else if (httpExchange.getRequestURI().toString().equals("/api/bank/all-cards")) {
//
//            } else if (httpExchange.getRequestURI().toString().equals("/api/bank/add-to-account")) {
//
//            } else if (httpExchange.getRequestURI().toString().equals("/api/bank/balance")) {
//
//            }

//            System.out.println(httpExchange.getRequestURI());
//            System.out.println(httpExchange.getHttpContext().getPath());
//            System.out.println(httpExchange.getRequestMethod());
//            System.out.println(httpExchange.getResponseHeaders());
//            String respText = "Hello!";
//            httpExchange.sendResponseHeaders(200, respText.getBytes().length);
//            OutputStream output = httpExchange.getResponseBody();
//            output.write(respText.getBytes());
//            requestParamValue = handleGetRequest(httpExchange);
//            System.out.println(requestParamValue);
//            output.flush();

        } else if("POST".equals(httpExchange)) {
            //requestParamValue = handlePostRequest(httpExchange);
        }

        //handleResponse(httpExchange,requestParamValue);

    }

    private String handleGetRequest(HttpExchange httpExchange) {

        return httpExchange.
                getRequestURI()
                .toString()
                .split("\\?")[1]
                .split("=")[1];
    }

//    private String handlePostRequest(HttpExchange httpExchange) {
//
//    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue) throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        //StringBuilder htmlBuilder = "OK";
//
//        htmlBuilder.append("").
//                append("").
//                append("<h1>").
//                append("Hello ")
//                .append(requestParamValue)
//                .append("</h1>")
//                .append("")
//                .append("");
//
//         //encode HTML content
//        String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder.toString());

        //this line is a must
//        httpExchange.sendResponseHeaders(200, htmlBuilder.length());
//
//        outputStream.write(htmlBuilder.getBytes());
//        outputStream.flush();
//        outputStream.close();
    }
}
