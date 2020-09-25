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

public class SubmitAnswerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

        String email = request.getParameter("student_email");
        String testName = request.getParameter("test_name");
        StringBuilder answerSequence=new StringBuilder(request.getParameter("answer_sequence"));

        int currentQuestionIndex = Integer.parseInt(request.getParameter("currentQuestionIndex"));
        int totalQuestions = Integer.parseInt(request.getParameter("totalQuestions"));

        String answer=request.getParameter("answer");

        TestInfo testInfo=testManagement.getTestInfoByName(testName);
        ArrayList<QuestionInfo> list=testManagement.getQuestionInfo(testInfo);
        QuestionInfo currentQuestion=list.get(currentQuestionIndex-1);
        ArrayList<AnswerInfo> correctAnswersList=testManagement.getCorrectAnswers(currentQuestion);

        if(correctAnswersList.get(0).getContent().equals(answer)){
            answerSequence.setCharAt(currentQuestionIndex-1,'1');
        }

        currentQuestionIndex++;

//        PrintWriter out = response.getWriter();
        if(currentQuestionIndex>totalQuestions){
            response.sendRedirect(request.getContextPath()+"/website/HTML/end_results.jsp?answer_sequence="+answerSequence+"&student_email="+email+"&test_name="+testName);
//            out.println(answerSequence);
        } else{
            response.sendRedirect(request.getContextPath()+"/website/HTML/take_test.jsp?currentQuestionIndex="+currentQuestionIndex+"&totalQuestions="+totalQuestions+"&answer_sequence="+answerSequence+"&student_email="+email+"&test_name="+testName);
        }



    }
}
