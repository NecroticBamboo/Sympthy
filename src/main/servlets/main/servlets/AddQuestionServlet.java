package main.servlets;

import DI.IServiceLocator;
import Interfaces.ITestManagement;
import Interfaces.QuestionInfo;
import Interfaces.TestInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddQuestionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context = request.getServletContext();
        var serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");

        String testName = request.getParameter("test_name");
        String email = request.getParameter("email");
        String submit = request.getParameter("submit");
        TestInfo test = testManagement.getTestInfoByName(testName);

        String questionContent = request.getParameter("question_content");
        String questionType = request.getParameter("question_type");
        String answerType = request.getParameter("answer_type");
        String scoreValue = request.getParameter("score_value");
        String numberOfAnswers = request.getParameter("number_of_answers");

        if (submit.equals("Stop") || questionContent.equals("") || scoreValue.equals("") || numberOfAnswers.equals("")) {
            response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + test.getTestName() + "&email=" + email);
            return;
        }

        int scoreValueInt = Integer.parseInt(scoreValue);
        int numberOfAnswersInt = Integer.parseInt(numberOfAnswers);

        if(scoreValueInt==0||numberOfAnswersInt==0){
            response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + test.getTestName() + "&email=" + email);
            return;
        }

        QuestionInfo question = testManagement.createQuestion(test);

        test.setNumberOfQuestions(test.getNumberOfQuestions() + 1);
        testManagement.updateTest(test);

        question.setTestId(test);
        question.setType(questionType);
        question.setNumberOfAnswers(numberOfAnswersInt);
        question.setTypeOfAnswers(answerType);
        question.setContent(questionContent);
        question.setScoreValue(scoreValueInt);

        testManagement.updateQuestion(question);

        response.sendRedirect(request.getContextPath() + "/website/HTML/add_question_to_test.jsp?test_name=" + testName + "&email=" + email);
    }
}
