package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.db.dao.ClientDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

public class ClientService implements IService<Client>{
    @Override
    public IDao<Client> getDao() {
        return new ClientDAO();
    }
}
