package ua.profitsoft.strymeneshenko.dao;

public interface IDao<T> {
    void create(T entity) throws Exception;
    T read(long id) throws Exception;
    void update(T entity) throws Exception;
    void delete(long id) throws Exception;
}
