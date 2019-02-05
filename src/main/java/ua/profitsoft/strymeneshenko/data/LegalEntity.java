package ua.profitsoft.strymeneshenko.data;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

//Entity LegalEntity
public class LegalEntity extends Client {

    @NotEmpty
    @Size(min = 2, max=40)
    private String nameOrganization = "";

    public LegalEntity() {
    }

    public LegalEntity(String nameOrganization, String adress) {
        super(adress);
        this.setNameOrganization(nameOrganization);
    }

    @Override
    public String toString() {
        return "LegalEntity [id="+ id +", nameOrganization=" + nameOrganization + "; adress=" + super.adress + "]";
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }
}
