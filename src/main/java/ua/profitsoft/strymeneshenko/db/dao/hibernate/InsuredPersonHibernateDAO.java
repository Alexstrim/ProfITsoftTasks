package ua.profitsoft.strymeneshenko.db.dao.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.util.hibernate.HibernateUtil;

import java.util.Iterator;

public class InsuredPersonHibernateDAO implements IDao<InsuredPerson> {
    @Override
    public void create(InsuredPerson entity) throws Exception {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        HibernateUtil.closeSession();
    }

    @Override
    public InsuredPerson read(long id) throws Exception {
        Session session = HibernateUtil.openSession();
        Query q = session.createQuery("SELECT ip FROM InsuredPerson as ip WHERE ip.id = :id");
        q.setLong("id", id);
        Iterator iter = q.list().iterator();
        if(iter.hasNext()){
            InsuredPerson ip = (InsuredPerson) iter.next();
            HibernateUtil.closeSession();
            return ip;
        }
        HibernateUtil.closeSession();
        return null;
    }

    @Override
    public void update(InsuredPerson entity) throws Exception {
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
        session.createQuery("delete from InsuredPerson as ip where ip.id = :id").setLong("id", id).executeUpdate();
        tx.commit();
        HibernateUtil.closeSession();
    }
}
