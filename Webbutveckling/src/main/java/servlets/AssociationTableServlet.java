package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
//QUESTIONS FOR LUKAS, - varför visas inte alla anjanis kurser i php

@WebServlet(urlPatterns = "/atable") //
public class AssociationTableServlet extends HttpServlet { //servlet klassen


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
            ResultSet rs = statement.executeQuery(" SELECT s.id, s.fname, s.lname, c.name, c.description " +
                    "FROM student_course sc " +
                    "LEFT JOIN students s ON sc.student_id = s.id " +
                    "LEFT JOIN courses c ON sc.course_id = c.id " +
                    "ORDER BY s.id");

            PrintWriter out = resp.getWriter();

            String htmlOut = ("<html>"+
                    "<head><title>Association Table</title></head>" +
                    "<link rel='stylesheet' type='text/css' href='styles.css'>" +
                    "<script src=https://cdn.tailwindcss.com></script>" +
                    "</head>" +
                    "<body>" +
                    "<p style='text-align:center'>" +
                    "<a href ='http://localhost:23306/students'>" + "Students |  </a>" +
                    "<a href ='http://localhost:23306/courses'>" + "Courses</a>" +
                    "</p>" +
                    "<div class='bg-white mx-auto text-center w-1/2 py-5 shadow-xl rounded-3xl my-96 max-w-2xl content-center'>" +
                    "<h3 class='table-heading'>Association Table for Student and Course</h3>" +
                    "<table>" +
                    "<tr>" +
                    "<th>ID</th>" +
                    "<th>Firstname</th>" +
                    "<th>Lastname</th>"+
                    "<th>Course</th>"+
                    "<th>Course Description</th>"+
                    "</tr>");

            out.println(htmlOut);

            while (rs.next()) {

                //int id = rs.getInt(1); //spara resultatet i id
                String ID = rs.getString("id");
                String name = rs.getString("fname");
                String lastname = rs.getString("lname");
                String courseName = rs.getString("name");
                String courseDescription = rs.getString("description");

                out.println("<tr>");
                out.println("<td>" + ID + "</td>");
                out.println("<td>" + name + "</td>");
                out.println("<td>" + lastname + "</td>");
                out.println("<td>" + courseName + "</td>");
                out.println("<td>" + courseDescription + "</td>");

                out.println("</tr>");

            }
                out.println ("</div>");
                out.println("</h3>");
                out.println("</table>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                out.close();

            } catch(SQLException | ClassNotFoundException e){
                throw new RuntimeException(e);
            } finally{


            }
        }
    }

