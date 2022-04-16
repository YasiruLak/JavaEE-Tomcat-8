import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE
 * @date : 4/16/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/
public class TestDeploymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write("");
    }
}
