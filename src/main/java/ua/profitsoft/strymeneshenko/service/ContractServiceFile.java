package ua.profitsoft.strymeneshenko.service;

import ua.profitsoft.strymeneshenko.dao.ConractsFileDAO;
import ua.profitsoft.strymeneshenko.dao.IDaoFile;
import ua.profitsoft.strymeneshenko.entity.Contract;

public class ContractServiceFile implements IServiceFile<Contract> {
    @Override
    public IDaoFile<Contract> getDaoFile() {
        return new ConractsFileDAO();
    }
}
