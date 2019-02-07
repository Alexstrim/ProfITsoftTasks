package ua.profitsoft.strymeneshenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

import java.util.List;

public class InsuredPersonService implements IService<InsuredPerson>{

    @Autowired
    @Qualifier("insuredPersonHibernateDAO")
    private IDao<InsuredPerson> dao;

    @Override
    public IDao<InsuredPerson> getDao() {
        return dao;
    }

    public List<InsuredPerson> getAllList() throws Exception {
        return dao.getAllList();
    }
}
