package Interfaces;

public class QuestionInfo {
    private long id;
    private TestInfo testId;
    private String type;
    private int numberOfAnswers;
    private String typeOfAnswers;
    private String content;
    private int scoreValue;

    public QuestionInfo(long idIn){
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public String getTypeOfAnswers() {
        return typeOfAnswers;
    }

    public void setTypeOfAnswers(String typeOfAnswers) {
        this.typeOfAnswers = typeOfAnswers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }
}
