package ua.hillel.jdbc.operations;

public interface JdbcOperations<T, ID> {

    T insert(T entity);

    T findById(ID id);

    void update(T entity);

    boolean delete(ID id);

}
