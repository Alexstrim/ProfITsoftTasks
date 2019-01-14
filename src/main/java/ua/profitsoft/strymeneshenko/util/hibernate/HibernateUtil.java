package ua.profitsoft.strymeneshenko.util.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    public static final ThreadLocal threadLocal = new ThreadLocal();

    static {
        Configuration conf = new Configuration(); 
        sessionFactory = conf.configure().buildSessionFactory();
    }

    public static Session openSession() {
        Session s = (Session)threadLocal.get();
        if (s==null) {
            s = sessionFactory.openSession();
            threadLocal.set(s);
        }
        return s;
    }

    public static void closeSession() {
        Session s = (Session)threadLocal.get();
        if(s!= null){
            s.close();
            threadLocal.set(null);
        }
    }

}
