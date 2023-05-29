package ua.hillel.web;

import ua.hillel.jdbc.entity.Product;

import java.time.LocalDateTime;

public class ClientDemo {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("ChatGPT subscription");
        product.setPrice(15.0);
        product.setCreatedAt(LocalDateTime.now());

        Client.send(product);
    }
}
