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
 * @date : 4/23/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "19980611");
            ResultSet resultSet = connection.prepareStatement("select * from Item").executeQuery();

            String allRecords = "";
            while (resultSet.next()){
                String code = resultSet.getString(1);
                String name = resultSet.getString(2);
                int qty = resultSet.getInt(3);
                double price = resultSet.getDouble(4);

                String item = "{\"code\":\"" + code + "\",\"name\":\"" + name + "\",\"qty\":\"" + qty + "\",\"price\":" + price + "},";
                allRecords = allRecords + item;
            }

            String finalJson = "[" + allRecords.substring(0,allRecords.length()-1) + "]";

            PrintWriter writer = resp.getWriter();
            writer.write(finalJson);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
