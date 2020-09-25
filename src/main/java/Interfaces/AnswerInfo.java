package Interfaces;

public class AnswerInfo {
    private long id;
    private String type;
    private String content;
    private boolean isCorrect;
    private QuestionInfo questionId;

    public AnswerInfo(long idIn){
        id=idIn;
    }

    public long getId() {
        return id;
    }

    public void internalUpdateIdForDAOUseOnly(long newId) { id=newId; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public QuestionInfo getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionInfo questionId) {
        this.questionId = questionId;
    }
}
