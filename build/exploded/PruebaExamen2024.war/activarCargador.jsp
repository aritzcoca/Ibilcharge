<%-- 
    Document   : activarCargador
    Created on : 4 abr 2025, 13:53:05
    Author     : aritz
--%>


<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.SQLException"%>
<%@page import="utils.BD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IbilCharge</title>
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body>
         <a href="gestion.jsp" class="return-button">← Return</a>
        <h1>Activar Cargador</h1>
        
        
        
        
        <%!
        
        private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   
   public void jspInit(){
       conn=BD.getConexion();
       
   }
        
        
        %>
        
        
        <%
            
        session = request.getSession();
          int id = (int) session.getAttribute("id");
          
          
          
          
          try{
              
              
            
            boolean tieneReservaActiva = false;
            String estadoCargador = "OFF"; 
            String sql = "SELECT estado_cargador " +
             "FROM reservas " +
             "WHERE idUsuario = ? " +
             "AND NOW() BETWEEN Fecha AND DATE_ADD(Fecha, INTERVAL 1 HOUR) " +
             "LIMIT 1";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                 if (rs.next()) {
                    tieneReservaActiva = true;
                    estadoCargador = rs.getString("estado_cargador"); // Será "ON" o "OFF"
                }
            
            %>
        
        <% if (tieneReservaActiva) { %>
    <% if ("OFF".equals(estadoCargador)) { %>
        <form action="Activar" method="get">
            <input type="hidden" name="accion" value="activar">
            <input type="submit" name="btnSubmit" value="ACTIVAR" class="boton-verde" style="background-color: #28a745; color: white; padding: 12px 24px; font-size: 16px; border: none; border-radius: 6px; cursor: pointer;">
        </form>
    <% } else { %>
        <form action="Activar" method="get">
            <input type="hidden" name="accion" value="desactivar">
            <input type="submit"  name="btnSubmit" value="DESACTIVAR" class="boton-rojo" style="background-color: #dc3545; color: white; padding: 12px 24px; font-size: 16px; border: none; border-radius: 6px; cursor: pointer;">
        </form>
    <% } %>
<% } else { %>
    <button disabled class="boton-desactivado" style="background-color: #6c757d; color: white; padding: 12px 24px; font-size: 16px; border: none; border-radius: 6px; opacity: 0.7; cursor: not-allowed;">No tienes una reserva activa</button>
<% } 
}catch(SQLException e){

                    System.out.println("error"+e);

}
%>
    </body>

</html>
