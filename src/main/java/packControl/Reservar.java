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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import utils.BD;

/**
 *
 * @author aritz
 */
public class Reservar extends HttpServlet {

     private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   @Override
   public void init(ServletConfig cfg){
       conn=BD.getConexion();
       
   }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          HttpSession session  = request.getSession();
          int id = (int) session.getAttribute("id");
         

         String fecha = request.getParameter("fecha");  
            String hora = request.getParameter("hora");   
       int idcoche = Integer.parseInt(request.getParameter("idCoche"));
        
      
       
            String fechaHoraStr = fecha + "T" + hora; // ej: "2025-04-20T12:00"
            LocalDateTime fechaReserva;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            fechaReserva = LocalDateTime.parse(fechaHoraStr, formatter);
        
            String fechaStr = fechaReserva.format(formatter);
        

   
               
        try{
        
       String sql3 = "SELECT COUNT(*) AS totalReservas FROM reservas WHERE idUsuario = ? AND Fecha > NOW()";

               
               ps=conn.prepareStatement(sql3);
                ps.setInt(1, id);
                rs=ps.executeQuery();
                
                int totalReservas = 0;
                if (rs.next()) {
                 totalReservas = rs.getInt("totalReservas");
                            }
                if(totalReservas<5){
 
             
            String sql2 ="INSERT INTO reservas (Fecha, idUsuario, idCoche) "
                    + "VALUES (?, ?, ?) ";
            ps=conn.prepareStatement(sql2);
            ps.setString(1, fechaStr);
            ps.setInt(2, id);
            ps.setInt(3, idcoche);
          
            int i=ps.executeUpdate();
            
            if (i>0){
                session.setAttribute("date",fecha);
                session.setAttribute("hour",hora);
                
                response.sendRedirect("inserccionCorrecta.jsp");
            }
            else{
                response.sendRedirect("error.jsp");
            }
            
       
                } 
                else{
                    String error=  "Has alcanzado el número máximo de reservas activas";
        request.getRequestDispatcher("ReservarCargador.jsp?error="+error).forward(request, response);
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
