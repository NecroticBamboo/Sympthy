package Interfaces;

public class UserInfo {
    private long id;
    private long orgId;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private int isActive;


    public UserInfo(long idIn){
        id = idIn;
        isActive = 1;
        orgId = -1;
    }

    public String getName() {
        return getFirstName();
    }

    public long getId() {
        return id;
    }
    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
