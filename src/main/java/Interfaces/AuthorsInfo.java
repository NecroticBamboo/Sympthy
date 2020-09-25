package Interfaces;

public class AuthorsInfo {
    private long id;
    private TestInfo testId;
    private UserInfo userId;

    public AuthorsInfo(long idIn){
        id=idIn;
    }

    public long getId() {
        return id;
    }

    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public TestInfo getTestId() {
        return testId;
    }

    public void setTestId(TestInfo testId) {
        this.testId = testId;
    }

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }
}
