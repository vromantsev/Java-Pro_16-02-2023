package ua.hillel.hw.task25.dao;

import java.util.List;

public interface GenericDao<T, ID> {

    T create(T obj);

    boolean delete(ID id);

    List<T> findAll();

    T findById(ID id);

}
