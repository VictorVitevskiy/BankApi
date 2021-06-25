package webapp.controller;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *Класс
 */

public class MyHttpServer {

    public static HttpServer server;

    public static void startServer() throws IOException {

        int serverPort = 8080;
        server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.createContext("/api/bank", new MyHttpHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();
    }





//        for (int i =0; i < 20; i++) {
//
//            //System.out.print(Bank.cardNumberGenerator()+";");
//            //System.out.print((int)(Math.random()*900 + 100)+";");
//            System.out.println("" + (int)(Math.random()*10) + (int)(Math.random()*10) + (int)(Math.random()*10) + (int)(Math.random()*10) + (int)(Math.random()*10) +
//                    + (int)(Math.random()*10) + (int)(Math.random()*10) + (int)(Math.random()*10)+ (int)(Math.random()*10) + (int)(Math.random()*10) + (int)(Math.random()*10));
//        }
//
//        //писать результат сериализации будем во Writer(StringWriter)
//        StringWriter writer = new StringWriter();
//
//        //это объект Jackson, который выполняет сериализацию
//        ObjectMapper mapper = new ObjectMapper();
//
//        // сама сериализация: 1-куда, 2-что
//        //mapper.writeValue(writer, cat);
//        Account account = new Account("40817810078398700000", 1761037.76, 1L);
//        mapper.writeValue(writer, account);
//        System.out.println(writer);


//        try (ServerSocket serverSocket = new ServerSocket(8080)) {
//            System.out.println("Server started!");
//
//            while (true) {
//                // ожидаем подключения
//                Socket socket = serverSocket.accept();
//                System.out.println("Client connected!");
//
//                // для подключившегося клиента открываем потоки
//                // чтения и записи
//                try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
//                     PrintWriter output = new PrintWriter(socket.getOutputStream())) {
//
//                    // ждем первой строки запроса
//                    while (!input.ready()) ;
//
//                    // считываем и печатаем все что было отправлено клиентом
//                    System.out.println();
//                    while (input.ready()) {
//                        System.out.println(input.readLine());
//                    }
//
//                    // отправляем ответ
//                    output.println("HTTP/1.1 200 OK");
//                    output.println("Content-Type: text/html; charset=utf-8");
//                    output.println();
//                    output.println("<p>Привет всем!</p>");
//                    output.flush();
//
//                    // по окончанию выполнения блока try-with-resources потоки,
//                    // а вместе с ними и соединение будут закрыты
//                    System.out.println("Client disconnected!");
//                }
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }



//                (exchange -> {
//            String respText = "Hello!";
//            exchange.sendResponseHeaders(200, respText.getBytes().length);
//            OutputStream output = exchange.getResponseBody();
//            output.write(respText.getBytes());
//            output.flush();
//            exchange.close();
//        }));
//        server.setExecutor(null); // creates a default executor
//        server.start();


}
