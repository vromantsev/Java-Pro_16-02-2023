package ua.hillel.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.exceptions.JdbcOperationException;
import ua.hillel.jdbc.operations.JdbcOperations;
import ua.hillel.jdbc.operations.impl.ProductJdbcOperations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ProductServiceMockTest {

    @Mock
    private ProductJdbcOperations jdbcOperationsMock;

    private ProductService productService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new DefaultProductService(jdbcOperationsMock);
    }

    @AfterEach
    public void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void whenCreateProductThenProductCreatedSuccessfully() {
        // given
        Product product = new Product();
        product.setName("water");
        product.setPrice(20);

        // when
        when(this.jdbcOperationsMock.insert(eq(product)))
                .thenAnswer((Answer<Product>) invocationOnMock -> {
                    Product p = (Product) invocationOnMock.getArguments()[0];
                    p.setId(1L);
                    return p;
                });

        // then
        Product actual = this.productService.create(product);

        // assertions & verification
        assertNotNull(actual);
        assertEquals(1L, actual.getId());
        verify(this.jdbcOperationsMock, times(1)).insert(product);
    }

    @Test
    public void whenCreateProductWithIdThenThrowException() {
        // given
        Product product = new Product();
        product.setId(1L);
        product.setName("water");
        product.setPrice(20);

        // when
        var errorMessage = "Id must not be provided during the insert operation!";
        when(this.jdbcOperationsMock.insert(eq(product)))
                .thenThrow(new JdbcOperationException(errorMessage));

        // then
        var ex = assertThrows(
                JdbcOperationException.class,
                () -> this.productService.create(product)
        );

        // assertions & verification
        assertNotNull(ex);
        assertEquals(errorMessage, ex.getMessage());
        verify(this.jdbcOperationsMock, times(1)).insert(product);
    }

    @Test
    public void whenInsertNullProductThenThrowException() {
        // when
        when(this.jdbcOperationsMock.insert(eq(null)))
                .thenThrow(new NullPointerException());

        // then
        var ex = assertThrows(NullPointerException.class, () -> this.productService.create(null));

        // assertions & verification
        assertNotNull(ex);
        verify(this.jdbcOperationsMock, never()).insert(null);
    }
}
