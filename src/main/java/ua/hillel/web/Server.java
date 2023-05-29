package ua.hillel.web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class.getName());

    static final String HOST = "localhost";
    static final int PORT = 2134;

    // localhost - 127.0.0.1
    public static void start() {
        LOGGER.info("Server started");
        ObjectInputStream is = null;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // open channel for the client and awaits until the clients request
            Socket clientSocket = serverSocket.accept();
            is = new ObjectInputStream(clientSocket.getInputStream());
            Object obj = is.readObject();
            LOGGER.info("Received from client: %s".formatted(obj));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {}
            }
        }
        LOGGER.info("Server stopped");
    }
}
