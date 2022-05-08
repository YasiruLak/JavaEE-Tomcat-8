package listeners;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 5/8/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/

@WebListener
public class MyListeners implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("Context Initialized");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/company");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("19980611");
        basicDataSource.setMaxTotal(5);
        basicDataSource.setInitialSize(5);

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("basicDataSource", basicDataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Context Destroyed");
    }
}
