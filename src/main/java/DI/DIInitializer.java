package DI;

import DAO.TestManagement;
import DAO.UserManagement;
import main.utils.DatabaseConnection;
import main.utils.IDatabaseManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DIInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
//
//        String url = ctx.getInitParameter("DBURL");
//        String u = ctx.getInitParameter("DBUSER");
//        String p = ctx.getInitParameter("DBPWD");
//

        ServiceLocator di = new ServiceLocator();
        ctx.setAttribute("ServiceLocator", di);


        //create database connection from init parameters and set it to context
        IDatabaseManager dbManager = new DatabaseConnection();
        di.setService( "IDatabaseManager", dbManager);

        var userManager = new UserManagement(di);
        di.setService( "IUserManagement", userManager);

        var testManager = new TestManagement(di);
        di.setService( "ITestManagement", testManager);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
//        DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
//        dbManager.closeConnection();
//        System.out.println("Database connection closed for Application.");

    }

}