package ua.profitsoft.strymeneshenko.db.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.util.hibernate.HibernateUtil;

import java.util.Iterator;

public class ContractHibernateDAO implements IDao<Contract> {

    @Override
    public void create(Contract entity) throws Exception {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();

        session.save(entity);

        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public Contract read(long id) throws Exception {
        Session session = HibernateUtil.openSession();
        Contract contract = session.get(Contract.class, id);
        HibernateUtil.closeSession();
        return contract;
    }

    @Override
    public void update(Contract entity) throws Exception {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public void delete(long id) throws Exception {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from Contract con where con.id = :id").setLong("id", id).executeUpdate();
        tx.commit();
        HibernateUtil.closeSession();
    }
}
