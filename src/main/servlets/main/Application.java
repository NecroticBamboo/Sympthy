//package main;
//
//import DAO.TestManagement;
//import DAO.UserManagement;
//import Interfaces.ITestManagement;
//import Interfaces.IUserManagement;
//import main.servlets.*;
//import main.utils.*;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
//@WebListener
//public class Application implements ServletContextListener {
//    private IDatabaseManager databaseManagement;
//    private IUserManagement userManagement;
//    private ITestManagement testManagement;
//
//    public Application()
//    {
//        databaseManagement = new DatabaseConnection();
//        userManagement = new UserManagement(databaseManagement);
//        testManagement = new TestManagement(databaseManagement);
//    }
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContext context = sce.getServletContext();
//        context.addServlet("checkin", new CheckInServlet()).addMapping("/*");
//        context.addServlet("create-org", new CreateOrganisationServlet()).addMapping("/*");
//        context.addServlet("sign-up", new SignUpServlet()).addMapping("/*");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//
//    }
//}