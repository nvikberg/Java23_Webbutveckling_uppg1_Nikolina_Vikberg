package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

    @WebServlet(urlPatterns = "/students") //
    public class StudentServlet extends HttpServlet { //servlet klassen


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy", "userSELECT", "user");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM students");

                PrintWriter out = resp.getWriter();
                String htmlOut = ("<html>" +
                        "<head><title>Students</title></head>" +
                        "<link rel='stylesheet' type='text/css' href='styles.css'>" +
                        "<script src=https://cdn.tailwindcss.com></script>" +
                        "</head>" +
                        "<body>" +
                        "<p style='text-align:center'>" +
                        "<a href ='http://localhost:23306/courses'>" + "Courses | </a>" +
                        "<a href ='http://localhost:23306/atable'>" + "Association Table   </a>" +
                        "</p>" +
                        "<div class='bg-white mx-auto text-center w-1/2 py-5 shadow-xl rounded-3xl my-96 max-w-2xl content-center'>" +
                        "<h3 class='table-heading'>Students</h3>" +
                        "<table>" +
                        "<tr>" +
                        "<th>ID</th>" +
                        "<th>Name</th>" +
                        "<th>Town</th>" +
                        "<th>Hobby</th>" +
                        "</tr>");

                    out.println(htmlOut);
                    while (rs.next()) {

                        int Id = rs.getInt(1); //spara resultatet i id

                        String Name = rs.getString("fName") + " " + rs.getString("lName");
                        String Town = rs.getString("town");
                        String Hobby = rs.getString("hobby");

                        out.println("<tr>"); //andra tables row med all table data
                        out.println("<td>" + Id + "</td>");
                        out.println("<td>" + Name + "</td>");
                        out.println("<td>" + Town + "</td>");
                        out.println("<td>" + Hobby + "</td>");
                        out.println("</tr>");
                    }

                        out.println("</table>");
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");
                 //   System.out.println(req.getParameter("name"));


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


