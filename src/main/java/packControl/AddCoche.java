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
public class AddCoche extends HttpServlet {

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
        int id = (int) session.getAttribute("id");
          String matricula =request.getParameter("matricula");
        
             
             try{
            String sql = "SELECT * "
                    + "FROM coche "
                    + "wHERE matricula = ? ";
            
           ps=conn.prepareStatement(sql);
           ps.setString(1, matricula);
          
           rs=ps.executeQuery();
           
           if(rs.next()){
            int idcoche = rs.getInt("idCoche");
            
           String sql2 = "SELECT * "
                    + "FROM usuariocoche "
                    + "wHERE Id = ? and IdCoche = ? ";
            
           ps=conn.prepareStatement(sql2);
           ps.setInt(1, id);
           ps.setInt(2, idcoche);
          
           rs=ps.executeQuery();
            
            if(rs.next()){
                
                 String error="Coche ya añadido a tu perfil.";
                request.getRequestDispatcher("añadirCoche.jsp?error="+error).forward(request, response);
            }
            else{
            String sql3= "INSERT INTO usuariocoche (Id, IdCoche) "
                    + "VALUES (?, ?) ";
            
             ps=conn.prepareStatement(sql3);
            ps.setInt(1, id);
            ps.setInt(2, idcoche);
        int i=ps.executeUpdate();
            
            if (i>0){
                response.sendRedirect("inserccionCorrecta5.jsp");
            }
            else{
                response.sendRedirect("error5.jsp");
            }
           }}
           
           else{
               
             
                request.getRequestDispatcher("cocheSinRegistrar.jsp").forward(request, response);
       
               
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
