package main.servlets;

import DI.IServiceLocator;
import Interfaces.AnswerInfo;
import Interfaces.ITestManagement;
import Interfaces.QuestionInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnswerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

        long questionId=Long.parseLong(request.getParameter("questionId"));
        int currentAnswerIndex=Integer.parseInt(request.getParameter("currentAnswerIndex"));
        int numberOfAnswers = Integer.parseInt(request.getParameter("numberOfAnswers"));
        String testName=request.getParameter("test_name");
        String email=request.getParameter("email");

        String answerContent=request.getParameter("answer_content");
        String answerType=request.getParameter("answer_type");
        boolean isCorrect=true;
        String isCorrectString=request.getParameter("isCorrect");
        if(isCorrectString.equals("No")){
            isCorrect=false;
        }

        QuestionInfo question=testManagement.getQuestionInfoById(questionId);
        AnswerInfo answerInfo=testManagement.createAnswer(question);

        answerInfo.setType(answerType);
        answerInfo.setContent(answerContent);
        answerInfo.setCorrect(isCorrect);
        answerInfo.setQuestionId(question);

        testManagement.updateAnswer(answerInfo);
        currentAnswerIndex++;

        if(currentAnswerIndex>numberOfAnswers){
            response.sendRedirect(request.getContextPath()+"/website/HTML/test_with_questions.jsp?test_name="+testName+"&email="+email);
        } else {
            response.sendRedirect(request.getContextPath()+"/website/HTML/add_answers.jsp?questionId=" + questionId + "&numberOfAnswers="+numberOfAnswers+"&currentAnswerIndex="+currentAnswerIndex+"&test_name="+testName+"&email="+email);
        }
//        PrintWriter out = response.getWriter();
//        out.println("ok");
    }
}
