package ua.profitsoft.strymeneshenko.entity;

//Entity LegalEntity
public class LegalEntity extends Client {

    private String nameOrganization = "";

    public LegalEntity() {
    }

    public LegalEntity(String nameOrganization, String adress) {
        super(adress);
        this.setNameOrganization(nameOrganization);
    }

    @Override
    public String toString() {
        return "LegalEntity [nameOrganization=" + nameOrganization + "; adress=" + super.adress + "]";
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }
}
