package ua.profitsoft.strymeneshenko.data;

import java.util.List;

public class ServiceResponse {

    private List<Contract> contracts;

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }
}
