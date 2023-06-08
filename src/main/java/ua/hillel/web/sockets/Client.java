package ua.hillel.web.sockets;

import ua.hillel.jdbc.entity.Product;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import static ua.hillel.web.sockets.Server.HOST;
import static ua.hillel.web.sockets.Server.PORT;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class.getName());

    public static void send(final Product product) {
        try (Socket clientSocket = new Socket(HOST, PORT);
             ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            outputStream.writeObject(product);
            LOGGER.info("Sending to server a product: %s".formatted(product));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
