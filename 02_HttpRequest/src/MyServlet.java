import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE
 * @date : 4/8/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/hello")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer =response.getWriter();
        writer.write("Application 2 Response Generated");
    }
}
