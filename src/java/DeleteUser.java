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
public class DeleteUser extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("txtUID_delete"));
        String pass = request.getParameter("txtPass_delete");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUser</title>");            
            out.println("</head>");
            out.println("<body>");
            if(deleteUser(id,pass)>0){
                out.println("<h1>User Details deleted..</h1>");
            }else{
                out.println("<h1>Unable to delete user details.</h1>");
            }
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private int deleteUser(int id,String pass){
        int ret = 0;
    try{
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE from db_employee WHERE ID=? and Password=?");
        ps.setInt(1,id);
        ps.setString(2,pass);
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
