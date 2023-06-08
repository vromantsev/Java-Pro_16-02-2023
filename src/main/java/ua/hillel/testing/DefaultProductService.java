package ua.hillel.testing;

import org.springframework.stereotype.Service;
import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.operations.JdbcOperations;

import java.util.Objects;

@Service
public class DefaultProductService implements ProductService {

    private JdbcOperations<Product, Long> jdbcOperations;

    public DefaultProductService(JdbcOperations<Product, Long> jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Product create(Product product) {
        Objects.requireNonNull(product);
        return this.jdbcOperations.insert(product);
    }

    @Override
    public Product findById(Long id) {
        Objects.requireNonNull(id);
        return this.jdbcOperations.findById(id);
    }

    @Override
    public Product update(Product product) {
        Objects.requireNonNull(product);
        return this.jdbcOperations.update(product);
    }

    @Override
    public boolean delete(Long id) {
        Objects.requireNonNull(id);
        return this.jdbcOperations.delete(id);
    }
}
