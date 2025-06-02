/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package packControl;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.BD;

/**
 *
 * @author aritz
 */
public class RegistrarCoche extends HttpServlet {

     private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   @Override
   public void init(ServletConfig cfg){
       conn=BD.getConexion();
       
   }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
       
          String matricula =request.getParameter("matricula");
           String marca =request.getParameter("marca");
            String modelo =request.getParameter("modelo");
             String color =request.getParameter("color");
             
             try{
            String sql = "SELECT * "
                    + "FROM coche "
                    + "wHERE matricula = ? ";
            
           ps=conn.prepareStatement(sql);
           ps.setString(1, matricula);
          
           rs=ps.executeQuery();
           
           if(rs.next()){
           
            String error="Coche ya registrado.";
                request.getRequestDispatcher("registrarCoche.jsp?error="+error).forward(request, response);
           }
           else{
               
               
             
            String sql2 ="INSERT INTO coche (Matricula, Marca, Modelo, Color) "
                    + "VALUES (?, ?, ?, ?) ";
            ps=conn.prepareStatement(sql2);
            ps.setString(1, matricula);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setString(4, color);
            
            int i=ps.executeUpdate();
            
            if (i>0){
                response.sendRedirect("inserccionCorrecta4.jsp");
            }
            else{
                response.sendRedirect("error4.jsp");
            }
            
       
               
           }
        }
        
           catch(SQLException e){
            
            e.printStackTrace();
            
        }
        
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
