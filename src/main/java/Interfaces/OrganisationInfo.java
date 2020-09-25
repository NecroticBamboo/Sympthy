package Interfaces;

public class OrganisationInfo {
    private long id;
    private String name;
    private String address;

    public OrganisationInfo(long idIn){
        id=idIn;
    }

    public long getId() {
        return id;
    }

    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
