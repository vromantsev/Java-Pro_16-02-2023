package ua.hillel.jdbc.operations;

public interface JdbcOperations<T, ID> {

    T insert(T entity);

    T findById(ID id);

    T update(T entity);

    boolean delete(ID id);

}
