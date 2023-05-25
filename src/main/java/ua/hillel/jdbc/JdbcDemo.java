package ua.hillel.jdbc;

import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.operations.JdbcOperations;
import ua.hillel.jdbc.operations.impl.ProductJdbcOperations;
import ua.hillel.jdbc.utils.JdbcUtils;

public class JdbcDemo {
    // ORM - object-relation mapping
    // Hibernate
    public static void main(String[] args) {
        JdbcOperations<Product, Long> productJdbcOperations = new ProductJdbcOperations(JdbcUtils.initDataSource());

        /*Product soap = new Product();
        soap.setName("soap");
        soap.setPrice(25.0);*/

        /*Product insertedSoap = productJdbcOperations.insert(soap);
        System.out.println(insertedSoap);*/

        Product productById = productJdbcOperations.findById(10L);
        System.out.println(productById);

        /*productById.setName("Updated SOAP");
        productById.setPrice(100.0);
        productJdbcOperations.update(productById);*/

        boolean isDeleted = productJdbcOperations.delete(10L);
        System.out.printf("Product %s was deleted: %s%n", productById, isDeleted);
    }
}
