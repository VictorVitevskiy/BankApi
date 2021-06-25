package webapp.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import webapp.databaseDAO.AccountsDAO;
import webapp.databaseDAO.ConnectToDatabase;
import webapp.model.Account;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyHttpHandlerTest {

    @BeforeAll
    public static void beforeAll() throws IOException {
        ConnectToDatabase.databaseConnection();
        MyHttpServer.startServer();
    }
    @AfterAll
    public static void afterAll() throws SQLException {
        ConnectToDatabase.connection.close();
        MyHttpServer.server.stop(0);
    }

    @Test
    void handle() throws IOException, InterruptedException, SQLException {
        AccountsDAO.insert(ConnectToDatabase.connection, new Account("40817810078398700000",1761037.76,1L));
        int expectedStatusCode = 200;
        //String requestBody = "{\"accountNumber\":\"10100202139087645632\"}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/bank/user/12/cards")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(expectedStatusCode, response.statusCode());
    }
}

//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(new URI("http://google.com"))
//            .headers("Content-Type", "application/json;charset=UTF-8")
//            .GET()
//            .build();
//
//    HttpResponse<String> response = HttpClient
//            .newBuilder()
//            .connectTimeout(Duration.ofSeconds(15))
//            .proxy(ProxySelector.of(
//                    new InetSocketAddress(
//                            "1.1.1.1", 1111
//                    )
//            ))
//            .build()
//            .send(request, HttpResponse.BodyHandlers.ofString());