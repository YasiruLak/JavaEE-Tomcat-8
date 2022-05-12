package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 5/12/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
//@WebFilter(urlPatterns = {"/customer","/item","/order"}) // filter only these servlets
@WebFilter(urlPatterns = "/*") //filter all requests
public class MyFilter implements Filter {

    public MyFilter() {
        System.out.println("Object Was Created From MyFilter Class");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Now MyFilter Class is processing Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //Before the request send
        System.out.println("First");


        // without this line the request will not proceed to the servlet
        filterChain.doFilter(servletRequest, servletResponse); // proceed request to the servlet

        PrintWriter writer = servletResponse.getWriter();
        writer.write("Added from MyFilter");


        //
        servletResponse.addHeader("MyCompany", "IJSE");


        //After the servlet response
        System.out.println("Second");
    }

    @Override
    public void destroy() {
        System.out.println("My Filter Was Destroyed");
    }
}
