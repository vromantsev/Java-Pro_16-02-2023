package ua.hillel.webserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ua.hillel.jdbc.operations.impl.ProductJdbcOperations;
import ua.hillel.testing.DefaultProductService;
import ua.hillel.testing.ProductService;

import java.io.IOException;

public class GetRequestHandler implements HttpHandler {

    private final ProductService productService = new DefaultProductService(new ProductJdbcOperations());
    private final ObjectMapper mapper = new ObjectMapper();

    {
        mapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        var product = productService.findById(5L);
        var productAsBytes = mapper.writeValueAsBytes(product);
        try (var out = exchange.getResponseBody()) {
            exchange.sendResponseHeaders(200, productAsBytes.length);
            out.write(productAsBytes);
            out.flush();
        }
    }
}
