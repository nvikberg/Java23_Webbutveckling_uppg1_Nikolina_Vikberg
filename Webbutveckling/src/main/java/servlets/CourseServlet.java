package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

    @WebServlet(urlPatterns = "/courses") //
    public class CourseServlet extends HttpServlet { //servlet klassen


        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //dependency måste läggas in i POM
            //PASTA IN KODEN FRÅN SLIDSEN , men bäst att skapa detta i en egen klass
            //skapa 3 servletss i sina egna klasser
            //bäst att skapa objekt som hanterar databas hantering
            //anropa queries istället för att skapa nya varje gång


            try {

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:13306/gritacademy", "userSELECT", "user");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM courses");

                //System.out.println(name + "name");
                //String htmlOut = ("" + id + ""); //spara alla htlm grejs i en sträng

                PrintWriter out = resp.getWriter();
                String htmlOut = ("<html>" +
                       "<head><title>Courses</title></head>" +
                        "<link rel='stylesheet' type='text/css' href='styles.css'>" +
                        "<script src=https://cdn.tailwindcss.com></script>" +
                        "</head>" +
                        "<body>" +
                        "<p style='text-align:center'>" +
                        "<a href ='http://localhost:23306/students'>" + "Students | </a>" +
                        "<a href ='http://localhost:23306/atable'>" + "Association Table   </a>" +
                        "</p>" +
                        "<div class='bg-white mx-auto text-center w-1/2 py-5 shadow-xl rounded-3xl my-96 max-w-2xl content-center'>" +
                        "<h3 class='table-heading'>Courses</h3>" +
                        "<table>" +
                        "<tr>" +
                        "<th>ID</td>" +
                        "<th>Course</td>" +
                        "<th>Points</td>" +
                        "<th>Description</td>" +
                        "</tr>");

                out.println(htmlOut);

                    while (rs.next()) {

                        int id = rs.getInt(1); //spara resultatet i id

                        String courseName = rs.getString("name");
                        String points = rs.getString("points");
                        String description = rs.getString("description");

                        out.println("<tr>");
                        out.println("<td>" + id + "</td>");
                        out.println("<td>" + courseName + "</td>");
                        out.println("<td>" + points + "</td>");
                        out.println("<td>" + description + "</td>");
                        out.println("</tr>");
                    }

                    out.println("</table>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        }


