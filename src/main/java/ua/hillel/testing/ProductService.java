package ua.hillel.testing;

import ua.hillel.jdbc.entity.Product;

public interface ProductService {

    Product create(final Product product);

    Product findById(final Long id);

    Product update(final Product product);

    boolean delete(final Long id);

}
