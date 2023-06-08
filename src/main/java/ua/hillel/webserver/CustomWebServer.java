package ua.hillel.webserver;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.StringJoiner;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class CustomWebServer {

    private static final Logger LOGGER = Logger.getLogger(CustomWebServer.class.getName());

    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    private final HttpServer httpServer;

    public CustomWebServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(HOST, PORT), 0);
    }

    public void start() {
        httpServer.createContext("/api/products", new GetRequestHandler());
        httpServer.setExecutor(Executors.newFixedThreadPool(10));
        httpServer.start();
        LOGGER.info("Server started on address %s".formatted(new StringJoiner(":").add(HOST).add(String.valueOf(PORT))));
    }

    public void stopServer() {
        int delaySeconds = 3;
        this.httpServer.stop(delaySeconds);
    }
}
