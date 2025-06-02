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
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.BD;

/**
 *
 * @author aritz
 */
public class Registrar extends HttpServlet {

     private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   @Override
   public void init(ServletConfig cfg){
       conn=BD.getConexion();
       
   }
   public String hashPassword(String password) throws Exception {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte b : hashBytes) {
        sb.append(String.format("%02x", b));
    }
    return sb.toString();
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String nombre=request.getParameter("nombre");
        String email=request.getParameter("email");
        String password =request.getParameter("password");
        String hashedPassword = hashPassword(password);

        
        try{
            String sql = "SELECT * "
                    + "FROM usuarios "
                    + "wHERE Correo= ? ";
            
           ps=conn.prepareStatement(sql);
           ps.setString(1, email);
           rs=ps.executeQuery();
           
           if(rs.next()){
           
              String error="Correo en uso, utiliza un correo diferente para registrarte.";
                request.getRequestDispatcher("index.jsp?error="+error).forward(request, response);
               
               
               
           }
           else{
                
                 String sql2 ="INSERT INTO usuarios (Correo, Password, Nombre) "
                    + "VALUES (?, ?, ?) ";
            ps=conn.prepareStatement(sql2);
            ps.setString(1, email);
            ps.setString(2, hashedPassword);
            ps.setString(3, nombre);
          
            int i=ps.executeUpdate();
            
            if (i>0){
                response.sendRedirect("inserccionCorrecta2.jsp");
            }
            else{
                response.sendRedirect("error2.jsp");
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
         try {
             processRequest(request, response);
         } catch (Exception ex) {
             Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
         }
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
         try {
             processRequest(request, response);
         } catch (Exception ex) {
             Logger.getLogger(Registrar.class.getName()).log(Level.SEVERE, null, ex);
         }
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
