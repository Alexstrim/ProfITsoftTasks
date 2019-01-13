package ua.profitsoft.strymeneshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.db.dao.ContractDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

public class ContractService implements IService<Contract> {

    @Autowired
    @Qualifier("contractHibernateDAO")
    private IDao<Contract> dao;

    @Override
    public IDao<Contract> getDao() {
        return dao;
    }
}
