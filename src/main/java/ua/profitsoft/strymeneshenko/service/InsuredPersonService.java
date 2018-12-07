package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.db.dao.InsuredPersonDAO;

public class InsuredPersonService implements IService<InsuredPerson>{

    @Override
    public IDao<InsuredPerson> getDao() {
        return new InsuredPersonDAO();
    }
}
