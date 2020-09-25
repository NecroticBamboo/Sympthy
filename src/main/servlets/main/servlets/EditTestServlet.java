package main.servlets;

import DI.IServiceLocator;
import Interfaces.ITestManagement;
import Interfaces.QuestionInfo;
import Interfaces.TestInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EditTestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        var context = request.getServletContext();
        var serviceLocator = (IServiceLocator) context.getAttribute("ServiceLocator");
        var testManagement = (ITestManagement) serviceLocator.getService("ITestManagement");

        String oldTestName = request.getParameter("test_name");
        String email = request.getParameter("email");
//        long orgId=Long.parseLong(request.getParameter("orgId"));
        int oldNumberOfQuestions = Integer.parseInt(request.getParameter("oldNumberOfQuestions"));

        String subjectName = request.getParameter("subject_name");
        String newTestName = request.getParameter("new_test_name").replaceAll("\\s+","");
//        Date timer=new Date();
        String isPublic = request.getParameter("isPublic");
        boolean isPublicInBoolean = false;
        if (isPublic.equals("Yes")) {
            isPublicInBoolean = true;
        }
//        int numberOfQuestions = Integer.parseInt(request.getParameter("number_of_questions"));

        TestInfo testInfo = testManagement.getTestInfoByName(oldTestName);
        if (testInfo == null) {
            response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + oldTestName);
        }

        ArrayList<QuestionInfo> list = testManagement.getQuestionInfo(testInfo);
//        int toDelete = oldNumberOfQuestions - numberOfQuestions;

//        if (numberOfQuestions < oldNumberOfQuestions) {
//            for (int i = 0; i < toDelete; i++) {
//                testManagement.removeQuestion(list.get(list.size() - i - 1));
//            }

//        testInfo.setOrgId(orgId);
        testInfo.setSubjectName(subjectName);
//        testInfo.setTimer();
        testInfo.setTestName(newTestName);
        testInfo.setPublic(isPublicInBoolean);
//        testInfo.setNumberOfQuestions(numberOfQuestions);

        testManagement.updateTest(testInfo);
        response.sendRedirect(request.getContextPath() + "/website/HTML/test_with_questions.jsp?test_name=" + newTestName+"&email="+email);
    }
}
