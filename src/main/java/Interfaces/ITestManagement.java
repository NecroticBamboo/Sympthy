package Interfaces;

import java.util.ArrayList;

public interface ITestManagement {
    TestInfo createTest(OrganisationInfo organisationInfo);
    void updateTest(TestInfo testInfo);
    void removeTest(TestInfo testInfo);
    ArrayList<TestInfo> getTestInfo(OrganisationInfo organisationInfo);
    ArrayList<TestInfo> getTestInfoBySubject(String subjectName);
    TestInfo getTestInfoByName(String testName);
    boolean testHasQuestions(TestInfo testInfo);
    int getNumberOfNonFilledQuestions(TestInfo testInfo);
    boolean testExist(OrganisationInfo organisationInfo,String testName);

    QuestionInfo createQuestion(TestInfo testInfo);
    void updateQuestion(QuestionInfo questionInfo);
    void removeQuestion(QuestionInfo questionInfo);
    ArrayList<QuestionInfo> getQuestionInfo(TestInfo testInfo);
    QuestionInfo getQuestionInfoById(long questionId);

    boolean questionExist(TestInfo testInfo,String content);

    AnswerInfo createAnswer(QuestionInfo questionInfo);
    void updateAnswer(AnswerInfo answerInfo);
    void removeAnswer(AnswerInfo answerInfo);
    ArrayList<AnswerInfo> getAllAnswersInfo(QuestionInfo questionInfo);
    AnswerInfo getSpecificAnswerInfo(QuestionInfo questionInfo,AnswerInfo answerInfo);
    ArrayList<AnswerInfo> getCorrectAnswers(QuestionInfo questionInfo);
    boolean answerCorrect(AnswerInfo answerInfo);
    boolean answerExist(QuestionInfo questionInfo,String content);
    int getNumberOfNonFilledAnswers(QuestionInfo questionInfo);

    void addAuthor(TestInfo testInfo,UserInfo userInfo);
    void removeAuthor(TestInfo testInfo,UserInfo userInfo);
    ArrayList<UserInfo> getAuthorsInfo(TestInfo testInfo);
    boolean authorExist(TestInfo testInfo,UserInfo userInfo);
}
