package DAO;

import DI.IServiceLocator;
import Interfaces.*;
import main.utils.ConnectionInfo;
import main.utils.IDatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class UserManagement implements IUserManagement {
    private IDatabaseManager connectionManager;

    public UserManagement(IServiceLocator serviceLocator) {
        connectionManager = serviceLocator.getService("IDatabaseManager");
    }

    @Override
    public UserInfo createUser() {
        UserInfo user = new UserInfo(-1);
        return user;
    }

    @Override
    public UserInfo getUserInfo(String email) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select user_id, organisation_id, role, first_name, last_name, is_active from onlinetest.user where email=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, email);

            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                int idxId = resultSet.findColumn("user_id");
                int idxOrgId = resultSet.findColumn("organisation_id");
                int idxRole = resultSet.findColumn("role");
                int idxFirstName = resultSet.findColumn("first_name");
                int idxLastName = resultSet.findColumn("last_name");
                int idxIsActive = resultSet.findColumn("is_active");

                long id = resultSet.getLong(idxId);
                long orgId = resultSet.getLong(idxOrgId);
                String role = resultSet.getString(idxRole);
                String firstName = resultSet.getString(idxFirstName);
                String lastName = resultSet.getString(idxLastName);
                int isActive = resultSet.getInt(idxIsActive);

                UserInfo info = new UserInfo(id);
                info.setOrgId(orgId);
                info.setRole(role);
                info.setFirstName(firstName);
                info.setLastName(lastName);
                info.setEmail(email);
                info.setIsActive(isActive);

                st.close();
                return info;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            int start;
            if (userInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.user( organisation_id, role, first_name, last_name, email, is_active) value (?,?,?,?,?,?)");
                start = 1;
            } else {
                st = con.prepareStatement("update onlinetest.user set organisation_id=?,role=?,first_name=?,last_name=?,email=?,is_active=? where user_id=?");
                st.setLong(7, userInfo.getId());
                start = 1;
            }
            st.setLong(start + 0, userInfo.getOrgId());
            st.setString(start + 1, userInfo.getRole());
            st.setString(start + 2, userInfo.getFirstName());
            st.setString(start + 3, userInfo.getLastName());
            st.setString(start + 4, userInfo.getEmail());
            st.setInt(start + 5, userInfo.getIsActive());

            st.execute();
            st.close();

            if (userInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    userInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeUser(UserInfo userInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.user where user_id=?");
            st.setLong(1, userInfo.getId());

            st.execute();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean userExist(String email) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;

            String check = "select first_name from onlinetest.user where email=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, email);

            ResultSet resultSet = st.executeQuery();


            var res =  resultSet.next();
            st.close();
            return res;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public StudentInfo createStudent() {
        StudentInfo student=new StudentInfo(-1);
        return student;
    }

    @Override
    public void updateStudent(StudentInfo studentInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            if (studentInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.students(user_id, class_id, type) value (?,?,?)");
            } else {
                st = con.prepareStatement("update onlinetest.students set user_id=?, class_id=?, type=? where id=?");
                st.setLong(4, studentInfo.getId());
            }
            st.setLong(1, studentInfo.getUserId().getId());
            st.setLong(2, studentInfo.getClassId().getId());
            st.setString(3, studentInfo.getUserType());

            st.execute();
            st.close();

            if (studentInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    studentInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public StudentInfo getStudentInfo(UserInfo userInfo, ClassInfo classInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select id, class_id, type from onlinetest.students where user_id=?"; //?????
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, userInfo.getId());

            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                int idxId = resultSet.findColumn("id");
                int classxId = resultSet.findColumn("class_id");
                int typex =resultSet.findColumn("type");

                long id=resultSet.getLong(idxId);
                long classId=resultSet.getLong(classxId);
                String type=resultSet.getNString(typex);

                StudentInfo info = new StudentInfo(id);
                info.setUserId(userInfo);
                info.setClassId(classInfo);
                info.setUserType(type);

                st.close();
                return info;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ArrayList<StudentInfo> getStudentInfo(ClassInfo classInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select id, user_id, class_id, type from onlinetest.students where class_id=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1,classInfo.getId());

            ResultSet resultSet = st.executeQuery();

            ArrayList<StudentInfo> result = new ArrayList<>();
            while (resultSet.next()) {
                StudentInfo holder = new StudentInfo(resultSet.getLong(1));

                UserInfo userInfo = new UserInfo(resultSet.getLong(2));
                holder.setUserId(userInfo);
                holder.setClassId(classInfo);
                holder.setUserType(resultSet.getString(4));

                result.add(holder);
            }
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeStudent(StudentInfo studentInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.students where user_id=?");
            st.setLong(1, studentInfo.getUserId().getId());

            st.execute();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean studentExist(StudentInfo studentInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;

            String check = "select user_id from onlinetest.students where class_id=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, studentInfo.getClassId().getId());

            ResultSet resultSet = st.executeQuery();

            var res =  resultSet.next();
            st.close();
            return res;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public OrganisationInfo createOrganisation() {
        OrganisationInfo organisation = new OrganisationInfo(-1);
        return organisation;
    }

    @Override
    public OrganisationInfo getOrganisationInfo(String orgName) {

        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select id, address from onlinetest.organisation where name=?"; //?????
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, orgName);

            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                int idxId = resultSet.findColumn("id");
                int idxAddress=resultSet.findColumn("address");

                long id = resultSet.getLong(idxId);
                String address=resultSet.getString(idxAddress);

                OrganisationInfo info = new OrganisationInfo(id);
                info.setAddress(address);
                info.setName(orgName);

                st.close();
                return info;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public OrganisationInfo getOrganisationInfoWithId(long orgId) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select name, address from onlinetest.organisation where id=?"; //?????
            PreparedStatement st = con.prepareStatement(check);
            st.setLong(1, orgId);

            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                int idxAddress=resultSet.findColumn("address");
                int idxName=resultSet.findColumn("name");

                String address=resultSet.getString(idxAddress);
                String name=resultSet.getString(idxName);

                OrganisationInfo info = new OrganisationInfo(orgId);
                info.setAddress(address);
                info.setName(name);

                st.close();
                return info;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void updateOrganisation(OrganisationInfo organisationInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            int start;

            if (organisationInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.organisation(name, address) value (?,?)");
                start = 1;
            } else {
                st = con.prepareStatement("update onlinetest.organisation set name=?,address=? where id=?");
                st.setLong(3, organisationInfo.getId());
                start = 1;
            }
            st.setString(start + 0, organisationInfo.getName());
            st.setString(start + 1, organisationInfo.getAddress());

            st.execute();
            st.close();

            if (organisationInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    organisationInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeOrganisation(OrganisationInfo organisationInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.organisation where id=?");
            st.setLong(1, organisationInfo.getId());

            st.execute();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean organisationExist(String orgName) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.organisation where name=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, orgName);

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }finally {
            connectionManager.release(ci);
        }

    }

    @Override
    public ClassInfo createClass() {
        ClassInfo classInfo = new ClassInfo(-1);
        return classInfo;
    }

    @Override
    public ClassInfo getClassInfo(String className) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return null;
            String check = "select class_id, organisation_id from onlinetest.class where name=?"; //?????
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, className);


            ResultSet resultSet = st.executeQuery();

            if (!resultSet.next()) {
                st.close();
                return null;
            } else {
                int idxId = resultSet.findColumn("class_id");
                int idxOrgId = resultSet.findColumn("organisation_id");

                long id = resultSet.getLong(idxId);
                long orgId=resultSet.getLong(idxOrgId);

                ClassInfo info = new ClassInfo(id);
//                info.setOrgId(orgId);          ???????????
                info.setName(className);

                st.close();
                return info;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        } finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public ClassInfo getClassInfoById(long id) {
        return null;
    }

    @Override
    public void updateClass(ClassInfo classInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;

            PreparedStatement st;
            int start;

            if (classInfo.getId() <= 0) {
                st = con.prepareStatement("insert into onlinetest.class(organisation_id, name) value (?,?)");
                start=1;
            } else {
                st = con.prepareStatement("update onlinetest.class set organisation_id=?,name=? where class_id=?");
                st.setLong(3, classInfo.getId());
                start=1;
            }

            st.setLong(start+0, classInfo.getOrgId().getId());
            st.setString(start+1, classInfo.getName());

            st.execute();
            st.close();

            if (classInfo.getId() <= 0) {
                st = con.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet = st.executeQuery();

                if (!resultSet.next()) {
                    //???
                } else {
                    long newId = resultSet.getLong(1);
                    classInfo.internalUpdateIdForDAOUseOnly(newId);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public void removeClass(ClassInfo classInfo) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return;
            PreparedStatement st = con.prepareStatement("delete from onlinetest.class where class_id=?");
            st.setLong(1, classInfo.getId());

            st.execute();
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            connectionManager.release(ci);
        }
    }

    @Override
    public boolean classExist(String className) {
        ConnectionInfo ci = connectionManager.acquire();
        try {
            Connection con = ci.getConnection();
            if (con == null) return false;
            String check = "select 1 from onlinetest.class where name=?";
            PreparedStatement st = con.prepareStatement(check);
            st.setString(1, className);

            ResultSet resultSet = st.executeQuery();

            st.execute();
            st.close();

            return resultSet.next();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }finally {
            connectionManager.release(ci);
        }

    }
}
