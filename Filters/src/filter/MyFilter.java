package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebFilter(urlPatterns = "/*")
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

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        System.out.println("First");


        filterChain.doFilter(servletRequest, servletResponse);

        PrintWriter writer = servletResponse.getWriter();
        writer.write("Added from MyFilter");


        res.addHeader("MyCompany", "IJSE");

        System.out.println("Second");
    }

    @Override
    public void destroy() {
        System.out.println("My Filter Was Destroyed");
    }
}
