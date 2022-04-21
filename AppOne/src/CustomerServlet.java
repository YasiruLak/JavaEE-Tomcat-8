import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 4/18/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company","root", "19980611");
            ResultSet resultSet = connection.prepareStatement("select * from Customer").executeQuery();
            while (resultSet.next()){
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                Double salary = resultSet.getDouble(4);
                System.out.println(id + " " + name + " " + address + " " + salary);
            }

            PrintWriter writer = resp.getWriter();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
