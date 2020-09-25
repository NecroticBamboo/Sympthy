package Interfaces;

import java.util.ArrayList;

public interface IUserManagement {
    UserInfo createUser();
    UserInfo getUserInfo(String email);
    void updateUser(UserInfo userInfo);
    void removeUser(UserInfo userInfo);
    boolean userExist(String email);

    StudentInfo createStudent();
    void updateStudent(StudentInfo studentInfo);
    StudentInfo getStudentInfo(UserInfo userInfo, ClassInfo classInfo);
    ArrayList<StudentInfo> getStudentInfo(ClassInfo classInfo);
    void removeStudent(StudentInfo studentInfo);
    boolean studentExist(StudentInfo studentInfo);

    OrganisationInfo createOrganisation();
    OrganisationInfo getOrganisationInfo(String orgName);
    OrganisationInfo getOrganisationInfoWithId(long orgId);
    void updateOrganisation(OrganisationInfo organisationInfo);
    void removeOrganisation(OrganisationInfo organisationInfo);
    boolean organisationExist(String orgName);

    ClassInfo createClass();
    ClassInfo getClassInfo(String className);
    ClassInfo getClassInfoById(long id);
    void updateClass(ClassInfo classInfo);
    void removeClass(ClassInfo classInfo);
    boolean classExist(String className);
}
