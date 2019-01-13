package ua.profitsoft.strymeneshenko.db.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.util.hibernate.HibernateUtil;

import java.util.Iterator;

public class ClientHibernateDAO implements IDao<Client> {
    @Override
    public void create(Client entity) throws Exception {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public Client read(long id) throws Exception {
        Session session = HibernateUtil.openSession();
        Query q = session.createQuery("SELECT c FROM Client as c WHERE c.id = :id");
        q.setLong("id", id);
        Iterator iter = q.list().iterator();
        if(iter.hasNext()){
            Client client = (Client)iter.next();
            HibernateUtil.closeSession();
            return client;
        }
        HibernateUtil.closeSession();
        return null;
    }

    @Override
    public void update(Client entity) throws Exception {
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
        session.createQuery("delete from Client c where c.id = :id").setLong("id", id).executeUpdate();
        tx.commit();
        HibernateUtil.closeSession();
    }
}
