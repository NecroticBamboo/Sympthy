package main.servlets;

import DI.IServiceLocator;
import Interfaces.ITestManagement;
import Interfaces.TestInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FindTestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        var context=request.getServletContext();
        var serviceLocator=(IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement=(ITestManagement) serviceLocator.getService("ITestManagement");

        String testName=request.getParameter("test_name").replaceAll("\\s+","");
        String email=request.getParameter("email");

        TestInfo holder=testManagement.getTestInfoByName(testName);

//        int numberOfQuestionsLeftToFill=testManagement.getNumberOfNonFilledQuestions(holder);

        if (holder==null){
            PrintWriter out = response.getWriter();
            out.println("That test doesn't exist!");
        } else{
            response.sendRedirect(request.getContextPath()+"/website/HTML/test_with_questions.jsp?test_name="+testName+"&email="+email);
        }
//        if (testManagement.testHasQuestions(holder)){
//            response.sendRedirect(request.getContextPath() + "/website/HTML/add_question_to_test.jsp?test_name="+testName+"&currentQuestionIndex=1&numberOfQuestions="+holder.getNumberOfQuestions()+"&email="+email);
//        } else if(numberOfQuestionsLeftToFill>0){
//            int numberOfQuestions=holder.getNumberOfQuestions();
//            response.sendRedirect(request.getContextPath()+"/website/HTML/add_question_to_test.jsp?test_name="+testName+"&currentQuestionIndex="+(numberOfQuestions-numberOfQuestionsLeftToFill+1)+"&numberOfQuestions="+holder.getNumberOfQuestions()+"&email="+email);
//        } else {
//            response.sendRedirect(request.getContextPath()+"/website/HTML/test_with_questions.jsp?test_name="+testName+"&email="+email);
//        }

    }
}
