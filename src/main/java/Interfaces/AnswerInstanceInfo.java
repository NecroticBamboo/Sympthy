package Interfaces;

public class AnswerInstanceInfo {
    private int id;
    private TestInstanceInfo testInstanceId;
    private QuestionInfo questionId;
    private String content;

    public AnswerInstanceInfo(int idIn,TestInstanceInfo testInstanceIdIn,QuestionInfo questionIdIn,String contentIn){
        setId(idIn);
        setTestInstanceId(testInstanceIdIn);
        setQuestionId(questionIdIn);
        setContent(contentIn);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TestInstanceInfo getTestInstanceId() {
        return testInstanceId;
    }

    public void setTestInstanceId(TestInstanceInfo testInstanceId) {
        this.testInstanceId = testInstanceId;
    }

    public QuestionInfo getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionInfo questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
