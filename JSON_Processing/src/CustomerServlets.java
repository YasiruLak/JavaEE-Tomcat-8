import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 4/28/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/customer")
public class CustomerServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("application/json");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/company", "root", "19980611");
            ResultSet resultSet = connection.prepareStatement("select * from Customer").executeQuery();

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                double salary = resultSet.getDouble(4);

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id", id);
                objectBuilder.add("name", name);
                objectBuilder.add("address", address);
                objectBuilder.add("salary", salary);

                arrayBuilder.add(objectBuilder.build());

            }

            PrintWriter writer = resp.getWriter();

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 200);
            response.add("message", "Done");
            response.add("data", arrayBuilder.build());

            writer.print(response.build());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("customerID");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/company", "root", "19980611");
            PreparedStatement pstm = connection.prepareStatement("Insert into Customer values(?,?,?,?)");
            pstm.setObject(1, customerID);
            pstm.setObject(2, customerName);
            pstm.setObject(3, customerAddress);
            pstm.setObject(4, customerSalary);


            if (pstm.executeUpdate() > 0) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                response.add("status", 200);
                response.add("message", "Successfully Added");
                response.add("data", "");
                writer.print(response.build());
            }

        } catch (ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", e.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        } catch (SQLException throwables) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("status", 400);
            response.add("message", "Error");
            response.add("data", throwables.getLocalizedMessage());
            writer.print(response.build());

            resp.setStatus(HttpServletResponse.SC_OK);
            throwables.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerID = req.getParameter("cusID");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/company", "root", "19980611");
            PreparedStatement pstm = connection.prepareStatement("Delete from Customer where id=?");
            pstm.setObject(1, customerID);

            if (pstm.executeUpdate() > 0) {

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("data", "");
                objectBuilder.add("message", "Successfully Deleted");
                writer.print(objectBuilder.build());

            } else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Wrong Id Inserted");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());


        } catch (SQLException throwables) {
            throwables.printStackTrace();

            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerID = req.getParameter("customerID");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerSalary = req.getParameter("customerSalary");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/company", "root", "19980611");
            PreparedStatement pstm = connection.prepareStatement("Update Customer set name=?,address=?,salary=? where id=?");
            pstm.setObject(1, customerName);
            pstm.setObject(2, customerAddress);
            pstm.setObject(3, customerSalary);
            pstm.setObject(4, customerID);

            boolean b = pstm.executeUpdate() > 0;
            PrintWriter writer = resp.getWriter();

            if (b) {
                writer.write("Customer Updated");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
