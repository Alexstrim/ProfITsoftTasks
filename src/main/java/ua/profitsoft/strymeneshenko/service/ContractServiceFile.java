package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.dao.ConractsFileDAO;
import ua.profitsoft.strymeneshenko.dao.IDao;
import ua.profitsoft.strymeneshenko.entity.Contract;

public class ContractServiceFile implements IServiceFile<Contract> {
    @Override
    public IDao<Contract> getDao() {
        return new ConractsFileDAO();
    }
}
