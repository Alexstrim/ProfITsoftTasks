package ua.profitsoft.strymeneshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

import java.util.List;

public class ClientService implements IService<Client>{

    @Autowired
    @Qualifier("clientHibernateDAO")
    private IDao<Client> dao;

    @Override
    public IDao<Client> getDao() {
        return dao;
    }

    public List<Client> getAllList() throws Exception {
        return dao.getAllList();
    }
}
