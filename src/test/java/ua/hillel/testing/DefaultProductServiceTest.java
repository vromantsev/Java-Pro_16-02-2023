package ua.hillel.testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.exceptions.JdbcOperationException;
import ua.hillel.jdbc.operations.impl.ProductJdbcOperations;
import ua.hillel.jdbc.utils.JdbcUtils;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultProductServiceTest {

    private static ProductService productService;

    @BeforeAll
    public static void setup() {
        //productService = new DefaultProductService(new ProductJdbcOperations(JdbcUtils.initDataSource()));
    }

    @AfterAll
    public static void tearDown() {
        // cleanup - delete rows from db, close resources
        productService = null;
    }

    @Test
    public void whenCreateProductThenProductCreatedSuccessfully() {
        var product = new Product();
        product.setName("Coca cola");
        product.setPrice(25.0);
        Product actual = productService.create(product);
        assertEquals(product, actual, "Product should not be null!");
    }

    @Test
    public void whenFindProductByIdThenProductIsFound() {
        Product actual = productService.findById(12L);
        Product expected = new Product();
        expected.setId(actual.getId());
        expected.setName(actual.getName());
        expected.setPrice(actual.getPrice());
        expected.setCreatedAt(actual.getCreatedAt());
        assertEquals(expected, actual, "Product should not be null!");
    }

    @Test
    public void whenFindProductByInvalidIdThenThrowException() {
        Long invalidProductId = 123L;
        String errorMessage = "Product with id = %d not found!".formatted(invalidProductId);
        JdbcOperationException ex = assertThrows(
                JdbcOperationException.class,
                () -> productService.findById(invalidProductId)
        );
        assertNotNull(ex);
        assertEquals(errorMessage, ex.getMessage());
    }

    @Test
    public void whenUpdateProductThenProductUpdatedSuccessfully() {
        Product expected = new Product();
        expected.setId(4L);
        expected.setName("Cheese");
        expected.setPrice(200);
        Product actual = productService.update(expected);
        assertEquals(expected, actual, "Product should not be null!");
    }

    @Test
    public void whenDeleteProductWithInvalidIdThenThrowException() {
        Long invalidProductId = 123L;
        String errorMessage = "No rows were deleted!";
        JdbcOperationException ex = assertThrows(
                JdbcOperationException.class,
                () -> productService.delete(invalidProductId)
        );
        assertEquals(errorMessage, ex.getMessage());
    }

    @Test
    public void whenDeleteProductThenProductDeletedSuccessfully() {
        boolean deleted = productService.delete(13L);
        assertTrue(deleted);
    }
}
