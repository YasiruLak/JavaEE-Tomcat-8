import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE-Tomcat-8
 * @date : 4/28/2022
 * @month : 04
 * @year : 2022
 * @since : 0.1.0
 **/

@WebServlet(urlPatterns = "/customer")
public class JsonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");

//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//
//        objectBuilder.add("id", "C001");
//        objectBuilder.add("name", "yasiru");
//        objectBuilder.add("address", "Galle");
//        objectBuilder.add("salary", 1000.00);
//
//        JsonObject build = objectBuilder.build();
//
//
//        PrintWriter writer = resp.getWriter();
//        writer.println(build);


        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("id", "C001");
        objectBuilder.add("name", "yasiru");
        objectBuilder.add("address", "Galle");
        objectBuilder.add("salary", 1000.00);

        JsonObjectBuilder objectBuilder2 = Json.createObjectBuilder();

        objectBuilder2.add("id", "C002");
        objectBuilder2.add("name", "Amal");
        objectBuilder2.add("address", "Galle");
        objectBuilder2.add("salary", 2000.00);

        arrayBuilder.add(objectBuilder.build());
        arrayBuilder.add(objectBuilder2.build());

        PrintWriter writer = resp.getWriter();

        writer.print(arrayBuilder.build());


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        ServletInputStream stream = req.getInputStream();
//
//        int read;
//
//        while ((read = stream.read()) != -1) {
//            System.out.print((char) read);
//        }

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        System.out.println(id);
    }
}
