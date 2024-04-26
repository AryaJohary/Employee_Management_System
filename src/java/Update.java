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

/**
 *
 * @author aryaj
 */
public class Update extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("txtUID_update"));
        String name = request.getParameter("txtName_update");
        int age = Integer.parseInt(request.getParameter("txtAge_update"));
        int salary = Integer.parseInt(request.getParameter("txtSalary_update"));
        String pass = request.getParameter("txtPass_update");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");            
            out.println("</head>");
            out.println("<body>");
            if(updateUser(id,name,age,salary,pass)>0){
                out.println("<h1>User Details updated..</h1>");
            }else{
                out.println("<h1>Unable to update user details.</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private int updateUser(int id, String name,int age, int salary, String pass){
        int ret = 0;
    try{
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE db_employee SET Name=?,Age=?,Salary=?,Password=? WHERE ID=?");
        ps.setString(1,name);
        ps.setInt(2,age);
        ps.setInt(3,salary);
        ps.setString(4, pass);
        ps.setInt(5,id);
        ret = ps.executeUpdate();
    }catch(Exception ex){}
        return ret;
    }
    
    private Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn;
        String dbUrl = "jdbc:mysql://localhost:3306/";
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbName = "db_arya";
        String dbUser = "root";
        String dbPass = "";
    
        Class.forName(dbDriver);
        conn = DriverManager.getConnection(dbUrl+dbName,dbUser,dbPass);
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
