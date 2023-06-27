package ua.hillel.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.operations.impl.ProductJdbcOperations;
import ua.hillel.testing.DefaultProductService;
import ua.hillel.testing.ProductService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

@WebServlet(value = "/api/product-management/products/*")
public class ProductServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ProductServlet.class.getName());

    private ObjectMapper objectMapper;
    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.objectMapper = new ObjectMapper();
        //this.objectMapper.registerModule(new JavaTimeModule());
        this.productService = new DefaultProductService(new ProductJdbcOperations());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        LOGGER.info(pathInfo);
        var parts = pathInfo.split("/");
        String idAsString = parts[parts.length - 1];
        var product = this.productService.findById(Long.parseLong(idAsString));
        byte[] productAsBytes = this.objectMapper.writeValueAsBytes(product);
        try (OutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(productAsBytes);
            outputStream.flush();
            resp.setContentLength(productAsBytes.length);
            resp.setContentType("application/json");
            resp.setStatus(200);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var is = req.getInputStream();
             var out = resp.getOutputStream()) {
            JsonNode productJson = this.objectMapper.readTree(is);
            Product product = new Product();
            product.setName(productJson.get("name").asText());
            product.setPrice(productJson.get("price").asDouble());
            var createdProduct = this.productService.create(product);

            var createdProductAsBytes = this.objectMapper.writeValueAsBytes(createdProduct);
            out.write(createdProductAsBytes);
            out.flush();
            resp.setContentLength(createdProductAsBytes.length);
            resp.setContentType("application/json");
            resp.setStatus(201);
        }
    }
}
