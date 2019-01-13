package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.db.dao.file.ConractsFileDAO;
import ua.profitsoft.strymeneshenko.db.dao.IDao;
import ua.profitsoft.strymeneshenko.data.Contract;

public class ContractServiceFile implements IService<Contract> {
    @Override
    public IDao<Contract> getDao() {
        return new ConractsFileDAO();
    }
}
