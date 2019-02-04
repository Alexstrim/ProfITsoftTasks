package ua.profitsoft.strymeneshenko.service;


import org.springframework.transaction.annotation.Transactional;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

public interface IService<T> {
    IDao<T> getDao();

    @Transactional
    default void create(T entity) throws Exception {
        getDao().create(entity);
    }

    @Transactional(readOnly = true)
    default T read(long id) throws Exception {
        return getDao().read(id);
    }

    @Transactional
    default void update(T entity) throws Exception {
        getDao().update(entity);
    }

    @Transactional
    default void delete(long id) throws Exception {
        getDao().delete(id);
    }
}
