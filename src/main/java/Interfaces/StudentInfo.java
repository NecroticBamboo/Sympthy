package Interfaces;

public class StudentInfo {
    private long id;
    private UserInfo userId;
    private ClassInfo classId;
    private String userType;

    public StudentInfo(long idIn){
        setId(idIn);
    }

    public long getId() {
        return id;
    }

    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public void setId(long id) {
        this.id = id;
    }

    public UserInfo getUserId() {
        return userId;
    }

    public void setUserId(UserInfo userId) {
        this.userId = userId;
    }

    public ClassInfo getClassId() {
        return classId;
    }

    public void setClassId(ClassInfo classId) {
        this.classId = classId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
/*
public interface IStudentInfoFactory
{
    StudentInfo Create(long idIn);
}

public class StudentInfoFactory implements IStudentInfoFactory
{
    public StudentInfo Create(long idIn)
    {
        return new StudentInfo(idIn);
    }
}
*/
