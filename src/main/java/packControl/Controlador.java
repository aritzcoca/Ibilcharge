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
public class Controlador extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String email=request.getParameter("email");
        String password =request.getParameter("password");
       
        
        try{
            String sql = "SELECT * "
                    + "FROM usuarios "
                    + "wHERE Correo= ? AND Password = ? ";
            
           ps=conn.prepareStatement(sql);
           ps.setString(1, email);
           ps.setString(2, hashPassword(password));
           rs=ps.executeQuery();
           
           if(rs.next()){
               int id = rs.getInt("Id");
               String nombre = rs.getString("Nombre");
               session.setAttribute("email", email);
               session.setAttribute("nombre",nombre);
               session.setAttribute("id",id);
               
               request.getRequestDispatcher("gestion.jsp").forward(request, response);
               
           }
           else{
                
                String error="Correo o contraseña incorrecto.";
                request.getRequestDispatcher("index.jsp?error="+error).forward(request, response);
                        
                        
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
           Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
           Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
