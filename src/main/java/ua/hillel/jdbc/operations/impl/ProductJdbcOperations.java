package ua.hillel.jdbc.operations.impl;

import ua.hillel.jdbc.entity.Product;
import ua.hillel.jdbc.exceptions.JdbcOperationException;
import ua.hillel.jdbc.operations.JdbcOperations;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Objects;

public class ProductJdbcOperations implements JdbcOperations<Product, Long> {

    private final DataSource dataSource;

    public ProductJdbcOperations(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product insert(final Product entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() != null) {
            throw new JdbcOperationException("Id must not be provided during the insert operation!");
        }
        var sql = """
                INSERT INTO postgres.product (name, price) VALUES (?, ?)
                """;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int idx = 1;
            ps.setString(idx++, entity.getName());
            ps.setDouble(idx, entity.getPrice());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted < 1) {
                throw new JdbcOperationException("No rows were inserted, input entity = %s".formatted(entity));
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                entity.setId(id);
            }
            return entity;
        } catch (SQLException e) {
            throw new JdbcOperationException("Failed to insert a product = %s".formatted(entity), e);
        }
    }

    @Override
    public Product findById(final Long id) {
        Objects.requireNonNull(id);
        var sql = """
                SELECT id, name, price, created_at FROM postgres.product WHERE id = ?
                """;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            int idx = 1;
            ps.setLong(idx, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                return product;
            } else {
                throw new JdbcOperationException("Product with id = %d not found!".formatted(id));
            }
        } catch (SQLException e) {
            throw new JdbcOperationException("Failed to find a product by id = %d".formatted(id), e);
        }
    }

    @Override
    public void update(final Product entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() == null) {
            throw new JdbcOperationException("Entity without id cannot be updated!");
        }
        var sql = """
                UPDATE postgres.product SET name = ?, price = ? WHERE id = ?
                """;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            int idx = 1;
            String name = entity.getName();
            if (name != null) {
                ps.setString(idx++, name);
            }
            ps.setDouble(idx++, entity.getPrice());
            ps.setLong(idx, entity.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated < 1) {
                throw new JdbcOperationException("No rows were updated!");
            }
        } catch (SQLException e) {
            throw new JdbcOperationException("Failed to update a product: %s".formatted(entity), e);
        }
    }

    @Override
    public boolean delete(final Long id) {
        Objects.requireNonNull(id);
        var sql = """
                DELETE FROM postgres.product WHERE id = ?
                """;
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            var idx = 1;
            ps.setLong(idx, id);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted < 1) {
                throw new JdbcOperationException("No rows were deleted!");
            }
            return true;
        } catch (SQLException e) {
            throw new JdbcOperationException("Failed to delete a product by id = %d".formatted(id), e);
        }
    }
}
