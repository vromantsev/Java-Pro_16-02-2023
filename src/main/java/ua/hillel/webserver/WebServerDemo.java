package ua.hillel.webserver;

import java.io.IOException;

public class WebServerDemo {
    public static void main(String[] args) throws IOException {
        CustomWebServer webServer = new CustomWebServer();
        webServer.start();
    }
}
