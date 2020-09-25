package main.servlets;

import DI.IServiceLocator;
import Interfaces.AnswerInfo;
import Interfaces.ITestManagement;
import Interfaces.QuestionInfo;
import Interfaces.TestInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EditQuestionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

        String testName=request.getParameter("test_name");
        String email=request.getParameter("email");
        long questionId = Long.parseLong(request.getParameter("questionId"));
        int oldNumberOfAnswers = Integer.parseInt(request.getParameter("oldNumberOfAnswers"));

        String questionContent=request.getParameter("question_content");
        String questionType=request.getParameter("question_type");
        int scoreValue=Integer.parseInt(request.getParameter("score_value"));
        int numberOfAnswers=Integer.parseInt(request.getParameter("number_of_answers"));
        String answerType=request.getParameter("answer_type");

        TestInfo test=testManagement.getTestInfoByName(testName);
        QuestionInfo question=testManagement.getQuestionInfoById(questionId);

        int toDelete=oldNumberOfAnswers-numberOfAnswers;
        ArrayList<AnswerInfo> list=testManagement.getAllAnswersInfo(question);

        if(list.size()!=oldNumberOfAnswers){
            question.setNumberOfAnswers(numberOfAnswers);
        }
        else if(numberOfAnswers<oldNumberOfAnswers){
            for(int i=0;i<toDelete;i++){
                testManagement.removeAnswer(list.get(list.size()-i-1));
            }
        }

        question.setTestId(test);
        question.setContent(questionContent);
        question.setType(questionType);
        question.setScoreValue(scoreValue);
        question.setNumberOfAnswers(numberOfAnswers);
        question.setTypeOfAnswers(answerType);

        testManagement.updateQuestion(question);

        response.sendRedirect(request.getContextPath()+"/website/HTML/test_with_questions.jsp?test_name="+testName+"&email="+email);
    }
}
