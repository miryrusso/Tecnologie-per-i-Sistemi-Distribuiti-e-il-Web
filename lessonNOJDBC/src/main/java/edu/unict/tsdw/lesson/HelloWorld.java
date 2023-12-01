package edu.unict.tsdw.lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*; 

import java.io.IOException;
import java.io.PrintWriter;

// @WebServlet("/hello")
public class HelloWorld extends HttpServlet {
    Connection connnection; 
    final String connectionString = "jdbc:mysql://localhost:3306/students?user=root&password=ruttolibero";

    EntityManager em; 

    public void init() {
        System.out.println("Servlet " + this.getServletName() + " has started");
        try {
             connnection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            // This print goes into catalina.out or standard output with Jetty
            System.out.println("Error while connecting to database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return;
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager(); 
        System.out.println("Connection Successfull");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.write("<html>");
        out.write("<head><title></title></head>");
        out.write("<body>");
        out.write("<h1>Hello Servlet World</h1>");
        //aggiungere query executeQuery prima
        
        /*try {
            Statement stmt = connnection.createStatement();
            ResultSet result = stmt.executeQuery("select name from student");
            while (result.next()) {
                out.write("<p>" + result.getString("name") + "</p>");
            }
        } catch (SQLException e) {
            System.out.println("Error while querying the database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }*/

        //dopo
        List<Student> Student = em
        .createQuery("Select m from Student m ", Student.class)
        .getResultList(); 
        for (Student s : Student) {
            out.write("<p>Student " + s.getName() + " Age "+s.getAge()+"</p>");
        }
    

        out.write("</body></html>");
    }

    /*
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Need to check parameters
        String name=request.getParameter("name");
        Integer age=Integer.parseInt(request.getParameter("age"));
        String query="INSERT INTO student (name, age) VALUES (?, ?)";
        try {
            java.sql.PreparedStatement stmt = connnection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setInt(2, age);
            int rowsAffected =stmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error while insert into the database");
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode()); 
        }
    }
    */

    //dopo->  quando creiamo il nuovo Studente: 
    Student student=new Student();
    student.setName(name);
    student.setAge(age);
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(student);
    transaction.commit();
}