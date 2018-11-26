package ua.profitsoft.strymeneshenko.service;


import ua.profitsoft.strymeneshenko.dao.IDao;

public interface IServiceFile<T> {
    IDao<T> getDao();

    default void create(T entity) throws Exception {
        getDao().create(entity);
    }

    default T read(long id) throws Exception {
        return getDao().read(id);
    }

    default void update(T entity) throws Exception {
        getDao().update(entity);
    }

    default void delete(long id) throws Exception {
        getDao().delete(id);
    }
}
