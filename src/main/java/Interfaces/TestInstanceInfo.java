package Interfaces;

import java.util.Date;

public class TestInstanceInfo {
    private int id;
    private TestInfo testId;
    private ClassInfo classId;
    private Date deadline;
    private String name;

    public TestInstanceInfo(int idIn,TestInfo testIdIn,ClassInfo classIdIn,Date deadlineIn,String nameIn){
        setId(idIn);
        setTestId(testIdIn);
        setClassId(classIdIn);
        setDeadline(deadlineIn);
        setName(nameIn);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestInfo getTestId() {
        return testId;
    }

    public void setTestId(TestInfo testId) {
        this.testId = testId;
    }

    public ClassInfo getClassId() {
        return classId;
    }

    public void setClassId(ClassInfo classId) {
        this.classId = classId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
