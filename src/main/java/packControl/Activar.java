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
import java.util.Map;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.BD;

/**
 *
 * @author aritz
 */
public class Activar extends HttpServlet {
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
    int id = (int) session.getAttribute("id"); // ID del usuario logueado

    String accion = request.getParameter("accion"); // "activar" o "desactivar"
    String nuevoEstado = accion.equals("activar") ? "ON" : "OFF";
    
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    
    Map<String, String> mapa = System.getenv();
    String url = mapa.getOrDefault("OCPP_URL", "http://ocpp:5000");
    
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build();
    
    
    ServicioOCPP service = retrofit.create(ServicioOCPP.class);
    
    if(nuevoEstado.equals("ON")){
        Response<Map<String,Object>> respuesta = service.start().execute();
       System.out.println(respuesta); 
        System.out.println(respuesta.body()); 

    }
        
        
    else  {
       Response<Map<String,Object>> respuesta = service.stop().execute();
       System.out.println(respuesta); 
        System.out.println(respuesta.body()); 

    }  
        
    
         try{
             
             
             
             String sql = "UPDATE reservas SET estado_cargador = ? " +
                     "WHERE idUsuario = ? " +
                     "AND NOW() BETWEEN Fecha AND DATE_ADD(Fecha, INTERVAL 1 HOUR)";

        ps = conn.prepareStatement(sql);
        ps.setString(1, nuevoEstado);
        ps.setInt(2, id);
        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            response.sendRedirect("activarCargador.jsp");
        } else {
            response.sendRedirect("activarCargador.jsp?error=No se encontró ninguna reserva activa");
        }

    }
          catch(SQLException e){
            
            e.printStackTrace();
            
        } }
  
        
          
        
    
    
    

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
