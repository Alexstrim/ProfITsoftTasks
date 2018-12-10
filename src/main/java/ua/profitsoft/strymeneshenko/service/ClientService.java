package ua.profitsoft.strymeneshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.db.dao.ClientDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

public class ClientService implements IService<Client>{

    @Autowired
    @Qualifier("clientDAO")
    private IDao<Client> dao;

    @Override
    public IDao<Client> getDao() {
        return dao;
    }
}
