package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.db.dao.ContractDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;

public class ContractService implements IServiceFile<Contract> {
    @Override
    public IDao<Contract> getDao() {
        return new ContractDAO();
    }
}
