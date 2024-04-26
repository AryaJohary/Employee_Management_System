/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aryaj
 */
public class display extends HttpServlet {

    Connection conn;

    public display() {

        try {
            this.conn = getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(display.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("txtUID_login"));
        String pass = request.getParameter("txtPass_login");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet display</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<div class=\"sub-container\">");
            out.println("<h1>Employee Details</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr><th>Attribute</th><th>Value</th></tr>");
            out.println("<tr><td>Employee ID</td><td>" + id + "</td></tr>");
            out.println("<tr><td>Employee Name</td><td>" + getName(id, pass) + "</td></tr>");
            out.println("<tr><td>Employee Age</td><td>" + getAge(id, pass) + "</td></tr>");
            out.println("<tr><td>Employee Salary</td><td>" + getSalary(id, pass) + "</td></tr>");
            out.println("</table>");
            out.println("<button class=\"make_change\" onclick=\"location.href='detailsPage.html'\">Make changes</button>");           
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getName(int id, String pass) {
        String name = "";
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Name FROM db_employee WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString("Name");
            }
            return name;
        } catch (Exception ex) {
        }
        return name;
    }

    private int getAge(int id, String pass) {
        int age = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Age FROM db_employee WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                age = rs.getInt(1);
            }
            return age;
        } catch (Exception ex) {
        }
        return age;
    }

    private int getSalary(int id, String pass) {
        int salary = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT Salary FROM db_employee WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                salary = rs.getInt(1);
            }
            return salary;
        } catch (Exception ex) {
        }
        return salary;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbName = "db_arya";
        String dbUser = "root";
        String dbPass = "";

        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl + dbName, dbUser, dbPass);
        return conn;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
