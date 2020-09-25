package Interfaces;

public class ClassInfo {
    private long id;
    private OrganisationInfo orgId;
    private String name;

    public ClassInfo(long idIn){
        id=idIn;
    }

    public long getId() {
        return id;
    }

    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public OrganisationInfo getOrgId() {
        return orgId;
    }

    public void setOrgId(OrganisationInfo orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
