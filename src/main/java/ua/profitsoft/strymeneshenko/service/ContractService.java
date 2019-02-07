package ua.profitsoft.strymeneshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

import java.util.List;

public class ContractService implements IService<Contract> {

    @Autowired
    @Qualifier("contractHibernateDAO")
    private IDao<Contract> dao;

    @Override
    public IDao<Contract> getDao() {
        return dao;
    }

    public List<Contract> getAllList() throws Exception {
        return dao.getAllList();
    }
}
