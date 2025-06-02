<%-- 
    Document   : buscarCitas
    Created on : 3 ene 2025, 9:39:05
    Author     : aritz
--%>

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
        <h1>VER MIS RESERVAS</h1>
        <%=session.getAttribute("nombre")%> tus reservas para utilizar el cargador son las siguientes:
        
        <%!
        
        private Connection conn;
   private PreparedStatement ps;
   private ResultSet rs;
   
   
   public void jspInit(){
       conn=BD.getConexion();
       
   }
        
        
        %>
        
        <%
            int id = (int) session.getAttribute("id");
            
            try{
            
           String sql = "SELECT r.Fecha, c.Matricula " +
             "FROM reservas AS r " +
             "JOIN coche AS c ON r.idCoche = c.idCoche " +
             "WHERE r.idUsuario = ?";
            
            ps=conn.prepareStatement(sql);
            ps.setInt(1, id);
         
           rs=ps.executeQuery();
            
            if(!rs.isBeforeFirst()){
            
        
        %>
        <h4>No hay RESERVAS disponibles</h4>
        <% 
            }else{
        %>
        <table border="3">
            <tr>
                
                <th>Fecha</th><th>Matrícula</th><th>Eliminar Reserva</th>
                
            </tr>
            
            <%
              while(rs.next()){
              
             
              String fecha=rs.getString("r.Fecha");
              String matricula=rs.getString("c.Matricula");
              
               java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               java.util.Date fechaReserva = sdf.parse(fecha);
               java.util.Date ahora = new java.util.Date();

                if(fechaReserva.after(ahora)){
                 
                %>
            
                <tr>
                    
                    <td><%=fecha%></td><td><%=matricula%></td><td><form action="Eliminar" method="get">
            
                    <input type="hidden" name="fecha" value="<%= fecha %>">
                    <input type="submit"  name="btnSubmit" value="Eliminar Reserva">
                    
                    </form></td>
                    
                    
                </tr>
                <%
                    } }}}catch(SQLException e){

                    System.out.println("error"+e);

}
                    %>
                
        </table>
    </body>
</html>
