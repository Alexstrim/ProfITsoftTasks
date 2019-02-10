package ua.profitsoft.strymeneshenko.data;

import java.util.List;

public class ContractResponse {

    private List<Contract> contract;

    public List<Contract> getContracts() {
        return contract;
    }

    public void setContracts(List<Contract> contracts) {
        this.contract = contracts;
    }
}
