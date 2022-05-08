import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 5/8/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/

@WebServlet(urlPatterns = "/hello")
public class MyServlet extends HttpServlet {

    public MyServlet() {
        System.out.println("Onna Object Eka Hambu Una");

    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        System.out.println("Onna Mama Servlet Ekak Una");
    }

    @Override
    public void destroy() {

        System.out.println("Onna Mama Mala");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Onna Get Request Ekak Labuna");
    }
}
